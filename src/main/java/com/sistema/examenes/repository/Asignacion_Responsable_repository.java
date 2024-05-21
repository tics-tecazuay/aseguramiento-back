package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Asignacion_Responsable;
import com.sistema.examenes.projection.ResponsableProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Asignacion_Responsable_repository extends JpaRepository<Asignacion_Responsable, Long> {
    @Query(value = "SELECT * from asignacion_responsable where  usuarioadmin_id = ?1 and usuarioresponsable_id = ?2 and id_modelo =?3 ", nativeQuery = true)
    Asignacion_Responsable asignacion_existente(Long id_usuarioAdmin, Long id_usuarioResponsable, Long idModelo);


   /* @Query(value = "SELECT u.id, CONCAT(per.primer_nombre, ' ', per.segundo_nombre, ' ', per.primer_apellido, ' ', per.segundo_apellido) AS nombres, " +
            "u.username AS usua, r.rolnombre AS rol, " +
            "CASE WHEN ae.count_evidencias IS NULL THEN 'Sin evidencias asignadas' " +
            "ELSE CONCAT('Tiene ', ae.count_evidencias, ' evidencia/s asignada/s') " +
            "END AS evidencias " +
            "FROM usuarios u " +
            "JOIN persona per ON per.id_persona = u.persona_id_persona " +
            "JOIN usuariorol ur ON u.id = ur.usuario_id " +
            "JOIN roles r ON ur.rol_rolid = r.rolid " +
            "LEFT JOIN (SELECT usuario_id, COUNT(DISTINCT evidencia_id_evidencia) AS count_evidencias " +
            "           FROM asignacion_evidencia " +
            "           WHERE visible = true AND id_modelo = :idModelo GROUP BY usuario_id) ae ON u.id = ae.usuario_id " +
            "LEFT JOIN asignacion_responsable asigres ON asigres.usuarioresponsable_id = u.id AND asigres.id_modelo = :idModelo " +
            "LEFT JOIN asignacion_admin asigadm ON asigres.usuarioresponsable_id = asigadm.usuario_id AND asigadm.id_modelo = :idModelo " +
            "AND asigadm.visible = true " +
            "WHERE r.rolnombre = 'RESPONSABLE' " +
            "AND ((asigres.usuarioadmin_id = :idAdministrador AND asigres.visible = true) " +
            "OR (u.id IN (SELECT ae_inner.usuario_id " +
            "             FROM asignacion_evidencia ae_inner " +
            "             JOIN evidencia e ON ae_inner.evidencia_id_evidencia = e.id_evidencia " +
            "             JOIN indicador i ON e.indicador_id_indicador = i.id_indicador " +
            "             JOIN asignacion_indicador ai ON i.id_indicador = ai.indicador_id_indicador AND ai.modelo_id_modelo = :idModelo " +
            "             JOIN subcriterio sc ON i.subcriterio_id_subcriterio = sc.id_subcriterio " +
            "             JOIN criterio c ON sc.id_criterio = c.id_criterio " +
            "             WHERE c.id_criterio IN (SELECT criterio_id_criterio " +
            "                                     FROM asignacion_admin " +
            "                                     WHERE usuario_id = :idAdministrador AND visible = true AND id_modelo = :idModelo) " +
            "             AND ae_inner.id_modelo = :idModelo ))) " +
            "AND u.visible = true AND asigres.visible = true " +
            "AND ((asigres.usuarioadmin_id IS NULL AND ae.count_evidencias IS NOT NULL) " +
            "OR (asigres.usuarioadmin_id IS NOT NULL)) " +
            "GROUP BY u.id, nombres, usua, rol, evidencias", nativeQuery = true)
    List<ResponsableProjection> listadeResponsablesByAdmin2(Long idModelo, Long idAdministrador);*/


    @Query(value = " SELECT u.id, CONCAT(per.primer_nombre, ' ', per.segundo_nombre, ' ', per.primer_apellido, ' ', per.segundo_apellido) AS nombres, " +
            "u.username AS usua, r.rolnombre AS rol, " +
            "CASE WHEN ae.count_evidencias IS NULL THEN 'Sin evidencias asignadas' " +
            "ELSE CONCAT('Tiene ', ae.count_evidencias, ' evidencia/s asignada/s') END AS evidencias " +
            "FROM usuarios u " +
            "JOIN persona per ON per.id_persona = u.persona_id_persona " +
            "JOIN usuariorol ur ON u.id = ur.usuario_id " +
            "JOIN roles r ON ur.rol_rolid = r.rolid " +
            "LEFT JOIN ( " +
            "SELECT usuario_id, COUNT(DISTINCT evidencia_id_evidencia) AS count_evidencias " +
            "FROM asignacion_evidencia " +
            "WHERE visible = true " +
            "AND id_modelo = :idModelo " +
            "GROUP BY usuario_id) ae ON u.id = ae.usuario_id " +
            "LEFT JOIN asignacion_responsable asigres ON asigres.usuarioresponsable_id = u.id " +
            "AND asigres.id_modelo = :idModelo " +
            "AND asigres.visible = true " +
            "LEFT JOIN asignacion_admin asigadm ON asigadm.usuario_id = u.id " +
            "AND asigadm.id_modelo = :idModelo AND asigadm.visible = true " +
            "WHERE r.rolnombre = 'RESPONSABLE' AND ( " +
            "(asigres.usuarioadmin_id = :idAdministrador AND asigres.visible = true) OR (u.id IN ( " +
            "SELECT ar.usuarioresponsable_id " +
            "FROM asignacion_responsable ar " +
            "JOIN asignacion_admin aa ON ar.usuarioadmin_id = aa.usuario_id " +
            "WHERE ar.id_modelo = :idModelo " +
            "AND ar.visible = true " +
            "AND aa.id_modelo = :idModelo " +
            "AND aa.visible = true " +
            "AND aa.criterio_id_criterio IN ( " +
            "SELECT criterio_id_criterio " +
            "FROM asignacion_admin " +
            "WHERE usuario_id = :idAdministrador " +
            "AND visible = true " +
            "AND id_modelo = :idModelo ) )) OR (u.id IN ( " +
            "SELECT ae_inner.usuario_id " +
            "FROM asignacion_evidencia ae_inner " +
            "JOIN evidencia e ON ae_inner.evidencia_id_evidencia = e.id_evidencia " +
            "JOIN indicador i ON e.indicador_id_indicador = i.id_indicador " +
            "JOIN asignacion_indicador ai ON i.id_indicador = ai.indicador_id_indicador " +
            "AND ai.modelo_id_modelo = :idModelo " +
            "JOIN subcriterio sc ON i.subcriterio_id_subcriterio = sc.id_subcriterio " +
            "JOIN criterio c ON sc.id_criterio = c.id_criterio " +
            "WHERE c.id_criterio IN ( " +
            "SELECT criterio_id_criterio " +
            "FROM asignacion_admin " +
            "WHERE usuario_id = :idAdministrador " +
            "AND visible = true " +
            "AND id_modelo = :idModelo ) AND ae_inner.id_modelo = :idModelo )) ) " +
            "AND u.visible = true " +
            "GROUP BY u.id, nombres, usua, rol, evidencias ", nativeQuery = true)
    List<ResponsableProjection> listadeResponsablesByAdmin(Long idModelo, Long idAdministrador);

    @Query("SELECT ar FROM Asignacion_Responsable ar WHERE ar.usuarioResponsable.id = :id_usuarioResponsable")
    Asignacion_Responsable asignacionByIdUsuarioResponsable(@Param("id_usuarioResponsable") Long id_usuarioResponsable);

    @Query("SELECT ar FROM Asignacion_Responsable ar WHERE ar.usuarioResponsable.id = :id_usuarioResponsable AND ar.id_modelo= :id_modelo ")
    Asignacion_Responsable asignacionByIdUsuarioResponsableIdModelo(@Param("id_usuarioResponsable") Long id_usuarioResponsable,@Param("id_modelo") Long id_modelo);

    @Query("SELECT ar FROM Asignacion_Responsable ar WHERE ar.usuarioAdmin.id = :idAdministrador")
    List<Asignacion_Responsable> Asignacion_ResponsablesByAdmin(@Param("idAdministrador") Long idAdministrador);

    @Query("SELECT ar FROM Asignacion_Responsable ar WHERE ar.usuarioResponsable.id = :id_usuarioResponsable AND ar.usuarioAdmin.id= :id_usuarioAdmin AND ar.id_modelo= :id_modelo")
    Asignacion_Responsable obtenerAsignacionResponsablePorIdResponsableIdAdminIdModelo(@Param("id_usuarioResponsable") Long id_usuarioResponsable,@Param("id_usuarioAdmin") Long id_usuarioAdmin, @Param("id_modelo") Long id_modelo);

    boolean existsByUsuarioAdminIdAndUsuarioResponsableId(Long adminId, Long userId);

}
