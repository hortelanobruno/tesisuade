/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package varios;

import javax.swing.ListModel;

/**
 *
 * @author Administrador
 */
public interface MutableListModel extends ListModel{ 
    public boolean isCellEditable(int index); 
    public void setValueAt(Object value, int index); 
} 
