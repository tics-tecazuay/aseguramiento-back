package com.sistema.examenes.projection;

import java.util.Date;

public interface EvidenciaReApPeAtrProjection {
    String getResponsable();
    String getNombre_criterio();
    String getNombre_subcriterio();
    String getNombre_indicador();
    String getEvidencia();
    Date getFecha_fin();
    Date getFecha_inicio();
    String getEstado();

}
