/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package varios.components;

import javax.swing.DefaultListModel;

/**
 *
 * @author Administrador
 */
public class DefaultMutableListModel extends DefaultListModel implements MutableListModel{ 
    public boolean isCellEditable(int index){ 
        return true; 
    } 
 
    public void setValueAt(Object value, int index){ 
        super.setElementAt(value, index); 
    } 
} 
