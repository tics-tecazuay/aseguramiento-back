package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Criterio;
import com.sistema.examenes.entity.Evaluar_Cuantitativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Evaluar_Cuantitativa_repository extends JpaRepository<Evaluar_Cuantitativa, Long> {
    @Query("SELECT ec FROM Evaluar_Cuantitativa ec WHERE ec.visible = true")
    List<Evaluar_Cuantitativa> listarEvaluarCuantitativa();

    /*
     * SELECT ec.*
     * FROM public.indicador i JOIN public.encabezado_evaluar ev ON
     * ev.indicador_id_indicador = i.id_indicador JOIN public.evaluar_cuantitativa
     * ec ON ec.encabezado_evaluar_id_encabezado_evaluar = ev.id_encabezado_evaluar
     * WHERE i.id_indicador=2
     */
    @Query("SELECT ec FROM Encabezado_Evaluar ev " +
            "JOIN ev.lista_cuantitativa ec " +
            "WHERE ev.indicador.id_indicador = :id_indicador " +
            "AND ev.visible = true " +
            "AND ec.visible = true")
    List<Evaluar_Cuantitativa> listarEvaluarCuantitativaPorIndicador(Long id_indicador);
}
