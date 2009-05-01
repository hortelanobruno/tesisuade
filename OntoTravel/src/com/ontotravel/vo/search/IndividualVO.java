/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ontotravel.vo.search;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Administrador
 */
public class IndividualVO {

    private String uri;
    private String nameIndividual;
    private Map<String, Object> propiedadesPrincipales;
    private Map<String, Object> propiedadesAvanzadas;
    private Double coincidencia;

    public IndividualVO() {
        propiedadesAvanzadas = new HashMap<String, Object>();
        propiedadesPrincipales = new HashMap<String, Object>();
    }

    public String getNameIndividual() {
        return nameIndividual;
    }

    public String getUri() {
        return uri;
    }

    public void setNameIndividual(String nameIndividual) {
        this.nameIndividual = nameIndividual;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * @return the propiedadesPrincipales
     */
    public Map<String, Object> getPropiedadesPrincipales() {
        return propiedadesPrincipales;
    }

    /**
     * @return the propiedadesAvanzadas
     */
    public Map<String, Object> getPropiedadesAvanzadas() {
        return propiedadesAvanzadas;
    }

    public Double coincidencia(Map<String, Object> consulta) {
        double cant = consulta.size();
        if(cant == 0D){
            return 1D;
        }
        double aux = 0;
        for (String prop : consulta.keySet()) {
            if (propiedadesAvanzadas.containsKey(prop)) {
                if (propiedadesAvanzadas.get(prop).equals(consulta.get(prop))) {
                    aux++;
                }
            }
        }
        if(aux == 0D){
            return 0D;
        }
        return aux / cant;
    }

    /**
     * @return the coincidencia
     */
    public Double getCoincidencia() {
        return coincidencia;
    }

    /**
     * @param coincidencia the coincidencia to set
     */
    public void setCoincidencia(Double coincidencia) {
        this.coincidencia = coincidencia;
    }
}
