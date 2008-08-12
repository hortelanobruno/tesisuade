package modelo;

import java.util.List;
import modelo.ontologia.ModeloOntologiaVocabulario;
import mvcframework.ProxyModelo;
import vo.IndividualVO;



public class BusinessDelegate extends ProxyModelo 
{

    private ModeloOntologiaVocabulario modOntologiaVocabulario;
    
    public BusinessDelegate() {
        modOntologiaVocabulario = new ModeloOntologiaVocabulario();
    }
	
    public List<String> obtenerInstanciasVocabuario(String url){
        return modOntologiaVocabulario.getInstancias(url);
    }
    
    public IndividualVO obtenerIndividual(String ind){
        return modOntologiaVocabulario.getIndividual(ind);
    }
}

