package com.sistema.examenes.controller;

import com.sistema.examenes.entity.Actividad;
import com.sistema.examenes.entity.Asignacion_Evidencia;
import com.sistema.examenes.entity.Notificacion;
import com.sistema.examenes.services.Actividad_Service;
import com.sistema.examenes.services.Asignacion_Evidencia_Service;
import com.sistema.examenes.services.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = { "https://apps.tecazuay.edu.ec" })
@RestController
@RequestMapping("/aseguramiento/api/notificacion")
public class Notificacion_Controller {
    @Autowired
    NotificacionService service;
    @Autowired
    Asignacion_Evidencia_Service act;

    @PostMapping("/crear")
    public ResponseEntity<Notificacion> crear(@RequestBody Notificacion not){
        try {
            not.setVisto(false);
            return new ResponseEntity<>(service.save(not), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listarTodasNotificaciones")
    public ResponseEntity<List<Notificacion>>listarTodasNotificaciones(){
        try {
            return new ResponseEntity<>(service.listarTodasNotificaciones(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listarnotificaciones/{id}")
    public ResponseEntity<List<Notificacion>>listar(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>(service.listar(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/notificacionsinleer/{id}")
    public ResponseEntity<List<Notificacion>>noleidos(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>(service.listarulNoti(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/listartodo/{roluser}")
    public ResponseEntity<List<Notificacion>>obtenerLista(@PathVariable("roluser") String roluser) {
        try {
            return new ResponseEntity<>(service.all(roluser), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/listartodo2/{roluser}/{userId}")
    public ResponseEntity<List<Notificacion>>obtenerLista2(@PathVariable("roluser") String roluser, @PathVariable("userId") Long userId) {
        try {
            return new ResponseEntity<>(service.all2(roluser,userId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id) {
        Notificacion notificacion=service.findById(id);
        if (notificacion == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                notificacion.setVisto(true);

                return new ResponseEntity<>(service.save(notificacion), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }
    @Scheduled(cron = "0 0 0 * * ?")
    public void eliminarNotificacionesAntiguas() {
        java.sql.Date fe=service.fechaeliminar();
        if(fe!=null){
        LocalDate fechaLocal = fe.toLocalDate();
        LocalDate fechaNueva = fechaLocal.plusDays(15);
        String fecha1=String.valueOf(fechaNueva);
        String fechael=fecha1;

        List<Notificacion> notificacionesAntiguas = service.listarNotifi(fechael);

        for (Notificacion notificacion : notificacionesAntiguas) {
            service.eliminar(notificacion.getId());
        }
       }
    }
//@Scheduled(cron = "segundo minuto hora día-del-mes mes día-de-la-semana")
    @Scheduled(cron = "0 0 10 * * ?") // Ejecutar todos los días a las 10 AM 13PM
    public void CrearNotificaciones() {
        List<Asignacion_Evidencia> actividades = act.findByAll();
        for (Asignacion_Evidencia actividad : actividades) {
            Date fechaFinActividad = actividad.getFecha_fin();
            Date fechaActual = new Date();

            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(fechaFinActividad);
            calendar1.add(Calendar.DAY_OF_MONTH, -1);
            Date fechaNotificacion1 = calendar1.getTime();

            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(fechaFinActividad);
            Date fechaNotificacion2 = calendar2.getTime();

            if (fechaActual.compareTo(fechaNotificacion1) >= 0 || fechaActual.compareTo(fechaNotificacion2) >= 0) {
                Notificacion notificacion = new Notificacion();
                notificacion.setFecha(new Date());
                notificacion.setRol("");
                if (fechaActual.compareTo(fechaNotificacion1) >= 0) {
                    notificacion.setMensaje("La actividad " + actividad.getEvidencia().getDescripcion() + " finalizará en 1 día. Asegúrese de haberla cumplido.");
                } else {
                    notificacion.setMensaje("Hoy es el día de entrega de la actividad " + actividad.getEvidencia().getDescripcion() + ". Asegúrese de haberla cumplido.");
                }
                notificacion.setVisto(false);
                notificacion.setUsuario(actividad.getUsuario().getId());

                service.save(notificacion);
            }
        }
    }
    @PostConstruct
    public void iniciarServidor() {
        eliminarNotificacionesAntiguas();
    }
}
