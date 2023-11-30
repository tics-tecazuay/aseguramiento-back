package com.sistema.examenes.services;

import com.sistema.examenes.entity.Actividad;
import com.sistema.examenes.entity.Cuantitativa;
import com.sistema.examenes.repository.Cuantitativa_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Cuantitativa_ServiceImpl extends GenericServiceImpl<Cuantitativa, Long> implements Cuantitativa_Service {
    @Autowired
    private Cuantitativa_repository repository;
    @Override
    public CrudRepository<Cuantitativa, Long > getDao() {
        return repository;
    }

    @Override
    public List<Cuantitativa> listar() {
        return repository.listarCuantitativa();
    }




}