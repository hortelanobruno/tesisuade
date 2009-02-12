/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package varios;

import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 *
 * @author Admin
 */
public class FileWriterWrapper {

    private String file;

    public FileWriterWrapper(String file) {
        this.file = file;
    }

    public void write(String contenido) {
        try {
            PrintStream printStream = new PrintStream(new FileOutputStream(file));
            printStream.print(contenido);
            printStream.close();
        } catch (Exception e) {
            System.err.println("Error al escribir en el archivo");
        }
    }
}
