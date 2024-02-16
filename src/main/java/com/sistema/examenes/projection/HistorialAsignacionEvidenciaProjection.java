package com.sistema.examenes.projection;

import java.util.Date;

public interface HistorialAsignacionEvidenciaProjection {
    String getNombre_usuario();
    Date getFecha();
    Date getFecha_fin();
    Date getFecha_inicio();
    String getEstado();
    String getTitulo_evidencia();
    String getTitulo_indicador();
    String getTitulo_subcriterio();
    String getTitulo_criterio();

}
