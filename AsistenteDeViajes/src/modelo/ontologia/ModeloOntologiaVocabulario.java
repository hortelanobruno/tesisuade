/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.ontologia;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import jena.ApiJena;
import vo.IndividualSinonimoVO;

/**
 *
 * @author Administrador
 */
public class ModeloOntologiaVocabulario {

    private OntModel m;
    private ApiJena jena;
    private String url;
    
    public ModeloOntologiaVocabulario() {
        jena = new ApiJena();
    }

    public void agregarPalabra(String ins) {
        jena.agregarPalabra(m,ins);
    }

    public IndividualSinonimoVO getIndividual(String ind) {
        return jena.showIndividualOfSinonimo(m,ind);
    }

    public void removerPalabra(String ins) {
        jena.removerPalabra(m,ins);
    }
    
    public void removerTraduccion(String ins, String sin){
        jena.removerTraduccion(m,ins,sin);
    }
    
    public void removerSinonimo(String ins, String sin){
        jena.removerSinonimo(m,ins,sin);
    }
    
    public void agregarTraduccion(String ins, String sin){
        jena.agregarTraduccion(m,ins,sin);
    }
    
    public void addSinonimo(String ins, String sin){
        jena.addSinonimo(m,ins,sin);
    }
    
    public List<String> getInstancias(String url){
        m = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM, null );
        m = loadOntModelFromOwlFile(url);
        this.url = url;
        return jena.showIndividuals(m);
    }

    public void guardarOntologiaSinonimo(){
        jena.grabarOntologia(m, url);
    }
    
    private OntModel loadOntModelFromOwlFile(String owlfile) {
        OntModel ontmodel = null;
        InputStream is = null;
        // OWL_MEM es la especificacion para modelos OWL almacenados en memoria
        ontmodel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        try {
                is = new FileInputStream(new File(owlfile));
                ontmodel.read(is, "");
        } catch (Exception e) {
                System.out.println("Se ha producido una excepcion controlada.");
                e.printStackTrace();
        }
        return ontmodel;
    }
}
