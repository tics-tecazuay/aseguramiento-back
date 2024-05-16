package com.sistema.examenes.services;

import com.sistema.examenes.entity.Usuario;
import com.sistema.examenes.projection.CriteProjection;
import com.sistema.examenes.projection.ResponsableProjection;
import com.sistema.examenes.projection.UsuariosProjection;

import java.util.List;

public interface UsuarioService extends GenericService<Usuario, Long> {
    public Usuario obtenerUsuario(String username);

    public Usuario obtenerId(String username);

    public Usuario findAllByUsername(String username);

    public List<Usuario> responsables();
    //public List<ResponsableProjection> responsablesAdmin(Long id_modelo);
    public List<Usuario> listaAdminDatos();
    public List<Usuario>listaSOLORESPONSABLES();
    public List<ResponsableProjection> responsablesAdmin(Long idAdministrador);
    List<UsuariosProjection> listarusercrite(Long id_modelo);
}
