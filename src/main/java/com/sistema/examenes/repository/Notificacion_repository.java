package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Notificacion;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

public interface Notificacion_repository extends JpaRepository<Notificacion, Long> {
    //ListarTODO
    @Query("SELECT n FROM Notificacion n WHERE n.id_modelo= :id_modelo ORDER BY n.fecha DESC")
    List<Notificacion> listarTodasNotificaciones(Long id_modelo);

    @Query("SELECT n FROM Notificacion n WHERE n.usuario= :id_usuario AND n.id_modelo= :id_modelo ORDER BY n.fecha DESC")
    List<Notificacion> listarNotificacionesPorUsuario(Long id_usuario, Long id_modelo);

    @Query("SELECT n FROM Notificacion n WHERE n.usuario = :user ORDER BY n.fecha DESC")
    List<Notificacion> listarUserNotimovil(Long user, Pageable pageable);
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM notificacion WHERE id=:id", nativeQuery = true)
    void borrar(Long id);

    @Query(value = "SELECT * FROM notificacion WHERE DATE(fecha)<CAST(:fec AS DATE)", nativeQuery = true)
    List<Notificacion> listarNot(String fec);

    @Query("SELECT n FROM Notificacion n WHERE n.rol = :roluser AND n.id_modelo= :id_modelo ORDER BY n.fecha DESC")
    List<Notificacion> listarNotificacionesPorRolUsuario(@Param("roluser") String roluser,@Param("id_modelo") Long id_modelo, Pageable pageable);

    @Query("SELECT n FROM Notificacion n WHERE n.rol = :roluser ORDER BY n.fecha DESC")
    List<Notificacion> allmovil(@Param("roluser") String roluser, Pageable pageable);
    @Query("SELECT n FROM Notificacion n " +
            "WHERE n.rol = :roluser " +
            "AND n.idactividad IN ( " +
            "    SELECT ae.evidencia.id_evidencia " +
            "    FROM Asignacion_Evidencia ae " +
            "    WHERE ae.id_usuario_asignador = :userId )" +
            "AND n.id_modelo= :id_modelo " +
            "ORDER BY n.fecha DESC")
    List<Notificacion> all2(@Param("roluser") String roluser, @Param("userId") Long userId, @Param("id_modelo") Long id_modelo);

    @Query(value = "SELECT DISTINCT ON (mensaje)* FROM notificacion WHERE usuario=:user ORDER BY mensaje, fecha DESC;",nativeQuery = true)
    List<Notificacion> listarulNoti(@Param("user") Long user);

    @Query(value = "SELECT DATE(fecha_fin) FROM modelo WHERE id_modelo=(SELECT MAX(id_modelo) FROM modelo WHERE id_modelo < (SELECT MAX(id_modelo) FROM modelo))", nativeQuery = true)
    Date fechaeliminar();
}
