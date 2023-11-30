package com.sistema.examenes.services;

import com.sistema.examenes.entity.Actividad;
import com.sistema.examenes.entity.Reporte;
import com.sistema.examenes.repository.Reporte_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Reporte_ServiceImpl extends GenericServiceImpl<Reporte, Long> implements Reporte_Service {
    @Autowired
    private Reporte_repository repository;
    @Override
    public CrudRepository<Reporte, Long > getDao() {
        return repository;
    }

    @Override
    public List<Reporte> listar() {
        return repository.listarReporte();
    }
}
