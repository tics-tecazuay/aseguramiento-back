package com.sistema.examenes.entity.dto;

import java.util.Date;

public interface SeguimientoUsuarioDTO {
    Long getId_seguimiento();
    String getUsername();
    String getRolnombre();
    String getCedula();
    String getUsuario();
    String getDescripcion();
    Date getFecha();
}
