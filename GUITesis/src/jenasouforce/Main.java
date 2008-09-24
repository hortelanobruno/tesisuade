package jenasouforce;

/*****************************************************************************
 * Source code information
 * -----------------------
 * Original author    Ian Dickinson, HP Labs Bristol
 * Author email       ian.dickinson@hp.com
 * Package            Jena 2
 * Web                http://sourceforge.net/projects/jena/
 * Created            22-Aug-2003
 * Filename           $RCSfile: Main.java.html,v $
 * Revision           $Revision: 1.4 $
 * Release status     $State: Exp $
 *
 * Last modified on   $Date: 2007/01/17 10:44:18 $
 *               by   $Author: andy_seaborne $
 *
 * (c) Copyright 2002, 2003, 2004, 2005, 2006, 2007 Hewlett-Packard Development Company, LP
 * (see footer for full conditions)
 *****************************************************************************/// Imports
///////////////
import com.hp.hpl.jena.graph.Triple;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.util.iterator.Filter;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 * Execution wrapper for class hierarchy example
 * </p>
 *
 * @author Ian Dickinson, HP Labs
 *         (<a  href="mailto:Ian.Dickinson@hp.com" >email</a>)
 * @version CVS $Id: Main.java.html,v 1.4 2007/01/17 10:44:18 andy_seaborne Exp $
 */
public class Main {
    // Constants
    //////////////////////////////////

    // Static variables
    //////////////////////////////////

    // Instance variables
    //////////////////////////////////

    // Constructors
    //////////////////////////////////

    // External signature methods
    //////////////////////////////////
    public Main() {
        // TODO Auto-generated constructor stub


        // we have a local copy of the wine ontology
/*	        m.getDocumentManager().addAltEntry( "http://www.w3.org/2001/sw/WebOnt/guide-src/wine",
        "file:testing/reasoners/bugs/wine.owl" );
        m.getDocumentManager().addAltEntry( "http://www.w3.org/2001/sw/WebOnt/guide-src/food",
        "file:testing/reasoners/bugs/food.owl" );*/

//	        m.read( "http://www.w3.org/2001/sw/WebOnt/guide-src/wine" );
        OntModel m = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM, null);
        m = loadOntModelFromOwlFile("C:\\Documents and Settings\\Administrador\\Escritorio\\Tesis\\Ontologias\\Ontologias\\pruebas\\prueba.owl");
        m.getNsPrefixMap();
        String uri = m.getNsPrefixMap().get("").toString();

        //ObjectProperty pro = m.getObjectProperty(uri+"ciudad");

//                m = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM, null );
//
//                
//                m.createOntology(uri);
//                OntClass window = m.createClass(uri + "NESTOR");
//                OntClass window2 = m.createClass(uri + "NESTOR_HIJO");
//                window2.addSuperClass(window);
//                OntClass window3 = m.createClass(uri + "NESTOR_HIJO_DE HIJO");
//                m.getOntClass(uri + "NESTOR_HIJO").addSubClass(window3);
//                ObjectProperty part = m.createObjectProperty(uri + "part");
//                ObjectProperty body = m.createObjectProperty(uri + "body");
//                DatatypeProperty part2 = m.createDatatypeProperty(uri + "part2");
//                DatatypeProperty body2 = m.createDatatypeProperty(uri + "body2");
//                
//                //part2.setRange(m.rd);
//                
//                //Individual throughTheLens = m.createIndividual(uri + "NestorEnBloque", window);
//                FileOutputStream fileout = null;
//                try {
//                    fileout = new FileOutputStream(new File("c:\\pureba.owl"));
//                } catch (FileNotFoundException ex) {
//                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                
//                m.write(fileout,"RDF/XML-ABBREV");



//      Funca
//        UnionClass ontUn = m.createUnionClass(null,
//                       m.createList(new RDFNode[]{clase1, clase2}));
        
        OntClass clase1 = m.getOntClass(uri + "lopes");
        ObjectProperty pro = m.getObjectProperty(uri + "objpro1");
        List<String> domain = new ArrayList<String>();
        UnionClass uc = (UnionClass) pro.getDomain().as(UnionClass.class);
        ExtendedIterator domainIt = uc.listOperands();
        while (domainIt.hasNext()) {
            OntClass mc = (OntClass) domainIt.next();
            domain.add(mc.getLocalName());
        }
        OntClass clases[] = new OntClass[domain.size()];
        for(int i=0 ; i < domain.size() ; i++){
            clases[i] = m.getOntClass(uri+domain.get(i));
        }
        RDFNode nodes[] = new RDFNode[domain.size()+1];
        for(int i=0 ; i < domain.size() ; i++){
            nodes[i] = clases[i];
        }
        nodes[nodes.length-1] = clase1;
        UnionClass ontUn = m.createUnionClass(null,m.createList(nodes));
        pro.setDomain(ontUn);

        
        
        //Grabaaaaaaaaaaaaaaaaa
        FileOutputStream fileout = null;
        try {
            fileout = new FileOutputStream(new File("C:\\Documents and Settings\\Administrador\\Escritorio\\Tesis\\Ontologias\\Ontologias\\pruebas\\prueba.owl"));
        } catch (FileNotFoundException ex) {
        }
        m.write(fileout, "RDF/XML-ABBREV");

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

    public List<String> getProperty(OntModel m, String c) {
        ArrayList<String> propiedades = new ArrayList<String>();
        String uri = m.getNsPrefixMap().get("").toString();

        OntClass clase = m.getOntClass(uri + c);
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
                        OntClass mc = (OntClass) domainIt.next();
                        domain.add(mc.getLocalName());
                    }
                } else {
                    domain.add(pro.getDomain().getLocalName());
                }
            }
            for (int j = 0; j < domain.size(); j++) {
                if (domain.get(j).equalsIgnoreCase(c)) {
                    propiedades.add(obcPro);
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
                        OntClass mc = (OntClass) domainIt.next();
                        domain.add(mc.getLocalName());
                    }
                } else {
                    domain.add(pro.getDomain().getLocalName());
                }
            }

            for (int j = 0; j < domain.size(); j++) {
                if (domain.get(j).equalsIgnoreCase(c)) {
                    propiedades.add(datPro);
                }
            }
        }
        return propiedades;
    }

    public void imprimirObjectProperties(OntModel m) {
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
        for (int j = 0; j < propiedades.size(); j++) {
            System.out.println(propiedades.get(j));
        }
    }

    public static void main(String[] args) {
        new Main();
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
}


