package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Asignacion_Admin;
import com.sistema.examenes.projection.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface Asignacion_Admin_repository extends JpaRepository<Asignacion_Admin, Long> {
    @Query("SELECT aa FROM Asignacion_Admin aa WHERE aa.visible = true")
    List<Asignacion_Admin> listarAsignacion_Admin();
    @Query(value = "SELECT DISTINCT u.id as enc, per.primer_nombre||' '||per.primer_apellido as nombrescri, cri.nombre as actividasi " +
            "FROM asignacion_admin aa JOIN usuarios u ON aa.usuario_id=u.id " +
            "JOIN persona per ON per.id_persona=u.persona_id_persona AND u.visible=true " +
            "JOIN criterio cri ON cri.id_criterio=aa.criterio_id_criterio AND cri.visible=true " +
            "JOIN subcriterio s ON s.id_criterio=cri.id_criterio AND s.visible=true " +
            "JOIN indicador i ON i.subcriterio_id_subcriterio= s.id_subcriterio AND i.visible=true " +
            "JOIN asignacion_indicador ai ON ai.indicador_id_indicador=i.id_indicador AND ai.visible=true " +
            "JOIN modelo mo ON mo.id_modelo=ai.modelo_id_modelo " +
            "WHERE aa.visible=CAST(:veri AS BOOLEAN) AND aa.id_modelo=:id_modelo ORDER BY u.id;", nativeQuery = true)
    List<AsignacionProjection> asignacionAdmin(Long id_modelo, String veri);

    @Query("SELECT aa FROM Asignacion_Admin aa " +
            "WHERE aa.criterio.id_criterio = :id_criterio " +
            "AND aa.id_modelo.id_modelo = :id_modelo " +
            "AND aa.usuario.id = :id_usuario")
    Asignacion_Admin asignacion_existente(Long id_criterio, Long id_modelo, Long id_usuario);

    //no se utiliza
    @Query(value = "SELECT per.primer_nombre ||' '|| per.segundo_nombre||' '||per.primer_apellido||' '||per.segundo_apellido AS nombreaa FROM persona per\n" +
            "JOIN usuarios u ON u.persona_id_persona=per.id_persona\n" +
            "JOIN asignacion_admin aa ON aa.usuario_id=u.id AND aa.visible=true\n" +
            "JOIN modelo mo ON mo.id_modelo=aa.id_modelo\n" +
            "WHERE aa.id_modelo=:id_modelo AND aa.criterio_id_criterio=:id_criterio", nativeQuery = true)
    NombreAsigProjection listarnombre_Admin(Long id_modelo, Long id_criterio);

    @Query(value = "SELECT DISTINCT u.id as enc, " +
            "per.primer_nombre || ' ' || per.primer_apellido as nombrescri, " +
            "cri.nombre as actividasi " +
            "FROM asignacion_admin aa " +
            "JOIN usuarios u ON aa.usuario_id = u.id " +
            "JOIN persona per ON per.id_persona = u.persona_id_persona AND u.visible = true " +
            "JOIN criterio cri ON cri.id_criterio = aa.criterio_id_criterio AND cri.visible = true " +
            "JOIN subcriterio s ON s.id_criterio = cri.id_criterio AND s.visible = true " +
            "JOIN indicador i ON i.subcriterio_id_subcriterio = s.id_subcriterio AND i.visible = true " +
            "JOIN asignacion_indicador ai ON ai.indicador_id_indicador = i.id_indicador AND ai.visible = true " +
            "JOIN modelo mo ON mo.id_modelo = ai.modelo_id_modelo " +
            "JOIN usuariorol ur ON ur.usuario_id = u.id " +
            "WHERE aa.visible = true " +
            "AND aa.id_modelo = :id_modelo " +  
            "AND cri.id_criterio = :id_criterio " +
            "AND ur.rol_rolid = 1 " +  // Nuevo filtro por rol_rolid
            "ORDER BY u.id;", nativeQuery = true)
    List<AsignacionProjection> veradminsporcriterio(Long id_modelo, Long id_criterio);

    @Query("SELECT aa.id_asignacion as id_asignacion, aa.usuario.id as idUsuario, aa.criterio.id_criterio as idCriterio, aa.id_modelo.id_modelo as idModelo, aa.visible as visible FROM Asignacion_Admin aa WHERE aa.usuario.id = ?1 AND aa.id_modelo.id_modelo = ?2 AND aa.criterio.id_criterio = ?3")
    AsignacionProjection buscarAsignacionAdmin(Long usuarioId, Long modeloId, Long criterioId);

    @Query(value = "SELECT DISTINCT u.id as enc, " +
            "per.primer_nombre || ' ' || per.primer_apellido as nombrescri, " +
            "cri.nombre as actividasi " +
            "FROM asignacion_admin aa " +
            "JOIN usuarios u ON aa.usuario_id = u.id " +
            "JOIN persona per ON per.id_persona = u.persona_id_persona AND u.visible = true " +
            "JOIN criterio cri ON cri.id_criterio = aa.criterio_id_criterio AND cri.visible = true " +
            "JOIN subcriterio s ON s.id_criterio = cri.id_criterio AND s.visible = true " +
            "JOIN indicador i ON i.subcriterio_id_subcriterio = s.id_subcriterio AND i.visible = true " +
            "JOIN asignacion_indicador ai ON ai.indicador_id_indicador = i.id_indicador AND ai.visible = true " +
            "JOIN modelo mo ON mo.id_modelo = ai.modelo_id_modelo " +
            "JOIN usuariorol ur ON ur.usuario_id = u.id " +
            "WHERE aa.visible = true " +
            "AND aa.id_modelo = :id_modelo " +
            "AND cri.id_criterio = :id_criterio " +
            "AND ur.rol_rolid = 3 " +  // Nuevo filtro por rol_rolid
            "ORDER BY u.id;", nativeQuery = true)
    List<AsignacionProjection> verresponsablesporcriterio(Long id_modelo, Long id_criterio);

    List<Asignacion_Admin> findAsignacion_AdminByUsuario_Id(Long id_usuario);

    @Query("SELECT CONCAT(pe.primer_nombre, ' ', pe.primer_apellido) AS encargado, " +
            "ac.fecha_inicio AS inicio, " +
            "cr.nombre AS criterio, " +
            "s.nombre AS subcriterio, " +
            "i.nombre AS indicador, " +
            "ac.fecha_fin AS fin, " +
            "ar.enlace AS enlace, " +
            "ar.nombre AS nomb, " +
            "ev.descripcion AS evidencia " +
            "FROM Asignacion_Evidencia ac " +
            "JOIN ac.evidencia ev " +
            "JOIN ev.indicador i " +
            "JOIN i.lista_asignacion ai " +
            "JOIN ai.modelo mo " +
            "JOIN ac.usuario u " +
            "LEFT JOIN ac.lista_archivo ar " +
            "JOIN u.persona pe " +
            "JOIN i.subcriterio s " +
            "JOIN s.criterio cr " +
            "WHERE ac.fecha_inicio BETWEEN mo.fecha_inicio AND mo.fecha_fin " +
            "AND ac.fecha_fin BETWEEN mo.fecha_inicio AND mo.fecha_fin " +
            "AND LOWER(ev.estado) = 'rechazada' " +
            "AND ac.visible = true " +
            "AND ai.visible = true " +
            "AND ac.id_modelo = :id_modelo")
    List<ActivAprobadaProjection> actividadRechazada(Long id_modelo);

    @Query("SELECT CONCAT(pe.primer_nombre, ' ', pe.primer_apellido) AS encargado, " +
            "ac.fecha_inicio AS inicio, " +
            "cr.nombre AS criterio, " +
            "s.nombre AS subcriterio, " +
            "i.nombre AS indicador, " +
            "ac.fecha_fin AS fin, " +
            "ar.enlace AS enlace, " +
            "ar.nombre AS nomb, " +
            "ev.descripcion AS evidencia " +
            "FROM Asignacion_Evidencia ac " +
            "JOIN ac.evidencia ev " +
            "JOIN ev.indicador i " +
            "JOIN i.lista_asignacion ai " +
            "JOIN ai.modelo mo " +
            "JOIN ac.usuario u " +
            "LEFT JOIN ac.lista_archivo ar " +
            "JOIN u.persona pe " +
            "JOIN i.subcriterio s " +
            "JOIN s.criterio cr " +
            "WHERE ac.fecha_inicio BETWEEN mo.fecha_inicio AND mo.fecha_fin " +
            "AND ac.fecha_fin BETWEEN mo.fecha_inicio AND mo.fecha_fin " +
            "AND LOWER(ev.estado) = 'pendiente' " +
            "AND ac.visible = true " +
            "AND ai.visible = true " +
            "AND ac.id_modelo = :id_modelo")
    List<ActivAprobadaProjection> actividadpendiente(Long id_modelo);

    @Query(value = "SELECT pe.primer_nombre || ' ' || pe.primer_apellido AS encargado, " +
            "ac.fecha_inicio AS inicio, " +
            "cr.nombre AS criterio, " +
            "s.nombre AS subcriterio, " +
            "i.nombre AS indicador, " +
            "ac.fecha_fin AS fin, " +
            "ar.enlace AS enlace, " +
            "ar.nombre AS nomb, " +
            "ev.descripcion AS evidencia " +
            "FROM asignacion_evidencia ac " +
            "JOIN evidencia ev ON ac.evidencia_id_evidencia = ev.id_evidencia AND ac.visible = true " +
            "JOIN indicador i ON i.id_indicador = ev.indicador_id_indicador AND i.visible = true " +
            "JOIN asignacion_indicador ai ON ai.indicador_id_indicador = i.id_indicador AND ai.visible = true " +
            "JOIN modelo mo ON mo.id_modelo = ai.modelo_id_modelo " +
            "JOIN usuarios u ON u.id = ac.usuario_id " +
            "LEFT JOIN archivo ar ON ar.id_asignacion_evidencia = ac.id_asignacion_evidencia AND ar.visible = true " +
            "JOIN persona pe ON pe.id_persona = u.persona_id_persona " +
            "JOIN subcriterio s ON s.id_subcriterio = i.subcriterio_id_subcriterio " +
            "JOIN criterio cr ON cr.id_criterio = s.id_criterio " +
            "WHERE ac.fecha_inicio BETWEEN mo.fecha_inicio AND mo.fecha_fin " +
            "AND ac.fecha_fin BETWEEN mo.fecha_inicio AND mo.fecha_fin " +
            "AND LOWER(ev.estado) = 'aprobada' " +
            "AND ac.id_modelo = :id_modelo " +
            "AND ac.visible = true", nativeQuery = true)
    List<ActivAprobadaProjection> actividadAprobada(Long id_modelo);

    @Query(value = "SELECT per.primer_nombre || ' ' || per.primer_apellido as nombres, " +
            "COUNT(ac.id_asignacion_evidencia) as total, " +
            "ROUND(SUM(CASE WHEN ac.estado = 'Aprobada' THEN 1 ELSE 0 END) * 100.0 / COUNT(ac.id_asignacion_evidencia), 2) as avance " +
            "FROM asignacion_evidencia ac " +
            "JOIN evidencia ev ON ac.evidencia_id_evidencia = ev.id_evidencia " +
            "JOIN indicador i ON i.id_indicador = ev.indicador_id_indicador " +
            "JOIN usuarios u ON u.id = ac.usuario_id " +
            "JOIN persona per ON u.persona_id_persona = per.id_persona " +
            "WHERE ac.id_modelo = :id_modelo " +
            "  AND ac.visible = true " +
            "  AND u.visible = true " +
            "GROUP BY per.primer_nombre, per.primer_apellido", nativeQuery = true)
    List<ActividadesAvanceProjection> actividadCont(Long id_modelo);

    @Query(value = "SELECT u.id as idusuario, e.id_evidencia as idevidencia, cri.nombre as criterio, s.nombre as subcriterio, i.nombre as indicador, e.descripcion as nombre, ae.fecha_fin as fechafin, " +
            "ae.fecha_inicio as fechainicio, pe.primer_nombre||' '||pe.primer_apellido as nombreresponsable, ae.estado as estado " +
            "FROM evidencia e " +
            "JOIN asignacion_evidencia ae ON ae.evidencia_id_evidencia = e.id_evidencia " +
            "JOIN usuarios u ON u.id = ae.usuario_id " +
            "JOIN persona pe ON pe.id_persona = u.persona_id_persona " +
            "JOIN indicador i ON e.indicador_id_indicador = i.id_indicador " +
            "JOIN subcriterio s ON i.subcriterio_id_subcriterio = s.id_subcriterio " +
            "JOIN criterio cri ON s.id_criterio = cri.id_criterio " +
            "JOIN asignacion_indicador ag ON ag.indicador_id_indicador = i.id_indicador " +
            "WHERE (LOWER(ae.estado) = LOWER(:estado)) " +
            "AND u.visible= true AND e.visible=true AND ae.visible=true " +
            "AND ag.visible= true AND ag.modelo_id_modelo = :id_modelo AND ae.id_modelo= :id_modelo ", nativeQuery = true)
    List<ActivProyection> listarEvidenciasAutoridad(String estado, Long id_modelo);


    @Query(value = "SELECT distinct ev.descripcion as nombreevidencia, ae.fecha_inicio as inicio, ae.fecha_fin as fin " +
            "FROM asignacion_evidencia ae " +
            "JOIN evidencia ev ON ev.id_evidencia = ae.evidencia_id_evidencia " +
            "JOIN indicador i ON i.id_indicador = ev.indicador_id_indicador " +
            "JOIN asignacion_indicador po ON po.indicador_id_indicador=i.id_indicador " +
            "JOIN modelo mo ON mo.id_modelo=po.modelo_id_modelo " +
            "WHERE mo.id_modelo= :id_modelo AND ae.id_modelo= :id_modelo " +
            "AND ae.visible=true AND ae.usuario_id=:id", nativeQuery = true)
    List<ActivProyection> evidenciaUsu(Long id, Long id_modelo);

}
