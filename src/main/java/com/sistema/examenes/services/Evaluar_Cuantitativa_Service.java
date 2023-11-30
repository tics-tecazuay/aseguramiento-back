package com.sistema.examenes.services;

import com.sistema.examenes.entity.Evaluar_Cuantitativa;

import java.util.List;

public interface Evaluar_Cuantitativa_Service extends GenericService<Evaluar_Cuantitativa, Long> {
    public List<Evaluar_Cuantitativa> listar();

    public List<Evaluar_Cuantitativa> listarEvaluarCuantitativaPorIndicador(Long id_indicador);

}
