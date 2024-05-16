package com.sistema.examenes.services;

import com.sistema.examenes.entity.Asignacion_Evidencia;
import com.sistema.examenes.entity.Evidencia;
import com.sistema.examenes.projection.*;
import com.sistema.examenes.repository.Evidencia_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Evidencia_ServiceImpl extends GenericServiceImpl<Evidencia, Long> implements Evidencia_Service {
    @Autowired
    private Evidencia_repository repository;

    @Override
    public CrudRepository<Evidencia, Long> getDao() {
        return repository;
    }

    @Override
    public List<Evidencia> listar() {
        return repository.listarEvidencia();
    }

    @Override
    public List<EvidenciaEvProjection> evidenciaUsuario(String username, Long id_modelo) {
        return repository.evidenciaUsuario(username, id_modelo);
    }

    @Override
    public List<EvidenciaEvProjection> evidenciaFiltraCriterio(String username, Long usuarioId, Long idModel) {
        return repository.evidenciaFiltraCriterio(username , usuarioId, idModel );
    }

    @Override
    public List<Evidencia> listarEvidenciaAsigna(Long idUsuario) {
        return repository.listarEvidenciaAsigna(idUsuario);
    }

    @Override
    public List<Evidencia> listarEvidenciaPorIndicador(Long id_indicador) {
        return repository.listarEvidenciaPorIndicador(id_indicador);

    }

    @Override
    public List<Evidencia> evidenciacriterio(Long idcriterio) {
        return repository.evidenciacriterio(idcriterio);
    }

    @Override
    public List<EvidenciasProjection> evidenciaAprobada(Long id_modelo) {
        return repository.evidenciaAprobada(id_modelo);
    }

    @Override
    public List<EvidenciasProjection> evidenciaRechazada(Long id_modelo) {
        return repository.evidenciaRechazada(id_modelo);
    }
    @Override
    public List<EvidenciaProjection> listararchivos(Long id_evidencia) {
        return repository.listararchivos(id_evidencia);
    }

    @Override
    public EvidenciaCalProjection evidenciacal(Long id_evidencia, Long id_modelo) {
        return repository.evidenciacal(id_evidencia, id_modelo);
    }

    @Override
    public List<AsigEvidProjection> obtenerEvidenciasPorCriterio(Long idcriterio, Long id_modelo) {
        return repository.obtenerEvidenciasPorCriterio(idcriterio, id_modelo);
    }

    @Override
    public List<AsigEvidProjection> listarEvidenciaAdmin(Long idUser,Long id_modelo) {
        return repository.listarEvidenciaAdmin(idUser,id_modelo);
    }

    @Override
    public List<EvidenciaProjection> evidenUsuario(String username, Long idModel) {
        return repository.evidenUsuario(username, idModel);
    }

    @Override
    public List<EvidenciaProjection> evidenUserPendiente(String username, Long id_modelo) { return repository.evidenUserPendiente(username,id_modelo);
    }

    @Override
    public ActiDiagramaPieProjection porcentajeEstadosdeActividades(Long responsableId, Long id_modelo) {
        return repository.porcentajeEstadosdeActividadesByResponsableId(responsableId, id_modelo);
    }
    @Override
    public ActiDiagramaPieProjection porcentajeEstadosdeEvidenciasGeneral(Long id_modelo) {
        return repository.porcentajeEstadosdeEvidenciasGeneral(id_modelo);
    }

    @Override
    public ValorObtenidoInd valoresObtenidosEvidencias(Long id_indicador) {
        return repository.obtenerTotalValoresEvidPorIndicador(id_indicador);
    }

}
