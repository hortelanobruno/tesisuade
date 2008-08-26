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
import java.util.HashMap;
import java.util.List;
import jenasouforce.ApiJena;
import vo.DatatypePropertyVO;
import vo.ObjectPropertyVO;

/**
 *
 * @author Administrador
 */
public class ModeloOntologiaViajes {

    private ApiJena jena;
    private OntModel m;
    
    public ModeloOntologiaViajes() {
        jena = new ApiJena();
    }

    
    public List<String> ClassProperty(String clase){
        return jena.getProperty(m,clase);
    }
    
    public void cargarOntologia(String url){
        m = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM, null );
        m = loadOntModelFromOwlFile(url);
    }

    public void nuevaOntologia() {
        m = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM, null );
    }
    
    private OntModel loadOntModelFromOwlFile(String owlfile) {
        OntModel ontmodel = null;
        InputStream is = null;
        // OWL_MEM es la especificacion para modelos OWL almacenados en memoria
        ontmodel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        try {
                is = new FileInputStream(new File(owlfile));
                ontmodel.read(is, "");
                System.out.println("Se ha cargado una instancia OntModel.");
        } catch (Exception e) {
                System.out.println("Se ha producido una excepcion controlada.");
                e.printStackTrace();
        }
        return ontmodel;
    }
    
    public void guardarOntologia(String url){
        jena.grabarOntologia(m, url);
    }
    
    public String getURIOntologia(){
        return jena.getURIOntologia(m);
    }
    
    public HashMap<String,String> showClasses(){
        return jena.showClass(m);
    }
    
    public void addClass(String h, String p){
        jena.addClass(m, h, p);
    }
    
    public List<String> showDatatypeProperties(){
        return jena.getDatatypeProperties(m);
    }
    
    public List<String> showObjectProperties(){
        return jena.getObjectProperties(m);
    }
    
    public DatatypePropertyVO getDatatypeProperty(String pro){
        return jena.getDatatypeProperty(m,pro);
    }
    
    public ObjectPropertyVO getObjectProperty(String pro){
        return jena.getObjectProperty(m,pro);
    }
}
