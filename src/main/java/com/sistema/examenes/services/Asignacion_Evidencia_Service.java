package com.sistema.examenes.services;

import com.sistema.examenes.entity.Asignacion_Evidencia;
import com.sistema.examenes.projection.*;
import com.sistema.examenes.entity.dto.Asignacion_EvidenciaDTO;

import java.util.List;

public interface Asignacion_Evidencia_Service extends GenericService<Asignacion_Evidencia, Long>{
    public List<AsignacionEvidenciaCalendarProjection> listar(Long id_modelo) ;
    public List<Asignacion_Evidencia> listarporUsuario (String usuario  ) ;

    List<AsignacionEvidenciaProyeccion> listarAsignacionEvidenciaProyeccion();
    Boolean verificarAsignacionUsuario(Long id_usuario, Long id_evidencia,Long id_modelo);
    List<AsignaProjection> listarAsigEvidencia(Long id_modelo);

    List<AsignaProjection>listarAsigEvidenciaPorUsuario(Long usuarioId);
    Asignacion_Evidencia fechaactividades(Long id_evidencia,Long id_modelo);

    List<ActiCalendarProjection> listarActiCalendarbyuser (Long usuario, Long id_modelo);

    List<Asignacion_EvidenciaDTO>listarAsigEviUser(String username, Long id_evidencia, Long idModel);
    public List<Asignacion_Evidencia> listarporusuario(String username);
    public List<Asignacion_Evidencia>listarporEvidencia(Long idEvidencia,Long id_modelo );
    public List<Asignacion_Evidencia> listarporUsuarioxd(Long userId);
    public List<EvidenciaReApPeAtrProjection>listarEvideByEstado(String estado,Long id_modelo);
    public List<EvidenciaReApPeAtrProjection>listarEvideByEstadoAdm(String estado, Long id_admin, Long idModel);

    public List<ActivProyection>listarByActividad();
    int countArchivosByIdAsigEv(Long idAsignacionEv);
}
