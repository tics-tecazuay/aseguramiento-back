package com.sistema.examenes.projection;

public interface IndicadoresProjection {
    String getNombre();
    Double getFaltante();
    Double getTotal();

    //reporte criterio
    String getNombrecriterio();
    String getNombresubcriterio();
    String getNombreindicador();
    String getDescripcionindicador();
    Double getValorobtenido();
    Double getPorcentajeobtenido();
    Double getPorcentajeutilidad();
    String getTipo();

}
