package com.sistema.examenes.services;

import com.sistema.examenes.entity.Criterio;
import com.sistema.examenes.projection.*;

import java.util.List;

public interface Criterio_Service extends GenericService<Criterio, Long> {

    public List<Criterio> listar();

    public List<Criterio> obtenerCriterios();
    public List<CriteProjection> ObtenerCriterioUltimoModelo(Long id_modelo);

    public List<Criterio> obtenerCriterioModelo();

    public List<Criterio> obtenerCriterioIdModelo(Long id);

    public List<Criterio> obtenerCriteriosUltimoModelo(Long id_modelo);

    // listarCriterioPorIndicador de repositorio
    public List<Criterio> listarCriterioPorIndicador(Long id_indicador);

    public List<Criterio> obtenerCriteriosPorUsuarioYModelo(Long usuarioId, Long modeloId);

    //Lista de criterios para el flujo
    public List<CriterioSubcriteriosProjection> obtenerDatosCriterios(Long id_modelo);
    public List<ValoresProjection>  listarvalores(Long id_modelo);
    public List<ValoresProjection>  listarvaloresmovil();
    List<ValoresProjection> valoresporcriterio(Long id_modelo,String nombre);
    List<ValoresProjection> listarvaladmin(Long id_modelo,Long id);
    List<ValoresProjection> listarvaladminmovil(Long id);
    IdCriterioProjection idcriterio(String nombre);
    List<CriteProjection> actividadesusuario(Long id, Long id_modelo);
    List<ValoresProjection> listarvalresp(Long id_modelo,Long id);
    CorreoProjection getCorreo(Long id_modelo, Long id_evidencia);
    List<CriterioAdm> listarCriterioAdms (Long id_modelo,Long userId);
    List<CriteRespProjection> criterioporresp(Long id, Long id_modelo);
    List<CriteProjection> listarcriusers(Long id_usuariorol, Long id_modelo);
    List<CriterioAdm> criteriosadmultimomodelo (Long userId);
    List<CriterioPorcProjection> criteriosporModelo(Long id_modelo);
}
