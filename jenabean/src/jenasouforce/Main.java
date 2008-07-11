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
import java.io.InputStream;

import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.rdf.model.ModelFactory;


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
	        m = loadOntModelFromOwlFile("C:\\Documents and Settings\\Administrador\\Escritorio\\Tesis\\Ontologias\\turismo2.owl");
	        new ClassHierarchy().showHierarchy2( System.out, m );
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
		
	    // Internal implementation methods
	    //////////////////////////////////

	    //==============================================================================
	    // Inner class definitions
	    //==============================================================================

	}


	/*
	    (c) Copyright 2002, 2003, 2004, 2005, 2006, 2007 Hewlett-Packard Development Company, LP
	    All rights reserved.

	    Redistribution and use in source and binary forms, with or without
	    modification, are permitted provided that the following conditions
	    are met:

	    1. Redistributions of source code must retain the above copyright
	       notice, this list of conditions and the following disclaimer.

	    2. Redistributions in binary form must reproduce the above copyright
	       notice, this list of conditions and the following disclaimer in the
	       documentation and/or other materials provided with the distribution.

	    3. The name of the author may not be used to endorse or promote products
	       derived from this software without specific prior written permission.

	    THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
	    IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
	    OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
	    IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
	    INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
	    NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
	    DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
	    THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
	    (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
	    THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
	*/

