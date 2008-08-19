package modelo;

import com.hp.hpl.jena.ontology.OntModel;
import java.util.List;
import modelo.ontologia.ModeloOntologiaViajes;
import modelo.ontologia.ModeloOntologiaVocabulario;
import mvcframework.ProxyModelo;
import vo.IndividualSinonimoVO;



public class BusinessDelegate extends ProxyModelo 
{

    private ModeloOntologiaVocabulario modOntologiaVocabulario;
    private ModeloOntologiaViajes modeloOntologiaViajes;
    
    public BusinessDelegate() {
        modOntologiaVocabulario = new ModeloOntologiaVocabulario();
        modeloOntologiaViajes = new ModeloOntologiaViajes();
    }
	
    public List<String> obtenerInstanciasVocabuario(String url){
        return modOntologiaVocabulario.getInstancias(url);
    }
    
    public IndividualSinonimoVO obtenerIndividual(String ind){
        return modOntologiaVocabulario.getIndividual(ind);
    }
    
    public OntModel nuevaOntologia(){
        return modeloOntologiaViajes.nuevaOntologia();
    }
    
    public OntModel obtenerOntologia(String url){
        return modeloOntologiaViajes.getOntologia(url);
    }
}

