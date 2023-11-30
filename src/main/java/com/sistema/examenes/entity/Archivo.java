package com.sistema.examenes.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
@Getter
@Setter

public class Archivo {
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "url")
    private String url;

    public Archivo(String nombre, String url) {
        this.nombre = nombre;
        this.url = url;
    }
}
