package com.sistema.examenes.fenix.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "docentesfenix")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Docentesfenix {
    @Id
    @Column(name = "cedula", nullable = false, updatable = false)
    private String cedula;
    private String primer_nombre;
    private String segundo_nombre;
    private String primer_apellido;
    private String segundo_apellido;
    private String correo;
    private String direccion;
    private String celular;
}
