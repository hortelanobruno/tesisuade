/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PanelInteger.java
 *
 * Created on 15/03/2009, 21:22:13
 */

package panels.busqueda.proprincipal;

/**
 *
 * @author Brunoli
 */
public class PropiedadPrincipalInteger extends javax.swing.JPanel implements PropiedadPrincipal {

    private String nombrePropiedad;
    /** Creates new form PanelInteger */
    public PropiedadPrincipalInteger(String nombre) {
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
        spinnerValor = new javax.swing.JSpinner();

        labelNombre.setText("Nombre:");
        labelNombre.setName("labelNombre"); // NOI18N

        spinnerValor.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), null, Integer.valueOf(200), Integer.valueOf(1)));
        spinnerValor.setName("spinnerValor"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelNombre)
                .addGap(95, 95, 95)
                .addComponent(spinnerValor, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addComponent(labelNombre)
                .addComponent(spinnerValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelNombre;
    private javax.swing.JSpinner spinnerValor;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getNombrePropiedad() {
        return nombrePropiedad;
    }

    @Override
    public Object getValor() {
        return spinnerValor.getValue();
    }

    @Override
    public void clearData() {
        spinnerValor.setValue(0);
    }

    @Override
    public boolean checkInput() {
        Object value = spinnerValor.getValue();
        if(value instanceof Integer){
            return true;
        }else{
            return false;
        }
    }

    private void ponerNombre(String nombre) {
        nombre = nombre.replace("_", " ");
        nombre += ":";
        labelNombre.setText(nombre);
    }

}
