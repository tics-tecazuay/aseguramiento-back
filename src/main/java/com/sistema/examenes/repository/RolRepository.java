package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RolRepository extends JpaRepository<Rol,Long> {
    @Query(value = "SELECT r.* FROM roles r " +
            "JOIN usuariorol ur ON r.rolid = ur.rol_rolid " +
            "JOIN usuarios u ON ur.usuario_id = u.id " +
            "WHERE u.visible=true AND u.username = :username", nativeQuery = true)
    List<Rol> listaRolesPorUsername(@Param("username") String username);
}
