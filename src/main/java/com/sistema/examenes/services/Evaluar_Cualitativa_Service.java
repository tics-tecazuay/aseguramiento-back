package com.sistema.examenes.services;

import com.sistema.examenes.entity.Evaluar_Cualitativa;

import java.util.List;

public interface Evaluar_Cualitativa_Service extends GenericService<Evaluar_Cualitativa, Long>{
    public List<Evaluar_Cualitativa> listar() ;

}
