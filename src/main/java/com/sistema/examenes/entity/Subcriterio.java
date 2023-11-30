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
@Table(name = "subcriterio")
public class Subcriterio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_subcriterio")
    private Long id_subcriterio;
    @Column(name = "descripcion", length = 10000)
    private String descripcion;
    @Column(name = "nombre")
    private String nombre;
    // Columna para el eliminado logico no borrar
    @Column(name = "visible")
    private boolean visible;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_criterio")
    private Criterio criterio;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "subcriterio")
    @JsonIgnore
    private Set<Indicador> lista_indicadores = new HashSet<>();
}
