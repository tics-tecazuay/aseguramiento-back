package com.sistema.examenes.services;

import com.sistema.examenes.entity.Historial_Asignacion_Evidencia;
import com.sistema.examenes.projection.HistorialAsignacionEvidenciaProjection;
import com.sistema.examenes.repository.Historial_Asignacion_Evidencia_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class Historial_Asignacion_Evidencia_ServiceImpl extends GenericServiceImpl<Historial_Asignacion_Evidencia, Long> implements Historial_Asignacion_Evidencia_Service {

    @Autowired
    private Historial_Asignacion_Evidencia_repository repository;
    @Override
    public CrudRepository<Historial_Asignacion_Evidencia, Long> getDao() {
        return repository;
    }
    @Override
    public List<HistorialAsignacionEvidenciaProjection> listarHistorial(Long id_usuario_asignador, Long id_criterio, String veri) {
        return repository.obtenerHistorialPorUsuario(id_usuario_asignador, id_criterio, veri );
    }

}
