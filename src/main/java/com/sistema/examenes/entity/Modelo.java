package com.sistema.examenes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Getter
@Setter
@Table(name = "modelo")
public class Modelo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_modelo")
    private Long id_modelo;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "fecha_inicio")
    private Date fecha_inicio;
    @Column(name = "fecha_fin")
    private Date fecha_fin;
    @Column(name = "fecha_final_act")
    private Date fecha_final_act;
    @Column(name = "visible")
    private boolean visible;

    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "modelo")
    @JsonIgnore
    private Set<Asignacion_Indicador> lista_criterios = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "modelo")
    @JsonIgnore
    private Set<Reporte> list_reporte = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "modelo")
    @JsonIgnore
    private Set<Ponderacion> ponderacion = new HashSet<>();

    public Modelo(Long id) {
        super();
        this.id_modelo = id;
    }

    public Modelo() {
    }
}
