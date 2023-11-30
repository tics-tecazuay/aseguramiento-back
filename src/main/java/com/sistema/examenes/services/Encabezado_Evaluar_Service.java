package com.sistema.examenes.services;

import com.sistema.examenes.entity.Encabezado_Evaluar;

import java.util.List;

public interface Encabezado_Evaluar_Service extends GenericService<Encabezado_Evaluar, Long>{
    public List<Encabezado_Evaluar> listar() ;

}
