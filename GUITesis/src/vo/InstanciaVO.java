/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vo;

import java.util.ArrayList;

/**
 *
 * @author Administrador
 */
public class InstanciaVO {

    private String nombreInstancia;
    private String uri;
    private ArrayList<String> sinonimos;
    private ArrayList<String> traduccion;

    public String getNombreInstancia() {
        return nombreInstancia;
    }

    public void setNombreInstancia(String nombreInstancia) {
        this.nombreInstancia = nombreInstancia;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public ArrayList<String> getSinonimos() {
        return sinonimos;
    }

    public void setSinonimos(ArrayList<String> sinonimos) {
        this.sinonimos = sinonimos;
    }

    public ArrayList<String> getTraduccion() {
        return traduccion;
    }

    public void setTraduccion(ArrayList<String> traduccion) {
        this.traduccion = traduccion;
    }
}
