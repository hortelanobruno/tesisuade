package modelo;

import com.hp.hpl.jena.ontology.OntModel;
import java.util.HashMap;
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
	
    public void removerTraduccion(String ins, String sin){
        modOntologiaVocabulario.removerTraduccion(ins,sin);
    }
    
    public void removerSinonimo(String ins, String sin){
        modOntologiaVocabulario.removerSinonimo(ins,sin);
    }
    
    public void agregarTraduccion(String instancia, String sin){
        modOntologiaVocabulario.agregarTraduccion(instancia,sin);
    }
    public void agregarSinonimo(String instancia, String sin){
        modOntologiaVocabulario.addSinonimo(instancia,sin);
    }
    public List<String> obtenerInstanciasVocabuario(String url){
        return modOntologiaVocabulario.getInstancias(url);
    }
    
    public void guardarOntologiaSinonimos(){
        modOntologiaVocabulario.guardarOntologiaSinonimo();
    }
    
    public IndividualSinonimoVO obtenerIndividual(String ind){
        return modOntologiaVocabulario.getIndividual(ind);
    }
    
    public void nuevaOntologia(){
        modeloOntologiaViajes.nuevaOntologia();
    }
    
    public void cargarOntologia(String url){
        modeloOntologiaViajes.cargarOntologia(url);
    }
    
    public void grabarOntologia(String url){
        modeloOntologiaViajes.guardarOntologia(url);
    }
    
    public String getURIOntologia(){
        return modeloOntologiaViajes.getURIOntologia();
    }
    
    public HashMap<String,String> showClasses(){
        return modeloOntologiaViajes.showClasses();
    }
    
    public void addClass(String hijo, String padre){
        modeloOntologiaViajes.addClass(hijo,padre);
    }
}

