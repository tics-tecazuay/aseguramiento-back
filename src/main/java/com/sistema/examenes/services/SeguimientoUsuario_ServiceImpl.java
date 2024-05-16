package com.sistema.examenes.services;

import com.sistema.examenes.entity.Historial_Asignacion_Evidencia;
import com.sistema.examenes.entity.SeguimientoUsuario;
import com.sistema.examenes.entity.dto.SeguimientoUsuarioDTO;
import com.sistema.examenes.projection.HistorialAsignacionEvidenciaProjection;
import com.sistema.examenes.repository.Historial_Asignacion_Evidencia_repository;
import com.sistema.examenes.repository.SeguimientoUsuario_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SeguimientoUsuario_ServiceImpl extends GenericServiceImpl<SeguimientoUsuario, Long> implements SeguimientoUsuario_Service {

    @Autowired
    private SeguimientoUsuario_repository repository;
    @Override
    public CrudRepository<SeguimientoUsuario, Long> getDao() {
        return repository;
    }

    @Override
    public List<SeguimientoUsuarioDTO> listaSeguimientoUsuario() {
        return repository.listaSeguimientoUsuario();
    }


}
