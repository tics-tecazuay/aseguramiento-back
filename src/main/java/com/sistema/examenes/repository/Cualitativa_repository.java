package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Criterio;
import com.sistema.examenes.entity.Cualitativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Cualitativa_repository extends JpaRepository<Cualitativa, Long> {
    @Query("SELECT c FROM Cualitativa c WHERE c.visible = true")
    List<Cualitativa> listarCualitativa();

}
