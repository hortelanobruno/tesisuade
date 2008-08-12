package modelo;

import java.util.List;
import modelo.ontologia.ModeloOntologiaVocabulario;
import mvcframework.ProxyModelo;



public class BusinessDelegate extends ProxyModelo 
{

    private ModeloOntologiaVocabulario modOntologia;
    
    public BusinessDelegate() {
        modOntologia = new ModeloOntologiaVocabulario();
    }
	
    public List<String> obtenerInstanciasVocabuario(String url){
        return modOntologia.getInstancias(url);
    }
    
}

