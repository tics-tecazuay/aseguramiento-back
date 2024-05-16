package com.sistema.examenes.services;

import com.sistema.examenes.entity.Asignacion_Admin;
import com.sistema.examenes.entity.Asignacion_Responsable;
import com.sistema.examenes.projection.ResponsableProjection;

import java.util.List;

public interface Asignacion_Responsable_Service extends GenericService<Asignacion_Responsable, Long> {
     Asignacion_Responsable asignacion_existente(Long id_usuarioAdmin, Long id_usuarioResponsable, Long idModelo);
     List<ResponsableProjection> listadeResponsablesByAdmin(Long idModelo, Long idAdministrador);
     List<Asignacion_Responsable> Asignacion_ResponsablesByAdmin(Long idAdministrador);
     Asignacion_Responsable asignacionByIdUsuarioResponsable(Long id_usuarioResponsable);
     Asignacion_Responsable asignacionByIdUsuarioResponsableIdModelo(Long id_usuarioResponsable, Long id_modelo);
}
