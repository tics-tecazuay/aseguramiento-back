package com.sistema.examenes.services;

import com.sistema.examenes.entity.Reporte;

import java.util.List;

public interface Reporte_Service extends GenericService<Reporte, Long>{
    public List<Reporte> listar() ;

}
