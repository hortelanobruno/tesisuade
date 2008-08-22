package Varios;



import com.thoughtworks.xstream.XStream;
import configuration.Configuration;
import varios.Constantes;

public class XMLWrapper 
{
	public XMLWrapper() 
	{

	}
	
        public Configuration parseConfiguracion(String url){
            FileReaderWrapper fileReader = new FileReaderWrapper(url);
            String config = fileReader.obtenerContenido();
            XStream xstream = new XStream();
            //xstream.alias("solicituddistribucion", Configuration.class);
            
            Configuration configuration = null;
            try{
                configuration = (Configuration) xstream.fromXML(config);
            }catch(Exception e){}
            return configuration;
        }
        

	public void parseXMLSolFab(Configuration conf) {
		XStream xstream = new XStream();
		
		//xstream.alias("articuloFabrica", XMLArticuloFabrica.class);
		
		String config = xstream.toXML(conf);
		
		//Escribo la salida en un archivo
		String file = Constantes.CONFIGURATION;
		FileWriterWrapper fileWriter = new FileWriterWrapper(file);
		fileWriter.write(config);
	}

	
}
