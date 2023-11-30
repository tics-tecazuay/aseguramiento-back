package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Criterio;
import com.sistema.examenes.entity.Cualitativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Cualitativa_repository extends JpaRepository<Cualitativa, Long> {
    @Query(value = "SELECT * from cualitativa where visible =true",nativeQuery = true)
    List<Cualitativa> listarCualitativa();

}
