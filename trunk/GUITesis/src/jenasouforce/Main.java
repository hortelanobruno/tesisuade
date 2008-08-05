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
	 *****************************************************************************/



	// Imports
	///////////////
	import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Writer;
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
	        OntModel m = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM, null );

	        // we have a local copy of the wine ontology
/*	        m.getDocumentManager().addAltEntry( "http://www.w3.org/2001/sw/WebOnt/guide-src/wine",
	                                            "file:testing/reasoners/bugs/wine.owl" );
	        m.getDocumentManager().addAltEntry( "http://www.w3.org/2001/sw/WebOnt/guide-src/food",
	                                            "file:testing/reasoners/bugs/food.owl" );*/

//	        m.read( "http://www.w3.org/2001/sw/WebOnt/guide-src/wine" );
	        m = loadOntModelFromOwlFile("C:\\Documents and Settings\\Administrador\\Escritorio\\Tesis\\Ontologias\\Ontologias\\pruebas\\prueba.owl");
	        String uri = m.getNsPrefixMap().values().iterator().next().toString();
                
                OntClass Window = m.createClass(uri + "NESTOR");
                Individual throughTheLens = m.createIndividual(uri + "NestorEnBloque", Window);
                FileOutputStream fileout = null;
                try {
                    fileout = new FileOutputStream(new File("c:\\pureba.owl"));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                m.write(fileout,"RDF/XML-ABBREV");
                ClassHierarchy classh = new ClassHierarchy();

	        classh.showHierarchy2( System.out, m );
	        
	        classh.showHierarchy( System.out, m );
		}
		
		
		
	    public static void main( String[] args ) {
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
				System.out.println("Se ha cargado una instancia OntModel.");
			} catch (Exception e) {
				System.out.println("Se ha producido una excepcion controlada.");
				e.printStackTrace();
			}
			return ontmodel;
		}
		

	}


