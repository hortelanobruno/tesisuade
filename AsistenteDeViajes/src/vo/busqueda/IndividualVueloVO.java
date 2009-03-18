/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vo.busqueda;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Administrador
 */
public class IndividualVueloVO {

    private String uri;
    private String nameIndividual;
    private Map<String, Object> propiedadesPrincipales;
    private Map<String, Object> propiedadesAvanzadas;

    public IndividualVueloVO() {
        propiedadesAvanzadas = new HashMap<String, Object>();
        propiedadesPrincipales = new HashMap<String, Object>();
    }

    public String getNameIndividual() {
        return nameIndividual;
    }

    public String getUri() {
        return uri;
    }

    public void setNameIndividual(String nameIndividual) {
        this.nameIndividual = nameIndividual;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * @return the propiedadesPrincipales
     */
    public Map<String, Object> getPropiedadesPrincipales() {
        return propiedadesPrincipales;
    }

    /**
     * @return the propiedadesAvanzadas
     */
    public Map<String, Object> getPropiedadesAvanzadas() {
        return propiedadesAvanzadas;
    }

    public Double coincidencia(Map<String, Object> consulta) {
        double cant = consulta.size();
        double aux = 0;
        for (String prop : consulta.keySet()) {
            if (propiedadesAvanzadas.containsKey(prop)) {
                if (propiedadesAvanzadas.get(prop).equals(consulta.get(prop))) {
                    aux++;
                }
            }
        }
        return aux / cant;
    }
}
