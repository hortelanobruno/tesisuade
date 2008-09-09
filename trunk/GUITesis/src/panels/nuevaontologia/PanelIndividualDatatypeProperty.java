/*
 * PanelIndividualDataProperties.java
 *
 * Created on August 25, 2008, 1:58 PM
 */

package panels.nuevaontologia;

/**
 *
 * @author  Admin
 */
public class PanelIndividualDatatypeProperty extends javax.swing.JPanel {

    /** Creates new form PanelIndividualDataProperties */
    public PanelIndividualDatatypeProperty() {
        initComponents();
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

        comboBoxType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "date", "string", "int", "double", "float", "boolean" }));

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
