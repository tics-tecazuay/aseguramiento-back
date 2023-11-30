package com.sistema.examenes.services;

import com.sistema.examenes.entity.Cuantitativa;

import java.util.List;

public interface Cuantitativa_Service extends GenericService<Cuantitativa, Long>{
    public List<Cuantitativa> listar() ;

}
