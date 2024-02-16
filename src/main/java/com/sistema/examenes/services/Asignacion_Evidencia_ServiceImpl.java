package com.sistema.examenes.services;

import com.sistema.examenes.entity.Actividad;
import com.sistema.examenes.entity.Asignacion_Evidencia;
import com.sistema.examenes.projection.ActiCalendarProjection;
import com.sistema.examenes.entity.dto.Asignacion_EvidenciaDTO;
import com.sistema.examenes.projection.AsignaProjection;
import com.sistema.examenes.projection.AsignacionEvidenciaProyeccion;
import com.sistema.examenes.projection.EvidenciaReApPeAtrProjection;
import com.sistema.examenes.repository.Asignacion_Evidencia_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class Asignacion_Evidencia_ServiceImpl extends GenericServiceImpl<Asignacion_Evidencia, Long> implements Asignacion_Evidencia_Service {
    @Autowired
    private Asignacion_Evidencia_repository repository;

    @Override
    public CrudRepository<Asignacion_Evidencia, Long> getDao() {
        return repository;
    }

    @Override
    public List<Asignacion_Evidencia> listarporUsuario(String usuario) {
        return repository.listarporAsignacionUsuario(usuario);
    }

    @Override
    public List<AsignacionEvidenciaProyeccion> listarAsignacionEvidenciaProyeccion() {
        return repository.listarAsignacionEvidenciaProyeccion();
    }

    @Override
    public Boolean verificarAsignacionUsuario(Long id_usuario, Long id_evidencia, Long id_modelo) {
        return repository.verificarAsignacionUsuario(id_usuario, id_evidencia, id_modelo);
    }

    @Override
    public List<AsignaProjection> listarAsigEvidencia() {
        return repository.listarAsigEvidencia();
    }

    @Override
    public Asignacion_Evidencia fechaactividades(Long id_evidencia, Long id_modelo) {
        return repository.fechaactividades(id_evidencia, id_modelo);
    }

    @Override
    public List<ActiCalendarProjection> listarActiCalendarbyuser(Long usuario) {
        return repository.findActCalendarByUsuarioId(usuario);
    }

    @Override
    public List<Asignacion_Evidencia> listar() {
        return repository.listarAsignacionEvidencia();
    }

    @Override
    public List<Asignacion_EvidenciaDTO> listarAsigEviUser(String username, Long id_evidencia) {
        List<Object[]> resultados = repository.listarAsigEviUser(username, id_evidencia);
        List<Asignacion_EvidenciaDTO> asignaciones = new ArrayList<>();

        for (Object[] fila : resultados) {
            Asignacion_EvidenciaDTO ae = new Asignacion_EvidenciaDTO();

            ae.setId_asignacion_evidencia(((BigInteger) fila[0]).longValue());
            ae.setDescripcion_evidencia((String) fila[1]);
            ae.setFecha_inicio((Date) fila[2]);
            ae.setFecha_fin((Date) fila[3]);
            ae.setEstado_evidencia((String) fila[4]);
            ae.setId_evidencia(((BigInteger) fila[5]).longValue());
            asignaciones.add(ae);
        }
        return asignaciones;
    }

    @Override
    public List<Asignacion_Evidencia> listarporusuario(String username) {
        return repository.listarporusuario(username);
    }
    @Override
    public List<Asignacion_Evidencia>listarporEvidencia(Long idEvidencia ) {
        return repository.listarporEvidencia(idEvidencia);

    }

    @Override
    public List<Asignacion_Evidencia> listarporUsuarioxd(Long userId) {
        return repository.listarporUsuarioxd(userId);
    }

    @Override
    public List<EvidenciaReApPeAtrProjection> listaEvidRe() {
        return repository.listarEvideRechazadas();
    }

    @Override
    public List<EvidenciaReApPeAtrProjection> listaEvidAp() {
        return repository.listarEvideAprobadas();
    }

    @Override
    public List<EvidenciaReApPeAtrProjection> listaEvidPen() {
        return repository.listarEvidePendientes();
    }

}
