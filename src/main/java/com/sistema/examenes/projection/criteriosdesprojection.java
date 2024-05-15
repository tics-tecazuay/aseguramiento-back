package com.sistema.examenes.projection;

import java.math.BigDecimal;

public interface criteriosdesprojection {
    String getcriterionomj();
    String getsubcrierioj();
    Long getId_indicardorj();
    Long getId_evidencia();
    String getEstado_evi();
    String getIdes();
    String getDescrip();
    double getPeso_evid();
    String getind_nombrej();
    String getTip();
    String getarchivo_nombre();
    String getarchivo_enlace();
    Boolean getVisi();
    BigDecimal getPes();
    BigDecimal getObt();
    BigDecimal getUti();
    BigDecimal getVal();
}
