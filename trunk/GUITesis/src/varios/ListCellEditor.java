/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package varios;

import java.awt.Component;
import javax.swing.CellEditor;
import javax.swing.JList;

/**
 *
 * @author Administrador
 */
public interface ListCellEditor extends CellEditor{ 
    Component getListCellEditorComponent(JList list, Object value, 
                                          boolean isSelected, 
                                          int index); 
}
