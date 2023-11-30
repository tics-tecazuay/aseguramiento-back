package com.sistema.examenes.services;

import com.sistema.examenes.entity.Actividad;
import com.sistema.examenes.entity.Evaluar_Cualitativa;
import com.sistema.examenes.repository.Evaluar_Cualitativa_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Evaluar_Cualitativa_ServiceImpl extends GenericServiceImpl<Evaluar_Cualitativa, Long> implements Evaluar_Cualitativa_Service {
    @Autowired
    private Evaluar_Cualitativa_repository repository;
    @Override
    public CrudRepository<Evaluar_Cualitativa, Long > getDao() {
        return repository;
    }

    @Override
    public List<Evaluar_Cualitativa> listar() {
        return repository.listarEvaluarCualiativa();
    }
}