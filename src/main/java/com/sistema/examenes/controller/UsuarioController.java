package com.sistema.examenes.controller;

import com.sistema.examenes.entity.*;
import com.sistema.examenes.entity.dto.SeguimientoUsuarioDTO;
import com.sistema.examenes.projection.ResponsableProjection;
import com.sistema.examenes.projection.UsuariosProjection;
import com.sistema.examenes.repository.Asignacion_Responsable_repository;
import com.sistema.examenes.repository.SeguimientoUsuario_repository;
import com.sistema.examenes.repository.UsuarioRepository;
import com.sistema.examenes.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.annotation.PostConstruct;
import java.util.*;

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
    Asignacion_Responsable_Service ServiceResponsable;

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

    @Autowired
    private Asignacion_Responsable_repository arr;
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
                usuarioExistente.getUsuarioRoles().clear();
                //Contraseña nueva ingresada
                String nuevaContraseña =  r.getPassword(); // La contraseña proporcionada por el usuario
                String contraseñaAlmacenada = usuarioExistente.getPassword(); // La contraseña almacenada en la base de datos
                // Verificar si la nueva contraseña no está vacía
                if (nuevaContraseña != null && !nuevaContraseña.isEmpty()) {
                    // Verificar si la nueva contraseña es diferente a la almacenada
                    if (!bCryptPasswordEncoder.matches(nuevaContraseña, contraseñaAlmacenada)) {
                        // Codificar y actualizar la nueva contraseña
                        usuarioExistente.setPassword(bCryptPasswordEncoder.encode(nuevaContraseña));
                    }
                } else {
                    // Si la nueva contraseña está vacía, mantener la contraseña almacenada
                    usuarioExistente.setPassword(contraseñaAlmacenada);
                }
                activaroRegistrarRoles(usuarioExistente,rolIds);
                usuarioService.save(usuarioExistente);
                return new ResponseEntity<>(usuarioExistente, HttpStatus.OK);
            }else{
                // Codificar la contraseña antes de guardar el usuario
                r.setPassword(this.bCryptPasswordEncoder.encode(r.getPassword()));
                //Setear Visible para que este activo en el sistema
                r.setVisible(true);
                //Se recorre los roles
                for (Long rol : rolIds) {
                    Rol nRol = rolService.findById(rol);
                    UsuarioRol usuarioRol = new UsuarioRol();
                    usuarioRol.setUsuario(r);
                    usuarioRol.setRol(nRol);
                    usuarioRol.setVisible(true);
                    r.getUsuarioRoles().add(usuarioRol);
                }
                // Guardar el usuario en la base de datos
                return new ResponseEntity<>(usuarioService.save(r), HttpStatus.CREATED);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/crearadm/{rolId}/{adminId}/{modeloId}")
    public ResponseEntity<Usuario> crearadm(@RequestBody Usuario r, @PathVariable Long rolId,@PathVariable Long adminId,@PathVariable Long modeloId) {
        try {
            Usuario usuarioExistente = usuarioService.findAllByUsername(r.getUsername());
            if (usuarioExistente != null) {
                //Si existe se debe comprobar si tiene el registro del responsable para que solo pase a true y listo, debe
                //validarse la combinacion del id_admin y id_modelo y id_usuario_responsable
                Asignacion_Responsable asignacionResponsableExistente = arr.obtenerAsignacionResponsablePorIdResponsableIdAdminIdModelo(usuarioExistente.getId(),adminId,modeloId);
                if (asignacionResponsableExistente==null) {
                    // Si no lo ha asignado, realizar la asignación
                    usuarioExistente.setVisible(true);
                    registrarCriteriosAdminAlResponsable(usuarioExistente, adminId, modeloId);
                    asignarResponsableAdm(usuarioExistente, adminId, modeloId);
                    return new ResponseEntity<>(usuarioService.save(usuarioExistente), HttpStatus.OK);
                } else {
                    asignacionResponsableExistente.setVisible(true);
                    arr.save(asignacionResponsableExistente);
                    return new ResponseEntity<>(usuarioExistente, HttpStatus.OK);
                }
            }
            // Buscar el rol por ID
            Rol rol = rolService.findById(rolId);
            r.setPassword(this.bCryptPasswordEncoder.encode(r.getPassword()));
            r.setVisible(true);
            // Crear un nuevo UsuarioRol
            UsuarioRol usuarioRol = new UsuarioRol();
            usuarioRol.setUsuario(r);
            usuarioRol.setRol(rol);
            usuarioRol.setVisible(true);
            r.getUsuarioRoles().add(usuarioRol);

            Usuario nuevoUsuario = uR.save(r);// Guardar el usuario en la base de datos
            registrarCriteriosAdminAlResponsable(nuevoUsuario, adminId, modeloId);//Asignacion con los criterios del admin
            asignarResponsableAdm(nuevoUsuario,adminId, modeloId);//Registra la asignacion del responsable con el admin
            registrarSeguimiento(nuevoUsuario);// Registra la acción en el seguimiento de usuarios

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
    private void asignarResponsableAdm(Usuario usuario, Long adminId, Long idModel) {

        Asignacion_Responsable asignacionExistente = asigresService.asignacion_existente(adminId, usuario.getId(), idModel);
        if (asignacionExistente != null) {
            asignacionExistente.setVisible(true);
            asigresService.save(asignacionExistente);
        }
        Asignacion_Responsable asigres = new Asignacion_Responsable();
        Usuario admin = usuarioService.findById(adminId);
        asigres.setUsuarioAdmin(admin);
        asigres.setUsuarioResponsable(usuario);
        asigres.setId_modelo(idModel);
        asigres.setVisible(true);
        asigresService.save(asigres);
    }
    private void activaroRegistrarRoles(Usuario usuario,List<Long> rolIds) {
        if(rolIds!=null) {
            List<UsuarioRol> usuarioRols = userrol.findByUsuarios_UsuarioId(usuario.getId());
            if (usuarioRols != null) {
                //Marcar todos los roles existentes como no visibles (eliminados lógicamente)
                for (UsuarioRol rolUsuario : usuarioRols) {
                    rolUsuario.setVisible(false);
                    userrol.save(rolUsuario);
                    System.out.println("Entro aqui y elimino logicamente los roles del usuario " + rolUsuario.getRol().getRolNombre() + " Estado: " + rolUsuario.isVisible());
                }
                for (Long idRol : rolIds) {
                    //Traer los roles del usuario y si coinciden con el id del array de roles colocar en true
                    //su visibilidad caso contrario si no tiene ese rol el usuario deberia crearse
                    for (UsuarioRol rolUsuario : usuarioRols) {
                        if (rolUsuario.getRol().getRolId().equals(idRol)) {
                            System.out.println("Activando el rol: " + rolUsuario.getRol().getRolNombre() + " estado: " + rolUsuario.isVisible());
                            rolUsuario.setVisible(true);
                            userrol.save(rolUsuario);
                        } else {
                            System.out.println("Entro aqui para crear el rol: ");
                            Rol rol = rolService.findById(idRol);
                            if (rol != null) {
                                UsuarioRol nuevoUsuarioRol = new UsuarioRol();
                                nuevoUsuarioRol.setUsuario(usuario);
                                nuevoUsuarioRol.setRol(rol);
                                nuevoUsuarioRol.setVisible(true); // Marcar como visible
                                userrol.save(nuevoUsuarioRol);
                                System.out.println("ROL CREADO: " + nuevoUsuarioRol.getRol().getRolNombre());
                            }
                        }
                    }
                }
            }
        }
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
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/listarResDatos/{id_modelo}")
    public ResponseEntity<List<Usuario>> obtenerListaRespoDatosSup(@PathVariable Long id_modelo) {
        try {
            return new ResponseEntity<>(uR.listaResponsablesDatos(id_modelo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listaResponsablesFromAdmin/{idAdministrador}/{idModel}")
    public ResponseEntity<List<Usuario>> obtenerListaRespoDatos(@PathVariable Long idAdministrador, @PathVariable Long idModel) {
        try {
            return new ResponseEntity<>(uR.listaResponsablesFromAdmin(idAdministrador, idModel), HttpStatus.OK);
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
    @GetMapping("/responsablesGeneral/{id_modelo}")
    public ResponseEntity<List<ResponsableProjection>> ResponsablesAdmin(@PathVariable("id_modelo") Long id_modelo) {
        try {
            return new ResponseEntity<>(uR.responsablesGeneral(id_modelo), HttpStatus.OK);
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
                //Marcar todos los roles existentes como no visibles (eliminados lógicamente)
                List<UsuarioRol> usuarioRols = userrol.findByUsuarios_UsuarioId(id);
                for (UsuarioRol rolUsuario : usuarioRols) {
                    rolUsuario.setVisible(false);
                    userrol.save(rolUsuario);
                    System.out.println("Entro aqui y elimino logicamente los roles del usuario " + rolUsuario.getRol().getRolNombre() +" Estado: "+rolUsuario.isVisible());
                }

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


                // Registrar la acción en el seguimiento de usuarios
                SeguimientoUsuario seguimiento = new SeguimientoUsuario();
                seguimiento.setUsuario(a);
                seguimiento.setDescripcion("Usuario inactivo");
                seguimiento.setFecha(new Date());
                seguimientoUsuarioRepository.save(seguimiento);

                // Guardar los cambios en el usuario
                a.setVisible(false);
                usuarioService.save(a);
                return new ResponseEntity<>(a,HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PutMapping("/eliminarlogicResp/{id_usuarioResponsable}/{id_modelo}")
    public ResponseEntity<?> eliminarlogicResp(@PathVariable Long id_usuarioResponsable,@PathVariable Long id_modelo) {
        Asignacion_Responsable a = ServiceResponsable.asignacionByIdUsuarioResponsableIdModelo(id_usuarioResponsable, id_modelo);
        if (a == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                //Usuario u = usuarioService.findById(id_usuarioResponsable);
                //u.setVisible(false);
                a.setVisible(false);

                // lista de las asignaciones
                List<Asignacion_Evidencia> asignaciones = asigeviservice.listarporUsuarioxd(id_usuarioResponsable);
                for (Asignacion_Evidencia asignacion : asignaciones) {
                    asignacion.setVisible(false);
                    // Guardar los cambios en cada asignación de evidencia
                    asigeviservice.save(asignacion);
                }
                //Listas de asignaciones admin
                //List<Asignacion_Admin> asignacionAdmins = asigadmservice.listaAsignacionAdminPorIdUsuario()
                //usuarioService.save(u);
                return new ResponseEntity<>(ServiceResponsable.save(a), HttpStatus.OK);
            } catch (Exception e) {
                System.out.println("ERROR AL ELIMINAR UN RESPONSABLE ASIGNADO: "+ e.getMessage());
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }

    @GetMapping("/listUserCrite/{id_modelo}")
    public ResponseEntity<List<UsuariosProjection>> listusercrite(@PathVariable("id_modelo") Long id_modelo) {
        try {
            return new ResponseEntity<>(usuarioService.listarusercrite(id_modelo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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
