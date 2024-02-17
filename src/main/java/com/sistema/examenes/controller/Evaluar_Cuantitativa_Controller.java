package com.sistema.examenes.controller;

import com.sistema.examenes.entity.Encabezado_Evaluar;
import com.sistema.examenes.entity.Evaluar_Cuantitativa;
import com.sistema.examenes.services.Evaluar_Cuantitativa_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin({"https://apps.tecazuay.edu.ec","http://localhost:4200/"})
@RestController
@RequestMapping("/aseguramiento/api/evaluar_cuantitativa")
public class Evaluar_Cuantitativa_Controller {
    @Autowired
    Evaluar_Cuantitativa_Service Service;

    @PostMapping("/crear")
    public ResponseEntity<Evaluar_Cuantitativa> crear(@RequestBody Evaluar_Cuantitativa r) {
        try {
            r.setVisible(true);

            return new ResponseEntity<>(Service.save(r), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Evaluar_Cuantitativa>> obtenerLista() {
        try {
            return new ResponseEntity<>(Service.findByAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listarv")
    public ResponseEntity<List<Evaluar_Cuantitativa>> obtenerListav() {
        try {
            return new ResponseEntity<>(Service.listar(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Evaluar_Cuantitativa> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(Service.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestBody Evaluar_Cuantitativa evaluar_cuantitativa) {
        return Service.delete(id);
    }

    @PutMapping("/eliminarlogic/{id}")
    public ResponseEntity<?> eliminarlogic(@PathVariable Long id) {
        Evaluar_Cuantitativa a = Service.findById(id);
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
    public ResponseEntity<Evaluar_Cuantitativa> actualizar(@PathVariable Long id, @RequestBody Evaluar_Cuantitativa p) {
        Evaluar_Cuantitativa a = Service.findById(id);
        if (a == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                a.setValor(p.getValor());
                return new ResponseEntity<>(Service.save(a), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }

    @GetMapping("/listarPorEncabezado/{id}")
    public ResponseEntity<List<Evaluar_Cuantitativa>> listarPorEncabezado(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(Service.listarEvaluarCuantitativaPorIndicador(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
