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
 * @author Brunoli
 */
public class VistaDefaultOntologia extends Vista{

    private FramePrincipal ref;

    public VistaDefaultOntologia(BusinessDelegate mod) {
            super(mod);
    }

    public void actualizar() {
            getRef().getPanelDefaultOntologia().update();
    }

    public FramePrincipal getRef() {
        return ref;
    }

    public void setRef(FramePrincipal ref) {
        this.ref = ref;
    }
}
