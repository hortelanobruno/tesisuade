/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controladores;

import mvcframework.Controlador;
import mvcframework.ProxyModelo;
import mvcframework.Vista;
import vistas.VistaNuevaOntologia;

/**
 *
 * @author Administrador
 */
public class ControladorPanelNuevaOntologia extends Controlador{

    public ControladorPanelNuevaOntologia(ProxyModelo mod, Vista vis) {
        super(mod, vis);
        // TODO Auto-generated constructor stub
    }
    
    public void doCargarIndividual(boolean b){
        ((VistaNuevaOntologia)vista).cargarIndividual(b);
        vista.actualizar();
    }
    
    public void doCargarObjectProperty(boolean b){
        ((VistaNuevaOntologia)vista).cargarObjectProperty(b);
        vista.actualizar();
    }
    
    public void doCargarDatatypeProperty(boolean b){
        ((VistaNuevaOntologia)vista).cargarDatatypeProperty(b);
        vista.actualizar();
    }
    
    public void doCargarOWL(boolean b){
        ((VistaNuevaOntologia)vista).cargarOntologia(b);
        vista.actualizar();
    }
    
    public void doNuevaOWL(boolean b){
        ((VistaNuevaOntologia)vista).nuevaOntologia(b);
        vista.actualizar();
    }
    
    public void doCargarClase(boolean b){
        ((VistaNuevaOntologia)vista).cargarClase(b);
        vista.actualizar();
    }
}
