/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import vo.MercaderiaVO;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "mercaderias")
@NamedQueries({@NamedQuery(name = "Mercaderia.findAll", query = "SELECT m FROM Mercaderia m"),
@NamedQuery(name = "Mercaderia.findById", query = "SELECT m FROM Mercaderia m WHERE m.id = :id"), 
@NamedQuery(name = "Mercaderia.destroyByName", query = "DELETE FROM Mercaderia m WHERE m.tipo = :name"),
@NamedQuery(name = "Mercaderia.findByCategoria", query = "SELECT m FROM Mercaderia m WHERE m.categoria = :categoria"),
@NamedQuery(name = "Mercaderia.findByTipo", query = "SELECT m FROM Mercaderia m WHERE m.tipo = :tipo"),
@NamedQuery(name = "Mercaderia.findByDetalle", query = "SELECT m FROM Mercaderia m WHERE m.detalle = :detalle"),
@NamedQuery(name = "Mercaderia.findByStock", query = "SELECT m FROM Mercaderia m WHERE m.stock = :stock")})
public class Mercaderia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "categoria")
    private int categoria;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @Column(name = "detalle")
    private String detalle;
    @Basic(optional = false)
    @Column(name = "stock")
    private int stock;

    public Mercaderia() {
    }

    public Mercaderia(Integer id) {
        this.id = id;
    }

    public Mercaderia(Integer id, int categoria, String tipo, String detalle, int stock) {
        this.id = id;
        this.categoria = categoria;
        this.tipo = tipo;
        this.detalle = detalle;
        this.stock = stock;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mercaderia)) {
            return false;
        }
        Mercaderia other = (Mercaderia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Mercaderia[id=" + id + "]";
    }

    public MercaderiaVO getVO(){
        return new MercaderiaVO(this.id,this.categoria,this.tipo,this.detalle,this.stock);
    }

    public void setVO(MercaderiaVO vo){
        setCategoria(vo.getCategoria());
        setDetalle(vo.getDetalle());
        setId(vo.getId());
        setStock(vo.getStock());
        setTipo(vo.getTipo());
    }
}
