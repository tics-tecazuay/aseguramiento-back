package com.sistema.examenes.services;

import com.sistema.examenes.entity.Criterio;
import com.sistema.examenes.entity.Indicador;
import com.sistema.examenes.projection.*;

import java.util.List;

public interface Indicador_Service extends GenericService<Indicador, Long> {
    public List<Indicador> listar();
    public List<PonderacionProjection> listarIndicadoresModelo(Long id_modelo);
    public List<IndicadoresProjection> indicadores(Long id_modelo);
    List<CriterioPorcProjection> indicadoresadmin(Long id_modelo,Long id);
    public List<Indicador> listarPorSubcriterio(Long id_subcriterio);

    List<Indicador> obtenerIndicadores(Long id);

    List<Indicador> listarIndicadorPorCriterioModelo(Long id_criterio, Long id_modelo);

    List<IndicadoresProjection> indicadoresPorCriterios(Long id_modelo,List<Long> id_criterio);

    List<IndicadoresProjection> indicadoresPorCriteriosPruebaCualitativa(List<Long> id_criterio,Long id_modelo);
    List<IndicadoresProjection> indicadoresPorCriteriosPruebaCuantitativa(List<Long> id_criterio,Long id_modelo);

    public List<IndicadorEvidenciasProjection> obtenerDatosIndicadores(Long id_subcriterio, Long id_modelo);
    //public List<IndicadorEvidenciasProjectionFull> obtenerDatosIndicadoresFull();
    List<Indicador> indicadoresPorModelo(Long id_modelo);
    List<IndiColProjection> indicadorval(Long id_modelo);

    List<IndicadorResp> indicadorPorSubcriterio(Long id_subcriterio,Long id_modelo);
    List<IndicadoresProjection> indicadoresresp(Long id_modelo,Long id);
    List<IndiColProjection> indicadorvaladmin(Long id_modelo,Long id);
    List<IndicadorPorcProjection> indicadoreporsubcriterio(String sub_nombre, Long id_modelo);

    List<IndicadoresGPieProjection> indicadoresPorcObtenidoM75(Long id_modelo);
    List<IndicadoresGPieProjection> indicadoresPorcObtenido50_75(Long id_modelo);
    List<IndicadoresGPieProjection> indicadoresPorcObtenido25_50(Long id_modelo);
    List<IndicadoresGPieProjection> indicadoresPorcObtenido0_25(Long id_modelo);
}
