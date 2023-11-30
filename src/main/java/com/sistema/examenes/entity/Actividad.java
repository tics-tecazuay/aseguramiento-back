package com.sistema.examenes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "actividad")
public class Actividad implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_actividad")
    private Long id_actividad;
    @Column(name = "descripcion", length = 10000 )
    private String descripcion;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "estado")
    private String estado;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_inicio")
    private Date fecha_inicio;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_fin ")
    private Date fecha_fin;
    //Columna para el eliminado logico no borrar
    @Column(name = "visible")
    private Boolean visible;

    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;

   @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_evidencia")
    private Evidencia evidencia;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "actividad")
    @JsonIgnore
    private Set<Observacion> lista_observaciones = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "actividad")
    @JsonIgnore
    private Set<Archivo_s> lista_archivo = new HashSet<>();


}
