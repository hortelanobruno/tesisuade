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
import configuration.Configuration;
import configuration.defaultontology.types.DefaultProperty;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import jenasouforce.ApiJena;

/**
 *
 * @author Brunoli
 */
public class ModeloTransformadorOntologia {

    private ApiJena jena;
    private Configuration configuration;

    public ModeloTransformadorOntologia() {
        jena = new ApiJena();
    }

    public ModeloTransformadorOntologia(Configuration configuration) {
        jena = new ApiJena();
        this.configuration = configuration;
    }

    public List<String> generarOntologiaBusqueda(String ontURL, String newURL, String sin) throws Exception {
        DIGReasoner r = (DIGReasoner) ReasonerRegistry.theRegistry().create(DIGReasonerFactory.URI, null);
        OntModelSpec spec = new OntModelSpec(OntModelSpec.OWL_MEM);
        //spec.setReasoner(r);
        OntModel ontologia = ModelFactory.createOntologyModel(spec, null);
        ontologia = loadOntModelFromOwlFile(ontURL);
        OntModel sinonimo = ModelFactory.createOntologyModel(spec, null);
        sinonimo = loadOntModelFromOwlFile(sin);
        OntModel nueva = ModelFactory.createOntologyModel(spec, null);
        List<String> errores = jena.generarOntologiaBusqueda(ontologia, sinonimo, nueva);
        if (errores != null) {
            return errores;
        } else {
            if(chequearMinimoCumplimiento(nueva)){
                jena.grabarOntologia(nueva, newURL);
                return null;
            }else{
                throw new Exception("No cumple el minimo requerimiento");
            }
        }
    }

    private boolean chequearMinimoCumplimiento(OntModel nueva) {
        //TODO - Verificar que la nueva ontologia cumpla con el minimo requerimiento
        //       Y tambien agregar a la configuracion si hay nuevas propiedades avanzadas
        String clase = configuration.getDefaultOntology().getViaje().getNombreClase();
        HashMap<String,String> mapa = jena.getProperty(nueva, clase);
        if(mapa != null){
            for(DefaultProperty pro : configuration.getDefaultOntology().getViaje().getDefaultProperties()){
                if(!mapa.containsKey(pro.getName())){
                    return false;
                }
            }
        }else{
            return false;
        }
        clase = configuration.getDefaultOntology().getAlojamiento().getNombreClase();
        mapa = jena.getProperty(nueva, clase);
        if(mapa != null){
            for(DefaultProperty pro : configuration.getDefaultOntology().getAlojamiento().getDefaultProperties()){
                if(!mapa.containsKey(pro.getName())){
                    return false;
                }
            }
        }else{
            return false;
        }
        clase = configuration.getDefaultOntology().getTranslado().getNombreClase();
        mapa = jena.getProperty(nueva, clase);
        if(mapa != null){
            for(DefaultProperty pro : configuration.getDefaultOntology().getTranslado().getDefaultProperties()){
                if(!mapa.containsKey(pro.getName())){
                    return false;
                }
            }
        }else{
            return false;
        }

        return true;
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

    /**
     * @return the configuration
     */
    public Configuration getConfiguration() {
        return configuration;
    }

    /**
     * @param configuration the configuration to set
     */
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
}
