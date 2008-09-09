/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.ontologia;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.reasoner.ReasonerRegistry;
import com.hp.hpl.jena.reasoner.dig.DIGReasoner;
import com.hp.hpl.jena.reasoner.dig.DIGReasonerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import jenasouforce.ApiJena;
import vo.DatatypePropertyVO;
import vo.IndividualViajesVO;
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

    public void addDatatypeProperty(String obj) {
        jena.addDatatypeProperty(m,obj);
    }

    public void addDomain(String pro, String domain) {
        jena.addDomain(m,pro,domain);
    }

    public void addObjectProperty(String obj) {
        jena.addObjectProperty(m,obj);
    }

    public void addRange(String pro, String range) {
        jena.addRange(m,pro,range);
    }
    
    //Aca agregue el rasonador, testearlo.
    public void cargarOntologia(String url){
        DIGReasoner r = (DIGReasoner) ReasonerRegistry.theRegistry().create( DIGReasonerFactory.URI, null );
        OntModelSpec spec = new OntModelSpec( OntModelSpec.OWL_MEM );
        spec.setReasoner( r );
        m = ModelFactory.createOntologyModel(spec, null );
        //m = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM, null );
        m = loadOntModelFromOwlFile(url);
    }

    public void changeNameDatatypeProperty(String old, String name) {
        jena.changeNameDatatypeProperty(m,old,name);
    }

    public void changeNameObjectProperty(String old, String name) {
        jena.changeNameObjectProperty(m,old,name);
    }

    public void changeRange(String pro, String range) {
        jena.changeRange(m,pro,range);
    }

    public ArrayList<String> listIndividuals(String clase) {
        return jena.listIndividuals(m,clase);
    }

    public void nuevaOntologia() {
        m = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM, null );
    }

    public IndividualViajesVO obtenerIndividual(String ind) {
        return jena.obtenerIndividualViajes(m,ind);
    }

    public void removeClass(String clase) {
        jena.removeClass(m,clase);
    }

    public void removeDatatypeProperty(String obj) {
        jena.removeDatatypeProperty(m,obj);
    }

    public void removeDomain(String pro, String domain) {
        jena.removeDomain(m,pro,domain);
    }

    public void removeObjectProperty(String property) {
        jena.removeObjectProperty(m,property);
    }

    public void removeRange(String pro, String range) {
        jena.removeRange(m,pro,range);
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
