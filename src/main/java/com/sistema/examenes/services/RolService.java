package com.sistema.examenes.services;

import com.sistema.examenes.entity.Rol;

import java.util.List;

public interface RolService extends GenericService<Rol, Long>{
    List<Rol> listaRolesPorUsername(String username);
}
