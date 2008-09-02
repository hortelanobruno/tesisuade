/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package varios.components;

import java.awt.Component;
import java.util.Hashtable;
import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author Administrador
 */
public class IconNodeRenderer extends DefaultTreeCellRenderer {

  public Component getTreeCellRendererComponent(JTree tree, Object value,
      boolean sel, boolean expanded, boolean leaf, int row,
      boolean hasFocus) {

    super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,
        row, hasFocus);

    Icon icon = ((IconNode) value).getIcon();

    if (icon == null) {
      Hashtable icons = (Hashtable) tree.getClientProperty("JTree.icons");
      String name = ((IconNode) value).getIconName();
      if ((icons != null) && (name != null)) {
        icon = (Icon) icons.get(name);
        if (icon != null) {
          setIcon(icon);
        }
      }
    } else {
      setIcon(icon);
    }

    return this;
  }
}
