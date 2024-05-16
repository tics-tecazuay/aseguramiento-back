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

    @Query("SELECT s.id_seguimiento AS id_seguimiento, " +
            "u.username AS username, " +
            "r.rolNombre AS rolnombre," +
            "p.cedula AS cedula, " +
            "CONCAT(p.primer_nombre, ' ', p.primer_apellido) AS usuario, " +
            "s.descripcion AS descripcion, " +
            "s.fecha AS fecha " +
            "FROM Usuario u " +
            "JOIN u.usuarioRoles ur " +
            "JOIN ur.rol r " +
            "JOIN u.persona p " +
            "JOIN SeguimientoUsuario s ON u.id = s.usuario.id")
    List<SeguimientoUsuarioDTO> listaSeguimientoUsuario();
}
