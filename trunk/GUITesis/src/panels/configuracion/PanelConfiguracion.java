/*
 * PanelConfiguracion.java
 *
 * Created on August 26, 2008, 1:00 PM
 */
package panels.configuracion;

import GUI.DirectoryChooser;
import gui.FileChooser;
import gui.FramePrincipal;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultListModel;
import modelo.BusinessDelegate;
import varios.FileCopy;
import vistas.VistaConfiguracion;

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
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listOntologiasViajes = new javax.swing.JList();
        buttonRemoverOntViajes = new javax.swing.JButton();
        buttonAddOntViajes = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        textFieldOntVocabulario = new javax.swing.JTextField();
        buttonChangeOntVocabulario = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        textFieldDirOnt = new javax.swing.JTextField();
        buttonChageDirectorioOntologia = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1023, 532));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Ontologias Viajes Cargadas");

        listOntologiasViajes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(listOntologiasViajes);

        buttonRemoverOntViajes.setText("Remover");
        buttonRemoverOntViajes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoverOntViajesActionPerformed(evt);
            }
        });

        buttonAddOntViajes.setText("Agregar");
        buttonAddOntViajes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddOntViajesActionPerformed(evt);
            }
        });

        jLabel2.setText("Ontologia Vocabulario");

        buttonChangeOntVocabulario.setText("Cambiar");
        buttonChangeOntVocabulario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonChangeOntVocabularioActionPerformed(evt);
            }
        });

        jLabel3.setText("Directorio Ontologias");

        buttonChageDirectorioOntologia.setText("Cambiar");
        buttonChageDirectorioOntologia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonChageDirectorioOntologiaActionPerformed(evt);
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
                        .addComponent(buttonAddOntViajes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonRemoverOntViajes))
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addComponent(textFieldOntVocabulario, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
                    .addComponent(buttonChangeOntVocabulario)
                    .addComponent(jLabel3)
                    .addComponent(textFieldDirOnt, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
                    .addComponent(buttonChageDirectorioOntologia))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAddOntViajes)
                    .addComponent(buttonRemoverOntViajes))
                .addGap(32, 32, 32)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textFieldOntVocabulario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonChangeOntVocabulario)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textFieldDirOnt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonChageDirectorioOntologia)
                .addContainerGap(91, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(424, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
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
        String urlNew = chooser.path.split("/")[chooser.path.split("/").length - 1];
        FileCopy copy = new FileCopy();
        copy.copiar(chooser.path, this.main.getConfiguration().getOwlDirectory() + urlNew);
        //Aca transformo, si va todo bien muestro un cartel con OK, sino un cartel con el error
        List<String> errores = ((BusinessDelegate)this.vista.getModelo()).generarOntologiaBusqueda(this.main.getConfiguration().getOwlDirectory() + urlNew,this.main.getConfiguration().getOntologiasVocabulario().get(0));
        
        
        if (main.getConfiguration().getOntologiasViajes() == null) {
            main.getConfiguration().setOntologiasViajes(new Vector<String>());
            main.getConfiguration().getOntologiasViajes().add(this.main.getConfiguration().getOwlDirectory() + urlNew);
        } else {
            main.getConfiguration().getOntologiasViajes().add(this.main.getConfiguration().getOwlDirectory() + urlNew);
        }
        ((DefaultListModel) listOntologiasViajes.getModel()).addElement(urlNew);
        main.recargarConfiguracion();
    }
}//GEN-LAST:event_buttonAddOntViajesActionPerformed

private void buttonRemoverOntViajesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoverOntViajesActionPerformed
// TODO add your handling code here:
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
}//GEN-LAST:event_buttonRemoverOntViajesActionPerformed

private void buttonChangeOntVocabularioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonChangeOntVocabularioActionPerformed
    chooser = new FileChooser(this.main, true, this.main.getConfiguration().getOwlDirectory());
    if (chooser.getButton().equals("Cancel")) {
    } else {
        String urlNew = chooser.path.split("/")[chooser.path.split("/").length - 1];
        FileCopy copy = new FileCopy();
        copy.copiar(chooser.path, this.main.getConfiguration().getOwlDirectory() + urlNew);
        textFieldOntVocabulario.setText(chooser.path);
        main.getConfiguration().setOntologiasVocabulario(new Vector<String>());
        main.getConfiguration().getOntologiasVocabulario().add(this.main.getConfiguration().getOwlDirectory() + urlNew);
        main.recargarConfiguracion();
    }
}//GEN-LAST:event_buttonChangeOntVocabularioActionPerformed

private void buttonChageDirectorioOntologiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonChageDirectorioOntologiaActionPerformed
    dirChooser = new DirectoryChooser(this.main, true);
    String folder = dirChooser.getPath();
    folder = folder + "/";
    textFieldDirOnt.setText(folder);
    main.getConfiguration().setOwlDirectory(folder);
    main.recargarConfiguracion();

}//GEN-LAST:event_buttonChageDirectorioOntologiaActionPerformed

public void update(){
    
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAddOntViajes;
    private javax.swing.JButton buttonChageDirectorioOntologia;
    private javax.swing.JButton buttonChangeOntVocabulario;
    private javax.swing.JButton buttonRemoverOntViajes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList listOntologiasViajes;
    private javax.swing.JTextField textFieldDirOnt;
    private javax.swing.JTextField textFieldOntVocabulario;
    // End of variables declaration//GEN-END:variables

}
