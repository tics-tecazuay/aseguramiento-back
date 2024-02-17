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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
            return new ResponseEntity<>(rolService.listaRolesPorUsername(username), HttpStatus.OK);
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
    public ResponseEntity<UsuarioRol> actualizarRolSup(@RequestBody UsuarioRol usuarioRol, @PathVariable Long usuarioRolId, @RequestParam("rolIds") List<Long> rolIds) {
        try {
            // Buscar el usuarioRol existente por su ID
            Optional<UsuarioRol> usuarioRolExistenteOptional = Optional.ofNullable(usuarioService.findById(usuarioRolId));

            if (usuarioRolExistenteOptional.isPresent()) {
                UsuarioRol usuarioRolExistente = usuarioRolExistenteOptional.get();

                // Actualizar la contraseña si es diferente
                String nuevaContraseña = usuarioRol.getUsuario().getPassword();
                if (!nuevaContraseña.equals(usuarioRolExistente.getUsuario().getPassword())) {
                    usuarioRolExistente.getUsuario().setPassword(bCryptPasswordEncoder.encode(nuevaContraseña));
                }

                // Obtener los registros de UsuarioRol asociados a ese usuario
                List<UsuarioRol> rolesUsuario = usuarioService.findByUsuarios_UsuarioId(usuarioRolExistente.getUsuarioRolId());
                // Eliminar los roles no incluidos en la lista de IDs de roles proporcionados
                rolesUsuario.stream()
                        .filter(ur -> !rolIds.contains(ur.getRol().getRolId()))
                        .map(UsuarioRol::getUsuarioRolId) // Obtener los IDs de UsuarioRol
                        .forEach(usuarioService::delete); // Llamar al método delete con los IDs

                // Iterar sobre los nuevos IDs de roles proporcionados en la solicitud
                for (Long idRol : rolIds) {
                    // Verificar si el rol ya está asociado al usuario
                    boolean rolYaAsociado = rolesUsuario.stream().anyMatch(ur -> ur.getRol().getRolId().equals(idRol));

                    // Si el rol no está asociado, entonces asociarlo
                    if (!rolYaAsociado) {
                        Rol rol = rolService.findById(idRol);
                        if (rol != null) {
                            UsuarioRol nuevoUsuarioRol = new UsuarioRol();
                            nuevoUsuarioRol.setUsuario(usuarioRolExistente.getUsuario());
                            nuevoUsuarioRol.setRol(rol);
                            usuarioService.save(nuevoUsuarioRol);
                        }
                    }
                }
                // Devolver el usuarioRol actualizado
                return new ResponseEntity<>(usuarioRolExistente, HttpStatus.OK);
            }

            // Si no se encuentra el usuarioRol
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
