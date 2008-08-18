/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import controladores.ControladorPanelMotorBusqueda;
import controladores.ControladorPanelNuevaOntologia;
import controladores.ControladorPanelSinonimos;
import modelo.BusinessDelegate;
import vistas.VistaMotorBusqueda;
import vistas.VistaNuevaOntologia;
import vistas.VistaSinonimos;

/**
 *
 * @author Administrador
 */
public class Main {

    public Main() {
        BusinessDelegate modelo;
        try {
            modelo = new BusinessDelegate();
            VistaMotorBusqueda vistaMotorBusqueda = new VistaMotorBusqueda(modelo);
            VistaSinonimos vistaSinonimos = new VistaSinonimos(modelo);
            VistaNuevaOntologia vistaNuevaOntologia = new VistaNuevaOntologia(modelo);
            
            FramePrincipal frame = new FramePrincipal(vistaMotorBusqueda,vistaSinonimos,vistaNuevaOntologia);
            frame.setVisible(true);
            
            vistaMotorBusqueda.setRef(frame);
            vistaSinonimos.setRef(frame);
            vistaNuevaOntologia.setRef(frame);
            
            new ControladorPanelMotorBusqueda(modelo,vistaMotorBusqueda);
            new ControladorPanelSinonimos(modelo, vistaSinonimos);
            new ControladorPanelNuevaOntologia(modelo, vistaNuevaOntologia);
        }catch(Exception e){
        }
    }

    public static void main(String args[]){
        new Main();
    }
}
