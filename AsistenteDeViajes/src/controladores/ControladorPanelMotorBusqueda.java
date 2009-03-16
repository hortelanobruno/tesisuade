/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controladores;

import mvcframework.Controlador;
import mvcframework.ProxyModelo;
import mvcframework.Vista;
import vistas.VistaMotorBusqueda;

/**
 *
 * @author Administrador
 */
public class ControladorPanelMotorBusqueda extends Controlador {

    public ControladorPanelMotorBusqueda(ProxyModelo mod, Vista vis) {
        super(mod, vis);
    }
    
    public void doBuscarVuelos(boolean b){
        ((VistaMotorBusqueda)vista).busquedaVuelos(b);
        vista.actualizar();
    }

    public void doBuscarAuto(boolean b){
        ((VistaMotorBusqueda)vista).busquedaAuto(b);
        vista.actualizar();
    }

    public void doBuscarAlojamiento(boolean b){
        ((VistaMotorBusqueda)vista).busquedaAlojamiento(b);
        vista.actualizar();
    }
}
