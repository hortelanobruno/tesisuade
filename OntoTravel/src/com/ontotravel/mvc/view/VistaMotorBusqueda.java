package com.ontotravel.mvc.view;

import com.ontotravel.gui.FramePrincipal;
import com.ontotravel.mvc.Vista;
import com.ontotravel.mvc.model.BusinessDelegate;

public class VistaMotorBusqueda extends Vista {

    private FramePrincipal ref;

    public VistaMotorBusqueda(BusinessDelegate mod) {
        super(mod);
    }

    public void busquedaVuelos(boolean b) {
        getRef().getPanelMotorBusqueda().setBuscarVuelos(b);
    }

    public void busquedaAuto(boolean b) {
        getRef().getPanelMotorBusqueda().setBuscarAuto(b);
    }

    public void busquedaAlojamiento(boolean b) {
        getRef().getPanelMotorBusqueda().setBuscarAlojameinto(b);
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
