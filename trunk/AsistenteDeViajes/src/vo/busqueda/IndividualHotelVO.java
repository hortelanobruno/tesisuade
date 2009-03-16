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
public class IndividualHotelVO {

    private String uri;
    private String nameIndividual;
    private String ciudad;
    private Calendar fechaInicio;
    private Calendar fechaSalida;
    private Integer cantPersonas;
    private Map<String, Object> propiedadesAvanzadas;

    public IndividualHotelVO() {
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
     * @return the ciudad
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * @param ciudad the ciudad to set
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * @return the fechaInicio
     */
    public Calendar getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Calendar fechaInicio) {
        this.fechaInicio = fechaInicio;
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
     * @return the cantPersonas
     */
    public Integer getCantPersonas() {
        return cantPersonas;
    }

    /**
     * @param cantPersonas the cantPersonas to set
     */
    public void setCantPersonas(Integer cantPersonas) {
        this.cantPersonas = cantPersonas;
    }

    /**
     * @return the propiedadesAvanzadas
     */
    public Map<String, Object> getPropiedadesAvanzadas() {
        return propiedadesAvanzadas;
    }
}
