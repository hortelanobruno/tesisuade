/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bruno.elbruto.db.persistencia.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "peleas")
@NamedQueries({@NamedQuery(name = "Pelea.findAll", query = "SELECT p FROM Pelea p"), @NamedQuery(name = "Pelea.findByFecha", query = "SELECT p FROM Pelea p WHERE p.peleaPK.fecha = :fecha"), @NamedQuery(name = "Pelea.findByNombre", query = "SELECT p FROM Pelea p WHERE p.peleaPK.nombre = :nombre"), @NamedQuery(name = "Pelea.findByRival", query = "SELECT p FROM Pelea p WHERE p.peleaPK.rival = :rival"), @NamedQuery(name = "Pelea.findByVictoria", query = "SELECT p FROM Pelea p WHERE p.victoria = :victoria")})
public class Pelea implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PeleaPK peleaPK;
    @Column(name = "victoria")
    private Boolean victoria;

    public Pelea() {
    }

    public Pelea(PeleaPK peleaPK) {
        this.peleaPK = peleaPK;
    }

    public Pelea(Date fecha, String nombre, String rival) {
        this.peleaPK = new PeleaPK(fecha, nombre, rival);
    }

    public PeleaPK getPeleaPK() {
        return peleaPK;
    }

    public void setPeleaPK(PeleaPK peleaPK) {
        this.peleaPK = peleaPK;
    }

    public Boolean getVictoria() {
        return victoria;
    }

    public void setVictoria(Boolean victoria) {
        this.victoria = victoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (peleaPK != null ? peleaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pelea)) {
            return false;
        }
        Pelea other = (Pelea) object;
        if ((this.peleaPK == null && other.peleaPK != null) || (this.peleaPK != null && !this.peleaPK.equals(other.peleaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bruno.elbruto.db.persistencia.entities.Pelea[peleaPK=" + peleaPK + "]";
    }

}
