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
    
    public void doAgregarSinonimo(boolean b){
        ((VistaSinonimos)vista).agregarSin(b);
        vista.actualizar();
    }
    
    public void doAgregarTraduccion(boolean b){
        ((VistaSinonimos)vista).agregarTra(b);
        vista.actualizar();
    }
    
    public void doRemoverSinonimo(boolean b){
        ((VistaSinonimos)vista).removerSin(b);
        vista.actualizar();
    }
    
    public void doRemoverTraduccion(boolean b){
        ((VistaSinonimos)vista).removerTra(b);
        vista.actualizar();
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
