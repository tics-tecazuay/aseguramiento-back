package com.sistema.examenes.services;

import com.sistema.examenes.entity.Formula;
import com.sistema.examenes.projection.FormulaProjection;

import java.util.List;

public interface Formula_Service extends GenericService<Formula, Long>{
    public List<FormulaProjection> listarFormulas(Long id_modelo) ;

}
