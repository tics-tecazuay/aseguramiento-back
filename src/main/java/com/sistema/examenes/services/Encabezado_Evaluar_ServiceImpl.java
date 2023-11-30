package com.sistema.examenes.services;

import com.sistema.examenes.entity.Actividad;
import com.sistema.examenes.entity.Encabezado_Evaluar;
import com.sistema.examenes.repository.Encabezado_Evaluar_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Encabezado_Evaluar_ServiceImpl extends GenericServiceImpl<Encabezado_Evaluar, Long> implements Encabezado_Evaluar_Service {
    @Autowired
    private Encabezado_Evaluar_repository repository;
    @Override
    public CrudRepository<Encabezado_Evaluar, Long > getDao() {
        return repository;
    }

    @Override
    public List<Encabezado_Evaluar> listar() {
        return repository.listarEncabezadoEvaluar();
    }
}