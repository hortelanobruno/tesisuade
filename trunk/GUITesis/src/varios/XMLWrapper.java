/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package varios;


import com.thoughtworks.xstream.XStream;
import configuration.Configuration;

/**
 *
 * @author Admin
 */
public class XMLWrapper {

    public XMLWrapper() {
    }

    public Configuration parseConfiguracion(String url) {
        FileReaderWrapper fileReader = new FileReaderWrapper(url);
        String config = fileReader.obtenerContenido();
        XStream xstream = new XStream();
        xstream.alias("configuration", Configuration.class);
        Configuration configuration = null;
        try {
            configuration = (Configuration) xstream.fromXML(config);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return configuration;
    }

    public void parseXMLSolFab(Configuration conf) {
        XStream xstream = new XStream();
        xstream.alias("configuration", Configuration.class);

        String config = xstream.toXML(conf);

        //Escribo la salida en un archivo
        String file = Constantes.CONFIGURATION_URL;
        FileWriterWrapper fileWriter = new FileWriterWrapper(file);
        fileWriter.write(config);
    }
}
