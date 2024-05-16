package com.sistema.examenes.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "evaluar_cuantitativa")
public class Evaluar_Cuantitativa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evaluar_cuantitativa")
    private Long id_evaluar_cuantitativa;

    @Column(name = "valor")
    private double valor;

    @Column(name = "visible")
    private boolean visible;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "encabezado_evaluar_id_encabezado_evaluar", referencedColumnName = "id_encabezado_evaluar")
    private Encabezado_Evaluar encabezado_evaluar;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cuantitativa_id_cuantitativa", referencedColumnName = "id_cuantitativa")
    private Cuantitativa cuantitativa;
}
