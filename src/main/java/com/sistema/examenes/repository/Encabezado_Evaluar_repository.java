package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Criterio;
import com.sistema.examenes.entity.Encabezado_Evaluar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Encabezado_Evaluar_repository extends JpaRepository<Encabezado_Evaluar, Long> {
    @Query("SELECT e FROM Encabezado_Evaluar e WHERE e.visible = true")
    List<Encabezado_Evaluar> listarEncabezadoEvaluar();

    @Query(value = "SELECT * FROM encabezado_evaluar WHERE formula_id_formula = ?1", nativeQuery = true)
    Encabezado_Evaluar findByIdFormula(Long formulaId);
}
