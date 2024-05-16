package com.sistema.examenes.controller;

import com.sistema.examenes.entity.Archivo;
import com.sistema.examenes.entity.Archivo_s;
import com.sistema.examenes.entity.Asignacion_Evidencia;
import com.sistema.examenes.entity.Modelo;
import com.sistema.examenes.mensajes.Archivosmensajes;
import com.sistema.examenes.projection.ArchivoAdmSupProjection;
import com.sistema.examenes.projection.ArchivoProjection;
import com.sistema.examenes.projection.ArchivoResProjection;
import com.sistema.examenes.repository.Archivo_repository;
import com.sistema.examenes.services.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;
@RestController
@CrossOrigin({"https://apps.tecazuay.edu.ec","http://localhost:4200/"})
@RequestMapping("/aseguramiento/archivo")
@AllArgsConstructor
public class Archivo_Controller {
    @Autowired
    Archivoservices servis;
    @Autowired
    Evidencia_Service eviservis;
    @Autowired
    Modelo_Service modelo_service;
    @Autowired
    Archivo_Service archivoservis;
    @Autowired
    Asignacion_Evidencia_Service actiservis;
    @Autowired
    HttpServletRequest request;

    @PostMapping("/upload")
    public ResponseEntity<Archivosmensajes> upload(@RequestParam("file") MultipartFile[] files,
                                                   @RequestParam("descripcion") String descripcion,
                                                   @RequestParam("id_evidencia") Long id_evidencia,
                                                   @RequestParam("id_modelo") Long id_modelo) {
        String mensaje = "";
        String comentario = "";
        try {
            Asignacion_Evidencia actividad = actiservis.findById(id_evidencia);
            if (actividad == null) {
                mensaje = "No se encontró la evidencia con id " + id_evidencia;
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Archivosmensajes(mensaje));
            }
            if(id_modelo == null){
                Modelo modeloVigente = modelo_service.listarMaximo();
                id_modelo=modeloVigente.getId_modelo();
            }

            // Calcular la diferencia entre la fecha límite y la fecha actual
            Date fechaLimiteDate = actividad.getFecha_fin();
            LocalDateTime fechaLimite = fechaLimiteDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            LocalDateTime fechaActual = LocalDateTime.now();
            Duration diff = Duration.between(fechaLimite, fechaActual);
            long diasRetraso = diff.toDays();
            long horasRetraso = diff.toHours() % 24;

            // Generar el mensaje de retraso
            if (diasRetraso > 0 || horasRetraso > 0) {
                comentario = "Archivo atrasado con " + diasRetraso + " día(s), " + horasRetraso + " hora(s).";
            } else {
                comentario = "Se subió el archivo a tiempo.";
            }

            // Guardar los archivos y obtener sus nombres
            List<String> fileNames = new ArrayList<>();
            Arrays.asList(files).stream().forEach(file -> {
                String nombreOriginal = file.getOriginalFilename();
                String nombreArchivoUnico = generarNombreUnico(nombreOriginal);

                // Verificar si el nombre ya existe y, si es así, generar uno nuevo
                while (servis.existsByNombre(nombreArchivoUnico)) {
                    nombreArchivoUnico = generarNombreUnico(nombreOriginal);
                }

                servis.guardar(file, nombreArchivoUnico);
                fileNames.add(nombreArchivoUnico);
            });

            // Construir la URL del archivo
            String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
            String url = ServletUriComponentsBuilder.fromHttpUrl(host)
                    .scheme("https") // Agrega este método para establecer el protocolo HTTPS
                    .path("/aseguramiento/archivo/").path(fileNames.get(0)).toUriString();

            // Crear el objeto Archivo_s con el comentario establecido y guardarlo en la base de datos
            Archivo_s arch = new Archivo_s(url, String.join(",", fileNames), descripcion, true, actividad);
            arch.setComentario(comentario);
            arch.setId_modelo(id_modelo);
            archivoservis.save(arch);

            // Mensaje de éxito
            mensaje = "Se subieron correctamente " + fileNames;
            return ResponseEntity.status(HttpStatus.OK).body(new Archivosmensajes(mensaje + "url:" + url));
        } catch (Exception e) {
            // Mensaje de error
            mensaje = "Fallo al subir";
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new Archivosmensajes(mensaje));
        }
    }


    private String generarNombreUnico(String nombreOriginal) {
        // Obtenemos la fecha y hora actual
        Date fechaHoraActual = new Date();
        // Creamos un formato de fecha
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyyMMddHHmmss");
        // Obtenemos la fecha y hora formateada
        String fechaHoraFormateada = formatoFecha.format(fechaHoraActual);
        // Obtenemos la extensión del archivo original (si tiene)
        String extension = "";
        int indicePunto = nombreOriginal.lastIndexOf(".");
        if (indicePunto != -1) {
            extension = nombreOriginal.substring(indicePunto);
            nombreOriginal = nombreOriginal.substring(0, indicePunto);
        }
        // Concatenamos la fecha y hora al nombre original y agregamos la extensión (si existe)
        return nombreOriginal + fechaHoraFormateada + extension;
    }

    @GetMapping("/listarv")
    public ResponseEntity<List<Archivo_s>> obtenerListav() {
        try {
            return new ResponseEntity<>(archivoservis.listar(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/archivoindi/{id_criterio}/{id_modelo}/{id_indicador}")
    public ResponseEntity<List<Archivo_s>> archivoindi(@PathVariable("id_criterio") Long id_criterio,
                                                       @PathVariable("id_modelo")Long id_modelo,
                                                       @PathVariable("id_indicador")Long id_indicador) {
        try {
            return new ResponseEntity<>(archivoservis.archivoporindicador(id_criterio,id_modelo,id_indicador), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/listararchi")
    public ResponseEntity<List<ArchivoProjection>> listaarchi() {
        try {
            return new ResponseEntity<>(archivoservis.listararchi(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/listar")
    public ResponseEntity<List<Archivo>> getFiles() {
        List<Archivo> archivos = servis.lIstar().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder.fromMethodName(Archivo_Controller.class, "getFile"
                    , path.getFileName().toString()).build().toString();

            return new Archivo(filename, url);

        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(archivos);
    }

    @GetMapping("{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = servis.load(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION
                , "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("/borrar/{filename:.+}")
    public ResponseEntity<Archivosmensajes> borrar(@PathVariable String filename) {
        String mensaje = "";
        try {
            mensaje = servis.borrar(filename);
            return ResponseEntity.status(HttpStatus.OK).body(new Archivosmensajes(mensaje));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new Archivosmensajes(mensaje));
        }
    }


    @GetMapping("/buscarev/{username}/{id_asignacion_evi}/{idModel}")
    public ResponseEntity<List<ArchivoResProjection>> listararchi(@PathVariable("username") String username, @PathVariable("id_asignacion_evi") Long id_asignacion_evi,
                                                                  @PathVariable("idModel") Long idModel) {
        try {   
            return new ResponseEntity<>(archivoservis.listararchivouser(username,id_asignacion_evi, idModel), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/buscararchivo/{idActi}/{idModel}")
    public ResponseEntity<List<ArchivoAdmSupProjection>> listararchiActividad(@PathVariable("idActi") Long idActividad, @PathVariable("idModel") Long idModel) {
        try {
            return new ResponseEntity<>(archivoservis.listararchivoActividad(idActividad, idModel), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/eliminarlogic/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Archivo_s as = archivoservis.findById(id);
        if (as == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                as.setVisible(false);
                return new ResponseEntity<>(archivoservis.save(as), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }
    @GetMapping("getfilepdf/{filename:.+}")
    public ResponseEntity<Resource> getFilePDF(@PathVariable String filename) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        Resource file = servis.load(filename);
        headers.add("Content-Disposition", "inline; filename= "+file.getFilename());
        return ResponseEntity.ok().headers(headers).body(file);
    }
}