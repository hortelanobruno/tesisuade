/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bruno.elbruto.db.persistencia.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Brunoli
 */
@Entity
@Table(name = "peleascompletadas")
@NamedQueries({@NamedQuery(name = "PeleasCompletadas.findAll", query = "SELECT p FROM PeleasCompletadas p"), @NamedQuery(name = "PeleasCompletadas.findByFecha", query = "SELECT p FROM PeleasCompletadas p WHERE p.peleasCompletadasPK.fecha = :fecha"), @NamedQuery(name = "PeleasCompletadas.findByNombre", query = "SELECT p FROM PeleasCompletadas p WHERE p.peleasCompletadasPK.nombre = :nombre")})
public class PeleasCompletadas implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PeleasCompletadasPK peleasCompletadasPK;

    public PeleasCompletadas() {
    }

    public PeleasCompletadas(PeleasCompletadasPK peleasCompletadasPK) {
        this.peleasCompletadasPK = peleasCompletadasPK;
    }

    public PeleasCompletadas(Date fecha, String nombre) {
        this.peleasCompletadasPK = new PeleasCompletadasPK(fecha, nombre);
    }

    public PeleasCompletadasPK getPeleasCompletadasPK() {
        return peleasCompletadasPK;
    }

    public void setPeleasCompletadasPK(PeleasCompletadasPK peleasCompletadasPK) {
        this.peleasCompletadasPK = peleasCompletadasPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (peleasCompletadasPK != null ? peleasCompletadasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeleasCompletadas)) {
            return false;
        }
        PeleasCompletadas other = (PeleasCompletadas) object;
        if ((this.peleasCompletadasPK == null && other.peleasCompletadasPK != null) || (this.peleasCompletadasPK != null && !this.peleasCompletadasPK.equals(other.peleasCompletadasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bruno.elbruto.db.persistencia.entities.PeleasCompletadas[peleasCompletadasPK=" + peleasCompletadasPK + "]";
    }

}
