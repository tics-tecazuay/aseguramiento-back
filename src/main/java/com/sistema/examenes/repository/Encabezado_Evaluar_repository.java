package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Criterio;
import com.sistema.examenes.entity.Encabezado_Evaluar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Encabezado_Evaluar_repository extends JpaRepository<Encabezado_Evaluar, Long> {
    @Query(value = "SELECT * from encabezado_evaluar where visible =true",nativeQuery = true)
    List<Encabezado_Evaluar> listarEncabezadoEvaluar();
}
