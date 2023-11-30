package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Criterio;
import com.sistema.examenes.entity.Cuantitativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Cuantitativa_repository extends JpaRepository<Cuantitativa, Long> {
    @Query(value = "SELECT * from cuantitativa where visible =true",nativeQuery = true)
    List<Cuantitativa> listarCuantitativa();
}
