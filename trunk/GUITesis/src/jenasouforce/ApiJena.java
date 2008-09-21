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
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.util.iterator.Filter;
import com.hp.hpl.jena.vocabulary.XSD;
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
import vo.IndividualViajesVO;
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

    public void addDatatypeProperty(OntModel m, String obj) {
        String uri = getURIOntologiaConNumeral(m);
        DatatypeProperty pro = m.createDatatypeProperty(uri + obj);
        //arsenal
    }

    public void addDatatypePropertyToClass(OntModel m, String clase, String pro) {
        String uri = getURIOntologiaConNumeral(m);
        OntClass clasee = m.getOntClass(uri+clase);
        DatatypeProperty proo = m.getDatatypeProperty(uri+pro);
        proo.addDomain(clasee);
    }

    
    public void addDomain(OntModel m, String pro, String domain) {
        String uri = getURIOntologiaConNumeral(m);
        OntProperty property = m.getOntProperty(uri+pro);
        OntClass clase = m.getOntClass(uri+domain);
        property.addDomain(clase);
    }

    //Terminar
    public void addIndividual(OntModel m, String ind, String cla) {
        String uri = getURIOntologiaConNumeral(m);
        OntClass clase = m.getOntClass(uri+cla);
        Individual individual = m.createIndividual(uri+ind, clase);
//        List<String> properties = getProperty(m, cla);
//        List<String> datatypeproperties = getDatatypeProperties(m);
//        List<String> objectproperties = getObjectProperties(m);
//        for(int i=0 ; i < properties.size() ; i++){
//            if(datatypeproperties.contains(properties.get(i))){
//                DatatypeProperty dpro = m.getDatatypeProperty(uri+properties.get(i));
//                individual.addProperty(dpro, "");
//            }else if(objectproperties.contains(properties.get(i))){
//                ObjectProperty opro = m.getObjectProperty(uri+properties.get(i));
//                individual.addProperty(opro, m.createResource());
//            }
//        }
    }

    public void addObjectProperty(OntModel m, String obj) {
        String uri = getURIOntologiaConNumeral(m);
        m.createObjectProperty(uri + obj);
    }

    public void addObjectPropertyToClass(OntModel m, String clase, String pro) {
        String uri = getURIOntologiaConNumeral(m);
        OntClass clasee = m.getOntClass(uri+clase);
        ObjectProperty proo = m.getObjectProperty(uri+pro);
        proo.addDomain(clasee);
    }

    
    public void addRange(OntModel m, String pro, String range) {
        String uri = getURIOntologiaConNumeral(m);
        OntProperty property = m.getOntProperty(uri+pro);
        OntClass clase = m.getOntClass(uri+range);
        property.addRange(clase);
    }

    public ArrayList<IndividualVueloVO> buscarVuelo(OntModel m, ConsultaVueloVO vuelo) {
        ArrayList<IndividualVueloVO> lista = new ArrayList<IndividualVueloVO>();
        HashMap<String, String> propiedades = new HashMap<String, String>();
        Iterator i = m.listIndividuals().filterDrop(new Filter() {

            public boolean accept(Object o) {
                return ((Resource) o).isAnon();
            }
        });

        while (i.hasNext()) {
            Individual ind = (Individual) i.next();
            propiedades = new HashMap<String, String>();
            for (StmtIterator sIter = ind.listProperties(); sIter.hasNext();) {
                Statement s = (Statement) sIter.next();
                Triple tri = s.asTriple();
                if (tri.getObject().isLiteral()) {
                    propiedades.put(tri.getPredicate().getLocalName(), tri.getMatchObject().getLiteral().getValue().toString());
                //System.out.println("   Propiedad: "+tri.getPredicate().getLocalName()+"\n   Valor: "+tri.getMatchObject().getLiteral().getValue()) ;
                } else {
                    propiedades.put(tri.getPredicate().getLocalName(), tri.getObject().getLocalName());
                //System.out.println("   Propiedad: "+tri.getPredicate().getLocalName()+"\n   Valor: "+tri.getObject().getLocalName()) ;
                }
            }
            if (propiedades.get("type").equalsIgnoreCase("Ticket_Viaje")) {
                if (coincideVuelo(vuelo, propiedades)) {
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
    public IndividualVueloVO cargarIndividualVueloVO(HashMap<String, String> p) {
        IndividualVueloVO invue = new IndividualVueloVO();
        invue.setCiudadOrigen(p.get("desde"));
        invue.setCiudadDestino(p.get("hacia"));
        invue.setFechaIda(p.get("hora_salida"));
        invue.setFechaVuelta(p.get("horario_regreso"));
        return invue;
    }

    //Testear
    public void changeIndividualClass(OntModel m, String old, String nuevo) {
        String uri = getURIOntologiaConNumeral(m);
        Individual oldInd = m.getIndividual(uri+old);
        Individual newInd = m.createIndividual(uri+nuevo, oldInd.getOntClass());
        for (StmtIterator sIter = oldInd.listProperties(); sIter.hasNext();) {
            Statement s = (Statement) sIter.next();
            newInd.addProperty(s.getPredicate(), s.getObject());
        }
    }

    //Testear
    public void changeNameClass(OntModel m, String old, String nuevo) {
        String uri = getURIOntologiaConNumeral(m);
        OntClass oldclass = m.getOntClass(uri+old);
        OntClass newclass = m.createClass(uri+nuevo);
        if(oldclass.getSuperClass() != null){
            newclass.addSuperClass(oldclass.getSuperClass());
        }
        if(oldclass.getSubClass() != null){
            newclass.addSubClass(oldclass.getSubClass());
        }
        String oldclassname = oldclass.getLocalName();
        Iterator i = m.listObjectProperties().filterDrop(new Filter() {

            public boolean accept(Object o) {
                return ((Resource) o).isAnon();
            }
        });
        while (i.hasNext()) {
            ObjectProperty pro = ((ObjectProperty) i.next());
            ArrayList<String> domain = new ArrayList<String>();
            OntResource dom = pro.getDomain();
            if(dom != null){
                if (dom.canAs(UnionClass.class)) {
                    UnionClass uc = (UnionClass) dom.as(UnionClass.class);
                    ExtendedIterator domainIt = uc.listOperands();
                    while (domainIt.hasNext()) {
                        OntClass mc = (OntClass) domainIt.next();
                        domain.add(mc.getLocalName());
                    }
                } else {
                    domain.add(pro.getDomain().getLocalName());
                }
            }
            for (int j = 0; j < domain.size(); j++) {
                if (domain.get(j).equalsIgnoreCase(oldclassname)) {
                    newclass.addProperty(pro, pro.getURI());
                }
            }
        }
        i = m.listDatatypeProperties().filterDrop(new Filter() {

            public boolean accept(Object o) {
                return ((Resource) o).isAnon();
            }
        });
        while (i.hasNext()) {
            DatatypeProperty pro = ((DatatypeProperty) i.next());
            ArrayList<String> domain = new ArrayList<String>();
            OntResource dom = pro.getDomain();
            if(dom != null){
                if (dom.canAs(UnionClass.class)) {
                    UnionClass uc = (UnionClass) dom.as(UnionClass.class);
                    ExtendedIterator domainIt = uc.listOperands();
                    while (domainIt.hasNext()) {
                        OntClass mc = (OntClass) domainIt.next();
                        domain.add(mc.getLocalName());
                    }
                } else {
                    domain.add(pro.getDomain().getLocalName());
                }
            }
            for (int j = 0; j < domain.size(); j++) {
                if (domain.get(j).equalsIgnoreCase(oldclassname)) {
                    newclass.addProperty(pro, pro.getURI());
                }
            }
        }
        oldclass.remove();
    }

    //Testear
    public void changeNameDatatypeProperty(OntModel m, String old, String name) {
        String uri = getURIOntologiaConNumeral(m);
        DatatypeProperty proOld = m.getDatatypeProperty(uri+old);
        DatatypeProperty proNew = m.createDatatypeProperty(uri+name);
        if(proOld.getRange() != null){
            proNew.setRange(proOld.getRange());
        }
        if(proOld.getDomain() != null){
            proNew.setDomain(proOld.getDomain());        
        }
        proOld.remove();
    }

    //Testear
    public void changeNameObjectProperty(OntModel m, String old, String name) {
        String uri = getURIOntologiaConNumeral(m);
        ObjectProperty proOld = m.getObjectProperty(uri+old);
        ObjectProperty proNew = m.createObjectProperty(uri+name);
        if(proOld.getRange() != null){
            proNew.setRange(proOld.getRange());
        }
        if(proOld.getDomain() != null){
            proNew.setDomain(proOld.getDomain());  
        }
        proOld.remove();
    }

    //Testear
    public void changeRange(OntModel m, String pro, String range) {
        //date, string, int, double, float, boolean
        String uri = getURIOntologiaConNumeral(m);
        DatatypeProperty proOld = m.getDatatypeProperty(uri+pro);
        if(range.equalsIgnoreCase("int")){
            proOld.setRange(XSD.xint);
        }
        if(range.equalsIgnoreCase("date")){
            proOld.setRange(XSD.date);
        }
        if(range.equalsIgnoreCase("string")){
            proOld.setRange(XSD.xstring);
        }
        if(range.equalsIgnoreCase("float")){
            proOld.setRange(XSD.xfloat);
        }
        if(range.equalsIgnoreCase("boolean")){
            proOld.setRange(XSD.xboolean);
        }
    }
    //falta la coincidencia de adultos,bebes y ninios y las opciones avanzadas
    public boolean coincideVuelo(ConsultaVueloVO vuelo, HashMap<String, String> propiedades) {
        boolean b = true;
        if (!propiedades.get("hacia").equalsIgnoreCase(vuelo.getCiudadDestino())) {
            b = false;
        }
        if (!propiedades.get("desde").equalsIgnoreCase(vuelo.getCiudadOrigen())) {
            b = false;
        }
        //vuelo.getFechaIda().toLocal    en owl es 2008-08-14
        String fechaida = vuelo.getFechaIda().toLocaleString().split(" ")[0];
        String[] fechaparseada = fechaida.split("/");
        String fechaidaposta = fechaparseada[2] + "-" + fechaparseada[1] + "-" + fechaparseada[0];
        if (!propiedades.get("hora_salida").substring(0, 10).equalsIgnoreCase(fechaidaposta)) {
            b = false;
        }
        String fechavuelta = vuelo.getFechaVuelta().toLocaleString().split(" ")[0];
        String[] fechaparseada2 = fechavuelta.split("/");
        String fechavueltaposta = fechaparseada2[2] + "-" + fechaparseada2[1] + "-" + fechaparseada2[0];
        if (!propiedades.get("horario_regreso").substring(0, 10).equalsIgnoreCase(fechavueltaposta)) {
            b = false;
        }
        return b;
    }

    //Testear
    public DatatypePropertyVO getDatatypeProperty(OntModel m, String pro) {
        DatatypePropertyVO vo = new DatatypePropertyVO();
        String uri = getURIOntologia(m);
        uri = uri + "#";
        DatatypeProperty property = m.getDatatypeProperty(uri + pro);
        vo.setName(property.getLocalName());
        if(property.getRange() != null){
            vo.setRange(property.getRange().getLocalName());
        }
        ArrayList<String> domain = new ArrayList<String>();
        OntResource dom = property.getDomain();
        if(dom != null){
            if (dom.canAs(UnionClass.class)) {
                UnionClass uc = (UnionClass) dom.as(UnionClass.class);
                ExtendedIterator domainIt = uc.listOperands();
                while (domainIt.hasNext()) {
                    OntClass mc = (OntClass) domainIt.next();
                    domain.add(mc.getLocalName());
                }
            } else {
                domain.add(property.getDomain().getLocalName());
            }
        }
        vo.setDomain(domain);
        return vo;
    }

    
    public ObjectPropertyVO getObjectProperty(OntModel m, String pro) {
        ObjectPropertyVO vo = new ObjectPropertyVO();
        String uri = getURIOntologiaConNumeral(m);
        ObjectProperty property = m.getObjectProperty(uri + pro);
        Iterator ii = m.listClasses().toList().iterator();
        while (ii.hasNext()) {
            ii.next().toString();
        }
        vo.setName(property.getLocalName());
        ArrayList<String> range = new ArrayList<String>();
        if(property.getRange() != null){
            range.add(property.getRange().getLocalName());
        }
        vo.setRange(range);
        ArrayList<String> domain = new ArrayList<String>();
        OntResource dom = property.getDomain();
        if(dom != null){
            if (dom.canAs(UnionClass.class)) {
                UnionClass uc = (UnionClass) dom.as(UnionClass.class);
                ExtendedIterator domainIt = uc.listOperands();
                while (domainIt.hasNext()) {
                    OntClass mc = (OntClass) domainIt.next();
                    domain.add(mc.getLocalName());
                }
            } else {
                domain.add(property.getDomain().getLocalName());
            }
        }
        vo.setDomain(domain);
        return vo;
    }

    public List<String> getObjectProperties(OntModel m) {
        ArrayList<String> propiedades = new ArrayList<String>();
        Iterator i = m.listObjectProperties().filterDrop(new Filter() {

            public boolean accept(Object o) {
                return ((Resource) o).isAnon();
            }
        });
        while (i.hasNext()) {
            ObjectProperty pro = ((ObjectProperty) i.next());
            propiedades.add(pro.getLocalName());
        }
        return propiedades;
    }

    public List<String> getDatatypeProperties(OntModel m) {
        ArrayList<String> propiedades = new ArrayList<String>();
        Iterator i = m.listDatatypeProperties().filterDrop(new Filter() {

            public boolean accept(Object o) {
                return ((Resource) o).isAnon();
            }
        });
        while (i.hasNext()) {
            DatatypeProperty pro = ((DatatypeProperty) i.next());
            propiedades.add(pro.getLocalName());
        }
        return propiedades;
    }

    //Testear la parte de los padres
    public HashMap<String,String> getProperty(OntModel m, String c) {
        HashMap<String,String> propiedades = new HashMap<String,String>();
        String uri = getURIOntologiaConNumeral(m);
        OntClass clase = m.getOntClass(uri + c);
        List<String> padres = new ArrayList<String>();
        while(clase.getSuperClass() != null){
            clase = clase.getSuperClass();
            padres.add(clase.getLocalName());
        }
        Iterator i = m.listObjectProperties().filterDrop(new Filter() {
            public boolean accept(Object o) {
                return ((Resource) o).isAnon();
            }
        });
        while (i.hasNext()) {
            ObjectProperty pro = ((ObjectProperty) i.next());
            String obcPro = pro.getLocalName();
            ArrayList<String> domain = new ArrayList<String>();
            OntResource dom = pro.getDomain();
            if(dom != null){
                if (dom.canAs(UnionClass.class)) {
                    UnionClass uc = (UnionClass) dom.as(UnionClass.class);
                    ExtendedIterator domainIt = uc.listOperands();
                    while (domainIt.hasNext()) {
                        OntClass mc = (OntClass) domainIt.next();
                        domain.add(mc.getLocalName());
                    }
                } else {
                    domain.add(pro.getDomain().getLocalName());
                }
            }
            for (int j = 0; j < domain.size(); j++) {
                if (domain.get(j).equalsIgnoreCase(c)) {
                    propiedades.put(obcPro,"oown");
                }
            }
        }
        i = m.listDatatypeProperties().filterDrop(new Filter() {

            public boolean accept(Object o) {
                return ((Resource) o).isAnon();
            }
        });
        while (i.hasNext()) {
            DatatypeProperty pro = ((DatatypeProperty) i.next());
            String datPro = pro.getLocalName();
            ArrayList<String> domain = new ArrayList<String>();
            OntResource dom = pro.getDomain();
            if(dom != null){
                if (dom.canAs(UnionClass.class)) {
                    UnionClass uc = (UnionClass) dom.as(UnionClass.class);
                    ExtendedIterator domainIt = uc.listOperands();
                    while (domainIt.hasNext()) {
                        OntClass mc = (OntClass) domainIt.next();
                        domain.add(mc.getLocalName());
                    }
                } else {
                    domain.add(pro.getDomain().getLocalName());
                }
            }
            for (int j = 0; j < domain.size(); j++) {
                if (domain.get(j).equalsIgnoreCase(c)) {
                    propiedades.put(datPro,"down");
                }
            }
        }
        for(int q=0 ; q < padres.size() ; q ++){
            i = m.listObjectProperties().filterDrop(new Filter() {
                public boolean accept(Object o) {
                    return ((Resource) o).isAnon();
                }
            });
            while (i.hasNext()) {
                ObjectProperty pro = ((ObjectProperty) i.next());
                String obcPro = pro.getLocalName();
                ArrayList<String> domain = new ArrayList<String>();
                OntResource dom = pro.getDomain();
                if(dom != null){
                    if (dom.canAs(UnionClass.class)) {
                        UnionClass uc = (UnionClass) dom.as(UnionClass.class);
                        ExtendedIterator domainIt = uc.listOperands();
                        while (domainIt.hasNext()) {
                            OntClass mc = (OntClass) domainIt.next();
                            domain.add(mc.getLocalName());
                        }
                    } else {
                        domain.add(pro.getDomain().getLocalName());
                    }
                }
                for (int j = 0; j < domain.size(); j++) {
                    if (domain.get(j).equalsIgnoreCase(padres.get(q))) {
                        propiedades.put(obcPro,"oinherited");
                    }
                }
            }
            i = m.listDatatypeProperties().filterDrop(new Filter() {

                public boolean accept(Object o) {
                    return ((Resource) o).isAnon();
                }
            });
            while (i.hasNext()) {
                DatatypeProperty pro = ((DatatypeProperty) i.next());
                String datPro = pro.getLocalName();
                ArrayList<String> domain = new ArrayList<String>();
                OntResource dom = pro.getDomain();
                if(dom != null){
                    if (dom.canAs(UnionClass.class)) {
                        UnionClass uc = (UnionClass) dom.as(UnionClass.class);
                        ExtendedIterator domainIt = uc.listOperands();
                        while (domainIt.hasNext()) {
                            OntClass mc = (OntClass) domainIt.next();
                            domain.add(mc.getLocalName());
                        }
                    } else {
                        domain.add(pro.getDomain().getLocalName());
                    }
                }
                for (int j = 0; j < domain.size(); j++) {
                    if (domain.get(j).equalsIgnoreCase(padres.get(q))) {
                        propiedades.put(datPro,"dinherited");
                    }
                }
            }
        } 
        return propiedades;
    }

    public String getURIOntologia(OntModel m) {
        String uri = m.getNsPrefixMap().values().iterator().next().toString();
        uri = uri.substring(0, uri.length() - 1);
        return uri;
    }
    
    public String getURIOntologiaConNumeral(OntModel m) {
        String uri = m.getNsPrefixMap().values().iterator().next().toString();
        uri = uri.substring(0, uri.length());
        return uri;
    }

    public ArrayList<String> listIndividuals(OntModel m, String clase) {
        ArrayList<String> individuals = new ArrayList<String>();
        Iterator i = m.listIndividuals().filterDrop(new Filter() {
            public boolean accept(Object o) {
                return ((Resource) o).isAnon();
            }
        });
        while (i.hasNext()) {
            Individual indi = ((Individual) i.next());
            for (StmtIterator sIter = indi.listProperties(); sIter.hasNext();) {
                Statement s = (Statement) sIter.next();
                Triple tri = s.asTriple();
                if (tri.getObject().isLiteral()) {
                    if (tri.getPredicate().getLocalName().equals("type")) {
                        if (tri.getMatchObject().getLiteral().getValue().equals(clase)) {
                            individuals.add(indi.getLocalName());
                        } 
                    }
                } else {
                    if (tri.getPredicate().getLocalName().equals("type")) {
                        if (tri.getObject().getLocalName().equals(clase)) {
                            individuals.add(indi.getLocalName());
                        }
                    }
                }
            }   
        }
        return individuals;
    }

    public IndividualViajesVO obtenerIndividualViajes(OntModel m, String ind) {
        IndividualViajesVO indViajes = new IndividualViajesVO();
        String uri = getURIOntologiaConNumeral(m);
        Individual individual = m.getIndividual(uri+ind);
        indViajes.setNombre(individual.getLocalName());
        HashMap<String,String> objectProperties = new HashMap<String,String>();
        HashMap<String,HashMap<String,String>> datatypeProperties = new HashMap<String,HashMap<String,String>>();
        //Cargo las propiedades que tiene seteada el individual y cargo las que no tiene seteadas
        for ( StmtIterator sIter = individual.listProperties(); sIter.hasNext() ; )
        {
            Statement s = (Statement) sIter.next() ;
            Triple tri = s.asTriple();
            if(tri.getObject().isLiteral()){
                HashMap<String,String> datosDatatype = new HashMap<String,String>();
                if(tri.getObject().getLiteralDatatype() != null){
                    String[] tipo = tri.getObject().getLiteralDatatype().getURI().split("#");
                    datosDatatype.put(tri.getMatchObject().getLiteral().getValue().toString() , tipo[tipo.length-1]);
                    String nombre = tri.getPredicate().getLocalName();
                    datatypeProperties.put(nombre, datosDatatype);
                }
            }else{
                String valor = tri.getObject().getLocalName();
                String nombre = tri.getPredicate().getLocalName();
                if(!nombre.equalsIgnoreCase("type"))
                    objectProperties.put(nombre, valor);
            }
        }
        //Probar
        
        HashMap<String,String> propiedades = getProperty(m, individual.getOntClass().getLocalName());
        String[] prop = (String[]) propiedades.keySet().toArray();
        for( int i=0 ; i < propiedades.size() ; i++ )
        {
            Property pro = m.getProperty(uri+prop[i]);
            if(pro.isLiteral()){
                HashMap<String,String> datosDatatype = new HashMap<String,String>();
                if(!datatypeProperties.containsKey(prop[i])){
                    DatatypeProperty data = m.getDatatypeProperty(uri+prop[i]);
                    datosDatatype.put(null , data.getRange().getLocalName());
                    datatypeProperties.put(prop[i], datosDatatype);
                }
            }else{
                if(!objectProperties.containsKey(prop[i])){
                    objectProperties.put(prop[i], null);
                }
            }
        }
        indViajes.setDatatypeProperties(datatypeProperties);
        indViajes.setObjectProperties(objectProperties);
        return indViajes;
    }

    public void removeClass(OntModel m, String clase) {
        String uri = getURIOntologiaConNumeral(m);
        m.getOntClass(uri +clase).remove();
    }

    public void removeDatatypeProperty(OntModel m, String obj) {
        String uri = getURIOntologia(m);
        uri = uri + "#";
        m.getDatatypeProperty(uri + obj).remove();
    }

    //Testear
    public void removeDomain(OntModel m, String pro, String domain) {
        String uri = getURIOntologiaConNumeral(m);
        OntProperty property = m.getOntProperty(uri+pro);
        OntClass clase = m.getOntClass(uri+domain);
        property.removeDomain(clase);
    }

    public void removeIndividual(OntModel m, String ind) {
        String uri = getURIOntologiaConNumeral(m);
        m.getIndividual(uri+ind).remove();
    }

    public void removeObjectProperty(OntModel m, String property) {
        String uri = getURIOntologia(m);
        uri = uri + "#";
        m.getObjectProperty(uri + property).remove();
    }

    //Testear
    public void removePropertyOfClass(OntModel m, String clase, String pro) {
        String uri = getURIOntologiaConNumeral(m);
        List<String> data = getDatatypeProperties(m);
        List<String> obj = getObjectProperties(m);
        if(data.contains(pro)){
            DatatypeProperty datapro = m.getDatatypeProperty(uri+pro);
            OntClass ontclass = m.getOntClass(uri+clase);
            datapro.removeDomain(ontclass);
        }else if(obj.contains(pro)){
            ObjectProperty objpro = m.getObjectProperty(uri+pro);
            OntClass ontclass = m.getOntClass(uri+clase);
            objpro.removeDomain(ontclass);
        }
    }

    //Testear
    public void removeRange(OntModel m, String pro, String range) {
        String uri = getURIOntologiaConNumeral(m);
        ObjectProperty objpro = m.getObjectProperty(uri+pro);
        OntClass clase = m.getOntClass(uri+range);
        objpro.removeRange(clase);
    }

    public void removerTraduccion(OntModel m, String ind, String sin) {
        String uri = getURIOntologia(m);
        uri = uri + "#";
        Individual individual = m.getIndividual(uri + ind);
        OntProperty proper = m.getOntProperty(uri + "traduccion");
        Literal lit = m.createLiteral(sin);
        individual.removeProperty(proper, lit);
    }

    public void removerSinonimo(OntModel m, String ind, String sin) {
        String uri = getURIOntologia(m);
        uri = uri + "#";
        Individual individual = m.getIndividual(uri + ind);
        OntProperty proper = m.getOntProperty(uri + "sinonimo");
        Literal lit = m.createLiteral(sin);
        individual.removeProperty(proper, lit);
    }

    public void agregarTraduccion(OntModel m, String ind, String sin) {
        String uri = getURIOntologia(m);
        uri = uri + "#";
        Individual individual = m.getIndividual(uri + ind);
        individual.addProperty(m.getProperty(uri + "traduccion"), sin);
    }

    public void addSinonimo(OntModel m, String ind, String sin) {
        String uri = getURIOntologia(m);
        uri = uri + "#";
        Individual individual = m.getIndividual(uri + ind);
        individual.addProperty(m.getProperty(uri + "sinonimo"), sin);
    }

    public IndividualSinonimoVO showIndividualOfSinonimo(OntModel m, String ind) {
        IndividualSinonimoVO individual = null;
        Iterator i = m.listIndividuals().filterDrop(new Filter() {

            public boolean accept(Object o) {
                return ((Resource) o).isAnon();
            }
        });

        while (i.hasNext()) {
            Individual indi = ((Individual) i.next());
            if (indi.getLocalName().equals(ind)) {
                individual = new IndividualSinonimoVO();
                ArrayList<String> sinonimo = new ArrayList<String>();
                ArrayList<String> traduccion = new ArrayList<String>();
                for (StmtIterator sIter = indi.listProperties(); sIter.hasNext();) {
                    Statement s = (Statement) sIter.next();
                    Triple tri = s.asTriple();
                    if (tri.getObject().isLiteral()) {
                        if (!tri.getPredicate().getLocalName().equals("type")) {
                            if (tri.getPredicate().getLocalName().equals("sinonimo")) {
                                sinonimo.add((String) tri.getMatchObject().getLiteral().getValue());
                            } else {
                                traduccion.add((String) tri.getMatchObject().getLiteral().getValue());
                            }
                        }
                    } else {
                        if (!tri.getPredicate().getLocalName().equals("type")) {
                            if (tri.getPredicate().getLocalName().equals("sinonimo")) {
                                sinonimo.add((String) tri.getObject().getLocalName());
                            } else {
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

    public ArrayList<String> showIndividuals(OntModel m) {
        ArrayList<String> individuals = new ArrayList<String>();
        Iterator i = m.listIndividuals().filterDrop(new Filter() {

            public boolean accept(Object o) {
                return ((Resource) o).isAnon();
            }
        });

        while (i.hasNext()) {
            individuals.add(((Individual) i.next()).getLocalName());
        }

        return individuals;
    }

    public HashMap<String, String> showClass(OntModel m) {
        HashMap<String, String> clases = new HashMap<String, String>();
        Iterator i = m.listClasses().filterDrop(new Filter() {

            public boolean accept(Object o) {
                return ((Resource) o).isAnon();
            }
        });
        while (i.hasNext()) {
            OntClass cls = ((OntClass) i.next());
            showClass(clases, cls, null, new ArrayList(), 0);
        }
        return clases;
    }

    public void addClass(OntModel m, String clase, String padre) {
        String uri = m.getNsPrefixMap().get("").toString();
        OntClass ontClass = m.createClass(uri + clase);
        if(padre != null){
            m.getOntClass(uri + padre).addSubClass(ontClass);
        }
    }

    public void grabarOntologia(OntModel m, String url) {
        FileOutputStream fileout = null;
        try {
            fileout = new FileOutputStream(new File(url));
        } catch (FileNotFoundException ex) {
        }
        m.write(fileout, "RDF/XML-ABBREV");
    }
    //USO INTERNO 
    private void showClass(HashMap<String, String> clases, OntClass cls, OntClass sub, List occurs, int depth) {
        renderClassDescription(clases, cls, sub, depth);

        // recurse to the next level down
        if (cls.canAs(OntClass.class) && !occurs.contains(cls)) {
            for (Iterator i = cls.listSubClasses(true); i.hasNext();) {
                OntClass subs = (OntClass) i.next();

                // we push this expression on the occurs list before we recurse
                occurs.add(cls);
                showClass(clases, cls, subs, occurs, depth + 1);
                occurs.remove(cls);
            }
        }
    }

    private void renderClassDescription(HashMap<String, String> clases, OntClass cls, OntClass sub, int depth) {
        if (sub == null) {
            if (!clases.containsKey(cls.getLocalName())) {
                clases.put(cls.getLocalName(), "");
            }
        } else {
            //if(!clases.containsKey(sub.getLocalName()))
            clases.put(sub.getLocalName(), cls.getLocalName());
        }
    }
}
