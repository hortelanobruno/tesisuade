/*
 * PanelIndividualObjectProperty.java
 *
 * Created on August 25, 2008, 2:05 PM
 */

package com.ontotravel.gui.panels.nuevaontologia;

/**
 *
 * @author  Admin
 */
public class PanelIndividualObjectProperty extends javax.swing.JPanel {
    
    private PanelNuevaOntologia ontologia;
    private String clase;
    /** Creates new form PanelIndividualObjectProperty */
    public PanelIndividualObjectProperty(PanelNuevaOntologia nueva,String clase) {
        initComponents();
        this.ontologia = nueva;
        this.clase = clase;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelNombrePropiedad = new javax.swing.JLabel();
        textFieldValorPropiedad = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        labelNombrePropiedad.setText("Nombre Propiedad");

        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(textFieldValorPropiedad, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(labelNombrePropiedad)
                        .addGap(33, 33, 33)
                        .addComponent(jButton1)))
                .addContainerGap(83, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(labelNombrePropiedad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textFieldValorPropiedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    PanelListaIndividual panel = new PanelListaIndividual(ontologia.getMain(),true,this,clase,labelNombrePropiedad.getText());
    panel.setVisible(true);
}//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel labelNombrePropiedad;
    private javax.swing.JTextField textFieldValorPropiedad;
    // End of variables declaration//GEN-END:variables

    public javax.swing.JButton getJButton1() {
        return jButton1;
    }

    public void setJButton1(javax.swing.JButton jButton1) {
        this.jButton1 = jButton1;
    }

    public javax.swing.JLabel getLabelNombrePropiedad() {
        return labelNombrePropiedad;
    }

    public void setLabelNombrePropiedad(javax.swing.JLabel labelNombrePropiedad) {
        this.labelNombrePropiedad = labelNombrePropiedad;
    }

    public javax.swing.JTextField getTextFieldValorPropiedad() {
        return textFieldValorPropiedad;
    }

    public void setTextFieldValorPropiedad(javax.swing.JTextField textFieldValorPropiedad) {
        this.textFieldValorPropiedad = textFieldValorPropiedad;
    }

    public PanelNuevaOntologia getOntologia() {
        return ontologia;
    }

    public void setOntologia(PanelNuevaOntologia ontologia) {
        this.ontologia = ontologia;
    }

}
