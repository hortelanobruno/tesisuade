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
