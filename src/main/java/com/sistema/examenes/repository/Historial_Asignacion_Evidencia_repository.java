package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Historial_Asignacion_Evidencia;
import com.sistema.examenes.projection.HistorialAsignacionEvidenciaProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Historial_Asignacion_Evidencia_repository extends JpaRepository<Historial_Asignacion_Evidencia, Long> {

    @Query(value = "SELECT per.primer_nombre ||' '|| per.primer_apellido AS nombre_usuario, " +
            "h.fecha AS fecha, " +
            "a.fecha_fin AS fecha_fin, " +
            "a.fecha_inicio AS fecha_inicio, " +
            "e.estado AS estado, " +
            "e.descripcion AS titulo_evidencia, " +
            "i.nombre AS titulo_indicador, " +
            "sc.nombre AS titulo_subcriterio, " +
            "c.nombre AS titulo_criterio " +
            "FROM Historial_Asignacion_Evidencia h " +
            "JOIN asignacion_evidencia a ON h.asignacion_evi_id_asignacion_evidencia = a.id_asignacion_evidencia " +
            "JOIN evidencia e ON a.evidencia_id_evidencia = e.id_evidencia  " +
            "JOIN indicador i ON e.indicador_id_indicador = i.id_indicador " +
            "JOIN subcriterio sc ON i.subcriterio_id_subcriterio = sc.id_subcriterio " +
            "JOIN criterio c ON sc.id_criterio = c.id_criterio " +
            "JOIN Usuarios u ON a.usuario_id = u.id " +
            "JOIN persona per ON u.persona_id_persona = per.id_persona " +
            "WHERE c.id_criterio = :critId " +
            "AND a.visible= :veri " +
            "AND h.id_modelo = :idModel " +
            "ORDER BY h.fecha, CAST(SUBSTRING(e.descripcion FROM '^[0-9]+') AS INTEGER), e.descripcion DESC ", nativeQuery = true)
    List<HistorialAsignacionEvidenciaProjection> obtenerHistorialPorUsuario( @Param("critId") Long critId, boolean veri, @Param("idModel") Long idModel);
}
