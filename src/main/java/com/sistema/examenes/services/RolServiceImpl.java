package com.sistema.examenes.services;

import com.sistema.examenes.entity.Rol;
import com.sistema.examenes.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class RolServiceImpl extends GenericServiceImpl<Rol, Long> implements RolService{
    @Autowired
    private RolRepository rolRepository;
    @Override
    public CrudRepository<Rol, Long> getDao() {
        return rolRepository;
    }
}
