package com.sistema.examenes.services;

import com.sistema.examenes.entity.Archivo_s;
import com.sistema.examenes.entity.Evidencia;
import com.sistema.examenes.projection.ArchivoAdmSupProjection;
import com.sistema.examenes.projection.ArchivoProjection;
import com.sistema.examenes.projection.ArchivoResProjection;
import com.sistema.examenes.repository.Archivo_repository;
import com.sistema.examenes.repository.Evidencia_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Archivo_ServiceImpl extends GenericServiceImpl<Archivo_s, Long> implements Archivo_Service {
    @Autowired
    private Archivo_repository repository;
    @Override
    public CrudRepository<Archivo_s, Long > getDao() {
        return repository;
    }

    @Override
    public List<Archivo_s> listar() {
        return repository.findByVisibleTrue();
    }

    @Override
    public List<ArchivoResProjection> listararchivouser(String username, Long id_asignacion_evi, Long idModel) {
        return repository.listararchivouser(username,id_asignacion_evi, idModel);
    }

    @Override
    public List<ArchivoAdmSupProjection> listararchivoActividad(Long idActividad, Long idModel) {
        return repository.listararchivoActividad(idActividad, idModel);
    }

    @Override
    public List<ArchivoProjection> listararchi() {
        return repository.listararchi();
    }

    @Override
    public List<Archivo_s> archivoporindicador(Long id_criterio, Long id_modelo, Long id_indicador) {
        return repository.archivoporindicador(id_criterio,id_modelo,id_indicador);
    }


}
