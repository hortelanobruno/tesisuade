/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author brunoli
 */
public class InsertThread implements Runnable {

    private String table;
    private Statement stmt;
    private PreparedStatement ps;
    private MySQLConfiguration conf;

    public InsertThread(MySQLConfiguration conf, String table) {
        this.table = table;
        this.conf = conf;
    }

    public void run() {
        try {
            int aux = 0;
            int aux2 = 0;
            MySQLDBManager manager = new MySQLDBManager(conf);
            manager.connect();
            IPDRFormat iPDRFormat;
            while (true) {
                try {
                    iPDRFormat = cargarIDPR();
                    ps = manager.getCon().prepareStatement("INSERT INTO " + table +  " VALUES (?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )");
                    ps.setLong(1, Calendar.getInstance().getTimeInMillis());
                    ps.setString(2, iPDRFormat.getCMTShostname());
                    ps.setString(3, iPDRFormat.getCMTSipAddress());
                    ps.setLong(4, iPDRFormat.getCMTSsysUpTime());
                    ps.setString(5, iPDRFormat.getCMTScatvIfName());
                    ps.setLong(6, iPDRFormat.getCMTScatvIfIndex());
                    ps.setString(7, iPDRFormat.getCMTSupIfName());
                    ps.setInt(8, iPDRFormat.getCMTSupIfType());
                    ps.setString(9, iPDRFormat.getCMTSdownIfName());
                    ps.setString(10, iPDRFormat.getCMmacAddress());
                    ps.setInt(11, iPDRFormat.getCMdocsisMode());
                    ps.setString(12, iPDRFormat.getCMipAddress());
                    ps.setString(13, iPDRFormat.getCMcpeIpv4List());
                    ps.setInt(14, iPDRFormat.getRecType());
                    ps.setString(15, iPDRFormat.getRecCreationTime());
                    ps.setLong(16, iPDRFormat.getServiceIdentifier());
                    ps.setLong(17, iPDRFormat.getGateId());
                    ps.setString(18, iPDRFormat.getServiceClassName());
                    ps.setInt(19, iPDRFormat.getServiceDirection());
                    ps.setLong(20, iPDRFormat.getServiceOctetsPassed());
                    ps.setLong(21, iPDRFormat.getServicePktsPassed());
                    ps.setLong(22, iPDRFormat.getServiceSlaDropPkts());
                    ps.setLong(23, iPDRFormat.getServiceSlaDelayPkts());
                    ps.setLong(24, iPDRFormat.getServiceTimeCreated());
                    ps.setLong(25, iPDRFormat.getServiceTimeActive());
                    ps.executeUpdate();
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("ERROR INSERT EN TABLA " + table);
                }
                aux++;
                aux2++;
                if (aux > 500) {
                    System.out.println(aux2 + " INSERT EN LA TABLA " + table);
                    aux = 0;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(InsertThread.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private IPDRFormat cargarIDPR() {
        IPDRFormat ipdr = new IPDRFormat();
        ipdr.setCMTScatvIfIndex(111l);
        ipdr.setCMTScatvIfName("aaaaa");
        ipdr.setCMTSdownIfName("aaa");
        ipdr.setCMTShostname("aaaaa");
        ipdr.setCMTSipAddress("sssss");
        ipdr.setCMTSsysUpTime(11111L);
        ipdr.setCMTSupIfName("ddddd");
        ipdr.setCMTSupIfType(11111);
        ipdr.setCMcpeIpv4List("wwwwwww");
        ipdr.setCMdocsisMode(11111111);
        ipdr.setCMipAddress("qqqqqq");
        ipdr.setCMmacAddress("eeeeee"+Math.random()*12341234);
        ipdr.setGateId(22222222L);
        ipdr.setRecCreationTime("ggggggg");
        ipdr.setRecType(2222222);
        ipdr.setServiceClassName("nnnnnnnn");
        ipdr.setServiceDirection(2222222);
        ipdr.setServiceIdentifier(222222L);
        ipdr.setServiceOctetsPassed(22222L);
        ipdr.setServicePktsPassed(22222L);
        ipdr.setServiceSlaDelayPkts(3333333L);
        ipdr.setServiceSlaDropPkts(222L);
        ipdr.setServiceTimeActive(222222L);
        ipdr.setServiceTimeCreated(33333L);
        return ipdr;
    }
}
