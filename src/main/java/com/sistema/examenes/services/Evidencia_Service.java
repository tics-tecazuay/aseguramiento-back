package com.sistema.examenes.services;

import com.sistema.examenes.entity.Evidencia;
import com.sistema.examenes.projection.*;

import java.util.List;

public interface Evidencia_Service extends GenericService<Evidencia, Long> {
    public List<Evidencia> listar();

    public List<EvidenciaEvProjection> evidenciaUsuario(String username, Long id_modelo);

    public List<EvidenciaEvProjection> evidenciaFiltraCriterio(String username, Long usuarioId, Long idModel);

    public List<Evidencia> listarEvidenciaAsigna(Long idUsuario) ;

    List<Evidencia> listarEvidenciaPorIndicador(Long id_indicador);
    List<Evidencia> evidenciacriterio(Long idcriterio);
    List<EvidenciasProjection> evidenciaAprobada(Long id_modelo);
    List<EvidenciaProjection> listararchivos(Long id_evidencia);
    List<EvidenciasProjection> evidenciaRechazada(Long id_modelo);
    EvidenciaCalProjection evidenciacal(Long id_evidencia, Long id_modelo);
    List<AsigEvidProjection> obtenerEvidenciasPorCriterio(Long idcriterio, Long id_modelo);
    List<AsigEvidProjection> listarEvidenciaAdmin(Long idUser, Long id_modelo);
    public List<EvidenciaProjection> evidenUsuario(String username, Long idModel);
    public List<EvidenciaProjection> evidenUserPendiente(String username, Long id_modelo);
    ActiDiagramaPieProjection porcentajeEstadosdeActividades (Long responsableId, Long id_modelo);
    ActiDiagramaPieProjection porcentajeEstadosdeEvidenciasGeneral(Long id_modelo);
    ValorObtenidoInd valoresObtenidosEvidencias (Long id_indicador);

}
