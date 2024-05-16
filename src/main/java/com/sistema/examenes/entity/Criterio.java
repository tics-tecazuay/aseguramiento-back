package com.sistema.examenes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "criterio")
public class Criterio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_criterio")
    private Long id_criterio;

    @Column(name = "descripcion", length = 10000)
    private String descripcion;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "visible")
    private boolean visible;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "criterio")
    private Set<Subcriterio> lista_subcriterios = new HashSet<>();

    public Criterio() {
    }

    public Criterio(Long id) {
        super();
        this.id_criterio = id;
    }
}
