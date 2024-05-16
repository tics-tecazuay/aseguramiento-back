package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Criterio;
import com.sistema.examenes.projection.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Criterio_repository extends JpaRepository<Criterio, Long> {

    @Query("SELECT c FROM Criterio c WHERE c.visible = true")
    List<Criterio> listarCriterio();
        @Query(value = "SELECT per.correo " +
                "FROM persona per JOIN usuarios u ON u.persona_id_persona=per.id_persona " +
                "JOIN asignacion_evidencia ae ON ae.usuario_id=u.id " +
                "WHERE ae.id_modelo=:id_modelo AND ae.evidencia_id_evidencia=:id_evidencia AND ae.visible=true ", nativeQuery = true)
        CorreoProjection getCorreo(Long id_modelo,Long id_evidencia);
    @Query("SELECT c FROM Criterio c " +
            "JOIN c.lista_subcriterios s " +
            "JOIN s.lista_indicadores i " +
            "WHERE c.visible = true " +
            "GROUP BY c.id_criterio " +
            "ORDER BY c.id_criterio")
    List<Criterio> obtenerCriterios();

        @Query(value = "SELECT c.id_criterio, c.nombre, c.descripcion, c.visible FROM asignacion_indicador ag\n"
                        + "JOIN indicador i ON ag.indicador_id_indicador = i.id_indicador\n"
                        + "JOIN subcriterio s ON s.id_subcriterio = i.subcriterio_id_subcriterio \n"
                        + "JOIN criterio c ON c.id_criterio = s.id_criterio \n"
                        + "WHERE ag.modelo_id_modelo = (SELECT MAX(id_modelo) FROM modelo)\n"
                        + "GROUP BY c.id_criterio, c.nombre ORDER BY c.id_criterio;", nativeQuery = true)
        public List<Criterio> obtenerCriteriosModelo();

        //abajo no se usa
        @Query(value = "SELECT c.id_criterio, c.nombre, c.descripcion, c.visible FROM asignacion_indicador ag\n"
                        + "JOIN indicador i ON ag.indicador_id_indicador = i.id_indicador\n"
                        + "JOIN subcriterio s ON s.id_subcriterio = i.subcriterio_id_subcriterio \n"
                        + "JOIN criterio c ON c.id_criterio = s.id_criterio \n"
                        + "WHERE ag.modelo_id_modelo = :modelo\n"
                        + "GROUP BY c.id_criterio, c.nombre ORDER BY c.id_criterio;", nativeQuery = true)
        public List<Criterio> obtenerCriteriosModeloId(Long modelo);


    @Query("SELECT DISTINCT c " +
            "FROM Criterio c " +
            "JOIN c.lista_subcriterios s " +
            "JOIN s.lista_indicadores i " +
            "JOIN i.lista_asignacion ai " +
            "WHERE ai.modelo.id_modelo = :id_modelo")
    List<Criterio> obtenerCriteriosUltimoModelo(Long id_modelo);

    @Query("SELECT DISTINCT c.id_criterio as idcriterio, c.nombre as nombrecriterio " +
            "FROM Criterio c " +
            "JOIN c.lista_subcriterios s " +
            "JOIN s.lista_indicadores i " +
            "JOIN i.lista_asignacion ai " +
            "WHERE ai.modelo.id_modelo = :id_modelo")
    List<CriteProjection> ObtenerCriterioUltimoModelo(Long id_modelo);

    //el de abajo no se vale
        @Query(value = "SELECT c.* FROM public.criterio c join public.subcriterio s ON s.id_criterio = c.id_criterio join public.indicador i ON i.subcriterio_id_subcriterio = s.id_subcriterio WHERE i.id_indicador=:id_indicador", nativeQuery = true)
        List<Criterio> listarCriterioPorIndicador(Long id_indicador);

        //
        @Query("SELECT c.id_criterio AS id_criterio, c.nombre AS nombre, c.descripcion AS descripcion, c.visible AS visible, " +
                "(SELECT COUNT(s2.id_subcriterio) " +
                "FROM Subcriterio s2 " +
                "JOIN Criterio c2 ON s2.criterio.id_criterio = c2.id_criterio AND c2.visible = true " +
                "WHERE c2.id_criterio = c.id_criterio " +
                "      AND s2.visible = true " +
                "      AND EXISTS ( " +
                "          SELECT 1 " +
                "          FROM Indicador i2 " +
                "          WHERE i2.subcriterio.id_subcriterio = s2.id_subcriterio " +
                "                AND i2.visible = true " +
                "                AND EXISTS ( " +
                "                    SELECT 1 " +
                "                    FROM Asignacion_Indicador ai2 " +
                "                    WHERE ai2.indicador.id_indicador = i2.id_indicador " +
                "                          AND ai2.modelo.id_modelo = :id_modelo " +
                "                ) " +
                "      )) AS cantidadSubcriterios " +
                "FROM Criterio c " +
                "JOIN Subcriterio s ON s.criterio.id_criterio=c.id_criterio AND s.visible=true " +
                "JOIN Indicador i ON i.subcriterio.id_subcriterio=s.id_subcriterio AND i.visible=true " +
                "JOIN Asignacion_Indicador ai ON ai.indicador.id_indicador= i.id_indicador AND ai.visible=true AND ai.modelo.id_modelo= :id_modelo " +
                "WHERE c.visible = true " +
                "GROUP BY id_criterio, nombre, descripcion, visible,cantidadSubcriterios " +
                "ORDER BY c.id_criterio ASC")
        List<CriterioSubcriteriosProjection> obtenerCriteriosConCantidadSubcriterios(Long id_modelo);

        @Query(value = "SELECT c.nombre AS Nomcriterio, " +
                "CAST(SUM(i.peso) AS NUMERIC(10, 2)) AS Ponderacio, " +
                "CAST(SUM(i.porc_utilida_obtenida) AS NUMERIC(10, 3)) AS VlObtenido, " +
                "CAST(SUM(i.peso) - SUM(i.porc_utilida_obtenida) AS NUMERIC(10, 3)) AS Vlobtener " +
                "FROM indicador i " +
                "JOIN asignacion_indicador ai ON i.id_indicador = ai.indicador_id_indicador " +
                "JOIN subcriterio sb ON i.subcriterio_id_subcriterio = sb.id_subcriterio " +
                "JOIN criterio c ON sb.id_criterio = c.id_criterio " +
                "JOIN modelo m ON m.id_modelo = ai.modelo_id_modelo " +
                "WHERE m.visible = true AND i.visible=true AND ai.modelo_id_modelo = ?1 " +
                "GROUP BY c.nombre, c.id_criterio " +
                "ORDER BY c.id_criterio", nativeQuery = true)
        List<ValoresProjection> listarvalores(Long id_modelo);
    @Query(value = "SELECT c.nombre AS Nomcriterio, " +
            "CAST(SUM(i.peso) AS NUMERIC(10, 2)) AS Ponderacio, " +
            "CAST(SUM(i.porc_utilida_obtenida) AS NUMERIC(10, 3)) AS VlObtenido, " +
            "CAST(SUM(i.peso) - SUM(i.porc_utilida_obtenida) AS NUMERIC(10, 3)) AS Vlobtener " +
            "FROM indicador i " +
            "JOIN asignacion_indicador ai ON i.id_indicador = ai.indicador_id_indicador " +
            "JOIN subcriterio sb ON i.subcriterio_id_subcriterio = sb.id_subcriterio " +
            "JOIN criterio c ON sb.id_criterio = c.id_criterio " +
            "JOIN modelo m ON m.id_modelo = ai.modelo_id_modelo " +
            "WHERE m.visible = true AND i.visible=true AND ai.modelo_id_modelo = (SELECT MAX(id_modelo) FROM modelo) " +
            "GROUP BY c.nombre, c.id_criterio " +
            "ORDER BY c.id_criterio", nativeQuery = true)
    List<ValoresProjection> listarvaloresMovil();
    @Query(value = "SELECT c.nombre AS Nomcriterio, " +
            "CAST(SUM(i.peso) AS NUMERIC(10, 2)) AS Ponderacio, " +
            "CAST(SUM(i.porc_utilida_obtenida) AS NUMERIC(10, 3)) AS VlObtenido, " +
            "CAST(SUM(i.peso) - SUM(i.porc_utilida_obtenida) AS NUMERIC(10, 3)) AS Vlobtener " +
            "FROM indicador i " +
            "JOIN asignacion_indicador ai ON i.id_indicador = ai.indicador_id_indicador " +
            "JOIN subcriterio sb ON i.subcriterio_id_subcriterio = sb.id_subcriterio " +
            "JOIN criterio c ON sb.id_criterio = c.id_criterio " +
            "JOIN modelo m ON m.id_modelo = ai.modelo_id_modelo " +
            "WHERE m.visible = true AND i.visible=true AND ai.modelo_id_modelo = ?1  AND c.nombre =?2 " +
            "GROUP BY c.nombre, c.id_criterio " +
            "ORDER BY c.id_criterio", nativeQuery = true)
        List<ValoresProjection> valoresporcriterio(Long id_modelo,String nombre);
        @Query(value = "SELECT cri.nombre AS \"Nomcriterio\",CAST(SUM(i.peso) AS NUMERIC(10, 2)) as \"Ponderacio\", " +
                "CAST(SUM(i.porc_utilida_obtenida) AS NUMERIC(10, 2)) AS \"VlObtenido\", " +
                "CAST(SUM(i.peso) - SUM(i.porc_utilida_obtenida) AS NUMERIC(10, 2)) AS \"Vlobtener\" " +
                "FROM indicador i JOIN subcriterio sub ON sub.id_subcriterio=i.subcriterio_id_subcriterio " +
                "JOIN criterio cri ON cri.id_criterio =sub.id_criterio " +
                "JOIN asignacion_admin aa ON aa.criterio_id_criterio=cri.id_criterio AND aa.visible=true " +
                "AND aa.id_modelo=?1 AND aa.usuario_id=?2 GROUP BY cri.nombre,cri.id_criterio  ORDER BY cri.id_criterio", nativeQuery = true)
        List<ValoresProjection> listarvaladmin(Long id_modelo,Long id);
    @Query(value = "SELECT cri.nombre AS \"Nomcriterio\",CAST(SUM(i.peso) AS NUMERIC(10, 2)) as \"Ponderacio\", " +
            "CAST(SUM(i.porc_utilida_obtenida) AS NUMERIC(10, 2)) AS \"VlObtenido\", " +
            "CAST(SUM(i.peso) - SUM(i.porc_utilida_obtenida) AS NUMERIC(10, 2)) AS \"Vlobtener\" " +
            "FROM indicador i JOIN subcriterio sub ON sub.id_subcriterio=i.subcriterio_id_subcriterio " +
            "JOIN criterio cri ON cri.id_criterio =sub.id_criterio " +
            "JOIN asignacion_admin aa ON aa.criterio_id_criterio=cri.id_criterio AND aa.visible=true " +
            "AND aa.id_modelo=(SELECT MAX(id_modelo) FROM modelo) AND aa.usuario_id=?1 " + // Cambio en la condición
            "GROUP BY cri.nombre,cri.id_criterio ORDER BY cri.id_criterio", nativeQuery = true)
    List<ValoresProjection> listarvaladminmovil(Long id_usuario);

        @Query(value = "SELECT cri.nombre AS Nomcriterio,CAST(SUM(i.peso) AS NUMERIC(10, 2)) as Ponderacio, " +
                "CAST(SUM(i.porc_utilida_obtenida) AS NUMERIC(10, 2)) AS VlObtenido, " +
                "CAST(SUM(i.peso) - SUM(i.porc_utilida_obtenida) AS NUMERIC(10, 2)) AS Vlobtener " +
                "FROM indicador i JOIN subcriterio sub ON sub.id_subcriterio=i.subcriterio_id_subcriterio " +
                "JOIN criterio cri ON cri.id_criterio =sub.id_criterio " +
                "JOIN asignacion_admin aa ON aa.criterio_id_criterio=cri.id_criterio AND aa.visible=true " +
                "AND aa.id_modelo=:id_modelo AND aa.criterio_id_criterio IN " +
                "(SELECT DISTINCT cri.id_criterio FROM asignacion_evidencia ae " +
                "JOIN evidencia e ON ae.evidencia_id_evidencia = e.id_evidencia AND ae.visible=true " +
                "JOIN indicador i ON i.id_indicador = e.indicador_id_indicador " +
                "JOIN subcriterio s ON s.id_subcriterio = i.subcriterio_id_subcriterio " +
                "JOIN criterio cri ON cri.id_criterio = s.id_criterio " +
                "WHERE ae.usuario_id =:id AND ae.id_modelo =:id_modelo ) GROUP BY cri.nombre,cri.id_criterio  ORDER BY cri.id_criterio", nativeQuery = true)
        List<ValoresProjection> listarvalresp(Long id_modelo,Long id);
        @Query(value = "SELECT id_criterio FROM criterio WHERE nombre=:nombre", nativeQuery = true)
        public IdCriterioProjection idcriterio(String nombre);

        //el de abajo no se utiliza
        @Query(value = "SELECT CASE WHEN criterio.nombre IS NOT NULL THEN criterio.nombre ELSE '' END AS criterio, " +
                "CASE WHEN evidencia.descripcion IS NOT NULL THEN evidencia.descripcion ELSE '' END AS evidencia " +
                "FROM usuarios u " +
                "LEFT JOIN usuariorol ur ON u.id = ur.usuario_id " +
                "LEFT JOIN asignacion_admin aa ON aa.usuario_id = u.id AND aa.visible = true AND aa.id_modelo =:id_modelo " +
                "LEFT JOIN criterio criterio ON aa.criterio_id_criterio = criterio.id_criterio " +
                "LEFT JOIN asignacion_evidencia ae ON ae.usuario_id = u.id AND ae.visible = true AND ae.id_modelo =:id_modelo " +
                "LEFT JOIN evidencia evidencia ON ae.evidencia_id_evidencia = evidencia.id_evidencia " +
                "WHERE u.id =:id " +
                "ORDER BY criterio.id_criterio, evidencia.id_evidencia", nativeQuery = true)
         List<CriteProjection> actividadesusuario(Long id, Long id_modelo);


    @Query("SELECT c.id_criterio AS id_criterio, c.nombre AS nombre_criterio, c.descripcion AS descripcion_criterio " +
            "FROM Criterio c " +
            "JOIN Asignacion_Admin aa ON aa.criterio.id_criterio = c.id_criterio " +
            "JOIN aa.usuario u " +
            "WHERE aa.visible = true AND aa.id_modelo.id_modelo = :id_modelo AND u.id = :userId " +
            "ORDER BY c.descripcion ASC")
    List<CriterioAdm> getCriteriosByAdmin(Long id_modelo, Long userId);

    @Query("SELECT cri.id_criterio AS id_criterio, " +
            "       cri.nombre AS criterio, " +
            "       cri.descripcion AS descripcion " +
            "FROM Indicador i " +
            "JOIN i.subcriterio sub " +
            "JOIN sub.criterio cri " +
            "JOIN Asignacion_Admin aa ON aa.criterio.id_criterio = cri.id_criterio AND aa.visible = true " +
            "                          AND aa.id_modelo.id_modelo = :idModelo AND aa.criterio.id_criterio IN " +
            "                          (SELECT DISTINCT cri.id_criterio " +
            "                           FROM Asignacion_Evidencia ae " +
            "                           JOIN ae.evidencia e ON ae.evidencia.id_evidencia = e.id_evidencia AND ae.visible = true " +
            "                           JOIN e.indicador i " +
            "                           JOIN i.subcriterio s " +
            "                           JOIN s.criterio cri " +
            "                           WHERE ae.usuario.id = :userId AND ae.id_modelo = :idModelo) " +
            "GROUP BY cri.id_criterio, cri.nombre, cri.descripcion " +
            "ORDER BY cri.id_criterio")
    List<CriteRespProjection> criterioporresp(Long userId, Long idModelo);


    @Query("SELECT DISTINCT c FROM Criterio c " +
            "JOIN c.lista_subcriterios sc " +
            "JOIN sc.lista_indicadores i " +
            "JOIN Asignacion_Admin aa ON c.id_criterio = aa.criterio.id_criterio " +
            "JOIN aa.id_modelo m " +
            "JOIN aa.usuario u " +
            "WHERE u.id = :usuarioId " +
            "AND m.id_modelo = :modeloId " +
            "AND c.visible = true " +
            "AND sc.visible = true " +
            "AND i.visible = true " +
            "AND aa.visible = true")
    List<Criterio> obtenerCriteriosPorUsuarioYModelo(Long usuarioId, Long modeloId);

    @Query(value = "SELECT DISTINCT ur.usuariorolid AS usuariorol, " +
            "    CASE " +
            "        WHEN ro.rolnombre = 'RESPONSABLE' THEN " +
            "            COALESCE((SELECT cri.nombre " +
            "                    FROM evidencia e " +
            "                    JOIN indicador i ON e.indicador_id_indicador = i.id_indicador " +
            "                    JOIN subcriterio sub ON i.subcriterio_id_subcriterio = sub.id_subcriterio " +
            "                    JOIN criterio cri ON sub.id_criterio = cri.id_criterio " +
            "                    JOIN asignacion_evidencia ae ON ae.evidencia_id_evidencia = e.id_evidencia " +
            "                    WHERE ae.usuario_id = ur.usuario_id AND ae.visible = true AND ae.id_modelo = :id_modelo " +
            "                    GROUP BY ur.usuariorolid, cri.nombre " +
            "                    LIMIT 1 " +
            "                ) " +
            "                , 'SIN CRITERIOS' " +
            "            ) " +
            "        ELSE COALESCE(criterio.nombre, 'SIN CRITERIOS') " +
            "    END AS criterionombre " +
            "FROM usuariorol ur " +
            "JOIN usuarios u ON ur.usuario_id = u.id " +
            "JOIN roles ro ON ur.rol_rolid = ro.rolid " +
            "LEFT JOIN asignacion_admin aa ON aa.usuario_id = u.id AND aa.id_modelo = :id_modelo AND aa.visible = true " +
            "LEFT JOIN criterio criterio ON aa.criterio_id_criterio = criterio.id_criterio " +
            "WHERE ur.visible = true AND ur.usuariorolid = :id_usuariorol " +
            "AND ((ro.rolnombre='ADMIN' AND ur.usuariorolid = :id_usuariorol) " +
            "    OR (ro.rolnombre='SUPERADMIN' AND ur.usuariorolid = :id_usuariorol) " +
            "    OR (ro.rolnombre='RESPONSABLE' AND ur.usuariorolid = :id_usuariorol) " +
            ") " +
            "GROUP BY ur.usuariorolid, ro.rolnombre, criterio.nombre", nativeQuery = true)
    List<CriteProjection> listarcriusers(Long id_usuariorol, Long id_modelo);

    @Query("SELECT c.id_criterio AS id_criterio, " +
            "       c.nombre AS nombre_criterio, " +
            "       c.descripcion AS descripcion_criterio " +
            "FROM Criterio c " +
            "JOIN Asignacion_Admin aa ON aa.criterio.id_criterio = c.id_criterio " +
            "JOIN aa.id_modelo m " +
            "WHERE aa.visible = true " +
            "  AND m.id_modelo = (SELECT MAX(m2.id_modelo) FROM Modelo m2) " +
            "  AND aa.usuario.id = ?1")
    List<CriterioAdm> criteriosadmultimomodelo(Long userId);

    @Query("SELECT cri.id_criterio AS id_criterio, " +
            "cri.nombre AS nombre, " +
            "COALESCE(SUM(ci.porc_utilida_obtenida), 0) AS total, " +
            "COALESCE(SUM(i.peso) - SUM(ci.porc_utilida_obtenida),0) AS faltante " +
            "FROM Indicador i " +
            "JOIN Asignacion_Indicador ai ON ai.indicador.id_indicador = i.id_indicador AND ai.visible=true " +
            "LEFT JOIN Calificar_Indicador ci ON i.id_indicador = ci.indicador.id_indicador AND ci.id_modelo = :id_modelo " +
            "JOIN i.subcriterio sub " +
            "JOIN sub.criterio cri " +
            "JOIN Modelo m ON ai.modelo.id_modelo = m.id_modelo " +
            "WHERE m.visible =true AND i.visible=true AND ai.modelo.id_modelo =:id_modelo " +
            "GROUP BY cri.nombre, cri.id_criterio " +
            "ORDER BY cri.id_criterio")
    List<CriterioPorcProjection> criteriosporModelo(Long id_modelo);

}
