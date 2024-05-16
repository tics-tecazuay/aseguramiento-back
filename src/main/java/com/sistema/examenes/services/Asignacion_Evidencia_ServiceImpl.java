package com.sistema.examenes.services;

import com.sistema.examenes.entity.Asignacion_Evidencia;
import com.sistema.examenes.projection.*;
import com.sistema.examenes.entity.dto.Asignacion_EvidenciaDTO;
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
    public List<AsignaProjection> listarAsigEvidencia(Long id_modelo) {
        return repository.listarAsigEvidencia(id_modelo);
    }

    @Override
    public List<AsignaProjection> listarAsigEvidenciaPorUsuario(Long usuarioId) {
        return repository.listarAsigEvidenciaPorUsuario(usuarioId);
    }

    @Override
    public Asignacion_Evidencia fechaactividades(Long id_evidencia, Long id_modelo) {
        return repository.fechaactividades(id_evidencia, id_modelo);
    }

    @Override
    public List<ActiCalendarProjection> listarActiCalendarbyuser(Long usuario, Long id_modelo) {
        return repository.findActCalendarByUsuarioId(usuario, id_modelo);
    }

    @Override
    public List<AsignacionEvidenciaCalendarProjection> listar(Long id_modelo) {
        return repository.listarAsignacionEvidencia(id_modelo);
    }

    @Override
    public List<Asignacion_EvidenciaDTO> listarAsigEviUser(String username, Long id_evidencia, Long idModel) {
        List<Object[]> resultados = repository.listarAsigEviUser(username, id_evidencia, idModel);
        List<Asignacion_EvidenciaDTO> asignaciones = new ArrayList<>();

        for (Object[] fila : resultados) {
            Asignacion_EvidenciaDTO ae = new Asignacion_EvidenciaDTO();

            ae.setId_asignacion_evidencia(((BigInteger) fila[0]).longValue());
            ae.setDescripcion_evidencia((String) fila[1]);
            ae.setFecha_inicio((Date) fila[2]);
            ae.setFecha_fin((Date) fila[3]);
            ae.setEstado_evidencia((String) fila[4]);
            ae.setId_evidencia(((BigInteger) fila[5]).longValue());
            ae.setObservacion((String) fila[6]);
            ae.setCountarchivos(((BigInteger) fila[7]).intValue());
            ae.setComentario_archivo((String) fila[8]);
            asignaciones.add(ae);
        }
        return asignaciones;
    }

    @Override
    public List<Asignacion_Evidencia> listarporusuario(String username) {
        return repository.listarporusuario(username);
    }
    @Override
    public List<Asignacion_Evidencia>listarporEvidencia(Long idEvidencia, Long id_modelo ) {
        return repository.listarporEvidencia(idEvidencia, id_modelo);
    }

    @Override
    public List<Asignacion_Evidencia> listarporUsuarioxd(Long userId) {
        return repository.listarporUsuarioxd(userId);
    }

    @Override
    public List<EvidenciaReApPeAtrProjection> listarEvideByEstado(String estado, Long id_modelo) {
        return repository.listarEvideByEstado(estado,id_modelo);
    }

    @Override
    public List<EvidenciaReApPeAtrProjection> listarEvideByEstadoAdm(String estado, Long id_admin, Long idModel) {
        return repository.listarEvideByEstadoAdm(estado,id_admin, idModel);
    }
    @Override
    public List<ActivProyection> listarByActividad() {
        return repository.listarByActividad();
    }

    @Override
    public int countArchivosByIdAsigEv(Long idAsignacionEv) {
        return repository.countArchivosByIdAsigEv(idAsignacionEv);
    }

}
