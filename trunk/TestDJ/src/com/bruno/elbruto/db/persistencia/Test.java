/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bruno.elbruto.db.persistencia;

import com.bruno.elbruto.db.persistencia.exceptions.PreexistingEntityException;
import com.bruno.elbruto.manager.Bruto;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class Test {

    public static void main(String[] args) {
        try {
            Bruto bruto = new Bruto();
            bruto.setNombre("brunoli");
            bruto.setNivel(1);
            bruto.setPassword("hortelano");
            BrutoJpaController con = new BrutoJpaController();
            con.create(bruto);
            System.out.println("Listo");
        } catch (PreexistingEntityException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
