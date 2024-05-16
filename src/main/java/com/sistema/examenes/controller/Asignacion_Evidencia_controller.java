package com.sistema.examenes.controller;

import com.sistema.examenes.entity.Asignacion_Evidencia;
import com.sistema.examenes.entity.Evidencia;
import com.sistema.examenes.entity.Historial_Asignacion_Evidencia;
import com.sistema.examenes.entity.Usuario;
import com.sistema.examenes.entity.pdto.AsignacionEvidenciaPDTO;
import com.sistema.examenes.projection.*;
import com.sistema.examenes.entity.dto.Asignacion_EvidenciaDTO;
import com.sistema.examenes.services.Asignacion_Evidencia_Service;
import com.sistema.examenes.services.Evidencia_Service;
import com.sistema.examenes.services.Historial_Asignacion_Evidencia_Service;
import com.sistema.examenes.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin({"https://apps.tecazuay.edu.ec","http://localhost:4200/"})
@RestController
@RequestMapping("/aseguramiento/api/asignacionevidencia")
public class Asignacion_Evidencia_controller {
    @Autowired
    Asignacion_Evidencia_Service Service;
    @Autowired
    Historial_Asignacion_Evidencia_Service ServiceHistorialAsignacion;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    Evidencia_Service evidenciaService;
    Historial_Asignacion_Evidencia nuevoRegistroAsignacion;
    Usuario usuarioAsignador;
    @PostMapping("/crear")
    public ResponseEntity<List<Asignacion_Evidencia>> crear(@RequestBody List<AsignacionEvidenciaPDTO> evidencias) {
        try {

            List<Asignacion_Evidencia> asignacionesGuardadas = new ArrayList<>();

            for (AsignacionEvidenciaPDTO evidencia : evidencias) {
                Asignacion_Evidencia nuevaAsignacion = new Asignacion_Evidencia();
                nuevaAsignacion.setVisible(true);
                nuevaAsignacion.setEstado("pendiente");
                nuevaAsignacion.setArchsubido(false);
                nuevaAsignacion.setId_modelo(evidencia.getId_modelo());
                nuevaAsignacion.setFecha_inicio(evidencia.getFecha_inicio());
                nuevaAsignacion.setFecha_fin(evidencia.getFecha_fin());
                nuevaAsignacion.setId_usuario_asignador(evidencia.getId_usuario_asignador());
                Usuario usuarioAsignado = usuarioService.findById(evidencia.getUsuario_id());
                nuevaAsignacion.setUsuario(usuarioAsignado);
                Evidencia evidenciaAsignada = evidenciaService.findById(evidencia.getEvidencia_id());
                nuevaAsignacion.setEvidencia(evidenciaAsignada);
                Asignacion_Evidencia asignacionGuardada = Service.save(nuevaAsignacion);

                nuevoRegistroAsignacion = new Historial_Asignacion_Evidencia();
                usuarioAsignador = usuarioService.findById(evidencia.getId_usuario_asignador());
                nuevoRegistroAsignacion.setUsuario_asignador(usuarioAsignador);
                nuevoRegistroAsignacion.setAsignacion_evi(asignacionGuardada);
                nuevoRegistroAsignacion.setVisible(true);
                ServiceHistorialAsignacion.save(nuevoRegistroAsignacion);

                asignacionesGuardadas.add(asignacionGuardada);
            }

            return new ResponseEntity<>(asignacionesGuardadas, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Asignacion_Evidencia>> obtenerLista() {
        try {
            return new ResponseEntity<>(Service.findByAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/listarv/{id_modelo}")
    public ResponseEntity<List<AsignacionEvidenciaCalendarProjection>> obtenerListav(@PathVariable("id_modelo") Long id_modelo) {
        try {
            return new ResponseEntity<>(Service.listar(id_modelo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listasignacion/{id_modelo}")
    public ResponseEntity<List<AsignaProjection>> obtenerListaasig(@PathVariable("id_modelo") Long id_modelo) {
        try {
            return new ResponseEntity<>(Service.listarAsigEvidencia(id_modelo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listasignacioneviporuser/{usuarioId}")
    public ResponseEntity<List<AsignaProjection>> obtenerListaAsignacion(@PathVariable("usuarioId") Long usuarioId) {
        try {
            List<AsignaProjection> asignaciones = Service.listarAsigEvidenciaPorUsuario(usuarioId);
            return new ResponseEntity<>(asignaciones, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/actCalendar/{id}/{id_modelo}")
    public ResponseEntity<List<ActiCalendarProjection>> getActCalUserById(@PathVariable("id") Long id,@PathVariable("id_modelo") Long id_modelo) {
        try {
            List<ActiCalendarProjection> actividades = Service.listarActiCalendarbyuser(id,id_modelo);
            return new ResponseEntity<>(actividades, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Asignacion_Evidencia> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(Service.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/eliminarlogic/{id}")
    public ResponseEntity<?> eliminarloginc(@PathVariable Long id) {
        Asignacion_Evidencia asignacion_evidencia = Service.findById(id);
        if (asignacion_evidencia == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                asignacion_evidencia.setVisible(false);
                return new ResponseEntity<>(Service.save(asignacion_evidencia), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }

    @PutMapping("/elimasig/{id}/{id_evi}/{id_usuario}/{id_modelo}")
    public ResponseEntity<?> eliminarasig(@PathVariable Long id, @PathVariable Long id_evi, @PathVariable Long id_usuario, @PathVariable Long id_modelo) {
        Asignacion_Evidencia asignacion_evidencia = Service.findById(id);
        if (asignacion_evidencia == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                Boolean existe=Service.verificarAsignacionUsuario(id_usuario,id_evi,id_modelo);
                if(existe){
                asignacion_evidencia.setVisible(false);
                return new ResponseEntity<>(Service.save(asignacion_evidencia), HttpStatus.CREATED);
                }else{
                    String mensaje="Solo el administrador del criterio puede eliminar la asignación";
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensaje);
                }
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
    @GetMapping("/fecha/{id_evidencia}/{id_modelo}")
    public ResponseEntity<Asignacion_Evidencia> listarfecha(@PathVariable("id_evidencia") Long id_evidencia, @PathVariable("id_modelo") Long id_modelo) {
        try {
            return new ResponseEntity<>(Service.fechaactividades(id_evidencia, id_modelo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Asignacion_Evidencia> actualizar(@PathVariable Long id,@RequestBody Asignacion_Evidencia p) {
        Asignacion_Evidencia asignacion_evidencia = Service.findById(id);
        if (asignacion_evidencia == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                asignacion_evidencia.setEvidencia(p.getEvidencia());
                return new ResponseEntity<>(Service.save(asignacion_evidencia), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Asignacion_Evidencia> editar(@PathVariable Long id,@RequestBody Asignacion_Evidencia p) {
        Asignacion_Evidencia asignacion_evidencia = Service.findById(id);
        if (asignacion_evidencia == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                asignacion_evidencia.setFecha_fin(p.getFecha_fin());
                asignacion_evidencia.setFecha_inicio(p.getFecha_inicio());
                return new ResponseEntity<>(Service.save(asignacion_evidencia), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }

    @PutMapping("/editarEstado/{id}")
    public ResponseEntity<Asignacion_Evidencia> editarEstado(@PathVariable Long id,@RequestBody Asignacion_Evidencia p) {
        Asignacion_Evidencia asignacion_evidencia = Service.findById(id);
        if (asignacion_evidencia == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                asignacion_evidencia.setEstado(p.getEstado());
                return new ResponseEntity<>(Service.save(asignacion_evidencia), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }
    @PutMapping("/editarArchSubido/{id}/{estado}")
    public ResponseEntity<Asignacion_Evidencia> editarArchSubido(@PathVariable Long id, @PathVariable boolean estado) {
        Asignacion_Evidencia asignacion_evidencia = Service.findById(id);
        if (asignacion_evidencia == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                // Aquí actualizamos el estado de archsubido a true
                asignacion_evidencia.setArchsubido(estado);
                return new ResponseEntity<>(Service.save(asignacion_evidencia), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }


    @PutMapping("/cambiarUsuario/{idEvidencia}/{idNuevoUsuario}/{id_modelo}")
    public ResponseEntity<Asignacion_Evidencia> cambiarUsuario(@PathVariable Long idEvidencia, @PathVariable Long idNuevoUsuario, @PathVariable Long id_modelo) {
        List<Asignacion_Evidencia> asignacionEvidenciaList = Service.listarporEvidencia(idEvidencia,id_modelo);
        Evidencia evidencia = evidenciaService.findById(idEvidencia);
        Usuario nuevoUsuario = usuarioService.findById(idNuevoUsuario);

        if (!asignacionEvidenciaList.isEmpty() && evidencia != null && nuevoUsuario != null) {
            // Verificar si el usuario ya está asignado a esta evidencia
            for (Asignacion_Evidencia asignacion : asignacionEvidenciaList) {
                if (asignacion.getUsuario().getId() == idNuevoUsuario) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // El usuario ya está asignado a esta evidencia
                }
            }

            // Si el usuario no está asignado, procedemos con la actualización
            Asignacion_Evidencia asignacionEvidencia = asignacionEvidenciaList.get(0); // Tomamos la primera asignación, ya que solo hay una

            // Actualizar el usuario en la asignación de evidencia
            asignacionEvidencia.setUsuario(nuevoUsuario);
            Service.save(asignacionEvidencia);
            evidenciaService.save(evidencia);

            return new ResponseEntity<>(asignacionEvidencia, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/countArchivos/{idAsignacionEv}")
    public int countArchivos(@PathVariable("idAsignacionEv") Long idAsignacionEv) {
        return Service.countArchivosByIdAsigEv(idAsignacionEv);
    }

    @GetMapping("/listarAsigEviUser/{username}/{id_evidencia}/{idModel}")
    public List<Asignacion_EvidenciaDTO> listarAsigEviUser(@PathVariable("username") String username,@PathVariable("id_evidencia") Long id_evidencia,
                                                           @PathVariable("idModel") Long idModel) {
        return Service.listarAsigEviUser(username,id_evidencia, idModel);
    }

    //no se utiliza la de abajo
    @GetMapping("/buscarusuario/{username}")
    public ResponseEntity <List<Asignacion_Evidencia>> listaractiUsuario(@PathVariable("username") String username) {

        try {
            return new ResponseEntity<>(Service.listarporusuario(username), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/buscarporEvide/{idEviden}/{id_modelo}")
    public ResponseEntity <List<Asignacion_Evidencia>> listarporEvidencia(@PathVariable("idEviden") Long idEvidencia,@PathVariable("id_modelo") Long id_modelo) {
        try {
            return new ResponseEntity<>(Service.listarporEvidencia(idEvidencia, id_modelo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//el de abajo no se utiliza
    @GetMapping("/evidencias/{estado}/{id_modelo}")
    public ResponseEntity<List<EvidenciaReApPeAtrProjection>> obtenerEvidenciasPorEstado(@PathVariable("estado") String estado, @PathVariable("id_modelo") Long id_modelo) {

        try {
            return new ResponseEntity<>(Service.listarEvideByEstado(estado,id_modelo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/evidenciasAdm/{estado}/{id_admin}/{idModel}")
    public ResponseEntity<List<EvidenciaReApPeAtrProjection>> obtenerEvidenciasPorEstadoAdm(@PathVariable("estado") String estado, @PathVariable("id_admin") Long id_admin,
                                                                                            @PathVariable("idModel") Long idModel) {
        try {
            return new ResponseEntity<>(Service.listarEvideByEstadoAdm(estado,id_admin, idModel), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//no se utiliza abajo
    @GetMapping("/listaractividad")
    public ResponseEntity<List<ActivProyection>> listarActividad () {
        try {
            return new ResponseEntity<>(Service.listarByActividad(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
