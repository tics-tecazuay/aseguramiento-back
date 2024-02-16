package com.sistema.examenes.services;

import com.sistema.examenes.entity.Historial_Asignacion_Evidencia;
import com.sistema.examenes.entity.SeguimientoUsuario;
import com.sistema.examenes.entity.dto.SeguimientoUsuarioDTO;
import com.sistema.examenes.projection.HistorialAsignacionEvidenciaProjection;
import com.sistema.examenes.projection.ResponsableProjection;

import java.util.List;

public interface SeguimientoUsuario_Service extends GenericService<SeguimientoUsuario, Long> {

    public List<SeguimientoUsuarioDTO> listaSeguimientoUsuario();
}
