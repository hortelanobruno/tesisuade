/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vo;

/**
 *
 * @author Administrador
 */
public class CategoriaVO {

    private Integer id;
    private String tipo;

    public CategoriaVO() {
    }

    public CategoriaVO(Integer id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    
}
