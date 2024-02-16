package com.sistema.examenes.controller;

import com.sistema.examenes.entity.Actividad;
import com.sistema.examenes.entity.Asignacion_Evidencia;
import com.sistema.examenes.entity.Historial_Asignacion_Evidencia;
import com.sistema.examenes.entity.Usuario;
import com.sistema.examenes.projection.ActiCalendarProjection;
import com.sistema.examenes.entity.dto.Asignacion_EvidenciaDTO;
import com.sistema.examenes.projection.AsignaProjection;
import com.sistema.examenes.projection.AsignacionEvidenciaProyeccion;
import com.sistema.examenes.projection.EvidenciaReApPeAtrProjection;
import com.sistema.examenes.services.Asignacion_Evidencia_Service;
import com.sistema.examenes.services.Historial_Asignacion_Evidencia_Service;
import com.sistema.examenes.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = { "https://apps.tecazuay.edu.ec" })
@RestController
@RequestMapping("/aseguramiento/api/asignacionevidencia")
public class Asignacion_Evidencia_controller {
    @Autowired
    Asignacion_Evidencia_Service Service;
    @Autowired
    Historial_Asignacion_Evidencia_Service ServiceHistorialAsignacion;
    @Autowired
    UsuarioService usuarioService;
    Historial_Asignacion_Evidencia nuevoRegistroAsignacion;
    Usuario usuarioAsignador;
    @PostMapping("/crear")
    public ResponseEntity<Asignacion_Evidencia> crear(@RequestBody Asignacion_Evidencia r) {
        try {
            r.setVisible(true);
            Asignacion_Evidencia asignacionGuardada = Service.save(r);
            usuarioAsignador= usuarioService.findById(r.getId_usuario_asignador());
            System.out.println("Usuario asignador:"+usuarioAsignador.getPersona().getCedula());
            nuevoRegistroAsignacion = new Historial_Asignacion_Evidencia();
            nuevoRegistroAsignacion.setUsuario_asignador(usuarioAsignador);
            nuevoRegistroAsignacion.setAsignacion_evi(asignacionGuardada);
            nuevoRegistroAsignacion.setVisible(true);
            ServiceHistorialAsignacion.save(nuevoRegistroAsignacion);
            return new ResponseEntity<>(asignacionGuardada, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
    @GetMapping("/listarv")
    public ResponseEntity<List<Asignacion_Evidencia>> obtenerListav() {
        try {
            return new ResponseEntity<>(Service.listar(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/listasignacion")
    public ResponseEntity<List<AsignaProjection>> obtenerListaasig() {
        try {
            return new ResponseEntity<>(Service.listarAsigEvidencia(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/listarpruebasevi")
    public ResponseEntity<List<AsignacionEvidenciaProyeccion>> listarpruebasevi() {
        try {
            return new ResponseEntity<>(Service.listarAsignacionEvidenciaProyeccion(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/actCalendar/{id}")
    public ResponseEntity<List<ActiCalendarProjection>> getActCalUserById(@PathVariable("id") Long id) {
        try {
            List<ActiCalendarProjection> actividades = Service.listarActiCalendarbyuser(id);
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
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestBody Actividad actividad) {
        return Service.delete(id);
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
        System.out.println("Prueba asignacion usuario"+id_usuario+" evidencia "+id_evi+" modelo "+id_modelo);
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
    @GetMapping("/listarEviUsua/{username}")
    public ResponseEntity<List<Asignacion_Evidencia>> listarAsigEvi(@PathVariable("username") String  username) {
        try {
            return new ResponseEntity<>(Service.listarporUsuario(username), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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

    @GetMapping("/listarAsigEviUser/{username}/{id_evidencia}")
    public List<Asignacion_EvidenciaDTO> listarAsigEviUser(@PathVariable("username") String username,@PathVariable("id_evidencia") Long id_evidencia) {
        return Service.listarAsigEviUser(username,id_evidencia);
    }

    @GetMapping("/buscarusuario/{username}")
    public ResponseEntity <List<Asignacion_Evidencia>> listaractiUsuario(@PathVariable("username") String username) {

        try {
            return new ResponseEntity<>(Service.listarporusuario(username), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/buscarporEvide/{idEviden}")
    public ResponseEntity <List<Asignacion_Evidencia>> listarporEvidencia(@PathVariable("idEviden") Long idEvidencia) {
        try {
            return new ResponseEntity<>(Service.listarporEvidencia(idEvidencia), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //listar las actividades rechazadas
    @GetMapping("/listEviR")
    public ResponseEntity<List<EvidenciaReApPeAtrProjection>> obtenerListaEviR() {
        try {
            return new ResponseEntity<>(Service.listaEvidRe(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listEviAp")
    public ResponseEntity<List<EvidenciaReApPeAtrProjection>> obtenerListaEviAp() {
        try {
            return new ResponseEntity<>(Service.listaEvidAp(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listEviPen")
    public ResponseEntity<List<EvidenciaReApPeAtrProjection>> obtenerListaEviPen() {
        try {
            return new ResponseEntity<>(Service.listaEvidPen(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
