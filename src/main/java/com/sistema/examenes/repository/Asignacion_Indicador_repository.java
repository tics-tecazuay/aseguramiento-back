package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Asignacion_Indicador;
import com.sistema.examenes.entity.Modelo;

import com.sistema.examenes.projection.AsignaIndicadorProjection;
import com.sistema.examenes.projection.AsignacionIndicadorProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface Asignacion_Indicador_repository extends JpaRepository<Asignacion_Indicador, Long> {

    @Query("SELECT ai FROM Asignacion_Indicador ai WHERE ai.visible = true")
    List<Asignacion_Indicador> listarAsignacionIndicador();

    // metodo para lista asignacion_indicador por objeto modelo
    @Query( "Select i.id_indicador AS id_indicador, c.nombre AS criterio, s.nombre AS subcriterio,  " +
            "       i.nombre AS indicador, " +
            "       i.peso AS peso, i.porc_obtenido AS porc_obtenido, " +
            "       i.porc_utilida_obtenida AS porc_utilida_obtenida, " +
            "       i.valor_obtenido  AS valor_obtenido " +
            "FROM Asignacion_Indicador ai " +
            "JOIN ai.indicador i " +
            "JOIN i.subcriterio s " +
            "JOIN s.criterio c " +
            "WHERE ai.modelo.id_modelo=:id_modelo AND ai.visible= true ")
    List<AsignacionIndicadorProjection> listarAsignacionIndicador(Long id_modelo);


    @Query("SELECT ai FROM Asignacion_Indicador ai WHERE ai.modelo.id_modelo = :id_modelo")
    List<Asignacion_Indicador> listarAsignacion(Long id_modelo);
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM asignacion_indicador WHERE modelo_id_modelo=:id AND indicador_id_indicador=:id_asig", nativeQuery = true)
    void eliminarasignacion(Long id,Long id_asig);
    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM asignacion_indicador WHERE indicador_id_indicador=:id_indi AND modelo_id_modelo=:id_modelo AND visible=true;", nativeQuery = true)
    Boolean existeIndicador(Long id_indi,Long id_modelo);
    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END " +
            "FROM asignacion_admin aa " +
            "JOIN criterio cri ON cri.id_criterio=aa.criterio_id_criterio AND aa.visible=true " +
            "JOIN subcriterio s ON s.id_criterio=cri.id_criterio " +
            "JOIN indicador i ON s.id_subcriterio= i.subcriterio_id_subcriterio " +
            "JOIN asignacion_indicador ai ON ai.indicador_id_indicador=i.id_indicador " +
            "WHERE aa.id_modelo=:id_modelo AND ai.indicador_id_indicador=:id_indi ;", nativeQuery = true)
    Boolean existeCriterio(Long id_modelo, Long id_indi);
    @Query(value = "SELECT cri.nombre FROM criterio cri " +
            "JOIN subcriterio s ON s.id_criterio = cri.id_criterio " +
            "JOIN indicador i ON i.subcriterio_id_subcriterio=s.id_subcriterio " +
            "JOIN asignacion_indicador ai ON ai.indicador_id_indicador=i.id_indicador " +
            "WHERE ai.indicador_id_indicador=:id_asig", nativeQuery = true)
    String nombreCriterio(@Param("id_asig") Long id_asig);

    @Query(value = "SELECT COUNT(ai.indicador_id_indicador) FROM asignacion_indicador ai " +
            "JOIN indicador i ON i.id_indicador=ai.indicador_id_indicador " +
            "JOIN subcriterio s ON s.id_subcriterio=i.subcriterio_id_subcriterio " +
            "JOIN criterio cri ON cri.id_criterio=s.id_criterio " +
            "WHERE ai.modelo_id_modelo=:id_modelo AND cri.nombre=:nombre", nativeQuery = true)
    Integer contar(Long id_modelo, String nombre);

    @Query("SELECT DISTINCT cri.id_criterio AS idcriterio, cri.nombre AS nombrecriterio, " +
            "cri.descripcion AS descripcio FROM Asignacion_Indicador ai " +
            "JOIN ai.indicador i " +
            "JOIN i.subcriterio s " +
            "JOIN s.criterio cri " +
            "WHERE ai.modelo.id_modelo = :id_modelo " +
            "ORDER BY cri.id_criterio ASC")
    List<AsignaIndicadorProjection> listarAsignaIndicador(Long id_modelo);
}
