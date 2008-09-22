/*
 * PanelDomain.java
 *
 * Created on 7 de septiembre de 2008, 13:16
 */

package panels.nuevaontologia;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import modelo.BusinessDelegate;

/**
 *
 * @author  Administrador
 */
public class PanelDomain extends javax.swing.JDialog {

    private PanelNuevaOntologia panel;
    private HashMap<String, DefaultMutableTreeNode> mapaNodos;
    private PanelPropertyDatatype proDatatype;
    private PanelPropertyObject proObject;
    private boolean forDomain;
    
    /** Creates new form PanelDomain */
    public PanelDomain(boolean forDomain,java.awt.Frame parent, boolean modal, PanelNuevaOntologia panel, PanelPropertyDatatype data) {
        super(parent, modal);
        initComponents();
        this.forDomain = forDomain;
        this.panel = panel;
        proDatatype = data;
        proObject = null;
        initComponents2();
    }
    
    public PanelDomain(boolean forDomain,java.awt.Frame parent, boolean modal, PanelNuevaOntologia panel, PanelPropertyObject data) {
        super(parent, modal);
        initComponents();
        this.forDomain = forDomain;
        this.panel = panel;
        proDatatype = null;
        proObject = data;
        initComponents2();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        treeClasses = new javax.swing.JTree();
        buttonAgregarDomain = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jScrollPane1.setViewportView(treeClasses);

        buttonAgregarDomain.setText("Add");
        buttonAgregarDomain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAgregarDomainActionPerformed(evt);
            }
        });

        buttonCancel.setText("Cancel");
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonCancel)
                    .addComponent(buttonAgregarDomain))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(buttonAgregarDomain)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonCancel)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void buttonAgregarDomainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAgregarDomainActionPerformed
    if(proDatatype == null){
        //cargo en object
        if(this.forDomain){
            String name = treeClasses.getSelectionPath().getLastPathComponent().toString();
            proObject.addDomain(name);
            this.setVisible(false);
        }else{
            String name = treeClasses.getSelectionPath().getLastPathComponent().toString();
            proObject.addRange(name);
            this.setVisible(false);
        }
    }else{
        //cargo en datatype
        String name = treeClasses.getSelectionPath().getLastPathComponent().toString();
        proDatatype.addDomain(name);
        this.setVisible(false);
    }
}//GEN-LAST:event_buttonAgregarDomainActionPerformed

private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
// TODO add your handling code here:
    this.setVisible(false);
}//GEN-LAST:event_buttonCancelActionPerformed

    
    public void initComponents2(){
        DefaultMutableTreeNode abuelo = new DefaultMutableTreeNode("Classes");
        DefaultTreeModel modelo = new DefaultTreeModel(abuelo);
        treeClasses = new JTree(modelo);
        

        HashMap<String,String> mapaClases = ((BusinessDelegate) panel.getVistaNuevaOntologia().getModelo()).showClasses();
        mapaNodos = new HashMap<String, DefaultMutableTreeNode>();
        Set<Entry<String,String>> setClases = mapaClases.entrySet();
        Iterator itClases = setClases.iterator();
        DefaultMutableTreeNode node = null;
        while(itClases.hasNext()){
            Entry<String,String> clase = (Entry<String, String>) itClases.next();
            node = new DefaultMutableTreeNode(clase.getKey());
            mapaNodos.put(clase.getKey(), node);
        }
        itClases = setClases.iterator();
        while(itClases.hasNext()){
            Entry<String,String> clase = (Entry<String, String>) itClases.next();
            if(clase.getValue().isEmpty()){
                abuelo.add(mapaNodos.get(clase.getKey()));
            }else{
                mapaNodos.get(clase.getValue()).add(mapaNodos.get(clase.getKey()));
            }
        }
        jScrollPane1.setViewportView(treeClasses);
        Object root = treeClasses.getModel().getRoot();
        TreePath path = new TreePath(root);
        treeClasses.expandPath(path);
        ImageIcon leafIcon = createImageIcon("/iconos/protege/TreeBold.gif");
        if (leafIcon != null) {
            DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
            renderer.setLeafIcon(leafIcon);
            renderer.setOpenIcon(leafIcon);
            renderer.setClosedIcon(leafIcon);
            treeClasses.setCellRenderer(renderer);
        }
    }
    
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = PanelNuevaOntologia.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAgregarDomain;
    private javax.swing.JButton buttonCancel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree treeClasses;
    // End of variables declaration//GEN-END:variables

}
