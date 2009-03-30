/*
 * FramePrincipal.java
 *
 * Created on 29 de julio de 2008, 00:35
 */
package gui;

import configuration.Configuration;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import modelo.BusinessDelegate;
import panels.busqueda.PanelMotorBusqueda;
import panels.configuracion.PanelConfiguracion;
import panels.configuracion.PanelDefaultOntologia;
import panels.nuevaontologia.PanelNuevaOntologia;
import panels.sinonimos.PanelSinonimos;
import varios.Constantes;
import varios.XMLWrapper;
import vistas.VistaConfiguracion;
import vistas.VistaDefaultOntologia;
import vistas.VistaMotorBusqueda;
import vistas.VistaNuevaOntologia;
import vistas.VistaSinonimos;

/**
 *
 * @author  Administrador
 */
public class FramePrincipal extends javax.swing.JFrame {

    private FileChooser chooser;
    private boolean isPanelPrincipalSelected;
    private boolean isPanelMotorBusquedaSelected;
    private boolean isPanelNuevaOntologiaSelected;
    private boolean isPanelSinonimosSelected;
    private boolean isPanelConfiguracionSelected;
    private boolean isPanelOntologiaDefaultSelected;
    private PanelMotorBusqueda panelMotorBusqueda;
    private PanelSinonimos panelSinonimos;
    private PanelNuevaOntologia panelNuevaOntologia;
    private PanelConfiguracion panelConfiguracion;
    private PanelDefaultOntologia panelDefaultOntologia;
    private JPanel activePanel;
    private VistaMotorBusqueda vistaMotorBusqueda;
    private VistaSinonimos vistaSinonimos;
    private VistaNuevaOntologia vistaNuevaOntologia;
    private VistaConfiguracion vistaConfiguracion;
    private VistaDefaultOntologia vistaDefaultOntologia;
    private Configuration configuration;

    /** Creates new form FramePrincipal */
    public FramePrincipal(VistaMotorBusqueda vistaMotor, VistaSinonimos vistaSin, VistaNuevaOntologia vistaNuevaOnt, VistaConfiguracion vistaConf) {
        this.vistaMotorBusqueda = vistaMotor;
        this.vistaNuevaOntologia = vistaNuevaOnt;
        this.vistaSinonimos = vistaSin;
        this.vistaConfiguracion = vistaConf;
        initComponents();
        this.setLookAndFeel();
        isPanelPrincipalSelected = true;
        iniciaCuenta();//De la progress bar
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

        itemsToolBar = new javax.swing.JToolBar();
        buttonBuscarVuelo = new javax.swing.JButton();
        buttonBuscarHotel = new javax.swing.JButton();
        buttonBuscarAuto = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        buttonSetting = new javax.swing.JButton();
        statusToolBar = new javax.swing.JToolBar();
        jLabel2 = new javax.swing.JLabel();
        labelEstado = new javax.swing.JLabel();
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
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JSeparator();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Asistente de Viajes");

        itemsToolBar.setFloatable(false);
        itemsToolBar.setMargin(new java.awt.Insets(0, 10, 0, 0));

        buttonBuscarVuelo.setText("Buscar vuelo");
        buttonBuscarVuelo.setFocusable(false);
        buttonBuscarVuelo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonBuscarVuelo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonBuscarVuelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBuscarVueloActionPerformed(evt);
            }
        });
        itemsToolBar.add(buttonBuscarVuelo);

        buttonBuscarHotel.setText("Buscar hotel");
        buttonBuscarHotel.setFocusable(false);
        buttonBuscarHotel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonBuscarHotel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonBuscarHotel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBuscarHotelActionPerformed(evt);
            }
        });
        itemsToolBar.add(buttonBuscarHotel);

        buttonBuscarAuto.setText("Buscar auto");
        buttonBuscarAuto.setFocusable(false);
        buttonBuscarAuto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonBuscarAuto.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonBuscarAuto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBuscarAutoActionPerformed(evt);
            }
        });
        itemsToolBar.add(buttonBuscarAuto);
        itemsToolBar.add(jSeparator2);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/nuevo/new_wiz.gif"))); // NOI18N
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        itemsToolBar.add(jButton2);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/nuevo/open.gif"))); // NOI18N
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        itemsToolBar.add(jButton3);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/nuevo/save.gif"))); // NOI18N
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        itemsToolBar.add(jButton4);
        itemsToolBar.add(jSeparator4);

        buttonSetting.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/setting.gif"))); // NOI18N
        buttonSetting.setFocusable(false);
        buttonSetting.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonSetting.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        itemsToolBar.add(buttonSetting);

        statusToolBar.setFloatable(false);
        statusToolBar.setMargin(new java.awt.Insets(0, 10, 0, 0));

        jLabel2.setText("     ");
        statusToolBar.add(jLabel2);

        labelEstado.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        labelEstado.setPreferredSize(new java.awt.Dimension(100, 0));
        statusToolBar.add(labelEstado);

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
                .addGap(393, 393, 393)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(403, Short.MAX_VALUE))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(173, 173, 173)
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(286, Short.MAX_VALUE))
        );

        jMenu1.setText("Archivo");

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/nuevo/view.gif"))); // NOI18N
        jMenuItem1.setText("Nueva Busqueda");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);
        jMenu1.add(jSeparator1);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/nuevo/exit.png"))); // NOI18N
        jMenuItem2.setText("Salir");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        menuBar.add(jMenu1);

        jMenu2.setText("Editar");

        jMenuItem3.setText("Configuracion");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem11.setText("OntologiaDefault");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem11);

        menuBar.add(jMenu2);

        jMenu5.setText("Vocabulario");

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/nuevo/book.gif"))); // NOI18N
        jMenuItem4.setText("Sinonimos");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem4);

        menuBar.add(jMenu5);

        jMenu6.setText("Ontologia");

        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/nuevo/new_wiz.gif"))); // NOI18N
        jMenuItem7.setText("Nueva Ontologia");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem7);

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/nuevo/open.gif"))); // NOI18N
        jMenuItem6.setText("Abrir Ontologia");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem6);

        jMenuItem9.setText("Cerrar Ontologia");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem9);
        jMenu6.add(jSeparator3);

        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/nuevo/save.gif"))); // NOI18N
        jMenuItem8.setText("Grabar Ontologia");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem8);

        menuBar.add(jMenu6);

        jMenu3.setText("Ayuda");

        jMenuItem10.setText("Acerca de");
        jMenu3.add(jMenuItem10);

        menuBar.add(jMenu3);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(itemsToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 990, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(statusToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 1010, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(itemsToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void initComponents2() {
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new java.awt.event.WindowAdapter() {

            public void windowClosing(WindowEvent winEvt) {
                CloseApplication();
            }
        });
    }

    private void CloseApplication() {
        if (JOptionPane.showConfirmDialog(this,
                "Esta seguro que desea cerrar la aplicacion?",
                "Asistente de Viajes", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) == 0) {
            guardarConfiguracion();
            System.exit(0);
        }
    }

    public void guardarConfiguracion() {
        XMLWrapper xml = new XMLWrapper();
        xml.guardarConfiguracion(getConfiguration());
    }

    public void cargarConfiguracion() {
        XMLWrapper xml = new XMLWrapper();
        Configuration conf = xml.leerConfiguracion(Constantes.CONFIGURATION_URL);
        if (conf != null) {
            this.setConfiguration(conf);
            recargarConfiguracion();
        } else {
            conf = new Configuration();
            this.setConfiguration(conf);
        }
    }

    public void recargarConfiguracion() {
        ((BusinessDelegate) vistaMotorBusqueda.getModelo()).cargarConfiguracion(this.getConfiguration());
    }

private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
    if (!isPanelMotorBusquedaSelected) {
        setPanelMotorBusqueda(new PanelMotorBusqueda(this, vistaMotorBusqueda));
        ponerPanel(getPanelMotorBusqueda());
        labelEstado.setText(Mensajes.NUEVA_BUSQUEDA);
    }
}//GEN-LAST:event_jMenuItem1ActionPerformed

private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
    if (!isPanelSinonimosSelected) {
        setPanelSinonimos(new PanelSinonimos(this, vistaSinonimos));
        ponerPanel(getPanelSinonimos());
        labelEstado.setText(Mensajes.SINONIMOS);
    }
}//GEN-LAST:event_jMenuItem4ActionPerformed

private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
    this.CloseApplication();
}//GEN-LAST:event_jMenuItem2ActionPerformed

private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
    nuevaOntologia();
}//GEN-LAST:event_jMenuItem7ActionPerformed

private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
// Abrir ontologia
    abrirOntologia();
}//GEN-LAST:event_jMenuItem6ActionPerformed

    public void abrirOntologia() {
        chooser = new FileChooser(this, true, this.getConfiguration().getOwlDirectory());
        if (chooser.getButton().equals("Cancel")) {
        } else {
            if (!isPanelNuevaOntologiaSelected) {
                setPanelNuevaOntologia(new PanelNuevaOntologia(this, vistaNuevaOntologia));
                ponerPanel(getPanelNuevaOntologia());
                getConfiguration().setDefaultURLOWLViajes(chooser.getPath());
                getPanelNuevaOntologia().setUrlOWL(chooser.getPath());
                getPanelNuevaOntologia().modoCargar();
                labelEstado.setText(Mensajes.ABRIR_ONTOLOGIA);
            }
        }
    }

    public void guardarOntologia() {
        if (isPanelNuevaOntologiaSelected) {
            getPanelNuevaOntologia().guardarOntologia();
        }
    }

private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
    guardarOntologia();
}//GEN-LAST:event_jMenuItem8ActionPerformed

private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
// Cerrar ontologia
    if (!isPanelPrincipalSelected) {
        ponerPanel(getPanelPrincipal());
        labelEstado.setText(Mensajes.PANEL_PRINCIPAL);
    }
}//GEN-LAST:event_jMenuItem9ActionPerformed

private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
// Cargar configuracion
    if (!isPanelConfiguracionSelected) {
        setPanelConfiguracion(new PanelConfiguracion(this, vistaConfiguracion));
        ponerPanel(getPanelConfiguracion());
        labelEstado.setText(Mensajes.CONFIGURACION);
    }
}//GEN-LAST:event_jMenuItem3ActionPerformed

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    nuevaOntologia();
}//GEN-LAST:event_jButton2ActionPerformed

private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    abrirOntologia();
}//GEN-LAST:event_jButton3ActionPerformed

private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    guardarOntologia();
}//GEN-LAST:event_jButton4ActionPerformed

private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
    // Cargar panel default ontologia
    if (!isPanelOntologiaDefaultSelected) {
        setPanelDefaultOntologia(new PanelDefaultOntologia(this, vistaDefaultOntologia));
        ponerPanel(getPanelDefaultOntologia());
        labelEstado.setText(Mensajes.ONTOLOGIA_DEFAULT);
    }
}//GEN-LAST:event_jMenuItem11ActionPerformed

private void buttonBuscarVueloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBuscarVueloActionPerformed
    if (!isPanelMotorBusquedaSelected) {
        setPanelMotorBusqueda(new PanelMotorBusqueda(this, vistaMotorBusqueda));
        getPanelMotorBusqueda().getJTabbedPane1().setSelectedIndex(0);
        ponerPanel(getPanelMotorBusqueda());
        labelEstado.setText(Mensajes.NUEVA_BUSQUEDA);
    }
}//GEN-LAST:event_buttonBuscarVueloActionPerformed

private void buttonBuscarHotelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBuscarHotelActionPerformed
    if (!isPanelMotorBusquedaSelected) {
        setPanelMotorBusqueda(new PanelMotorBusqueda(this, vistaMotorBusqueda));
        getPanelMotorBusqueda().getJTabbedPane1().setSelectedIndex(1);
        ponerPanel(getPanelMotorBusqueda());
        labelEstado.setText(Mensajes.NUEVA_BUSQUEDA);
    }
}//GEN-LAST:event_buttonBuscarHotelActionPerformed

private void buttonBuscarAutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBuscarAutoActionPerformed
    if (!isPanelMotorBusquedaSelected) {
        setPanelMotorBusqueda(new PanelMotorBusqueda(this, vistaMotorBusqueda));
        getPanelMotorBusqueda().getJTabbedPane1().setSelectedIndex(2);
        ponerPanel(getPanelMotorBusqueda());
        labelEstado.setText(Mensajes.NUEVA_BUSQUEDA);
    }
}//GEN-LAST:event_buttonBuscarAutoActionPerformed

    public void nuevaOntologia() {
        if (!isPanelNuevaOntologiaSelected) {
            setPanelNuevaOntologia(new PanelNuevaOntologia(this, vistaNuevaOntologia));
            ponerPanel(getPanelNuevaOntologia());
            getPanelNuevaOntologia().modoNuevo();
            labelEstado.setText(Mensajes.NUEVA_ONTOLOGIA);
        }
    }

    private void setLookAndFeel() throws HeadlessException {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
            Toolkit t = Toolkit.getDefaultToolkit();
            this.setLocation((int) (t.getScreenSize().getWidth() - this.getWidth()) / 2, (int) (t.getScreenSize().getHeight() - this.getHeight()) / 2);
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
        if (isPanelPrincipalSelected) {
            this.remove(getPanelPrincipal());
            this.repaint();
        }
        if (isPanelMotorBusquedaSelected) {
            this.remove(getPanelMotorBusqueda());
            this.repaint();
        }
        if (isPanelSinonimosSelected) {
            this.remove(getPanelSinonimos());
            this.repaint();
        }
        if (isPanelNuevaOntologiaSelected) {
            this.remove(getPanelNuevaOntologia());
            this.repaint();
        }
        if (isPanelConfiguracionSelected) {
            this.remove(getPanelConfiguracion());
            this.repaint();
        }
        if (isPanelOntologiaDefaultSelected) {
            this.remove(getPanelDefaultOntologia());
            this.repaint();
        }

        if (panel instanceof PanelDefaultOntologia) {
            this.isPanelOntologiaDefaultSelected = true;
            this.isPanelConfiguracionSelected = false;
            this.isPanelPrincipalSelected = false;
            this.isPanelSinonimosSelected = false;
            this.isPanelNuevaOntologiaSelected = false;
            this.setActivePanel(getPanelDefaultOntologia());
            this.isPanelMotorBusquedaSelected = false;
        } else if (panel instanceof PanelConfiguracion) {
            this.isPanelOntologiaDefaultSelected = false;
            this.isPanelConfiguracionSelected = true;
            this.isPanelPrincipalSelected = false;
            this.isPanelSinonimosSelected = false;
            this.isPanelNuevaOntologiaSelected = false;
            this.setActivePanel(getPanelConfiguracion());
            this.isPanelMotorBusquedaSelected = false;
        } else if (panel instanceof PanelMotorBusqueda) {
            this.isPanelOntologiaDefaultSelected = false;
            this.isPanelConfiguracionSelected = false;
            this.isPanelPrincipalSelected = false;
            this.isPanelSinonimosSelected = false;
            this.isPanelNuevaOntologiaSelected = false;
            this.setActivePanel(getPanelMotorBusqueda());
            this.isPanelMotorBusquedaSelected = true;
        } else if (panel instanceof PanelSinonimos) {
            this.isPanelOntologiaDefaultSelected = false;
            this.isPanelConfiguracionSelected = false;
            this.isPanelPrincipalSelected = false;
            this.isPanelMotorBusquedaSelected = false;
            this.isPanelNuevaOntologiaSelected = false;
            this.setActivePanel(getPanelSinonimos());
            this.isPanelSinonimosSelected = true;
        } else if (panel instanceof PanelNuevaOntologia) {
            this.isPanelOntologiaDefaultSelected = false;
            this.isPanelConfiguracionSelected = false;
            this.isPanelPrincipalSelected = false;
            this.isPanelMotorBusquedaSelected = false;
            this.isPanelSinonimosSelected = false;
            this.setActivePanel(getPanelSinonimos());
            this.isPanelNuevaOntologiaSelected = true;
        } else {
            this.isPanelOntologiaDefaultSelected = false;
            this.isPanelConfiguracionSelected = false;
            this.isPanelPrincipalSelected = true;
            this.isPanelMotorBusquedaSelected = false;
            this.isPanelSinonimosSelected = false;
            this.isPanelNuevaOntologiaSelected = false;
            this.setActivePanel(getPanelPrincipal());
        }



        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(itemsToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 759, Short.MAX_VALUE).addComponent(statusToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 759, Short.MAX_VALUE).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(itemsToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, Short.MAX_VALUE).addComponent(statusToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)));
    //Esto hace que la aplicacion aparezca maximizada.
    //     this.setExtendedState(JFrame.MAXIMIZED_BOTH);

    }

    public PanelMotorBusqueda getPanelMotorBusqueda() {
        return panelMotorBusqueda;
    }

    public void setPanelMotorBusqueda(PanelMotorBusqueda panelMotorBusqueda) {
        this.panelMotorBusqueda = panelMotorBusqueda;
    }

    public PanelSinonimos getPanelSinonimos() {
        return panelSinonimos;
    }

    public void setPanelSinonimos(PanelSinonimos panelSinonimos) {
        this.panelSinonimos = panelSinonimos;
    }

    public PanelNuevaOntologia getPanelNuevaOntologia() {
        return panelNuevaOntologia;
    }

    public void setPanelNuevaOntologia(PanelNuevaOntologia panelNuevaOntologia) {
        this.panelNuevaOntologia = panelNuevaOntologia;
    }

    public boolean isIsPanelNuevaOntologiaSelected() {
        return isPanelNuevaOntologiaSelected;
    }

    public void setIsPanelNuevaOntologiaSelected(boolean isPanelNuevaOntologiaSelected) {
        this.isPanelNuevaOntologiaSelected = isPanelNuevaOntologiaSelected;
    }//Thread para cargar el progressBar
    Thread hilo;
    Object objeto = new Object();
    boolean pideParar = false;

    public boolean esImpar(int iNumero) {
        if (iNumero % 2 != 0) {
            return true;
        } else {
            return false;
        }
    }

    public void iniciaCuenta() {
        if (hilo == null) {
            hilo = new ThreadCarga();
            pideParar = false;
            hilo.start();
        }
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public javax.swing.JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(javax.swing.JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public boolean isIsPanelConfiguracionSelected() {
        return isPanelConfiguracionSelected;
    }

    public void setIsPanelConfiguracionSelected(boolean isPanelConfiguracion) {
        this.isPanelConfiguracionSelected = isPanelConfiguracion;
    }

    public PanelConfiguracion getPanelConfiguracion() {
        return panelConfiguracion;
    }

    public void setPanelConfiguracion(PanelConfiguracion panelConfiguracion) {
        this.panelConfiguracion = panelConfiguracion;
    }

    /**
     * @return the panelDefaultOntologia
     */
    public PanelDefaultOntologia getPanelDefaultOntologia() {
        return panelDefaultOntologia;
    }

    /**
     * @param panelDefaultOntologia the panelDefaultOntologia to set
     */
    public void setPanelDefaultOntologia(PanelDefaultOntologia panelDefaultOntologia) {
        this.panelDefaultOntologia = panelDefaultOntologia;
    }

    class ThreadCarga extends Thread {

        public void run() {
            int min = 0;
            int max = 100;
            int aux = 1;
            jProgressBar1.setValue(min);
            jProgressBar1.setMinimum(min);
            jProgressBar1.setMaximum(max);
            while (true) {
                if (esImpar(aux)) {
                    for (int i = min; i <= max; i++) {
                        jProgressBar1.setValue(i);
                        repaint();
                        synchronized (objeto) {
                            if (pideParar) {
                                break;
                            }
                            try {
                                objeto.wait(10);
                            } catch (InterruptedException e) {
                                // Se ignoran las excepciones
                            }
                        }
                    }
                } else {
                    for (int i = max; i >= min; i--) {
                        jProgressBar1.setValue(i);
                        repaint();
                        synchronized (objeto) {
                            if (pideParar) {
                                break;
                            }
                            try {
                                objeto.wait(10);
                            } catch (InterruptedException e) {
                                // Se ignoran las excepciones
                            }
                        }
                    }
                }
                aux++;
            }
        //hilo = null;
        }
    }// Termina el kilombo del progressbar
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBuscarAuto;
    private javax.swing.JButton buttonBuscarHotel;
    private javax.swing.JButton buttonBuscarVuelo;
    private javax.swing.JButton buttonSetting;
    private javax.swing.JToolBar itemsToolBar;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JLabel labelEstado;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JToolBar statusToolBar;
    // End of variables declaration//GEN-END:variables
}
