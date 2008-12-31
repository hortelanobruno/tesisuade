/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import controladores.EventoJpaController;

/**
 *
 * @author Administrador
 */
public class ManagerEventos {

    private EventoJpaController eventoController;
    
    public ManagerEventos() {
        eventoController = new EventoJpaController();
    }


}
