/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PanelString.java
 *
 * Created on 15/03/2009, 21:33:52
 */

package com.ontotravel.gui.panels.busqueda.proprincipal;

/**
 *
 * @author Brunoli
 */
public class PropiedadPrincipalString extends javax.swing.JPanel implements PropiedadPrincipal {

    private String nombrePropiedad;
    /** Creates new form PanelString */
    public PropiedadPrincipalString(String nombre) {
        initComponents();
        this.nombrePropiedad = nombre;
        ponerNombre(nombre);
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
        tfValor = new javax.swing.JTextField();

        setMaximumSize(new java.awt.Dimension(274, 20));
        setMinimumSize(new java.awt.Dimension(274, 20));

        labelNombre.setText("Nombre:");
        labelNombre.setName("labelNombre"); // NOI18N

        tfValor.setName("tfValor"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfValor, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addComponent(tfValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(labelNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelNombre;
    private javax.swing.JTextField tfValor;
    // End of variables declaration//GEN-END:variables

    private void ponerNombre(String nombre) {
        nombre = nombre.replace("_", " ");
        nombre += ":";
        labelNombre.setText(nombre);
    }

    @Override
    public String getNombrePropiedad() {
        return nombrePropiedad;
    }

    @Override
    public Object getValor() {
        return tfValor.getText();
    }

    @Override
    public void clearData() {
        tfValor.setText("");
    }

    @Override
    public boolean checkInput() {
        return true;
    }

}
