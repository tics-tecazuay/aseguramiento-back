package com.sistema.examenes.services;


import com.sistema.examenes.entity.Archivo_s;
import com.sistema.examenes.entity.Evidencia;
import com.sistema.examenes.projection.ArchivoAdmSupProjection;
import com.sistema.examenes.projection.ArchivoProjection;
import com.sistema.examenes.projection.ArchivoResProjection;

import java.util.List;

public interface Archivo_Service extends GenericService<Archivo_s, Long>{
    public List<Archivo_s> listar() ;
    public List<ArchivoResProjection> listararchivouser(String username, Long id_asignacion_evi, Long idModel);
    public List<ArchivoAdmSupProjection> listararchivoActividad(Long idActividad, Long idModel);
    public List<ArchivoProjection> listararchi() ;
    List<Archivo_s> archivoporindicador(Long id_criterio,Long id_modelo,Long id_indicador);
}
