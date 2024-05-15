package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Criterio;
import com.sistema.examenes.entity.Detalle_Evaluacion;
import com.sistema.examenes.projection.AsignacionProjection;
import com.sistema.examenes.projection.DetalleEvaluacionProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Detalle_Evaluacion_repository extends JpaRepository<Detalle_Evaluacion, Long> {

    //el de abajo no se utiliza
    /*@Query(value = "SELECT * from detalle_evaluacion where visible =true",nativeQuery = true)
    List<Detalle_Evaluacion> listarDetalleEvaluacion();*/
    @Query("SELECT d FROM Detalle_Evaluacion d " +
            "WHERE d.evidencia.id_evidencia = :id_evidencia " +
            "AND d.id_modelo = :id_modelo " +
            "AND d.fecha = (SELECT MAX(d2.fecha) FROM Detalle_Evaluacion d2 " +
            "WHERE d2.evidencia.id_evidencia = :id_evidencia " +
            "AND d2.id_modelo = :id_modelo)")
    List<Detalle_Evaluacion> listarbservaciones(Long id_evidencia, Long id_modelo);

    @Query("SELECT d FROM Detalle_Evaluacion d " +
            "WHERE d.visible = true " +
            "AND d.evidencia.id_evidencia = :idEvidencia " +
            "AND d.id_modelo= :id_modelo " +
            "ORDER BY d.fecha DESC")
    List<Detalle_Evaluacion> listarDetalleEvaluacion(Long idEvidencia, Long id_modelo);

    @Query(value = "SELECT d.observacion as comentario, e.estado as estado, d.fecha as fecha, " +
            "per.primer_nombre||' '||per.primer_apellido as usuarioevaluador " +
            "FROM Detalle_Evaluacion d " +
            "JOIN evidencia e ON d.evidencia_id_evidencia = e.id_evidencia " +
            "JOIN usuarios u ON d.usuario_id = u.id " +
            "JOIN persona per ON u.persona_id_persona = per.id_persona " +
            "WHERE d.visible = true " +
            "AND d.evidencia_id_evidencia =:idevidencia  AND d.id_modelo = :id_modelo " +
            "ORDER BY d.fecha DESC", nativeQuery = true)
    List<DetalleEvaluacionProjection> listarDetallesEvalu(Long idevidencia, Long id_modelo);

    @Query("SELECT CASE WHEN COUNT(d.id_detalle_evaluacion) > 0 THEN true ELSE false END " +
            "FROM Detalle_Evaluacion d " +
            "WHERE d.evidencia.id_evidencia = :id_evidencia " +
            "AND d.usuario.id = :id " +
            "AND d.id_modelo = :id_modelo")
    Boolean existeeva(Long id_evidencia, Long id, Long id_modelo);

    @Query("SELECT d.id_detalle_evaluacion " +
            "FROM Detalle_Evaluacion d " +
            "WHERE d.evidencia.id_evidencia = :id_evidencia " +
            "AND d.usuario.id = :id " +
            "AND d.id_modelo = :id_modelo " +
            "AND d.estado = true")
    Long iddetalle(Long id_evidencia, Long id, Long id_modelo);

}
