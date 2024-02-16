package com.sistema.examenes.entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table (name = "historial_asignacion_evidencia")

public class Historial_Asignacion_Evidencia implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_haev")
    private Long id_haev;

    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @Column(name = "visible")
    private Boolean visible;

    @PrePersist
    protected void onCreate() {
        fecha = new Date();
    }

    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario_asignador;

    @ManyToOne(fetch = FetchType.EAGER)
    private Asignacion_Evidencia asignacion_evi;

}
