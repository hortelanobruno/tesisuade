/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vo;

import java.util.HashMap;

/**
 *
 * @author Administrador
 */
public class IndividualViajesVO {

    private String nombre;
    private HashMap<String,HashMap<String,String>> datatypeProperties;
    private HashMap<String,String> objectProperties;

    public IndividualViajesVO() {
    }

    public IndividualViajesVO(String nombre, HashMap<String, HashMap<String, String>> datatypeProperties, HashMap<String, String> objectProperties) {
        this.nombre = nombre;
        this.datatypeProperties = datatypeProperties;
        this.objectProperties = objectProperties;
    }

    public HashMap<String, HashMap<String, String>> getDatatypeProperties() {
        return datatypeProperties;
    }

    public String getNombre() {
        return nombre;
    }

    public HashMap<String,String> getObjectProperties() {
        return objectProperties;
    }

    public void setDatatypeProperties(HashMap<String, HashMap<String, String>> datatypeProperties) {
        this.datatypeProperties = datatypeProperties;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setObjectProperties(HashMap<String, String> objectProperties) {
        this.objectProperties = objectProperties;
    }
}
