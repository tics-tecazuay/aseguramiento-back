package com.sistema.examenes.controller;

import com.sistema.examenes.entity.Criterio;
import com.sistema.examenes.entity.Subcriterio;
import com.sistema.examenes.entity.Usuario;
import com.sistema.examenes.entity.pdto.SubcriterioPDTO;
import com.sistema.examenes.projection.*;
import com.sistema.examenes.services.Criterio_Service;
import com.sistema.examenes.services.Subcriterio_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin({"https://apps.tecazuay.edu.ec","http://localhost:4200/"})
@RestController
@RequestMapping("/aseguramiento/api/subcriterio")
public class Subcriterio_Controller {
    @Autowired
    Subcriterio_Service Service;
    @Autowired
    Criterio_Service serviceCriterio;

    @PostMapping("/crear")
    public ResponseEntity<Subcriterio> crear(@RequestBody SubcriterioPDTO r) {
        try {
            Subcriterio nuevoSubcriterio = new Subcriterio();
            nuevoSubcriterio.setVisible(true);
            nuevoSubcriterio.setDescripcion(r.getDescripcion());
            nuevoSubcriterio.setNombre(r.getNombre());
            Criterio criterio = serviceCriterio.findById(r.getId_criterio());
            nuevoSubcriterio.setCriterio(criterio);
            return new ResponseEntity<>(Service.save(nuevoSubcriterio), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Subcriterio>> obtenerLista() {
        try {
            return new ResponseEntity<>(Service.listar(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Subcriterio> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(Service.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Subcriterio subcriterio = Service.findById(id);
        if (subcriterio == null) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                subcriterio.setVisible(false);
                return new ResponseEntity<>(Service.save(subcriterio), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Subcriterio> actualizar(@PathVariable Long id, @RequestBody Subcriterio p) {
        Subcriterio subcriterio = Service.findById(id);
        if (subcriterio == null) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                subcriterio.setNombre(p.getNombre());
                subcriterio.setDescripcion(p.getDescripcion());
                return new ResponseEntity<>(Service.save(subcriterio), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }

    @GetMapping("/listarPorCriterio/{id_criterio}")
    public ResponseEntity<List<Subcriterio>> listarPorCriterio(@PathVariable("id_criterio") Long id_criterio) {
        try {
            return new ResponseEntity<>(Service.listarPorCriterio(id_criterio), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/datosSubcriterios/{id_criterio}/{id_modelo}")
    public List<SubcriterioIndicadoresProjection> obtenerDatosSubcriterios(@PathVariable("id_criterio") Long id_criterio, @PathVariable("id_modelo") Long id_modelo) {
        return Service.obtenerDatosSubcriterios(id_criterio,id_modelo);
    }

    @GetMapping("/subcritindi/{id_criterio}/{id_modelo}")
    public List<SubcriterioIndicadoresProjection> obtenerSubcriterios(@PathVariable("id_criterio") Long id_criterio,@PathVariable("id_modelo") Long id_modelo) {
        return Service.obtenerSubcriterios(id_criterio, id_modelo);
    }
    @GetMapping("/datosSubcriteriosFull")
    public List<SubcriterioIndicadoresProjectionFull> obtenerDatosSubcriteriosFull() {
        return Service.obtenerDatosSubcriteriosFull();
    }
    @GetMapping("/listarporcsubcriterios/{cri_nombre}/{id_modelo}")
    public ResponseEntity<List<SubcriterioPorcProjection>> listarporcsubcriterios(@PathVariable("cri_nombre") String cri_nombre,@PathVariable("id_modelo") Long id_modelo) {
        try {
            return new ResponseEntity<>(Service.subcriteriosporCriterio(cri_nombre,id_modelo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}