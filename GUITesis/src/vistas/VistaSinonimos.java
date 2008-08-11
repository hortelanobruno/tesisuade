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
public class VistaSinonimos extends Vista{

    private FramePrincipal ref;

    public VistaSinonimos(BusinessDelegate mod) {
            super(mod);
    }

    public void actualizar() {
            getRef().getPanelMotorBusqueda().update();
    }

    public FramePrincipal getRef() {
        return ref;
    }

    public void setRef(FramePrincipal ref) {
        this.ref = ref;
    }
}
