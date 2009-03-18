/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PanelDate.java
 *
 * Created on 15/03/2009, 15:14:18
 */
package panels.busqueda.tipodatoproavanzada;

/**
 *
 * @author Brunoli
 */
public class PanelDate extends javax.swing.JPanel implements PanelTipoDato {

    private String nombrePropiedad;

    /** Creates new form PanelDate */
    public PanelDate(String nombre) {
        initComponents();
        this.nombrePropiedad = nombre;
        ponerNombre(nombre);
        this.dateChooser.setEnabled(false);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser = new com.toedter.calendar.JDateChooser();
        labelNombre = new javax.swing.JLabel();
        cbActived = new javax.swing.JCheckBox();

        dateChooser.setName("dateChooser"); // NOI18N

        labelNombre.setText("Nombre:");
        labelNombre.setName("labelNombre"); // NOI18N

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
                .addGap(18, 18, 18)
                .addComponent(labelNombre)
                .addGap(52, 52, 52)
                .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addComponent(cbActived)
                .addComponent(labelNombre)
                .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbActivedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbActivedActionPerformed
        dateChooser.setEnabled(cbActived.isSelected());
        if (!cbActived.isSelected()) {
            dateChooser.cleanup();
        }
}//GEN-LAST:event_cbActivedActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox cbActived;
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JLabel labelNombre;
    // End of variables declaration//GEN-END:variables

    @Override
    public Object getValue() {
        return dateChooser.getCalendar().toString();
    }

    private void ponerNombre(String nombre) {
        nombre = nombre.replace("_", " ");
        nombre += ":";
        labelNombre.setText(nombre);
    }

    @Override
    public boolean isActived() {
        return cbActived.isEnabled();
    }

    @Override
    public boolean checkInput() {
        if (dateChooser.getCalendar() == null) {
            return false;
        }
        return true;
    }

    @Override
    public void clearData() {
        dateChooser.cleanup();
        cbActived.setEnabled(false);
    }

    @Override
    public String getNombre() {
        return nombrePropiedad;
    }
}
