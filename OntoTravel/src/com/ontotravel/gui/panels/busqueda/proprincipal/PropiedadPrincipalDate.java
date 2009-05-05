/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PanelDate.java
 *
 * Created on 15/03/2009, 21:30:27
 */

package com.ontotravel.gui.panels.busqueda.proprincipal;

import java.util.Calendar;

/**
 *
 * @author Brunoli
 */
public class PropiedadPrincipalDate extends javax.swing.JPanel implements PropiedadPrincipal {

    private String nombrePropiedad;
    /** Creates new form PanelDate */
    public PropiedadPrincipalDate(String nombre) {
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
        dateChooserValor = new com.toedter.calendar.JDateChooser();

        setMaximumSize(new java.awt.Dimension(264, 20));
        setMinimumSize(new java.awt.Dimension(264, 20));

        labelNombre.setText("Nomber:");
        labelNombre.setName("labelNombre"); // NOI18N

        dateChooserValor.setName("dateChooserValor"); // NOI18N
        dateChooserValor.setPreferredSize(new java.awt.Dimension(100, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateChooserValor, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dateChooserValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(labelNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser dateChooserValor;
    private javax.swing.JLabel labelNombre;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getNombrePropiedad() {
        return nombrePropiedad;
    }

    @Override
    public Object getValor() {
        if(dateChooserValor.getCalendar() == null){
            return null;
        }else{
            return formatearFecha(dateChooserValor.getCalendar());
        }
    }

    @Override
    public void clearData() {
        dateChooserValor.cleanup();
    }

    @Override
    public boolean checkInput() {
        if(dateChooserValor.getCalendar() == null){
            return false;
        }
        return true;
    }

    private void ponerNombre(String nombre) {
        nombre = nombre.replace("_", " ");
        nombre += ":";
        labelNombre.setText(nombre);
    }

    private String formatearFecha(Calendar date){
        int year = date.getTime().getYear()+1900;
        int month = date.getTime().getMonth()+1;
        if(month<10){
            if(date.getTime().getDate()<10){
                //return year+"-0"+month+"-0"+date.getTime().getDate();
                return "0"+date.getTime().getDate()+"/0"+month+"/"+year;
            }else{
                //return year+"-0"+month+"-"+date.getTime().getDate();
                return date.getTime().getDate()+"/0"+month+"/"+year;
            }
        }else{
            if(date.getTime().getDate()<10){
                //return year+"-"+month+"-0"+date.getTime().getDate();
                return "0"+date.getTime().getDate()+"/"+month+"/"+year;
            }else{
                //return year+"-"+month+"-"+date.getTime().getDate();
                return date.getTime().getDate()+"/"+month+"/"+year;
            }
        }
    }

}
