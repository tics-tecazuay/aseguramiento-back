package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Usuario;
import java.util.List;

import com.sistema.examenes.projection.CriteProjection;
import com.sistema.examenes.projection.ResponsableProjection;
import com.sistema.examenes.projection.UsuariosProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
       // @Query(value = "SELECT * FROM usuarios WHERE username = :username AND visible=true", nativeQuery = true)
        Usuario findByUsernameAndVisibleTrue(String username);

        @Query(value = "SELECT * FROM usuarios WHERE username = :username", nativeQuery = true)
        public Usuario findAllByUsername(String username);

        @Query(value = "SELECT *\n" +
                        "\tFROM usuarios WHERE enabled = true AND visible=true", nativeQuery = true)
        public abstract List<Usuario> listar();

        @Query(value = "SELECT * FROM usuarios;", nativeQuery = true)
        public abstract List<Usuario> listaResponsables();

        @Query(value = "SELECT * FROM usuarios WHERE username=:user", nativeQuery = true)
        public Usuario buscarId(String user);


        //aquiii lista completa
        @Query(value = "SELECT u.id as id,ur.usuariorolid as userrolid, pe.primer_nombre||' '||pe.segundo_nombre||' '||pe.primer_apellido||' '||pe.segundo_apellido as nombres, u.username as usuario, ro.rolnombre as rolnombre,\n" +
                "u.password as contrasenia, CASE WHEN criterio.nombre IS NOT NULL THEN criterio.nombre ELSE '' END AS criterionombre\n" +
                "FROM usuariorol ur JOIN usuarios u ON ur.usuario_id=u.id\n" +
                "LEFT JOIN persona pe ON u.persona_id_persona=pe.id_persona \n" +
                "LEFT JOIN roles ro ON ur.rol_rolid=ro.rolid\n" +
                "LEFT JOIN asignacion_admin aa ON aa.usuario_id = u.id AND aa.visible = true AND aa.id_modelo =:id_modelo\n" +
                "LEFT JOIN criterio criterio ON aa.criterio_id_criterio = criterio.id_criterio \n" +
                "WHERE u.visible = true AND ur.visible=true", nativeQuery = true)
        List<UsuariosProjection> listarusercrite(Long id_modelo);

      
        @Query(value = "SELECT DISTINCT u.* " +
                "FROM " +
                "    usuarios u " +
                "JOIN " +
                "    persona per ON per.id_persona = u.persona_id_persona " +
                "JOIN " +
                "    usuariorol ur ON u.id = ur.usuario_id " +
                "JOIN " +
                "    roles r ON ur.rol_rolid = r.rolid " +
                "LEFT JOIN " +
                "    asignacion_responsable asigres ON asigres.usuarioresponsable_id = u.id AND asigres.id_modelo = :idModel " +
                "LEFT JOIN " +
                "    asignacion_admin asigadm ON asigres.usuarioresponsable_id = asigadm.usuario_id AND asigadm.id_modelo = :idModel " +
                "WHERE " +
                "    r.rolnombre = 'RESPONSABLE' " +
                "    AND ( " +
                "        (asigres.usuarioadmin_id = :idAdministrador AND asigres.visible = true) " +
                "        OR " +
                "        (u.id IN ( " +
                "            SELECT ae_inner.usuario_id " +
                "            FROM asignacion_evidencia ae_inner " +
                "            JOIN evidencia e ON ae_inner.evidencia_id_evidencia = e.id_evidencia " +
                "            JOIN indicador i ON e.indicador_id_indicador = i.id_indicador " +
                "            JOIN subcriterio sc ON i.subcriterio_id_subcriterio = sc.id_subcriterio " +
                "            JOIN criterio c ON sc.id_criterio = c.id_criterio " +
                "            WHERE c.id_criterio IN ( " +
                "                SELECT criterio_id_criterio " +
                "                FROM asignacion_admin " +
                "                WHERE usuario_id = :idAdministrador AND id_modelo = :idModel " +
                "            ) AND ae_inner.id_modelo = :idModel " +
                "        )) " +
                "    ) " +
                "    AND u.visible = true ", nativeQuery = true)
        List<Usuario> listaResponsablesFromAdmin(@Param("idAdministrador") Long idAdministrador, @Param("idModel") Long idModel);

         @Query(value = "SELECT u.*, p.* " +
                 "FROM usuarios u " +
                 "JOIN asignacion_evidencia ae ON u.id = ae.usuario_id AND ae.visible = true AND ae.id_modelo = :id_modelo " +
                 "JOIN persona p ON u.persona_id_persona = p.id_persona " +
                 "WHERE u.visible = true " +
                 "GROUP BY u.id, p.id_persona " +
                 "ORDER BY p.primer_nombre" , nativeQuery = true)
         List<Usuario> listaResponsablesDatos(@Param("id_modelo") Long id_modelo);

        /*
         @Query( "SELECT u.id AS id, p.primer_nombre ||' '|| p.primer_apellido AS nombres," +
                " u.username AS username, p.correo AS correo " +
                "FROM Usuario u " +
                "JOIN Asignacion_Evidencia ae ON u.id = ae.usuario.id " +
                "JOIN Persona p  ON u.persona.id_persona = p.id_persona " +
                "WHERE u.visible = true AND ae.visible = true")
        public List<UsuariosResProjection> listaResponsablesDatos();
*/
        @Query(value = "SELECT u.* FROM usuarios u " +
                        "JOIN usuariorol ur ON u.id = ur.usuario_id " +
                        "LEFT JOIN asignacion_evidencia ae ON u.id = ae.usuario_id " +
                        "WHERE ur.rol_rolid = 3 AND ae.id_asignacion_evidencia IS NULL AND u.visible=true", nativeQuery = true)
        public List<Usuario> listaResponsablesAdmin();
        @Query(value = "SELECT " +
                "    u.id, " +
                "    CONCAT(per.primer_nombre, ' ', per.segundo_nombre, ' ', per.primer_apellido, ' ', per.segundo_apellido) AS nombres, " +
                "    u.username AS usua, " +
                "    r.rolnombre AS rol, " +
                "    CASE " +
                "        WHEN ae.count_evidencias IS NULL THEN 'Sin evidencias asignadas' " +
                "        ELSE CONCAT('Tiene ', ae.count_evidencias, ' evidencia/s asignada/s') " +
                "    END AS evidencias " +
                "FROM " +
                "    usuarios u " +
                "JOIN " +
                "    persona per ON per.id_persona = u.persona_id_persona " +
                "JOIN " +
                "    usuariorol ur ON u.id = ur.usuario_id " +
                "JOIN " +
                "    roles r ON ur.rol_rolid = r.rolid " +
                "LEFT JOIN " +
                "    ( " +
                "        SELECT " +
                "            usuario_id, " +
                "            COUNT(DISTINCT evidencia_id_evidencia) as count_evidencias " +
                "        FROM " +
                "            asignacion_evidencia ae_inner " +
                "        JOIN " +
                "            evidencia e_inner ON e_inner.id_evidencia = ae_inner.evidencia_id_evidencia " +
                "        JOIN " +
                "            indicador i_inner ON i_inner.id_indicador = e_inner.indicador_id_indicador " +
                "        JOIN " +
                "            asignacion_indicador po_inner ON po_inner.indicador_id_indicador = i_inner.id_indicador AND po_inner.modelo_id_modelo= :id_modelo " +
                "        WHERE " +
                "            ae_inner.visible = true   " +
                "            AND ae_inner.id_modelo= :id_modelo" +
                "        GROUP BY " +
                "            usuario_id " +
                "    ) ae ON u.id = ae.usuario_id " +
                "WHERE " +
                "    ur.rol_rolid != 4 " +
                "    AND u.visible = true " +
                "GROUP BY " +
                "    u.id, " +
                "    per.primer_nombre, " +
                "    per.segundo_nombre, " +
                "    per.primer_apellido, " +
                "    per.segundo_apellido, " +
                "    u.username, " +
                "    r.rolnombre, " +
                "    ae.count_evidencias", nativeQuery = true)
        public List<ResponsableProjection> responsablesGeneral(Long id_modelo);

        @Query(value = "SELECT u.* FROM public.usuarios u JOIN public.usuariorol ur ON ur.usuario_id = u.id WHERE ur.rol_rolid = 3 AND u.visible=true", nativeQuery = true)
        List<Usuario> responsables();


        @Query(value = "SELECT u.*\n" +
                        "FROM public.usuarios u\n" +
                        "JOIN public.usuariorol ur ON ur.usuario_id = u.id\n" +
                        "WHERE ur.rol_rolid = 1 AND u.visible=true;", nativeQuery = true)
        public List<Usuario> listaAdminDatos();


        @Query(value = "SELECT u.*,per.* FROM usuarios u " +
                "JOIN usuariorol ur ON u.id = ur.usuario_id " +
                "JOIN persona per on per.id_persona=u.persona_id_persona " +
                "WHERE ur.rol_rolid = 3 AND u.visible=true", nativeQuery = true)
        public List<Usuario> listaSOLORESPONSABLES();


        //Usuarios responsables que tengan los mismos criterios que el administrador
        @Query(value = "SELECT " +
                "    u.id, " +
                "    CONCAT(per.primer_nombre, ' ', per.primer_apellido) AS nombres, " +
                "    u.username AS usua, " +
                "    r.rolnombre AS rol, " +
                "    a.fecha_inicio, " +
                "    a.fecha_fin, " +
                "    CASE " +
                "        WHEN ae.count_evidencias IS NULL THEN 'Sin evidencias asignadas' " +
                "        ELSE 'Tiene ' || ae.count_evidencias || ' evidencia/s asignada/s'\n" +
                "    END AS evidencias " +
                "FROM " +
                "    usuarios u " +
                "JOIN " +
                "    asignacion_admin asig ON asig.usuario_id = u.id " +
                "JOIN " +
                "    persona per ON per.id_persona = u.persona_id_persona " +
                "JOIN " +
                "    usuariorol ur ON u.id = ur.usuario_id " +
                "JOIN " +
                "    roles r ON ur.rol_rolid = r.rolid " +
                "LEFT JOIN " +
                "    ( " +
                "        SELECT " +
                "            usuario_id, " +
                "            COUNT(DISTINCT evidencia_id_evidencia) AS count_evidencias " +
                "        FROM " +
                "            asignacion_evidencia ae_inner " +
                "        JOIN " +
                "            evidencia e_inner ON e_inner.id_evidencia = ae_inner.evidencia_id_evidencia " +
                "        JOIN " +
                "            indicador i_inner ON i_inner.id_indicador = e_inner.indicador_id_indicador " +
                "        JOIN " +
                "            asignacion_indicador po_inner ON po_inner.indicador_id_indicador = i_inner.id_indicador " +
                "        JOIN " +
                "            (SELECT MAX(id_modelo) AS max_id_modelo FROM modelo) max_mo ON po_inner.modelo_id_modelo = max_mo.max_id_modelo " +
                "        WHERE " +
                "            ae_inner.visible = true " +
                "        GROUP BY " +
                "            usuario_id " +
                "    ) ae ON u.id = ae.usuario_id " +
                "LEFT JOIN " +
                "    actividad a ON u.id = a.usuario_id " +
                "WHERE " +
                "    r.rolnombre = 'RESPONSABLE' " +
                "    AND asig.criterio_id_criterio IN ( " +
                "        SELECT criterio.id_criterio " +
                "        FROM criterio " +
                "        JOIN asignacion_admin ON asignacion_admin.criterio_id_criterio = criterio.id_criterio " +
                "        WHERE asignacion_admin.usuario_id =:idAdministrador " +
                "    ) " +
                "GROUP BY " +
                "    u.id, " +
                "    per.primer_nombre, " +
                "    per.primer_apellido, " +
                "    u.username, " +
                "    r.rolnombre, " +
                "    ae.count_evidencias, " +
                "    a.fecha_fin, " +
                "    a.fecha_inicio;", nativeQuery = true)
        public List<ResponsableProjection> responsablesAdmin(@Param("idAdministrador") Long idAdministrador);
}
