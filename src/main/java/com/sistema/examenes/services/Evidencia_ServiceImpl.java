package com.sistema.examenes.services;

import com.sistema.examenes.entity.Evidencia;
import com.sistema.examenes.projection.AsigEvidProjection;
import com.sistema.examenes.projection.EvidenciaCalProjection;
import com.sistema.examenes.projection.EvidenciaProjection;
import com.sistema.examenes.projection.EvidenciasProjection;
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
    public List<Evidencia> evidenciaUsuario(String username) {
        return repository.evidenciaUsuario(username);
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
    public EvidenciaCalProjection evidenciacal(Long id_evidencia, Long id_modelo) {
        return repository.evidenciacal(id_evidencia, id_modelo);
    }

    @Override
    public List<AsigEvidProjection> evidenciatab(Long idcriterio) {
        return repository.evidenciatab(idcriterio);
    }

    @Override
    public List<AsigEvidProjection> listarEvidenciaAdmin(Long idUser) {
        return repository.listarEvidenciaAdmin(idUser);
    }

    @Override
    public List<EvidenciaProjection> evidenUsuario(String username) {
        return repository.evidenUsuario(username);
    }
}
