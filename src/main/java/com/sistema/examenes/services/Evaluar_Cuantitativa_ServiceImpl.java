package com.sistema.examenes.services;

import com.sistema.examenes.entity.Actividad;
import com.sistema.examenes.entity.Evaluar_Cuantitativa;
import com.sistema.examenes.repository.Evaluar_Cuantitativa_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Evaluar_Cuantitativa_ServiceImpl extends GenericServiceImpl<Evaluar_Cuantitativa, Long>
        implements Evaluar_Cuantitativa_Service {
    @Autowired
    private Evaluar_Cuantitativa_repository repository;

    @Override
    public CrudRepository<Evaluar_Cuantitativa, Long> getDao() {
        return repository;
    }

    @Override
    public List<Evaluar_Cuantitativa> listar() {
        return repository.listarEvaluarCuantitativa();
    }

    @Override
    public List<Evaluar_Cuantitativa> listarEvaluarCuantitativaPorIndicador(Long id_indicador) {
        return repository.listarEvaluarCuantitativaPorIndicador(id_indicador);
    }
}
