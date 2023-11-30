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
@Table(name = "indicador")
public class Indicador implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_indicador")
    private Long id_indicador;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion", length = 10000 )
    private String descripcion;
    @Column(name = "peso")
    private double peso;
    @Column(name = "estandar")
    private double estandar;
    @Column(name = "valor_obtenido")
    private double valor_obtenido;
    @Column(name = "porc_obtenido")
    private double porc_obtenido;
    @Column(name = "porc_utilida_obtenida")
    private double porc_utilida_obtenida;
    @Column(name = "tipo")
    private String tipo;
    //
    @Column(name = "visible")
    private boolean visible;
    @ManyToOne(fetch = FetchType.EAGER)
    private Subcriterio subcriterio;


    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "indicador")
    @JsonIgnore
    private Set<Evaluar_Cualitativa> lista_eva_cual = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "indicador")
    @JsonIgnore
    private Set<Evidencia> lista_evidencia = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "indicador")
    @JsonIgnore
    private Set<Encabezado_Evaluar> lista_enc_eva = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "indicador")
    @JsonIgnore
    private Set<Asignacion_Indicador> lista_asignacion = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "indicador")
    @JsonIgnore
    private Set<Ponderacion> lista_ponderacion = new HashSet<>();


}
