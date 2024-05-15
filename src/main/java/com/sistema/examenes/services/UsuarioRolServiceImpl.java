package com.sistema.examenes.services;


import com.sistema.examenes.entity.UsuarioRol;
import com.sistema.examenes.repository.UsuarioRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioRolServiceImpl extends GenericServiceImpl<UsuarioRol, Long> implements UsuarioRolService{
    @Autowired
    private UsuarioRolRepository usuarioRolRepository;
    @Override
    public CrudRepository<UsuarioRol, Long> getDao() {
        return usuarioRolRepository;
    }

    @Override
    public List<UsuarioRol> listarv() {
        return usuarioRolRepository.listarv();
    }

    @Override
    public List<UsuarioRol> findByUsuarios_UsuarioId(Long usuarioId) {
        return usuarioRolRepository.findByUsuarios_Usuario_Id(usuarioId);
    }
    @Override
    public UsuarioRol findByUsuarioAndRol(Long usuarioId, Long rolId) {
        return usuarioRolRepository.findByUsuarioAndRol(usuarioId, rolId);
    }
}
