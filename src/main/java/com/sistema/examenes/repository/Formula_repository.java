package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Criterio;
import com.sistema.examenes.entity.Formula;
import com.sistema.examenes.projection.FormulaProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Formula_repository extends JpaRepository<Formula, Long> {
    @Query(value = "SELECT cr.nombre AS criterio, s.nombre AS subcriterio, i.nombre AS indicador, f.id_formula, f.formula, f.descripcion " +
            "FROM encabezado_evaluar ee " +
            "JOIN formula f ON ee.formula_id_formula = f.id_formula " +
            "JOIN indicador i ON ee.indicador_id_indicador = i.id_indicador " +
            "JOIN asignacion_indicador ai ON i.id_indicador = ai.indicador_id_indicador AND ai.modelo_id_modelo = :id_modelo " +
            "JOIN subcriterio s ON i.subcriterio_id_subcriterio = s.id_subcriterio " +
            "JOIN criterio cr ON s.id_criterio = cr.id_criterio " +
            "WHERE f.visible = true AND ee.visible = true AND i.visible = true", nativeQuery = true)
    List<FormulaProjection> listarFormulas(Long id_modelo);
}
