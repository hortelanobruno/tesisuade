/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vo;

/**
 *
 * @author Administrador
 */
public class EventoVO {

    private Integer id;
    private String evento;

    public EventoVO() {
    }

    public EventoVO(Integer id, String evento) {
        this.id = id;
        this.evento = evento;
    }

    public String getEvento() {
        return evento;
    }

    public Integer getId() {
        return id;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
