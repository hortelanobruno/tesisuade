/*
 * FramePrincipal.java
 *
 * Created on 29 de julio de 2008, 00:35
 */
package gui;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import panels.PanelMotorBusqueda;
import panels.PanelSinonimos;

/**
 *
 * @author  Administrador
 */
public class FramePrincipal extends javax.swing.JFrame {

    private boolean isPanelPrincipalSelected;
    private boolean isPanelMotorBusquedaSelected;
    private boolean isPanelSinonimosSelected;
    private PanelMotorBusqueda panelMotorBusqueda;
    private PanelSinonimos panelSinonimos;
    private JPanel activePanel;

    /** Creates new form FramePrincipal */
    public FramePrincipal() {
        initComponents();
        this.setLookAndFeel();
        isPanelPrincipalSelected = true;
        iniciaCuenta();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        itemsToolBar = new javax.swing.JToolBar();
        jButton2 = new javax.swing.JButton();
        statusToolBar = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        panelPrincipal = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        menuBar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Asistente de Viajes");

        itemsToolBar.setFloatable(false);

        jButton2.setText("jButton2");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        itemsToolBar.add(jButton2);

        statusToolBar.setFloatable(false);

        jButton1.setText("jButton1");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        statusToolBar.add(jButton1);

        panelPrincipal.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Pantalla principal del asistente de viajes");

        jProgressBar1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jProgressBar1.setIndeterminate(true);
        jProgressBar1.setStringPainted(true);

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGap(269, 269, 269)
                        .addComponent(jLabel1))
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGap(298, 298, 298)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(276, Short.MAX_VALUE))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(156, 156, 156)
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(195, Short.MAX_VALUE))
        );

        jMenu1.setText("File");

        jMenuItem1.setText("Nueva Busqueda");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);
        jMenu1.add(jSeparator1);

        jMenuItem2.setText("Salir");
        jMenu1.add(jMenuItem2);

        menuBar.add(jMenu1);

        jMenu2.setText("Edit");

        jMenuItem3.setText("Configuracion");
        jMenu2.add(jMenuItem3);

        menuBar.add(jMenu2);

        jMenu5.setText("Vocabulario");

        jMenuItem4.setText("Sinonimos");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem4);

        jMenu4.setText("Idiomas");

        jMenuItem5.setText("Ingles");
        jMenu4.add(jMenuItem5);

        jMenu5.add(jMenu4);

        menuBar.add(jMenu5);

        jMenu6.setText("OWL");

        jMenuItem6.setText("OWL Explorer");
        jMenu6.add(jMenuItem6);

        menuBar.add(jMenu6);

        jMenu3.setText("Help");
        menuBar.add(jMenu3);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(itemsToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
            .addComponent(statusToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(251, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(itemsToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 104, Short.MAX_VALUE)
                .addComponent(statusToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
    if (!isPanelMotorBusquedaSelected) {
        panelMotorBusqueda = new PanelMotorBusqueda(this);
        ponerPanel(panelMotorBusqueda);
    }
}//GEN-LAST:event_jMenuItem1ActionPerformed

private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
    if (!isPanelSinonimosSelected) {
        panelSinonimos = new PanelSinonimos(this);
        ponerPanel(panelSinonimos);
    }
}//GEN-LAST:event_jMenuItem4ActionPerformed
    
private void setLookAndFeel() throws HeadlessException {
        try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                SwingUtilities.updateComponentTreeUI(this);

                Toolkit t = Toolkit.getDefaultToolkit();
                this
                                .setLocation((int) (t.getScreenSize().getWidth() - this
                                                .getWidth()) / 2, (int) (t.getScreenSize()
                                                .getHeight() - this.getHeight()) / 2);
        } catch (ClassNotFoundException ex) {

        } catch (InstantiationException ex) {

        } catch (IllegalAccessException ex) {

        } catch (UnsupportedLookAndFeelException ex) {

        }
}
public void setActivePanel(JPanel activePanel) {
	this.activePanel = activePanel;
}

public void ponerPanel(JPanel panel) {
        if(isPanelPrincipalSelected) {
            this.remove(panelPrincipal);
            this.repaint();
        }
        if(isPanelMotorBusquedaSelected) {
            this.remove(panelMotorBusqueda);
            this.repaint();
        }
        if(isPanelSinonimosSelected) {
            this.remove(panelSinonimos);
            this.repaint();
        }


        if(panel instanceof PanelMotorBusqueda) {
            this.isPanelPrincipalSelected = false;
            this.isPanelSinonimosSelected = false;
            this.setActivePanel(panelMotorBusqueda);
            this.isPanelMotorBusquedaSelected = true;
        }
        if(panel instanceof PanelSinonimos){
            this.isPanelPrincipalSelected = false;
            this.isPanelMotorBusquedaSelected = false;
            this.setActivePanel(panelSinonimos);
            this.isPanelSinonimosSelected = true;
        }


javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(itemsToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 759, Short.MAX_VALUE)
            .addComponent(statusToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 759, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(itemsToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(statusToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        
   //     this.setExtendedState(JFrame.MAXIMIZED_BOTH);

}

Thread hilo;
  Object objeto = new Object();
  boolean pideParar = false;
  
public boolean esImpar(int iNumero) {
  if (iNumero%2!=0)
    return true;
  else
    return false;
}

  public void iniciaCuenta() {
    if( hilo == null ) {
      hilo = new ThreadCarga();
      pideParar = false;
      hilo.start();
    }
  }
class ThreadCarga extends Thread {
    public void run() {
      int min = 0;
      int max = 100;
      int aux = 1;
      jProgressBar1.setValue( min );
      jProgressBar1.setMinimum( min );
      jProgressBar1.setMaximum( max );
      while(true){
      if(esImpar(aux)){
          for (int i=min; i <= max; i++ ) {
            jProgressBar1.setValue( i );
            repaint();
            synchronized( objeto ) {
              if( pideParar )
                break;
              try {
                objeto.wait( 10 );
              } catch( InterruptedException e ) {
                // Se ignoran las excepciones
              }
            }
          }
      }else{
          for (int i=max; i >= min; i-- ) {
            jProgressBar1.setValue( i );
            repaint();
            synchronized( objeto ) {
              if( pideParar )
                break;
              try {
                objeto.wait( 10 );
              } catch( InterruptedException e ) {
                // Se ignoran las excepciones
              }
            }
          }
      }
      aux++;
      }
      //hilo = null;
    }
  }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FramePrincipal().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar itemsToolBar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JToolBar statusToolBar;
    // End of variables declaration//GEN-END:variables
    
}
