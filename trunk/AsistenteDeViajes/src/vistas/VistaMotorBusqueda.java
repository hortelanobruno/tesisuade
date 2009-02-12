package vistas;

import gui.FramePrincipal;
import modelo.BusinessDelegate;
import mvcframework.Vista;


public class VistaMotorBusqueda extends Vista {

    private FramePrincipal ref;

    public VistaMotorBusqueda(BusinessDelegate mod) {
            super(mod);
    }

    public void busquedaVuelos(boolean b){
        getRef().getPanelMotorBusqueda().setBuscarVuelos(b);
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
