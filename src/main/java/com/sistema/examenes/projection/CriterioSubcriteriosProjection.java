package com.sistema.examenes.projection;

public interface CriterioSubcriteriosProjection {
    Long getId_criterio();
    String getNombre();
    String getDescripcion();
    boolean isVisible();
    Long getCantidadSubcriterios();
}
