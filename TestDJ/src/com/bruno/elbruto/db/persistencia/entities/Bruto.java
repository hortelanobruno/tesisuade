/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bruno.elbruto.db.persistencia.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "brutos")
@NamedQueries({@NamedQuery(name = "Bruto.findAll", query = "SELECT b FROM Bruto b"),
@NamedQuery(name = "Bruto.findByNombre", query = "SELECT b FROM Bruto b WHERE b.nombre = :nombre"),
@NamedQuery(name = "Bruto.findByPassword", query = "SELECT b FROM Bruto b WHERE b.password = :password"),
@NamedQuery(name = "Bruto.findByNivel", query = "SELECT b FROM Bruto b WHERE b.nivel = :nivel"),
@NamedQuery(name = "Bruto.findByClasificacion", query = "SELECT b FROM Bruto b WHERE b.clasificacion = :clasificacion"), @NamedQuery(name = "Bruto.findByPropietario", query = "SELECT b FROM Bruto b WHERE b.propietario = :propietario")})
public class Bruto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Column(name = "nivel")
    private int nivel;
    @Column(name = "clasificacion")
    private Integer clasificacion;
    @Column(name = "propietario")
    private Boolean propietario;
    @Column(name = "victorias")
    private Long victorias;

    public Bruto() {
    }

    public Bruto(String nombre) {
        this.nombre = nombre;
    }

    public Bruto(String nombre, int nivel) {
        this.nombre = nombre;
        this.nivel = nivel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public Integer getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(Integer clasificacion) {
        this.clasificacion = clasificacion;
    }

    public Boolean getPropietario() {
        return propietario;
    }

    public void setPropietario(Boolean propietario) {
        this.propietario = propietario;
    }

    public Long getVictorias() {
        return victorias;
    }

    public void setVictorias(Long victorias) {
        this.victorias = victorias;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nombre != null ? nombre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bruto)) {
            return false;
        }
        Bruto other = (Bruto) object;
        if ((this.nombre == null && other.nombre != null) || (this.nombre != null && !this.nombre.equals(other.nombre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bruno.elbruto.db.persistencia.entities.Bruto[nombre=" + nombre + "]";
    }
}
