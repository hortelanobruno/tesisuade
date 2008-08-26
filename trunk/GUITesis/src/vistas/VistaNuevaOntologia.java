/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vistas;

import gui.FramePrincipal;
import modelo.BusinessDelegate;
import mvcframework.Vista;

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
    
    public void cargarObjectProperty(boolean b){
        ref.getPanelNuevaOntologia().setCargarDatatypeProperty(false);
        ref.getPanelNuevaOntologia().setCargarObjectProperty(b);
        ref.getPanelNuevaOntologia().setNuevaOntologia(false);
        ref.getPanelNuevaOntologia().setCargarOntologia(false);
        ref.getPanelNuevaOntologia().setCargarClase(false);
    }
    
    public void cargarDatatypeProperty(boolean b){
        ref.getPanelNuevaOntologia().setCargarDatatypeProperty(b);
        ref.getPanelNuevaOntologia().setCargarObjectProperty(false);
        ref.getPanelNuevaOntologia().setNuevaOntologia(false);
        ref.getPanelNuevaOntologia().setCargarOntologia(false);
        ref.getPanelNuevaOntologia().setCargarClase(false);
    }
    
    public void cargarClase(boolean b){
        ref.getPanelNuevaOntologia().setCargarDatatypeProperty(false);
        ref.getPanelNuevaOntologia().setCargarObjectProperty(false);
        ref.getPanelNuevaOntologia().setNuevaOntologia(false);
        ref.getPanelNuevaOntologia().setCargarOntologia(false);
        ref.getPanelNuevaOntologia().setCargarClase(b);
    }

    public void cargarOntologia(boolean b){
        ref.getPanelNuevaOntologia().setCargarDatatypeProperty(false);
        ref.getPanelNuevaOntologia().setCargarObjectProperty(false);
        ref.getPanelNuevaOntologia().setNuevaOntologia(false);
        ref.getPanelNuevaOntologia().setCargarClase(false);
        ref.getPanelNuevaOntologia().setCargarOntologia(b);
    }
    
    public void nuevaOntologia(boolean b){
        ref.getPanelNuevaOntologia().setCargarDatatypeProperty(false);
        ref.getPanelNuevaOntologia().setCargarObjectProperty(false);
        ref.getPanelNuevaOntologia().setCargarOntologia(false);
        ref.getPanelNuevaOntologia().setCargarClase(false);
        ref.getPanelNuevaOntologia().setNuevaOntologia(b);
    }
    
    public FramePrincipal getRef() {
        return ref;
    }

    public void setRef(FramePrincipal ref) {
        this.ref = ref;
    }
    
}
