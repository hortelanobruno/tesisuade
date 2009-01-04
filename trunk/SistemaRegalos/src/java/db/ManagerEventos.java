/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import controladores.EventoJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.Evento;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vo.EventoVO;

/**
 *
 * @author Administrador
 */
public class ManagerEventos {

    private EventoJpaController eventoController;
    
    public ManagerEventos() {
        eventoController = new EventoJpaController();
    }

    public List<EventoVO> getEventos() {
        List<Evento> eventos = eventoController.findEventoEntities();
        List<EventoVO> eventosVO = new ArrayList<EventoVO>();
        for(Evento cat : eventos){
            eventosVO.add(cat.getVO());
        }
        return eventosVO;
    }

    public void actualizarEvento(String viejo, String nuevo) {
        try {
            Evento evento = eventoController.findEventoByName(viejo);
            evento.setEvento(nuevo);
            eventoController.edit(evento);
        } catch (NonexistentEntityException ex) {
            System.out.println("1");
        } catch (Exception ex) {
            Evento evento = new Evento();
            evento.setEvento(nuevo);
            eventoController.create(evento);
        }
    }


}
