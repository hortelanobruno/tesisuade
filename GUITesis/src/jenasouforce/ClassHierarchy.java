package jenasouforce;

import com.hp.hpl.jena.graph.Triple;
import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.rdf.model.impl.ResourceImpl;
import com.hp.hpl.jena.shared.PrefixMapping;
import com.hp.hpl.jena.util.iterator.Filter;

import java.io.PrintStream;
import java.util.*;


/**
 * <p>
 * Simple demonstration program to show how to list a hierarchy of classes. This
 * is not a complete solution to the problem (sub-classes of restrictions, for example,
 * are not shown).  It is inteded only to be illustrative of the general approach.
 * </p>
 *
 * @author Ian Dickinson, HP Labs
 *         (<a  href="mailto:Ian.Dickinson@hp.com" >email</a>)
 * @version CVS $Id: ClassHierarchy.java.html,v 1.4 2007/01/17 10:44:18 andy_seaborne Exp $
 */
public class ClassHierarchy {
    // Constants
    //////////////////////////////////

    // Static variables
    //////////////////////////////////

    // Instance variables
    //////////////////////////////////

    protected OntModel m_model;
    private Map m_anonIDs = new HashMap();
    private int m_anonCount = 0;



    // Constructors
    //////////////////////////////////

    // External signature methods
    //////////////////////////////////

    /** Show the sub-class hierarchy encoded by the given model */
    public void showHierarchy( PrintStream out, OntModel m ) {
        // create an iterator over the root classes that are not anonymous class expressions
        Iterator i = m.listHierarchyRootClasses()
                      .filterDrop( new Filter() {
                                    public boolean accept( Object o ) {
                                        return ((Resource) o).isAnon();
                                    }} );

        while (i.hasNext()) {
            showClass( out, (OntClass) i.next(), new ArrayList(), 0 );
        }
    }
    
    public void showHierarchy2( PrintStream out, OntModel m ) {
        // create an iterator over the root classes that are not anonymous class expressions
    	
        Iterator i = m.listIndividuals()
                      .filterDrop( new Filter() {
                                    public boolean accept( Object o ) {
                                        return ((Resource) o).isAnon();
                                    }} );

        while (i.hasNext()) {
            showClass2( out, (Individual) i.next(), new ArrayList(), 0 );
        }
    }


    // Internal implementation methods
    //////////////////////////////////

    /** Present a class, then recurse down to the sub-classes.
     *  Use occurs check to prevent getting stuck in a loop
     */
    protected void showClass( PrintStream out, OntClass cls, List occurs, int depth ) {
        renderClassDescription( out, cls, depth );
        out.println();

        // recurse to the next level down
        if (cls.canAs( OntClass.class )  &&  !occurs.contains( cls )) {
            for (Iterator i = cls.listSubClasses( true );  i.hasNext(); ) {
                OntClass sub = (OntClass) i.next();

                // we push this expression on the occurs list before we recurse
                occurs.add( cls );
                showClass( out, sub, occurs, depth + 1 );
                occurs.remove( cls );
            }
        }
    }

    protected void showClass2( PrintStream out, Individual cls, List occurs, int depth ) {
        renderIndividualDescription( out, cls, depth );
        out.println();

        // recurse to the next level down
/*        if (cls.canAs( OntClass.class )  &&  !occurs.contains( cls )) {
            for (Iterator i = cls.listSubClasses( true );  i.hasNext(); ) {
                OntClass sub = (OntClass) i.next();

                // we push this expression on the occurs list before we recurse
                occurs.add( cls );
                showClass( out, sub, occurs, depth + 1 );
                occurs.remove( cls );
            }
        }*/
    }

    /**
     * <p>Render a description of the given class to the given output stream.</p>
     * @param out A print stream to write to
     * @param c The class to render
     */
    public void renderClassDescription( PrintStream out, OntClass c, int depth ) {
        indent( out, depth );
        //System.out.println("DEPTH :" + depth);
        if (c.isRestriction()) {
            renderRestriction( out, (Restriction) c.as( Restriction.class ) );
        }
        else {
            if (!c.isAnon()) {
                out.print( "Class " );
                renderURI( out, c.getModel(), c.getURI() );
                out.print( "    " );
            }
            else {
                renderAnonymous( out, c, "class" );
            }
        }
    }

    public void renderIndividualDescription( PrintStream out, Individual c, int depth ) {
        indent( out, depth );

        if (!c.isAnon()) {
            out.print( "Individual " );
            renderURI( out, c.getModel(), c.getURI() );
            out.println( ' ' );
        }
        else {
            renderAnonymous( out, c, "class" );
        }
        
        
        for ( StmtIterator sIter = c.listProperties(); sIter.hasNext() ; )
        {
            Statement s = (Statement) sIter.next() ;
            Triple tri = s.asTriple();
            if(tri.getObject().isLiteral()){
            	System.out.println("DATATYPE");
                System.out.println("   Propiedad: "+tri.getPredicate().getLocalName()+"\n   Valor: "+tri.getMatchObject().getLiteral().getValue()) ;
                System.out.println(tri.getObject().getLiteralDatatype().getJavaClass().getName());
            }else{
                System.out.println("OBJECT");
            	System.out.println("   Propiedad: "+tri.getPredicate().getLocalName()+"\n   Valor: "+tri.getObject().getLocalName()) ;
            }
        }
        
        

    }
    
    /**
     * <p>Handle the case of rendering a restriction.</p>
     * @param out The print stream to write to
     * @param r The restriction to render
     */
    protected void renderRestriction( PrintStream out, Restriction r ) {
        if (!r.isAnon()) {
            out.print( "Restriction " );
            renderURI( out, r.getModel(), r.getURI() );
        }
        else {
            renderAnonymous( out, r, "restriction" );
        }

        out.print( " on property " );
        renderURI( out, r.getModel(), r.getOnProperty().getURI() );
    }

    /** Render a URI */
    protected void renderURI( PrintStream out, PrefixMapping prefixes, String uri ) {
        out.print( prefixes.shortForm( uri ) );
    }

    /** Render an anonymous class or restriction */
    protected void renderAnonymous( PrintStream out, Resource anon, String name ) {
        String anonID = (String) m_anonIDs.get( anon.getId() );
        if (anonID == null) {
            anonID = "a-" + m_anonCount++;
            m_anonIDs.put( anon.getId(), anonID );
        }

        out.print( "Anonymous ");
        out.print( name );
        out.print( " with ID " );
        out.print( anonID );
    }

    /** Generate the indentation */
    protected void indent( PrintStream out, int depth ) {
        for (int i = 0;  i < depth; i++) {
            out.print( "  " );
        }
    }


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

