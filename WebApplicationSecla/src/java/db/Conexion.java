package db;
import java.sql.Connection;

import varios.ConfiguracionXMLWrapper;



public class Conexion {
	
    private ConexionVO conexionVO;
    private Connection con;
    private static final String ODBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    
    public Conexion() { 
    	conexionVO = new ConexionVO();
    	ConfiguracionXMLWrapper conf = new ConfiguracionXMLWrapper();
    	conexionVO = conf.parseConfiguracion("c:/conf.xml");
    }
    
    public java.sql.Connection getCon() { return con; }
    
    public boolean abrirConexion() {
    	String url = "jdbc:sqlserver://"+conexionVO.getIp()+":"+conexionVO.getPort()+";databaseName="+conexionVO.getDatabase();
        try {
            con = java.sql.DriverManager.getConnection(url,conexionVO.getUsuario(),conexionVO.getPassword());
            return true;
        } catch( java.sql.SQLException e ) { 
        	return false;
        }
    }
    
    public boolean cerrarConexion() {
        try {
            con.close();
            return true;
        } catch( java.sql.SQLException e ) { 
            return false;
        }
    }
    
    public static void driverOdbc() {
        try {
            Class.forName(ODBC_DRIVER);
        } catch( ClassNotFoundException e ) { }
    }
}
