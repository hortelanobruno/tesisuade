/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package configuration;


import java.util.Vector;


public class Configuration {

    private Vector<String> ontologiasViajes;
    private Vector<String> ontologiasVocabulario;
    private String defaultURLOWLSinonimos;
    private String defaultURLOWLViajes;
    
    public Configuration() {
    }

    public Vector<String> getOntologiasViajes() {
        return ontologiasViajes;
    }

    public void setOntologiasViajes(Vector<String> ontologiasViajes) {
        this.ontologiasViajes = ontologiasViajes;
    }

    public Vector<String> getOntologiasVocabulario() {
        return ontologiasVocabulario;
    }

    public void setOntologiasVocabulario(Vector<String> ontologiasVocabulario) {
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
            a = a + "\\" + aa[i];
        }
        return a;
    }

}