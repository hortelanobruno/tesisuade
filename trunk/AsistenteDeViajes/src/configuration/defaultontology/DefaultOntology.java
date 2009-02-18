/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package configuration.defaultontology;

import configuration.defaultontology.types.DefaultAlojamiento;
import configuration.defaultontology.types.DefaultTranslado;
import configuration.defaultontology.types.DefaultViaje;

/**
 *
 * @author Brunoli
 */
public class DefaultOntology {

    private DefaultAlojamiento alojamiento;
    private DefaultTranslado translado;
    private DefaultViaje viaje;

    public DefaultOntology() {
        alojamiento = new DefaultAlojamiento();
        translado = new DefaultTranslado();
        viaje = new DefaultViaje();
    }

    public DefaultAlojamiento getAlojamiento() {
        return alojamiento;
    }

    public DefaultTranslado getTranslado() {
        return translado;
    }

    public DefaultViaje getViaje() {
        return viaje;
    }

    public void setAlojamiento(DefaultAlojamiento alojamiento) {
        this.alojamiento = alojamiento;
    }

    public void setTranslado(DefaultTranslado translado) {
        this.translado = translado;
    }

    public void setViaje(DefaultViaje viaje) {
        this.viaje = viaje;
    }
}
