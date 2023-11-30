package com.sistema.examenes.excepciones;

import com.sistema.examenes.mensajes.Archivosmensajes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
public class Archivoloadexception {
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<Archivosmensajes>maxexception(MaxUploadSizeExceededException exc){
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new Archivosmensajes("EL tamaño del archivo excede al maximo "));
    }
}
