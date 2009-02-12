/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package varios;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Administrador
 */
public class OWLFilter extends FileFilter{

    final static String owl = "owl";
    
    public String getDescription() 
    {
        return "Archivos OWL";
    }

    
    public boolean accept(File f) {
        if (f.isDirectory()) return true;

        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) 
        {
            String extension = s.substring(i+1).toLowerCase();
            if (owl.equals(extension)) return true;
            else return false; 
        }

        return false;
    }
}
