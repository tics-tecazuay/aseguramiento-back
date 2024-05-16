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
@Table(name = "calificar_indicador")
public class Calificar_Indicador implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_calificar_indicador")
    private Long id_calificar_indicador;

    @Column(name = "valor_obtenido")
    private double valor_obtenido;

    @Column(name = "porc_obtenido")
    private double porc_obtenido;

    @Column(name = "porc_utilida_obtenida")
    private double porc_utilida_obtenida;

    @Column(name = "id_modelo") // Columna para guardar el Modelo en el que se realiza el registro
    private Long id_modelo;

    @Column(name = "visible")
    private boolean visible;

    @ManyToOne(fetch = FetchType.EAGER)
    private Indicador indicador;
}
