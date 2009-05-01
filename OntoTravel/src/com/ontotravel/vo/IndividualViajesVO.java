/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ontotravel.vo;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class IndividualViajesVO {

    private String nombre;
    private List<DatatypePropertyVO> datatypeProperties;
    private List<ObjectPropertyVO> objectProperties;

    public IndividualViajesVO() {
    }

    public IndividualViajesVO(String nombre, List<DatatypePropertyVO> datatypeProperties, List<ObjectPropertyVO> objectProperties) {
        this.nombre = nombre;
        this.datatypeProperties = datatypeProperties;
        this.objectProperties = objectProperties;
    }

    public List<DatatypePropertyVO> getDatatypeProperties() {
        return datatypeProperties;
    }

    public String getNombre() {
        return nombre;
    }

    public List<ObjectPropertyVO> getObjectProperties() {
        return objectProperties;
    }

    public void setDatatypeProperties(List<DatatypePropertyVO> datatypeProperties) {
        this.datatypeProperties = datatypeProperties;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setObjectProperties(List<ObjectPropertyVO> objectProperties) {
        this.objectProperties = objectProperties;
    }
}
