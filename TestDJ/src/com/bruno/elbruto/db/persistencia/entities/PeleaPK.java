/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bruno.elbruto.db.persistencia.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Administrator
 */
@Embeddable
public class PeleaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "rival")
    private String rival;

    public PeleaPK() {
    }

    public PeleaPK(Date fecha, String nombre, String rival) {
        this.fecha = fecha;
        this.nombre = nombre;
        this.rival = rival;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRival() {
        return rival;
    }

    public void setRival(String rival) {
        this.rival = rival;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fecha != null ? fecha.hashCode() : 0);
        hash += (nombre != null ? nombre.hashCode() : 0);
        hash += (rival != null ? rival.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeleaPK)) {
            return false;
        }
        PeleaPK other = (PeleaPK) object;
        if ((this.fecha == null && other.fecha != null) || (this.fecha != null && !this.fecha.equals(other.fecha))) {
            return false;
        }
        if ((this.nombre == null && other.nombre != null) || (this.nombre != null && !this.nombre.equals(other.nombre))) {
            return false;
        }
        if ((this.rival == null && other.rival != null) || (this.rival != null && !this.rival.equals(other.rival))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bruno.elbruto.db.persistencia.entities.PeleaPK[fecha=" + fecha + ", nombre=" + nombre + ", rival=" + rival + "]";
    }

}
