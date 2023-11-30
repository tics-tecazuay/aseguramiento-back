package com.sistema.examenes.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "reporte")
public class Reporte implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reporte")
    private Long id_reporte;
    @Column(name = "enlace")
    private String enlace;
    @Column(name = "fecha")
    private Date fecha;
    private boolean visible;

    @ManyToOne(fetch = FetchType.EAGER)
    private Modelo modelo ;
}
