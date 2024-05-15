package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Criterio;
import com.sistema.examenes.entity.Observacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Observacion_repository extends JpaRepository<Observacion, Long> {

    @Query("SELECT ob FROM Observacion ob WHERE ob.visible =true")
    List<Observacion> listarObservacion();
    @Query("SELECT ob FROM Observacion ob " +
            "WHERE ob.actividad.id_asignacion_evidencia =:id_asignacion_evidencia " +
            "AND ob.visible = true " +
            "AND (ob.observacion != 'Ninguna' OR ob.id_observacion = (SELECT MAX(obs.id_observacion) " +
            "FROM Observacion obs WHERE obs.observacion = 'Ninguna' AND obs.actividad.id_asignacion_evidencia =:id_asignacion_evidencia " +
            "AND obs.visible = true))")
    List<Observacion> observacionactividad(Long id_asignacion_evidencia);
    @Query("SELECT o from Observacion o " +
            "JOIN FETCH o.usuario u " +
            "JOIN FETCH o.actividad ac  " +
            "WHERE o.visible =true and u.username=:user " +
            "and ac.id_asignacion_evidencia=:id")
    List<Observacion> observacionUsuario(String user, Long id);

    @Modifying
    @Query(value = "DELETE FROM observacion WHERE id_observacion=:id", nativeQuery = true)
    void borrar(Long id);
    
    @Query("SELECT o FROM Observacion o WHERE o.actividad.id_asignacion_evidencia=:idact AND o.visible=true")
    List<Observacion> observacionActividad(Long idact);
    
}
