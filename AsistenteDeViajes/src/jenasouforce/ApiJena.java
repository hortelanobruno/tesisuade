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
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.rdf.model.impl.LiteralImpl;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.util.iterator.Filter;
import com.hp.hpl.jena.vocabulary.XSD;
import configuration.AdvancedProperty;
import configuration.Configuration;
import configuration.TipoDato;
import configuration.defaultontology.DefaultOntology;
import configuration.defaultontology.types.DefaultProperty;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import varios.ErrorTransoformacion;
import vo.DatatypePropertyVO;
import vo.IndividualSinonimoVO;
import vo.IndividualViajesVO;
import vo.ObjectPropertyVO;
import vo.busqueda.ConsultaVO;
import vo.busqueda.IndividualVO;

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
    }

    public void addDatatypePropertyToClass(OntModel m, String clase, String pro) {
        String uri = getURIOntologiaConNumeral(m);
        OntClass clasee = m.getOntClass(uri + clase);
        DatatypeProperty proo = m.getDatatypeProperty(uri + pro);
        if (proo.getDomain() == null) {
            proo.addDomain(clasee);
        } else {
            OntResource dom = proo.getDomain();
            if (dom.canAs(UnionClass.class)) {
                List<String> domain = new ArrayList<String>();
                UnionClass uc = (UnionClass) dom.as(UnionClass.class);
                ExtendedIterator domainIt = uc.listOperands();
                while (domainIt.hasNext()) {
                    OntClass mc = (OntClass) domainIt.next();
                    domain.add(mc.getLocalName());
                }
                OntClass clases[] = new OntClass[domain.size()];
                for (int i = 0; i < domain.size(); i++) {
                    clases[i] = m.getOntClass(uri + domain.get(i));
                }
                RDFNode nodes[] = new RDFNode[domain.size() + 1];
                for (int i = 0; i < domain.size(); i++) {
                    nodes[i] = clases[i];
                }
                nodes[nodes.length - 1] = clasee;
                UnionClass ontUn = m.createUnionClass(null, m.createList(nodes));
                proo.setDomain(ontUn);
            } else {
                OntResource domm = (OntResource) proo.getDomain();
                UnionClass ontUn = m.createUnionClass(null, m.createList(new RDFNode[]{domm, clasee}));
                proo.setDomain(ontUn);
            }
        }
    }

    public void addDomain(OntModel m, String pro, String domaina) {
        String uri = getURIOntologiaConNumeral(m);
        OntProperty property = m.getOntProperty(uri + pro);
        OntClass clase = m.getOntClass(uri + domaina);
        if (property.getDomain() == null) {
            property.addDomain(clase);
        } else {
            OntResource dom = property.getDomain();
            if (dom.canAs(UnionClass.class)) {
                List<String> domain = new ArrayList<String>();
                UnionClass uc = (UnionClass) dom.as(UnionClass.class);
                ExtendedIterator domainIt = uc.listOperands();
                while (domainIt.hasNext()) {
                    OntClass mc = (OntClass) domainIt.next();
                    domain.add(mc.getLocalName());
                }
                OntClass clases[] = new OntClass[domain.size()];
                for (int i = 0; i < domain.size(); i++) {
                    clases[i] = m.getOntClass(uri + domain.get(i));
                }
                RDFNode nodes[] = new RDFNode[domain.size() + 1];
                for (int i = 0; i < domain.size(); i++) {
                    nodes[i] = clases[i];
                }
                nodes[nodes.length - 1] = clase;
                UnionClass ontUn = m.createUnionClass(null, m.createList(nodes));
                property.setDomain(ontUn);
            } else {
                OntResource domm = (OntResource) property.getDomain();
                UnionClass ontUn = m.createUnionClass(null, m.createList(new RDFNode[]{domm, clase}));
                property.setDomain(ontUn);
            }
        }
    }

    public void addIndividual(OntModel m, String ind, String cla) {
        String uri = getURIOntologiaConNumeral(m);
        OntClass clase = m.getOntClass(uri + cla);
        Individual individual = m.createIndividual(uri + ind, clase);
    }

    public void addObjectProperty(OntModel m, String obj) {
        String uri = getURIOntologiaConNumeral(m);
        m.createObjectProperty(uri + obj);
    }

    public void addObjectPropertyToClass(OntModel m, String clase, String pro) {
        String uri = getURIOntologiaConNumeral(m);
        OntClass clasee = m.getOntClass(uri + clase);
        ObjectProperty proo = m.getObjectProperty(uri + pro);
        if (proo.getDomain() == null) {
            proo.addDomain(clasee);
        } else {
            OntResource dom = proo.getDomain();
            if (dom.canAs(UnionClass.class)) {
                List<String> domain = new ArrayList<String>();
                UnionClass uc = (UnionClass) dom.as(UnionClass.class);
                ExtendedIterator domainIt = uc.listOperands();
                while (domainIt.hasNext()) {
                    OntClass mc = (OntClass) domainIt.next();
                    domain.add(mc.getLocalName());
                }
                OntClass clases[] = new OntClass[domain.size()];
                for (int i = 0; i < domain.size(); i++) {
                    clases[i] = m.getOntClass(uri + domain.get(i));
                }
                RDFNode nodes[] = new RDFNode[domain.size() + 1];
                for (int i = 0; i < domain.size(); i++) {
                    nodes[i] = clases[i];
                }
                nodes[nodes.length - 1] = clasee;
                UnionClass ontUn = m.createUnionClass(null, m.createList(nodes));
                proo.setDomain(ontUn);
            } else {
                OntResource domm = (OntResource) proo.getDomain();
                UnionClass ontUn = m.createUnionClass(null, m.createList(new RDFNode[]{domm, clasee}));
                proo.setDomain(ontUn);
            }
        }
    }

    public void addRange(OntModel m, String pro, String range) {
        String uri = getURIOntologiaConNumeral(m);
        OntProperty property = m.getOntProperty(uri + pro);
        OntClass clase = m.getOntClass(uri + range);
        if (property.getRange() == null) {
            property.addRange(clase);
        } else {
            OntResource dom = property.getRange();
            if (dom.canAs(UnionClass.class)) {
                List<String> range2 = new ArrayList<String>();
                UnionClass uc = (UnionClass) dom.as(UnionClass.class);
                ExtendedIterator domainIt = uc.listOperands();
                while (domainIt.hasNext()) {
                    OntClass mc = (OntClass) domainIt.next();
                    range2.add(mc.getLocalName());
                }
                OntClass clases[] = new OntClass[range2.size()];
                for (int i = 0; i < range2.size(); i++) {
                    clases[i] = m.getOntClass(uri + range2.get(i));
                }
                RDFNode nodes[] = new RDFNode[range2.size() + 1];
                for (int i = 0; i < range2.size(); i++) {
                    nodes[i] = clases[i];
                }
                nodes[nodes.length - 1] = clase;
                UnionClass ontUn = m.createUnionClass(null, m.createList(nodes));
                property.setRange(ontUn);
            } else {
                OntResource domm = (OntResource) property.getRange();
                UnionClass ontUn = m.createUnionClass(null, m.createList(new RDFNode[]{domm, clase}));
                property.setRange(ontUn);
            }
        }
    }

    public void agregarPalabra(OntModel m, String ins) {
        String uri = getURIOntologiaConNumeral(m);
        OntClass clase = m.getOntClass(uri + "palabra");
        Individual individual = m.createIndividual(uri + ins, clase);
    }

    public ArrayList<IndividualVO> buscarIndividualHotel(OntModel m, ConsultaVO vuelo, DefaultOntology defOnt) {
        ArrayList<IndividualVO> lista = new ArrayList<IndividualVO>();
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
                } else {
                    propiedades.put(tri.getPredicate().getLocalName(), tri.getObject().getLocalName());
                }
            }
            if (propiedades.get("type").equalsIgnoreCase(defOnt.getAlojamiento().getNombreClase())) {
                if (coincideHotel(vuelo, propiedades, defOnt)) {
                    IndividualVO invue = cargarIndividualHotelVO(propiedades, defOnt);
                    invue.setNameIndividual(ind.getLocalName());
                    invue.setUri(ind.getURI());
                    lista.add(invue);
                }
            }
        }
        return lista;
    }

    private boolean coincideHotel(ConsultaVO hotel, HashMap<String, String> propiedades, DefaultOntology defOnt) {
        List<String> propNames = defOnt.getAlojamiento().getDefaultPropertiesNames();
        String value;
        for (String prop : propNames) {
            value = propiedades.get(prop);
            if (!value.equalsIgnoreCase(hotel.getPropiedadesPrincipales().get(prop).toString())) {
                return false;
            }
        }
        return true;
    }

    public IndividualVO cargarIndividualHotelVO(HashMap<String, String> p, DefaultOntology defOnt) {
        IndividualVO invue = new IndividualVO();
        for (String prop : defOnt.getAlojamiento().getDefaultPropertiesNames()) {
            if (p.containsKey(prop)) {
                invue.getPropiedadesPrincipales().put(prop, p.get(prop));
                p.remove(prop);
            }
        }
        for (String prop : p.keySet()) {
            invue.getPropiedadesAvanzadas().put(prop, p.get(prop));
        }
        return invue;
    }

    public ArrayList<IndividualVO> buscarIndividualAuto(OntModel m, ConsultaVO vuelo, DefaultOntology defOnt) {
        ArrayList<IndividualVO> lista = new ArrayList<IndividualVO>();
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
                } else {
                    propiedades.put(tri.getPredicate().getLocalName(), tri.getObject().getLocalName());
                }
            }
            if (propiedades.get("type").equalsIgnoreCase(defOnt.getTranslado().getNombreClase())) {
                if (coincideAuto(vuelo, propiedades, defOnt)) {
                    IndividualVO invue = cargarIndividualAutoVO(propiedades, defOnt);
                    invue.setNameIndividual(ind.getLocalName());
                    invue.setUri(ind.getURI());
                    lista.add(invue);
                }
            }
        }
        return lista;
    }

    private boolean coincideAuto(ConsultaVO auto, HashMap<String, String> propiedades, DefaultOntology defOnt) {
        List<String> propNames = defOnt.getTranslado().getDefaultPropertiesNames();
        String value;
        for (String prop : propNames) {
            value = propiedades.get(prop);
            if (!value.equalsIgnoreCase(auto.getPropiedadesPrincipales().get(prop).toString())) {
                return false;
            }
        }
        return true;
    }

    public IndividualVO cargarIndividualAutoVO(HashMap<String, String> p, DefaultOntology defOnt) {
        IndividualVO invue = new IndividualVO();
        for (String prop : defOnt.getTranslado().getDefaultPropertiesNames()) {
            if (p.containsKey(prop)) {
                invue.getPropiedadesPrincipales().put(prop, p.get(prop));
                p.remove(prop);
            }
        }
        for (String prop : p.keySet()) {
            invue.getPropiedadesAvanzadas().put(prop, p.get(prop));
        }
        return invue;
    }

    public ArrayList<IndividualVO> buscarIndividualVuelo(OntModel m, ConsultaVO vuelo, DefaultOntology defOnt) {
        ArrayList<IndividualVO> lista = new ArrayList<IndividualVO>();
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
                } else {
                    propiedades.put(tri.getPredicate().getLocalName(), tri.getObject().getLocalName());
                }
            }
            if (propiedades.get("type").equalsIgnoreCase(defOnt.getViaje().getNombreClase())) {
                if (coincideVuelo(vuelo, propiedades, defOnt)) {
                    IndividualVO invue = cargarIndividualVueloVO(propiedades, defOnt);
                    invue.setNameIndividual(ind.getLocalName());
                    invue.setUri(ind.getURI());
                    lista.add(invue);
                }
            }
        }
        return lista;
    }

    private boolean coincideVuelo(ConsultaVO vuelo, HashMap<String, String> propiedades, DefaultOntology defOnt) {
        List<String> propNames = defOnt.getViaje().getDefaultPropertiesNames();
        String value;
        for (String prop : propNames) {
            value = propiedades.get(prop);
            if (!value.equalsIgnoreCase(vuelo.getPropiedadesPrincipales().get(prop).toString())) {
                return false;
            }
        }
        return true;
    }

    public IndividualVO cargarIndividualVueloVO(HashMap<String, String> p, DefaultOntology defOnt) {
        IndividualVO invue = new IndividualVO();
        for (String prop : defOnt.getViaje().getDefaultPropertiesNames()) {
            if (p.containsKey(prop)) {
                invue.getPropiedadesPrincipales().put(prop, p.get(prop));
                p.remove(prop);
            }
        }
        for (String prop : p.keySet()) {
            invue.getPropiedadesAvanzadas().put(prop, p.get(prop));
        }
        return invue;
    }
    
    public void cargarPropiedadIndividual(OntModel m, String ind, String pro, String valor) {
        String uri = getURIOntologiaConNumeral(m);
        Individual individual = m.getIndividual(uri + ind);
        OntProperty propiedad = m.getOntProperty(uri + pro);
        if (propiedad.isDatatypeProperty()) {
            DatatypeProperty datatypeProperty = m.getDatatypeProperty(uri + pro);
            individual.addLiteral(datatypeProperty, valor);
        } else {
            ObjectProperty objectProperty = m.getObjectProperty(uri + pro);
            individual.addProperty(objectProperty, valor);
        }
    }

    public void changePropiedadIndividual(OntModel m, String ind, String pro, String valor) {
        String uri = getURIOntologiaConNumeral(m);
        Individual individual = m.getIndividual(uri + ind);
        OntProperty propiedad = m.getOntProperty(uri + pro);
        if (propiedad.isDatatypeProperty()) {
            DatatypeProperty datatypeProperty = m.getDatatypeProperty(uri + pro);
            if(individual.getProperty(datatypeProperty) != null){
                if (individual.getProperty(datatypeProperty).getLiteral() != null) {
                    Literal lit = individual.getProperty(datatypeProperty).getLiteral();
                    individual.removeProperty(datatypeProperty, lit);
                    individual.addLiteral(datatypeProperty, valor);
                } else {
                    individual.addLiteral(datatypeProperty, valor);
                }
            }else{
                individual.addLiteral(datatypeProperty, valor);
            }
        } else {
            ObjectProperty objectProperty = m.getObjectProperty(uri + pro);
            individual.getProperty(objectProperty).changeObject(valor);
        }
    }

    //Testear
    public void changeIndividualClass(OntModel m, String old, String nuevo) {
        String uri = getURIOntologiaConNumeral(m);
        Individual oldInd = m.getIndividual(uri + old);
        Individual newInd = m.createIndividual(uri + nuevo, oldInd.getOntClass());
        for (StmtIterator sIter = oldInd.listProperties(); sIter.hasNext();) {
            Statement s = (Statement) sIter.next();
            newInd.addProperty(s.getPredicate(), s.getObject());
        }
    }

    //Testear
    public void changeNameClass(OntModel m, String old, String nuevo) {
        String uri = getURIOntologiaConNumeral(m);
        OntClass oldclass = m.getOntClass(uri + old);
        OntClass newclass = m.createClass(uri + nuevo);
        if (oldclass.getSuperClass() != null) {
            newclass.addSuperClass(oldclass.getSuperClass());
        }
        if (oldclass.getSubClass() != null) {
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
            if (dom != null) {
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
            if (dom != null) {
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
        DatatypeProperty proOld = m.getDatatypeProperty(uri + old);
        DatatypeProperty proNew = m.createDatatypeProperty(uri + name);
        if (proOld.getRange() != null) {
            proNew.setRange(proOld.getRange());
        }
        if (proOld.getDomain() != null) {
            proNew.setDomain(proOld.getDomain());
        }
        proOld.remove();
    }

    //Testear
    public void changeNameObjectProperty(OntModel m, String old, String name) {
        String uri = getURIOntologiaConNumeral(m);
        ObjectProperty proOld = m.getObjectProperty(uri + old);
        ObjectProperty proNew = m.createObjectProperty(uri + name);
        if (proOld.getRange() != null) {
            proNew.setRange(proOld.getRange());
        }
        if (proOld.getDomain() != null) {
            proNew.setDomain(proOld.getDomain());
        }
        proOld.remove();
    }

    //Testear
    public void changeRange(OntModel m, String pro, String range) {
        //date, string, int, double, float, boolean
        String uri = getURIOntologiaConNumeral(m);
        DatatypeProperty proOld = m.getDatatypeProperty(uri + pro);
        if (range.equalsIgnoreCase("int")) {
            proOld.setRange(XSD.xint);
        }
        if (range.equalsIgnoreCase("date")) {
            proOld.setRange(XSD.date);
        }
        if (range.equalsIgnoreCase("string")) {
            proOld.setRange(XSD.xstring);
        }
        if (range.equalsIgnoreCase("float")) {
            proOld.setRange(XSD.xfloat);
        }
        if (range.equalsIgnoreCase("boolean")) {
            proOld.setRange(XSD.xboolean);
        }
        if (range.equalsIgnoreCase("double")) {
            proOld.setRange(XSD.xdouble);
        }
    }

    public String convertirPropiedad(OntModel sinonimo, String propiedad) {
        String uri = getURIOntologiaConNumeral(sinonimo);
        List<String> individuals = showIndividuals(sinonimo);
        for (int i = 0; i < individuals.size(); i++) {
            Individual individual = sinonimo.getIndividual(uri + individuals.get(i));
            for (StmtIterator sIter = individual.listProperties(); sIter.hasNext();) {
                Statement s = (Statement) sIter.next();
                Triple tri = s.asTriple();
                String name = individual.getLocalName();
                if (tri.getObject().isLiteral()) {
                    String valor = tri.getMatchObject().getLiteral().getValue().toString();
                    if (valor.equalsIgnoreCase(propiedad)) {
                        return name;
                    }
                } else {
                    String valor = tri.getObject().getLocalName();
                    String nombre = tri.getPredicate().getLocalName();
                    if (!nombre.equalsIgnoreCase("type")) {
                        if (valor.equalsIgnoreCase(propiedad)) {
                            return name;
                        }
                    }
                }
            }
        }
        return null;
    }

    public ErrorTransoformacion generarOntologiaBusqueda2(Configuration conf, OntModel ontologia, OntModel sinonimo, OntModel nueva, DefaultOntology defaultOntology) {
        //Chequeo que en estas clases esten las 3 requeridas
        DefaultOntology newDefaultOntology = chequearCumplimiento3Clases(sinonimo, ontologia, defaultOntology);
        if (newDefaultOntology != null) {
            System.out.println("Cumple default ontology");
            //Como cumple tengo q pasar las clases con las propiedades
            //Creo todo
            createClassWithPropForProcesodeTransformacion(nueva, ontologia, defaultOntology, newDefaultOntology);
            //Ahora hay que traducir todo
            List<String> errores = translateAllOntology(nueva, sinonimo);
            if(errores.isEmpty()){
                //Agrego propiedades nuevas a la configuracion
                addNewPropertyForConfiguration(conf, nueva, defaultOntology);
                return null;
            }else{
                //No tiene todos los sinonimos
                ErrorTransoformacion error = new ErrorTransoformacion();
                error.setError("Faltan sinonimos para traducir la ontologia");
                error.setSubErrores(errores);
                return error;
            }
        } else {
            //No cumple con la ontologia default
            ErrorTransoformacion error = new ErrorTransoformacion();
            error.setError("NO Cumple con la Ontologia Base");
            System.out.println("NO Cumple default ontology");
            return error;
        }


    }

    private void addNewPropertyForConfiguration(Configuration conf, OntModel nueva, DefaultOntology defaultOntology) {
        AdvancedProperty advPro;
        String range;
        //Primero para alojameinto
        String clase = defaultOntology.getAlojamiento().getNombreClase();
        Set<String> props = getProperty(nueva, clase).keySet();
        for (String prop : props) {
            if (!defaultOntology.getAlojamiento().getDefaultPropertiesNames().contains(prop)) {
                if (!conf.getNombrePropiedadesAvanzadasHotel().contains(prop)) {
                    range = getDatatypeProperty(nueva, prop).getRange();
                    if (range.equalsIgnoreCase("Date")) {
                        advPro = new AdvancedProperty(prop, TipoDato.DATE);
                    } else if (range.equalsIgnoreCase("String")) {
                        advPro = new AdvancedProperty(prop, TipoDato.STRING);
                    } else if (range.equalsIgnoreCase("int")) {
                        advPro = new AdvancedProperty(prop, TipoDato.INTEGER);
                    } else if (range.equalsIgnoreCase("Float")) {
                        advPro = new AdvancedProperty(prop, TipoDato.FLOAT);
                    } else if (range.equalsIgnoreCase("Boolean")) {
                        advPro = new AdvancedProperty(prop, TipoDato.BOOLEAN);
                    } else {
                        System.out.println("ERROR EN TIPO DATO");
                        return;
                    }
                    conf.getPropiedadesAvanzadasHotel().add(advPro);
                }
            }
        }
        //Para vuelo
        clase = defaultOntology.getViaje().getNombreClase();
        props = getProperty(nueva, clase).keySet();
        for (String prop : props) {
            if (!defaultOntology.getViaje().getDefaultPropertiesNames().contains(prop)) {
                if (!conf.getNombrePropiedadesAvanzadasVuelo().contains(prop)) {
                    range = getDatatypeProperty(nueva, prop).getRange();
                    if (range.equalsIgnoreCase("Date")) {
                        advPro = new AdvancedProperty(prop, TipoDato.DATE);
                    } else if (range.equalsIgnoreCase("String")) {
                        advPro = new AdvancedProperty(prop, TipoDato.STRING);
                    } else if (range.equalsIgnoreCase("int")) {
                        advPro = new AdvancedProperty(prop, TipoDato.INTEGER);
                    } else if (range.equalsIgnoreCase("Float")) {
                        advPro = new AdvancedProperty(prop, TipoDato.FLOAT);
                    } else if (range.equalsIgnoreCase("Boolean")) {
                        advPro = new AdvancedProperty(prop, TipoDato.BOOLEAN);
                    } else {
                        System.out.println("ERROR EN TIPO DATO");
                        return;
                    }
                    conf.getPropiedadesAvanzadasVuelo().add(advPro);
                }
            }
        }
        //Para auto
        clase = defaultOntology.getTranslado().getNombreClase();
        props = getProperty(nueva, clase).keySet();
        for (String prop : props) {
            if (!defaultOntology.getTranslado().getDefaultPropertiesNames().contains(prop)) {
                if (!conf.getNombrePropiedadesAvanzadasAuto().contains(prop)) {
                    range = getDatatypeProperty(nueva, prop).getRange();
                    if (range.equalsIgnoreCase("Date")) {
                        advPro = new AdvancedProperty(prop, TipoDato.DATE);
                    } else if (range.equalsIgnoreCase("String")) {
                        advPro = new AdvancedProperty(prop, TipoDato.STRING);
                    } else if (range.equalsIgnoreCase("int")) {
                        advPro = new AdvancedProperty(prop, TipoDato.INTEGER);
                    } else if (range.equalsIgnoreCase("Float")) {
                        advPro = new AdvancedProperty(prop, TipoDato.FLOAT);
                    } else if (range.equalsIgnoreCase("Boolean")) {
                        advPro = new AdvancedProperty(prop, TipoDato.BOOLEAN);
                    } else {
                        System.out.println("ERROR EN TIPO DATO");
                        return;
                    }
                    conf.getPropiedadesAvanzadasAuto().add(advPro);
                }
            }
        }
    }

    private List<String> translateAllOntology(OntModel nueva, OntModel sinonimo) {
        List<String> errores = new ArrayList<String>();
        Set<String> classes = showClass(nueva).keySet();
        for (String clase : classes) {
            //Cambio propiedades
            Set<String> propAlo = getProperty(nueva, clase).keySet();
            for (String pro : propAlo) {
                String tipo = getProperty(nueva, clase).get(pro);
                if (tipo.startsWith("d")) {
                    //datatype
                    String newProp;
                    try {
                        newProp = getPalabraOfSinOrTra(sinonimo, pro);
                        if (newProp != null) {
                            changeNameDatatypeProperty(nueva, tipo, newProp);
                        }
                    } catch (Exception ex) {
                        errores.add("No hay sinonimo para: "+pro);
                    }
                } else {
                    //obj
                    String newProp;
                    try {
                        newProp = getPalabraOfSinOrTra(sinonimo, pro);
                        if (newProp != null) {
                            changeNameObjectProperty(nueva, tipo, newProp);
                        }
                    } catch (Exception ex) {
                        errores.add("No hay sinonimo para: "+pro);
                    }
                }
            }

            //Cambio nombre de clase
            String newClase;
            try {
                newClase = getPalabraOfSinOrTra(sinonimo, clase);
                if (newClase != null) {
                    changeNameClass(nueva, clase, newClase);
                }
            } catch (Exception ex) {
                errores.add("No hay sinonimo para: "+clase);
            }
        }
        return errores;
    }

    private void createClassWithPropForProcesodeTransformacion(OntModel nueva, OntModel ontologia, DefaultOntology defaultOntology, DefaultOntology newDefaultOntology) {
        //Alojamiento
        addClass(nueva, defaultOntology.getAlojamiento().getNombreClase(), null);
        Set<String> propAlo = getProperty(ontologia, newDefaultOntology.getAlojamiento().getNombreClase()).keySet();
        for (String pro : propAlo) {
            String tipo = getProperty(ontologia, defaultOntology.getAlojamiento().getNombreClase()).get(pro);
            if (tipo.startsWith("d")) {
                //datatype
                addDatatypeProperty(nueva, pro);
                addDatatypePropertyToClass(nueva, defaultOntology.getAlojamiento().getNombreClase(), pro);
                changeRange(nueva, pro, getDatatypeProperty(ontologia, pro).getRange());
            } else {
                //obj
                addDatatypeProperty(nueva, pro);
                addDatatypePropertyToClass(nueva, defaultOntology.getAlojamiento().getNombreClase(), pro);
                changeRange(nueva, pro, "string");
            }
        }
        List<String> inds = listIndividuals(ontologia, newDefaultOntology.getAlojamiento().getNombreClase());
        for (String ind : inds) {
            addIndividual(nueva, ind, defaultOntology.getAlojamiento().getNombreClase());
            IndividualViajesVO indVO = obtenerIndividualViajes(ontologia, ind);
            for (DatatypePropertyVO datVO : indVO.getDatatypeProperties()) {
                cargarPropiedadIndividual(nueva, ind, datVO.getName(), datVO.getValor());
            }
            for (ObjectPropertyVO objVO : indVO.getObjectProperties()) {
                cargarPropiedadIndividual(nueva, ind, objVO.getName(), objVO.getValor());
            }
        }

        //Viaje
        addClass(nueva, defaultOntology.getViaje().getNombreClase(), null);
        propAlo = getProperty(ontologia, newDefaultOntology.getViaje().getNombreClase()).keySet();
        for (String pro : propAlo) {
            String tipo = getProperty(ontologia, defaultOntology.getViaje().getNombreClase()).get(pro);
            if (tipo.startsWith("d")) {
                //datatype
                addDatatypeProperty(nueva, pro);
                addDatatypePropertyToClass(nueva, defaultOntology.getViaje().getNombreClase(), pro);
                changeRange(nueva, pro, getDatatypeProperty(ontologia, pro).getRange());
            } else {
                //obj
                addDatatypeProperty(nueva, pro);
                addDatatypePropertyToClass(nueva, defaultOntology.getViaje().getNombreClase(), pro);
                changeRange(nueva, pro, "string");
            }
        }
        inds = listIndividuals(ontologia, newDefaultOntology.getViaje().getNombreClase());
        for (String ind : inds) {
            addIndividual(nueva, ind, defaultOntology.getViaje().getNombreClase());
            IndividualViajesVO indVO = obtenerIndividualViajes(ontologia, ind);
            for (DatatypePropertyVO datVO : indVO.getDatatypeProperties()) {
                cargarPropiedadIndividual(nueva, ind, datVO.getName(), datVO.getValor());
            }
            for (ObjectPropertyVO objVO : indVO.getObjectProperties()) {
                cargarPropiedadIndividual(nueva, ind, objVO.getName(), objVO.getValor());
            }
        }


        //Auto
        addClass(nueva, defaultOntology.getTranslado().getNombreClase(), null);
        propAlo = getProperty(ontologia, newDefaultOntology.getTranslado().getNombreClase()).keySet();
        for (String pro : propAlo) {
            String tipo = getProperty(ontologia, defaultOntology.getTranslado().getNombreClase()).get(pro);
            if (tipo.startsWith("d")) {
                //datatype
                addDatatypeProperty(nueva, pro);
                addDatatypePropertyToClass(nueva, defaultOntology.getTranslado().getNombreClase(), pro);
                changeRange(nueva, pro, getDatatypeProperty(ontologia, pro).getRange());
            } else {
                //obj
                addDatatypeProperty(nueva, pro);
                addDatatypePropertyToClass(nueva, defaultOntology.getTranslado().getNombreClase(), pro);
                changeRange(nueva, pro, "string");
            }
        }
        inds = listIndividuals(ontologia, newDefaultOntology.getTranslado().getNombreClase());
        for (String ind : inds) {
            addIndividual(nueva, ind, defaultOntology.getTranslado().getNombreClase());
            IndividualViajesVO indVO = obtenerIndividualViajes(ontologia, ind);
            for (DatatypePropertyVO datVO : indVO.getDatatypeProperties()) {
                cargarPropiedadIndividual(nueva, ind, datVO.getName(), datVO.getValor());
            }
            for (ObjectPropertyVO objVO : indVO.getObjectProperties()) {
                cargarPropiedadIndividual(nueva, ind, objVO.getName(), objVO.getValor());
            }
        }
    }

    private String getPalabraOfSinOrTra(OntModel sinonimo, String sinOrTra) throws Exception {
        ArrayList<String> inds = listIndividuals(sinonimo, "palabra");
        for (String ind : inds) {
            if (ind.equalsIgnoreCase(sinOrTra)) {
                return null;
            } else {
                List<String> trads = getSinAndTraOfPalabra(sinonimo, ind);
                for (String trad : trads) {
                    if (trad.equalsIgnoreCase(sinOrTra)) {
                        return ind;
                    }
                }
            }
        }
        throw new Exception("No hay sinonimo");
    }

    private List<String> getSinAndTraOfPalabra(OntModel sinonimo, String palabra) {
        List<String> posiblesNombresDeAlojamiento = new ArrayList<String>();
        posiblesNombresDeAlojamiento.add(palabra);
        IndividualSinonimoVO sinVO = showIndividualOfSinonimo(sinonimo, palabra);
        if (sinVO != null) {
            posiblesNombresDeAlojamiento.addAll(sinVO.getSinonimos());
            posiblesNombresDeAlojamiento.addAll(sinVO.getTraduccion());
        }
        return posiblesNombresDeAlojamiento;
    }

    private String chequearCumplimientoSinonimos(List<String> posiblesNombres, List<String> muestra) {
        for (String pos : posiblesNombres) {
            if (muestra.contains(pos)) {
                return pos;
            }
        }
        return null;
    }

    private boolean chequearCumplientoTipoAlojamiento(DefaultOntology AA, String nombreClase, List<DefaultProperty> defaultsPropAlojamiento, OntModel sinonimo, OntModel ontologia) {
        Set<String> clases = showClass(ontologia).keySet();
        List<String> posiblesNombresDeAlojamiento = getSinAndTraOfPalabra(sinonimo, nombreClase);
        String resultAlojamiento = chequearCumplimientoSinonimos(posiblesNombresDeAlojamiento, new ArrayList<String>(clases));
        if (resultAlojamiento != null) {
            AA.getAlojamiento().setNombreClase(resultAlojamiento);
        } else {
            return false;
        }
        //Chequeo prop alojamiento
        Set<String> muestraPropAlo = getProperty(ontologia, resultAlojamiento).keySet();
        for (DefaultProperty defProp : defaultsPropAlojamiento) {
            List<String> posPropDeAlojamiento = getSinAndTraOfPalabra(sinonimo, defProp.getName());
            String resPropDeAlojamiento = chequearCumplimientoSinonimos(posPropDeAlojamiento, new ArrayList<String>(muestraPropAlo));
            if (resPropDeAlojamiento != null) {
                String tipo = getProperty(ontologia, resultAlojamiento).get(resPropDeAlojamiento);
                if (tipo.startsWith("d")) {
                    //es datatype
                    tipo = getDatatypeProperty(ontologia, resPropDeAlojamiento).getRange();
                    if (tipo.equalsIgnoreCase("Date")) {
                        AA.getAlojamiento().getDefaultProperties().add(new DefaultProperty(resPropDeAlojamiento, TipoDato.DATE));
                    } else if (tipo.equalsIgnoreCase("String")) {
                        AA.getAlojamiento().getDefaultProperties().add(new DefaultProperty(resPropDeAlojamiento, TipoDato.STRING));
                    } else if (tipo.equalsIgnoreCase("int")) {
                        AA.getAlojamiento().getDefaultProperties().add(new DefaultProperty(resPropDeAlojamiento, TipoDato.INTEGER));
                    } else if (tipo.equalsIgnoreCase("Float")) {
                        AA.getAlojamiento().getDefaultProperties().add(new DefaultProperty(resPropDeAlojamiento, TipoDato.FLOAT));
                    } else if (tipo.equalsIgnoreCase("Boolean")) {
                        AA.getAlojamiento().getDefaultProperties().add(new DefaultProperty(resPropDeAlojamiento, TipoDato.BOOLEAN));
                    }
                } else {
                    //es object
                    AA.getAlojamiento().getDefaultProperties().add(new DefaultProperty(resPropDeAlojamiento, TipoDato.ANY));
                }
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean chequearCumplientoTipoViaje(DefaultOntology AA, String nombreClase, List<DefaultProperty> defaultsPropAlojamiento, OntModel sinonimo, OntModel ontologia) {
        Set<String> clases = showClass(ontologia).keySet();
        List<String> posiblesNombresDeAlojamiento = getSinAndTraOfPalabra(sinonimo, nombreClase);
        String resultAlojamiento = chequearCumplimientoSinonimos(posiblesNombresDeAlojamiento, new ArrayList<String>(clases));
        if (resultAlojamiento != null) {
            AA.getViaje().setNombreClase(resultAlojamiento);
        } else {
            return false;
        }
        //Chequeo prop alojamiento
        Set<String> muestraPropAlo = getProperty(ontologia, resultAlojamiento).keySet();
        for (DefaultProperty defProp : defaultsPropAlojamiento) {
            List<String> posPropDeAlojamiento = getSinAndTraOfPalabra(sinonimo, defProp.getName());
            String resPropDeAlojamiento = chequearCumplimientoSinonimos(posPropDeAlojamiento, new ArrayList<String>(muestraPropAlo));
            if (resPropDeAlojamiento != null) {
                String tipo = getProperty(ontologia, resultAlojamiento).get(resPropDeAlojamiento);
                if (tipo.startsWith("d")) {
                    //es datatype
                    tipo = getDatatypeProperty(ontologia, resPropDeAlojamiento).getRange();
                    if (tipo.equalsIgnoreCase("Date")) {
                        AA.getViaje().getDefaultProperties().add(new DefaultProperty(resPropDeAlojamiento, TipoDato.DATE));
                    } else if (tipo.equalsIgnoreCase("String")) {
                        AA.getViaje().getDefaultProperties().add(new DefaultProperty(resPropDeAlojamiento, TipoDato.STRING));
                    } else if (tipo.equalsIgnoreCase("int")) {
                        AA.getViaje().getDefaultProperties().add(new DefaultProperty(resPropDeAlojamiento, TipoDato.INTEGER));
                    } else if (tipo.equalsIgnoreCase("Float")) {
                        AA.getViaje().getDefaultProperties().add(new DefaultProperty(resPropDeAlojamiento, TipoDato.FLOAT));
                    } else if (tipo.equalsIgnoreCase("Boolean")) {
                        AA.getViaje().getDefaultProperties().add(new DefaultProperty(resPropDeAlojamiento, TipoDato.BOOLEAN));
                    }
                } else {
                    //es object
                    AA.getViaje().getDefaultProperties().add(new DefaultProperty(resPropDeAlojamiento, TipoDato.ANY));
                }
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean chequearCumplientoTipoTranslado(DefaultOntology AA, String nombreClase, List<DefaultProperty> defaultsPropAlojamiento, OntModel sinonimo, OntModel ontologia) {
        Set<String> clases = showClass(ontologia).keySet();
        List<String> posiblesNombresDeAlojamiento = getSinAndTraOfPalabra(sinonimo, nombreClase);
        String resultAlojamiento = chequearCumplimientoSinonimos(posiblesNombresDeAlojamiento, new ArrayList<String>(clases));
        if (resultAlojamiento != null) {
            AA.getTranslado().setNombreClase(resultAlojamiento);
        } else {
            return false;
        }
        //Chequeo prop alojamiento
        Set<String> muestraPropAlo = getProperty(ontologia, resultAlojamiento).keySet();
        for (DefaultProperty defProp : defaultsPropAlojamiento) {
            List<String> posPropDeAlojamiento = getSinAndTraOfPalabra(sinonimo, defProp.getName());
            String resPropDeAlojamiento = chequearCumplimientoSinonimos(posPropDeAlojamiento, new ArrayList<String>(muestraPropAlo));
            if (resPropDeAlojamiento != null) {
                String tipo = getProperty(ontologia, resultAlojamiento).get(resPropDeAlojamiento);
                if (tipo.startsWith("d")) {
                    //es datatype
                    tipo = getDatatypeProperty(ontologia, resPropDeAlojamiento).getRange();
                    if (tipo.equalsIgnoreCase("Date")) {
                        AA.getTranslado().getDefaultProperties().add(new DefaultProperty(resPropDeAlojamiento, TipoDato.DATE));
                    } else if (tipo.equalsIgnoreCase("String")) {
                        AA.getTranslado().getDefaultProperties().add(new DefaultProperty(resPropDeAlojamiento, TipoDato.STRING));
                    } else if (tipo.equalsIgnoreCase("int")) {
                        AA.getTranslado().getDefaultProperties().add(new DefaultProperty(resPropDeAlojamiento, TipoDato.INTEGER));
                    } else if (tipo.equalsIgnoreCase("Float")) {
                        AA.getTranslado().getDefaultProperties().add(new DefaultProperty(resPropDeAlojamiento, TipoDato.FLOAT));
                    } else if (tipo.equalsIgnoreCase("Boolean")) {
                        AA.getTranslado().getDefaultProperties().add(new DefaultProperty(resPropDeAlojamiento, TipoDato.BOOLEAN));
                    }
                } else {
                    //es object
                    AA.getTranslado().getDefaultProperties().add(new DefaultProperty(resPropDeAlojamiento, TipoDato.ANY));
                }
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * Chequea que la ontologia cumple con la ontologia default
     * @param sinonimo
     * @param ontologia
     * @param defaultOntology
     * @return devuelve la ontologia default con los nombres correspondientes a la ontologia default
     */
    private DefaultOntology chequearCumplimiento3Clases(OntModel sinonimo, OntModel ontologia, DefaultOntology defaultOntology) {
        DefaultOntology newDefaultOntology = new DefaultOntology();
        //Chequeo alojamiento
        boolean result = chequearCumplientoTipoAlojamiento(newDefaultOntology, defaultOntology.getAlojamiento().getNombreClase(), defaultOntology.getAlojamiento().getDefaultProperties(), sinonimo, ontologia);
        if (!result) {
            return null;
        }
        //Chequeo viaje
        result = chequearCumplientoTipoViaje(newDefaultOntology, defaultOntology.getViaje().getNombreClase(), defaultOntology.getViaje().getDefaultProperties(), sinonimo, ontologia);
        if (!result) {
            return null;
        }
        //Chequeo auto
        result = chequearCumplientoTipoTranslado(newDefaultOntology, defaultOntology.getTranslado().getNombreClase(), defaultOntology.getTranslado().getDefaultProperties(), sinonimo, ontologia);
        if (!result) {
            return null;
        }
        return newDefaultOntology;
    }

    @Deprecated
    public List<String> generarOntologiaBusqueda(OntModel ontologia, OntModel sinonimo, OntModel nueva) {
        List<String> errores = new ArrayList<String>();
        String uriOntologia = getURIOntologiaConNumeral(ontologia);
        String uriNuevaOntologia = getURIOntologiaConNumeral(nueva);
        List<String> individuals = showIndividuals(ontologia);
        for (int i = 0; i < individuals.size(); i++) {
            Individual individual = ontologia.getIndividual(uriOntologia + individuals.get(i));
            OntClass clase = individual.getOntClass();
            Individual indNuevo = nueva.createIndividual(uriNuevaOntologia + individuals.get(i), clase);
            for (StmtIterator sIter = individual.listProperties(); sIter.hasNext();) {
                Statement s = (Statement) sIter.next();
                Triple tri = s.asTriple();
                if (tri.getObject().isLiteral()) {
                    //Datatype
                    String propiedad = tri.getPredicate().getLocalName();
                    String valor = tri.getMatchObject().getLiteral().getValue().toString();
                    String propiedadNueva = convertirPropiedad(sinonimo, propiedad);
                    if (propiedadNueva != null) {
                        OntProperty proNueva = nueva.getOntProperty(uriNuevaOntologia + propiedadNueva);
                        if (proNueva == null) {
                            OntProperty pro = nueva.createOntProperty(uriNuevaOntologia + propiedadNueva);
                            indNuevo.addProperty(pro, valor);
                        } else {
                            indNuevo.addProperty(proNueva, valor);
                        }
                    } else {
                        errores.add(propiedad);
                    }
                } else {
                    //Object
                    String propiedad = tri.getPredicate().getLocalName();
                    String valor = tri.getObject().getLocalName();
                    if (!propiedad.equalsIgnoreCase("type")) {
                        String propiedadNueva = convertirPropiedad(sinonimo, propiedad);
                        if (propiedadNueva != null) {
                            OntProperty proNueva = nueva.getOntProperty(uriNuevaOntologia + propiedadNueva);
                            if (proNueva == null) {
                                OntProperty pro = nueva.createOntProperty(uriNuevaOntologia + propiedadNueva);
                                indNuevo.addProperty(pro, valor);
                            } else {
                                indNuevo.addProperty(proNueva, valor);
                            }
                        } else {
                            errores.add(propiedad);
                        }
                    }
                }
            }
        }
        if (errores.isEmpty()) {
            return null;
        }
        return errores;
    }

    //Testear
    public DatatypePropertyVO getDatatypeProperty(OntModel m, String pro) {
        DatatypePropertyVO vo = new DatatypePropertyVO();
        String uri = getURIOntologia(m);
        uri = uri + "#";
        DatatypeProperty property = m.getDatatypeProperty(uri + pro);
        vo.setName(property.getLocalName());
        if (property.getRange() != null) {
            vo.setRange(property.getRange().getLocalName());
        }
        ArrayList<String> domain = new ArrayList<String>();
        OntResource dom = property.getDomain();
        if (dom != null) {
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
        if (property.getRange() != null) {
            range.add(property.getRange().getLocalName());
        }
        vo.setRange(range);
        ArrayList<String> domain = new ArrayList<String>();
        OntResource dom = property.getDomain();
        if (dom != null) {
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

    /**
     * Obtiene las propiedades de una clase
     * @param ontologia
     * @param nombre de clase
     * @return devuelve hashMap. key=nombre propiedad value=oown,down,oinherited,dinherited
     */
    public HashMap<String, String> getProperty(OntModel m, String c) {
        //Testear la parte de los padres
        HashMap<String, String> propiedades = new HashMap<String, String>();
        String uri = getURIOntologiaConNumeral(m);
        OntClass clase = m.getOntClass(uri + c);
        List<String> padres = new ArrayList<String>();
        while (clase.getSuperClass() != null) {
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
            if (dom != null) {
                if (dom.canAs(UnionClass.class)) {
                    UnionClass uc = (UnionClass) dom.as(UnionClass.class);
                    ExtendedIterator domainIt = uc.listOperands();
                    while (domainIt.hasNext()) {
                        OntResource mc = (OntResource) domainIt.next();
                        domain.add(mc.getLocalName());
                    }
                } else {
                    domain.add(pro.getDomain().getLocalName());
                }
            }
            for (int j = 0; j < domain.size(); j++) {
                if (domain.get(j).equalsIgnoreCase(c)) {
                    propiedades.put(obcPro, "oown");
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
            if (dom != null) {
                if (dom.canAs(UnionClass.class)) {
                    UnionClass uc = (UnionClass) dom.as(UnionClass.class);
                    ExtendedIterator domainIt = uc.listOperands();
                    while (domainIt.hasNext()) {
                        OntResource mc = (OntResource) domainIt.next();
                        domain.add(mc.getLocalName());
                    }
                } else {
                    domain.add(pro.getDomain().getLocalName());
                }
            }
            for (int j = 0; j < domain.size(); j++) {
                if (domain.get(j).equalsIgnoreCase(c)) {
                    propiedades.put(datPro, "down");
                }
            }
        }
        for (int q = 0; q < padres.size(); q++) {
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
                if (dom != null) {
                    if (dom.canAs(UnionClass.class)) {
                        UnionClass uc = (UnionClass) dom.as(UnionClass.class);
                        ExtendedIterator domainIt = uc.listOperands();
                        while (domainIt.hasNext()) {
                            OntResource mc = (OntResource) domainIt.next();
                            domain.add(mc.getLocalName());
                        }
                    } else {
                        domain.add(pro.getDomain().getLocalName());
                    }
                }
                for (int j = 0; j < domain.size(); j++) {
                    if (domain.get(j).equalsIgnoreCase(padres.get(q))) {
                        propiedades.put(obcPro, "oinherited");
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
                if (dom != null) {
                    if (dom.canAs(UnionClass.class)) {
                        UnionClass uc = (UnionClass) dom.as(UnionClass.class);
                        ExtendedIterator domainIt = uc.listOperands();
                        while (domainIt.hasNext()) {
                            OntResource mc = (OntResource) domainIt.next();
                            domain.add(mc.getLocalName());
                        }
                    } else {
                        domain.add(pro.getDomain().getLocalName());
                    }
                }
                for (int j = 0; j < domain.size(); j++) {
                    if (domain.get(j).equalsIgnoreCase(padres.get(q))) {
                        propiedades.put(datPro, "dinherited");
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
        Individual individual = m.getIndividual(uri + ind);
        indViajes.setNombre(individual.getLocalName());
        List<ObjectPropertyVO> objectProperties = new ArrayList<ObjectPropertyVO>();
        List<DatatypePropertyVO> datatypeProperties = new ArrayList<DatatypePropertyVO>();
        //Cargo las propiedades que tiene seteada el individual y cargo las que no tiene seteadas
        for (StmtIterator sIter = individual.listProperties(); sIter.hasNext();) {
            Statement s = (Statement) sIter.next();
            Triple tri = s.asTriple();
            if (tri.getObject().isLiteral()) {
                DatatypePropertyVO datapro = new DatatypePropertyVO();
                if(tri.getObject() != null){
                    if (tri.getObject().getLiteralDatatype() != null) {
                        String[] tipo = tri.getObject().getLiteralDatatype().getURI().split("#");
                        datapro.setRange(tipo[tipo.length - 1]);
                        datapro.setValor(tri.getMatchObject().getLiteral().getValue().toString());
                        String nombre = tri.getPredicate().getLocalName();
                        datapro.setName(nombre);
                        datatypeProperties.add(datapro);
                    }else{
//                        String[] tipo = tri.getObject().getURI().split("#");
//                        datapro.setRange(tipo[tipo.length - 1]);
//                        datapro.setValor(tri.getObject().getLiteralValue().toString());
//                        String nombre = tri.getPredicate().getLocalName();
//                        datapro.setName(nombre);
//                        datatypeProperties.add(datapro);
                    }
                }
            } else {
                String valor = tri.getObject().getLocalName();
                String nombre = tri.getPredicate().getLocalName();
                if (!nombre.equalsIgnoreCase("type")) {
                    ObjectPropertyVO objpro = new ObjectPropertyVO();
                    ObjectProperty oo = m.getObjectProperty(uri + nombre);
                    List<String> lista = new ArrayList<String>();
                    lista.add(oo.getRange().getLocalName());
                    objpro.setRange(lista);
                    objpro.setValor(valor);
                    objpro.setName(nombre);
                    objectProperties.add(objpro);
                }
            }
        }
        HashMap<String, String> propiedades = getProperty(m, individual.getOntClass().getLocalName());
        Object[] prop = propiedades.keySet().toArray();
        for (int i = 0; i < propiedades.size(); i++) {
            OntProperty pro = m.getOntProperty(uri + prop[i]);
            if (pro.isDatatypeProperty()) {
                int aux = 0;
                for (int j = 0; j < datatypeProperties.size(); j++) {
                    DatatypePropertyVO datapro2 = datatypeProperties.get(j);
                    if (datapro2.getName().equalsIgnoreCase(prop[i].toString())) {
                        aux++;
                    }
                }
                if (aux == 0) {
                    DatatypePropertyVO datapro = new DatatypePropertyVO();
                    DatatypeProperty data = m.getDatatypeProperty(uri + prop[i]);
                    datapro.setRange(data.getRange().getLocalName());
                    datapro.setName(prop[i].toString());
                    datatypeProperties.add(datapro);
                }
            } else {
                int aux = 0;
                for (int j = 0; j < objectProperties.size(); j++) {
                    ObjectPropertyVO objpro = objectProperties.get(j);
                    if (objpro.getName().equalsIgnoreCase(prop[i].toString())) {
                        aux++;
                    }
                }
                if (aux == 0) {
                    ObjectPropertyVO objpro = new ObjectPropertyVO();
                    objpro.setName(prop[i].toString());
                    ObjectProperty oo = m.getObjectProperty(uri + prop[i].toString());
                    List<String> lista = new ArrayList<String>();
                    lista.add(oo.getRange().getLocalName());
                    objpro.setRange(lista);
                    objectProperties.add(objpro);
                }
            }
        }
        indViajes.setDatatypeProperties(datatypeProperties);
        indViajes.setObjectProperties(objectProperties);
        return indViajes;
    }

    public void removeClass(OntModel m, String clase) {
        String uri = getURIOntologiaConNumeral(m);
        m.getOntClass(uri + clase).remove();
    }

    public void removeDatatypeProperty(OntModel m, String obj) {
        String uri = getURIOntologia(m);
        uri = uri + "#";
        m.getDatatypeProperty(uri + obj).remove();
    }

    //Testear
    public void removeDomain(OntModel m, String pro, String domain) {
        String uri = getURIOntologiaConNumeral(m);
        OntProperty property = m.getOntProperty(uri + pro);
        OntClass clase = m.getOntClass(uri + domain);
        property.removeDomain(clase);
    }

    public void removeIndividual(OntModel m, String ind) {
        String uri = getURIOntologiaConNumeral(m);
        m.getIndividual(uri + ind).remove();
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
        if (data.contains(pro)) {
            DatatypeProperty datapro = m.getDatatypeProperty(uri + pro);
            OntClass ontclass = m.getOntClass(uri + clase);
            datapro.removeDomain(ontclass);
        } else if (obj.contains(pro)) {
            ObjectProperty objpro = m.getObjectProperty(uri + pro);
            OntClass ontclass = m.getOntClass(uri + clase);
            objpro.removeDomain(ontclass);
        }
    }

    public void removeRange(OntModel m, String pro, String range) {
        String uri = getURIOntologiaConNumeral(m);
        ObjectProperty objpro = m.getObjectProperty(uri + pro);
        OntClass clase = m.getOntClass(uri + range);
        objpro.removeRange(clase);
    }

    public void removerPalabra(OntModel m, String ins) {
        String uri = getURIOntologiaConNumeral(m);
        m.getIndividual(uri + ins).remove();
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
            if (indi.getLocalName().equalsIgnoreCase(ind)) {
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
        String uri = getURIOntologiaConNumeral(m);
        OntClass ontClass = m.createClass(uri + clase);
        if (padre != null) {
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
