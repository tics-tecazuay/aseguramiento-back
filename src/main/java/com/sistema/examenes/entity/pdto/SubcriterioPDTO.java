package com.sistema.examenes.entity.pdto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SubcriterioPDTO {

    private String nombre;
    private String descripcion;
    private Long id_criterio;
}
