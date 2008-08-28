/*
 * PanelConfiguracion.java
 *
 * Created on August 26, 2008, 1:00 PM
 */

package panels.configuracion;

import gui.FileChooser;
import gui.FramePrincipal;
import java.util.Vector;
import javax.swing.DefaultListModel;

/**
 *
 * @author  Admin
 */
public class PanelConfiguracion extends javax.swing.JPanel {

    private FramePrincipal main;
    private FileChooser chooser;
    
    /** Creates new form PanelConfiguracion */
    public PanelConfiguracion(FramePrincipal ref) {
        this.main = ref;
        initComponents();
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listOntologiasViajes = new javax.swing.JList();
        buttonRemoverOntViajes = new javax.swing.JButton();
        buttonAddOntViajes = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        textFieldOntVocabulario = new javax.swing.JTextField();
        buttonChangeOntVocabulario = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        buttonChageDirectorioOntologia = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1023, 532));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Ontologias Viajes Cargadas");

        listOntologiasViajes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(listOntologiasViajes);

        buttonRemoverOntViajes.setText("Rem");
        buttonRemoverOntViajes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoverOntViajesActionPerformed(evt);
            }
        });

        buttonAddOntViajes.setText("Add");
        buttonAddOntViajes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddOntViajesActionPerformed(evt);
            }
        });

        jLabel2.setText("Ontologia Vocabulario");

        buttonChangeOntVocabulario.setText("Change");
        buttonChangeOntVocabulario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonChangeOntVocabularioActionPerformed(evt);
            }
        });

        jLabel3.setText("Directorio Ontologias");

        buttonChageDirectorioOntologia.setText("Change");
        buttonChageDirectorioOntologia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonChageDirectorioOntologiaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(buttonAddOntViajes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonRemoverOntViajes))
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addComponent(textFieldOntVocabulario, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
                    .addComponent(buttonChangeOntVocabulario)
                    .addComponent(jLabel3)
                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
                    .addComponent(buttonChageDirectorioOntologia))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAddOntViajes)
                    .addComponent(buttonRemoverOntViajes))
                .addGap(32, 32, 32)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textFieldOntVocabulario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonChangeOntVocabulario)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonChageDirectorioOntologia)
                .addContainerGap(91, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(424, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    
    public void initComponents2(){
        listOntologiasViajes.setModel(new DefaultListModel());
    }
    
private void buttonAddOntViajesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddOntViajesActionPerformed
    chooser = new FileChooser(this.main, true, this.main.getConfiguration().getOwlDirectory());
    if (chooser.getButton().equals("Cancel")) {
    } else {
        ((DefaultListModel)listOntologiasViajes.getModel()).addElement(chooser.path);
        if(main.getConfiguration().getOntologiasViajes() == null){
            main.getConfiguration().setOntologiasViajes(new Vector<String>());
            main.getConfiguration().getOntologiasViajes().add(chooser.path);
        }
        main.recargarConfiguracion();
    }
}//GEN-LAST:event_buttonAddOntViajesActionPerformed

private void buttonRemoverOntViajesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoverOntViajesActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_buttonRemoverOntViajesActionPerformed

private void buttonChangeOntVocabularioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonChangeOntVocabularioActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_buttonChangeOntVocabularioActionPerformed

private void buttonChageDirectorioOntologiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonChageDirectorioOntologiaActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_buttonChageDirectorioOntologiaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAddOntViajes;
    private javax.swing.JButton buttonChageDirectorioOntologia;
    private javax.swing.JButton buttonChangeOntVocabulario;
    private javax.swing.JButton buttonRemoverOntViajes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JList listOntologiasViajes;
    private javax.swing.JTextField textFieldOntVocabulario;
    // End of variables declaration//GEN-END:variables

}
