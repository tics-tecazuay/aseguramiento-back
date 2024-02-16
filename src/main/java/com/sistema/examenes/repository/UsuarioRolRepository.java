package com.sistema.examenes.repository;

import com.sistema.examenes.entity.UsuarioRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRolRepository extends JpaRepository<UsuarioRol,Long> {

    @Query("SELECT ur FROM UsuarioRol ur JOIN FETCH ur.usuario u WHERE u.visible = true")
    List<UsuarioRol> listarv();

    UsuarioRol findByUsuario_Id(Long usuarioId);
    @Query(value = "SELECT ur.* FROM UsuarioRol ur JOIN usuarios u ON u.id = ur.usuario_id WHERE u.visible=true AND ur.usuario_id = :usuarioId", nativeQuery = true)
    List<UsuarioRol> findByUsuarios_Usuario_Id(Long usuarioId);
}
