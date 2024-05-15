package com.sistema.examenes.services;

import com.sistema.examenes.entity.Calificar_Indicador;
import com.sistema.examenes.entity.Indicador;
import com.sistema.examenes.projection.*;
import com.sistema.examenes.repository.Calificar_Indicador_repository;
import com.sistema.examenes.repository.Indicador_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Calificar_Indicador_ServiceImpl extends GenericServiceImpl<Calificar_Indicador, Long> implements Calificar_Indicador_Service {
    @Autowired
    private Calificar_Indicador_repository repository;

    @Override
    public CrudRepository<Calificar_Indicador, Long> getDao() {
        return repository;
    }

    @Override
    public Calificar_Indicador obtenerCalificacionPorIdIndicadorIdModelo(Long id_indicador, Long id_modelo) {
        return repository.obtenerCalificacionPorIdIndicadorIdModelo(id_indicador, id_modelo);
    }
}
