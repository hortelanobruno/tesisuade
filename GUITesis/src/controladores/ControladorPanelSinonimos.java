/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controladores;

import mvcframework.Controlador;
import mvcframework.ProxyModelo;
import mvcframework.Vista;
import vistas.VistaSinonimos;

/**
 *
 * @author Administrador
 */
public class ControladorPanelSinonimos extends Controlador {

    public ControladorPanelSinonimos(ProxyModelo mod, Vista vis) {
        super(mod, vis);
        // TODO Auto-generated constructor stub
    }
    
    public void doCargarOWL(boolean b){
        ((VistaSinonimos)vista).cargarArbol(b);
        vista.actualizar();
    }
    
    public void doCargarInstancia(boolean instancia){
        ((VistaSinonimos)vista).cargarInstancia(instancia);
        vista.actualizar();
    }
}
