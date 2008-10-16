package Varios;

import java.io.FileOutputStream;
import java.io.PrintStream;

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
