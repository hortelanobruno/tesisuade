/*
 * PanelConfiguracion.java
 *
 * Created on August 26, 2008, 1:00 PM
 */
package com.ontotravel.gui.panels.configuracion;

import com.ontotravel.gui.DirectoryChooser;
import com.ontotravel.gui.FileChooser;
import com.ontotravel.gui.FramePrincipal;
import com.ontotravel.mvc.model.BusinessDelegate;
import com.ontotravel.mvc.view.VistaConfiguracion;
import com.ontotravel.util.ErrorTransoformacion;
import com.ontotravel.util.FileCopy;
import com.ontotravel.util.RelativePath;
import java.io.File;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author  Admin
 */
public class PanelConfiguracion extends javax.swing.JPanel {

    private FramePrincipal main;
    private FileChooser chooser;
    private DirectoryChooser dirChooser;
    private VistaConfiguracion vista;

    /** Creates new form PanelConfiguracion */
    public PanelConfiguracion(FramePrincipal ref, VistaConfiguracion vista) {
        this.main = ref;
        this.vista = vista;
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        buttonAddOntViajes = new javax.swing.JButton();
        buttonRemoverOntViajes = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listOntologiasViajes = new javax.swing.JList();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        textFieldOntVocabulario = new javax.swing.JTextField();
        buttonChangeOntVocabulario = new javax.swing.JButton();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        textFieldDirOnt = new javax.swing.JTextField();
        buttonChageDirectorioOntologia = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1023, 532));

        buttonAddOntViajes.setIcon(new javax.swing.ImageIcon("C:\\Users\\Brunoli\\Documents\\NetBeansProjects\\OntoTravel\\icons\\add_obj.gif")); // NOI18N
        buttonAddOntViajes.setContentAreaFilled(false);
        buttonAddOntViajes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddOntViajesActionPerformed(evt);
            }
        });

        buttonRemoverOntViajes.setIcon(new javax.swing.ImageIcon("C:\\Users\\Brunoli\\Documents\\NetBeansProjects\\OntoTravel\\icons\\delete_obj.gif")); // NOI18N
        buttonRemoverOntViajes.setContentAreaFilled(false);
        buttonRemoverOntViajes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoverOntViajesActionPerformed(evt);
            }
        });

        listOntologiasViajes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(listOntologiasViajes);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(buttonAddOntViajes, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonRemoverOntViajes, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonAddOntViajes)
                    .addComponent(buttonRemoverOntViajes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Ontologias", jPanel2);

        buttonChangeOntVocabulario.setIcon(new javax.swing.ImageIcon("C:\\Users\\Brunoli\\Documents\\NetBeansProjects\\OntoTravel\\icons\\open.gif")); // NOI18N
        buttonChangeOntVocabulario.setContentAreaFilled(false);
        buttonChangeOntVocabulario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonChangeOntVocabularioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonChangeOntVocabulario, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldOntVocabulario, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonChangeOntVocabulario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textFieldOntVocabulario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Vocabulario", jPanel3);

        buttonChageDirectorioOntologia.setIcon(new javax.swing.ImageIcon("C:\\Users\\Brunoli\\Documents\\NetBeansProjects\\OntoTravel\\icons\\open.gif")); // NOI18N
        buttonChageDirectorioOntologia.setContentAreaFilled(false);
        buttonChageDirectorioOntologia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonChageDirectorioOntologiaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonChageDirectorioOntologia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldDirOnt, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(buttonChageDirectorioOntologia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textFieldDirOnt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );

        jTabbedPane3.addTab("Carpeta", jPanel4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTabbedPane3, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                    .addComponent(jTabbedPane2, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    public void initComponents2() {
        listOntologiasViajes.setModel(new DefaultListModel());

        //carga viajes
        Vector<String> viajes = main.getConfiguration().getOntologiasViajes();
        if (viajes != null) {
            DefaultListModel model = (DefaultListModel) listOntologiasViajes.getModel();
            for (int i = 0; i < viajes.size(); i++) {
                String[] nombre = viajes.elementAt(i).split("/");
                model.addElement(nombre[nombre.length - 1]);
            }
        }

        //cargar vocabulario
        Vector<String> voc = main.getConfiguration().getOntologiasVocabulario();
        if (voc != null) {
            for (int i = 0; i < voc.size(); i++) {
                String vocabulario = main.getConfiguration().getOntologiasVocabulario().elementAt(i);
                textFieldOntVocabulario.setText(vocabulario);
            }
        }

        //carga directorio de ontologia
        String dirOnt = main.getConfiguration().getOwlDirectory();
        textFieldDirOnt.setText(dirOnt);
    }

private void buttonAddOntViajesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddOntViajesActionPerformed
    chooser = new FileChooser(this.main, true, this.main.getConfiguration().getOwlDirectory());
    if (chooser.getButton().equals("Cancel")) {
    } else {
        try {
            String urlNew = chooser.path.split("/")[chooser.path.split("/").length - 1];
            //Aca transformo, si va todo bien muestro un cartel con OK, sino un cartel con el error
            ErrorTransoformacion errores = ((BusinessDelegate) this.vista.getModelo()).generarOntologiaBusqueda(this.main.getConfiguration(), chooser.path, this.main.getConfiguration().getOwlDirectory() + urlNew, this.main.getConfiguration().getOntologiasVocabulario().get(0));
            if (errores == null) {
                if (main.getConfiguration().getOntologiasViajes() == null) {
                    main.getConfiguration().setOntologiasViajes(new Vector<String>());
                    main.getConfiguration().getOntologiasViajes().add(this.main.getConfiguration().getOwlDirectory() + urlNew);
                } else {
                    main.getConfiguration().getOntologiasViajes().add(this.main.getConfiguration().getOwlDirectory() + urlNew);
                }
                ((DefaultListModel) listOntologiasViajes.getModel()).addElement(urlNew);
                main.recargarConfiguracion();
                main.guardarConfiguracion();
                JOptionPane.showMessageDialog(this, "La ontologia fue cargada correctamente", "Asistente de Viajes", JOptionPane.INFORMATION_MESSAGE);
            } else {
                if (!errores.getSubErrores().isEmpty()) {
                    DialogoErroresOntologia dialogoErrores = new DialogoErroresOntologia(main, true, errores);
                    dialogoErrores.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "La ontologia NO cumple con los requerimientos minimos", "Asistente de Viajes", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        } catch (Exception ex) {
            //Entro aca porque no cumple el minimo requerimiento
            JOptionPane.showMessageDialog(this, "La ontologia NO cumple con los requerimientos minimos", "Asistente de Viajes", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}//GEN-LAST:event_buttonAddOntViajesActionPerformed

private void buttonRemoverOntViajesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoverOntViajesActionPerformed
    if (!listOntologiasViajes.isSelectionEmpty()) {
        String viaje = listOntologiasViajes.getSelectedValue().toString();
        DefaultListModel model = (DefaultListModel) listOntologiasViajes.getModel();
        model.removeElement(viaje);
        Vector<String> viajes = main.getConfiguration().getOntologiasViajes();
        int aux = -1;
        for (int i = 0; i < viajes.size(); i++) {
            String ele = viajes.elementAt(i);
            String[] elements = ele.split("/");
            if (elements[elements.length - 1].equalsIgnoreCase(viaje)) {
                aux = i;
                viaje = ele;
                break;
            }
        }
        main.getConfiguration().getOntologiasViajes().remove(aux);
        FileCopy copy = new FileCopy();
        copy.eliminar(viaje);
        main.recargarConfiguracion();
        JOptionPane.showMessageDialog(this, "La ontologia fue borrada correctamente", "Asistente de Viajes", JOptionPane.INFORMATION_MESSAGE);
    }
}//GEN-LAST:event_buttonRemoverOntViajesActionPerformed

private void buttonChangeOntVocabularioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonChangeOntVocabularioActionPerformed
    chooser = new FileChooser(this.main, true, this.main.getConfiguration().getOwlDirectory());
    if (chooser.getButton().equals("Aceptar")) {
        String urlNew = chooser.path.split("/")[chooser.path.split("/").length - 1];
        FileCopy copy = new FileCopy();
        copy.copiar(chooser.path, this.main.getConfiguration().getOwlDirectory() + urlNew);
        textFieldOntVocabulario.setText(RelativePath.getRelativePath(new File(this.main.getConfiguration().getOwlDirectory() + urlNew)));
        main.getConfiguration().setOntologiasVocabulario(new Vector<String>());
        main.getConfiguration().getOntologiasVocabulario().add(RelativePath.getRelativePath(new File(this.main.getConfiguration().getOwlDirectory() + urlNew)));
        main.recargarConfiguracion();
    }
}//GEN-LAST:event_buttonChangeOntVocabularioActionPerformed

private void buttonChageDirectorioOntologiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonChageDirectorioOntologiaActionPerformed
    dirChooser = new DirectoryChooser(this.main, true);
    if (dirChooser.getButton().equals("Aceptar")) {
        String folder = dirChooser.getPath();
        folder = folder + "/";
        textFieldDirOnt.setText(RelativePath.getRelativePath(new File(folder)));
        main.getConfiguration().setOwlDirectory(RelativePath.getRelativePath(new File(folder)));
        main.recargarConfiguracion();
    }
}//GEN-LAST:event_buttonChageDirectorioOntologiaActionPerformed

    public void update() {
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAddOntViajes;
    private javax.swing.JButton buttonChageDirectorioOntologia;
    private javax.swing.JButton buttonChangeOntVocabulario;
    private javax.swing.JButton buttonRemoverOntViajes;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JList listOntologiasViajes;
    private javax.swing.JTextField textFieldDirOnt;
    private javax.swing.JTextField textFieldOntVocabulario;
    // End of variables declaration//GEN-END:variables
}
