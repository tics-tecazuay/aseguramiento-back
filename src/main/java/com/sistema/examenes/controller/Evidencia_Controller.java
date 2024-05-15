package com.sistema.examenes.controller;

import com.sistema.examenes.entity.Evidencia;
import com.sistema.examenes.projection.*;
import com.sistema.examenes.services.Evidencia_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin({"https://apps.tecazuay.edu.ec","http://localhost:4200/"})
@RestController
@RequestMapping("/aseguramiento/api/evidencia")
public class Evidencia_Controller {

    @Autowired
    Evidencia_Service Service;

    @PostMapping("/crear")
    public ResponseEntity<Evidencia> crear(@RequestBody Evidencia r) {
        try {
            r.setVisible(true);

            return new ResponseEntity<>(Service.save(r), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/listar")
    public ResponseEntity<List<Evidencia>> obtenerLista() {
        try {
            return new ResponseEntity<>(Service.findByAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listarv")
    public ResponseEntity<List<Evidencia>> obtenerListav() {
        try {
            return new ResponseEntity<>(Service.listar(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/listarvAsigna/{id}")
    public ResponseEntity<List<Evidencia>> obtenerListavAsigna(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(Service.listarEvidenciaAsigna(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/evidenciaprobada/{id_modelo}")
    public ResponseEntity<List<EvidenciasProjection>> evidenciaprobada(@PathVariable("id_modelo") Long id_modelo) {
        try {
            return new ResponseEntity<>(Service.evidenciaAprobada(id_modelo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/evidenciarechazada/{id_modelo}")
    public ResponseEntity<List<EvidenciasProjection>> evidenciarechazada(@PathVariable("id_modelo") Long id_modelo) {
        try {
            return new ResponseEntity<>(Service.evidenciaRechazada(id_modelo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/evicriterio/{idcriterio}")
    public ResponseEntity<List<Evidencia>> evidenciaindicador(@PathVariable("idcriterio") Long idcriterio) {
        try {
            return new ResponseEntity<>(Service.evidenciacriterio(idcriterio), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/obtenerevidenciasporcriterio/{idcriterio}/{id_modelo}")
    public ResponseEntity<List<AsigEvidProjection>> obtenerEvidenciasPorCriterio(@PathVariable("idcriterio") Long idcriterio, @PathVariable("id_modelo") Long id_modelo) {
        try {
            return new ResponseEntity<>(Service.obtenerEvidenciasPorCriterio(idcriterio,id_modelo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/eviasigadmin/{idUser}/{id_modelo}")
    public ResponseEntity<List<AsigEvidProjection>> evidenciaadmintabla(@PathVariable("idUser") Long idUser,@PathVariable("id_modelo") Long id_modelo) {
        try {
            return new ResponseEntity<>(Service.listarEvidenciaAdmin(idUser,id_modelo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Evidencia> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(Service.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/buscarev/{username}/{id_modelo}")
    public ResponseEntity<List<EvidenciaEvProjection>> buscarEvidencia(@PathVariable("username") String username, @PathVariable("id_modelo") Long id_modelo) {
        try {
            return new ResponseEntity<>(Service.evidenciaUsuario(username,id_modelo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/searchevifiltradoporadm/{username}/{usuarioId}/{idModel}")
    public ResponseEntity<List<EvidenciaEvProjection>> buscarEvidenciaPorCriterio(@PathVariable("username") String username, @PathVariable("usuarioId") Long usuarioId,
                                                                                  @PathVariable("idModel") Long idModel) {
        try {
            return new ResponseEntity<>(Service.evidenciaFiltraCriterio(username, usuarioId, idModel), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/evidenuser/{username}/{idModel}")
    public ResponseEntity<List<EvidenciaProjection>> evidenciauser(@PathVariable("username") String username, @PathVariable("idModel") Long idModel) {
        try {
            return new ResponseEntity<>(Service.evidenUsuario(username, idModel), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/evidenuserpendiente/{username}/{id_modelo}")
    public ResponseEntity<List<EvidenciaProjection>> evidenUserPendiente(@PathVariable("username") String username, @PathVariable("id_modelo") Long id_modelo) {
        try {
            return new ResponseEntity<>(Service.evidenUserPendiente(username, id_modelo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/evidenciacal/{id_evidencia}/{id_modelo}")
    public ResponseEntity<EvidenciaCalProjection> evidenciacal(@PathVariable("id_evidencia") Long id_evidencia,@PathVariable("id_modelo") Long id_modelo) {
        try {
            return new ResponseEntity<>(Service.evidenciacal(id_evidencia,id_modelo), HttpStatus.OK);
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
        Evidencia a = Service.findById(id);
        if (a==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                a.setVisible(false);
                return new ResponseEntity<>(Service.save(a), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Evidencia> actualizar(@PathVariable Long id, @RequestBody Evidencia p) {
        Evidencia a = Service.findById(id);
        if (a == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                a.setDescripcion(p.getDescripcion());
                a.setEstado(p.getEstado());
                return new ResponseEntity<>(Service.save(a), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }

    @PutMapping("/actualizar2/{id}")
    public ResponseEntity<Evidencia> actualizar2(@PathVariable Long id, @RequestBody Evidencia p) {
        Evidencia a = Service.findById(id);
        if (a == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                //a.setDescripcion(p.getDescripcion());
                a.setEstado(p.getEstado());
                return new ResponseEntity<>(Service.save(a), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }

    @GetMapping("/listarArchivos/{id_evidencia}")
    public ResponseEntity<List<EvidenciaProjection>> listarArchivosporEvidencia(
            @PathVariable("id_evidencia") Long id_evidencia) {
        try {
            return new ResponseEntity<>(Service.listararchivos(id_evidencia), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listarEvidenciaPorIndicador/{id_indicador}")
    public ResponseEntity<List<Evidencia>> listarEvidenciaPorIndicador(
            @PathVariable("id_indicador") Long id_indicador) {
        try {
            return new ResponseEntity<>(Service.listarEvidenciaPorIndicador(id_indicador), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/porcentajeEstadosdeActividades/{responsableId}/{id_modelo}")
    public ResponseEntity<ActiDiagramaPieProjection> porcentajeEstadosdeActividadesPorResponsableId(@PathVariable("responsableId") Long responsableId, @PathVariable("id_modelo") Long id_modelo) {
        try {
            ActiDiagramaPieProjection actividades = Service.porcentajeEstadosdeActividades(responsableId, id_modelo);
            return new ResponseEntity<>(actividades, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Estados de las Evidencias Generales - Rol Autoridad
    @GetMapping("/porcentajeEstadosdeActividadesGeneral/{id_modelo}")
    public ResponseEntity<ActiDiagramaPieProjection> porcentajeEstadosdeEvidenciasGeneral(@PathVariable("id_modelo") Long id_modelo) {
        try {
            ActiDiagramaPieProjection actividades = Service.porcentajeEstadosdeEvidenciasGeneral(id_modelo);
            return new ResponseEntity<>(actividades, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/editarValorEvid/{id}")
    public ResponseEntity<Evidencia> editarValorEvid(@PathVariable Long id,  @RequestParam("valorevid") double valorevid) {
        Evidencia evidencia = Service.findById(id);
        if (evidencia == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                // Aquí actualizamos el valor de la evidencia
                evidencia.setValor_obtenido(valorevid);
                return new ResponseEntity<>(Service.save(evidencia), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
    @PutMapping("/editarValoresEvidaCero/{id_indicador}")
    public ResponseEntity<Evidencia> editarValoresEvidaCero(@PathVariable Long id_indicador) {
        List<Evidencia> evidencia = Service.listarEvidenciaPorIndicador(id_indicador);
        if (evidencia == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                for (Evidencia e : evidencia) {
                    // Aquí actualizamos el valor de la evidencia
                    e.setValor_obtenido(0.0);
                    Service.save(e);
                }
                return new ResponseEntity<>(evidencia.get(0), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
    @GetMapping("/valoresObtenidosEvidPorIndicador/{id_indicador}")
    public ResponseEntity<ValorObtenidoInd> valoresObtenidosEvidPorIndicador(@PathVariable("id_indicador") Long id_indicador) {
        try {
            ValorObtenidoInd valores = Service.valoresObtenidosEvidencias(id_indicador);
            return new ResponseEntity<>(valores, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}