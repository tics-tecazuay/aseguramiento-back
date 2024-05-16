package com.sistema.examenes.services;

import com.sistema.examenes.entity.Rol;
import com.sistema.examenes.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImpl extends GenericServiceImpl<Rol, Long> implements RolService{
    @Autowired
    private RolRepository rolRepository;
    @Override
    public CrudRepository<Rol, Long> getDao() {
        return rolRepository;
    }

    @Override
    public List<Rol> listaRolesPorUsername(String username) {
        return rolRepository.findRolesByUsername(username);
    }
}
