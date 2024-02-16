package com.sistema.examenes.entity.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SeguimientoUsuarioDTO {
    private Long  id_seguimiento;
    private String username;
    private String rolnombre;
    private String cedula;
    private String usuario;
    private String descripcion;
    private Date fecha;
}
