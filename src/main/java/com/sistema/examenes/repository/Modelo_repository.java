package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Modelo;
import com.sistema.examenes.projection.ModelIndiProjection;
import com.sistema.examenes.projection.ModeloVistaProjection;
import com.sistema.examenes.projection.criteriosdesprojection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Modelo_repository extends JpaRepository<Modelo, Long> {
    @Query("SELECT m from Modelo m order by m.id_modelo desc")
    List<Modelo> listarModelo();

    @Query("SELECT m from Modelo m where m.id_modelo= (SELECT MAX(m2.id_modelo) FROM Modelo m2 WHERE m2.estadoad=true)")
    Modelo listarModeloMaximo();

    @Query("SELECT m from Modelo m where m.visible =true and m.id_modelo!=:id_modelo")
    List<Modelo> listarModeloExcepto(Long id_modelo);

        @Query( "SELECT m.id_modelo as id_modelo, " +
                "m.nombre as nombre, " +
                "m.fecha_fin as fecha_fin, " +
                "m.fecha_final_act as fecha_final_act, " +
                "m.fecha_inicio as fecha_inicio, " +
                "m.estadoad as estadoad, " +
                "(SELECT COUNT(*) FROM Asignacion_Indicador ai WHERE ai.modelo.id_modelo = m.id_modelo AND ai.visible = true) AS nro_indicadores, " +
                "COUNT(DISTINCT ic.subcriterio.id_subcriterio) AS nro_subcriterios, " +
                "COUNT(DISTINCT sc.criterio.id_criterio) AS nro_criterios " +
                "FROM Modelo m " +
                "JOIN Asignacion_Indicador ai2 ON ai2.modelo.id_modelo = m.id_modelo " +
                "JOIN ai2.indicador ic  " +
                "JOIN ic.subcriterio sc  " +
                "WHERE ai2.visible  = true AND ic.visible = true AND m.visible=true " +
                "GROUP by m.id_modelo " +
                "order by m.id_modelo DESC")
    List<ModeloVistaProjection> obtenerModeloVista();
    @Query( "SELECT cri.nombre AS crite, subc.nombre AS sub, i.id_indicador AS id_indi, i.nombre AS ind_nombre, " +
            "CASE WHEN ai.visible IS NOT NULL THEN ai.visible ELSE false END AS visi " +
            "FROM Criterio cri " +
            "JOIN Subcriterio subc ON subc.criterio.id_criterio = cri.id_criterio AND cri.visible=true " +
            "JOIN Indicador i ON i.subcriterio.id_subcriterio = subc.id_subcriterio AND subc.visible=true " +
            "LEFT JOIN Asignacion_Indicador  ai ON ai.indicador.id_indicador = i.id_indicador AND i.visible=true " +
            "AND ai.modelo.id_modelo =:id_modelo " +
            "ORDER BY cri.id_criterio,subc.id_subcriterio,i.id_indicador")
    List<ModelIndiProjection> listindiModelo(Long id_modelo);

    @Query(value = "SELECT cri.nombre AS criterionomj, " +
            "sub.nombre AS subcrierioj, " +
            "i.id_indicador AS id_indicardorj, " +
            "i.nombre AS ind_nombrej, " +
            "i.descripcion AS ides, " +
            "i.tipo AS tip, " +
            "ev.descripcion AS descrip, " +
            "i.id_indicador, " +
            "ev.id_evidencia AS id_evidencia, " +
            "ac.estado AS estado_evi," +
            "(CASE " +
            "   WHEN (SELECT count(evi.id_evidencia) FROM evidencia evi WHERE evi.indicador_id_indicador = i.id_indicador) = 0 THEN 0 " +
            "   ELSE (i.peso / (SELECT count(evi.id_evidencia) FROM evidencia evi WHERE evi.indicador_id_indicador = i.id_indicador)) " +
            "END) AS peso_evid, " +
            "i.peso AS pes, " +
            "COALESCE(ci.porc_obtenido, 0) AS obt," +
            "COALESCE(ci.porc_utilida_obtenida, 0) AS uti," +
            "COALESCE(ci.valor_obtenido, 0) AS val," +
            "CASE WHEN ai.visible IS NOT NULL THEN ai.visible ELSE false END AS visi, " +
            "arc.nombre AS archivo_nombre, " +
            "arc.enlace AS archivo_enlace " +
            "FROM criterio cri " +
            "JOIN subcriterio sub ON cri.id_criterio = sub.id_criterio AND sub.visible = true " +
            "LEFT JOIN indicador i ON sub.id_subcriterio = i.subcriterio_id_subcriterio AND i.visible = true " +
            "LEFT JOIN calificar_indicador ci ON i.id_indicador = ci.indicador_id_indicador AND ci.id_modelo = :id_modelo " +
            "LEFT JOIN asignacion_indicador ai ON i.id_indicador = ai.indicador_id_indicador AND ai.modelo_id_modelo = :id_modelo " +
            "LEFT JOIN evidencia ev ON i.id_indicador = ev.indicador_id_indicador AND ev.visible = true " +
            "LEFT JOIN asignacion_evidencia ac ON ev.id_evidencia = ac.evidencia_id_evidencia AND ac.visible = true AND ac.id_modelo= :id_modelo " +
            "LEFT JOIN archivo arc ON ac.id_asignacion_evidencia = arc.id_asignacion_evidencia AND arc.visible = true AND arc.id_modelo= :id_modelo " +
            "WHERE ai.modelo_id_modelo = :id_modelo " +
            "AND cri.nombre = :nombre " +
            "ORDER BY cri.nombre, sub.nombre, i.nombre, CAST(SUBSTRING(ev.descripcion FROM '^[0-9]+') AS INTEGER), " +
            "ev.descripcion ASC", nativeQuery = true)
    List<criteriosdesprojection> listicritedes(Long id_modelo,String nombre);

    @Query(value = "SELECT cri.nombre AS criterionomj, " +
            "sub.nombre AS subcrierioj, " +
            "i.id_indicador AS id_indicardorj, " +
            "i.nombre AS ind_nombrej, " +
            "i.descripcion AS ides, " +
            "i.tipo AS tip, " +
            "ev.descripcion AS descrip, " +
            "ac.estado AS estado_evi," +
            "(CASE " +
            "WHEN (SELECT count(evi.id_evidencia) FROM evidencia evi WHERE evi.indicador_id_indicador = i.id_indicador) = 0 THEN 0 " +
            "ELSE (i.peso / (SELECT count(evi.id_evidencia) FROM evidencia evi WHERE evi.indicador_id_indicador = i.id_indicador)) " +
            "END) AS peso_evid, " +
            "i.peso AS pes, " +
            "COALESCE(ci.porc_obtenido, 0) AS obt," +
            "COALESCE(ci.porc_utilida_obtenida, 0) AS uti," +
            "COALESCE(ci.valor_obtenido, 0) AS val," +
            "CASE WHEN ai.visible IS NOT NULL THEN ai.visible ELSE false END AS visi, " +
            "arc.nombre AS archivo_nombre, " +
            "arc.enlace AS archivo_enlace " +
            "FROM criterio cri " +
            "JOIN subcriterio sub ON cri.id_criterio = sub.id_criterio AND sub.visible = true " +
            "LEFT JOIN indicador i ON sub.id_subcriterio = i.subcriterio_id_subcriterio AND i.visible = true " +
            "LEFT JOIN calificar_indicador ci ON i.id_indicador = ci.indicador_id_indicador AND ci.id_modelo = :id_modelo " +
            "LEFT JOIN asignacion_indicador ai ON i.id_indicador = ai.indicador_id_indicador AND ai.visible=true " +
            "LEFT JOIN evidencia ev ON i.id_indicador = ev.indicador_id_indicador AND ev.visible = true " +
            "LEFT JOIN asignacion_evidencia ac ON ev.id_evidencia = ac.evidencia_id_evidencia AND ac.visible = true AND ac.id_modelo= :id_modelo " +
            "LEFT JOIN archivo arc ON ac.id_asignacion_evidencia = arc.id_asignacion_evidencia AND arc.visible = true AND arc.id_modelo= :id_modelo " +
            "WHERE cri.id_criterio = :id_criterio  AND ai.modelo_id_modelo = :id_modelo " +
            "ORDER BY cri.id_criterio, sub.id_subcriterio, i.id_indicador", nativeQuery = true)
    List<criteriosdesprojection> listcritmodel(Long id_criterio, Long id_modelo);

    @Query(value = "SELECT cri.nombre AS criterionomj, " +
            "sub.nombre AS subcrierioj, " +
            "i.id_indicador AS id_indicardorj, " +
            "i.nombre AS ind_nombrej, " +
            "ev.descripcion AS descrip, " +
            "ac.estado AS estado_evi, " +
            "(CASE " +
            "WHEN (SELECT count(evi.id_evidencia) FROM evidencia evi WHERE evi.indicador_id_indicador = i.id_indicador) = 0 THEN 0 " +
            "ELSE (i.peso / (SELECT count(evi.id_evidencia) FROM evidencia evi WHERE evi.indicador_id_indicador = i.id_indicador)) " +
            "END) AS peso_evid, " +
            "i.peso AS pes, " +
            "COALESCE(ci.porc_obtenido, 0) AS obt," +
            "COALESCE(ci.porc_utilida_obtenida, 0) AS uti," +
            "COALESCE(ci.valor_obtenido, 0) AS val," +
            "i.tipo AS tip, " +
            "CASE WHEN ai.visible IS NOT NULL THEN ai.visible ELSE false END AS visi, " +
            "arc.nombre AS archivo_nombre, " +
            "arc.enlace AS archivo_enlace " +
            "FROM criterio cri " +
            "JOIN subcriterio sub ON cri.id_criterio = sub.id_criterio AND sub.visible = true " +
            "LEFT JOIN indicador i ON sub.id_subcriterio = i.subcriterio_id_subcriterio AND i.visible = true " +
            "LEFT JOIN calificar_indicador ci ON i.id_indicador = ci.indicador_id_indicador AND ci.id_modelo = :id_modelo " +
            "LEFT JOIN asignacion_indicador ai ON i.id_indicador = ai.indicador_id_indicador  AND ai.modelo_id_modelo = :id_modelo " +
            "LEFT JOIN evidencia ev ON i.id_indicador = ev.indicador_id_indicador AND ev.visible = true " +
            "LEFT JOIN asignacion_evidencia ac ON ev.id_evidencia = ac.evidencia_id_evidencia AND ac.visible = true  AND ac.id_modelo= :id_modelo " +
            "LEFT JOIN archivo arc ON ac.id_asignacion_evidencia = arc.id_asignacion_evidencia AND arc.visible = true AND arc.id_modelo= :id_modelo " +
            "JOIN asignacion_admin aa ON aa.criterio_id_criterio = cri.id_criterio AND aa.visible = true AND aa.id_modelo = :id_modelo " +
            "WHERE ai.modelo_id_modelo = :id_modelo AND aa.usuario_id = :id " +
            "ORDER BY cri.nombre, sub.nombre, i.nombre, CAST(SUBSTRING(ev.descripcion FROM '^[0-9]+') AS INTEGER), " +
            "ev.descripcion ASC", nativeQuery = true)
    List<criteriosdesprojection> criterioadmin(Long id_modelo,Long id);

    @Query(value = "SELECT DISTINCT cri.id_criterio, sub.id_subcriterio, i.id_indicador, ae.evidencia_id_evidencia, " +
            "cri.nombre AS criterionomj, sub.nombre AS subcrierioj, i.id_indicador AS id_indicardorj, " +
            "i.nombre AS ind_nombrej, ev.descripcion AS descrip, i.peso AS pes, i.porc_obtenido AS obt, " +
            "i.tipo AS tip, i.porc_utilida_obtenida AS uti, i.valor_obtenido AS val, " +
            "CASE WHEN ai.visible IS NOT NULL THEN ai.visible ELSE false END AS visi, " +
            "arc.nombre AS archivo_nombre, arc.enlace AS archivo_enlace " +
            "FROM indicador i " +
            "JOIN asignacion_indicador ai ON i.id_indicador = ai.indicador_id_indicador " +
            "JOIN subcriterio sub ON sub.id_subcriterio = i.subcriterio_id_subcriterio AND i.visible = true " +
            "JOIN criterio cri ON cri.id_criterio = sub.id_criterio AND sub.visible = true " +
            "JOIN evidencia ev ON i.id_indicador = ev.indicador_id_indicador AND ev.visible = true " +
            "LEFT JOIN actividad ac ON ev.id_evidencia = ac.id_evidencia AND ac.visible = true " +
            "LEFT JOIN archivo arc ON ac.id_actividad = arc.id_actividad AND arc.visible = true " +
            "JOIN asignacion_admin aa ON aa.criterio_id_criterio = cri.id_criterio AND aa.visible = true AND aa.id_modelo =:id_modelo " +
            "JOIN asignacion_evidencia ae ON ev.id_evidencia = ae.evidencia_id_evidencia AND ae.visible = true AND ae.id_modelo =:id_modelo " +
            "WHERE ai.modelo_id_modelo =:id_modelo AND ae.usuario_id =:id " +
            "ORDER BY cri.id_criterio, sub.id_subcriterio, i.id_indicador, ae.evidencia_id_evidencia ", nativeQuery = true)
    List<criteriosdesprojection> criterioresp(Long id_modelo,Long id);

    @Query(value = "SELECT cri.nombre AS criterionomj,sub.nombre AS subcrierioj,i.id_indicador AS id_indicardorj, " +
            "i.nombre AS ind_nombrej,CASE WHEN ai.visible IS NOT NULL THEN ai.visible ELSE false END AS visi, " +
            "arc.nombre AS archivo_nombre,arc.enlace AS archivo_enlace " +
            "FROM archivo arc JOIN actividad ac ON ac.id_actividad = arc.id_actividad " +
            "JOIN evidencia ev ON ev.id_evidencia = ac.id_evidencia " +
            "JOIN indicador i ON ev.indicador_id_indicador = i.id_indicador " +
            "JOIN subcriterio sub ON i.subcriterio_id_subcriterio = sub.id_subcriterio AND sub.visible = true " +
            "JOIN criterio cri ON cri.id_criterio = sub.id_criterio AND sub.visible = true " +
            "LEFT JOIN asignacion_indicador ai ON ai.indicador_id_indicador = i.id_indicador AND i.visible = true " +
            "WHERE ai.modelo_id_modelo =:id_modelo AND cri.nombre =:nomcrite ORDER BY cri.id_criterio, sub.id_subcriterio, i.id_indicador", nativeQuery = true)
    List<criteriosdesprojection> listicrinom(Long id_modelo, String nomcrite);
    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END " +
            "FROM modelo WHERE fecha_inicio <=CAST(:fin AS DATE) " +
            "AND fecha_fin >=CAST(:inicio AS DATE) " +
            "AND id_modelo=(SELECT MAX(id_modelo) FROM modelo)", nativeQuery = true)
    Boolean existefecha(String inicio,String fin);
    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END " +
            "FROM modelo WHERE fecha_inicio <=CAST(:fin AS DATE) AND fecha_fin >=CAST(:inicio AS DATE) " +
            "AND id_modelo=(SELECT MAX(id_modelo) FROM modelo " +
            "WHERE id_modelo < (SELECT MAX(id_modelo) FROM modelo))", nativeQuery = true)
    Boolean fechaeditar(String inicio,String fin);

}
