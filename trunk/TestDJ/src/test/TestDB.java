/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.bruno.elbruto.db.persistencia.controller.BrutoJpaController;
import com.bruno.elbruto.db.persistencia.controller.PeleaJpaController;
import com.bruno.elbruto.db.persistencia.entities.Bruto;
import com.bruno.elbruto.db.persistencia.entities.Pelea;
import com.bruno.elbruto.db.persistencia.entities.PeleaPK;
import java.util.Date;

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
            bruto.setPassword("hortelano");
            bruto.setPropietario(false);
            bruto.setNivel(7);
            con.create(bruto);
            Bruto bruto2 = new Bruto();
            bruto2.setNombre("11clan11");
            bruto2.setPassword("hortelano");
            bruto2.setPropietario(false);
            bruto2.setNivel(7);
            con.create(bruto2);
            Bruto bruto3 = new Bruto();
            bruto3.setNombre("kroes");
            bruto3.setPassword("hortelano");
            bruto3.setPropietario(false);
            bruto3.setNivel(7);
            con.create(bruto3);
//            bruto = new Bruto();
//            bruto.setNombre("azm");
//            bruto.setNivel(6);
//            con.create(bruto);
//            bruto = new Bruto();
//            bruto.setNombre("guilios");
//            bruto.setNivel(6);
//            con.create(bruto);
//            bruto = new Bruto();
//            bruto.setNombre("l-shakas");
//            bruto.setNivel(6);
//            con.create(bruto);
//            bruto = new Bruto();
//            bruto.setNombre("neee");
//            bruto.setNivel(1);
//            con.create(bruto);
//            bruto = new Bruto();
//            bruto.setNombre("sexologo");
//            bruto.setNivel(1);
//            con.create(bruto);
//            bruto = new Bruto();
//            bruto.setNombre("camiloo");
//            bruto.setNivel(1);
//            con.create(bruto);
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
            Pelea pelea = new Pelea();
            PeleaPK pK = new PeleaPK();
            pK.setRival(bruto2.getNombre());
            pK.setFecha(new Date());
            pK.setNombre(bruto.getNombre());
            pelea.setPeleaPK(pK);
            pelea.setVictoria(true);
            PeleaJpaController pel = new PeleaJpaController();
            pel.create(pelea);
            pK.setRival(bruto3.getNombre());
            pelea.setPeleaPK(pK);
            pel.create(pelea);
//            System.out.println(pel.findCantPeleas(bruto, new Date()));
            System.out.println("Listo");
//        } catch (PreexistingEntityException ex) {
//            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
        }
    }
}
