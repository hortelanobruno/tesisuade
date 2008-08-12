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
import vo.IndividualVO;

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

    public IndividualVO showIndividual(OntModel m, String ind) {
        IndividualVO individual = null;
        Iterator i = m.listIndividuals()
                      .filterDrop( new Filter() {
                                    public boolean accept( Object o ) {
                                        return ((Resource) o).isAnon();
                                    }} );

        while (i.hasNext()) {
           Individual indi = ((Individual) i.next());
           if(indi.getLocalName().equals(ind)){
               individual = new IndividualVO();
               ArrayList<String> sinonimo = new ArrayList<String>();
               ArrayList<String> traduccion = new ArrayList<String>();
                for ( StmtIterator sIter = indi.listProperties(); sIter.hasNext() ; )
                {
                    Statement s = (Statement) sIter.next() ;
                    Triple tri = s.asTriple();
                    if(tri.getObject().isLiteral()){
                        if(tri.getPredicate().getLocalName().equals("sinonimo")){
                            sinonimo.add((String) tri.getMatchObject().getLiteral().getValue());
                        }else{
                            traduccion.add((String) tri.getMatchObject().getLiteral().getValue());
                        }
                    }else{
                        if(tri.getPredicate().getLocalName().equals("sinonimo")){
                            sinonimo.add((String) tri.getObject().getLocalName());
                        }else{
                            traduccion.add((String) tri.getObject().getLocalName());
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
    
    
}
