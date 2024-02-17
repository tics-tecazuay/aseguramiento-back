package com.sistema.examenes.controller;

import com.sistema.examenes.entity.Asignacion_Admin;
import com.sistema.examenes.entity.Asignacion_Responsable;
import com.sistema.examenes.entity.Criterio;
import com.sistema.examenes.projection.AsignacionProjection;
import com.sistema.examenes.projection.NombreAsigProjection;
import com.sistema.examenes.services.Asignacion_Admin_Service;

import com.sistema.examenes.services.Asignacion_Responsable_Service;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin({"https://apps.tecazuay.edu.ec","http://localhost:4200/"})
@RestController
@RequestMapping("/aseguramiento/api/asignacion_admin")
public class Asignacion_Admin_Controller {
    @Autowired
    Asignacion_Admin_Service Service;

    @Autowired
    Asignacion_Responsable_Service asignacionResService;

    @PostMapping("/crear")
    public ResponseEntity<Asignacion_Admin> crear(@RequestBody Asignacion_Admin r) {
        try {
            Long criterio = r.getCriterio().getId_criterio(); // Obtener el ID del criterio
            Long modelo = r.getId_modelo().getId_modelo();
            Long usuario=r.getUsuario().getId();
            Asignacion_Admin asignacionExistente = Service.asignacion_existente(criterio, modelo,usuario);
            if (asignacionExistente != null) {
                asignacionExistente.setVisible(true);
                return new ResponseEntity<>(Service.save(asignacionExistente), HttpStatus.OK);
            }
            r.setVisible(true);
            return new ResponseEntity<>(Service.save(r), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/asignacionadmin/{id_modelo}/{veri}")
    public ResponseEntity<List<AsignacionProjection>> asignacionadmin(@PathVariable("id_modelo") Long id_modelo,@PathVariable("veri") String veri) {
        try {
            return new ResponseEntity<>(Service.asignacionAdmin(id_modelo,veri), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/listar")
    public ResponseEntity<List<Asignacion_Admin>> obtenerLista() {
        try {
            return new ResponseEntity<>(Service.findByAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listarv")
    public ResponseEntity<List<Asignacion_Admin>> obtenerListav() {
        try {
            return new ResponseEntity<>(Service.listar(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Asignacion_Admin> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(Service.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        return Service.delete(id);
    }

    @PutMapping("/eliminarlogic/{id}")
    public ResponseEntity<?> eliminarlogic(@PathVariable Long id) {
        try {
            Asignacion_Admin a = Service.findById(id);
            if (a == null) {
                return new ResponseEntity<>("Asignacion_Admin no encontrada con el ID proporcionado", HttpStatus.NOT_FOUND);
            }
            Criterio criterioAdm = a.getCriterio();
            List<Asignacion_Responsable> responsables = asignacionResService.Asignacion_ResponsablesByAdmin(a.getUsuario().getId());
            if (!responsables.isEmpty()) {
                for (Asignacion_Responsable responsable : responsables) {
                    Asignacion_Admin asigcriterioResp = Service.findById(responsable.getUsuarioResponsable().getId());
                    if (criterioAdm.getId_criterio().equals(asigcriterioResp.getCriterio().getId_criterio())) {
                        responsable.setVisible(false);
                        asignacionResService.save(responsable);
                    }
                }
            }
            a.setVisible(false);
            return new ResponseEntity<>(Service.save(a), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al cambiar la visibilidad de la asignación admin: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Asignacion_Admin> actualizar(@PathVariable Long id, @RequestBody Asignacion_Admin p) {
        Asignacion_Admin a = Service.findById(id);
        if (a == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                //a.setUsuario(p.getUsuario());
              //  a.setCriterio(p.getCriterio());
                a.setVisible(p.isVisible());
                return new ResponseEntity<>(Service.save(a), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }

    @GetMapping("/listarAsignacion_AdminPorUsuario/{id_usuario}/{id_modelo}")
    public ResponseEntity<Asignacion_Admin> listarAsignacion_AdminPorUsuario(
            @PathVariable("id_usuario") Long id_usuario, @PathVariable("id_modelo") Long id_modelo) {
        try {
            return new ResponseEntity<>(Service.listarAsignacion_AdminPorUsuario(id_usuario,id_modelo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/listarnombre_admin/{id_modelo}/{id_criterio}")
    public ResponseEntity<NombreAsigProjection> listarnombre_Admin(
            @PathVariable("id_modelo") Long id_modelo, @PathVariable("id_criterio") Long id_criterio) {
        try {
            return new ResponseEntity<>(Service.listarnombre_Admin(id_modelo,id_criterio), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/veradminsporcriterio/{id_modelo}/{id_criterio}")
    public ResponseEntity<List<AsignacionProjection>> veradminsporcriterio(
            @PathVariable("id_modelo") Long id_modelo, @PathVariable("id_criterio") Long id_criterio) {
        try {
            return new ResponseEntity<>(Service.veradminsporcriterio(id_modelo, id_criterio), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/verresponsablesporcriterio/{id_modelo}/{id_criterio}")
    public ResponseEntity<List<AsignacionProjection>> verresponsablesporcriterio(
            @PathVariable("id_modelo") Long id_modelo, @PathVariable("id_criterio") Long id_criterio) {
        try {
            return new ResponseEntity<>(Service.verresponsablesporcriterio(id_modelo, id_criterio), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listarAsignacion_AdminPorUsuarioCriterio/{id_criterio}/{id_modelo}")
    public ResponseEntity<List<Asignacion_Admin>> listarAsignacion_AdminPorUsuarioCriterio(
            @PathVariable("id_criterio") Long id_criterio,  @PathVariable("id_modelo") Long id_modelo) {
        try {
            List<Asignacion_Admin> asignaciones = Service.listarAsignacion_AdminPorUsuarioCriterio(id_criterio, id_modelo);

            if (asignaciones.isEmpty()) {
                // Manejar el caso en el que no hay asignaciones
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // o cualquier otro código de estado adecuado
            } else {
                // Retornar la lista de asignaciones
                return new ResponseEntity<>(asignaciones, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizarAgregado/{id_asignacion}")
    public ResponseEntity<Asignacion_Admin> actualizarAgregado(@PathVariable("id_asignacion") Long id_asignacion) {
        Asignacion_Admin a = Service.findById(id_asignacion);
        if (a == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                a.setVisible(true);
                return new ResponseEntity<>(Service.save(a), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }


    @GetMapping("/busqueda_especifica/{idUsuario}/{idModelo}/{idCriterio}")
    public ResponseEntity<Asignacion_Admin> buscarAsignacionAdmin(
            @PathVariable("idUsuario") Long idUsuario,
            @PathVariable("idModelo") Long idModelo,
            @PathVariable("idCriterio") Long idCriterio) {
        try {
            Asignacion_Admin asignacionAdmin = Service.buscar_asignacion_especifica(idUsuario, idModelo, idCriterio);

            if (asignacionAdmin != null) {
                return new ResponseEntity<>(asignacionAdmin, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Manejar el caso en el que no se encuentre ninguna asignación
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}