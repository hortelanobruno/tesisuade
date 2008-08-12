/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jenasouforce;

import com.hp.hpl.jena.graph.Triple;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.Restriction;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.shared.PrefixMapping;
import com.hp.hpl.jena.util.iterator.Filter;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

    public ArrayList<String> showIndividuals(OntModel m){
        ArrayList<String> individuals = new ArrayList<String>();
        Iterator i = m.listIndividuals()
                      .filterDrop( new Filter() {
                                    public boolean accept( Object o ) {
                                        return ((Resource) o).isAnon();
                                    }} );

        while (i.hasNext()) {
           individuals.add(((Individual) i.next()).getURI());
        }
 
        return individuals;
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
    
    protected void showClass2( PrintStream out, Individual cls, List occurs, int depth ) {
    renderIndividualDescription( out, cls, depth );
    out.println();


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
                System.out.println("   Propiedad: "+tri.getPredicate().getLocalName()+"\n   Valor: "+tri.getMatchObject().getLiteral().getValue()) ;
            }else{
                System.out.println("   Propiedad: "+tri.getPredicate().getLocalName()+"\n   Valor: "+tri.getObject().getLocalName()) ;
            }
        }
    }
    
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
}
