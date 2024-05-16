package com.sistema.examenes.repository;

import java.util.List;

import com.sistema.examenes.projection.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sistema.examenes.entity.Indicador;
import org.springframework.data.repository.query.Param;


public interface Indicador_repository extends JpaRepository<Indicador, Long> {
    // un query para buscar por id_subcriterio
    @Query("SELECT ind from Indicador ind where ind.subcriterio.id_subcriterio = :id_subcriterio")
    List<Indicador> listarIndicadorPorSubcriterio(Long id_subcriterio);

    @Query("SELECT i from Indicador i JOIN i.subcriterio s " +
            "JOIN s.criterio cri " +
            "WHERE i.visible =true " +
            "ORDER BY cri.id_criterio, s.id_subcriterio, i.id_indicador")
    List<Indicador> listarIndicador();

    @Query("SELECT i.id_indicador as idindicador, cri.nombre as nombrecriterio, s.nombre as nombresubcriterio, i.nombre as nombreindicador, " +
            "i.valor_obtenido as valorobtenido, i.peso as peso, " +
            "i.porc_obtenido as porcentajeobtenido, i.porc_utilida_obtenida as porcentajeutilidad " +
            "FROM Asignacion_Indicador ai " +
            "JOIN ai.indicador i " +
            "JOIN i.subcriterio s " +
            "JOIN s.criterio cri " +
            "WHERE ai.modelo.id_modelo = :id_modelo " +
            "AND i.visible = true ORDER BY cri.id_criterio ASC")
    List<PonderacionProjection> listarIndicadoresModelo(Long id_modelo);

    @Query("SELECT i FROM Indicador i " +
            "JOIN i.subcriterio s " +
            "JOIN s.criterio c  " +
            "WHERE c.id_criterio = :criterio " +
            "GROUP BY i.id_indicador,s.id_subcriterio, c.id_criterio, c.nombre " +
            "ORDER BY i.id_indicador")
    List<Indicador> obtenerIndicadores(Long criterio);

    @Query("SELECT cri.nombre AS nombre, " +
            "SUM(i.porc_utilida_obtenida) AS total, " +
            "SUM(i.peso) AS faltante " +
            "FROM Indicador i JOIN i.subcriterio sub " +
            "JOIN sub.criterio cri " +
            "JOIN Asignacion_Admin aa ON aa.criterio.id_criterio = cri.id_criterio AND aa.visible = true " +
            "AND aa.id_modelo.id_modelo = :id_modelo " +
            "GROUP BY cri.nombre, cri.id_criterio " +
            "ORDER BY cri.id_criterio")
    List<IndicadoresProjection> Indicadores(Long id_modelo);

    @Query("SELECT cri.id_criterio AS id_criterio, " +
            "cri.nombre AS nombre, " +
            "SUM(i.porc_utilida_obtenida) AS total, " +
            "SUM(i.peso) - SUM(i.porc_utilida_obtenida) AS faltante " +
            "FROM Indicador i " +
            "JOIN Asignacion_Indicador ai ON ai.indicador.id_indicador = i.id_indicador " +
            "JOIN i.subcriterio sub " +
            "JOIN sub.criterio cri " +
            "JOIN Asignacion_Admin aa ON aa.criterio.id_criterio = cri.id_criterio AND aa.visible = true " +
            "JOIN Modelo m ON ai.modelo.id_modelo = m.id_modelo " +
            "WHERE m.visible =true AND i.visible=true AND ai.modelo.id_modelo =:id_modelo AND aa.usuario.id=:id " +
            "GROUP BY cri.nombre, cri.id_criterio " +
            "ORDER BY cri.id_criterio")
    List<CriterioPorcProjection> indicadoresadmin(@Param("id_modelo") Long id_modelo, @Param("id") Long id);

    @Query( "SELECT cri.nombre AS nombre, " +
            "SUM(i.porc_utilida_obtenida)  AS total, " +
            "SUM(i.peso) AS faltante " +
            "FROM Indicador i " +
            "JOIN i.subcriterio sub " +
            "JOIN sub.criterio cri  " +
            "JOIN Asignacion_Admin aa ON aa.criterio.id_criterio=cri.id_criterio AND aa.visible=true " +
            "AND aa.id_modelo.id_modelo=:id_modelo AND aa.criterio.id_criterio IN " +
            "(SELECT DISTINCT cri.id_criterio FROM Asignacion_Evidencia ae " +
            "JOIN ae.evidencia e " +
            "JOIN e.indicador i " +
            "JOIN i.subcriterio s " +
            "JOIN s.criterio cri " +
            "WHERE ae.usuario.id =:id AND ae.id_modelo =:id_modelo AND ae.visible=true ) " +
            "GROUP BY cri.nombre,cri.id_criterio  " +
            "ORDER BY cri.id_criterio")
    List<IndicadoresProjection> indicadoresresp(Long id_modelo,Long id);
    @Query( "SELECT i " +
            "FROM Modelo m " +
            "JOIN Asignacion_Indicador a ON a.modelo.id_modelo = m.id_modelo " +
            "JOIN a.indicador i  " +
            "JOIN i.subcriterio s " +
            "JOIN s.criterio c " +
            "WHERE c.id_criterio= :id_criterio and m.id_modelo= :id_modelo AND a.visible=true " +
            "ORDER BY i.id_indicador")
    List<Indicador> listarIndicadorPorCriterioModelo(Long id_criterio, Long id_modelo);

    @Query("SELECT DISTINCT cri.nombre as nombrecriterio, s.nombre as nombresubcriterio, i.nombre as nombreindicador, " +
            "i.descripcion as descripcionindicador, i.valor_obtenido as valorobtenido, i.porc_obtenido as porcentajeobtenido, " +
            "i.porc_utilida_obtenida as porcentajeutilidad " +
            "FROM Asignacion_Indicador ai " +
            "JOIN ai.indicador i " +
            "JOIN i.subcriterio s " +
            "JOIN s.criterio cri " +
            "WHERE ai.modelo.id_modelo = :id_modelo " +
            "AND (cri.id_criterio IN :idCriterios OR :idCriterios IS NULL)")
    List<IndicadoresProjection> indicadoresPorCriterios(Long id_modelo,List<Long> idCriterios);

    @Query("SELECT DISTINCT cri.nombre as nombrecriterio, s.nombre as nombresubcriterio, i.nombre as nombreindicador, " +
            "i.descripcion as descripcionindicador, COALESCE(ci.valor_obtenido, 0) as valorobtenido, COALESCE(ci.porc_obtenido, 0) as porcentajeobtenido, " +
            "COALESCE(ci.porc_utilida_obtenida, 0) as porcentajeutilidad, i.tipo as tipo " +
            "FROM Asignacion_Indicador ai " +
            "JOIN ai.indicador i " +
            "LEFT JOIN Calificar_Indicador ci ON i.id_indicador=ci.indicador.id_indicador AND ci.id_modelo= :id_modelo " +
            "JOIN i.subcriterio s " +
            "JOIN s.criterio cri " +
            "WHERE ai.modelo.id_modelo = :id_modelo " +
            "AND (cri.id_criterio IN :idCriterios OR :idCriterios IS NULL) AND i.tipo='cualitativa'")
    List<IndicadoresProjection> indicadoresPorCriteriosPruebaCualitativa(List<Long> idCriterios, Long id_modelo);

    @Query("SELECT DISTINCT cri.nombre as nombrecriterio, s.nombre as nombresubcriterio, i.nombre as nombreindicador, " +
            "i.descripcion as descripcionindicador, COALESCE(ci.valor_obtenido, 0) as valorobtenido, COALESCE(ci.porc_obtenido, 0) as porcentajeobtenido, " +
            "COALESCE(ci.porc_utilida_obtenida, 0) as porcentajeutilidad, i.tipo as tipo " +
            "FROM Asignacion_Indicador ai " +
            "JOIN ai.indicador i " +
            "LEFT JOIN Calificar_Indicador ci ON i.id_indicador=ci.indicador.id_indicador AND ci.id_modelo= :id_modelo " +
            "JOIN i.subcriterio s " +
            "JOIN s.criterio cri " +
            "WHERE ai.modelo.id_modelo = :id_modelo " +
            "AND (cri.id_criterio IN :idCriterios OR :idCriterios IS NULL) AND i.tipo='cuantitativa'")
    List<IndicadoresProjection> indicadoresPorCriteriosPruebaCuantitativa(List<Long> idCriterios,Long id_modelo);

    /*
        @Query("SELECT DISTINCT i, c FROM Indicador i " +
            "JOIN i.subcriterio s " +
            "JOIN s.criterio c " +
            "JOIN Asignacion_Indicador ai ON ai.indicador = i " +
            "WHERE ai.modelo.id_modelo = (SELECT MAX(m.id_modelo) FROM Modelo m) " +
            "AND (c.id_criterio IN :idCriterios OR COALESCE(:idCriterios, NULL) IS NULL) AND i.tipo='cualitativa'")
    List<Indicador> indicadoresPorCriteriosPruebaCuali(List<Long> idCriterios);

    @Query("SELECT DISTINCT i, c FROM Indicador i " +
            "JOIN i.subcriterio s " +
            "JOIN s.criterio c " +
            "JOIN Asignacion_Indicador ai ON ai.indicador = i " +
            "WHERE ai.modelo.id_modelo = (SELECT MAX(m.id_modelo) FROM Modelo m) " +
            "AND (c.id_criterio IN :idCriterios OR COALESCE(:idCriterios, NULL) IS NULL) AND i.tipo='cuantitativa'")
    List<Indicador> indicadoresPorCriteriosPruebaCuanti(List<Long> idCriterios);
    */


   /* @Query(value ="SELECT DISTINCT m.id_modelo, c.nombre, s.nombre, \n" +
            "i.nombre, e.nombre, i.valor_obtenido, i.porc_obtenido, i.porc_utilida_obtenida, \n" +
            "de.observacion \n" +
            "FROM criterio c \n" +
            "LEFT JOIN subcriterio s ON c.id_criterio= s.id_criterio \n" +
            "LEFT JOIN indicador i ON s.id_subcriterio = i.subcriterio_id_subcriterio \n" +
            "LEFT JOIN asignacion_indicador ai ON i.id_indicador = ai.indicador_id_indicador \n" +
            "LEFT JOIN modelo m ON ai.modelo_id_modelo = m.id_modelo \n" +
            "LEFT JOIN evidencia e ON i.id_indicador = e.indicador_id_indicador \n" +
            "LEFT JOIN detalle_evaluacion de ON e.id_evidencia = de.evidencia_id_evidencia \n" +
            "WHERE ai.modelo_id_modelo = (SELECT MAX(m.id_modelo) FROM Modelo m) AND i.tipo='cualitativa' \n" +
            "ORDER BY m.id_modelo ", nativeQuery = true)
    List<Indicador> indicadoresPorCriteriosCuali();

    @Query(value ="SELECT DISTINCT m.id_modelo, c.nombre, s.nombre, \n" +
            "i.nombre, e.nombre, i.valor_obtenido, i.porc_obtenido, i.porc_utilida_obtenida, \n" +
            "de.observacion \n" +
            "FROM criterio c \n" +
            "LEFT JOIN subcriterio s ON c.id_criterio= s.id_criterio \n" +
            "LEFT JOIN indicador i ON s.id_subcriterio = i.subcriterio_id_subcriterio \n" +
            "LEFT JOIN asignacion_indicador ai ON i.id_indicador = ai.indicador_id_indicador \n" +
            "LEFT JOIN modelo m ON ai.modelo_id_modelo = m.id_modelo \n" +
            "LEFT JOIN evidencia e ON i.id_indicador = e.indicador_id_indicador \n" +
            "LEFT JOIN detalle_evaluacion de ON e.id_evidencia = de.evidencia_id_evidencia \n" +
            "WHERE ai.modelo_id_modelo = (SELECT MAX(m.id_modelo) FROM Modelo m) AND i.tipo='cuantitativa' \n" +
            "ORDER BY m.id_modelo ", nativeQuery = true)
    List<Indicador> indicadoresPorCriteriosCuanti();*/

    @Query( "SELECT i.id_indicador AS id_indicador ,i.nombre AS nombre, i.descripcion AS descripcion, i.peso AS peso, i.estandar AS estandar, i.tipo AS tipo ," +
            "i.valor_obtenido AS valor_obtenido,i.porc_obtenido AS porc_obtenido,i.porc_utilida_obtenida AS porc_utilida_obtenida, i.visible AS visible, " +
            "(SELECT COUNT(e2.id_evidencia) " +
            "FROM Evidencia e2 WHERE e2.indicador.id_indicador = i.id_indicador AND e2.visible = true) " +
            "AS cantidadEvidencia " +
            "FROM Indicador i " +
            "JOIN Asignacion_Indicador ai ON ai.indicador.id_indicador = i.id_indicador AND ai.visible = true AND ai.modelo.id_modelo= :id_modelo " +
            "LEFT JOIN Evidencia e ON i.id_indicador = e.indicador.id_indicador "+
            "WHERE i.visible = true " +
            "AND i.subcriterio.id_subcriterio = :id_subcriterio " +
            "GROUP BY i.id_indicador " +
            "ORDER BY i.descripcion ASC")
    List<IndicadorEvidenciasProjection> obtenerIndicadoresConCantidadEvidencia(Long id_subcriterio, Long id_modelo);

   /* @Query(value = "SELECT i.id_indicador, i.nombre indicador, c.nombre criterio, s.nombre subcriterio, e.nombre evidencia, i.descripcion, i.peso, i.estandar, i.tipo ," +
            "i.valor_obtenido,i.porc_obtenido,i.porc_utilida_obtenida, i.visible, " +
            "(SELECT COUNT(e2.id_evidencia) " +
            "FROM evidencia e2 WHERE e2.indicador_id_indicador = i.id_indicador AND e2.visible = true) " +
            "AS cantidadEvidencia," +
            "s.nombre as nombreSubcriterio " +
            "FROM indicador i " +
            "JOIN subcriterio s " +
            "ON s.id_subcriterio = i.subcriterio_id_subcriterio " +
            "JOIN criterio c " +
            "ON c.id_criterio = s.id_criterio " +
            "LEFT JOIN evidencia e " +
            "ON i.id_indicador = e.indicador_id_indicador "+
            "WHERE i.visible = true " +
            "GROUP BY i.id_indicador, c.nombre, s.nombre, e.nombre " +
            "ORDER BY s.nombre, i.id_indicador ", nativeQuery = true)
    List<IndicadorEvidenciasProjectionFull> obtenerIndicadoresConCantidadEvidenciaFull();*/

    @Query("SELECT DISTINCT i FROM Indicador i " +
            "JOIN i.subcriterio s " +
            "JOIN Asignacion_Indicador ai ON ai.indicador = i " +
            "WHERE ai.modelo.id_modelo = :id_modelo " +
            "AND i.visible = true")
    List<Indicador> indicadoresPorModelo(Long id_modelo);

    @Query(value = "SELECT DISTINCT i.*, COALESCE(per.primer_nombre || ' ' || per.primer_apellido, 'Las evidencias no estan asignadas') AS respon " +
            "FROM indicador i JOIN subcriterio s ON i.subcriterio_id_subcriterio = s.id_subcriterio " +
            "JOIN criterio cri ON cri.id_criterio = s.id_criterio JOIN asignacion_indicador ai " +
            "ON ai.indicador_id_indicador = i.id_indicador AND ai.visible = true " +
            "LEFT JOIN evidencia e ON e.indicador_id_indicador = i.id_indicador AND e.visible = true " +
            "LEFT JOIN asignacion_evidencia aa ON aa.evidencia_id_evidencia = e.id_evidencia AND " +
            "aa.id_modelo = ai.modelo_id_modelo AND aa.visible = true " +
            "LEFT JOIN usuarios u ON u.id = aa.usuario_id AND u.visible = true " +
            "LEFT JOIN persona per ON per.id_persona = u.persona_id_persona AND " +
            "per.visible = true WHERE s.id_subcriterio =:id_subcriterio AND ai.modelo_id_modelo =:id_modelo " +
            "AND (per.id_persona IS NOT NULL OR NOT EXISTS " +
            "(SELECT 1 FROM asignacion_indicador ai2 LEFT JOIN evidencia e2 ON " +
            "e2.indicador_id_indicador = ai2.indicador_id_indicador AND e2.visible = true " +
            "LEFT JOIN asignacion_evidencia aa2 ON aa2.evidencia_id_evidencia = e2.id_evidencia AND aa2.id_modelo = ai2.modelo_id_modelo AND aa2.visible = true " +
            "LEFT JOIN usuarios u2 ON u2.id = aa2.usuario_id AND u2.visible = true " +
            "LEFT JOIN persona per2 ON per2.id_persona = u2.persona_id_persona AND per2.visible = true " +
            "WHERE ai2.modelo_id_modelo = ai.modelo_id_modelo AND ai2.indicador_id_indicador = ai.indicador_id_indicador " +
            "AND per2.id_persona IS NOT NULL)) ORDER BY i.descripcion ASC;", nativeQuery = true)
    List<IndicadorResp> indicadorPorSubcriterio(Long id_subcriterio,Long id_modelo);


    @Query(value = "WITH color_values AS ( " +
            "SELECT 'verde' AS color UNION ALL SELECT 'amarillo' " +
            "UNION ALL SELECT 'naranja' UNION ALL SELECT 'rojo') " +
            "SELECT COALESCE(counts.indica, 0) AS indica, cv.color AS color, " +
            "ROUND(COALESCE(counts.indica, 0) * 100.0 / NULLIF(total.total_count, 0), 2) AS porcentaje " +
            "FROM color_values cv LEFT JOIN (SELECT CASE " +
            "WHEN ci.porc_obtenido > 75 THEN 'verde' " +
            "WHEN ci.porc_obtenido > 50 AND ci.porc_obtenido <= 75 THEN 'amarillo' " +
            "WHEN ci.porc_obtenido > 25 AND ci.porc_obtenido <= 50 THEN 'naranja' " +
            "ELSE 'rojo' END AS color, " +
            "COUNT(ai.indicador_id_indicador) AS indica FROM asignacion_indicador ai " +
            "JOIN indicador i ON i.id_indicador = ai.indicador_id_indicador AND ai.visible = true " +
            "LEFT JOIN calificar_indicador ci ON i.id_indicador = ci.indicador_id_indicador AND ci.id_modelo = :id_modelo " +
            "JOIN modelo mo ON ai.modelo_id_modelo = mo.id_modelo " +
            "WHERE mo.id_modelo =:id_modelo AND i.visible = true GROUP BY color " +
            ") counts ON cv.color = counts.color LEFT JOIN ( " +
            "SELECT COUNT(indicador_id_indicador) AS total_count " +
            "FROM asignacion_indicador WHERE modelo_id_modelo =:id_modelo AND visible = true) " +
            "AS total ON 1=1;", nativeQuery = true)
    List<IndiColProjection> indicadorval(Long id_modelo);

    @Query(value = "SELECT i.id_indicador AS id_indicador," +
            " i.nombre AS nombre_indicador, " +
            " i.porc_obtenido AS porc_obtenido " +
            " FROM Indicador i " +
            " JOIN Asignacion_Indicador ai ON ai.indicador.id_indicador = i.id_indicador " +
            " WHERE i.visible=true AND ai.visible =true AND i.porc_obtenido > 75 AND ai.modelo.id_modelo =:id_modelo")
    List<IndicadoresGPieProjection> indicadoresPorcObtenidoM75(Long id_modelo);
    @Query(value = "SELECT i.id_indicador AS id_indicador," +
            " i.nombre AS nombre_indicador, " +
            " i.porc_obtenido AS porc_obtenido " +
            " FROM Indicador i " +
            " JOIN Asignacion_Indicador ai ON ai.indicador.id_indicador = i.id_indicador " +
            " WHERE i.visible=true AND ai.visible =true AND i.porc_obtenido > 50 AND i.porc_obtenido <= 75 AND ai.modelo.id_modelo =:id_modelo")
    List<IndicadoresGPieProjection> indicadoresPorcObtenido50_75(Long id_modelo);
    @Query(value = "SELECT i.id_indicador AS id_indicador," +
            " i.nombre AS nombre_indicador, " +
            " i.porc_obtenido AS porc_obtenido " +
            " FROM Indicador i " +
            " JOIN Asignacion_Indicador ai ON ai.indicador.id_indicador = i.id_indicador " +
            " WHERE i.visible=true AND ai.visible =true AND i.porc_obtenido > 25 AND i.porc_obtenido <= 50 AND ai.modelo.id_modelo =:id_modelo")
    List<IndicadoresGPieProjection> indicadoresPorcObtenido25_50(Long id_modelo);
    @Query(value = "SELECT i.id_indicador AS id_indicador," +
            " i.nombre AS nombre_indicador, " +
            " i.porc_obtenido AS porc_obtenido " +
            " FROM Indicador i " +
            " JOIN Asignacion_Indicador ai ON ai.indicador.id_indicador = i.id_indicador " +
            " WHERE i.visible=true AND ai.visible =true AND i.porc_obtenido >= 0 AND i.porc_obtenido <= 25 AND ai.modelo.id_modelo =:id_modelo")
    List<IndicadoresGPieProjection> indicadoresPorcObtenido0_25(Long id_modelo);
    @Query(value = "WITH color_values AS (SELECT 'verde' AS color UNION ALL SELECT 'amarillo' " +
            "UNION ALL SELECT 'naranja' UNION ALL SELECT 'rojo') " +
            "SELECT COALESCE(counts.indica, 0) AS indica, cv.color AS color, " +
            "ROUND(COALESCE(counts.indica, 0) * 100.0 / NULLIF(total.total_count, 0), 2) AS porcentaje " +
            "FROM color_values cv LEFT JOIN (SELECT CASE " +
            "WHEN i.porc_obtenido > 75 THEN 'verde' " +
            "WHEN i.porc_obtenido > 50 AND i.porc_obtenido <= 75 THEN 'amarillo' " +
            "WHEN i.porc_obtenido > 25 AND i.porc_obtenido <= 50 THEN 'naranja' " +
            "ELSE 'rojo' END AS color, " +
            "COUNT(ai.indicador_id_indicador) AS indica FROM asignacion_indicador ai " +
            "JOIN indicador i ON i.id_indicador = ai.indicador_id_indicador AND ai.visible = true " +
            "JOIN subcriterio s ON s.id_subcriterio=i.subcriterio_id_subcriterio " +
            "JOIN criterio cri ON cri.id_criterio=s.id_criterio " +
            "JOIN asignacion_admin aa ON aa.criterio_id_criterio=cri.id_criterio " +
            "JOIN modelo mo ON ai.modelo_id_modelo = mo.id_modelo " +
            "WHERE mo.id_modelo =:id_modelo AND aa.usuario_id=:id AND i.visible = true GROUP BY color) counts " +
            "ON cv.color = counts.color LEFT JOIN (SELECT COUNT(ai.indicador_id_indicador) AS total_count " +
            "FROM asignacion_indicador ai JOIN indicador i ON i.id_indicador=ai.indicador_id_indicador " +
            "JOIN subcriterio s ON s.id_subcriterio=i.subcriterio_id_subcriterio " +
            "JOIN criterio cri ON cri.id_criterio=s.id_criterio " +
            "JOIN asignacion_admin aa ON aa.criterio_id_criterio=cri.id_criterio AND aa.visible=true " +
            "WHERE ai.modelo_id_modelo =:id_modelo AND aa.usuario_id=:id AND ai.visible = true) " +
            "AS total ON 1=1;", nativeQuery = true)
    List<IndiColProjection> indicadorvaladmin(Long id_modelo,Long id);

    //Grafica de barras, porcentajes de indicadores por subcriterio
    @Query("SELECT i.id_indicador AS id_indicador, " +
            "i.nombre AS nombre, " +
            "COALESCE(SUM(ci.porc_utilida_obtenida), 0) AS total, " +
            "COALESCE(SUM(i.peso) - SUM(ci.porc_utilida_obtenida),0) AS faltante " +
            "FROM Indicador i " +
            "JOIN Asignacion_Indicador ai ON ai.indicador.id_indicador = i.id_indicador AND ai.visible=true " +
            "LEFT JOIN Calificar_Indicador ci ON i.id_indicador = ci.indicador.id_indicador AND ci.id_modelo = :id_modelo " +
            "JOIN i.subcriterio sub " +
            "JOIN sub.criterio cri " +
            "WHERE i.visible=true  AND ai.modelo.id_modelo =:id_modelo AND  sub.nombre=:sub_nombre " +
            "GROUP BY i.nombre, i.id_indicador " +
            "ORDER BY i.id_indicador")
    List<IndicadorPorcProjection> indicadoreporsubcriterio(String sub_nombre, Long id_modelo);

}
