/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ontotravel.config.defaultontology.types;

import com.ontotravel.config.TipoDato;

/**
 *
 * @author Brunoli
 */
public class DefaultProperty {

    private TipoDato tipoDato;
    private String name;

    public DefaultProperty() {
    }

    public DefaultProperty(String name, TipoDato tipoDato) {
        this.tipoDato = tipoDato;
        this.name = name;
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

    @Override
    public String toString() {
        return name;
    }
}
