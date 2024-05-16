package com.sistema.examenes.controller;

import com.sistema.examenes.entity.*;
import com.sistema.examenes.entity.pdto.AsignacionAdminPDTO;
import com.sistema.examenes.projection.*;
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
    public ResponseEntity<Asignacion_Admin> crear(@RequestBody AsignacionAdminPDTO dto) {
        try {
            Long criterio = dto.getIdCriterio();
            Long modelo = dto.getIdModelo();
            Long usuario = dto.getIdUsuario();

            Asignacion_Admin asignacionExistente = Service.asignacion_existente(criterio, modelo, usuario);

            if (asignacionExistente != null) {
                // La asignación ya existe
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                Asignacion_Admin nuevaAsignacion = new Asignacion_Admin();
                nuevaAsignacion.setCriterio(new Criterio(criterio));
                nuevaAsignacion.setId_modelo(new Modelo(modelo));
                nuevaAsignacion.setUsuario(new Usuario(usuario));
                nuevaAsignacion.setVisible(true);

                // Manejar el responsable aquí
                Criterio criterioAdm = nuevaAsignacion.getCriterio();
                List<Asignacion_Responsable> responsables = asignacionResService.Asignacion_ResponsablesByAdmin(usuario);
                if (responsables != null) {
                    for (Asignacion_Responsable responsable : responsables) {
                        Asignacion_Admin asigcriterioResp = Service.findById(responsable.getUsuarioResponsable().getId());
                        if (asigcriterioResp != null && criterioAdm.getId_criterio().equals(asigcriterioResp.getCriterio().getId_criterio())) {
                            responsable.setVisible(true);
                            asignacionResService.save(responsable);
                        }
                    }
                }
                Asignacion_Admin asignacionGuardada = Service.save(nuevaAsignacion);
                return new ResponseEntity<>(asignacionGuardada, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizarEstado/{id}")
    public ResponseEntity<?> actualizarEstado(@PathVariable Long id) {
        try {
            // Obtener la asignación por su ID
            Asignacion_Admin asignacion = Service.findById(id);
            if (asignacion == null) {
                return new ResponseEntity<>("Asignacion_Admin no encontrada con el ID proporcionado", HttpStatus.NOT_FOUND);
            }
            asignacion.setVisible(true);
            Service.save(asignacion);
            // Devolver el DTO de la asignación actualizada
            AsignacionAdminPDTO asignacionAdminPDTO = new AsignacionAdminPDTO();
            asignacionAdminPDTO.setIdAsignacion(asignacion.getId_asignacion());
            return new ResponseEntity<>(asignacionAdminPDTO, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Error al actualizar el estado de visibilidad de la asignación admin: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
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
            // Obtener la asignación por su ID
            Asignacion_Admin a = Service.findById(id);
            if (a == null) {
                return new ResponseEntity<>("Asignacion_Admin no encontrada con el ID proporcionado", HttpStatus.NOT_FOUND);
            }
            Criterio criterioAdm = a.getCriterio();
            List<Asignacion_Responsable> responsables = asignacionResService.Asignacion_ResponsablesByAdmin(a.getUsuario().getId());
            if (responsables != null) {
                for (Asignacion_Responsable responsable : responsables) {
                    Asignacion_Admin asigcriterioResp = Service.findById(responsable.getUsuarioResponsable().getId());
                    if (asigcriterioResp != null && criterioAdm.getId_criterio().equals(asigcriterioResp.getCriterio().getId_criterio())) {
                        responsable.setVisible(false);
                        asignacionResService.save(responsable);
                    }
                }
            }

            a.setVisible(false);
            Service.save(a);

            // Crear el DTO para el proceso de eliminación
            AsignacionAdminPDTO asignacionAdminPDTO = new AsignacionAdminPDTO();
            asignacionAdminPDTO.setIdAsignacion(a.getId_asignacion());
            // Devolver el DTO en la respuesta
            return new ResponseEntity<>(asignacionAdminPDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
    public ResponseEntity<?> buscarAsignacionAdmin(
            @PathVariable("idUsuario") Long idUsuario,
            @PathVariable("idModelo") Long idModelo,
            @PathVariable("idCriterio") Long idCriterio) {
        try {
            AsignacionProjection asignacionAdmin = Service.buscarAsignacionAdmin(idUsuario, idModelo, idCriterio);

            if (asignacionAdmin != null) {
                return new ResponseEntity<>(asignacionAdmin, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/actividadatrasa/{id_modelo}")
    public ResponseEntity<List<ActivAprobadaProjection>> actividadAtra(@PathVariable("id_modelo") Long id_modelo) {

        try {
            return new ResponseEntity<>(Service.actividadAtrasada(id_modelo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/actividadaprobada/{id_modelo}")
    public ResponseEntity<List<ActivAprobadaProjection>> actividadApro(@PathVariable("id_modelo") Long id_modelo) {

        try {
            return new ResponseEntity<>(Service.actividadAprobada(id_modelo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/actividadpendiente/{id_modelo}")
    public ResponseEntity<List<ActivAprobadaProjection>> actividadpendiente(@PathVariable("id_modelo") Long id_modelo) {

        try {
            return new ResponseEntity<>(Service.actividadpendiente(id_modelo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/listaractiv/{id_modelo}")
    public ResponseEntity<List<ActividadesAvanceProjection>> obtactiv(@PathVariable("id_modelo")Long id_modelo) {
        try {
            return new ResponseEntity<>(Service.actividadCont(id_modelo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listarevidencias/{estado}/{id_modelo}")
    public ResponseEntity<List<ActivProyection>> evidenciasautoridad(@PathVariable("estado")String estado, @PathVariable("id_modelo")Long id_modelo) {

        try {
            return new ResponseEntity<>(Service.listarEvidenciasAutoridad(estado, id_modelo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/evidenciausuario/{id}/{id_modelo}")
    public ResponseEntity<List<ActivProyection>> evidenciausuario(@PathVariable("id")Long id,@PathVariable("id_modelo")Long id_modelo) {
        try {
            return new ResponseEntity<>(Service.evidenciaUsu(id, id_modelo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}