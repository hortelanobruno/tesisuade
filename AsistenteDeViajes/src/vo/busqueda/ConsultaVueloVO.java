/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vo.busqueda;

import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author Administrador
 */
public class ConsultaVueloVO {

    private Date fechaIda;
    private Date fechaVuelta;
    private String ciudadOrigen;
    private String ciudadDestino;
    private int adultos;
    private int ninios;
    private int bebes;
    private HashMap<String,String> propiedadesAvanzadas;
    
    public ConsultaVueloVO() {
        
    }

    public ConsultaVueloVO(Date fechaIda, Date fechaVuelta, String ciudadOrigen, String ciudadDestino, int adultos, int ninios, int bebes, HashMap<String, String> propiedadesAvanzadas) {
        this.fechaIda = fechaIda;
        this.fechaVuelta = fechaVuelta;
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.adultos = adultos;
        this.ninios = ninios;
        this.bebes = bebes;
        this.propiedadesAvanzadas = propiedadesAvanzadas;
    }

    public Date getFechaIda() {
        return fechaIda;
    }

    public void setFechaIda(Date fechaIda) {
        this.fechaIda = fechaIda;
    }

    public Date getFechaVuelta() {
        return fechaVuelta;
    }

    public void setFechaVuelta(Date fechaVuelta) {
        this.fechaVuelta = fechaVuelta;
    }

    public String getCiudadOrigen() {
        return ciudadOrigen;
    }

    public void setCiudadOrigen(String ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    public String getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(String ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public int getAdultos() {
        return adultos;
    }

    public void setAdultos(int adultos) {
        this.adultos = adultos;
    }

    public int getNinios() {
        return ninios;
    }

    public void setNinios(int ninios) {
        this.ninios = ninios;
    }

    public int getBebes() {
        return bebes;
    }

    public void setBebes(int bebes) {
        this.bebes = bebes;
    }

    public HashMap<String, String> getPropiedadesAvanzadas() {
        return propiedadesAvanzadas;
    }

    public void setPropiedadesAvanzadas(HashMap<String, String> propiedadesAvanzadas) {
        this.propiedadesAvanzadas = propiedadesAvanzadas;
    }
    
    

    
}
