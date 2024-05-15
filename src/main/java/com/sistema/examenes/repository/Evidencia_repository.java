package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Criterio;
import com.sistema.examenes.entity.Evidencia;
import com.sistema.examenes.projection.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Evidencia_repository extends JpaRepository<Evidencia, Long> {
    @Query("SELECT e FROM Evidencia e WHERE e.visible = true")
    List<Evidencia> listarEvidencia();

    @Query(value = "SELECT e.id_evidencia AS id_evidencia, " +
            "c.nombre AS nombrecriterio, " +
            "s.nombre AS nombresubcriterio, " +
            "i.nombre AS nombreindicador, " +
            "i.tipo AS tipo, e.descripcion AS descripcionevidencia, " +
            "ae.estado AS estado, " +
            "(SELECT comentario FROM archivo " +
            "WHERE id_asignacion_evidencia = ae.id_asignacion_evidencia " +
            "AND visible = true AND id_modelo= :id_modelo ORDER BY id_archivo DESC LIMIT 1 ) AS comentario " +
            "FROM evidencia e " +
            "JOIN asignacion_evidencia ae ON e.id_evidencia = ae.evidencia_id_evidencia AND ae.id_modelo = :id_modelo AND ae.visible = true  " +
            "JOIN usuarios u ON ae.usuario_id = u.id AND u.visible=true " +
            "JOIN indicador i ON e.indicador_id_indicador = i.id_indicador " +
            "JOIN asignacion_indicador ai ON i.id_indicador = ai.indicador_id_indicador AND ai.modelo_id_modelo = :id_modelo " +
            "JOIN subcriterio s ON i.subcriterio_id_subcriterio = s.id_subcriterio " +
            "JOIN criterio c ON s.id_criterio = c.id_criterio " +
            "WHERE u.username = :username AND e.visible = true ", nativeQuery = true)
    public List<EvidenciaEvProjection> evidenciaUsuario(@Param("username") String username, @Param("id_modelo") Long id_modelo  );


    @Query(value = "SELECT e.id_evidencia AS id_evidencia, cri.nombre AS nombrecriterio, s.nombre AS nombresubcriterio, " +
            "i.nombre AS nombreindicador, i.tipo AS tipo, e.descripcion AS descripcionevidencia, ae.estado AS estado, " +
            "(SELECT comentario FROM archivo WHERE id_asignacion_evidencia = ae.id_asignacion_evidencia AND visible = true " +
            "AND id_modelo = :idModel ORDER BY id_archivo DESC LIMIT 1 ) AS comentario " +
            "FROM asignacion_evidencia ae " +
            "JOIN evidencia e ON e.id_evidencia = ae.evidencia_id_evidencia AND e.visible = true " +
            "JOIN usuarios u_resp ON u_resp.id = ae.usuario_id AND u_resp.visible = true " +
            "JOIN persona pe_resp ON pe_resp.id_persona = u_resp.persona_id_persona " +
            "JOIN usuarios u_asig ON u_asig.id = ae.id_usuario_asignador AND u_asig.visible = true " +
            "JOIN persona pe_asig ON pe_asig.id_persona = u_asig.persona_id_persona " +
            "JOIN indicador i ON e.indicador_id_indicador = i.id_indicador " +
            "JOIN subcriterio s ON s.id_subcriterio = i.subcriterio_id_subcriterio " +
            "JOIN criterio cri ON cri.id_criterio = s.id_criterio " +
            "JOIN asignacion_indicador ai ON i.id_indicador = ai.indicador_id_indicador AND ai.visible = true AND ai.modelo_id_modelo = :idModel " +
            "JOIN asignacion_admin aa ON aa.criterio_id_criterio = cri.id_criterio AND aa.visible = true AND aa.id_modelo = :idModel " +
            "JOIN usuarios u ON ae.usuario_id = u.id " +
            "WHERE ae.visible = true " +
            "AND ae.id_modelo = :idModel " +
            "AND aa.usuario_id = :usuarioId " +
            "AND u.username = :username " +
            "ORDER BY cri.nombre, s.nombre, i.nombre, " +
            "CAST(SUBSTRING(e.descripcion FROM '^[0-9]+') AS INTEGER), e.descripcion ASC", nativeQuery = true)
    List<EvidenciaEvProjection> evidenciaFiltraCriterio(String username, Long usuarioId, Long idModel);

    @Query("SELECT e.id_evidencia AS id_evidencia, cri.nombre AS criterio, s.nombre AS subcriterio, i.nombre AS indicador, " +
            "e.descripcion AS descripcion, e.estado AS estado, ae.id_asignacion_evidencia AS id_asignacion_evidencia, " +
            "(SELECT COUNT(a.id_archivo) FROM Archivo_s a WHERE a.actividad.id_asignacion_evidencia = ae.id_asignacion_evidencia AND a.visible = true) AS countarchivos " +
            "FROM Evidencia e " +
            "JOIN e.indicador i " +
            "JOIN i.subcriterio s " +
            "JOIN s.criterio cri " +
            "JOIN e.lista_evidencias ae " +
            "JOIN ae.usuario u " +
            "WHERE u.username = :username " +
            "AND ae.visible = true " +
            "AND ae.id_modelo = :idModel " +
            "ORDER BY cri.id_criterio ASC ")
    public List<EvidenciaProjection> evidenUsuario(@Param("username") String username, @Param("idModel") Long idModel);

    @Query("SELECT e.id_evidencia AS id_evidencia, cri.nombre AS criterio, s.nombre AS subcriterio, i.nombre AS indicador, " +
            "e.descripcion AS descripcion, e.estado AS estado " +
            "FROM Evidencia e " +
            "JOIN e.indicador i " +
            "JOIN i.subcriterio s " +
            "JOIN s.criterio cri " +
            "JOIN e.lista_evidencias ae " +
            "JOIN ae.usuario u " +
            "WHERE u.username = :username " +
            "AND ae.visible = true " +
            "AND ae.archsubido = false " +
            "AND ae.id_modelo = :id_modelo")
    public List<EvidenciaProjection> evidenUserPendiente(@Param("username") String username, @Param("id_modelo") Long id_modelo);

   /* @Query(value = " SELECT e.* FROM evidencia e  \n" +
            "               LEFT JOIN asignacion_evidencia ae ON e.id_evidencia = ae.evidencia_id_evidencia \n" +
            "               WHERE ae.id_asignacion_evidencia IS NULL AND e.visible=true",nativeQuery = true)*/

    @Query(value = "SELECT cri.nombre criterio, s.nombre subcriterio, i.nombre indicador, e.* FROM evidencia e " +
            "JOIN indicador i ON i.id_indicador = e.indicador_id_indicador " +
            "JOIN subcriterio s ON s.id_subcriterio = i.subcriterio_id_subcriterio " +
            "JOIN criterio cri ON cri.id_criterio = s.id_criterio " +
            "LEFT JOIN asignacion_evidencia ae ON ae.evidencia_id_evidencia = e.id_evidencia AND ae.visible = true " +
            "LEFT JOIN asignacion_admin aa ON aa.criterio_id_criterio = cri.id_criterio AND aa.visible = true " +
            "WHERE aa.id_modelo = (SELECT MAX(id_modelo) FROM modelo) AND aa.usuario_id =:idUser " +
            "AND (e.id_evidencia IS NULL OR e.id_evidencia NOT IN (SELECT evidencia_id_evidencia FROM asignacion_evidencia WHERE visible = true)) " +
            "AND e.visible = true ORDER BY e.id_evidencia;", nativeQuery = true)
    List<Evidencia> listarEvidenciaAsigna(Long idUser);

    @Query(value = "SELECT DISTINCT e.id_evidencia, e.*, c.id_criterio FROM evidencia e " +
            "JOIN indicador i ON e.indicador_id_indicador = i.id_indicador " +
            "JOIN subcriterio sc ON i.subcriterio_id_subcriterio = sc.id_subcriterio " +
            "JOIN criterio c ON sc.id_criterio = c.id_criterio " +
            "JOIN asignacion_admin aa ON c.id_criterio = aa.criterio_id_criterio AND aa.visible = true " +
            "WHERE c.id_criterio=:idcriterio AND e.visible = true AND aa.id_modelo = (SELECT MAX(id_modelo) FROM modelo) " +
            "AND e.id_evidencia NOT IN (SELECT evidencia_id_evidencia FROM asignacion_evidencia  WHERE visible=true) " +
            "ORDER BY c.id_criterio,e.id_evidencia", nativeQuery = true)
    List<Evidencia> evidenciacriterio(Long idcriterio);

    @Query(value = "SELECT DISTINCT " +
            "e.id_evidencia AS idev, " +
            "c.id_criterio AS idcri, " +
            "c.nombre AS nombcri, " +
            "sc.id_subcriterio AS idsub, " +
            "sc.nombre AS nombsub, " +
            "i.id_indicador AS idind, " +
            "i.nombre AS nombind, " +
            "e.descripcion AS descripc " +
            "FROM " +
            "evidencia e " +
            "JOIN " +
            "indicador i ON e.indicador_id_indicador = i.id_indicador " +
            "JOIN " +
            "asignacion_indicador ai ON ai.indicador_id_indicador = i.id_indicador AND ai.visible = true AND ai.modelo_id_modelo = :id_modelo " +
            "LEFT JOIN " +
            "asignacion_evidencia ae ON ae.evidencia_id_evidencia = e.id_evidencia AND ae.visible = true AND ae.id_modelo = :id_modelo " +
            "JOIN " +
            "subcriterio sc ON i.subcriterio_id_subcriterio = sc.id_subcriterio " +
            "JOIN " +
            "criterio c ON sc.id_criterio = c.id_criterio " +
            "WHERE " +
            "c.id_criterio = :idcriterio " +
            "AND e.visible = true " +
            "AND e.id_evidencia NOT IN (SELECT evidencia_id_evidencia FROM asignacion_evidencia WHERE visible = true AND ae.id_modelo = :id_modelo) " +
            "ORDER BY " +
            "sc.nombre, i.nombre, e.descripcion ASC", nativeQuery = true)
    List<AsigEvidProjection> obtenerEvidenciasPorCriterio(Long idcriterio, Long id_modelo);

    @Query(value = "SELECT cri.id_criterio AS idcri, cri.nombre AS nombcri, s.id_subcriterio AS idsub, s.nombre AS nombsub, i.id_indicador AS idind, i.nombre AS nombind, e.id_evidencia AS idev, e.descripcion AS descripc " +
            "FROM evidencia e " +
            "JOIN indicador i ON i.id_indicador = e.indicador_id_indicador " +
            "JOIN asignacion_indicador ai ON ai.indicador_id_indicador = i.id_indicador AND ai.visible = true AND ai.modelo_id_modelo = :id_modelo " +
            "JOIN subcriterio s ON s.id_subcriterio = i.subcriterio_id_subcriterio " +
            "JOIN criterio cri ON cri.id_criterio = s.id_criterio " +
            "LEFT JOIN asignacion_evidencia ae ON ae.evidencia_id_evidencia = e.id_evidencia AND ae.visible = true AND ae.id_modelo= :id_modelo " +
            "LEFT JOIN asignacion_admin aa ON aa.criterio_id_criterio = cri.id_criterio AND aa.visible = true " +
            "WHERE aa.id_modelo = :id_modelo AND aa.usuario_id = :idUser " +
            "AND (e.id_evidencia IS NULL OR e.id_evidencia NOT IN (SELECT evidencia_id_evidencia FROM asignacion_evidencia WHERE visible = true AND ae.id_modelo= :id_modelo)) " +
            "AND e.visible = true " +
            "ORDER BY cri.nombre, s.nombre, i.nombre, e.descripcion ASC", nativeQuery = true)
    List<AsigEvidProjection> listarEvidenciaAdmin(Long idUser, Long id_modelo);
    // SELECT evidencia.*
    // FROM public.indicador join public.evidencia ON
    // evidencia.indicador_id_indicador = indicador.id_indicador
    // WHERE evidencia.indicador_id_indicador=6 And evidencia.visible=true;
    @Query(value = "SELECT evidencia.* FROM public.indicador JOIN public.evidencia ON evidencia.indicador_id_indicador = indicador.id_indicador " +
            "WHERE evidencia.indicador_id_indicador=:id_indicador AND evidencia.visible=true " +
            "ORDER BY CAST(SUBSTRING(evidencia.descripcion FROM '^[0-9]+') AS INTEGER), evidencia.descripcion ASC", nativeQuery = true)
    List<Evidencia> listarEvidenciaPorIndicador(Long id_indicador);

    @Query(value = "SELECT DISTINCT e.id_evidencia AS idev,per.primer_nombre||' '||per.primer_apellido AS enca, " +
            "cri.nombre AS crit, s.nombre AS subc, i.nombre AS indic, e.descripcion AS descr FROM detalle_evaluacion d " +
            "JOIN evidencia e ON d.evidencia_id_evidencia=e.id_evidencia " +
            "JOIN indicador i ON i.id_indicador=e.indicador_id_indicador " +
            "JOIN subcriterio s ON s.id_subcriterio=i.subcriterio_id_subcriterio " +
            "JOIN criterio cri ON cri.id_criterio=s.id_criterio " +
            "JOIN asignacion_evidencia ae ON ae.evidencia_id_evidencia=e.id_evidencia " +
            "JOIN usuarios u ON u.id=ae.usuario_id " +
            "JOIN persona per ON per.id_persona=u.persona_id_persona AND d.estado=true " +
            "WHERE d.id_modelo=:id_modelo", nativeQuery = true)
    List<EvidenciasProjection> evidenciaAprobada(Long id_modelo);

    @Query(value = "SELECT DISTINCT e.id_evidencia AS idev,per.primer_nombre||' '||per.primer_apellido AS enca, " +
            "cri.nombre AS crit, s.nombre AS subc, i.nombre AS indic, e.descripcion AS descr FROM detalle_evaluacion d " +
            "JOIN evidencia e ON d.evidencia_id_evidencia=e.id_evidencia " +
            "JOIN indicador i ON i.id_indicador=e.indicador_id_indicador " +
            "JOIN subcriterio s ON s.id_subcriterio=i.subcriterio_id_subcriterio " +
            "JOIN criterio cri ON cri.id_criterio=s.id_criterio " +
            "JOIN asignacion_evidencia ae ON ae.evidencia_id_evidencia=e.id_evidencia " +
            "JOIN usuarios u ON u.id=ae.usuario_id " +
            "JOIN persona per ON per.id_persona=u.persona_id_persona AND d.estado=false " +
            "WHERE d.id_modelo=:id_modelo", nativeQuery = true)
    List<EvidenciasProjection> evidenciaRechazada(Long id_modelo);

    @Query(value = "SELECT STRING_AGG(ar.enlace, ' - ') AS enlaces " +
            "FROM evidencia e " +
            "JOIN asignacion_evidencia ae ON ae.evidencia_id_evidencia = e.id_evidencia " +
            "JOIN archivo ar ON ar.id_asignacion_evidencia = ae.id_asignacion_evidencia AND ar.visible = true \n" +
            "JOIN usuarios u ON u.id = ae.usuario_id " +
            "JOIN indicador i ON e.indicador_id_indicador = i.id_indicador " +
            "JOIN asignacion_indicador ag ON ag.indicador_id_indicador = i.id_indicador " +
            "WHERE e.id_evidencia =:id_evidencia " +
            "AND u.visible = true  " +
            "AND ae.visible = true " +
            "AND e.visible = true " +
            "AND ag.modelo_id_modelo = (SELECT MAX(id_modelo) FROM modelo)", nativeQuery = true)
    List<EvidenciaProjection> listararchivos(Long id_evidencia);

    @Query(value = "SELECT i.tipo AS tipo, i.id_indicador AS id_in,i.peso AS peso,  e.descripcion AS descrip, " +
            "e.estado AS est from evidencia e JOIN asignacion_evidencia ae ON ae.evidencia_id_evidencia = e.id_evidencia " +
            "JOIN indicador i ON i.id_indicador = e.indicador_id_indicador AND i.visible = true " +
            "JOIN usuarios u ON ae.usuario_id = u.id where e.visible =true AND e.id_evidencia=:id_evidencia " +
            "AND ae.visible=true AND ae.id_modelo=:id_modelo ", nativeQuery = true)
    EvidenciaCalProjection evidenciacal(Long id_evidencia, Long id_modelo);

    @Query(value = "SELECT " +
            "SUM(CASE WHEN LOWER(asi.estado) = 'pendiente' THEN 1 ELSE 0 END) AS pendientes, \n" +
            "SUM(CASE WHEN LOWER(asi.estado) = 'aprobada' THEN 1 ELSE 0 END) AS aprobados, \n" +
            "SUM(CASE WHEN LOWER(asi.estado) = 'rechazada' THEN 1 ELSE 0 END) AS rechazados, \n" +
            "COUNT(*) AS total, "+
            "TRUNC((SUM(CASE WHEN LOWER(asi.estado) = 'pendiente' THEN 1 ELSE 0 END) * 100.0) / COUNT(*), 2) AS porcentaje_pendientes, " +
            "TRUNC((SUM(CASE WHEN LOWER(asi.estado) = 'aprobada' THEN 1 ELSE 0 END) * 100.0) / COUNT(*), 2) AS porcentaje_aprobados, " +
            "TRUNC((SUM(CASE WHEN LOWER(asi.estado) = 'rechazada' THEN 1 ELSE 0 END) * 100.0) / COUNT(*), 2) AS porcentaje_rechazados " +
            "FROM " +
            "evidencia e " +
            "JOIN " +
            "asignacion_evidencia asi ON e.id_evidencia = asi.evidencia_id_evidencia " +
            "WHERE " +
            "asi.usuario_id = :responsableId AND asi.visible = true AND asi.id_modelo= :id_modelo ", nativeQuery = true)
    ActiDiagramaPieProjection porcentajeEstadosdeActividadesByResponsableId(@Param("responsableId") Long responsableId,@Param("id_modelo") Long id_modelo);

    @Query(value = "SELECT SUM(CASE WHEN LOWER(asi.estado) = 'pendiente' THEN 1 ELSE 0 END) AS pendientes, " +
            "SUM(CASE WHEN LOWER(asi.estado) = 'aprobada' THEN 1 ELSE 0 END) AS aprobados, " +
            "SUM(CASE WHEN LOWER(asi.estado) = 'rechazada' THEN 1 ELSE 0 END) AS rechazados, " +
            "COUNT(*) AS total, " +
            "TRUNC((SUM(CASE WHEN LOWER(asi.estado) = 'pendiente' THEN 1 ELSE 0 END) * 100.0) / COUNT(*), 2) AS porcentaje_pendientes, " +
            "TRUNC((SUM(CASE WHEN LOWER(asi.estado) = 'aprobada' THEN 1 ELSE 0 END) * 100.0) / COUNT(*), 2) AS porcentaje_aprobados, " +
            "TRUNC((SUM(CASE WHEN LOWER(asi.estado) = 'rechazada' THEN 1 ELSE 0 END) * 100.0) / COUNT(*), 2) AS porcentaje_rechazados " +
            "FROM " +
            "evidencia e " +
            "JOIN " +
            "asignacion_evidencia asi ON e.id_evidencia = asi.evidencia_id_evidencia " +
            "JOIN " +
            "usuarios u ON u.id = asi.usuario_id " +
            "WHERE e.visible = true AND u.visible= true  AND asi.visible=true AND asi.id_modelo= :id_modelo", nativeQuery = true)
    ActiDiagramaPieProjection porcentajeEstadosdeEvidenciasGeneral(Long id_modelo);

    @Query("SELECT SUM(e.valor_obtenido) AS valor_obtenido FROM Evidencia e WHERE e.visible = true AND e.indicador.id_indicador = :id_indicador")
    ValorObtenidoInd obtenerTotalValoresEvidPorIndicador(@Param("id_indicador") Long id_indicador);
}
