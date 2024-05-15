package com.sistema.examenes.projection;

import java.util.Date;

public interface PonderacionProjection {
    Long getContador();
    Date getFechapo();
    Long getMaxcontador();

    //Ponderacion Modelo
    Long getIdponderacion();
    Long getIdindicador();
    String getNombrecriterio();
    String getNombresubcriterio();
    String getNombreindicador();
    Double getValorobtenido();
    Double getPorcentajeobtenido();
    Double getPorcentajeutilidad();
    Double getPeso();
}
