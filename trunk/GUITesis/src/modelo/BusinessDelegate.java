package modelo;

import configuration.Configuration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import modelo.motor.ModeloMotorBusqueda;
import modelo.ontologia.ModeloOntologiaViajes;
import modelo.ontologia.ModeloOntologiaVocabulario;
import mvcframework.ProxyModelo;
import vo.DatatypePropertyVO;
import vo.IndividualSinonimoVO;
import vo.IndividualViajesVO;
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

    public ArrayList<String> listIndividuals(String clase){
       return modeloOntologiaViajes.listIndividuals(clase);
    }
    
    public void changeNameDatatypeProperty(String old, String name){
        modeloOntologiaViajes.changeNameDatatypeProperty(old,name);
    }
    
    public void removeRange(String pro, String range){
        modeloOntologiaViajes.removeRange(pro,range);
    }
    
    public void addRange(String pro, String range){
        modeloOntologiaViajes.addRange(pro,range);
    }
    
    public void chageRange(String pro, String range){
        modeloOntologiaViajes.changeRange(pro,range);
    }
    
    public void addDomain(String pro, String domain){
        modeloOntologiaViajes.addDomain(pro,domain);
    }
    
    public void removeDomain(String pro, String domain){
        modeloOntologiaViajes.removeDomain(pro,domain);
    }
    
    public void changeNameObjectProperty(String old, String name){
        modeloOntologiaViajes.changeNameObjectProperty(old,name);
    }
    
    public void removeClass(String clase){
        modeloOntologiaViajes.removeClass(clase);
    }

    public void addDatatypeProperty(String obj){
        modeloOntologiaViajes.addDatatypeProperty(obj);
    }
    
    public void addObjectProperty(String obj){
        modeloOntologiaViajes.addObjectProperty(obj);
    }
    
    public void removeDatatypeProperty(String obj){
        modeloOntologiaViajes.removeDatatypeProperty(obj);
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
    
    public IndividualViajesVO obtenerIndividualViajes(String ind){
        return modeloOntologiaViajes.obtenerIndividual(ind);
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

