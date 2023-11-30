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
@Table(name = "formula")
public class Formula implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_formula")
    private Long id_formula;
    @Column(name = "formula")
    private String formula;
    @Column(name = "descripcion", length = 10000 )
    private String descripcion;
    //Columna para el eliminado logico no borrar
    @Column(name = "visible")
    private boolean visible;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "formula")
    @JsonIgnore
    private Set<Encabezado_Evaluar> lista_enc_eva = new HashSet<>();
}
