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

    public void cargarOntologia(boolean b){
        ref.getPanelNuevaOntologia().setNuevaOntologia(false);
        ref.getPanelNuevaOntologia().setCargarOntologia(b);
    }
    
    public void nuevaOntologia(boolean b){
        ref.getPanelNuevaOntologia().setCargarOntologia(false);
        ref.getPanelNuevaOntologia().setNuevaOntologia(b);
    }
    
    public FramePrincipal getRef() {
        return ref;
    }

    public void setRef(FramePrincipal ref) {
        this.ref = ref;
    }
    
}
