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
@Table(name = "encabezado_evaluar")
public class  Encabezado_Evaluar implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_encabezado_evaluar")
    private Long id_encabezado_evaluar;
    //Columna para el eliminado logico no borrar
    @Column(name = "visible")
    private boolean visible;


    @ManyToOne(fetch = FetchType.EAGER)
    private Formula formula;

    @ManyToOne(fetch = FetchType.EAGER)
    private Indicador indicador;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "encabezado_evaluar")
    @JsonIgnore
    private Set<Evaluar_Cuantitativa> lista_cuantitativa = new HashSet<>();
}
