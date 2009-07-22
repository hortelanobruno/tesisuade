/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba2;

import prueba.db.*;
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

    private Statement stmt;
    private PreparedStatement ps;
    private MySQLConfiguration conf;
    private String tabla1;
    private String tabla2;
    private String tabla3;

    public InsertThread(MySQLConfiguration conf, String tabla1, String tabla2, String tabla4) {
        this.conf = conf;
        this.tabla1 = tabla1;
        this.tabla2 = tabla2;
        this.tabla3 = tabla4;
    }

    public void run() {
        try {
            int aux = 0;
            int aux2 = 0;
            int cant = 1;
            double diff = 0;
            MySQLDBManager manager = new MySQLDBManager(conf);
            manager.connect();
            IPDRFormat iPDRFormat;
            Long milis = Calendar.getInstance().getTimeInMillis();
            PreparedStatement ps1 = manager.getCon().prepareStatement("INSERT INTO " + tabla1 + " VALUES (?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )");
            PreparedStatement ps2 = manager.getCon().prepareStatement("INSERT INTO " + tabla2 + " VALUES (?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )");
            PreparedStatement ps3 = manager.getCon().prepareStatement("INSERT INTO " + tabla3 + " VALUES (?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )");
            while (true) {
                try {
                    iPDRFormat = cargarIDPR();
                    mandarIPDR2(manager, iPDRFormat, ps1);
                    mandarIPDR2(manager, iPDRFormat, ps2);
                    mandarIPDR2(manager, iPDRFormat, ps3);
                } catch (SQLException ex) {
                    System.out.println("ERROR INSERT EN TABLA ");
                }
                aux++;
                aux2++;
                if (aux >= 5000) {
                    diff += (Calendar.getInstance().getTimeInMillis() - milis);
                    diff = diff / cant;
                    cant++;
                    System.out.println(aux2 + " INSERTS EN LA TABLA. Tardo prom.: " + diff + " milis");
                    aux = 0;
                    milis = Calendar.getInstance().getTimeInMillis();
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
        ipdr.setCMmacAddress("eeeeee" + Math.random() * 12341234);
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

    private void mandarIPDR2(MySQLDBManager manager, IPDRFormat iPDRFormat, PreparedStatement ps) throws SQLException {
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
    }

    private void mandarIPDR(MySQLDBManager manager, IPDRFormat iPDRFormat, String tabla) throws SQLException {
        ps = manager.getCon().prepareStatement("INSERT INTO " + tabla + " VALUES (?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )");
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
    }
}
