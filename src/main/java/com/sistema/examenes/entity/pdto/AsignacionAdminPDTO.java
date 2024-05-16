package com.sistema.examenes.entity.pdto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AsignacionAdminPDTO {

    //para eliminar
    private Long idAsignacion;

    //para crear
    private Long idModelo;
    private Long idCriterio;
    private Long idUsuario;
}
