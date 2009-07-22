/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba.db;


import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.PooledConnection;

/**
 *
 * @author brunoli
 */
public class MySQLDBManager {

    protected MySQLConfiguration configuration;
    private Connection con;
    private static final String ODBC_DRIVER = "com.mysql.jdbc.Driver";
    private String url;
    private MysqlConnectionPoolDataSource ds = null;
    private PooledConnection connectionPool = null;

    public MySQLDBManager(MySQLConfiguration configuration) {
        this.configuration = configuration;
    }

    public void connect() throws SQLException {
        driverOdbc();
        url = "jdbc:mysql://" + configuration.getIp() + ":" + configuration.getPort() + "/" + configuration.getDatabase();
        con = java.sql.DriverManager.getConnection(url, configuration.getUser(), configuration.getPassword());
    }

    public void connectPool() throws SQLException{
        if (ds == null) {
			ds = new MysqlConnectionPoolDataSource();
			ds.setUrl("jdbc:mysql://" + configuration.getIp() + ":" + configuration.getPort() + "/" + configuration.getDatabase());
			ds.setUser(configuration.getUser());
			ds.setPassword(configuration.getPassword());
			ds.setAutoReconnect(true);
			connectionPool = ds.getPooledConnection();
		}
		con = connectionPool.getConnection();
		con.setAutoCommit(false);
    }

    public void disconnect() throws SQLException {
        try {
            getCon().close();
        } catch (Exception e) {
        }
    }

    public static void driverOdbc() {
        try {
            Class.forName(ODBC_DRIVER);
        } catch (ClassNotFoundException e) {
        }
    }

    /**
     * @return the con
     */
    public Connection getCon() {
        return con;
    }
}

