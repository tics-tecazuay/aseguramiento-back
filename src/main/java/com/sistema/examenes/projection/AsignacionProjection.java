package com.sistema.examenes.projection;

public interface AsignacionProjection {
    Long getEnc();
    String getNombrescri();
    String getActividasi();

    //Para la busqueda especifica
    Long getId_asignacion();
    Long getIdUsuario();
    Long getIdCriterio();
    Long getIdModelo();
    Boolean getVisible();
}
