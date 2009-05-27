/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.bruno.elbruto.db.persistencia.*;
import com.bruno.elbruto.db.persistencia.exceptions.PreexistingEntityException;
import com.bruno.elbruto.manager.Bruto;
import com.bruno.elbruto.manager.Pelea;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class TestDB {

    public static void main(String[] args) {
        try {
            Bruto bruto = new Bruto();
            bruto.setNombre("brunoli");
            bruto.setNivel(1);
            bruto.setPassword("hortelano");
            Bruto bruto2 = new Bruto();
            bruto2.setNombre("brunoli2");
            bruto2.setNivel(2);
            bruto2.setPassword("hortelano2");
            BrutoJpaController con = new BrutoJpaController();
            con.create(bruto);
            con.create(bruto2);
            Pelea pelea = new Pelea();
            pelea.setBruto(bruto);
            pelea.setRival(bruto2);
            pelea.setVictoria(true);
            pelea.setFecha(new Date());
            PeleaJpaController pel = new PeleaJpaController();
            pel.create(pelea);
            System.out.println("Listo");
        } catch (PreexistingEntityException ex) {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
