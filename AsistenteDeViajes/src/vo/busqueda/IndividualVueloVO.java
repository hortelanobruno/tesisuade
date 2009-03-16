/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vo.busqueda;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Administrador
 */
public class IndividualVueloVO {

    private String uri;
    private String nameIndividual;
    private Calendar fechaSalida;
    private String ciudadOrigen;
    private String ciudadDestino;
    private Map<String, Object> propiedadesAvanzadas;

    public IndividualVueloVO() {
        propiedadesAvanzadas = new HashMap<String, Object>();
    }

    /**
     * @return the uri
     */
    public String getUri() {
        return uri;
    }

    /**
     * @param uri the uri to set
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * @return the nameIndividual
     */
    public String getNameIndividual() {
        return nameIndividual;
    }

    /**
     * @param nameIndividual the nameIndividual to set
     */
    public void setNameIndividual(String nameIndividual) {
        this.nameIndividual = nameIndividual;
    }

    /**
     * @return the fechaSalida
     */
    public Calendar getFechaSalida() {
        return fechaSalida;
    }

    /**
     * @param fechaSalida the fechaSalida to set
     */
    public void setFechaSalida(Calendar fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    /**
     * @return the ciudadOrigen
     */
    public String getCiudadOrigen() {
        return ciudadOrigen;
    }

    /**
     * @param ciudadOrigen the ciudadOrigen to set
     */
    public void setCiudadOrigen(String ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    /**
     * @return the ciudadDestino
     */
    public String getCiudadDestino() {
        return ciudadDestino;
    }

    /**
     * @param ciudadDestino the ciudadDestino to set
     */
    public void setCiudadDestino(String ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    /**
     * @return the propiedadesAvanzadas
     */
    public Map<String, Object> getPropiedadesAvanzadas() {
        return propiedadesAvanzadas;
    }
}
