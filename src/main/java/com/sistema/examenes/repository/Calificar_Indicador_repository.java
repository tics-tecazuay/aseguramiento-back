package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Calificar_Indicador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface Calificar_Indicador_repository extends JpaRepository<Calificar_Indicador, Long> {
    @Query("SELECT ci FROM Calificar_Indicador ci WHERE ci.indicador.id_indicador = :id_indicador AND ci.id_modelo =:id_modelo")
    Calificar_Indicador obtenerCalificacionPorIdIndicadorIdModelo(@Param("id_indicador") Long id_indicador,@Param("id_modelo") Long id_modelo);

}
