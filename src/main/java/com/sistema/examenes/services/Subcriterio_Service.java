package com.sistema.examenes.services;

import com.sistema.examenes.entity.Subcriterio;
import com.sistema.examenes.projection.*;

import java.util.List;

public interface Subcriterio_Service extends GenericService<Subcriterio, Long> {

    public List<Subcriterio> listar();

    public List<Subcriterio> listarPorCriterio(Long id_criterio);

    public List<SubcriterioIndicadoresProjection> obtenerDatosSubcriterios(Long id_criterio,Long id_modelo);
    public List<SubcriterioIndicadoresProjectionFull> obtenerDatosSubcriteriosFull();
    List<SubcriterioIndicadoresProjection> obtenerSubcriterios(Long id_criterio,Long id_modelo);
    List<SubcriterioPorcProjection> subcriteriosporCriterio(String cri_nombre,Long id_modelo);
}
