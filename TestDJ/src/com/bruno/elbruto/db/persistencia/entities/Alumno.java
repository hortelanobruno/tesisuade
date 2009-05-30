/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bruno.elbruto.db.persistencia.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Brunoli
 */
@Entity
@Table(name = "alumnoproductivo")
@NamedQueries({@NamedQuery(name = "Alumno.findAll", query = "SELECT a FROM Alumno a"), @NamedQuery(name = "Alumno.findByFecha", query = "SELECT a FROM Alumno a WHERE a.fecha = :fecha"), @NamedQuery(name = "Alumno.findByNombre", query = "SELECT a FROM Alumno a WHERE a.nombre = :nombre"), @NamedQuery(name = "Alumno.findByAncestro", query = "SELECT a FROM Alumno a WHERE a.ancestro = :ancestro"), @NamedQuery(name = "Alumno.findByIp", query = "SELECT a FROM Alumno a WHERE a.ip = :ip")})
public class Alumno implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Id
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "ancestro")
    private String ancestro;
    @Basic(optional = false)
    @Column(name = "ip")
    private String ip;

    public Alumno() {
    }

    public Alumno(String nombre) {
        this.nombre = nombre;
    }

    public Alumno(String nombre, Date fecha, String ancestro, String ip) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.ancestro = ancestro;
        this.ip = ip;
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

    public String getAncestro() {
        return ancestro;
    }

    public void setAncestro(String ancestro) {
        this.ancestro = ancestro;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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
        if (!(object instanceof Alumno)) {
            return false;
        }
        Alumno other = (Alumno) object;
        if ((this.nombre == null && other.nombre != null) || (this.nombre != null && !this.nombre.equals(other.nombre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bruno.elbruto.db.persistencia.entities.Alumno[nombre=" + nombre + "]";
    }

}
