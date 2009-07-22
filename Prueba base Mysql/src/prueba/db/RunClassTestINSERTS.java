/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package prueba.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author brunoli
 */
public class RunClassTestINSERTS {

    private Statement stmt;
    private PreparedStatement ps;
    private ExecutorService executorService;
    private MySQLConfiguration conf;
    private MySQLDBManager dbmanager;

    public RunClassTestINSERTS() {
        //Creo tablas;
        createTables();
        inserts();
    }


    public static void main(String[] args) {
        new RunClassTestINSERTS();
    }

    private void createTables() {
        try {
            conf = new MySQLConfiguration();
            conf.setDatabase("pruebaperformance");
            conf.setIp("localhost");
            conf.setPassword("root");
            conf.setPort("3306");
            conf.setUser("root");
            
            dbmanager = new MySQLDBManager(conf);
            dbmanager.connect();
            String create = "create table IF NOT EXISTS pruebaINNODB (timestamp BIGINT, CMTShostname VARCHAR(50),CMTSipAddress VARCHAR(50)," + "CMTSsysUpTime BIGINT,CMTScatvIfName VARCHAR(50),CMTScatvIfIndex BIGINT," + "CMTSupIfName VARCHAR(50),CMTSupIfType INT,CMTSdownIfName VARCHAR(50)," + "CMmacAddress VARCHAR(50),CMdocsisMode INT,CMipAddress VARCHAR(50)," + "CMcpeIpv4List VARCHAR(50),RecType INT,RecCreationTime VARCHAR(50)," + "serviceIdentifier BIGINT,gateId BIGINT,serviceClassName VARCHAR(50)," + "serviceDirection INT,serviceOctetsPassed BIGINT,servicePktsPassed BIGINT," + "serviceSlaDropPkts BIGINT,serviceSlaDelayPkts BIGINT,serviceTimeCreated BIGINT," + "serviceTimeActive BIGINT, primary key(timestamp,CMmacAddress))ENGINE=innodb;";
            stmt = dbmanager.getCon().createStatement();
            stmt.execute(create);
            create = "create table IF NOT EXISTS pruebaMYISAM (timestamp BIGINT, CMTShostname VARCHAR(50),CMTSipAddress VARCHAR(50)," + "CMTSsysUpTime BIGINT,CMTScatvIfName VARCHAR(50),CMTScatvIfIndex BIGINT," + "CMTSupIfName VARCHAR(50),CMTSupIfType INT,CMTSdownIfName VARCHAR(50)," + "CMmacAddress VARCHAR(50),CMdocsisMode INT,CMipAddress VARCHAR(50)," + "CMcpeIpv4List VARCHAR(50),RecType INT,RecCreationTime VARCHAR(50)," + "serviceIdentifier BIGINT,gateId BIGINT,serviceClassName VARCHAR(50)," + "serviceDirection INT,serviceOctetsPassed BIGINT,servicePktsPassed BIGINT," + "serviceSlaDropPkts BIGINT,serviceSlaDelayPkts BIGINT,serviceTimeCreated BIGINT," + "serviceTimeActive BIGINT, primary key(timestamp,CMmacAddress))ENGINE=myisam;";
            stmt = dbmanager.getCon().createStatement();
            stmt.execute(create);
            stmt.close();
        } catch (SQLException ex) {
            
        }
    }

    private void inserts() {
        executorService = Executors.newFixedThreadPool(2);
        InsertThread thread1 = new InsertThread(conf, "pruebaINNODB");
        InsertThread thread2 = new InsertThread(conf, "pruebaMYISAM");
        executorService.execute(thread1);
        executorService.execute(thread2);
    }
}
