package com.sistema.examenes.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "asignacion_admin")
public class Asignacion_Admin implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asignacion")
    private Long id_asignacion;

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "criterio_id_criterio", referencedColumnName = "id_criterio")
    private Criterio criterio;

    @Column(name = "visible")
    private boolean visible;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_modelo")
    private Modelo id_modelo;

    public Asignacion_Admin() {
    }

    public Long getId_asignacion() {
        return id_asignacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Criterio getCriterio() {
        return criterio;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setId_asignacion(Long id_asignacion) {
        this.id_asignacion = id_asignacion;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setCriterio(Criterio criterio) {
        this.criterio = criterio;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

}
