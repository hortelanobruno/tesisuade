/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package varios;


import com.thoughtworks.xstream.XStream;
import configuration.AdvancedProperty;
import configuration.Configuration;
import configuration.TipoDato;
import configuration.defaultontology.DefaultOntology;
import configuration.defaultontology.types.DefaultAlojamiento;
import configuration.defaultontology.types.DefaultProperty;
import configuration.defaultontology.types.DefaultTranslado;
import configuration.defaultontology.types.DefaultViaje;

/**
 *
 * @author Admin
 */
public class XMLWrapper {

    public XMLWrapper() {
    }

    public Configuration leerConfiguracion(String url) {
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

    public void guardarConfiguracion(Configuration conf) {
        XStream xstream = new XStream();
        xstream.alias("configuration", Configuration.class);
        xstream.alias("tipodato", TipoDato.class);
        xstream.alias("advancedproperty", AdvancedProperty.class);
        xstream.alias("defaultontology", DefaultOntology.class);
        xstream.alias("defaultviaje", DefaultViaje.class);
        xstream.alias("defaulttranslado", DefaultTranslado.class);
        xstream.alias("defaultproperty", DefaultProperty.class);
        xstream.alias("defaultalojamiento", DefaultAlojamiento.class);

        String config = xstream.toXML(conf);

        //Escribo la salida en un archivo
        String file = Constantes.CONFIGURATION_URL;
        FileWriterWrapper fileWriter = new FileWriterWrapper(file);
        fileWriter.write(config);
    }
}
