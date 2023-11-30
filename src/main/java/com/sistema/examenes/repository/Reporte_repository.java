package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Criterio;
import com.sistema.examenes.entity.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Reporte_repository extends JpaRepository<Reporte, Long> {
    @Query(value = "SELECT * from reporte where visible =true",nativeQuery = true)
    List<Reporte> listarReporte();
}
