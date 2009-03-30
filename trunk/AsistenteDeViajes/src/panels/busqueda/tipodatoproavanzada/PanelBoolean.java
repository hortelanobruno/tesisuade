/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PanelBoolean.java
 *
 * Created on 15/03/2009, 15:23:05
 */
package panels.busqueda.tipodatoproavanzada;

/**
 *
 * @author Brunoli
 */
public class PanelBoolean extends javax.swing.JPanel implements PanelTipoDato {

    private String nombrePropiedad;

    /** Creates new form PanelBoolean */
    public PanelBoolean(String nombre) {
        initComponents();
        nombrePropiedad = nombre;
        ponerNombre(nombre);
        this.checkBoxValue.setEnabled(false);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelNombre = new javax.swing.JLabel();
        checkBoxValue = new javax.swing.JCheckBox();
        cbActived = new javax.swing.JCheckBox();

        labelNombre.setText("Nombre:");
        labelNombre.setName("labelNombre"); // NOI18N

        checkBoxValue.setName("checkBoxValue"); // NOI18N

        cbActived.setName("cbActived"); // NOI18N
        cbActived.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbActivedActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbActived)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(checkBoxValue)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addComponent(cbActived)
                .addComponent(labelNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(checkBoxValue))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbActivedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbActivedActionPerformed
        checkBoxValue.setEnabled(cbActived.isSelected());
        if (!cbActived.isSelected()) {
            checkBoxValue.setSelected(false);
        }
    }//GEN-LAST:event_cbActivedActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox cbActived;
    private javax.swing.JCheckBox checkBoxValue;
    private javax.swing.JLabel labelNombre;
    // End of variables declaration//GEN-END:variables

    @Override
    public Object getValue() {
        return checkBoxValue.isSelected();
    }

    private void ponerNombre(String nombre) {
        nombre = nombre.replace("_", " ");
        nombre += ":";
        labelNombre.setText(nombre);
    }

    @Override
    public boolean isActived() {
        return cbActived.isSelected();
    }

    @Override
    public boolean checkInput() {
        return true;
    }

    @Override
    public void clearData() {
        cbActived.setEnabled(false);
        checkBoxValue.setEnabled(false);
    }

    @Override
    public String getNombre() {
        return nombrePropiedad;
    }
}
