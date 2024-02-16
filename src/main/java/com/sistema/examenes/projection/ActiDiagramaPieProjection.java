package com.sistema.examenes.projection;

public interface ActiDiagramaPieProjection {
    Double getPendientes();
    Double getAprobados();
    Double getRechazados();
    Double getTotal();
    Double getPorcentaje_pendientes();
    Double getPorcentaje_aprobados();
    Double getPorcentaje_rechazados();

}
