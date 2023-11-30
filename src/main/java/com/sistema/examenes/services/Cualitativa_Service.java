package com.sistema.examenes.services;

import com.sistema.examenes.entity.Cualitativa;

import java.util.List;

public interface Cualitativa_Service extends GenericService<Cualitativa, Long>{
    public List<Cualitativa> listar() ;

}
