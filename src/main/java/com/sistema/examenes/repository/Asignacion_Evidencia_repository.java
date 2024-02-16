package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Actividad;
import com.sistema.examenes.entity.Asignacion_Evidencia;
import com.sistema.examenes.projection.ActiCalendarProjection;
import com.sistema.examenes.projection.AsignaProjection;
import com.sistema.examenes.projection.AsignacionEvidenciaProyeccion;
import com.sistema.examenes.projection.EvidenciaReApPeAtrProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Asignacion_Evidencia_repository extends JpaRepository<Asignacion_Evidencia, Long> {
    @Query(value = "SELECT ae.* from asignacion_evidencia ae JOIN evidencia e ON e.id_evidencia=ae.evidencia_id_evidencia AND ae.visible =true\n" +
            "JOIN indicador i ON e.indicador_id_indicador = i.id_indicador\n" +
            "JOIN asignacion_indicador po ON i.id_indicador = po.indicador_id_indicador\n" +
            "JOIN modelo mo ON mo.id_modelo=po.modelo_id_modelo\n" +
            "AND mo.id_modelo = (SELECT MAX(id_modelo) FROM modelo) \n" +
            "WHERE ae.visible=true ORDER BY ae.usuario_id,ae.evidencia_id_evidencia;",nativeQuery = true)
    List<Asignacion_Evidencia> listarAsignacionEvidencia();
    @Query(value = "SELECT * FROM asignacion_evidencia " +
            "WHERE evidencia_id_evidencia=:id_evidencia AND visible=true AND id_modelo=:id_modelo ",nativeQuery = true)
    Asignacion_Evidencia fechaactividades(Long id_evidencia,Long id_modelo);
    @Query(value = "SELECT ae.id_asignacion_evidencia AS idevid, e.id_evidencia AS ideviden, cri.nombre AS crite,s.nombre AS subcrite,i.nombre AS indi, " +
            "pe.primer_nombre||' '||pe.primer_apellido AS respon, e.descripcion AS descev,ae.fecha_inicio AS ini, ae.fecha_fin AS fini " +
            "FROM asignacion_evidencia ae JOIN evidencia e ON e.id_evidencia=ae.evidencia_id_evidencia AND ae.visible =true\n" +
            "JOIN usuarios u ON u.id=ae.usuario_id \n" +
            "JOIN persona pe ON pe.id_persona=u.persona_id_persona \n" +
            "JOIN indicador i ON e.indicador_id_indicador = i.id_indicador \n" +
            "JOIN subcriterio s ON s.id_subcriterio=i.subcriterio_id_subcriterio \n" +
            "JOIN criterio cri ON cri.id_criterio = s.id_criterio \n" +
            "JOIN asignacion_indicador po ON i.id_indicador = po.indicador_id_indicador\n" +
            "JOIN modelo mo ON mo.id_modelo=po.modelo_id_modelo\n" +
            "AND mo.id_modelo = (SELECT MAX(id_modelo) FROM modelo) \n" +
            "WHERE ae.visible=true ORDER BY ae.usuario_id,cri.id_criterio, s.id_subcriterio,i.id_indicador;",nativeQuery = true)
    List<AsignaProjection> listarAsigEvidencia();
    @Query(value = "SELECT ae.id_asignacion_evidencia as idAsignacionEvidencia, ae.usuario_id as usuarioId, ae.evidencia_id_evidencia as evidenciaId " +
            "FROM asignacion_evidencia ae " +
            "JOIN evidencia e ON e.id_evidencia = ae.evidencia_id_evidencia AND ae.visible = true " +
            "JOIN indicador i ON e.indicador_id_indicador = i.id_indicador " +
            "JOIN asignacion_indicador po ON i.id_indicador = po.indicador_id_indicador " +
            "JOIN modelo mo ON mo.id_modelo = po.modelo_id_modelo " +
            "AND mo.id_modelo = (SELECT MAX(id_modelo) FROM modelo) " +
            "WHERE ae.visible = true " +
            "ORDER BY ae.usuario_id, ae.evidencia_id_evidencia;", nativeQuery = true)
    List<AsignacionEvidenciaProyeccion> listarAsignacionEvidenciaProyeccion();
    @Query(value =
            "SELECT asignacion_evidencia.id_asignacion_evidencia, evidencia.id_evidencia, evidencia.descripcion, evidencia.nombre\n" +
                    "FROM asignacion_evidencia, evidencia, usuarios\n" +
                    "WHERE asignacion_evidencia.evidencia_id_evidencia = evidencia.id_evidencia\n" +
                    "AND asignacion_evidencia.usuario_id = usuarios.id\n" +
                    "AND evidencia.visible = true\n" +
                    "AND usuarios.username= :usuario", nativeQuery = true)
    List<Asignacion_Evidencia> listarporAsignacionUsuario(@Param("usuario") String usuario);
    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM asignacion_evidencia ae " +
                    "JOIN evidencia e ON e.id_evidencia=ae.evidencia_id_evidencia AND ae.visible=true AND e.visible=true " +
                    "JOIN indicador i ON i.id_indicador=e.indicador_id_indicador " +
                    "JOIN subcriterio s ON s.id_subcriterio=i.subcriterio_id_subcriterio " +
                    "JOIN criterio cri ON cri.id_criterio = s.id_criterio " +
                    "JOIN asignacion_admin aa ON aa.criterio_id_criterio=cri.id_criterio AND aa.visible=true " +
                    "WHERE aa.usuario_id=:id_usuario " +
                    "AND ae.evidencia_id_evidencia=:id_evidencia " +
                    "AND aa.id_modelo=:id_modelo", nativeQuery = true)
    Boolean verificarAsignacionUsuario(Long id_usuario, Long id_evidencia,Long id_modelo);

    @Query(value = "SELECT e.descripcion, a.fecha_inicio, a.fecha_fin " +
            "FROM asignacion_evidencia a " +
            "JOIN evidencia e ON e.id_evidencia = a.evidencia_id_evidencia " +
            "WHERE a.usuario_id = :usuarioId AND a.visible = true AND LOWER(e.estado)='pendiente'", nativeQuery = true)
    List<ActiCalendarProjection> findActCalendarByUsuarioId(@Param("usuarioId") Long usuarioId);

    @Query(value = "SELECT ae.id_asignacion_evidencia, e.descripcion, ae.fecha_inicio, ae.fecha_fin, e.estado, e.id_evidencia " +
            "FROM asignacion_evidencia ae " +
            "JOIN usuarios u ON ae.usuario_id = u.id " +
            "JOIN evidencia e ON ae.evidencia_id_evidencia = e.id_evidencia " +
            "WHERE u.username = :username AND ae.evidencia_id_evidencia=:id_evidencia ", nativeQuery = true)
    List<Object[]> listarAsigEviUser(String username, Long id_evidencia);

    @Query(value = "select * from  asignacion_evidencia ac JOIN usuarios u ON ac.usuario_id = u.id where u.username=:username and ac.visible =true",nativeQuery = true)
    List<Asignacion_Evidencia>listarporusuario(String username);

    @Query(value = "SELECT * FROM asignacion_evidencia WHERE visible= true AND evidencia_id_evidencia=:idEvidendicia ;",nativeQuery = true)
    List<Asignacion_Evidencia>listarporEvidencia(Long idEvidendicia);

    @Query(value = "SELECT * FROM asignacion_evidencia WHERE usuario_id = :userId AND visible = true;",nativeQuery = true)
    List<Asignacion_Evidencia> listarporUsuarioxd(Long userId);

    @Query(value = "SELECT pe.primer_nombre ||' '|| pe.primer_apellido AS responsable, " +
            "c.nombre AS nombre_criterio, s.nombre AS nombre_subcriterio, i.nombre AS nombre_indicador, " +
            "e.descripcion AS evidencia, ae.fecha_fin, ae.fecha_inicio, e.estado " +
            "FROM asignacion_evidencia ae " +
            "JOIN evidencia e ON ae.evidencia_id_evidencia = e.id_evidencia " +
            "JOIN indicador i ON e.indicador_id_indicador = i.id_indicador " +
            "JOIN subcriterio s ON i.subcriterio_id_subcriterio = s.id_subcriterio " +
            "JOIN criterio c ON s.id_criterio = c.id_criterio " +
            "JOIN asignacion_indicador ag ON ag.indicador_id_indicador = i.id_indicador " +
            "JOIN usuarios u ON ae.usuario_id = u.id " +
            "JOIN persona pe ON u.persona_id_persona = pe.id_persona " +
            "WHERE (LOWER(e.estado) = 'rechazada') " +
            "AND ag.modelo_id_modelo = (SELECT MAX(id_modelo) FROM modelo)", nativeQuery = true)
    List<EvidenciaReApPeAtrProjection> listarEvideRechazadas();

    @Query(value = "SELECT pe.primer_nombre ||' '|| pe.primer_apellido AS responsable, " +
            "c.nombre AS nombre_criterio, s.nombre AS nombre_subcriterio, i.nombre AS nombre_indicador, " +
            "e.descripcion AS evidencia, ae.fecha_fin, ae.fecha_inicio, e.estado " +
            "FROM asignacion_evidencia ae " +
            "JOIN evidencia e ON ae.evidencia_id_evidencia = e.id_evidencia " +
            "JOIN indicador i ON e.indicador_id_indicador = i.id_indicador " +
            "JOIN subcriterio s ON i.subcriterio_id_subcriterio = s.id_subcriterio " +
            "JOIN criterio c ON s.id_criterio = c.id_criterio " +
            "JOIN asignacion_indicador ag ON ag.indicador_id_indicador = i.id_indicador " +
            "JOIN usuarios u ON ae.usuario_id = u.id " +
            "JOIN persona pe ON u.persona_id_persona = pe.id_persona " +
            "WHERE (LOWER(e.estado) = 'aprobada') " +
            "AND ag.modelo_id_modelo = (SELECT MAX(id_modelo) FROM modelo)", nativeQuery = true)
    List<EvidenciaReApPeAtrProjection> listarEvideAprobadas();

    @Query(value = "SELECT pe.primer_nombre ||' '|| pe.primer_apellido AS responsable, " +
            "c.nombre AS nombre_criterio, s.nombre AS nombre_subcriterio, i.nombre AS nombre_indicador, " +
            "e.descripcion AS evidencia, ae.fecha_fin, ae.fecha_inicio, e.estado " +
            "FROM asignacion_evidencia ae " +
            "JOIN evidencia e ON ae.evidencia_id_evidencia = e.id_evidencia " +
            "JOIN indicador i ON e.indicador_id_indicador = i.id_indicador " +
            "JOIN subcriterio s ON i.subcriterio_id_subcriterio = s.id_subcriterio " +
            "JOIN criterio c ON s.id_criterio = c.id_criterio " +
            "JOIN asignacion_indicador ag ON ag.indicador_id_indicador = i.id_indicador " +
            "JOIN usuarios u ON ae.usuario_id = u.id " +
            "JOIN persona pe ON u.persona_id_persona = pe.id_persona " +
            "WHERE (LOWER(e.estado) = 'pendiente') " +
            "AND ag.modelo_id_modelo = (SELECT MAX(id_modelo) FROM modelo)", nativeQuery = true)
    List<EvidenciaReApPeAtrProjection> listarEvidePendientes();
}
