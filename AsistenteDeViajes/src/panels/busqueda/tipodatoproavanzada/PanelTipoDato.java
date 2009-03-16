/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package panels.busqueda.tipodatoproavanzada;

/**
 *
 * @author Brunoli
 */
public interface PanelTipoDato {

    public Object getValue();
    public String getNombre();
    public boolean isActived();
    public boolean checkInput();
    public void clearData();
}
