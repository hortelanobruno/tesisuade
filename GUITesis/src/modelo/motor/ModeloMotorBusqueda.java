/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.motor;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import configuration.Configuration;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import jenasouforce.ApiJena;
import vo.busqueda.ConsultaVueloVO;
import vo.busqueda.IndividualVueloVO;

/**
 *
 * @author Administrador
 */
public class ModeloMotorBusqueda {

    private ApiJena jena;
    private ArrayList<OntModel> ontologias;
    private Configuration config;
    
    public ModeloMotorBusqueda() {
        jena = new ApiJena();
        ontologias = new ArrayList<OntModel>();
    }

    public List<IndividualVueloVO> buscarVuelos(ConsultaVueloVO consulta){
        ArrayList<IndividualVueloVO> vuelos = new ArrayList<IndividualVueloVO>();
        ArrayList<IndividualVueloVO> aux = null;
        for(int i = 0 ; i < ontologias.size() ; i++){
            aux = new ArrayList<IndividualVueloVO>();
            aux = jena.buscarVuelo(ontologias.get(i),consulta);
            vuelos.addAll(aux);
        }
        return vuelos;
    }
    
    public void cargarModelos(){
        Vector<String> ontos = config.getOntologiasViajes();
        OntModel m = null;
        ontologias = new ArrayList<OntModel>();
        for(int i = 0 ; i < ontos.size() ; i++){
            m = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM, null );
            m = loadOntModelFromOwlFile(ontos.elementAt(i));
            ontologias.add(m);
        }
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
    
    public Configuration getConfig() {
        return config;
    }

    public void setConfig(Configuration config) {
        this.config = config;
    }
}
