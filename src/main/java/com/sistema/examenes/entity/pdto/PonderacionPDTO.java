package com.sistema.examenes.entity.pdto;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PonderacionPDTO {

    private LocalDate fecha;
    private Double peso;
    private Double porcentajeobtenido;
    private Double porcentajeutilidad;
    private Double valorobtenido;
    private Long idindicador;
    private Long idmodelo;
    private Long contador;


}
