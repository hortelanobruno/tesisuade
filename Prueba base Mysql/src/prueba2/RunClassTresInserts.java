/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba2;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import prueba.db.MySQLConfiguration;
import prueba.db.MySQLDBManager;

/**
 *
 * @author Brunoli
 */
public class RunClassTresInserts {

    private Statement stmt;
    private PreparedStatement ps;
    private ExecutorService executorService;
    private MySQLConfiguration conf;
    private MySQLDBManager dbmanager;
    private String tabla1;
    private String tabla2;
    private String tabla3;
    private String tabla4;
    private String tabla5;
    private String tabla6;
    private String tabla7;

    public RunClassTresInserts() {
        createTables();
        inserts();
    }

    private void createTables() {
        try {
            conf = new MySQLConfiguration();
            conf.setDatabase("pruebaperformance");
            conf.setIp("localhost");
            conf.setPassword("root");
            conf.setPort("3306");
            conf.setUser("root");
            tabla1 = "IPDRHISTORIAL";
            tabla2 = "VIRTUALPIPE1";
            tabla3 = "IPDR1";
            tabla4 = "IPDR2";
            tabla5 = "IPDR3";
            tabla6 = "IPDR4";
            tabla7 = "IPDR5";
            dbmanager = new MySQLDBManager(conf);
            dbmanager.connect();
            String create = "create table IF NOT EXISTS " + tabla1 + " (timestamp BIGINT, CMTShostname VARCHAR(50),CMTSipAddress VARCHAR(50)," + "CMTSsysUpTime BIGINT,CMTScatvIfName VARCHAR(50),CMTScatvIfIndex BIGINT," + "CMTSupIfName VARCHAR(50),CMTSupIfType INT,CMTSdownIfName VARCHAR(50)," + "CMmacAddress VARCHAR(50),CMdocsisMode INT,CMipAddress VARCHAR(50)," + "CMcpeIpv4List VARCHAR(50),RecType INT,RecCreationTime VARCHAR(50)," + "serviceIdentifier BIGINT,gateId BIGINT,serviceClassName VARCHAR(50)," + "serviceDirection INT,serviceOctetsPassed BIGINT,servicePktsPassed BIGINT," + "serviceSlaDropPkts BIGINT,serviceSlaDelayPkts BIGINT,serviceTimeCreated BIGINT," + "serviceTimeActive BIGINT, primary key(timestamp,CMmacAddress))ENGINE=myisam;";
            stmt = dbmanager.getCon().createStatement();
            stmt.execute(create);
            create = "create table IF NOT EXISTS " + tabla2 + " (timestamp BIGINT, CMTShostname VARCHAR(50),CMTSipAddress VARCHAR(50)," + "CMTSsysUpTime BIGINT,CMTScatvIfName VARCHAR(50),CMTScatvIfIndex BIGINT," + "CMTSupIfName VARCHAR(50),CMTSupIfType INT,CMTSdownIfName VARCHAR(50)," + "CMmacAddress VARCHAR(50),CMdocsisMode INT,CMipAddress VARCHAR(50)," + "CMcpeIpv4List VARCHAR(50),RecType INT,RecCreationTime VARCHAR(50)," + "serviceIdentifier BIGINT,gateId BIGINT,serviceClassName VARCHAR(50)," + "serviceDirection INT,serviceOctetsPassed BIGINT,servicePktsPassed BIGINT," + "serviceSlaDropPkts BIGINT,serviceSlaDelayPkts BIGINT,serviceTimeCreated BIGINT," + "serviceTimeActive BIGINT, primary key(timestamp,CMmacAddress))ENGINE=myisam;";
            stmt = dbmanager.getCon().createStatement();
            stmt.execute(create);
            create = "create table IF NOT EXISTS " + tabla3 + " (timestamp BIGINT, CMTShostname VARCHAR(50),CMTSipAddress VARCHAR(50)," + "CMTSsysUpTime BIGINT,CMTScatvIfName VARCHAR(50),CMTScatvIfIndex BIGINT," + "CMTSupIfName VARCHAR(50),CMTSupIfType INT,CMTSdownIfName VARCHAR(50)," + "CMmacAddress VARCHAR(50),CMdocsisMode INT,CMipAddress VARCHAR(50)," + "CMcpeIpv4List VARCHAR(50),RecType INT,RecCreationTime VARCHAR(50)," + "serviceIdentifier BIGINT,gateId BIGINT,serviceClassName VARCHAR(50)," + "serviceDirection INT,serviceOctetsPassed BIGINT,servicePktsPassed BIGINT," + "serviceSlaDropPkts BIGINT,serviceSlaDelayPkts BIGINT,serviceTimeCreated BIGINT," + "serviceTimeActive BIGINT, primary key(timestamp,CMmacAddress))ENGINE=myisam;";
            stmt = dbmanager.getCon().createStatement();
            stmt.execute(create);
            create = "create table IF NOT EXISTS " + tabla4 + " (timestamp BIGINT, CMTShostname VARCHAR(50),CMTSipAddress VARCHAR(50)," + "CMTSsysUpTime BIGINT,CMTScatvIfName VARCHAR(50),CMTScatvIfIndex BIGINT," + "CMTSupIfName VARCHAR(50),CMTSupIfType INT,CMTSdownIfName VARCHAR(50)," + "CMmacAddress VARCHAR(50),CMdocsisMode INT,CMipAddress VARCHAR(50)," + "CMcpeIpv4List VARCHAR(50),RecType INT,RecCreationTime VARCHAR(50)," + "serviceIdentifier BIGINT,gateId BIGINT,serviceClassName VARCHAR(50)," + "serviceDirection INT,serviceOctetsPassed BIGINT,servicePktsPassed BIGINT," + "serviceSlaDropPkts BIGINT,serviceSlaDelayPkts BIGINT,serviceTimeCreated BIGINT," + "serviceTimeActive BIGINT, primary key(timestamp,CMmacAddress))ENGINE=myisam;";
            stmt = dbmanager.getCon().createStatement();
            stmt.execute(create);
            create = "create table IF NOT EXISTS " + tabla5 + " (timestamp BIGINT, CMTShostname VARCHAR(50),CMTSipAddress VARCHAR(50)," + "CMTSsysUpTime BIGINT,CMTScatvIfName VARCHAR(50),CMTScatvIfIndex BIGINT," + "CMTSupIfName VARCHAR(50),CMTSupIfType INT,CMTSdownIfName VARCHAR(50)," + "CMmacAddress VARCHAR(50),CMdocsisMode INT,CMipAddress VARCHAR(50)," + "CMcpeIpv4List VARCHAR(50),RecType INT,RecCreationTime VARCHAR(50)," + "serviceIdentifier BIGINT,gateId BIGINT,serviceClassName VARCHAR(50)," + "serviceDirection INT,serviceOctetsPassed BIGINT,servicePktsPassed BIGINT," + "serviceSlaDropPkts BIGINT,serviceSlaDelayPkts BIGINT,serviceTimeCreated BIGINT," + "serviceTimeActive BIGINT, primary key(timestamp,CMmacAddress))ENGINE=myisam;";
            stmt = dbmanager.getCon().createStatement();
            stmt.execute(create);
            create = "create table IF NOT EXISTS " + tabla6 + " (timestamp BIGINT, CMTShostname VARCHAR(50),CMTSipAddress VARCHAR(50)," + "CMTSsysUpTime BIGINT,CMTScatvIfName VARCHAR(50),CMTScatvIfIndex BIGINT," + "CMTSupIfName VARCHAR(50),CMTSupIfType INT,CMTSdownIfName VARCHAR(50)," + "CMmacAddress VARCHAR(50),CMdocsisMode INT,CMipAddress VARCHAR(50)," + "CMcpeIpv4List VARCHAR(50),RecType INT,RecCreationTime VARCHAR(50)," + "serviceIdentifier BIGINT,gateId BIGINT,serviceClassName VARCHAR(50)," + "serviceDirection INT,serviceOctetsPassed BIGINT,servicePktsPassed BIGINT," + "serviceSlaDropPkts BIGINT,serviceSlaDelayPkts BIGINT,serviceTimeCreated BIGINT," + "serviceTimeActive BIGINT, primary key(timestamp,CMmacAddress))ENGINE=myisam;";
            stmt = dbmanager.getCon().createStatement();
            stmt.execute(create);
            create = "create table IF NOT EXISTS " + tabla7 + " (timestamp BIGINT, CMTShostname VARCHAR(50),CMTSipAddress VARCHAR(50)," + "CMTSsysUpTime BIGINT,CMTScatvIfName VARCHAR(50),CMTScatvIfIndex BIGINT," + "CMTSupIfName VARCHAR(50),CMTSupIfType INT,CMTSdownIfName VARCHAR(50)," + "CMmacAddress VARCHAR(50),CMdocsisMode INT,CMipAddress VARCHAR(50)," + "CMcpeIpv4List VARCHAR(50),RecType INT,RecCreationTime VARCHAR(50)," + "serviceIdentifier BIGINT,gateId BIGINT,serviceClassName VARCHAR(50)," + "serviceDirection INT,serviceOctetsPassed BIGINT,servicePktsPassed BIGINT," + "serviceSlaDropPkts BIGINT,serviceSlaDelayPkts BIGINT,serviceTimeCreated BIGINT," + "serviceTimeActive BIGINT, primary key(timestamp,CMmacAddress))ENGINE=myisam;";
            stmt = dbmanager.getCon().createStatement();
            stmt.execute(create);
            stmt.close();
            dbmanager.disconnect();
        } catch (SQLException ex) {
            System.out.println("ERROR");
        }
    }

    private void inserts() {
        executorService = Executors.newCachedThreadPool();
        InsertThread thread1 = new InsertThread(conf, tabla1, tabla2, tabla3);
        InsertThread thread2 = new InsertThread(conf, tabla1, tabla2, tabla4);
        InsertThread thread3 = new InsertThread(conf, tabla1, tabla2, tabla5);
        InsertThread thread4 = new InsertThread(conf, tabla1, tabla2, tabla6);
        InsertThread thread5 = new InsertThread(conf, tabla1, tabla2, tabla7);
        executorService.execute(thread1);
        executorService.execute(thread2);
        executorService.execute(thread3);
        executorService.execute(thread4);
        executorService.execute(thread5);
    }

    public static void main(String[] args) {
        new RunClassTresInserts();
    }
}
