package com.sistema.examenes.entity;


import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
@Entity
@Getter
@Setter
@Table(name = "archivo")
public class Archivo_s implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_archivo")
    private Long id_archivo;

    @Column(name = "enlace")
    private String enlace;

    @Column(name = "nombre", length = 10000)
    private String nombre;

    @Column(name = "descripcion", length = 10000)
    private String descripcion;

    @Column(name = "visible")
    private boolean visible;

    @Column(name = "comentario")
    private String comentario;

    @Column(name = "id_modelo") // Columna para guardar el Modelo en el que se realiza el registro
    private Long id_modelo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_asignacion_evidencia")
    private Asignacion_Evidencia actividad;

    public Archivo_s() {
    }

    public Archivo_s(String enlace, String nombre, String descripcion, boolean visible) {
        this.enlace = enlace;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.visible = visible;
    }

    public Archivo_s(String enlace, String nombre, String descripcion, boolean visible, Asignacion_Evidencia actividad) {
        this.enlace = enlace;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.visible = visible;
        this.actividad = actividad;
    }
}

