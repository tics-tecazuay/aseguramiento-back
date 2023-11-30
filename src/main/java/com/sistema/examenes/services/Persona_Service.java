package com.sistema.examenes.services;


import com.sistema.examenes.entity.Persona;
import org.springframework.data.jpa.repository.Query;

public interface Persona_Service extends GenericService<Persona, Long>{
    @Query(value = "SELECT * FROM persona p JOIN usuarios u ON p.id_persona = u.persona_id_persona WHERE u.username = :username", nativeQuery = true)
    public Persona obtenerPersona(String username);
    
    public Persona obtenerPersonaPorIdUsuario(Long id);
    public Persona findByCedula(String cedula);

}
