package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Criterio;
import com.sistema.examenes.entity.Ponderacion;
import com.sistema.examenes.projection.PonderacionProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface Ponderacion_repository extends JpaRepository<Ponderacion, Long> {
    @Query("SELECT p FROM Ponderacion p " +
            "JOIN FETCH p.modelo m " +
            "JOIN FETCH p.indicador i " +
            "JOIN FETCH i.subcriterio s " +
            "JOIN FETCH s.criterio cri " +
            "WHERE p.visible = true " +
            "ORDER BY cri.id_criterio")
    List<Ponderacion> listarPonderacion();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM ponderacion WHERE contador =?1 AND fecha=TO_DATE(?2, 'YYYY-MM-DD')", nativeQuery = true)
    void eliminarPonderacion(Long contador, String fecha);

   /* @Query(value = "SELECT p.contador, p.fecha as fechapo " +
            "FROM Ponderacion p " +
            "JOIN p.modelo m " +
            "WHERE m.id_modelo =:id_modelo " +
            "GROUP BY p.contador, p.fecha " +
            "ORDER BY p.contador")
    List<Ponderacion> listarPonderacionPorModelo(Long id_modelo);*/

    @Query("SELECT p.contador AS contador, " +
            "p.fecha AS fechapo " +
            "FROM Ponderacion p " +
            "JOIN p.modelo m " +
            "WHERE m.id_modelo = :id_modelo " +
            "GROUP BY p.contador, p.fecha " +
            "ORDER BY p.fecha DESC")
    List<PonderacionProjection> listarPonderacionModelo(Long id_modelo);

    @Query("SELECT p " +
            "FROM Ponderacion p " +
            "JOIN FETCH p.modelo m " +
            "WHERE p.fecha = TO_DATE(:fecha, 'YYYY-MM-DD')")
    List<Ponderacion> listarPorFecha(@Param("fecha") String fecha);


    @Query("SELECT p.id_ponderacion as idponderacion, i.id_indicador as idindicador, c.nombre as nombrecriterio, s.nombre as nombresubcriterio, i.nombre as nombreindicador, " +
            "p.valor_obtenido as valorobtenido, p.peso as peso, " +
            "p.porc_obtenido as porcentajeobtenido, p.porc_utilida_obtenida as porcentajeutilidad, " +
            "p.fecha as fechapo, p.contador as contador " +
            "FROM Ponderacion p " +
            "JOIN p.modelo m " +
            "JOIN p.indicador i " +
            "JOIN i.subcriterio s " +
            "JOIN s.criterio c " +
            "WHERE p.fecha = TO_DATE(?1, 'YYYY-MM-DD') AND p.contador = ?2 ")
    List<PonderacionProjection> listarPonderacionPorFecha(String fecha, Long contador);

    @Query("SELECT p.contador AS contador FROM Ponderacion p WHERE p.modelo.id_modelo = :idModelo ORDER BY p.contador DESC")
    List<PonderacionProjection> idmax(@Param("idModelo") Long idModelo, Pageable pageable);
}
