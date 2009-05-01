/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ontotravel.util;

import com.ontotravel.config.AdvancedProperty;
import com.ontotravel.config.Configuration;
import com.ontotravel.config.TipoDato;
import com.ontotravel.config.defaultontology.DefaultOntology;
import com.ontotravel.config.defaultontology.types.DefaultAlojamiento;
import com.ontotravel.config.defaultontology.types.DefaultProperty;
import com.ontotravel.config.defaultontology.types.DefaultTranslado;
import com.ontotravel.config.defaultontology.types.DefaultViaje;
import com.thoughtworks.xstream.XStream;


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
        xstream.alias("tipodato", TipoDato.class);
        xstream.alias("advancedproperty", AdvancedProperty.class);
        xstream.alias("defaultontology", DefaultOntology.class);
        xstream.alias("defaultviaje", DefaultViaje.class);
        xstream.alias("defaulttranslado", DefaultTranslado.class);
        xstream.alias("defaultproperty", DefaultProperty.class);
        xstream.alias("defaultalojamiento", DefaultAlojamiento.class);
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
