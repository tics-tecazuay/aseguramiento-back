package com.sistema.examenes.mensajes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Archivosmensajes {
    private String mensajes;

    public Archivosmensajes(String mensajes) {
        this.mensajes = mensajes;
    }
}
