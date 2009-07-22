/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package prueba.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

/**
 *
 * @author brunoli
 */
public class SelectThread implements Runnable{

    private String table;
    private Statement stmt;
    private PreparedStatement ps;
    private MySQLConfiguration conf;

    public SelectThread(MySQLConfiguration conf, String table) {
        this.table = table;
        this.conf = conf;
    }



    public void run() {
        try {
            int aux = 0;
            int aux2 = 0;
            MySQLDBManager manager = new MySQLDBManager(conf);
            manager.connect();
            long lapso = (long) (Math.random() * 100 + 100);
            int ifIndex = (int) (Math.random() * 10);
            double threshold = 0.2;
            int limite = 100;
            String consulta = "SELECT sum(serviceOctetsPassed) / " + lapso + " * 60 * 8 / 256 AS consumption, CMipAddress, CMmacAddress " +
                            "FROM " + table +
                            " WHERE timestamp >= ? and CMTScatvIfIndex = ? GROUP BY CMipAddress HAVING consumption > ? ORDER BY consumption DESC LIMIT ?;";
                    
            while (true) {
                try {
                    ps = manager.getCon().prepareStatement(consulta);
                    ps.setLong(1, Calendar.getInstance().getTimeInMillis() - lapso);
                    ps.setInt(2, ifIndex);
                    ps.setDouble(3, threshold);
                    ps.setInt(4, limite);
                    ps.executeQuery();
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("ERROR SELECT EN TABLA " + table);
                }
                aux++;
                aux2++;
                if (aux > 20) {
                    System.out.println(aux2 + " SELECT EN LA TABLA " + table);
                    aux = 0;
                }
            }
        } catch (SQLException ex) {
            
        }
    }

}
