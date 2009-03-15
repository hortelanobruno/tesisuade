package modelo;

import configuration.Configuration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import modelo.motor.ModeloMotorBusqueda;
import modelo.ontologia.ModeloOntologiaViajes;
import modelo.ontologia.ModeloOntologiaVocabulario;
import modelo.ontologia.ModeloTransformadorOntologia;
import mvcframework.ProxyModelo;
import vo.DatatypePropertyVO;
import vo.IndividualSinonimoVO;
import vo.IndividualViajesVO;
import vo.ObjectPropertyVO;
import vo.busqueda.ConsultaVueloVO;
import vo.busqueda.IndividualVueloVO;

public class BusinessDelegate extends ProxyModelo {

    private ModeloOntologiaVocabulario modeloOntologiaVocabulario;
    private ModeloOntologiaViajes modeloOntologiaViajes;
    private ModeloMotorBusqueda modeloMotorBusqueda;
    private ModeloTransformadorOntologia modeloTransformadorOntologia;
    private Configuration conf;

    public BusinessDelegate() {
        modeloOntologiaVocabulario = new ModeloOntologiaVocabulario();
        modeloOntologiaViajes = new ModeloOntologiaViajes();
        modeloMotorBusqueda = new ModeloMotorBusqueda();
        modeloTransformadorOntologia = new ModeloTransformadorOntologia();
    }

    public void cargarConfiguracion(Configuration conf) {
        this.conf = conf;
        modeloMotorBusqueda.setConfig(conf);
        modeloTransformadorOntologia.setConfiguration(conf);
        modeloMotorBusqueda.cargarModelos();
    }

    public List<String> generarOntologiaBusqueda(Configuration configuration, String ontURL, String newURL, String sin) throws Exception {
        return modeloTransformadorOntologia.generarOntologiaBusqueda2(configuration, ontURL, newURL, sin);
    }

    public void cargarPropiedadIndividual(String ind, String pro, String valor) {
        modeloOntologiaViajes.cargarPropiedadIndividual(ind, pro, valor);
    }

    public void addDatatypePropertyToClass(String clase, String pro) {
        modeloOntologiaViajes.addDatatypePropertyToClass(clase, pro);
    }

    public void addObjectPropertyToClass(String clase, String pro) {
        modeloOntologiaViajes.addObjectPropertyToClass(clase, pro);
    }

    public void addIndividual(String ind, String clase) {
        modeloOntologiaViajes.addIndividual(ind, clase);
    }

    public void removeIndividual(String ind) {
        modeloOntologiaViajes.removeIndividual(ind);
    }

    public ArrayList<String> listIndividuals(String clase) {
        return modeloOntologiaViajes.listIndividuals(clase);
    }

    public void removePropertyOfClass(String clase, String pro) {
        modeloOntologiaViajes.removePropertyOfClass(clase, pro);
    }

    public void changeNameClass(String old, String nuevo) {
        modeloOntologiaViajes.changeNameClass(old, nuevo);
    }

    public void changeIndividualClass(String old, String nuevo) {
        modeloOntologiaViajes.changeIndividualClass(old, nuevo);
    }

    public void changeNameDatatypeProperty(String old, String name) {
        modeloOntologiaViajes.changeNameDatatypeProperty(old, name);
    }

    public void removeRange(String pro, String range) {
        modeloOntologiaViajes.removeRange(pro, range);
    }

    public void addRange(String pro, String range) {
        modeloOntologiaViajes.addRange(pro, range);
    }

    public void changeRange(String pro, String range) {
        modeloOntologiaViajes.changeRange(pro, range);
    }

    public void addDomain(String pro, String domain) {
        modeloOntologiaViajes.addDomain(pro, domain);
    }

    public void removeDomain(String pro, String domain) {
        modeloOntologiaViajes.removeDomain(pro, domain);
    }

    public void changeNameObjectProperty(String old, String name) {
        modeloOntologiaViajes.changeNameObjectProperty(old, name);
    }

    public void removeClass(String clase) {
        modeloOntologiaViajes.removeClass(clase);
    }

    public void addDatatypeProperty(String obj) {
        modeloOntologiaViajes.addDatatypeProperty(obj);
    }

    public void addObjectProperty(String obj) {
        modeloOntologiaViajes.addObjectProperty(obj);
    }

    public void removeDatatypeProperty(String obj) {
        modeloOntologiaViajes.removeDatatypeProperty(obj);
    }

    public List<IndividualVueloVO> buscarVuelos(ConsultaVueloVO consulta) {
        return modeloMotorBusqueda.buscarVuelos(consulta);
    }

    public DatatypePropertyVO getDatatypeProperty(String pro) {
        return modeloOntologiaViajes.getDatatypeProperty(pro);
    }

    public ObjectPropertyVO getObjectProperty(String pro) {
        return modeloOntologiaViajes.getObjectProperty(pro);
    }

    public HashMap<String, String> ClassProperty(String clase) {
        return modeloOntologiaViajes.ClassProperty(clase);
    }

    public void removeObjectProperty(String property) {
        modeloOntologiaViajes.removeObjectProperty(property);
    }

    public List<String> showDatatypeProperties() {
        return modeloOntologiaViajes.showDatatypeProperties();
    }

    public List<String> showObjectProperties() {
        return modeloOntologiaViajes.showObjectProperties();
    }

    public void removerPalabra(String ins){
        modeloOntologiaVocabulario.removerPalabra(ins);
    }

    public void agregarPalabra(String ins){
        modeloOntologiaVocabulario.agregarPalabra(ins);
    }

    public void removerTraduccion(String ins, String sin) {
        modeloOntologiaVocabulario.removerTraduccion(ins, sin);
    }

    public void removerSinonimo(String ins, String sin) {
        modeloOntologiaVocabulario.removerSinonimo(ins, sin);
    }

    public void agregarTraduccion(String instancia, String sin) {
        modeloOntologiaVocabulario.agregarTraduccion(instancia, sin);
    }

    public void agregarSinonimo(String instancia, String sin) {
        modeloOntologiaVocabulario.addSinonimo(instancia, sin);
    }

    public List<String> obtenerInstanciasVocabuario(String url) {
        return modeloOntologiaVocabulario.getInstancias(url);
    }

    public void guardarOntologiaSinonimos() {
        modeloOntologiaVocabulario.guardarOntologiaSinonimo();
    }

    public IndividualSinonimoVO obtenerIndividual(String ind) {
        return modeloOntologiaVocabulario.getIndividual(ind);
    }

    public IndividualViajesVO obtenerIndividualViajes(String ind) {
        return modeloOntologiaViajes.obtenerIndividual(ind);
    }

    public void nuevaOntologia() {
        modeloOntologiaViajes.nuevaOntologia();
    }

    public void cargarOntologia(String url) {
        modeloOntologiaViajes.cargarOntologia(url);
    }

    public void grabarOntologia(String url) {
        modeloOntologiaViajes.guardarOntologia(url);
    }

    public String getURIOntologia() {
        return modeloOntologiaViajes.getURIOntologia();
    }

    public HashMap<String, String> showClasses() {
        return modeloOntologiaViajes.showClasses();
    }

    public void addClass(String hijo, String padre) {
        modeloOntologiaViajes.addClass(hijo, padre);
    }

    public Configuration getConf() {
        return conf;
    }

    public void setConf(Configuration conf) {
        this.conf = conf;
    }
}

