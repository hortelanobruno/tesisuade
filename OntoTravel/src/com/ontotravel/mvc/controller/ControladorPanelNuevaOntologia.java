/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ontotravel.mvc.controller;

import com.ontotravel.mvc.Controlador;
import com.ontotravel.mvc.ProxyModelo;
import com.ontotravel.mvc.Vista;
import com.ontotravel.mvc.view.VistaNuevaOntologia;

/**
 *
 * @author Administrador
 */
public class ControladorPanelNuevaOntologia extends Controlador {

    public ControladorPanelNuevaOntologia(ProxyModelo mod, Vista vis) {
        super(mod, vis);
    }

    public void doCargarIndividual(boolean b) {
        ((VistaNuevaOntologia) vista).cargarIndividual(b);
        vista.actualizar();
    }

    public void doCargarObjectProperty(boolean b) {
        ((VistaNuevaOntologia) vista).cargarObjectProperty(b);
        vista.actualizar();
    }

    public void doCargarDatatypeProperty(boolean b) {
        ((VistaNuevaOntologia) vista).cargarDatatypeProperty(b);
        vista.actualizar();
    }

    public void doCargarOWL(boolean b) {
        ((VistaNuevaOntologia) vista).cargarOntologia(b);
        vista.actualizar();
    }

    public void doNuevaOWL(boolean b) {
        ((VistaNuevaOntologia) vista).nuevaOntologia(b);
        vista.actualizar();
    }

    public void doCargarClase(boolean b) {
        ((VistaNuevaOntologia) vista).cargarClase(b);
        vista.actualizar();
    }
}
