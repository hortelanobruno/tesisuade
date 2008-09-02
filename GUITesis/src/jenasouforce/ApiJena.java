/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jenasouforce;

import com.hp.hpl.jena.graph.Triple;
import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.ontology.UnionClass;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.util.iterator.Filter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import vo.DatatypePropertyVO;
import vo.IndividualSinonimoVO;
import vo.ObjectPropertyVO;
import vo.busqueda.ConsultaVueloVO;
import vo.busqueda.IndividualVueloVO;

/**
 *
 * @author Administrador
 */
public class ApiJena {

    protected OntModel m_model;
    private Map m_anonIDs = new HashMap();
    private int m_anonCount = 0;
    
    
    public ApiJena() {
    }

    
    public ArrayList<IndividualVueloVO> buscarVuelo(OntModel m, ConsultaVueloVO vuelo){
        ArrayList<IndividualVueloVO> lista = new ArrayList<IndividualVueloVO>();
        HashMap<String,String> propiedades = new HashMap<String,String>();
        Iterator i = m.listIndividuals()
                      .filterDrop( new Filter() {
                                    public boolean accept( Object o ) {
                                        return ((Resource) o).isAnon();
                                    }} );
        
        while (i.hasNext()) {
            Individual ind = (Individual) i.next();
            propiedades = new HashMap<String,String>();
            for ( StmtIterator sIter = ind.listProperties(); sIter.hasNext() ; )
            {
                Statement s = (Statement) sIter.next() ;
                Triple tri = s.asTriple();
                if(tri.getObject().isLiteral()){
                    propiedades.put(tri.getPredicate().getLocalName(),tri.getMatchObject().getLiteral().getValue().toString());
                    //System.out.println("   Propiedad: "+tri.getPredicate().getLocalName()+"\n   Valor: "+tri.getMatchObject().getLiteral().getValue()) ;
                }else{
                    propiedades.put(tri.getPredicate().getLocalName(),tri.getObject().getLocalName());
                    //System.out.println("   Propiedad: "+tri.getPredicate().getLocalName()+"\n   Valor: "+tri.getObject().getLocalName()) ;
                }
            }
            if(propiedades.get("type").equalsIgnoreCase("Ticket_Viaje")){
                if(coincideVuelo(vuelo,propiedades)){
                    IndividualVueloVO invue = cargarIndividualVueloVO(propiedades);
                    invue.setNameIndividual(ind.getLocalName());
                    invue.setUri(ind.getURI());
                    lista.add(invue);
                }
            }
        }
        return lista;
    }
    
    //falta cargar la parte de opciones avanzadas y adultos, ninios y bebes
    public IndividualVueloVO cargarIndividualVueloVO(HashMap<String,String> p){
        IndividualVueloVO invue = new IndividualVueloVO();
        invue.setCiudadOrigen(p.get("desde"));
        invue.setCiudadDestino(p.get("hacia"));
        invue.setFechaIda(p.get("hora_salida"));
        invue.setFechaVuelta(p.get("horario_regreso"));
        return invue;
    }
    //falta la coincidencia de adultos,bebes y ninios y las opciones avanzadas
    public boolean coincideVuelo(ConsultaVueloVO vuelo, HashMap<String,String> propiedades){
        boolean b = true;
        if(!propiedades.get("hacia").equalsIgnoreCase(vuelo.getCiudadDestino())){
            b = false;
        }
        if(!propiedades.get("desde").equalsIgnoreCase(vuelo.getCiudadOrigen())){
            b = false;
        }
        //vuelo.getFechaIda().toLocal    en owl es 2008-08-14
        String fechaida = vuelo.getFechaIda().toLocaleString().split(" ")[0];
        String[] fechaparseada = fechaida.split("/");
        String fechaidaposta = fechaparseada[2]+"-"+fechaparseada[1]+"-"+fechaparseada[0];
        if(!propiedades.get("hora_salida").substring(0, 10).equalsIgnoreCase(fechaidaposta)){
            b = false;
        }
        String fechavuelta = vuelo.getFechaVuelta().toLocaleString().split(" ")[0];
        String[] fechaparseada2 = fechavuelta.split("/");
        String fechavueltaposta = fechaparseada2[2]+"-"+fechaparseada2[1]+"-"+fechaparseada2[0];
        if(!propiedades.get("horario_regreso").substring(0, 10).equalsIgnoreCase(fechavueltaposta)){
            b = false;
        }
        return b;
    }
    
    public DatatypePropertyVO getDatatypeProperty(OntModel m, String pro){
        DatatypePropertyVO vo = new DatatypePropertyVO();
        String uri = getURIOntologia(m);
        uri = uri + "#";
        DatatypeProperty property = m.getDatatypeProperty(uri+pro);
        vo.setName(property.getLocalName());
        vo.setRange(property.getRange().getLocalName());
        ArrayList<String> domain = new ArrayList<String>();
        OntResource dom = property.getDomain();
        if(dom.canAs(UnionClass.class)){
            UnionClass uc = (UnionClass)dom.as(UnionClass.class);
            ExtendedIterator domainIt= uc.listOperands();
            while(domainIt.hasNext()){
                OntClass mc = (OntClass) domainIt.next();
                domain.add(mc.getLocalName());
            }
        }else{
            domain.add(property.getDomain().getLocalName());
        }
        vo.setDomain(domain);
        return vo;
    }
    
    
    public ObjectPropertyVO getObjectProperty(OntModel m, String pro){
        ObjectPropertyVO vo = new ObjectPropertyVO();
        String uri = getURIOntologia(m);
        uri = uri + "#";
        ObjectProperty property = m.getObjectProperty(uri+pro);
        Iterator ii = m.listClasses().toList().iterator();
        while(ii.hasNext()){
            ii.next().toString();
        }
        vo.setName(property.getLocalName());
        ArrayList<String> range = new ArrayList<String>();
        range.add(property.getRange().getLocalName());
        vo.setRange(range);
        ArrayList<String> domain = new ArrayList<String>();
        OntResource dom = property.getDomain();
        if(dom.canAs(UnionClass.class)){
            UnionClass uc = (UnionClass)dom.as(UnionClass.class);
            ExtendedIterator domainIt= uc.listOperands();
            while(domainIt.hasNext()){
                OntClass mc = (OntClass) domainIt.next();
                domain.add(mc.getLocalName());
            }
        }else{
            domain.add(property.getDomain().getLocalName());
        }
        vo.setDomain(domain);
        return vo;
    }
    
    public List<String> getObjectProperties(OntModel m){
        ArrayList<String> propiedades = new ArrayList<String>();
        Iterator i =  m.listObjectProperties()
                      .filterDrop( new Filter() {
                                    public boolean accept( Object o ) {
                                        return ((Resource) o).isAnon();
                                    }} );
        while (i.hasNext()) {
           ObjectProperty pro = ((ObjectProperty) i.next());
           propiedades.add(pro.getLocalName());
        }
        return propiedades;
    }
    
    public List<String> getDatatypeProperties(OntModel m){
        ArrayList<String> propiedades = new ArrayList<String>();
        Iterator i =  m.listDatatypeProperties()
                      .filterDrop( new Filter() {
                                    public boolean accept( Object o ) {
                                        return ((Resource) o).isAnon();
                                    }} );
        while (i.hasNext()) {
           DatatypeProperty pro = ((DatatypeProperty) i.next());
           propiedades.add(pro.getLocalName());
        }
        return propiedades;
    }
    
    //Terminar, no agarra todas las properties
    public List<String> getProperty(OntModel m, String c){
        ArrayList<String> propiedades = new ArrayList<String>();
        String uri = getURIOntologia(m);
        uri = uri + "#";
        OntClass clase = m.getOntClass(uri+c);
        Iterator i =  m.listObjectProperties()
                      .filterDrop( new Filter() {
                                    public boolean accept( Object o ) {
                                        return ((Resource) o).isAnon();
                                    }} );
        while (i.hasNext()) {
           ObjectProperty pro = ((ObjectProperty) i.next());
           String obcPro = pro.getLocalName();
           ArrayList<String> domain = new ArrayList<String>();
           OntResource dom = pro.getDomain();
           if(dom.canAs(UnionClass.class)){
                UnionClass uc = (UnionClass)dom.as(UnionClass.class);
                ExtendedIterator domainIt= uc.listOperands();
                while(domainIt.hasNext()){
                    OntClass mc = (OntClass) domainIt.next();
                    domain.add(mc.getLocalName());
                }
           }else{
                domain.add(pro.getDomain().getLocalName());
           }
           for(int j=0 ; j<domain.size() ; j++){
               if(domain.get(j).equalsIgnoreCase(c)){
                   propiedades.add(obcPro);
               }
           }
        }
        i =  m.listDatatypeProperties()
                      .filterDrop( new Filter() {
                                    public boolean accept( Object o ) {
                                        return ((Resource) o).isAnon();
                                    }} );
        while (i.hasNext()) {
           DatatypeProperty pro = ((DatatypeProperty) i.next());
           String datPro = pro.getLocalName();
           ArrayList<String> domain = new ArrayList<String>();
           OntResource dom = pro.getDomain();
           if(dom.canAs(UnionClass.class)){
                UnionClass uc = (UnionClass)dom.as(UnionClass.class);
                ExtendedIterator domainIt= uc.listOperands();
                while(domainIt.hasNext()){
                    OntClass mc = (OntClass) domainIt.next();
                    domain.add(mc.getLocalName());
                }
           }else{
                domain.add(pro.getDomain().getLocalName());
           }
           for(int j=0 ; j < domain.size() ; j++){
               if(domain.get(j).equalsIgnoreCase(c)){
                   propiedades.add(datPro);
               }
           }
        }                       
        return propiedades;
    }
    
    
    public String getURIOntologia(OntModel m){
        String uri = m.getNsPrefixMap().values().iterator().next().toString();
        uri = uri.substring(0, uri.length() -1);
        return uri;
    }
    
    
    public void removerTraduccion(OntModel m, String ind, String sin){
        String uri = getURIOntologia(m);
        uri = uri + "#";
        Individual individual = m.getIndividual(uri+ind);
        OntProperty proper = m.getOntProperty(uri+"traduccion");
        Literal lit = m.createLiteral(sin);
        individual.removeProperty(proper, lit);
    }
    
    
    public void removerSinonimo(OntModel m, String ind, String sin){
        String uri = getURIOntologia(m);
        uri = uri + "#";
        Individual individual = m.getIndividual(uri+ind);
        OntProperty proper = m.getOntProperty(uri+"sinonimo");
        Literal lit = m.createLiteral(sin);
        individual.removeProperty(proper, lit);
    }
    
    public void agregarTraduccion(OntModel m, String ind, String sin){
        String uri = getURIOntologia(m);
        uri = uri + "#";
        Individual individual = m.getIndividual(uri+ind);
        individual.addProperty(m.getProperty(uri+"traduccion"), sin);
    }
    
    
    public void addSinonimo(OntModel m, String ind, String sin){
        String uri = getURIOntologia(m);
        uri = uri + "#";
        Individual individual = m.getIndividual(uri+ind);
        individual.addProperty(m.getProperty(uri+"sinonimo"), sin);
    }
    
    public IndividualSinonimoVO showIndividualOfSinonimo(OntModel m, String ind) {
        IndividualSinonimoVO individual = null;
        Iterator i = m.listIndividuals()
                      .filterDrop( new Filter() {
                                    public boolean accept( Object o ) {
                                        return ((Resource) o).isAnon();
                                    }} );

        while (i.hasNext()) {
           Individual indi = ((Individual) i.next());
           if(indi.getLocalName().equals(ind)){
               individual = new IndividualSinonimoVO();
               ArrayList<String> sinonimo = new ArrayList<String>();
               ArrayList<String> traduccion = new ArrayList<String>();
                for ( StmtIterator sIter = indi.listProperties(); sIter.hasNext() ; )
                {
                    Statement s = (Statement) sIter.next() ;
                    Triple tri = s.asTriple();
                    if(tri.getObject().isLiteral()){
                        if(!tri.getPredicate().getLocalName().equals("type")){
                            if(tri.getPredicate().getLocalName().equals("sinonimo")){
                                sinonimo.add((String) tri.getMatchObject().getLiteral().getValue());
                            }else{
                                traduccion.add((String) tri.getMatchObject().getLiteral().getValue());
                            }
                        }
                    }else{
                        if(!tri.getPredicate().getLocalName().equals("type")){
                            if(tri.getPredicate().getLocalName().equals("sinonimo")){
                            sinonimo.add((String) tri.getObject().getLocalName());
                        }else{
                            traduccion.add((String) tri.getObject().getLocalName());
                        }
                        }
                    }
                }
               individual.setNombreInstancia(ind);
               individual.setSinonimos(sinonimo);
               individual.setTraduccion(traduccion);
               individual.setUri(indi.getURI());
               return individual;
           }
        }
        return individual;
    }

    public ArrayList<String> showIndividuals(OntModel m){
        ArrayList<String> individuals = new ArrayList<String>();
        Iterator i = m.listIndividuals()
                      .filterDrop( new Filter() {
                                    public boolean accept( Object o ) {
                                        return ((Resource) o).isAnon();
                                    }} );

        while (i.hasNext()) {
           individuals.add(((Individual) i.next()).getLocalName());
        }
 
        return individuals;
    }

    public HashMap<String,String> showClass(OntModel m){
        HashMap<String,String> clases = new HashMap<String,String>();
        Iterator i = m.listClasses().filterDrop(new Filter() { public boolean accept( Object o ) {
                                        return ((Resource) o).isAnon();
                                    }} );
        while (i.hasNext()) {
           OntClass cls = ((OntClass) i.next());
           showClass(clases,cls,null,new ArrayList(), 0 );
        }
        return clases;
    }
    
    public void addClass(OntModel m, String clase, String padre){
        String uri = m.getNsPrefixMap().get("").toString();
        OntClass ontClass = m.createClass(uri + clase);
        m.getOntClass(uri + padre).addSubClass(ontClass);
    }
    
    
    public void grabarOntologia(OntModel m, String url){
        FileOutputStream fileout = null;
        try {
            fileout = new FileOutputStream(new File(url));
        } catch (FileNotFoundException ex) {
            
        }
        m.write(fileout,"RDF/XML-ABBREV");
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   //USO INTERNO 
   private void showClass(HashMap<String,String> clases, OntClass cls, OntClass sub, List occurs, int depth ) {
        renderClassDescription(clases,cls,sub, depth );

        // recurse to the next level down
        if (cls.canAs( OntClass.class )  &&  !occurs.contains( cls )) {
            for (Iterator i = cls.listSubClasses( true );  i.hasNext(); ) {
                OntClass subs = (OntClass) i.next();

                // we push this expression on the occurs list before we recurse
                occurs.add( cls );
                showClass(clases, cls, subs, occurs, depth + 1 );
                occurs.remove( cls );
            }
        }
    }
   
   

   
   private void renderClassDescription(HashMap<String,String> clases, OntClass cls, OntClass sub, int depth ) {
       if(sub == null){
            if(!clases.containsKey(cls.getLocalName()))
                clases.put(cls.getLocalName(),"");   
       }else{
           //if(!clases.containsKey(sub.getLocalName()))
            clases.put(sub.getLocalName(),cls.getLocalName());
       }
    }
}
