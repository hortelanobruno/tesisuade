/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package prueba.db;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author brunoli
 */
public class RunClassTestSelects {

    private Statement stmt;
    private PreparedStatement ps;
    private ExecutorService executorService;
    private MySQLConfiguration conf;
    private MySQLDBManager dbmanager;

    public RunClassTestSelects() {
        conf = new MySQLConfiguration();
        conf.setDatabase("prueba");
        conf.setIp("localhost");
        conf.setPassword("root");
        conf.setPort("3306");
        conf.setUser("root");

        executorService = Executors.newFixedThreadPool(2);
        SelectThread thread1 = new SelectThread(conf, "pruebaINNODB");
        SelectThread thread2 = new SelectThread(conf, "pruebaMYISAM");
        executorService.execute(thread1);
        executorService.execute(thread2);
    }

    

    public static void main(String[] args) {
        new RunClassTestSelects();
    }
}
