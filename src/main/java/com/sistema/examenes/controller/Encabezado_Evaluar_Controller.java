package com.sistema.examenes.controller;

import com.sistema.examenes.entity.Criterio;
import com.sistema.examenes.entity.Encabezado_Evaluar;
import com.sistema.examenes.services.Encabezado_Evaluar_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "https://apps.tecazuay.edu.ec" })
@RestController
@RequestMapping("/aseguramiento/api/encabezado_evaluar")
public class Encabezado_Evaluar_Controller {
    @Autowired
    Encabezado_Evaluar_Service Service;

    @PostMapping("/crear")
    public ResponseEntity<Encabezado_Evaluar> crear(@RequestBody Encabezado_Evaluar r) {
        try {
            r.setVisible(true);

            return new ResponseEntity<>(Service.save(r), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Encabezado_Evaluar>> obtenerLista() {
        try {
            return new ResponseEntity<>(Service.findByAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/listarv")
    public ResponseEntity<List<Encabezado_Evaluar>> obtenerListav() {
        try {
            return new ResponseEntity<>(Service.listar(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Encabezado_Evaluar> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(Service.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestBody Encabezado_Evaluar encabezado_evaluar) {
        return Service.delete(id);
    }
    @PutMapping("/eliminarlogic/{id}")
    public ResponseEntity<?> eliminarlogic(@PathVariable Long id) {
        Encabezado_Evaluar a = Service.findById(id);
        if (a == null) {
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
    public ResponseEntity<Encabezado_Evaluar> actualizar(@PathVariable Long id, @RequestBody Encabezado_Evaluar p) {
        Encabezado_Evaluar a = Service.findById(id);
        if (a == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                a.setFormula(p.getFormula());
                return new ResponseEntity<>(Service.save(a), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }
}
