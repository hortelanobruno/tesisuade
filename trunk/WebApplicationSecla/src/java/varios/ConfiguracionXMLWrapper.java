package varios;



import com.thoughtworks.xstream.*;

import db.ConexionVO;

public class ConfiguracionXMLWrapper {

	public ConfiguracionXMLWrapper() {
		// TODO Auto-generated constructor stub
	}
	
	
	public void parseXMLSolFab(ConexionVO con) {
		XStream xstream = new XStream();		
		xstream.alias("configuration", ConexionVO.class);
		String config = xstream.toXML(con);
		String file = "c:/conf.xml";
		FileWriterWrapper fileWriter = new FileWriterWrapper(file);
		fileWriter.write(config);
	}
	
	public ConexionVO parseConfiguracion(String url){
		FileReaderWrapper fileReader = new FileReaderWrapper(url);
        String cc = fileReader.obtenerContenido();
        XStream xstreamm = new XStream();
        xstreamm.alias("conexion", ConexionVO.class);
        ConexionVO con = null;
        try{
            con = (ConexionVO) xstreamm.fromXML(cc);
            return con;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
	}
}
