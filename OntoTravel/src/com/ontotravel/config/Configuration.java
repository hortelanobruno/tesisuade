/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ontotravel.config;

import com.ontotravel.config.defaultontology.DefaultOntology;
import java.util.Vector;

public class Configuration {

    private DefaultOntology defaultOntology;
    private Vector<AdvancedProperty> propiedadesAvanzadasVuelo;
    private Vector<AdvancedProperty> propiedadesAvanzadasHotel;
    private Vector<AdvancedProperty> propiedadesAvanzadasAuto;
    private Vector<String> ontologiasViajes;
    private Vector<String> ontologiasVocabulario;
    private String defaultURLOWLSinonimos;
    private String defaultURLOWLViajes;
    private String owlDirectory;

    public Configuration() {
        defaultURLOWLSinonimos = new String();
        defaultURLOWLViajes = new String();
        owlDirectory = new String();
        ontologiasViajes = new Vector<String>();
        ontologiasVocabulario = new Vector<String>();
        propiedadesAvanzadasAuto = new Vector<AdvancedProperty>();
        propiedadesAvanzadasHotel = new Vector<AdvancedProperty>();
        propiedadesAvanzadasVuelo = new Vector<AdvancedProperty>();
        defaultOntology = new DefaultOntology();
    }

    public Vector<String> getOntologiasViajes() {
        return ontologiasViajes;
    }

    public void setOntologiasViajes(Vector<String> ontologiasViajes) {
        this.ontologiasViajes = ontologiasViajes;
    }

    public Vector<String> getOntologiasVocabulario() {
        return ontologiasVocabulario;
    }

    public void setOntologiasVocabulario(Vector<String> ontologiasVocabulario) {
        this.ontologiasVocabulario = ontologiasVocabulario;
    }

    public String getDefaultURLOWLSinonimos() {
        return defaultURLOWLSinonimos;
    }

    public void setDefaultURLOWLSinonimos(String defaultURLOWLSinonimos) {
        this.defaultURLOWLSinonimos = parsearURL(defaultURLOWLSinonimos);
    }

    public String getDefaultURLOWLViajes() {
        return defaultURLOWLViajes;
    }

    public void setDefaultURLOWLViajes(String defaultURLOWLViajes) {
        this.defaultURLOWLViajes = parsearURL(defaultURLOWLViajes);
    }

    public String parsearURL(String urlCompleta) {
        String a = new String();
        String[] aa = urlCompleta.split("\\\\");
        a = a + aa[0];
        for (int i = 1; i < aa.length; i++) {
            a = a + "/" + aa[i];
        }
        return a;
    }

    public String getOwlDirectory() {
        return owlDirectory;
    }

    public void setOwlDirectory(String owlDirectory) {
        this.owlDirectory = parsearURL(owlDirectory) + "/";
    }

    public Vector<AdvancedProperty> getPropiedadesAvanzadasAuto() {
        return propiedadesAvanzadasAuto;
    }

    public Vector<String> getNombrePropiedadesAvanzadasAuto() {
        Vector<String> names = new Vector<String>();
        for (AdvancedProperty proAd : getPropiedadesAvanzadasAuto()) {
            names.add(proAd.getNombre());
        }
        return names;
    }

    public Vector<AdvancedProperty> getPropiedadesAvanzadasHotel() {
        return propiedadesAvanzadasHotel;
    }

    public Vector<String> getNombrePropiedadesAvanzadasHotel() {
        Vector<String> names = new Vector<String>();
        for (AdvancedProperty proAd : getPropiedadesAvanzadasHotel()) {
            names.add(proAd.getNombre());
        }
        return names;
    }

    public Vector<AdvancedProperty> getPropiedadesAvanzadasVuelo() {
        return propiedadesAvanzadasVuelo;
    }

    public Vector<String> getNombrePropiedadesAvanzadasVuelo() {
        Vector<String> names = new Vector<String>();
        for (AdvancedProperty proAd : getPropiedadesAvanzadasVuelo()) {
            names.add(proAd.getNombre());
        }
        return names;
    }

    public void setPropiedadesAvanzadasAuto(Vector<AdvancedProperty> propiedadesAvanzadasAuto) {
        this.propiedadesAvanzadasAuto = propiedadesAvanzadasAuto;
    }

    public void setPropiedadesAvanzadasHotel(Vector<AdvancedProperty> propiedadesAvanzadasHotel) {
        this.propiedadesAvanzadasHotel = propiedadesAvanzadasHotel;
    }

    public void setPropiedadesAvanzadasVuelo(Vector<AdvancedProperty> propiedadesAvanzadasVuelo) {
        this.propiedadesAvanzadasVuelo = propiedadesAvanzadasVuelo;
    }

    public DefaultOntology getDefaultOntology() {
        return defaultOntology;
    }

    public void setDefaultOntology(DefaultOntology defaultOntology) {
        this.defaultOntology = defaultOntology;
    }
}
