package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Historial_Asignacion_Evidencia;
import com.sistema.examenes.entity.SeguimientoUsuario;
import com.sistema.examenes.entity.dto.SeguimientoUsuarioDTO;
import com.sistema.examenes.projection.HistorialAsignacionEvidenciaProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeguimientoUsuario_repository extends JpaRepository<SeguimientoUsuario, Long> {

    @Query(value = "SELECT s.id_seguimiento, u.username, r.rolnombre AS rolnombre, p.cedula, CONCAT(p.primer_nombre, ' ', p.primer_apellido) AS usuario, s.descripcion, s.fecha " +
            "FROM usuarios u " +
            "JOIN usuariorol ur ON u.id = ur.usuario_id " +
            "JOIN roles r ON ur.rol_rolid = r.rolid " +
            "JOIN persona p ON u.persona_id_persona = p.id_persona " +
            "JOIN seguimiento_usuario s ON u.id = s.usuario_id", nativeQuery = true)
    List<Object[]> listaSeguimientoUsuario();
}
