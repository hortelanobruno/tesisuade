/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ontotravel.vo;

import java.util.List;

/**
 *
 * @author Administrador
 */
public class ObjectPropertyVO {

    private String name;
    private List<String> domain;
    private List<String> range;
    private String valor;
    
    public ObjectPropertyVO() {
    }

    public ObjectPropertyVO(String name, List<String> domian, List<String> range) {
        this.name = name;
        this.domain = domian;
        this.range = range;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getDomain() {
        return domain;
    }

    public void setDomain(List<String> domian) {
        this.domain = domian;
    }

    public List<String> getRange() {
        return range;
    }

    public void setRange(List<String> range) {
        this.range = range;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    
}
