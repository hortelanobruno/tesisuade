/*
 * PanelMotorBusqueda.java
 *
 * Created on 29 de julio de 2008, 00:42
 */
package com.ontotravel.gui.panels.busqueda;

import com.ontotravel.config.AdvancedProperty;
import com.ontotravel.config.defaultontology.types.DefaultProperty;
import com.ontotravel.gui.FramePrincipal;
import com.ontotravel.gui.panels.busqueda.proprincipal.PropiedadPrincipal;
import com.ontotravel.gui.panels.busqueda.proprincipal.PropiedadPrincipalDate;
import com.ontotravel.gui.panels.busqueda.proprincipal.PropiedadPrincipalInteger;
import com.ontotravel.gui.panels.busqueda.proprincipal.PropiedadPrincipalString;
import com.ontotravel.gui.panels.busqueda.resultado.PanelResultado;
import com.ontotravel.gui.panels.busqueda.resultado.PanelResultadoVuelo;
import com.ontotravel.gui.panels.busqueda.tipodatoproavanzada.PanelAnyType;
import com.ontotravel.gui.panels.busqueda.tipodatoproavanzada.PanelBoolean;
import com.ontotravel.gui.panels.busqueda.tipodatoproavanzada.PanelDate;
import com.ontotravel.gui.panels.busqueda.tipodatoproavanzada.PanelTipoDato;
import com.ontotravel.mvc.controller.ControladorPanelMotorBusqueda;
import com.ontotravel.mvc.model.BusinessDelegate;
import com.ontotravel.mvc.view.VistaMotorBusqueda;
import com.ontotravel.util.Constantes;
import com.ontotravel.vo.search.ConsultaVO;
import com.ontotravel.vo.search.IndividualVO;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author  Administrador
 */
public class PanelMotorBusqueda extends javax.swing.JPanel {

    private FramePrincipal main;
    private VistaMotorBusqueda vistaMotorBusqueda;
    private boolean buscarVuelos;
    private boolean buscarAlojameinto;
    private boolean buscarAuto;
    private List<PanelTipoDato> proAdvVuelo;
    private List<PanelTipoDato> proAdvAlojamiento;
    private List<PanelTipoDato> proAdvAuto;
    private List<PropiedadPrincipal> proVuelo;
    private List<PropiedadPrincipal> proAlojamiento;
    private List<PropiedadPrincipal> proAuto;

    /** Creates new form PanelMotorBusqueda */
    public PanelMotorBusqueda(FramePrincipal main, VistaMotorBusqueda vista) {
        this.main = main;
        this.vistaMotorBusqueda = vista;
        initComponents();
        proAdvAlojamiento = new ArrayList<PanelTipoDato>();
        proAdvVuelo = new ArrayList<PanelTipoDato>();
        proAdvAuto = new ArrayList<PanelTipoDato>();
        proAlojamiento = new ArrayList<PropiedadPrincipal>();
        proVuelo = new ArrayList<PropiedadPrincipal>();
        proAuto = new ArrayList<PropiedadPrincipal>();
        generarOpcionesPrincipales();
        generarOpcionesAvanzadas();
        this.panelOpcionesAvanzadaAlojamiento.setVisible(false);
        this.panelOpcionesAvanzadasAutos.setVisible(false);
        this.panelOpcionesAvanzadasVuelos.setVisible(false);
    }

    private void cargarResultadoAlojamiento(List<IndividualVO> indAlojamiento) {
        //Primero crear el model
        int size = this.main.getConfiguration().getDefaultOntology().getAlojamiento().getDefaultProperties().size();
        size += this.main.getConfiguration().getNombrePropiedadesAvanzadasHotel().size();
        size++;
        String[] columnNames = new String[size];
        int count = 0;
        for (DefaultProperty dp : this.main.getConfiguration().getDefaultOntology().getAlojamiento().getDefaultProperties()) {
            columnNames[count] = new String(dp.getName());
            count++;
        }
        for (String string : this.main.getConfiguration().getNombrePropiedadesAvanzadasHotel()) {
            columnNames[count] = new String(string);
            count++;
        }
        columnNames[count] = "Coincidencia";
        ((DefaultTableModel) tableResultadoAlojamiento.getModel()).setDataVector(new Object[][]{}, columnNames);
        //Cargo el model
        String[] values;
        for (IndividualVO indi : indAlojamiento) {
            count = 0;
            values = new String[size];
            String colName;
            for (int i = 0; i < columnNames.length; i++) {
                colName = columnNames[i];
                if (indi.getPropiedadesPrincipales().containsKey(colName)) {
                    values[count] = (String) indi.getPropiedadesPrincipales().get(colName);
                } else if (indi.getPropiedadesAvanzadas().containsKey(colName)) {
                    values[count] = (String) indi.getPropiedadesAvanzadas().get(colName);
                } else if (colName.equalsIgnoreCase("Coincidencia")) {
                    values[count] = "" + indi.getCoincidencia();
                } else {
                    values[count] = "";
                }
                count++;
            }
            ((DefaultTableModel) tableResultadoAlojamiento.getModel()).addRow(values);
        }
    }

    private void cargarResultadoAuto(List<IndividualVO> indAutos) {
        //Primero crear el model
        int size = this.main.getConfiguration().getDefaultOntology().getTranslado().getDefaultProperties().size();
        size += this.main.getConfiguration().getNombrePropiedadesAvanzadasAuto().size();
        size++;
        String[] columnNames = new String[size];
        int count = 0;
        for (DefaultProperty dp : this.main.getConfiguration().getDefaultOntology().getTranslado().getDefaultProperties()) {
            columnNames[count] = new String(dp.getName());
            count++;
        }
        for (String string : this.main.getConfiguration().getNombrePropiedadesAvanzadasAuto()) {
            columnNames[count] = new String(string);
            count++;
        }
        columnNames[count] = "Coincidencia";
        ((DefaultTableModel) tableResultadoAuto.getModel()).setDataVector(new Object[][]{}, columnNames);
        //Cargo el model
        String[] values;
        for (IndividualVO indi : indAutos) {
            count = 0;
            values = new String[size];
            String colName;
            for (int i = 0; i < columnNames.length; i++) {
                colName = columnNames[i];
                if (indi.getPropiedadesPrincipales().containsKey(colName)) {
                    values[count] = (String) indi.getPropiedadesPrincipales().get(colName);
                } else if (indi.getPropiedadesAvanzadas().containsKey(colName)) {
                    values[count] = (String) indi.getPropiedadesAvanzadas().get(colName);
                } else if (colName.equalsIgnoreCase("Coincidencia")) {
                    values[count] = "" + indi.getCoincidencia();
                } else {
                    values[count] = "";
                }
                count++;
            }
            ((DefaultTableModel) tableResultadoAuto.getModel()).addRow(values);
        }
    }

    private void cargarResultadoVuelo(List<IndividualVO> indVuelos) {
        //Primero crear el model
        int size = this.main.getConfiguration().getDefaultOntology().getViaje().getDefaultProperties().size();
        size += this.main.getConfiguration().getNombrePropiedadesAvanzadasVuelo().size();
        size++;
        String[] columnNames = new String[size];
        int count = 0;
        for (DefaultProperty dp : this.main.getConfiguration().getDefaultOntology().getViaje().getDefaultProperties()) {
            columnNames[count] = new String(dp.getName());
            count++;
        }
        for (String string : this.main.getConfiguration().getNombrePropiedadesAvanzadasVuelo()) {
            columnNames[count] = new String(string);
            count++;
        }
        columnNames[count] = "Coincidencia";
        ((DefaultTableModel) tableResultadoVuelo.getModel()).setDataVector(new Object[][]{}, columnNames);
        //Cargo el model
        String[] values;
        for (IndividualVO indi : indVuelos) {
            count = 0;
            values = new String[size];
            String colName;
            for (int i = 0; i < columnNames.length; i++) {
                colName = columnNames[i];
                if (indi.getPropiedadesPrincipales().containsKey(colName)) {
                    values[count] = (String) indi.getPropiedadesPrincipales().get(colName);
                } else if (indi.getPropiedadesAvanzadas().containsKey(colName)) {
                    values[count] = (String) indi.getPropiedadesAvanzadas().get(colName);
                } else if (colName.equalsIgnoreCase("Coincidencia")) {
                    values[count] = "" + indi.getCoincidencia();
                } else {
                    values[count] = "";
                }
                count++;
            }
            ((DefaultTableModel) tableResultadoVuelo.getModel()).addRow(values);
        }
    }

    private void cargoPropiedadPrincipal(DefaultProperty prop, JPanel panelAlojamiento, List<PropiedadPrincipal> proAlojamiento) {
        PropiedadPrincipal panel = null;
        switch (prop.getTipoDato()) {
            case DATE:
                panel = new PropiedadPrincipalDate(prop.getName());
                break;
            case INTEGER:
                panel = new PropiedadPrincipalInteger(prop.getName());
                break;
            case STRING:
                panel = new PropiedadPrincipalString(prop.getName());
                break;
        }

        proAlojamiento.add(panel);
        panelAlojamiento.add((JPanel) panel);
    }

    private void cargoPropiedadAvanzada(AdvancedProperty pro, JPanel panelOpcionesAvanzadasVuelosDefault, List<PanelTipoDato> paneles) {
        PanelTipoDato panel = null;
        switch (pro.getTipoDato()) {
            case ANY:
                panel = new PanelAnyType(pro.getNombre(), pro.getTipoDato());
                break;
            case BOOLEAN:
                panel = new PanelBoolean(pro.getNombre());
                break;
            case DATE:
                panel = new PanelDate(pro.getNombre());
                break;
            case DOUBLE:
                panel = new PanelAnyType(pro.getNombre(), pro.getTipoDato());
                break;
            case FLOAT:
                panel = new PanelAnyType(pro.getNombre(), pro.getTipoDato());
                break;
            case INTEGER:
                panel = new PanelAnyType(pro.getNombre(), pro.getTipoDato());
                break;
            case STRING:
                panel = new PanelAnyType(pro.getNombre(), pro.getTipoDato());
                break;
        }
        paneles.add(panel);
        panelOpcionesAvanzadasVuelosDefault.add((JPanel) panel);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        panelMotorBusquedaVuelo = new javax.swing.JPanel();
        panelResultadoVuelo = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableResultadoVuelo = new javax.swing.JTable();
        panelOpcionesAvanzadasVuelos = new javax.swing.JPanel();
        panelVuelo = new javax.swing.JPanel();
        toggleButtonOpcionesAvanzadasVuelo = new javax.swing.JToggleButton();
        buttonBuscarVuelos = new javax.swing.JButton();
        panelMotorBusquedaHotel = new javax.swing.JPanel();
        panelAlojamiento = new javax.swing.JPanel();
        panelOpcionesAvanzadaAlojamiento = new javax.swing.JPanel();
        panelResultadoAlojamiento = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableResultadoAlojamiento = new javax.swing.JTable();
        toggleButtonOpAvAlojamiento = new javax.swing.JToggleButton();
        buttonBuscarAlojamiento = new javax.swing.JButton();
        panelMotorBusquedaAuto = new javax.swing.JPanel();
        panelAutos = new javax.swing.JPanel();
        panelOpcionesAvanzadasAutos = new javax.swing.JPanel();
        panelResultadoAutos = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableResultadoAuto = new javax.swing.JTable();
        toggleButtonOpAvAuto = new javax.swing.JToggleButton();
        buttonBuscarAuto = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        panelResultadoVuelo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelResultadoVuelo.setPreferredSize(new java.awt.Dimension(513, 453));
        panelResultadoVuelo.setRequestFocusEnabled(false);

        tableResultadoVuelo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableResultadoVuelo.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tableResultadoVuelo);

        javax.swing.GroupLayout panelResultadoVueloLayout = new javax.swing.GroupLayout(panelResultadoVuelo);
        panelResultadoVuelo.setLayout(panelResultadoVueloLayout);
        panelResultadoVueloLayout.setHorizontalGroup(
            panelResultadoVueloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 912, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        panelResultadoVueloLayout.setVerticalGroup(
            panelResultadoVueloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        panelOpcionesAvanzadasVuelos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelOpcionesAvanzadasVuelos.setPreferredSize(new java.awt.Dimension(414, 182));
        panelOpcionesAvanzadasVuelos.setLayout(new java.awt.GridLayout(0, 1));

        panelVuelo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelVuelo.setPreferredSize(new java.awt.Dimension(414, 216));
        panelVuelo.setLayout(new java.awt.GridLayout(6, 1));

        toggleButtonOpcionesAvanzadasVuelo.setText("Opciones avanzadas");
        toggleButtonOpcionesAvanzadasVuelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleButtonOpcionesAvanzadasVueloActionPerformed(evt);
            }
        });

        buttonBuscarVuelos.setIcon(new javax.swing.ImageIcon("C:\\Users\\Brunoli\\Documents\\NetBeansProjects\\OntoTravel\\icons\\view.gif")); // NOI18N
        buttonBuscarVuelos.setText("Buscar vuelos");
        buttonBuscarVuelos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBuscarVuelosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelMotorBusquedaVueloLayout = new javax.swing.GroupLayout(panelMotorBusquedaVuelo);
        panelMotorBusquedaVuelo.setLayout(panelMotorBusquedaVueloLayout);
        panelMotorBusquedaVueloLayout.setHorizontalGroup(
            panelMotorBusquedaVueloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMotorBusquedaVueloLayout.createSequentialGroup()
                .addGroup(panelMotorBusquedaVueloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMotorBusquedaVueloLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelVuelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(panelOpcionesAvanzadasVuelos, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelMotorBusquedaVueloLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(buttonBuscarVuelos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(toggleButtonOpcionesAvanzadasVuelo))
                    .addGroup(panelMotorBusquedaVueloLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelResultadoVuelo, javax.swing.GroupLayout.PREFERRED_SIZE, 916, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        panelMotorBusquedaVueloLayout.setVerticalGroup(
            panelMotorBusquedaVueloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMotorBusquedaVueloLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(panelMotorBusquedaVueloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelOpcionesAvanzadasVuelos, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                    .addComponent(panelVuelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelMotorBusquedaVueloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(toggleButtonOpcionesAvanzadasVuelo)
                    .addComponent(buttonBuscarVuelos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelResultadoVuelo, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Vuelos", panelMotorBusquedaVuelo);

        panelAlojamiento.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelAlojamiento.setPreferredSize(new java.awt.Dimension(414, 216));
        panelAlojamiento.setLayout(new java.awt.GridLayout(0, 1));

        panelOpcionesAvanzadaAlojamiento.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelOpcionesAvanzadaAlojamiento.setPreferredSize(new java.awt.Dimension(414, 182));
        panelOpcionesAvanzadaAlojamiento.setLayout(new java.awt.GridLayout(0, 1));

        panelResultadoAlojamiento.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelResultadoAlojamiento.setPreferredSize(new java.awt.Dimension(513, 453));

        tableResultadoAlojamiento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tableResultadoAlojamiento);

        javax.swing.GroupLayout panelResultadoAlojamientoLayout = new javax.swing.GroupLayout(panelResultadoAlojamiento);
        panelResultadoAlojamiento.setLayout(panelResultadoAlojamientoLayout);
        panelResultadoAlojamientoLayout.setHorizontalGroup(
            panelResultadoAlojamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 929, Short.MAX_VALUE)
        );
        panelResultadoAlojamientoLayout.setVerticalGroup(
            panelResultadoAlojamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
        );

        toggleButtonOpAvAlojamiento.setText("Opciones avanzadas");
        toggleButtonOpAvAlojamiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleButtonOpAvAlojamientoActionPerformed(evt);
            }
        });

        buttonBuscarAlojamiento.setIcon(new javax.swing.ImageIcon("C:\\Users\\Brunoli\\Documents\\NetBeansProjects\\OntoTravel\\icons\\view.gif")); // NOI18N
        buttonBuscarAlojamiento.setText("Buscar alojamietno");
        buttonBuscarAlojamiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBuscarAlojamientoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelMotorBusquedaHotelLayout = new javax.swing.GroupLayout(panelMotorBusquedaHotel);
        panelMotorBusquedaHotel.setLayout(panelMotorBusquedaHotelLayout);
        panelMotorBusquedaHotelLayout.setHorizontalGroup(
            panelMotorBusquedaHotelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMotorBusquedaHotelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMotorBusquedaHotelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelResultadoAlojamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 933, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelMotorBusquedaHotelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(buttonBuscarAlojamiento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(toggleButtonOpAvAlojamiento))
                    .addGroup(panelMotorBusquedaHotelLayout.createSequentialGroup()
                        .addComponent(panelAlojamiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelOpcionesAvanzadaAlojamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelMotorBusquedaHotelLayout.setVerticalGroup(
            panelMotorBusquedaHotelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMotorBusquedaHotelLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(panelMotorBusquedaHotelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelAlojamiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelOpcionesAvanzadaAlojamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelMotorBusquedaHotelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(toggleButtonOpAvAlojamiento)
                    .addComponent(buttonBuscarAlojamiento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelResultadoAlojamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Hoteles", panelMotorBusquedaHotel);

        panelAutos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelAutos.setPreferredSize(new java.awt.Dimension(414, 216));
        panelAutos.setLayout(new java.awt.GridLayout(0, 1));

        panelOpcionesAvanzadasAutos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelOpcionesAvanzadasAutos.setPreferredSize(new java.awt.Dimension(414, 182));
        panelOpcionesAvanzadasAutos.setLayout(new java.awt.GridLayout(0, 1));

        panelResultadoAutos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelResultadoAutos.setPreferredSize(new java.awt.Dimension(513, 453));

        tableResultadoAuto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tableResultadoAuto);

        javax.swing.GroupLayout panelResultadoAutosLayout = new javax.swing.GroupLayout(panelResultadoAutos);
        panelResultadoAutos.setLayout(panelResultadoAutosLayout);
        panelResultadoAutosLayout.setHorizontalGroup(
            panelResultadoAutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 929, Short.MAX_VALUE)
        );
        panelResultadoAutosLayout.setVerticalGroup(
            panelResultadoAutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
        );

        toggleButtonOpAvAuto.setText("Opciones avanzadas");
        toggleButtonOpAvAuto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleButtonOpAvAutoActionPerformed(evt);
            }
        });

        buttonBuscarAuto.setIcon(new javax.swing.ImageIcon("C:\\Users\\Brunoli\\Documents\\NetBeansProjects\\OntoTravel\\icons\\view.gif")); // NOI18N
        buttonBuscarAuto.setText("Buscar auto");
        buttonBuscarAuto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBuscarAutoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelMotorBusquedaAutoLayout = new javax.swing.GroupLayout(panelMotorBusquedaAuto);
        panelMotorBusquedaAuto.setLayout(panelMotorBusquedaAutoLayout);
        panelMotorBusquedaAutoLayout.setHorizontalGroup(
            panelMotorBusquedaAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMotorBusquedaAutoLayout.createSequentialGroup()
                .addGroup(panelMotorBusquedaAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelMotorBusquedaAutoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelResultadoAutos, javax.swing.GroupLayout.DEFAULT_SIZE, 933, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelMotorBusquedaAutoLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(buttonBuscarAuto, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(toggleButtonOpAvAuto))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelMotorBusquedaAutoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelAutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(panelOpcionesAvanzadasAutos, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelMotorBusquedaAutoLayout.setVerticalGroup(
            panelMotorBusquedaAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMotorBusquedaAutoLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(panelMotorBusquedaAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelOpcionesAvanzadasAutos, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                    .addComponent(panelAutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelMotorBusquedaAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(toggleButtonOpAvAuto)
                    .addComponent(buttonBuscarAuto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelResultadoAutos, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        jTabbedPane1.addTab("Autos", panelMotorBusquedaAuto);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 958, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void toggleButtonOpcionesAvanzadasVueloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toggleButtonOpcionesAvanzadasVueloActionPerformed
        if (((JToggleButton) evt.getSource()).isSelected()) {
            panelOpcionesAvanzadasVuelos.setVisible(true);
            this.repaint();
        } else {
            panelOpcionesAvanzadasVuelos.setVisible(false);
            for (PanelTipoDato panel : proAdvVuelo) {
                panel.clearData();
            }
            this.repaint();
        }
}//GEN-LAST:event_toggleButtonOpcionesAvanzadasVueloActionPerformed

private void buttonBuscarVuelosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBuscarVuelosActionPerformed
    ((ControladorPanelMotorBusqueda) vistaMotorBusqueda.getControlador()).doBuscarVuelos(true);
}//GEN-LAST:event_buttonBuscarVuelosActionPerformed

private void toggleButtonOpAvAlojamientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toggleButtonOpAvAlojamientoActionPerformed
    if (((JToggleButton) evt.getSource()).isSelected()) {
        panelOpcionesAvanzadaAlojamiento.setVisible(true);
        this.repaint();
    } else {
        panelOpcionesAvanzadaAlojamiento.setVisible(false);
        for (PanelTipoDato panel : proAdvAlojamiento) {
            panel.clearData();
        }
        this.repaint();
    }
}//GEN-LAST:event_toggleButtonOpAvAlojamientoActionPerformed

private void toggleButtonOpAvAutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toggleButtonOpAvAutoActionPerformed
    if (((JToggleButton) evt.getSource()).isSelected()) {
        panelOpcionesAvanzadasAutos.setVisible(true);
        this.repaint();
    } else {
        panelOpcionesAvanzadasAutos.setVisible(false);
        for (PanelTipoDato panel : proAdvAuto) {
            panel.clearData();
        }
        this.repaint();
    }
}//GEN-LAST:event_toggleButtonOpAvAutoActionPerformed

private void buttonBuscarAlojamientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBuscarAlojamientoActionPerformed
    ((ControladorPanelMotorBusqueda) vistaMotorBusqueda.getControlador()).doBuscarAlojamiento(true);
}//GEN-LAST:event_buttonBuscarAlojamientoActionPerformed

private void buttonBuscarAutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBuscarAutoActionPerformed
    ((ControladorPanelMotorBusqueda) vistaMotorBusqueda.getControlador()).doBuscarAuto(true);
}//GEN-LAST:event_buttonBuscarAutoActionPerformed

    public void update() {
        if (isBuscarVuelos()) {
            buscarVuelos();
        }
        if (isBuscarAuto()) {
            buscarAuto();
        }
        if (isBuscarAlojameinto()) {
            buscarAlojamiento();
        }
    }

    private void buscarAlojamiento() {
        if (validarInputAlojamiento()) {
            ConsultaVO hotelVO = obtenerDatosConsultaAlojamiento();
            //TODO Hasta aca esta bien, falta la busqueda y mostrar el resultado
            List<IndividualVO> indVuelos = ((BusinessDelegate) vistaMotorBusqueda.getModelo()).buscarHotel(hotelVO, this.main.getConfiguration().getDefaultOntology());
            if (!indVuelos.isEmpty()) {
                //Mostrar los resultados
                cargarResultadoAlojamiento(indVuelos);
            } else {
                //Mandar cartel diciendo que no se encontro nada
                vaciarTablaAlojamientos();
                JOptionPane.showMessageDialog(this, "La consulta no arrojo ningun resultado", Constantes.APPLICATION_NAME, JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Completar todos los campos obligatorios", Constantes.APPLICATION_NAME, JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarAuto() {
        if (validarInputAuto()) {
            ConsultaVO autoVO = obtenerDatosConsultaAuto();
            //TODO Hasta aca esta bien, falta la busqueda y mostrar el resultado
            List<IndividualVO> indVuelos = ((BusinessDelegate) vistaMotorBusqueda.getModelo()).buscarAutos(autoVO, this.main.getConfiguration().getDefaultOntology());
            if (!indVuelos.isEmpty()) {
                //Mostrar los resultados
                cargarResultadoAuto(indVuelos);
            } else {
                //Mandar cartel diciendo que no se encontro nada
                vaciarTablaAutos();
                JOptionPane.showMessageDialog(this, "La consulta no arrojo ningun resultado", Constantes.APPLICATION_NAME, JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Completar todos los campos obligatorios", Constantes.APPLICATION_NAME, JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarVuelos() {
        if (validarInputVuelo()) {
            ConsultaVO vueloVO = obtenerDatosConsultaVuelo();
            List<IndividualVO> indVuelos = ((BusinessDelegate) vistaMotorBusqueda.getModelo()).buscarVuelos(vueloVO, this.main.getConfiguration().getDefaultOntology());
            if (!indVuelos.isEmpty()) {
                //Mostrar los resultados
                cargarResultadoVuelo(indVuelos);
            } else {
                //Mandar cartel diciendo que no se encontro nada
                vaciarTablaVuelos();
                JOptionPane.showMessageDialog(this, "La consulta no arrojo ningun resultado", Constantes.APPLICATION_NAME, JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            vaciarTablaVuelos();
            JOptionPane.showMessageDialog(this, "Completar todos los campos obligatorios", Constantes.APPLICATION_NAME, JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cargarPanelBusquedaVuelo(PanelResultadoVuelo panel, IndividualVO ind) {
    }

    private void vaciarTablaAlojamientos() {
        ((DefaultTableModel)tableResultadoAlojamiento.getModel()).getDataVector().clear();
        tableResultadoAlojamiento.repaint();
        tableResultadoAlojamiento.invalidate();
        tableResultadoAlojamiento.getParent().validate();
    }

    private void vaciarTablaAutos() {
        ((DefaultTableModel)tableResultadoAuto.getModel()).getDataVector().clear();
        tableResultadoAuto.repaint();
        tableResultadoAuto.invalidate();
        tableResultadoAuto.getParent().validate();
    }

    private void vaciarTablaVuelos() {
        ((DefaultTableModel)tableResultadoVuelo.getModel()).getDataVector().clear();
        tableResultadoVuelo.repaint();
        tableResultadoVuelo.invalidate();
        tableResultadoVuelo.getParent().validate();
    }

    private boolean validarInputAuto() {
        //Primero valido datos principales
        for (PropiedadPrincipal panel : proAuto) {
            if (!panel.checkInput()) {
                return false;
            }
        }
        //Ahora las avanzadas
        for (PanelTipoDato panel : proAdvAuto) {
            if (panel.isActived()) {
                if (!panel.checkInput()) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean validarInputAlojamiento() {
        //Primero valido datos principales
        for (PropiedadPrincipal panel : proAlojamiento) {
            if (!panel.checkInput()) {
                return false;
            }
        }
        //Ahora las avanzadas
        for (PanelTipoDato panel : proAdvAlojamiento) {
            if (panel.isActived()) {
                if (!panel.checkInput()) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean validarInputVuelo() {
        //Primero valido datos principales
        for (PropiedadPrincipal panel : proVuelo) {
            if (!panel.checkInput()) {
                return false;
            }
        }
        //Ahora las avanzadas
//        for (PanelTipoDato panel : proAdvVuelo) {
//            if (panel.isActived()) {
//                if (!panel.checkInput()) {
//                    return false;
//                }
//            }
//        }
        return true;
    }

    private ConsultaVO obtenerDatosConsultaAuto() {
        ConsultaVO auto = new ConsultaVO();
        for (PropiedadPrincipal pro : proAuto) {
            auto.getPropiedadesPrincipales().put(pro.getNombrePropiedad(), pro.getValor());
        }
        for (PanelTipoDato panel : proAdvAuto) {
            if (panel.isActived()) {
                auto.getPropiedadesAvanzadas().put(panel.getNombre(), panel.getValue());
            }
        }
        return auto;
    }

    private ConsultaVO obtenerDatosConsultaAlojamiento() {
        ConsultaVO hotel = new ConsultaVO();
        for (PropiedadPrincipal pro : proAlojamiento) {
            hotel.getPropiedadesPrincipales().put(pro.getNombrePropiedad(), pro.getValor());
        }
        for (PanelTipoDato panel : proAdvAlojamiento) {
            if (panel.isActived()) {
                hotel.getPropiedadesAvanzadas().put(panel.getNombre(), panel.getValue());
            }
        }
        return hotel;
    }

    private ConsultaVO obtenerDatosConsultaVuelo() {
        ConsultaVO vuelo = new ConsultaVO();
        for (PropiedadPrincipal pro : proVuelo) {
            vuelo.getPropiedadesPrincipales().put(pro.getNombrePropiedad(), pro.getValor());
        }
        for (PanelTipoDato panel : proAdvVuelo) {
            if (panel.isActived()) {
                vuelo.getPropiedadesAvanzadas().put(panel.getNombre(), panel.getValue());
            }
        }
        return vuelo;
    }

    private void generarOpcionesPrincipales() {
        List<DefaultProperty> props = this.main.getConfiguration().getDefaultOntology().getAlojamiento().getDefaultProperties();
        for (DefaultProperty prop : props) {
            cargoPropiedadPrincipal(prop, panelAlojamiento, proAlojamiento);
        }
        props = this.main.getConfiguration().getDefaultOntology().getTranslado().getDefaultProperties();
        for (DefaultProperty prop : props) {
            cargoPropiedadPrincipal(prop, panelAutos, proAuto);
        }
        props = this.main.getConfiguration().getDefaultOntology().getViaje().getDefaultProperties();
        for (DefaultProperty prop : props) {
            cargoPropiedadPrincipal(prop, panelVuelo, proVuelo);
        }
    }

    private void generarOpcionesAvanzadas() {
        Vector<AdvancedProperty> advPro = this.main.getConfiguration().getPropiedadesAvanzadasVuelo();
        for (AdvancedProperty pro : advPro) {
            cargoPropiedadAvanzada(pro, panelOpcionesAvanzadasVuelos, proAdvVuelo);
        }
        advPro = this.main.getConfiguration().getPropiedadesAvanzadasHotel();
        for (AdvancedProperty pro : advPro) {
            cargoPropiedadAvanzada(pro, panelOpcionesAvanzadaAlojamiento, proAdvAlojamiento);
        }
        advPro = this.main.getConfiguration().getPropiedadesAvanzadasAuto();
        for (AdvancedProperty pro : advPro) {
            cargoPropiedadAvanzada(pro, panelOpcionesAvanzadasAutos, proAdvAuto);
        }
    }

    public void setVistaMotorBusqueda(VistaMotorBusqueda vista) {
        this.vistaMotorBusqueda = vista;
    }

    public VistaMotorBusqueda getVistaMotorBusqueda() {
        return this.vistaMotorBusqueda;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBuscarAlojamiento;
    private javax.swing.JButton buttonBuscarAuto;
    private javax.swing.JButton buttonBuscarVuelos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel panelAlojamiento;
    private javax.swing.JPanel panelAutos;
    private javax.swing.JPanel panelMotorBusquedaAuto;
    private javax.swing.JPanel panelMotorBusquedaHotel;
    private javax.swing.JPanel panelMotorBusquedaVuelo;
    private javax.swing.JPanel panelOpcionesAvanzadaAlojamiento;
    private javax.swing.JPanel panelOpcionesAvanzadasAutos;
    private javax.swing.JPanel panelOpcionesAvanzadasVuelos;
    private javax.swing.JPanel panelResultadoAlojamiento;
    private javax.swing.JPanel panelResultadoAutos;
    private javax.swing.JPanel panelResultadoVuelo;
    private javax.swing.JPanel panelVuelo;
    private javax.swing.JTable tableResultadoAlojamiento;
    private javax.swing.JTable tableResultadoAuto;
    private javax.swing.JTable tableResultadoVuelo;
    private javax.swing.JToggleButton toggleButtonOpAvAlojamiento;
    private javax.swing.JToggleButton toggleButtonOpAvAuto;
    private javax.swing.JToggleButton toggleButtonOpcionesAvanzadasVuelo;
    // End of variables declaration//GEN-END:variables

    public boolean isBuscarVuelos() {
        return buscarVuelos;
    }

    public void setBuscarVuelos(boolean buscarVuelos) {
        this.buscarVuelos = buscarVuelos;
    }

    /**
     * @return the buscarAlojameinto
     */
    public boolean isBuscarAlojameinto() {
        return buscarAlojameinto;
    }

    /**
     * @param buscarAlojameinto the buscarAlojameinto to set
     */
    public void setBuscarAlojameinto(boolean buscarAlojameinto) {
        this.buscarAlojameinto = buscarAlojameinto;
    }

    /**
     * @return the buscarAuto
     */
    public boolean isBuscarAuto() {
        return buscarAuto;
    }

    /**
     * @param buscarAuto the buscarAuto to set
     */
    public void setBuscarAuto(boolean buscarAuto) {
        this.buscarAuto = buscarAuto;
    }

    /**
     * @return the jTabbedPane1
     */
    public javax.swing.JTabbedPane getJTabbedPane1() {
        return jTabbedPane1;
    }
}

