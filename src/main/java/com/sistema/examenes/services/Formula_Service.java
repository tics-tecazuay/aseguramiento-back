package com.sistema.examenes.services;

import com.sistema.examenes.entity.Formula;

import java.util.List;

public interface Formula_Service extends GenericService<Formula, Long>{
    public List<Formula> listar() ;

}
