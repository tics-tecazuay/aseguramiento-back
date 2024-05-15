package com.sistema.examenes.controller;


import com.sistema.examenes.entity.Rol;
import com.sistema.examenes.entity.Usuario;
import com.sistema.examenes.entity.UsuarioRol;
import com.sistema.examenes.services.RolService;
import com.sistema.examenes.services.UsuarioRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/aseguramiento/api/usuariorol")
@CrossOrigin({"https://apps.tecazuay.edu.ec","http://localhost:4200/"})
public class Usuario_Rol_Controller {
    @Autowired
    private UsuarioRolService usuarioService;
    @Autowired
    private RolService rolService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @GetMapping("/listarv")
    public ResponseEntity<List<UsuarioRol>> obtenerLista() {
        try {
            return new ResponseEntity<>(usuarioService.listarv(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/listarrolesporusername/{username}")
        public ResponseEntity<List<Rol>> listaRolesPorUsername( @PathVariable String username) {
        try {
            if(!username.isEmpty()){
                return new ResponseEntity<>(rolService.listaRolesPorUsername(username.trim()), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/actualizar/{usuarioRolId}")
    public ResponseEntity<UsuarioRol> actualizarRol(@RequestBody UsuarioRol usuarioRol, @PathVariable Long usuarioRolId) {
        try {
            UsuarioRol usuarioRolExistente = usuarioService.findById(usuarioRolId);
            if (usuarioRolExistente != null) {
                String nuevaContraseña = usuarioRol.getUsuario().getPassword();
                // Actualizar la contraseña en el usuario existente
                if (!nuevaContraseña.equals(usuarioRolExistente.getUsuario().getPassword())) {
                    usuarioRolExistente.getUsuario().setPassword(bCryptPasswordEncoder.encode(nuevaContraseña));
                }
                usuarioRolExistente.setRol(usuarioRol.getRol());
                UsuarioRol usuarioRolActualizado = usuarioService.save(usuarioRolExistente);
                return new ResponseEntity<>(usuarioRolActualizado, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/actualizarsup/{usuarioRolId}")
    @Transactional
    public ResponseEntity<UsuarioRol> actualizarRolSup(@RequestBody UsuarioRol usuarioRol, @PathVariable Long usuarioRolId, @RequestParam("rolIds") List<Long> rolIds) {
        try {
            // Buscar el usuarioRol existente por su ID
            UsuarioRol usuarioRolExistente = usuarioService.findById(usuarioRolId);
            if (usuarioRolExistente != null) {
                String nuevaContraseña =  usuarioRol.getUsuario().getPassword(); // La contraseña proporcionada por el usuario
                String contraseñaAlmacenada = usuarioRolExistente.getUsuario().getPassword(); // La contraseña almacenada en la base de datos
                // Verificar si la nueva contraseña no está vacía
                if (nuevaContraseña != null && !nuevaContraseña.isEmpty()) {
                    // Verificar si la nueva contraseña es diferente a la almacenada
                    if (!bCryptPasswordEncoder.matches(nuevaContraseña, contraseñaAlmacenada)) {
                        // Codificar y actualizar la nueva contraseña
                        usuarioRolExistente.getUsuario().setPassword(bCryptPasswordEncoder.encode(nuevaContraseña));
                    }
                } else {
                    // Si la nueva contraseña está vacía, mantener la contraseña almacenada
                    usuarioRolExistente.getUsuario().setPassword(contraseñaAlmacenada);
                }

                 //Marcar todos los roles existentes como no visibles (eliminados lógicamente)
                List<UsuarioRol> usuarioRols = usuarioService.findByUsuarios_UsuarioId(usuarioRolExistente.getUsuario().getId());
                for (UsuarioRol rolUsuario : usuarioRols) {
                        rolUsuario.setVisible(false);
                        usuarioService.save(rolUsuario);
                }

                // Asociar los nuevos roles proporcionados en la solicitud
                for (Long idRol : rolIds) {
                    // Verificar si el rol ya está asociado al usuario
                    Rol rolListado =  rolService.findById(idRol);
                    UsuarioRol rolUsuarioExistente = usuarioService.findByUsuarioAndRol(usuarioRolExistente.getUsuario().getId(),rolListado.getRolId());
                    if (rolUsuarioExistente != null) {
                        rolUsuarioExistente.setVisible(true); // Marcar como visible
                        usuarioService.save(rolUsuarioExistente);
                    } else {
                        // Si el rol no está asociado, entonces asociarlo
                        Rol rol = rolService.findById(idRol);
                        if (rol != null) {
                            UsuarioRol nuevoUsuarioRol = new UsuarioRol();
                            nuevoUsuarioRol.setUsuario(usuarioRolExistente.getUsuario());
                            nuevoUsuarioRol.setRol(rol);
                            nuevoUsuarioRol.setVisible(true); // Marcar como visible
                            usuarioService.save(nuevoUsuarioRol);
                        }
                    }
                }   
                // Devolver el usuarioRol actualizado
                usuarioService.save(usuarioRolExistente);
                return new ResponseEntity<>(usuarioRolExistente, HttpStatus.OK);
            }
            // Si no se encuentra el usuarioRol
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println("Error al actualizar el usuarioRol: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
