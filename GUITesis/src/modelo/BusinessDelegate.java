package modelo;

import configuration.Configuration;
import java.util.HashMap;
import java.util.List;
import modelo.motor.ModeloMotorBusqueda;
import modelo.ontologia.ModeloOntologiaViajes;
import modelo.ontologia.ModeloOntologiaVocabulario;
import mvcframework.ProxyModelo;
import vo.DatatypePropertyVO;
import vo.IndividualSinonimoVO;
import vo.ObjectPropertyVO;
import vo.busqueda.ConsultaVueloVO;
import vo.busqueda.IndividualVueloVO;



public class BusinessDelegate extends ProxyModelo 
{

    private ModeloOntologiaVocabulario modeloOntologiaVocabulario;
    private ModeloOntologiaViajes modeloOntologiaViajes;
    private ModeloMotorBusqueda modeloMotorBusqueda;
    private Configuration conf;
    
    public BusinessDelegate() {
        modeloOntologiaVocabulario = new ModeloOntologiaVocabulario();
        modeloOntologiaViajes = new ModeloOntologiaViajes();
        modeloMotorBusqueda = new ModeloMotorBusqueda();
    }
    
    public void cargarConfiguracion(Configuration conf){
        this.conf = conf;
        modeloMotorBusqueda.setConfig(conf);
        modeloMotorBusqueda.cargarModelos();
    }
    
    public List<IndividualVueloVO> buscarVuelos(ConsultaVueloVO consulta){
        return modeloMotorBusqueda.buscarVuelos(consulta);
    }
    
    public DatatypePropertyVO getDatatypeProperty(String pro){
        return modeloOntologiaViajes.getDatatypeProperty(pro);
    }
    
    public ObjectPropertyVO getObjectProperty(String pro){
        return modeloOntologiaViajes.getObjectProperty(pro);
    }
    
    public List<String> ClassProperty(String clase){
        return modeloOntologiaViajes.ClassProperty(clase);
    }
    
    public void removeObjectProperty(String property){
        modeloOntologiaViajes.removeObjectProperty(property);
    }
    
    public List<String> showDatatypeProperties(){
        return modeloOntologiaViajes.showDatatypeProperties();
    }
	
    public List<String> showObjectProperties(){
        return modeloOntologiaViajes.showObjectProperties();
    }
    
    public void removerTraduccion(String ins, String sin){
        modeloOntologiaVocabulario.removerTraduccion(ins,sin);
    }
    
    public void removerSinonimo(String ins, String sin){
        modeloOntologiaVocabulario.removerSinonimo(ins,sin);
    }
    
    public void agregarTraduccion(String instancia, String sin){
        modeloOntologiaVocabulario.agregarTraduccion(instancia,sin);
    }
    public void agregarSinonimo(String instancia, String sin){
        modeloOntologiaVocabulario.addSinonimo(instancia,sin);
    }
    public List<String> obtenerInstanciasVocabuario(String url){
        return modeloOntologiaVocabulario.getInstancias(url);
    }
    
    public void guardarOntologiaSinonimos(){
        modeloOntologiaVocabulario.guardarOntologiaSinonimo();
    }
    
    public IndividualSinonimoVO obtenerIndividual(String ind){
        return modeloOntologiaVocabulario.getIndividual(ind);
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

    public Configuration getConf() {
        return conf;
    }

    public void setConf(Configuration conf) {
        this.conf = conf;
    }
}

