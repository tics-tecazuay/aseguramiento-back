package com.sistema.examenes.controller;

import com.sistema.examenes.entity.Asignacion_Evidencia;
import com.sistema.examenes.entity.Historial_Asignacion_Evidencia;
import com.sistema.examenes.projection.HistorialAsignacionEvidenciaProjection;
import com.sistema.examenes.services.Historial_Asignacion_Evidencia_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = { "https://apps.tecazuay.edu.ec" })
@RestController
@RequestMapping("/aseguramiento/api/historialasignacionevidencia")
public class Historial_Asignacion_Evidencia_controller {
    @Autowired
    Historial_Asignacion_Evidencia_Service Service;

    @PostMapping("/crear")
    public ResponseEntity<Historial_Asignacion_Evidencia> crear(@RequestBody Historial_Asignacion_Evidencia r, Asignacion_Evidencia a) {
        try {
            r.setVisible(true);
            return new ResponseEntity<>(Service.save(r), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Historial_Asignacion_Evidencia>> obtenerLista() {
        try {
            return new ResponseEntity<>(Service.findByAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Historial_Asignacion_Evidencia> actualizar(@PathVariable Long id,@RequestBody Historial_Asignacion_Evidencia p) {
        Historial_Asignacion_Evidencia historial_asignacion_evidencia = Service.findById(id);
        if (historial_asignacion_evidencia == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                historial_asignacion_evidencia.setAsignacion_evi(p.getAsignacion_evi());
                historial_asignacion_evidencia.setFecha(p.getFecha());
                historial_asignacion_evidencia.setVisible(p.getVisible());
                return new ResponseEntity<>(Service.save(historial_asignacion_evidencia), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }
    @PutMapping("/eliminarlogic/{id}")
    public ResponseEntity<?> eliminarloginc(@PathVariable Long id) {
        Historial_Asignacion_Evidencia historial_asignacion_evidencia = Service.findById(id);
        if (historial_asignacion_evidencia == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                historial_asignacion_evidencia.setVisible(false);
                return new ResponseEntity<>(Service.save(historial_asignacion_evidencia), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestBody Historial_Asignacion_Evidencia actividad) {
        return Service.delete(id);
    }
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Historial_Asignacion_Evidencia> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(Service.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/listarHistorial/{id_usuario_asignador}/{id_criterio}/{veri}")
    public ResponseEntity<List<HistorialAsignacionEvidenciaProjection>> listarHistorial(@PathVariable("id_usuario_asignador") Long id_usuario_asignador , @PathVariable("id_criterio") Long id_criterio, @PathVariable("veri") String veri ) {
        try {
            return new ResponseEntity<>(Service.listarHistorial(id_usuario_asignador, id_criterio, veri), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
