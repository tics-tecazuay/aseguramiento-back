package com.sistema.examenes.entity.dto;

import com.sistema.examenes.entity.Evidencia;
import com.sistema.examenes.entity.Usuario;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Asignacion_EvidenciaDTO {
    private Long  id_asignacion_evidencia;
    private Date  fecha_inicio;
    private Date  fecha_fin;
    private Long id_evidencia;
    private String descripcion_evidencia;
    private String estado_evidencia;
    private Long id_usuario;
    private String username;
    private Long id_observacion;
    private String observacion;
    private int countarchivos;
    private String comentario_archivo;
}
