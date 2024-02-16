package com.sistema.examenes.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "seguimiento_usuario")
public class SeguimientoUsuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_seguimiento")
    private Long id_seguimiento;
    @Column(name = "descripcion", length = 10000 )
    private String descripcion;
    @Column(name = "fecha")
    private Date fecha;

    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;
}
