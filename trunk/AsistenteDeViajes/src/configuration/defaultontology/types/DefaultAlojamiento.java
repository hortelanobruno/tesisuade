/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package configuration.defaultontology.types;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Brunoli
 */
public class DefaultAlojamiento {

    private String nombreClase;
    private List<DefaultProperty> defaultProperties;

    public DefaultAlojamiento() {
        defaultProperties = new ArrayList<DefaultProperty>();
    }

    public List<DefaultProperty> getDefaultProperties() {
        return defaultProperties;
    }

    public void setDefaultProperties(List<DefaultProperty> defaultProperties) {
        this.defaultProperties = defaultProperties;
    }

    /**
     * @return the nombreClase
     */
    public String getNombreClase() {
        return nombreClase;
    }

    /**
     * @param nombreClase the nombreClase to set
     */
    public void setNombreClase(String nombreClase) {
        this.nombreClase = nombreClase;
    }
}
