package com.sistema.examenes.services;

import com.sistema.examenes.entity.UsuarioRol;
import java.util.List;

public interface UsuarioRolService extends GenericService<UsuarioRol, Long>{

    public List<UsuarioRol> listarv();
    public List<UsuarioRol> findByUsuarios_UsuarioId(Long usuarioId);
    UsuarioRol findByUsuarioAndRol(Long usuarioId, Long rolId);

}
