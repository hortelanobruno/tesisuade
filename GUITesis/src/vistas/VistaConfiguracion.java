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
public class VistaConfiguracion extends Vista {

    private FramePrincipal ref;

    public VistaConfiguracion(BusinessDelegate mod) {
            super(mod);
    }

    public void actualizar() {
            getRef().getPanelConfiguracion().update();
    }
    
    public FramePrincipal getRef() {
        return ref;
    }

    public void setRef(FramePrincipal ref) {
        this.ref = ref;
    }
    
}
