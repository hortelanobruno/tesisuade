/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bruno.elbruto.manager;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Brunoli
 */
@Entity
public class Bruto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String nombre;
    private String password;
    private int nivel;
    private boolean propietario;

    public Bruto() {
    }

    public Bruto(String nombre, String password, int nivel) {
        this.nombre = nombre;
        this.password = password;
        this.nivel = nivel;
    }

    public boolean isPropietario() {
        return propietario;
    }

    public void setPropietario(boolean propietario) {
        this.propietario = propietario;
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

    public String getNombre() {
        return nombre;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Bruto other = (Bruto) obj;
        if ((this.nombre == null) ? (other.nombre != null) : !this.nombre.equals(other.nombre)) {
            return false;
        }
        if ((this.password == null) ? (other.password != null) : !this.password.equals(other.password)) {
            return false;
        }
        if (this.nivel != other.nivel) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + (this.nombre != null ? this.nombre.hashCode() : 0);
        hash = 13 * hash + (this.password != null ? this.password.hashCode() : 0);
        hash = 13 * hash + this.nivel;
        return hash;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
