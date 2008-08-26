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
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.iterator.Filter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import vo.DatatypePropertyVO;
import vo.IndividualSinonimoVO;
import vo.ObjectPropertyVO;

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

    
    public DatatypePropertyVO getDatatypeProperty(OntModel m, String pro){
        DatatypePropertyVO vo = new DatatypePropertyVO();
        String uri = getURIOntologia(m);
        uri = uri + "#";
        DatatypeProperty property = m.getDatatypeProperty(uri+pro);
        vo.setName(property.getLocalName());
        vo.setRange(property.getRange().getLocalName());
        ArrayList<String> domain = new ArrayList<String>();
        Iterator i = m.listClasses().filterDrop(new Filter() { public boolean accept( Object o ) {
                                return ((Resource) o).isAnon();
                            }} );
        while (i.hasNext()) {
           OntClass cls = ((OntClass) i.next());
           if(cls.hasProperty(m.getProperty(uri+pro))){
               domain.add(cls.getLocalName());
           }
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
        //property.getDomain().getLocalName() para uno solo
        Iterator i = m.listClasses().filterDrop(new Filter() { public boolean accept( Object o ) {
                                return ((Resource) o).isAnon();
                            }} );
        while (i.hasNext()) {
           OntClass cls = ((OntClass) i.next());
           if(cls.hasProperty(m.getProperty(uri+pro))){
               domain.add(cls.getLocalName());
           }
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
//        Iterator it = m.listDatatypeProperties().toList().iterator();
//        while(it.hasNext()){
//            DatatypeProperty dp = (DatatypeProperty) it.next();
//            
//            if(dp.getDomain().getLocalName() == null){
//                
//            }else{
//                //es el nombre
//            }
//            Iterator itDom = dp.listDomain().toList().iterator();
//            while(itDom.hasNext()){
//                OntClass dom = (OntClass) itDom.next();
//                if(dom.getLocalName().equalsIgnoreCase(c)){
//                    propiedades.add(dp.getLocalName());
//                }
//            }
//        }
//        Iterator it2 = m.listObjectProperties().toList().iterator();
//        while(it2.hasNext()){
//            ObjectProperty dp = (ObjectProperty) it2.next();
//            Iterator itDom2 = dp.listDomain().toList().iterator();
//            while(itDom2.hasNext()){
//                OntClass dom = (OntClass) itDom2.next();
//                if(dom.getLocalName().equalsIgnoreCase(c)){
//                    propiedades.add(dp.getLocalName());
//                }
//            }
//        }
        Iterator it =  clase.listDeclaredProperties()
                      .filterDrop( new Filter() {
                                    public boolean accept( Object o ) {
                                        return ((Resource) o).isAnon();
                                    }} );
        Iterator i = clase.listDeclaredProperties().toList().iterator();
        while (i.hasNext()) {
           Property pro = ((Property) i.next());
           propiedades.add(pro.getLocalName());
        }
//        for ( StmtIterator sIter = clase.listProperties(); sIter.hasNext() ; )
//        {
//            Statement s = (Statement) sIter.next() ;
//            Triple tri = s.asTriple();
//            if(tri.getObject().isLiteral()){
//            	System.out.println("   Propiedad: "+tri.getPredicate().getLocalName()+"\n   Valor: "+tri.getMatchObject().getLiteral().getValue()) ;
//                propiedades.add(tri.getPredicate().getLocalName());
//            }else{
//            	System.out.println("   Propiedad: "+tri.getPredicate().getLocalName()+"\n   Valor: "+tri.getObject().getLocalName()) ;
//                propiedades.add(tri.getPredicate().getLocalName());
//            }
//        }
        return propiedades;
    }
    
    
    public String getURIOntologia(OntModel m){
        String uri = m.getNsPrefixMap().values().iterator().next().toString();
        uri = uri.substring(0, uri.length() -1);
        return uri;
    }
    
    //terminar
    public void removerTraduccion(OntModel m, String ind, String sin){
        String uri = getURIOntologia(m);
        uri = uri + "#";
        Individual individual = m.getIndividual(uri+ind);
        //individual.removeProperty(m.getProperty(uri+"traduccion"), sin);
    }
    
    //terminar
    public void removerSinonimo(OntModel m, String ind, String sin){
        String uri = getURIOntologia(m);
        uri = uri + "#";
        Individual individual = m.getIndividual(uri+ind);
        //individual.removeProperty(m.getProperty(uri+"sinonimo"), sin);
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
