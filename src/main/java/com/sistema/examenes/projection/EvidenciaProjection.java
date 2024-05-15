package com.sistema.examenes.projection;

public interface EvidenciaProjection {

    Long getId_evidencia();
    String getCriterio();
    String getSubcriterio();
    String getIndicador();
    String getEstado();
    String getDescripcion();
    Long getId_asignacion_evidencia();
    Long getCountarchivos();

    //Listar Archivos - Rol Autoridad
    String getEnlaces();

}
