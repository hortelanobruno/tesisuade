/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.bruno.elbruto.db.persistencia.*;
import com.bruno.elbruto.manager.Bruto;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class TestDB {

    /*cargarBruto("qwerfdsa", 8);
    cargarBruto("asdfvcxz", 8);
    cargarBruto("brunoli", 9);
    cargarBruto("brunoli2", 9);
    cargarBruto("nestornbloq", 9);
    cargarBruto("fdsavcxz", 1);
    cargarBruto("qaswzx", 1);
     * cargarRival("m0renaa",7);
        cargarRival("11clan11",7);
        cargarRival("kroes",7);
        cargarRival("azm",6);
        cargarRival("guilios",6);
        cargarRival("l-shakas",6);
     */
    public static void main(String[] args) {
        try {
            BrutoJpaController con = new BrutoJpaController();
            Bruto bruto = new Bruto();
            bruto.setNombre("m0renaa");
            bruto.setNivel(7);
            con.create(bruto);
            bruto = new Bruto();
            bruto.setNombre("11clan11");
            bruto.setNivel(7);
            con.create(bruto);
            bruto = new Bruto();
            bruto.setNombre("kroes");
            bruto.setNivel(7);
            con.create(bruto);
            bruto = new Bruto();
            bruto.setNombre("azm");
            bruto.setNivel(6);
            con.create(bruto);
            bruto = new Bruto();
            bruto.setNombre("guilios");
            bruto.setNivel(6);
            con.create(bruto);
            bruto = new Bruto();
            bruto.setNombre("l-shakas");
            bruto.setNivel(6);
            con.create(bruto);
            bruto = new Bruto();
            bruto.setNombre("neee");
            bruto.setNivel(1);
            con.create(bruto);
            bruto = new Bruto();
            bruto.setNombre("sexologo");
            bruto.setNivel(1);
            con.create(bruto);
            bruto = new Bruto();
            bruto.setNombre("camiloo");
            bruto.setNivel(1);
            con.create(bruto);
//            bruto.setNombre("brunoli");
//            bruto.setNivel(9);
//            bruto.setPassword("hortelano");
//            bruto.setPropietario(true);
//            con.create(bruto);
//            bruto = new Bruto();
//            bruto.setNombre("brunoli2");
//            bruto.setNivel(9);
//            bruto.setPassword("hortelano");
//            bruto.setPropietario(true);
//            con.create(bruto);
//            bruto = new Bruto();
//            bruto.setNombre("qwerfdsa");
//            bruto.setNivel(8);
//            bruto.setPassword("hortelano");
//            bruto.setPropietario(true);
//            con.create(bruto);
//            bruto = new Bruto();
//            bruto.setNombre("asdfvcxz");
//            bruto.setNivel(8);
//            bruto.setPropietario(true);
//            con.create(bruto);
//            bruto = new Bruto();
//            bruto.setNombre("nestornbloq");
//            bruto.setNivel(9);
//            bruto.setPassword("hortelano");
//            bruto.setPropietario(true);
//            con.create(bruto);
//            bruto = new Bruto();
//            bruto.setNombre("fdsavcxz");
//            bruto.setNivel(3);
//            bruto.setPassword("hortelano");
//            bruto.setPropietario(true);
//            con.create(bruto);
//            bruto = new Bruto();
//            bruto.setNombre("qaswzx");
//            bruto.setNivel(3);
//            bruto.setPassword("hortelano");
//            bruto.setPropietario(true);
//            con.create(bruto);
//            con.create(bruto);
//            con.create(bruto2);
//            Pelea pelea = new Pelea();
//            pelea.setBruto(bruto);
//            pelea.setRival(bruto2);
//            pelea.setVictoria(true);
//            pelea.setFecha(new Date());
//            PeleaJpaController pel = new PeleaJpaController();
//            System.out.println(pel.findCantPeleas(bruto, new Date()));
            System.out.println("Listo");
//        } catch (PreexistingEntityException ex) {
//            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
