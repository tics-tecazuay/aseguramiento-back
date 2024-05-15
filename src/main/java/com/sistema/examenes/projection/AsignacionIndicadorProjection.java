package com.sistema.examenes.projection;

public interface AsignacionIndicadorProjection {
    Long getId_indicador();
    String getCriterio();
    String getSubcriterio();
    String getIndicador();
    double getPeso();
    double getPorc_obtenido();
    double getPorc_utilida_obtenida();
    double getValor_obtenido();

}
