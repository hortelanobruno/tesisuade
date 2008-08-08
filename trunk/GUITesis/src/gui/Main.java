/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import modelo.BusinessDelegate;
import vistas.VistaMotorBusqueda;

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
        
            FramePrincipal frame = new FramePrincipal(vistaMotorBusqueda);
            frame.setVisible(true);
            
            vistaMotorBusqueda.setRef(frame);
        }catch(Exception e){
        }
    }

    public static void main(String args[]){
        new Main();
    }
}
