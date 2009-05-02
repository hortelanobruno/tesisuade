/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PanelDefaultOntologia.java
 *
 * Created on 19/02/2009, 07:20:20
 */
package com.ontotravel.gui.panels.configuracion;

import com.ontotravel.config.defaultontology.DefaultOntology;
import com.ontotravel.config.defaultontology.types.DefaultProperty;
import com.ontotravel.gui.FramePrincipal;
import com.ontotravel.mvc.view.VistaDefaultOntologia;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Brunoli
 */
public class PanelDefaultOntologia extends javax.swing.JPanel {

    private FramePrincipal main;
    private VistaDefaultOntologia vista;
    private DefaultOntology defaultOntology;

    /** Creates new form PanelDefaultOntologia */
    public PanelDefaultOntologia(FramePrincipal aThis, VistaDefaultOntologia vistaDefaultOntologia) {
        initComponents();
        this.main = aThis;
        this.vista = vistaDefaultOntologia;
        cargarOntologiaDefault();
    }

    private void cargarNuevaOntologiaDefault() {
        defaultOntology = new DefaultOntology();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        lbNombreClaseVuelo = new javax.swing.JLabel();
        tfNombreClaseVuelo = new javax.swing.JTextField();
        lbPropieadesVuelo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePropieadesVuelo = new javax.swing.JTable();
        buttonAddPropiedadVuelo = new javax.swing.JButton();
        buttonRemovePropiedadVuelo = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablePropieadesHotel = new javax.swing.JTable();
        lbPropieadesHotel = new javax.swing.JLabel();
        buttonAddPropiedadHotel = new javax.swing.JButton();
        buttonRemovePropiedadHotel = new javax.swing.JButton();
        lbNombreClaseHotel = new javax.swing.JLabel();
        tfNombreClaseHotel = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablePropieadesTranslado = new javax.swing.JTable();
        lbPropieadesTranslado = new javax.swing.JLabel();
        buttonAddPropiedadTranslado = new javax.swing.JButton();
        buttonRemovePropiedadTranslado = new javax.swing.JButton();
        lbNombreClaseTranslado = new javax.swing.JLabel();
        tfNombreClaseTranslado = new javax.swing.JTextField();
        buttonSave = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Ontologia Default"));
        jPanel4.setName("jPanel4"); // NOI18N

        jTabbedPane1.setName("jTabbedPane1"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N

        lbNombreClaseVuelo.setText("Nombre Clase");
        lbNombreClaseVuelo.setName("lbNombreClaseVuelo"); // NOI18N

        tfNombreClaseVuelo.setName("tfNombreClaseVuelo"); // NOI18N

        lbPropieadesVuelo.setText("Propiedades");
        lbPropieadesVuelo.setName("lbPropieadesVuelo"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tablePropieadesVuelo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Tipo de Dato"
            }
        ));
        tablePropieadesVuelo.setName("tablePropieadesVuelo"); // NOI18N
        jScrollPane1.setViewportView(tablePropieadesVuelo);
        tablePropieadesVuelo.getColumnModel().getColumn(0).setResizable(false);
        tablePropieadesVuelo.getColumnModel().getColumn(1).setResizable(false);

        buttonAddPropiedadVuelo.setIcon(new javax.swing.ImageIcon("C:\\Users\\Brunoli\\Documents\\NetBeansProjects\\OntoTravel\\icons\\add_obj.gif")); // NOI18N
        buttonAddPropiedadVuelo.setContentAreaFilled(false);
        buttonAddPropiedadVuelo.setName("buttonAddPropiedadVuelo"); // NOI18N
        buttonAddPropiedadVuelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddPropiedadVueloActionPerformed(evt);
            }
        });

        buttonRemovePropiedadVuelo.setIcon(new javax.swing.ImageIcon("C:\\Users\\Brunoli\\Documents\\NetBeansProjects\\OntoTravel\\icons\\delete_obj.gif")); // NOI18N
        buttonRemovePropiedadVuelo.setContentAreaFilled(false);
        buttonRemovePropiedadVuelo.setName("buttonRemovePropiedadVuelo"); // NOI18N
        buttonRemovePropiedadVuelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemovePropiedadVueloActionPerformed(evt);
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
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(lbNombreClaseVuelo)
                            .addGap(18, 18, 18)
                            .addComponent(tfNombreClaseVuelo, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                            .addGap(56, 56, 56))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(lbPropieadesVuelo)
                            .addGap(29, 29, 29)
                            .addComponent(buttonAddPropiedadVuelo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(buttonRemovePropiedadVuelo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(238, Short.MAX_VALUE)))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNombreClaseVuelo)
                    .addComponent(tfNombreClaseVuelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lbPropieadesVuelo)
                    .addComponent(buttonAddPropiedadVuelo)
                    .addComponent(buttonRemovePropiedadVuelo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Vuelo", jPanel1);

        jPanel2.setName("jPanel2"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        tablePropieadesHotel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Tipo de Dato"
            }
        ));
        tablePropieadesHotel.setName("tablePropieadesHotel"); // NOI18N
        jScrollPane2.setViewportView(tablePropieadesHotel);
        tablePropieadesHotel.getColumnModel().getColumn(0).setResizable(false);
        tablePropieadesHotel.getColumnModel().getColumn(1).setResizable(false);

        lbPropieadesHotel.setText("Propiedades");
        lbPropieadesHotel.setName("lbPropieadesHotel"); // NOI18N

        buttonAddPropiedadHotel.setIcon(new javax.swing.ImageIcon("C:\\Users\\Brunoli\\Documents\\NetBeansProjects\\OntoTravel\\icons\\add_obj.gif")); // NOI18N
        buttonAddPropiedadHotel.setContentAreaFilled(false);
        buttonAddPropiedadHotel.setName("buttonAddPropiedadHotel"); // NOI18N
        buttonAddPropiedadHotel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddPropiedadHotelActionPerformed(evt);
            }
        });

        buttonRemovePropiedadHotel.setIcon(new javax.swing.ImageIcon("C:\\Users\\Brunoli\\Documents\\NetBeansProjects\\OntoTravel\\icons\\delete_obj.gif")); // NOI18N
        buttonRemovePropiedadHotel.setContentAreaFilled(false);
        buttonRemovePropiedadHotel.setName("buttonRemovePropiedadHotel"); // NOI18N
        buttonRemovePropiedadHotel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemovePropiedadHotelActionPerformed(evt);
            }
        });

        lbNombreClaseHotel.setText("Nombre Clase");
        lbNombreClaseHotel.setName("lbNombreClaseHotel"); // NOI18N

        tfNombreClaseHotel.setName("tfNombreClaseHotel"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbNombreClaseHotel)
                        .addGap(18, 18, 18)
                        .addComponent(tfNombreClaseHotel, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbPropieadesHotel)
                        .addGap(25, 25, 25)
                        .addComponent(buttonAddPropiedadHotel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonRemovePropiedadHotel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNombreClaseHotel)
                    .addComponent(tfNombreClaseHotel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(buttonAddPropiedadHotel)
                    .addComponent(buttonRemovePropiedadHotel)
                    .addComponent(lbPropieadesHotel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Hotel", jPanel2);

        jPanel3.setName("jPanel3"); // NOI18N

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        tablePropieadesTranslado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Tipo de Dato"
            }
        ));
        tablePropieadesTranslado.setName("tablePropieadesTranslado"); // NOI18N
        jScrollPane3.setViewportView(tablePropieadesTranslado);
        tablePropieadesTranslado.getColumnModel().getColumn(0).setResizable(false);
        tablePropieadesTranslado.getColumnModel().getColumn(1).setResizable(false);

        lbPropieadesTranslado.setText("Propiedades");
        lbPropieadesTranslado.setName("lbPropieadesTranslado"); // NOI18N

        buttonAddPropiedadTranslado.setIcon(new javax.swing.ImageIcon("C:\\Users\\Brunoli\\Documents\\NetBeansProjects\\OntoTravel\\icons\\add_obj.gif")); // NOI18N
        buttonAddPropiedadTranslado.setContentAreaFilled(false);
        buttonAddPropiedadTranslado.setName("buttonAddPropiedadTranslado"); // NOI18N
        buttonAddPropiedadTranslado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddPropiedadTransladoActionPerformed(evt);
            }
        });

        buttonRemovePropiedadTranslado.setIcon(new javax.swing.ImageIcon("C:\\Users\\Brunoli\\Documents\\NetBeansProjects\\OntoTravel\\icons\\delete_obj.gif")); // NOI18N
        buttonRemovePropiedadTranslado.setContentAreaFilled(false);
        buttonRemovePropiedadTranslado.setName("buttonRemovePropiedadTranslado"); // NOI18N
        buttonRemovePropiedadTranslado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemovePropiedadTransladoActionPerformed(evt);
            }
        });

        lbNombreClaseTranslado.setText("Nombre Clase");
        lbNombreClaseTranslado.setName("lbNombreClaseTranslado"); // NOI18N

        tfNombreClaseTranslado.setName("tfNombreClaseTranslado"); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lbNombreClaseTranslado)
                        .addGap(18, 18, 18)
                        .addComponent(tfNombreClaseTranslado, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(buttonAddPropiedadTranslado, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonRemovePropiedadTranslado, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbPropieadesTranslado))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNombreClaseTranslado)
                    .addComponent(tfNombreClaseTranslado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(buttonRemovePropiedadTranslado)
                    .addComponent(buttonAddPropiedadTranslado)
                    .addComponent(lbPropieadesTranslado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Translado", jPanel3);

        buttonSave.setIcon(new javax.swing.ImageIcon("C:\\Users\\Brunoli\\Documents\\NetBeansProjects\\OntoTravel\\icons\\save.gif")); // NOI18N
        buttonSave.setContentAreaFilled(false);
        buttonSave.setName("buttonSave"); // NOI18N
        buttonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveActionPerformed(evt);
            }
        });

        jLabel1.setText("Guardar");
        jLabel1.setName("jLabel1"); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(buttonSave)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveActionPerformed
        //Las propiedades se cargan solas en el panel individual

        defaultOntology.getAlojamiento().setNombreClase(tfNombreClaseHotel.getText());

        defaultOntology.getTranslado().setNombreClase(tfNombreClaseTranslado.getText());

        defaultOntology.getViaje().setNombreClase(tfNombreClaseVuelo.getText());

        this.main.getConfiguration().setDefaultOntology(defaultOntology);
        this.main.guardarConfiguracion();
    }//GEN-LAST:event_buttonSaveActionPerformed

    private void buttonAddPropiedadVueloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddPropiedadVueloActionPerformed
        PanelDefaultProperty listProperties = new PanelDefaultProperty(main, true, (DefaultTableModel) tablePropieadesVuelo.getModel(), defaultOntology.getViaje());
        listProperties.setVisible(true);
    }//GEN-LAST:event_buttonAddPropiedadVueloActionPerformed

    private void buttonRemovePropiedadVueloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemovePropiedadVueloActionPerformed
        int selectedRow = tablePropieadesVuelo.getSelectedRow();
        if (selectedRow >= 0) {
            String nombre = (String) ((DefaultTableModel) tablePropieadesVuelo.getModel()).getValueAt(selectedRow, 0);
            defaultOntology.getViaje().removeProperty(nombre);
            ((DefaultTableModel) tablePropieadesVuelo.getModel()).removeRow(selectedRow);
        }
    }//GEN-LAST:event_buttonRemovePropiedadVueloActionPerformed

    private void buttonAddPropiedadHotelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddPropiedadHotelActionPerformed
        PanelDefaultProperty listProperties = new PanelDefaultProperty(main, true, (DefaultTableModel) tablePropieadesHotel.getModel(), defaultOntology.getAlojamiento());
        listProperties.setVisible(true);
    }//GEN-LAST:event_buttonAddPropiedadHotelActionPerformed

    private void buttonRemovePropiedadHotelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemovePropiedadHotelActionPerformed
        int selectedRow = tablePropieadesHotel.getSelectedRow();
        if (selectedRow >= 0) {
            String nombre = (String) ((DefaultTableModel) tablePropieadesHotel.getModel()).getValueAt(selectedRow, 0);
            defaultOntology.getAlojamiento().removeProperty(nombre);
            ((DefaultTableModel) tablePropieadesHotel.getModel()).removeRow(selectedRow);
        }
    }//GEN-LAST:event_buttonRemovePropiedadHotelActionPerformed

    private void buttonAddPropiedadTransladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddPropiedadTransladoActionPerformed
        PanelDefaultProperty listProperties = new PanelDefaultProperty(main, true, (DefaultTableModel) tablePropieadesTranslado.getModel(), defaultOntology.getTranslado());
        listProperties.setVisible(true);
    }//GEN-LAST:event_buttonAddPropiedadTransladoActionPerformed

    private void buttonRemovePropiedadTransladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemovePropiedadTransladoActionPerformed
        int selectedRow = tablePropieadesTranslado.getSelectedRow();
        if (selectedRow >= 0) {
            String nombre = (String) ((DefaultTableModel) tablePropieadesTranslado.getModel()).getValueAt(selectedRow, 0);
            defaultOntology.getTranslado().removeProperty(nombre);
            ((DefaultTableModel) tablePropieadesTranslado.getModel()).removeRow(selectedRow);
        }
    }//GEN-LAST:event_buttonRemovePropiedadTransladoActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAddPropiedadHotel;
    private javax.swing.JButton buttonAddPropiedadTranslado;
    private javax.swing.JButton buttonAddPropiedadVuelo;
    private javax.swing.JButton buttonRemovePropiedadHotel;
    private javax.swing.JButton buttonRemovePropiedadTranslado;
    private javax.swing.JButton buttonRemovePropiedadVuelo;
    private javax.swing.JButton buttonSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbNombreClaseHotel;
    private javax.swing.JLabel lbNombreClaseTranslado;
    private javax.swing.JLabel lbNombreClaseVuelo;
    private javax.swing.JLabel lbPropieadesHotel;
    private javax.swing.JLabel lbPropieadesTranslado;
    private javax.swing.JLabel lbPropieadesVuelo;
    private javax.swing.JTable tablePropieadesHotel;
    private javax.swing.JTable tablePropieadesTranslado;
    private javax.swing.JTable tablePropieadesVuelo;
    private javax.swing.JTextField tfNombreClaseHotel;
    private javax.swing.JTextField tfNombreClaseTranslado;
    private javax.swing.JTextField tfNombreClaseVuelo;
    // End of variables declaration//GEN-END:variables

    public void update() {
    }

    private void cargarOntologiaDefault() {
        defaultOntology = this.main.getConfiguration().getDefaultOntology();
        if (defaultOntology == null) {
            cargarNuevaOntologiaDefault();
        }
        cargarDefaultHotel();
        cargarDefaultVuelo();
        cargarDefaultTranslado();
    }

    private void cargarDefaultHotel() {
        if (defaultOntology.getAlojamiento().getNombreClase() != null) {
            tfNombreClaseHotel.setText(defaultOntology.getAlojamiento().getNombreClase());
        }

        for (DefaultProperty property : defaultOntology.getAlojamiento().getDefaultProperties()) {
            ((DefaultTableModel) tablePropieadesHotel.getModel()).addRow(new String[]{property.getName(), property.getTipoDato().toString()});
        }
    }

    private void cargarDefaultVuelo() {
        if (defaultOntology.getViaje().getNombreClase() != null) {
            tfNombreClaseVuelo.setText(defaultOntology.getViaje().getNombreClase());
        }

        for (DefaultProperty property : defaultOntology.getViaje().getDefaultProperties()) {
            ((DefaultTableModel) tablePropieadesVuelo.getModel()).addRow(new String[]{property.getName(), property.getTipoDato().toString()});
        }
    }

    private void cargarDefaultTranslado() {
        if (defaultOntology.getTranslado().getNombreClase() != null) {
            tfNombreClaseTranslado.setText(defaultOntology.getTranslado().getNombreClase());
        }

        for (DefaultProperty property : defaultOntology.getTranslado().getDefaultProperties()) {
            ((DefaultTableModel) tablePropieadesTranslado.getModel()).addRow(new String[]{property.getName(), property.getTipoDato().toString()});
        }
    }
}
