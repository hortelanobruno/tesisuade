/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ontotravel.mvc.view;

import com.ontotravel.gui.FramePrincipal;
import com.ontotravel.mvc.Vista;
import com.ontotravel.mvc.model.BusinessDelegate;

/**
 *
 * @author Administrador
 */
public class VistaNuevaOntologia extends Vista {

    private FramePrincipal ref;

    public VistaNuevaOntologia(BusinessDelegate mod) {
        super(mod);
    }

    public void actualizar() {
        getRef().getPanelNuevaOntologia().update();
    }

    public void cargarIndividual(boolean b) {
        ref.getPanelNuevaOntologia().setCargarIndividual(b);
        ref.getPanelNuevaOntologia().setCargarDatatypeProperty(false);
        ref.getPanelNuevaOntologia().setCargarObjectProperty(false);
        ref.getPanelNuevaOntologia().setNuevaOntologia(false);
        ref.getPanelNuevaOntologia().setCargarOntologia(false);
        ref.getPanelNuevaOntologia().setCargarClase(false);
    }

    public void cargarObjectProperty(boolean b) {
        ref.getPanelNuevaOntologia().setCargarIndividual(false);
        ref.getPanelNuevaOntologia().setCargarDatatypeProperty(false);
        ref.getPanelNuevaOntologia().setCargarObjectProperty(b);
        ref.getPanelNuevaOntologia().setNuevaOntologia(false);
        ref.getPanelNuevaOntologia().setCargarOntologia(false);
        ref.getPanelNuevaOntologia().setCargarClase(false);
    }

    public void cargarDatatypeProperty(boolean b) {
        ref.getPanelNuevaOntologia().setCargarDatatypeProperty(b);
        ref.getPanelNuevaOntologia().setCargarObjectProperty(false);
        ref.getPanelNuevaOntologia().setNuevaOntologia(false);
        ref.getPanelNuevaOntologia().setCargarOntologia(false);
        ref.getPanelNuevaOntologia().setCargarClase(false);
        ref.getPanelNuevaOntologia().setCargarIndividual(false);
    }

    public void cargarClase(boolean b) {
        ref.getPanelNuevaOntologia().setCargarIndividual(false);
        ref.getPanelNuevaOntologia().setCargarDatatypeProperty(false);
        ref.getPanelNuevaOntologia().setCargarObjectProperty(false);
        ref.getPanelNuevaOntologia().setNuevaOntologia(false);
        ref.getPanelNuevaOntologia().setCargarOntologia(false);
        ref.getPanelNuevaOntologia().setCargarClase(b);
    }

    public void cargarOntologia(boolean b) {
        ref.getPanelNuevaOntologia().setCargarDatatypeProperty(false);
        ref.getPanelNuevaOntologia().setCargarObjectProperty(false);
        ref.getPanelNuevaOntologia().setNuevaOntologia(false);
        ref.getPanelNuevaOntologia().setCargarClase(false);
        ref.getPanelNuevaOntologia().setCargarOntologia(b);
        ref.getPanelNuevaOntologia().setCargarIndividual(false);
    }

    public void nuevaOntologia(boolean b) {
        ref.getPanelNuevaOntologia().setCargarDatatypeProperty(false);
        ref.getPanelNuevaOntologia().setCargarObjectProperty(false);
        ref.getPanelNuevaOntologia().setCargarOntologia(false);
        ref.getPanelNuevaOntologia().setCargarClase(false);
        ref.getPanelNuevaOntologia().setNuevaOntologia(b);
        ref.getPanelNuevaOntologia().setCargarIndividual(false);
    }

    public FramePrincipal getRef() {
        return ref;
    }

    public void setRef(FramePrincipal ref) {
        this.ref = ref;
    }
}
