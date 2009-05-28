/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bruno.elbruto.manager;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 *
 * @author Administrator
 */
public class Pelea {

    private Date fecha;
    private Bruto rival;
    private Bruto bruto;
    private boolean victoria;

    public Pelea() {
    }

    public Bruto getBruto() {
        return bruto;
    }

    public Date getFecha() {
        return fecha;
    }

    public Bruto getRival() {
        return rival;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setRival(Bruto rival) {
        this.rival = rival;
    }

    public boolean isVictoria() {
        return victoria;
    }

    public void setBruto(Bruto bruto) {
        this.bruto = bruto;
    }

    public void setVictoria(boolean victoria) {
        this.victoria = victoria;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pelea other = (Pelea) obj;
        if (this.fecha != other.fecha && (this.fecha == null || !this.fecha.equals(other.fecha))) {
            return false;
        }
        if (this.rival != other.rival && (this.rival == null || !this.rival.equals(other.rival))) {
            return false;
        }
        if (this.bruto != other.bruto && (this.bruto == null || !this.bruto.equals(other.bruto))) {
            return false;
        }
        if (this.victoria != other.victoria) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.fecha != null ? this.fecha.hashCode() : 0);
        hash = 97 * hash + (this.rival != null ? this.rival.hashCode() : 0);
        hash = 97 * hash + (this.bruto != null ? this.bruto.hashCode() : 0);
        hash = 97 * hash + (this.victoria ? 1 : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Pelea entre " + bruto.getNombre() + " y " + rival.getNombre() + ".";
    }
}
