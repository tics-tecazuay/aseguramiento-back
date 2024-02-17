package com.sistema.examenes.controller;

import com.sistema.examenes.entity.*;
import com.sistema.examenes.entity.dto.SeguimientoUsuarioDTO;
import com.sistema.examenes.projection.ResponsableProjection;
import com.sistema.examenes.repository.SeguimientoUsuario_repository;
import com.sistema.examenes.repository.UsuarioRepository;
import com.sistema.examenes.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/aseguramiento/usuarios")
@CrossOrigin({"https://apps.tecazuay.edu.ec","http://localhost:4200/"})
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private Asignacion_Evidencia_Service asigeviservice;
    @Autowired
    private Asignacion_Admin_Service asigadmservice;
    @Autowired
    private Modelo_Service modeloService;

    @Autowired
    private RolService rolService;

    @Autowired
    private UsuarioRepository uR;
    @Autowired
    private UsuarioRolService userrol;

    @Autowired
    private Criterio_Service criterioService;

    @Autowired
    private Asignacion_Responsable_Service asigresService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private SeguimientoUsuario_repository seguimientoUsuarioRepository;
    @Autowired
    private SeguimientoUsuario_Service suService;


    @PostConstruct
    public void init() {
        Rol usuario1 = new Rol(1L, "ADMIN");
        Rol usuario2 = new Rol(2L, "SUPERADMIN");
        Rol usuario3 = new Rol(3L, "RESPONSABLE");
        Rol usuario4 = new Rol(4L, "AUTORIDAD");

        rolService.save(usuario1);
        rolService.save(usuario2);
        rolService.save(usuario3);
        rolService.save(usuario4);
    }

    @PostMapping("/crearsup")
    public ResponseEntity<Usuario> crearSup(@RequestBody Usuario r, @RequestParam("rolIds") List<Long> rolIds) {
        try {
            Usuario usuarioExistente = usuarioService.findAllByUsername(r.getUsername());
            if (usuarioExistente != null) {
                usuarioExistente.setVisible(true);
                return new ResponseEntity<>(usuarioService.save(usuarioExistente), HttpStatus.OK);
            }

            for (Long rol : rolIds) {
                Rol nRol = rolService.findById(rol);
                UsuarioRol usuarioRol = new UsuarioRol();
                usuarioRol.setUsuario(r);
                usuarioRol.setRol(nRol);
                r.getUsuarioRoles().add(usuarioRol);
            }

            // Codificar la contraseña antes de guardar el usuario
            r.setPassword(this.bCryptPasswordEncoder.encode(r.getPassword()));
            r.setVisible(true);

            // Guardar el usuario en la base de datos
            return new ResponseEntity<>(usuarioService.save(r), HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/crearadm/{rolId}/{adminId}/{modeloId}")
    public ResponseEntity<Usuario> crearadm(@RequestBody Usuario r, @PathVariable Long rolId,@PathVariable Long adminId,@PathVariable Long modeloId) {
        try {
            Usuario usuarioExistente = usuarioService.findAllByUsername(r.getUsername());
            if (usuarioExistente != null) {

                usuarioExistente.setVisible(true);
                //Si el responsable existe se crea nuevamente las asignaciones de los criterios
                registrarCriteriosAdminAlResponsable(usuarioExistente,adminId,modeloId);
                asignarResponsableAdm(usuarioExistente,adminId);
                // Registrar la acción en el seguimiento de usuarios
                registrarSeguimiento(usuarioExistente);
                return new ResponseEntity<>(usuarioService.save(usuarioExistente), HttpStatus.OK);
            }
            // Buscar el rol por ID
            Rol rol = rolService.findById(rolId);
            r.setPassword(this.bCryptPasswordEncoder.encode(r.getPassword()));
            r.setVisible(true);
            // Crear un nuevo UsuarioRol y establecer las referencias correspondientes
            UsuarioRol usuarioRol = new UsuarioRol();
            usuarioRol.setUsuario(r);
            usuarioRol.setRol(rol);

            // Agregar el UsuarioRol a la lista de roles del usuario
            r.getUsuarioRoles().add(usuarioRol);
            // Guardar el usuario en la base de datos
            Usuario nuevoUsuario = uR.save(r);
            //Una vez que se crea el usuario se debe crear una asignacion con los criterios del admin
            registrarCriteriosAdminAlResponsable(nuevoUsuario, adminId, modeloId);
            //Registrar la asignacion del responsable con el admin
            asignarResponsableAdm(nuevoUsuario,adminId);
            // Registrar la acción en el seguimiento de usuarios
            registrarSeguimiento(nuevoUsuario);

            return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    private void registrarCriteriosAdminAlResponsable(Usuario responsable, Long adminId, Long modeloId) {
        List<Criterio> listaCriteriosAdm = criterioService.obtenerCriteriosPorUsuarioYModelo(adminId,modeloId);
        for (Criterio criterios : listaCriteriosAdm) {
            Asignacion_Admin asignacion= asigadmservice.asignacion_existente(criterios.getId_criterio(),modeloId,responsable.getId());
            if(asignacion==null){
                Asignacion_Admin nuevaAsignacion = new Asignacion_Admin();
                nuevaAsignacion.setUsuario(responsable);
                nuevaAsignacion.setCriterio(criterios);
                Modelo modelo = modeloService.findById(modeloId);
                nuevaAsignacion.setId_modelo(modelo);
                nuevaAsignacion.setVisible(true);
                asigadmservice.save(nuevaAsignacion);
            }else {
                asignacion.setVisible(true);
                asigadmservice.save(asignacion);
            }
        }
    }
    private void asignarResponsableAdm(Usuario usuario, Long adminId) {
        Asignacion_Responsable asignacionExistente = asigresService.asignacion_existente(adminId, usuario.getId());
        if (asignacionExistente != null) {
            asignacionExistente.setVisible(true);
            asigresService.save(asignacionExistente);
        }
        Asignacion_Responsable asigres = new Asignacion_Responsable();
        Usuario admin = usuarioService.findById(adminId);
        asigres.setUsuarioAdmin(admin);
        asigres.setUsuarioResponsable(usuario);
        asigres.setVisible(true);
        asigresService.save(asigres);
    }
    private void registrarSeguimiento(Usuario usuario) {
        SeguimientoUsuario seguimiento = new SeguimientoUsuario();
        seguimiento.setUsuario(usuario);
        seguimiento.setDescripcion("Usuario creado");
        seguimiento.setFecha(new Date());
        seguimientoUsuarioRepository.save(seguimiento);
    }
    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> obtenerLista() {
        try {

            return new ResponseEntity<>(usuarioService.findByAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // listar solo los responsables
    @GetMapping("/listarResponsable")
    public ResponseEntity<List<Usuario>> obtenerListaResponsables() {
        try {
            // List<Usuario> responsables = uR.listaResponsables();
            return new ResponseEntity<>(uR.listaResponsables(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listarv")
    public ResponseEntity<List<Usuario>> obtenerListav() {
        try {
            return new ResponseEntity<>(uR.listar(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listarResDatos")
    public ResponseEntity<List<Usuario>> obtenerListaRespoDatos() {
        try {

            return new ResponseEntity<>(uR.listaResponsablesDatos(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listarResponsableAdmin")
    public ResponseEntity<List<Usuario>> obtenerListaResponsableAdmin() {
        try {
            return new ResponseEntity<>(uR.listaResponsablesAdmin(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/responsables")
    public ResponseEntity<List<Usuario>> Responsables() {
        try {
            return new ResponseEntity<>(uR.responsables(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/responsablesGeneral")
    public ResponseEntity<List<ResponsableProjection>> ResponsablesAdmin() {
        try {
            return new ResponseEntity<>(uR.responsablesGeneral(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/buscar/{username}")
    public Usuario obtenerUsuario(@PathVariable("username") String username) {
        return usuarioService.obtenerUsuario(username);
    }

    @GetMapping("/buscaruser/{username}")
    public Usuario obtenerIdUsuario(@PathVariable("username") String username) {
        return usuarioService.obtenerId(username);
    }

    @DeleteMapping("/{usuarioId}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable("usuarioId") Long usuarioId) {
        try {
            Usuario usuario = usuarioService.findById(usuarioId);
            if (usuario == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            // Registrar la acción en el seguimiento de usuarios antes de eliminar el usuario
            SeguimientoUsuario seguimiento = new SeguimientoUsuario();
            seguimiento.setUsuario(usuario);
            seguimiento.setDescripcion("Usuario eliminado");
            seguimiento.setFecha(new Date());
            seguimientoUsuarioRepository.save(seguimiento);

            // Eliminar al usuario
            usuarioService.delete(usuarioId);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Usuario> actualizarCliente(@PathVariable Long id, @RequestBody Usuario p) {
        Usuario usuarioAntes = usuarioService.findById(id);
        if (usuarioAntes == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            // Capturar los campos editados
            List<String> camposEditados = new ArrayList<>();
            if (!Objects.equals(usuarioAntes.getPassword(), p.getPassword())) {
                camposEditados.add("contraseña");
            }
            // Actualizar el usuario con los nuevos valores
            usuarioAntes.setPassword(this.bCryptPasswordEncoder.encode(p.getPassword()));

            // Guardar el usuario actualizado en la base de datos
            Usuario usuarioDespues = usuarioService.save(usuarioAntes);

            // Si hay campos editados, registrar la acción en el seguimiento de usuarios
            if (!camposEditados.isEmpty()) {
                SeguimientoUsuario seguimiento = new SeguimientoUsuario();
                seguimiento.setUsuario(usuarioDespues);
                seguimiento.setDescripcion("Usuario actualizado - Campos editados: " + String.join(", ", camposEditados));
                seguimiento.setFecha(new Date());
                seguimientoUsuarioRepository.save(seguimiento);
            }

            return new ResponseEntity<>(usuarioDespues, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/eliminarlogic/{id}")
    public ResponseEntity<?> eliminarlogic(@PathVariable Long id) {
        Usuario a = usuarioService.findById(id);
        if (a == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                a.setVisible(false);
                // Guardar los cambios en el usuario
                usuarioService.save(a);

                // Obtener las asignaciones de evidencia relacionadas con el usuario
                List<Asignacion_Evidencia> asignaciones = asigeviservice.listarporUsuarioxd(id);
                for (Asignacion_Evidencia asignacion : asignaciones) {
                    asignacion.setVisible(false);
                    // Guardar los cambios en cada asignación de evidencia
                    asigeviservice.save(asignacion);
                }
                // Obtener las asignaciones de admins con criterios relacionadas con el usuario
                List<Asignacion_Admin> asignaciones_admin = asigadmservice.listaAsignacionAdminPorIdUsuario(id);
                for (Asignacion_Admin asignacion : asignaciones_admin) {
                    asignacion.setVisible(false);
                    // Guardar los cambios en cada asignación de evidencia
                    asigadmservice.save(asignacion);
                }
                // Obtener los registros del usuariorol relacionadas con el usuario
                List<UsuarioRol> usuarioRols =userrol.findByUsuarios_UsuarioId(id);
                for (UsuarioRol usuarioconRol : usuarioRols) {
                    userrol.delete(usuarioconRol.getUsuarioRolId());
                }

                // Registrar la acción en el seguimiento de usuarios
                SeguimientoUsuario seguimiento = new SeguimientoUsuario();
                seguimiento.setUsuario(a);
                seguimiento.setDescripcion("Usuario inactivo");
                seguimiento.setFecha(new Date());
                seguimientoUsuarioRepository.save(seguimiento);

                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }



    // public List<Usuario> listaAdminDatos();
    @GetMapping("/listarAdminDatos")
    public ResponseEntity<List<Usuario>> obtenerListaAdminDatos() {
        try {

            return new ResponseEntity<>(uR.listaAdminDatos(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listarsoloResponsables")
    public ResponseEntity<List<Usuario>> solloresonsables() {
        try {

            return new ResponseEntity<>(uR.listaSOLORESPONSABLES(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/responsablesAdmin/{idAdministrador}")
    public ResponseEntity<List<ResponsableProjection>> responsablesAdmin(@PathVariable Long idAdministrador) {
        try {
            //Tendria que tomar el id del administrador como parametro, y con ello traigo los responsables
            List<ResponsableProjection> responsables = usuarioService.responsablesAdmin(idAdministrador);
            return new ResponseEntity<>(responsables, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listarSeguimientoUsuario")
    public ResponseEntity<List<SeguimientoUsuarioDTO>> listaSeguimientoUsuario() {
        try {
            return new ResponseEntity<>(suService.listaSeguimientoUsuario(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
