package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Historial_Asignacion_Evidencia;
import com.sistema.examenes.projection.HistorialAsignacionEvidenciaProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Historial_Asignacion_Evidencia_repository extends JpaRepository<Historial_Asignacion_Evidencia, Long> {
    @Query(value = "SELECT per.primer_nombre ||' '|| per.primer_apellido AS nombre_usuario, " +
            "h.fecha, " +
            "a.fecha_fin, " +
            "a.fecha_inicio, " +
            "e.estado, " +
            "e.descripcion AS titulo_evidencia, " +
            "i.nombre AS titulo_indicador, " +
            "sc.nombre AS titulo_subcriterio, " +
            "c.nombre AS titulo_criterio " +
            "FROM historial_asignacion_evidencia h " +
            "JOIN asignacion_evidencia a ON h.asignacion_evi_id_asignacion_evidencia = a.id_asignacion_evidencia " +
            "JOIN evidencia e ON a.evidencia_id_evidencia = e.id_evidencia " +
            "JOIN indicador i ON e.indicador_id_indicador = i.id_indicador " +
            "JOIN subcriterio sc ON i.subcriterio_id_subcriterio = sc.id_subcriterio " +
            "JOIN criterio c ON sc.id_criterio = c.id_criterio " +
            "JOIN usuarios u ON u.id = a.usuario_id " +
            "JOIN persona per ON per.id_persona = u.persona_id_persona " +
            "WHERE h.usuario_asignador_id = :userId and c.id_criterio = :critId " +
            "and a.visible=CAST(:veri AS BOOLEAN) " +
            "ORDER BY h.fecha DESC", nativeQuery = true)
    List<HistorialAsignacionEvidenciaProjection> obtenerHistorialPorUsuario(@Param("userId") Long userId, @Param("critId") Long critId, String veri);
}
