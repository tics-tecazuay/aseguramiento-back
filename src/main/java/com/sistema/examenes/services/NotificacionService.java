package com.sistema.examenes.services;

import com.sistema.examenes.entity.Notificacion;
import java.sql.Date;
import java.util.List;

public interface NotificacionService extends GenericService<Notificacion, Long> {
    public List<Notificacion> listarTodasNotificaciones(Long id_modelo);
    public List<Notificacion> listarNotificacionesPorUsuario(Long id_usuario, Long id_modelo);
    public List<Notificacion> listarmovil(Long us);

    public List<Notificacion> listarNotificacionesPorRolUsuario(String roluserer, Long id_modelo);
    public List<Notificacion> allmovil(String roluserer);
    public void eliminar(Long id);
    public List<Notificacion> listarNotifi(String fecha);

    public List<Notificacion> listarulNoti(Long us);
    public List<Notificacion> all2(String roluserer,Long userId, Long id_modelo);
    Date fechaeliminar();
}
