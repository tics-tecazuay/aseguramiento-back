package com.sistema.examenes.services;

import com.sistema.examenes.entity.Criterio;
import com.sistema.examenes.entity.Indicador;
import com.sistema.examenes.projection.*;
import com.sistema.examenes.repository.Indicador_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Indicador_ServiceImpl extends GenericServiceImpl<Indicador, Long> implements Indicador_Service {
    @Autowired
    private Indicador_repository repository;

    @Override
    public CrudRepository<Indicador, Long> getDao() {

        return repository;
    }

    @Override
    public List<Indicador> listar() {
        return repository.listarIndicador();
    }
    @Override
    public List<PonderacionProjection> listarIndicadoresModelo(Long id_modelo) {
        return repository.listarIndicadoresModelo(id_modelo);
    }

    @Override
    public List<IndicadoresProjection> indicadores(Long id_modelo) {
        return repository.Indicadores(id_modelo);
    }

    @Override
    public List<CriterioPorcProjection> indicadoresadmin(Long id_modelo, Long id) {
        return repository.indicadoresadmin(id_modelo, id);
    }

    @Override
    public List<Indicador> listarPorSubcriterio(Long id_subcriterio) {
        return repository.listarIndicadorPorSubcriterio(id_subcriterio);
    }

    @Override
    public List<Indicador> obtenerIndicadores(Long id) {
        return repository.obtenerIndicadores(id);
    }

    @Override
    public List<Indicador> listarIndicadorPorCriterioModelo(Long id_criterio, Long id_modelo) {
        return repository.listarIndicadorPorCriterioModelo(id_criterio, id_modelo);
    }

    @Override
    public List<IndicadoresProjection> indicadoresPorCriterios(Long id_modelo,List<Long> id_criterio) {
        return repository.indicadoresPorCriterios(id_modelo, id_criterio);
    }

    @Override
    public List<IndicadoresProjection> indicadoresPorCriteriosPruebaCualitativa(List<Long> id_criterio, Long id_modelo) {
        return repository.indicadoresPorCriteriosPruebaCualitativa(id_criterio, id_modelo);
    }

    @Override
    public List<IndicadoresProjection> indicadoresPorCriteriosPruebaCuantitativa(List<Long> id_criterio,Long id_modelo) {
        return repository.indicadoresPorCriteriosPruebaCuantitativa(id_criterio, id_modelo);
    }


   /* @Override
    public List<Indicador> indicadoresPorCriteriosCuali() {return repository.indicadoresPorCriteriosCuali();}
    @Override
    public List<Indicador> indicadoresPorCriteriosCuanti() {return repository.indicadoresPorCriteriosCuanti();}*/

    @Override
    public List<IndicadorEvidenciasProjection> obtenerDatosIndicadores(Long id_subcriterio, Long id_modelo){
        return repository.obtenerIndicadoresConCantidadEvidencia(id_subcriterio, id_modelo);
    };
    /*@Override
    public List<IndicadorEvidenciasProjectionFull> obtenerDatosIndicadoresFull(){
        return repository.obtenerIndicadoresConCantidadEvidenciaFull();
    };*/
    @Override
    public List<Indicador> indicadoresPorModelo(Long id_modelo) {
        return repository.indicadoresPorModelo(id_modelo);
    }

    @Override
    public List<IndiColProjection> indicadorval(Long id_modelo) {
        return repository.indicadorval(id_modelo);
    }

    @Override
    public List<IndicadorResp> indicadorPorSubcriterio(Long id_subcriterio, Long id_modelo) {
        return repository.indicadorPorSubcriterio(id_subcriterio, id_modelo);
    }

    @Override
    public List<IndicadoresProjection> indicadoresresp(Long id_modelo, Long id) {
        return repository.indicadoresresp(id_modelo, id);
    }

    @Override
    public List<IndiColProjection> indicadorvaladmin(Long id_modelo, Long id) {
        return repository.indicadorvaladmin(id_modelo, id);
    }
    @Override
    public List<IndicadorPorcProjection> indicadoreporsubcriterio(String sub_nombre, Long id_modelo) {
        return repository.indicadoreporsubcriterio(sub_nombre, id_modelo);
    }
    @Override
    public List<IndicadoresGPieProjection> indicadoresPorcObtenidoM75(Long id_modelo) {
        return repository.indicadoresPorcObtenidoM75(id_modelo);
    }

    @Override
    public List<IndicadoresGPieProjection> indicadoresPorcObtenido50_75(Long id_modelo) {
        return repository.indicadoresPorcObtenido50_75(id_modelo);
    }
    @Override
    public List<IndicadoresGPieProjection> indicadoresPorcObtenido25_50(Long id_modelo) {
        return repository.indicadoresPorcObtenido25_50(id_modelo);
    }
    @Override
    public List<IndicadoresGPieProjection> indicadoresPorcObtenido0_25(Long id_modelo) {
        return repository.indicadoresPorcObtenido0_25(id_modelo);
    }

}
