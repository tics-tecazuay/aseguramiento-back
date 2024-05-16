package com.sistema.examenes.services;

import com.sistema.examenes.entity.Usuario;
import com.sistema.examenes.projection.CriteProjection;
import com.sistema.examenes.projection.ResponsableProjection;
import com.sistema.examenes.projection.UsuariosProjection;
import com.sistema.examenes.repository.Asignacion_Responsable_repository;
import com.sistema.examenes.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl extends GenericServiceImpl<Usuario, Long> implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private Asignacion_Responsable_repository arr;

    @Override
    public CrudRepository<Usuario, Long> getDao() {
        return usuarioRepository;
    }

    @Override
    public Usuario obtenerUsuario(String username) {
        return usuarioRepository.findByUsernameAndVisibleTrue(username);
    }
    @Override
    public Usuario findAllByUsername(String username) {
        return usuarioRepository.findAllByUsername(username);
    }

    @Override
    public List<Usuario> responsables() {
        return usuarioRepository.responsables();
    }

    /*@Override
    public List<ResponsableProjection> responsablesAdmin(Long id_modelo) {
        return usuarioRepository.responsablesGeneral(id_modelo);
    }*/

    @Override
    public Usuario obtenerId(String username) {
        return usuarioRepository.buscarId(username);
    }

    @Override
    public List<Usuario> listaAdminDatos() {
        return usuarioRepository.listaAdminDatos();
    }
    @Override
    public List<Usuario> listaSOLORESPONSABLES() {
        return usuarioRepository.listaSOLORESPONSABLES();
    }

    @Override
    public List<ResponsableProjection> responsablesAdmin(Long idAdministrador) {
        return usuarioRepository.responsablesAdmin(idAdministrador);
    }

    @Override
    public List<UsuariosProjection> listarusercrite(Long id_modelo) {
        return usuarioRepository.listarusercrite(id_modelo);
    }
}