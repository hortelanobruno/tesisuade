/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vo;

/**
 *
 * @author Administrador
 */
public class MercaderiaVO {

    private Integer id;
    private int categoria;
    private String tipo;
    private String detalle;
    private int stock;

    public MercaderiaVO() {
    }

    public MercaderiaVO(Integer id, int categoria, String tipo, String detalle, int stock) {
        this.id = id;
        this.categoria = categoria;
        this.tipo = tipo;
        this.detalle = detalle;
        this.stock = stock;
    }

    public int getCategoria() {
        return categoria;
    }

    public String getDetalle() {
        return detalle;
    }

    public Integer getId() {
        return id;
    }

    public int getStock() {
        return stock;
    }

    public String getTipo() {
        return tipo;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}
