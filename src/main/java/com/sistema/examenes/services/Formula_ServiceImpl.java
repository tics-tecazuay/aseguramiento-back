package com.sistema.examenes.services;

import com.sistema.examenes.entity.Actividad;
import com.sistema.examenes.entity.Formula;
import com.sistema.examenes.repository.Formula_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Formula_ServiceImpl extends GenericServiceImpl<Formula, Long> implements Formula_Service {
    @Autowired
    private Formula_repository repository;
    @Override
    public CrudRepository<Formula, Long > getDao() {
        return repository;
    }

    @Override
    public List<Formula> listar() {
        return repository.listarFormula();
    }
}