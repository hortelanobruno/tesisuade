/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package configuration.defaultontology.types;

import configuration.TipoDato;

/**
 *
 * @author Brunoli
 */
public class DefaultProperty {

    private TipoDato tipoDato;
    private String name;

    public DefaultProperty() {
    }

    public String getName() {
        return name;
    }

    public TipoDato getTipoDato() {
        return tipoDato;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTipoDato(TipoDato tipoDato) {
        this.tipoDato = tipoDato;
    }
}
