package com.sistema.examenes.services;

import com.sistema.examenes.entity.Historial_Asignacion_Evidencia;
import com.sistema.examenes.projection.HistorialAsignacionEvidenciaProjection;

import java.util.List;

public interface Historial_Asignacion_Evidencia_Service extends GenericService<Historial_Asignacion_Evidencia, Long> {
    public List<HistorialAsignacionEvidenciaProjection> listarHistorial (Long id_criterio, String veri, Long idModel) ;

}
