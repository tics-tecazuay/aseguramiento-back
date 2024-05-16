package com.sistema.examenes.controller;

import com.sistema.examenes.entity.Calificar_Indicador;
import com.sistema.examenes.services.Calificar_Indicador_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin({"https://apps.tecazuay.edu.ec","http://localhost:4200/"})
@RestController
@RequestMapping("/aseguramiento/api/calificar_indicador")
public class Calificar_Indicador_Controller {

    @Autowired
    Calificar_Indicador_Service Service;

    @PostMapping("/crear")
    public ResponseEntity<Calificar_Indicador> crear(@RequestBody Calificar_Indicador r) {
        try {
            if (r == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            Calificar_Indicador calificacionExistente = Service.obtenerCalificacionPorIdIndicadorIdModelo(r.getIndicador().getId_indicador(), r.getId_modelo());
            if(calificacionExistente!=null){
                calificacionExistente.setValor_obtenido(r.getValor_obtenido());
                calificacionExistente.setPorc_obtenido(r.getPorc_obtenido());
                calificacionExistente.setPorc_utilida_obtenida(r.getPorc_utilida_obtenida());
                Service.save(calificacionExistente);
            }else{
                r.setVisible(true);
                Service.save(r);
            }
            return new ResponseEntity<>(r, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/buscar/{id}")
    public ResponseEntity<Calificar_Indicador> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(Service.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/buscarporindicador/{id_indicador}/{id_modelo}")
    public ResponseEntity<Calificar_Indicador> getById(@PathVariable("id_indicador") Long id_indicador,@PathVariable("id_modelo") Long id_modelo) {
        try {
            return new ResponseEntity<>(Service.obtenerCalificacionPorIdIndicadorIdModelo(id_indicador,id_modelo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/eliminarlogic/{id}")
    public ResponseEntity<?> eliminarLogic(@PathVariable Long id) {
        Calificar_Indicador Calificar_Indicador = Service.findById(id);
        if (Calificar_Indicador == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                Calificar_Indicador.setVisible(false);
                return new ResponseEntity<>(Service.save(Calificar_Indicador), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Calificar_Indicador> actualizar(@PathVariable Long id, @RequestBody Calificar_Indicador p) {
        Calificar_Indicador Calificar_Indicador = Service.findById(id);
        if (Calificar_Indicador == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                Calificar_Indicador.setValor_obtenido(p.getValor_obtenido()); ;
                Calificar_Indicador.setPorc_obtenido(p.getPorc_obtenido());
                Calificar_Indicador.setPorc_utilida_obtenida(p.getPorc_utilida_obtenida());
                return new ResponseEntity<>(Service.save(Calificar_Indicador), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }
}