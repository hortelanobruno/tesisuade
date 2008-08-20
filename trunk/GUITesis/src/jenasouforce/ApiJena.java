/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jenasouforce;

import com.hp.hpl.jena.graph.Triple;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.iterator.Filter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.JTree;
import javax.swing.text.Position;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import vo.IndividualSinonimoVO;

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

    public String getURIOntologia(OntModel m){
        String uri = m.getNsPrefixMap().values().iterator().next().toString();
        uri = uri.substring(0, uri.length() -1);
        return uri;
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

    public void showClass(OntModel m, JTree tree, DefaultTreeModel model,DefaultMutableTreeNode rootNode){
        Iterator i = m.listClasses().filterDrop(new Filter() { public boolean accept( Object o ) {
                                        return ((Resource) o).isAnon();
                                    }} );
        while (i.hasNext()) {
           OntClass cls = ((OntClass) i.next());
           showClass(tree,model,rootNode,cls,null,new ArrayList(), 0 );
        }
    }
    
   private void showClass(JTree tree, DefaultTreeModel model,DefaultMutableTreeNode rootNode, OntClass cls, OntClass sub, List occurs, int depth ) {
        renderClassDescription(tree,model,rootNode, cls,sub, depth );

        // recurse to the next level down
        if (cls.canAs( OntClass.class )  &&  !occurs.contains( cls )) {
            for (Iterator i = cls.listSubClasses( true );  i.hasNext(); ) {
                OntClass subs = (OntClass) i.next();

                // we push this expression on the occurs list before we recurse
                occurs.add( cls );
                showClass(tree,model,rootNode, cls, subs, occurs, depth + 1 );
                occurs.remove( cls );
            }
        }
    }
   
   

   
   private void renderClassDescription(JTree tree, DefaultTreeModel model, DefaultMutableTreeNode rootNode, OntClass cls, OntClass sub, int depth ) {
        
        if(sub != null){
            DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(sub.getLocalName().toString());
            DefaultMutableTreeNode parent = null;
            
            for(int i = 0 ; i < tree.getRowCount() ; i++){
                DefaultMutableTreeNode parent2 = (DefaultMutableTreeNode) tree.getPathForRow(i).getLastPathComponent();
                
                if(parent2.getLastChild().toString().equals(cls.getLocalName().toString())){
                    parent = parent2;
                }    
            }
            if (cls == null) {
                parent = rootNode;
            }
//            }else{
//                parent = (DefaultMutableTreeNode) (parentPath.getLastPathComponent());
//            }
            //It is key to invoke this on the TreeModel, and NOT DefaultMutableTreeNode
            System.out.println("1");
            model.insertNodeInto(childNode, parent, 
                                     parent.getChildCount());
        }else{
            DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(cls.getLocalName().toString());
            DefaultMutableTreeNode parent = null;
            parent = rootNode;
            System.out.println("1");
            model.insertNodeInto(childNode, parent, 
                                     parent.getChildCount());
        }
       
//        classes.put(cls.getLocalName(),depth);
    }
}
