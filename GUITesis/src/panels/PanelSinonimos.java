/*
 * PanelSinonimos.java
 *
 * Created on 31 de julio de 2008, 23:33
 */

package panels;

import GUI.FileChooser;
import controladores.ControladorPanelSinonimos;
import gui.FramePrincipal;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import modelo.BusinessDelegate;
import vistas.VistaSinonimos;
import vo.IndividualVO;

/**
 *
 * @author  Administrador
 */
public class PanelSinonimos extends javax.swing.JPanel {

    private FileChooser chooser;
    private FramePrincipal main;
    private VistaSinonimos vista;
    private String urlOWL;
    private String chooserButton;
    private boolean cargarArbol;
    private TreeSelectionEvent eventoTree;
    private boolean cargarInstancia;
    
    /** Creates new form PanelSinonimos */
    public PanelSinonimos(FramePrincipal main, VistaSinonimos vista) {
        this.main = main;
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

        jLabel1 = new javax.swing.JLabel();
        textFieldURL = new javax.swing.JTextField();
        buttonExaminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        treeIndividual = new javax.swing.JTree();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listSinonimo = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        listTraduccion = new javax.swing.JList();
        buttonAddSinonimo = new javax.swing.JButton();
        buttonRemoveSinonimo = new javax.swing.JButton();
        buttonAddTraduccion = new javax.swing.JButton();
        buttonRemoveTraduccion = new javax.swing.JButton();
        labelPalabra = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1023, 532));

        jLabel1.setText("Ontologia Vocabulario");

        buttonExaminar.setText("Examinar");
        buttonExaminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExaminarActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(treeIndividual);

        jLabel2.setText("Palabra");

        jLabel3.setText("Sinonimo");

        jLabel4.setText("Traduccion");

        jScrollPane2.setViewportView(listSinonimo);

        jScrollPane3.setViewportView(listTraduccion);

        buttonAddSinonimo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/add_obj.gif"))); // NOI18N

        buttonRemoveSinonimo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/delete_obj.gif"))); // NOI18N

        buttonAddTraduccion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/add_obj.gif"))); // NOI18N

        buttonRemoveTraduccion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/delete_obj.gif"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 162, Short.MAX_VALUE)
                        .addComponent(buttonAddTraduccion, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonRemoveTraduccion, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 176, Short.MAX_VALUE)
                        .addComponent(buttonAddSinonimo, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonRemoveSinonimo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                        .addComponent(labelPalabra, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE))
                .addGap(33, 33, 33))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelPalabra, javax.swing.GroupLayout.DEFAULT_SIZE, 14, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonRemoveSinonimo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAddSinonimo)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(buttonAddTraduccion)
                    .addComponent(buttonRemoveTraduccion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(26, 26, 26)
                        .addComponent(textFieldURL, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonExaminar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(441, 441, 441))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(buttonExaminar)
                    .addComponent(textFieldURL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

private void buttonExaminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExaminarActionPerformed
//Filechooser para elegir el archivo owl
    chooser = new FileChooser(main, true, main.getDefaultOWLPath());
    setUrlOWL(chooser.getPath());
    setChooserButton(chooser.getButton());
    if (chooserButton.equals("Cancel")) {

    } else {
            // Cargar los table
            ((ControladorPanelSinonimos) vista.getControlador()).doCargarOWL(true);
    }
}//GEN-LAST:event_buttonExaminarActionPerformed

public void update() {
    if(cargarArbol){
        cargarTree();
        if(cargarInstancia){
            String instancia = eventoTree.getPath().getLastPathComponent().toString();
            IndividualVO individual = (IndividualVO) ((BusinessDelegate)vista.getModelo()).obtenerIndividual(instancia);
        }
    }else{
        
    }
}

public void cargarTree(){
    ArrayList<String> individuals = (ArrayList<String>) ((BusinessDelegate)vista.getModelo()).obtenerInstanciasVocabuario(urlOWL);

    DefaultMutableTreeNode abuelo = new DefaultMutableTreeNode("Palabras");
    DefaultTreeModel modelo = new DefaultTreeModel(abuelo);
    treeIndividual = new JTree(modelo);
    DefaultMutableTreeNode[] padre = new DefaultMutableTreeNode[individuals.size()];
    Iterator it = individuals.iterator();
    int i = 0;
    while(it.hasNext()){
            padre[i] = new DefaultMutableTreeNode(it.next().toString());
            i++;
    }
    for(int j = 0 ; j < padre.length ; j++){
            modelo.insertNodeInto(padre[j],abuelo, j);
    }
    treeIndividual.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
    public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
        buttonCargarActionPerformed(evt);
    }
    });
    jScrollPane1.setViewportView(treeIndividual);

}

private void buttonCargarActionPerformed(javax.swing.event.TreeSelectionEvent evt) {
        // Cargar solicitud en tablas
        this.eventoTree = evt;
        if(!eventoTree.getPath().getLastPathComponent().toString().equals("Solicitudes")){
                ((ControladorPanelSinonimos) vista.getControlador()).doCargarInstancia(true);
        }
}
public void setCargarArbol(boolean flag){
    this.cargarArbol = flag;
}


private void initComponents2(){
    DefaultMutableTreeNode abuelo = new DefaultMutableTreeNode("Palabra");
    DefaultTreeModel modelo = new DefaultTreeModel(abuelo);
    treeIndividual = new JTree(modelo);
    jScrollPane1.setViewportView(treeIndividual);
}
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAddSinonimo;
    private javax.swing.JButton buttonAddTraduccion;
    private javax.swing.JButton buttonExaminar;
    private javax.swing.JButton buttonRemoveSinonimo;
    private javax.swing.JButton buttonRemoveTraduccion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelPalabra;
    private javax.swing.JList listSinonimo;
    private javax.swing.JList listTraduccion;
    private javax.swing.JTextField textFieldURL;
    private javax.swing.JTree treeIndividual;
    // End of variables declaration//GEN-END:variables

    public String getUrlOWL() {
        return urlOWL;
    }

    public void setUrlOWL(String urlOWL) {
        this.urlOWL = urlOWL;
    }

    public String getChooserButton() {
        return chooserButton;
    }

    public void setChooserButton(String chooserButton) {
        this.chooserButton = chooserButton;
    }

    public boolean isCargarInstancia() {
        return cargarInstancia;
    }

    public void setCargarInstancia(boolean cargarInstancia) {
        this.cargarInstancia = cargarInstancia;
    }

}