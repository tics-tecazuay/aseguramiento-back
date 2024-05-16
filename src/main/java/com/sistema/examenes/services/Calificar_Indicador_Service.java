package com.sistema.examenes.services;

import com.sistema.examenes.entity.Calificar_Indicador;

public interface Calificar_Indicador_Service extends GenericService<Calificar_Indicador, Long> {
     Calificar_Indicador obtenerCalificacionPorIdIndicadorIdModelo(Long id_indicador, Long id_modelo) ;
}
