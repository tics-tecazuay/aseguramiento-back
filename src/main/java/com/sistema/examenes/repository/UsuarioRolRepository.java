package com.sistema.examenes.repository;

import com.sistema.examenes.entity.UsuarioRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRolRepository extends JpaRepository<UsuarioRol,Long> {

    @Query("SELECT ur FROM UsuarioRol ur JOIN FETCH ur.usuario u WHERE u.visible = true")
    List<UsuarioRol> listarv();

    @Query(value = "SELECT ur.* FROM UsuarioRol ur JOIN usuarios u ON u.id = ur.usuario_id \n" +
            "AND ur.visible=true AND ur.usuario_id =:usuarioId", nativeQuery = true)
    List<UsuarioRol> findByUsuarios_Usuario_Id(Long usuarioId);


    @Query(value = "SELECT ur.* FROM UsuarioRol ur JOIN usuarios u ON u.id = ur.usuario_id WHERE ur.usuario_id = :usuarioId AND ur.rol_rolid= :rolId AND  u.visible=true", nativeQuery = true)
    UsuarioRol findByUsuarioAndRol(Long usuarioId, Long rolId);

}
