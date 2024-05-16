package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Archivo_s;
import com.sistema.examenes.projection.ArchivoAdmSupProjection;
import com.sistema.examenes.projection.ArchivoProjection;
import com.sistema.examenes.projection.ArchivoResProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Archivo_repository extends JpaRepository<Archivo_s, Long> {
   /*@Query("SELECT a FROM Archivo_s a WHERE a.visible = true")
    List<Archivo_s> listararchivo();*/
    List<Archivo_s> findByVisibleTrue();
   //List<ArchivoResProjection> findByActividadUsuarioUsernameAndVisibleTrueAndActividad_Id_asignacion_evidencia(String username, Long id_asignacion_evi);
    @Query("SELECT ar.id_archivo AS id_archivo," +
            "ar.enlace AS enlace, " +
            "ar.nombre AS nombre, " +
            "ar.descripcion AS descripcion, " +
            "ar.comentario AS comentario " +
            "FROM Archivo_s ar " +
            "JOIN ar.actividad asig " +
            "JOIN asig.usuario u " +
            "WHERE u.username = :username AND ar.visible = true AND asig.id_asignacion_evidencia= :id_asignacion_evi " +
            "AND ar.id_modelo = :idModel ")
    List<ArchivoResProjection> listararchivouser(@Param("username") String username, @Param("id_asignacion_evi") Long id_asignacion_evi,  @Param("idModel") Long idModel);

    @Query("SELECT ar.id_archivo AS id_archivo, ar.enlace AS enlace, ar.nombre AS nombre, ar.descripcion AS descripcion, ar.comentario AS comentario " +
            "FROM Archivo_s ar WHERE ar.visible = true AND ar.actividad.id_asignacion_evidencia = :idActividad AND ar.id_modelo = :idModel ")
    List<ArchivoAdmSupProjection> listararchivoActividad(@Param("idActividad") Long idActividad, @Param("idModel") Long idModel);

    @Query(value = "SELECT u.id AS idper, " +
            "per.primer_nombre || ' ' || per.primer_apellido AS resp, " +
            "COALESCE(per.correo, 'Sin correo') AS correo, " +
            "ar.nombre AS archiv, " +
            "e.descripcion AS activid, " +
            "ac.fecha_inicio AS ini, " +
            "ac.fecha_fin AS finish, " +
            "ar.enlace AS enlac " +
            "FROM archivo ar " +
            "JOIN asignacion_evidencia ac ON ar.id_asignacion_evidencia = ac.id_asignacion_evidencia AND ar.visible = true " +
            "JOIN evidencia e ON e.id_evidencia = ac.evidencia_id_evidencia " +
            "JOIN usuarios u ON u.id = ac.usuario_id " +
            "JOIN persona per ON per.id_persona = u.persona_id_persona " +
            "JOIN indicador i ON i.id_indicador = e.indicador_id_indicador " +
            "JOIN asignacion_indicador ai ON ai.indicador_id_indicador = i.id_indicador AND ai.visible = true " +
            "JOIN modelo mo ON mo.id_modelo = ai.modelo_id_modelo " +
            "WHERE mo.id_modelo = (SELECT MAX(id_modelo) FROM modelo) " +
            "GROUP BY idper, resp, correo, archiv, activid, ini, finish, enlac",
            nativeQuery = true)
    List<ArchivoProjection> listararchi();

    @Query(value = "SELECT ar.* FROM archivo ar " +
            "JOIN asignacion_evidencia ac ON ar.id_asignacion_evidencia = ac.id_asignacion_evidencia AND ar.visible = true " +
            "JOIN evidencia ev ON ev.id_evidencia = ac.evidencia_id_evidencia AND ev.visible = true " +
            "JOIN indicador i ON ev.indicador_id_indicador = i.id_indicador AND i.visible = true " +
            "JOIN asignacion_indicador a ON a.indicador_id_indicador = i.id_indicador AND a.visible = true " +
            "JOIN modelo m ON a.modelo_id_modelo = m.id_modelo " +
            "JOIN subcriterio s ON s.id_subcriterio = i.subcriterio_id_subcriterio AND s.visible = true " +
            "JOIN criterio c ON c.id_criterio = s.id_criterio AND c.visible = true " +
            "WHERE c.id_criterio = :id_criterio " +
            "AND m.id_modelo = :id_modelo " +
            "AND ev.indicador_id_indicador = :id_indicador " +
            "ORDER BY i.id_indicador", nativeQuery = true)
    List<Archivo_s> archivoporindicador(Long id_criterio,Long id_modelo,Long id_indicador);

    boolean existsByNombre(String nombre);
}
