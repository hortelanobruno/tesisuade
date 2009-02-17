/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package configuration;

/**
 *
 * @author Brunoli
 */
public class AdvancedProperty {

    private String nombre;
    private TipoDato tipoDato;

    public AdvancedProperty() {
    }

    public AdvancedProperty(String nombre, TipoDato tipoDato) {
        this.nombre = nombre;
        this.tipoDato = tipoDato;
    }

    public String getNombre() {
        return nombre;
    }

    public TipoDato getTipoDato() {
        return tipoDato;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipoDato(TipoDato tipoDato) {
        this.tipoDato = tipoDato;
    }
}
