/*
 * PanelIndividualDataProperties.java
 *
 * Created on August 25, 2008, 1:58 PM
 */

package panels.nuevaontologia;

import java.awt.event.KeyEvent;

/**
 *
 * @author  Admin
 */
public class PanelIndividualDatatypeProperty extends javax.swing.JPanel {

    private int varEnter;
    private PanelNuevaOntologia ontologia;
    
    /** Creates new form PanelIndividualDataProperties */
    public PanelIndividualDatatypeProperty(PanelNuevaOntologia nueva) {
        initComponents();
        this.ontologia = nueva;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        comboBoxType = new javax.swing.JComboBox();
        textFieldValorProperty = new javax.swing.JTextField();
        labelNombrePropiedad = new javax.swing.JLabel();

        comboBoxType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "date", "string", "int", "float", "boolean" }));
        comboBoxType.setEnabled(false);

        textFieldValorProperty.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textFieldValorPropertyFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                textFieldValorPropertyFocusLost(evt);
            }
        });
        textFieldValorProperty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textFieldValorPropertyKeyTyped(evt);
            }
        });

        labelNombrePropiedad.setText("Nombre Propiedad");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelNombrePropiedad)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textFieldValorProperty, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(comboBoxType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(labelNombrePropiedad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldValorProperty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

private void textFieldValorPropertyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldValorPropertyKeyTyped
    if(evt.getKeyChar() == KeyEvent.VK_ENTER){
        varEnter = 1;
        ontologia.cargarPropiedadIndividual(labelNombrePropiedad.getText(),textFieldValorProperty.getText());
    }else{
        varEnter = 0;
    }
}//GEN-LAST:event_textFieldValorPropertyKeyTyped

private void textFieldValorPropertyFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textFieldValorPropertyFocusGained
    varEnter = 0;
}//GEN-LAST:event_textFieldValorPropertyFocusGained

private void textFieldValorPropertyFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textFieldValorPropertyFocusLost
    if(varEnter == 0){
        ontologia.cargarPropiedadIndividual(labelNombrePropiedad.getText(),textFieldValorProperty.getText());
    }
}//GEN-LAST:event_textFieldValorPropertyFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox comboBoxType;
    private javax.swing.JLabel labelNombrePropiedad;
    private javax.swing.JTextField textFieldValorProperty;
    // End of variables declaration//GEN-END:variables

    public javax.swing.JComboBox getComboBoxType() {
        return comboBoxType;
    }

    public void setComboBoxType(javax.swing.JComboBox comboBoxType) {
        this.comboBoxType = comboBoxType;
    }

    public javax.swing.JLabel getLabelNombrePropiedad() {
        return labelNombrePropiedad;
    }

    public void setLabelNombrePropiedad(javax.swing.JLabel labelNombrePropiedad) {
        this.labelNombrePropiedad = labelNombrePropiedad;
    }

    public javax.swing.JTextField getTextFieldValorProperty() {
        return textFieldValorProperty;
    }

    public void setTextFieldValorProperty(javax.swing.JTextField textFieldValorProperty) {
        this.textFieldValorProperty = textFieldValorProperty;
    }

}