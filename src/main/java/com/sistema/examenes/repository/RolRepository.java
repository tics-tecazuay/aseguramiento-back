package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RolRepository extends JpaRepository<Rol,Long> {
    @Query("SELECT DISTINCT ur.rol FROM Usuario u " +
            "JOIN u.usuarioRoles ur " +
            "WHERE u.username = :username AND u.visible = true AND ur.visible = true")
    List<Rol> findRolesByUsername(@Param("username") String username);
}
