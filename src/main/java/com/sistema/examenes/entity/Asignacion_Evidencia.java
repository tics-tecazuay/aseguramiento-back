package com.sistema.examenes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "asignacion_evidencia")
public class Asignacion_Evidencia implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asignacion_evidencia")
    private Long id_asignacion_evidencia;

    @Column(name = "archsubido")
    private boolean archsubido = false;

    @Column(name = "visible")
    private boolean visible;

    @Column(name = "id_modelo")
    private Long id_modelo;

    @Column(name = "estado")
    private String estado;

    @Column(name = "fecha_inicio")
    private Date fecha_inicio;

    @Column(name = "fecha_fin")
    private Date fecha_fin;

    @Column(name = "id_usuario_asignador")
    private Long id_usuario_asignador;

    @ManyToOne(fetch = FetchType.EAGER)
    private Evidencia evidencia;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "asignacion_evi")
    private Set<Historial_Asignacion_Evidencia> historial_asignacion = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "actividad")
    private Set<Archivo_s> lista_archivo = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "actividad")
    private Set<Observacion> lista_observaciones = new HashSet<>();
}
