package com.sistema.examenes.repository;


import com.sistema.examenes.entity.Criterio;
import com.sistema.examenes.entity.Evaluar_Cualitativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Evaluar_Cualitativa_repository extends JpaRepository<Evaluar_Cualitativa, Long> {
    @Query("SELECT ec FROM Evaluar_Cualitativa ec WHERE ec.visible = true")
    List<Evaluar_Cualitativa> listarEvaluarCualiativa();

    @Query("SELECT ec FROM Evaluar_Cualitativa ec " +
            "JOIN Indicador i ON ec.indicador.id_indicador = i.id_indicador AND i.visible = true " +
            "JOIN Evidencia ev ON ev.indicador.id_indicador = i.id_indicador AND ev.visible = true " +
            "WHERE ev.id_evidencia =: id_evidencia")
    List<Evaluar_Cualitativa> findByIdEvidencia(Long id_evidencia);
}
