/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ontotravel.config.defaultontology.types;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Brunoli
 */
public class DefaultType {

    private String nombreClase;
    private List<DefaultProperty> defaultProperties;

    public DefaultType() {
        defaultProperties = new ArrayList<DefaultProperty>();
    }

    public List<String> getDefaultPropertiesNames(){
        List<String> props = null;
        if(!defaultProperties.isEmpty()){
            props=new ArrayList<String>();
            for (DefaultProperty pro : defaultProperties) {
                props.add(pro.getName());
            }
        }
        return props;
    }

    public List<DefaultProperty> getDefaultProperties() {
        return defaultProperties;
    }

    public void setDefaultProperties(List<DefaultProperty> defaultProperties) {
        this.defaultProperties = defaultProperties;
    }

    public void removeProperty(String nombre) {
        for(DefaultProperty pro : defaultProperties){
            if(pro.getName().equalsIgnoreCase(nombre)){
                defaultProperties.remove(pro);
                return;
            }
        }
    }
    /**
     * @return the nombreClase
     */
    public String getNombreClase() {
        return nombreClase;
    }

    /**
     * @param nombreClase the nombreClase to set
     */
    public void setNombreClase(String nombreClase) {
        this.nombreClase = nombreClase;
    }
}
