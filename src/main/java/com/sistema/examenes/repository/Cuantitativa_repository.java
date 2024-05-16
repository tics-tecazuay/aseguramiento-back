package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Criterio;
import com.sistema.examenes.entity.Cuantitativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Cuantitativa_repository extends JpaRepository<Cuantitativa, Long> {
    @Query("SELECT c FROM Cuantitativa c WHERE c.visible = true ORDER BY c.id_cuantitativa ASC")
    List<Cuantitativa> listarCuantitativa();
}
