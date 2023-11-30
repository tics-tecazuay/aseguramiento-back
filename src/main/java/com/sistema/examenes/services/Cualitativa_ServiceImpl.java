package com.sistema.examenes.services;

import com.sistema.examenes.entity.Actividad;
import com.sistema.examenes.entity.Cualitativa;
import com.sistema.examenes.repository.Cualitativa_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Cualitativa_ServiceImpl extends GenericServiceImpl<Cualitativa, Long> implements Cualitativa_Service {
    @Autowired
    private Cualitativa_repository repository;
    @Override
    public CrudRepository<Cualitativa, Long > getDao() {
        return repository;
    }
    @Override
    public List<Cualitativa> listar() {
        return repository.listarCualitativa();
    }
}
