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
public class ConsultaVueloVO {

    private Map<String, Object> propiedadesPrincipales;
    private Map<String, Object> propiedadesAvanzadas;

    public ConsultaVueloVO() {
        propiedadesAvanzadas = new HashMap<String, Object>();
        propiedadesPrincipales = new HashMap<String, Object>();
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
}
