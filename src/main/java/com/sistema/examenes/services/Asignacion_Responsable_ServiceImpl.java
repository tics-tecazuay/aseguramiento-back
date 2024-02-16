package com.sistema.examenes.services;

import com.sistema.examenes.entity.Asignacion_Responsable;
import com.sistema.examenes.projection.ResponsableProjection;
import com.sistema.examenes.repository.Asignacion_Responsable_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Asignacion_Responsable_ServiceImpl extends GenericServiceImpl<Asignacion_Responsable, Long>
        implements Asignacion_Responsable_Service {
    @Autowired
    private Asignacion_Responsable_repository repository;

    @Override
    public CrudRepository<Asignacion_Responsable, Long> getDao() {
        return repository;
    }


    @Override
    public List<ResponsableProjection> listadeResponsablesByAdmin(Long idAdministrador) {
        return repository.listadeResponsablesByAdmin(idAdministrador);
    }

    @Override
    public Asignacion_Responsable asignacionByIdUsuarioResponsable(Long id_usuarioResponsable) {
        return repository.asignacionByIdUsuarioResponsable(id_usuarioResponsable);
    }

    @Override
    public Asignacion_Responsable asignacion_existente(Long id_usuarioAdmin, Long id_usuarioResponsable) {
        return repository.asignacion_existente(id_usuarioAdmin,id_usuarioResponsable);
    }

    @Override
    public List<Asignacion_Responsable> Asignacion_ResponsablesByAdmin(Long idAdministrador) {
        return repository.Asignacion_ResponsablesByAdmin(idAdministrador);
    }


}
