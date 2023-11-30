package com.sistema.examenes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "cuantitativa")
public class Cuantitativa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cuantitativa")
    private Long id_cuantitativa;
    @Column(name = "descripcion", length = 10000 )
    private String descripcion;
    @Column(name = "abreviatura")
    private String abreviatura;
    //Columna para el eliminado logico no borrar
    @Column(name = "visible")
    private boolean visible;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "cuantitativa")
    @JsonIgnore
    private Set<Evaluar_Cuantitativa> lista_eva_indicador = new HashSet<>();
}
