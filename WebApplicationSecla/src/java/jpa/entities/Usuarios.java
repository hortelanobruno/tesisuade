/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *SELECT responsable FROM usuarios where tipocuenta <> 'administrador' and habilitado = 1
 * @author Administrador
 */
@Entity
@Table(name = "usuarios")
@NamedQueries({@NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u"),
@NamedQuery(name = "Usuarios.findByUsuario", query = "SELECT u FROM Usuarios u WHERE u.usuario = :usuario"),
@NamedQuery(name = "Usuarios.findByPassword", query = "SELECT u FROM Usuarios u WHERE u.password = :password"),
@NamedQuery(name = "Usuarios.findByTipocuenta", query = "SELECT u FROM Usuarios u WHERE u.tipocuenta = :tipocuenta"),
@NamedQuery(name = "Usuarios.findByResponsable", query = "SELECT u FROM Usuarios u WHERE u.responsable = :responsable"),
@NamedQuery(name = "Usuarios.existsUsuarioByResponsable", query = "SELECT count(u) FROM Usuarios u WHERE u.responsable = :responsable"),
@NamedQuery(name = "Usuarios.findBySede", query = "SELECT u FROM Usuarios u WHERE u.sede = :sede"),
@NamedQuery(name = "Usuarios.findBySector", query = "SELECT u FROM Usuarios u WHERE u.sector = :sector"),
@NamedQuery(name = "Usuarios.existDigitos", query = "SELECT count(u) FROM Usuarios u WHERE u.digitos = :digitos"),
@NamedQuery(name = "Usuarios.existUsuarioByDigitos", query = "SELECT count(u) FROM Usuarios u WHERE u.responsable <> :responsable and u.digitos = :digitos"),
@NamedQuery(name = "Usuarios.findByHabilitado", query = "SELECT u FROM Usuarios u WHERE u.habilitado = :habilitado"),
@NamedQuery(name = "Usuarios.operatorInspectorCajeroList", query = "SELECT u.responsable FROM Usuarios u WHERE u.tipocuenta <> 'administrador' and u.habilitado = 1"),
@NamedQuery(name = "Usuarios.operatorInspectorListUsuario", query = "SELECT u.usuario FROM Usuarios u WHERE (u.tipocuenta = 'inspector' OR u.tipocuenta = 'operador' ) and u.habilitado = 1"),
@NamedQuery(name = "Usuarios.operatorInspectorList", query = "SELECT u.responsable FROM Usuarios u WHERE (u.tipocuenta = 'inspector' OR u.tipocuenta = 'operador' ) and u.habilitado = 1"),
@NamedQuery(name = "Usuarios.sectorList", query = "SELECT distinct u.sector FROM Usuarios u WHERE u.habilitado = 1 and (u.tipocuenta = 'inspector' OR u.tipocuenta = 'operador' )")})
public class Usuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "usuario")
    private String usuario;
    @Column(name = "password")
    private String password;
    @Column(name = "tipocuenta")
    private String tipocuenta;
    @Column(name = "responsable")
    private String responsable;
    @Column(name = "sede")
    private String sede;
    @Column(name = "sector")
    private String sector;
    @Column(name = "digitos")
    private String digitos;
    @Column(name = "habilitado")
    private Integer habilitado;

    public Usuarios() {
    }

    public Usuarios(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipocuenta() {
        return tipocuenta;
    }

    public void setTipocuenta(String tipocuenta) {
        this.tipocuenta = tipocuenta;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getDigitos() {
        return digitos;
    }

    public void setDigitos(String digitos) {
        this.digitos = digitos;
    }

    public Integer getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Integer habilitado) {
        this.habilitado = habilitado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuario != null ? usuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.usuario == null && other.usuario != null) || (this.usuario != null && !this.usuario.equals(other.usuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.Usuarios[usuario=" + usuario + "]";
    }

}
