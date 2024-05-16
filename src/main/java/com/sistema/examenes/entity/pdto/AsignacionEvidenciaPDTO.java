package com.sistema.examenes.entity.pdto;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AsignacionEvidenciaPDTO {

    Long id_modelo;
    Date fecha_inicio;
    Date fecha_fin;
    Long id_usuario_asignador;
    Long evidencia_id;
    Long usuario_id;
}
