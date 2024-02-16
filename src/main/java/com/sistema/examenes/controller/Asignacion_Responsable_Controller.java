package com.sistema.examenes.controller;

import com.sistema.examenes.entity.Asignacion_Responsable;
import com.sistema.examenes.entity.Usuario;
import com.sistema.examenes.projection.ResponsableProjection;
import com.sistema.examenes.services.Asignacion_Responsable_Service;
import com.sistema.examenes.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "https://apps.tecazuay.edu.ec" })
@RestController
@RequestMapping("/aseguramiento/api/asignacion_responsable")
public class Asignacion_Responsable_Controller {
    @Autowired
    Asignacion_Responsable_Service Service;
    @Autowired
    UsuarioService ServiceUsuario;
    @PostMapping("/crear/{usuarioAdmin}/{usuarioResponsable}")
    public ResponseEntity<?> crear(@PathVariable Long usuarioAdmin, @PathVariable String usuarioResponsable) {
        try {
            Usuario usuarioAdm = ServiceUsuario.findById(usuarioAdmin);
            Usuario usuarioRes = ServiceUsuario.findAllByUsername(usuarioResponsable);
            Asignacion_Responsable asignacionExistente = Service.asignacion_existente(usuarioAdm.getId(),usuarioRes.getId());
            if (asignacionExistente != null) {
                asignacionExistente.setVisible(true);
                return new ResponseEntity<>(Service.save(asignacionExistente), HttpStatus.OK);
            }
            Asignacion_Responsable r = new Asignacion_Responsable();
            r.setUsuarioAdmin(usuarioAdm);
            r.setUsuarioResponsable(usuarioRes);
            r.setVisible(true);
            return new ResponseEntity<>(Service.save(r), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Asignacion_Responsable> actualizar(@PathVariable Long id, @RequestBody Asignacion_Responsable p) {
        Asignacion_Responsable a = Service.findById(id);
        if (a == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                a.setVisible(p.isVisible());
                return new ResponseEntity<>(Service.save(a), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        return Service.delete(id);
    }
    @PutMapping("/eliminarlogic/{id_usuarioResponsable}")
    public ResponseEntity<?> eliminarlogic(@PathVariable Long id_usuarioResponsable) {
        Asignacion_Responsable a = Service.asignacionByIdUsuarioResponsable(id_usuarioResponsable);
        if (a == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                Usuario u = ServiceUsuario.findById(id_usuarioResponsable);
                u.setVisible(false);
                a.setVisible(false);
                ServiceUsuario.save(u);
                return new ResponseEntity<>(Service.save(a), HttpStatus.NO_CONTENT);
            } catch (Exception e) {

                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }
    @GetMapping("/listadeResponsablesByAdmin/{idAdministrador}")
    public ResponseEntity<List<ResponsableProjection>> listadeResponsablesByAdmin(@PathVariable Long idAdministrador) {
        try {
            //Tendria que tomar el id del administrador como parametro, y con ello traigo los responsables
            List<ResponsableProjection> responsables = Service.listadeResponsablesByAdmin(idAdministrador);
            return new ResponseEntity<>(responsables, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}