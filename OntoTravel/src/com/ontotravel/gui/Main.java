/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ontotravel.gui;

import com.ontotravel.mvc.controller.ControladorPanelMotorBusqueda;
import com.ontotravel.mvc.controller.ControladorPanelNuevaOntologia;
import com.ontotravel.mvc.controller.ControladorPanelSinonimos;
import com.ontotravel.mvc.model.BusinessDelegate;
import com.ontotravel.mvc.view.VistaConfiguracion;
import com.ontotravel.mvc.view.VistaMotorBusqueda;
import com.ontotravel.mvc.view.VistaNuevaOntologia;
import com.ontotravel.mvc.view.VistaSinonimos;

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
            VistaConfiguracion vistaConfiguracion = new VistaConfiguracion(modelo);

            FramePrincipal frame = new FramePrincipal(vistaMotorBusqueda, vistaSinonimos, vistaNuevaOntologia, vistaConfiguracion);
            frame.setVisible(true);

            vistaMotorBusqueda.setRef(frame);
            vistaSinonimos.setRef(frame);
            vistaNuevaOntologia.setRef(frame);

            new ControladorPanelMotorBusqueda(modelo, vistaMotorBusqueda);
            new ControladorPanelSinonimos(modelo, vistaSinonimos);
            new ControladorPanelNuevaOntologia(modelo, vistaNuevaOntologia);

            frame.cargarConfiguracion();
        } catch (Exception e) {
            System.out.println(e.getCause().getMessage());
        }
    }
}
