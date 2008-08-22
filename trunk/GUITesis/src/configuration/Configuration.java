/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package configuration;

import java.util.ArrayList;

/**
 *
 * @author Administrador
 */
public class Configuration {

    private ArrayList<String> ontologiasViajes;
    private ArrayList<String> ontologiasVocabulario;
    private String defaultURLOWLSinonimos;
    private String defaultURLOWLViajes;
    
    public Configuration() {
    }

    public ArrayList<String> getOntologiasViajes() {
        return ontologiasViajes;
    }

    public void setOntologiasViajes(ArrayList<String> ontologiasViajes) {
        this.ontologiasViajes = ontologiasViajes;
    }

    public ArrayList<String> getOntologiasVocabulario() {
        return ontologiasVocabulario;
    }

    public void setOntologiasVocabulario(ArrayList<String> ontologiasVocabulario) {
        this.ontologiasVocabulario = ontologiasVocabulario;
    }

    public String getDefaultURLOWLSinonimos() {
        return defaultURLOWLSinonimos;
    }

    public void setDefaultURLOWLSinonimos(String defaultURLOWLSinonimos) {
        this.defaultURLOWLSinonimos = parsearURL(defaultURLOWLSinonimos);
    }

    public String getDefaultURLOWLViajes() {
        return defaultURLOWLViajes;
    }

    public void setDefaultURLOWLViajes(String defaultURLOWLViajes) {
        this.defaultURLOWLViajes = parsearURL(defaultURLOWLViajes);
    }
    
    public String parsearURL(String urlCompleta){
        String a = new String();
        String[] aa = urlCompleta.split("\\\\");
        a = a + aa[0];
        for(int i = 1 ; i < aa.length -1 ; i++){
            a = a + "\\\\" + aa[i];
        }
        return a;
    }

}
