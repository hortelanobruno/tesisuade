/*
 * PanelListProperties.java
 *
 * Created on 15 de septiembre de 2008, 20:31
 */

package panels.nuevaontologia;

import java.awt.Toolkit;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import modelo.BusinessDelegate;
import varios.components.JListCellRenderer;
import varios.components.JListItem;

/**
 *
 * @author  Administrador
 */
public class PanelListProperties extends javax.swing.JDialog {

    private PanelNuevaOntologia nuevaOntologia;
    private List<String> propiedadesCargadas;
    private List<String> datatypeProperties;
    private List<String> objectProperties;
    private String clase;
    /** Creates new form PanelListProperties */
    public PanelListProperties(java.awt.Frame parent, boolean modal, String clase, PanelNuevaOntologia nueva, List<String> propiedadesCargadas) {
        super(parent, modal);
        this.nuevaOntologia = nueva;
        this.propiedadesCargadas = propiedadesCargadas;
        this.clase = clase;
        initComponents();
        Toolkit t = Toolkit.getDefaultToolkit();
            this.setLocation((int) (t.getScreenSize().getWidth() - this.getWidth()) / 2, (int) (t.getScreenSize().getHeight() - this.getHeight()) / 2);
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

        buttonAddPropertie = new javax.swing.JButton();
        buttonRemovePropertie = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listProperties = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        buttonAddPropertie.setText("Add");
        buttonAddPropertie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddPropertieActionPerformed(evt);
            }
        });

        buttonRemovePropertie.setText("Can");
        buttonRemovePropertie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemovePropertieActionPerformed(evt);
            }
        });

        listProperties.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(listProperties);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonAddPropertie)
                    .addComponent(buttonRemovePropertie))
                .addContainerGap(120, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(buttonAddPropertie)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonRemovePropertie))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    public void initComponents2(){
        datatypeProperties = ((BusinessDelegate) nuevaOntologia.getVistaNuevaOntologia().getModelo()).showDatatypeProperties();
        objectProperties = ((BusinessDelegate) nuevaOntologia.getVistaNuevaOntologia().getModelo()).showObjectProperties();
        DefaultListModel model = new DefaultListModel();
        for(int i=0 ; i < datatypeProperties.size() ; i++){
            String propiedad = datatypeProperties.get(i);
            if(!propiedadesCargadas.contains(propiedad)){
                model.addElement(new JListItem(propiedad,"src/iconos/protege/OWLDatatypeProperty.GIF"));
            }
        }
        for(int i=0 ; i < objectProperties.size() ; i++){
            String propiedad = objectProperties.get(i);
            if(!propiedadesCargadas.contains(propiedad)){
                model.addElement(new JListItem(propiedad,"src/iconos/protege/OWLObjectProperty.GIF"));
            }
        }
        listProperties = new JList(model);
        listProperties.setCellRenderer(new JListCellRenderer());
        jScrollPane1.setViewportView(listProperties);
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
    
private void buttonAddPropertieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddPropertieActionPerformed
// Agregar propiedad seleccionada
    JListItem item = (JListItem) listProperties.getSelectedValue();
    String instancia = item.getTitle();
    if(instancia != null){
        if(datatypeProperties.contains(instancia)){
            nuevaOntologia.addClassProperty(this.clase,instancia,"datatype");
        }else{
            nuevaOntologia.addClassProperty(this.clase,instancia,"object");
        }
    }
    this.setVisible(false);
}//GEN-LAST:event_buttonAddPropertieActionPerformed

private void buttonRemovePropertieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemovePropertieActionPerformed
// TODO add your handling code here:
    this.setVisible(false);
}//GEN-LAST:event_buttonRemovePropertieActionPerformed




    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAddPropertie;
    private javax.swing.JButton buttonRemovePropertie;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList listProperties;
    // End of variables declaration//GEN-END:variables

}
