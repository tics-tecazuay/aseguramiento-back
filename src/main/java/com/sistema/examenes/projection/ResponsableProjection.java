package com.sistema.examenes.projection;

import java.util.Date;

public interface ResponsableProjection {
    Long getId();
    String getNombres();

    String getRol();
    String getUsua();
    String getEvidencias();

    String getNombre_criterio();
}
