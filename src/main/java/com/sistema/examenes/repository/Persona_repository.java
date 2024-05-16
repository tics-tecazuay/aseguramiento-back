package com.sistema.examenes.repository;


import com.sistema.examenes.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Persona_repository extends JpaRepository<Persona, Long> {
    @Query("SELECT p FROM Persona p JOIN p.listausuarios u WHERE u.username = :username")
    Persona obtenerPersona(@Param("username") String username);

    @Query( "SELECT p FROM Persona p JOIN p.listausuarios u  WHERE u.id = :id")
    Persona obtenerPersonaUsuario(@Param("id") Long id);

    Persona findByCedula(String cedula);

    @Query(value = "SELECT p FROM Persona p JOIN Usuario u ON p.id_persona = u.persona.id_persona WHERE u.visible=true")
    List<Persona> findAllPersonasByUsuarios();
}
