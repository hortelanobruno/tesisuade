/*
 * PanelResultadoVuelo.java
 *
 * Created on 27 de agosto de 2008, 21:21
 */

package panels.busqueda.resultado;

/**
 *
 * @author  Administrador
 */
public class PanelResultadoVuelo extends javax.swing.JPanel {

    /** Creates new form PanelResultadoVuelo */
    public PanelResultadoVuelo() {
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

        jLabel2 = new javax.swing.JLabel();
        panelFechaVuelta = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        labelFechaVuelta = new javax.swing.JLabel();
        labelFechaIda = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        labelCiudadDestino = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        labelCiudadOrigen = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        labelCantAdultos = new javax.swing.JLabel();
        labelCantNinios = new javax.swing.JLabel();
        labelCantBebes = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setText("Fecha Ida");

        jLabel1.setText("Fecha Vuelta");

        javax.swing.GroupLayout panelFechaVueltaLayout = new javax.swing.GroupLayout(panelFechaVuelta);
        panelFechaVuelta.setLayout(panelFechaVueltaLayout);
        panelFechaVueltaLayout.setHorizontalGroup(
            panelFechaVueltaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFechaVueltaLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(labelFechaVuelta, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
        );
        panelFechaVueltaLayout.setVerticalGroup(
            panelFechaVueltaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFechaVueltaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1)
                .addComponent(labelFechaVuelta, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel3.setText("Ciudad Destino");

        jLabel4.setText("Ciudad Origen");

        jLabel6.setText("Cantidad Adultos");

        jLabel7.setText("Cantidad Ninios");

        jLabel8.setText("Cantidad Bebes");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addContainerGap(340, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(43, 43, 43)
                                .addComponent(labelCantBebes, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(33, 33, 33)
                                .addComponent(labelFechaIda, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelCantAdultos, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                                    .addComponent(labelCantNinios, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)))
                            .addComponent(panelFechaVuelta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelCiudadOrigen, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                            .addComponent(labelCiudadDestino, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE))
                        .addGap(57, 57, 57))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(labelCiudadOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelFechaIda, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelCiudadDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(panelFechaVuelta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(labelCantAdultos, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(labelCantNinios, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(labelCantBebes, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel labelCantAdultos;
    private javax.swing.JLabel labelCantBebes;
    private javax.swing.JLabel labelCantNinios;
    private javax.swing.JLabel labelCiudadDestino;
    private javax.swing.JLabel labelCiudadOrigen;
    private javax.swing.JLabel labelFechaIda;
    private javax.swing.JLabel labelFechaVuelta;
    private javax.swing.JPanel panelFechaVuelta;
    // End of variables declaration//GEN-END:variables

    public javax.swing.JLabel getLabelCantAdultos() {
        return labelCantAdultos;
    }

    public void setLabelCantAdultos(javax.swing.JLabel labelCantAdultos) {
        this.labelCantAdultos = labelCantAdultos;
    }

    public javax.swing.JLabel getLabelCantBebes() {
        return labelCantBebes;
    }

    public void setLabelCantBebes(javax.swing.JLabel labelCantBebes) {
        this.labelCantBebes = labelCantBebes;
    }

    public javax.swing.JLabel getLabelCantNinios() {
        return labelCantNinios;
    }

    public void setLabelCantNinios(javax.swing.JLabel labelCantNinios) {
        this.labelCantNinios = labelCantNinios;
    }

    public javax.swing.JLabel getLabelCiudadDestino() {
        return labelCiudadDestino;
    }

    public void setLabelCiudadDestino(javax.swing.JLabel labelCiudadDestino) {
        this.labelCiudadDestino = labelCiudadDestino;
    }

    public javax.swing.JLabel getLabelCiudadOrigen() {
        return labelCiudadOrigen;
    }

    public void setLabelCiudadOrigen(javax.swing.JLabel labelCiudadOrigen) {
        this.labelCiudadOrigen = labelCiudadOrigen;
    }

    public javax.swing.JLabel getLabelFechaIda() {
        return labelFechaIda;
    }

    public void setLabelFechaIda(javax.swing.JLabel labelFechaIda) {
        this.labelFechaIda = labelFechaIda;
    }

    public javax.swing.JLabel getLabelFechaVuelta() {
        return labelFechaVuelta;
    }

    public void setLabelFechaVuelta(javax.swing.JLabel labelFechaVuelta) {
        this.labelFechaVuelta = labelFechaVuelta;
    }

    public javax.swing.JPanel getPanelFechaVuelta() {
        return panelFechaVuelta;
    }

    public void setPanelFechaVuelta(javax.swing.JPanel panelFechaVuelta) {
        this.panelFechaVuelta = panelFechaVuelta;
    }

}
