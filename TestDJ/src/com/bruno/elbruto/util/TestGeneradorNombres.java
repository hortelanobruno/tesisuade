/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bruno.elbruto.util;

import com.bruno.elbruto.db.DBManager;
import com.bruno.elbruto.db.persistencia.controller.NombreJpaController;
import com.bruno.elbruto.db.persistencia.controller.exceptions.PreexistingEntityException;
import com.bruno.elbruto.db.persistencia.entities.Nombre;

/**
 *
 * @author Admin
 */
public class TestGeneradorNombres {

    public static void main(String[] args) {
        crearDefaultNombres();
    }

    private static void crearDefaultNombres() {
        try {
            NombreJpaController jpa = new NombreJpaController();
            Nombre nombre = new Nombre();
            nombre.setNombre("brunoli");
            jpa.create(nombre);
            nombre.setNombre("gambit");
            jpa.create(nombre);
            nombre.setNombre("elpolaco");
            jpa.create(nombre);
            nombre.setNombre("bruno");
            jpa.create(nombre);
            nombre.setNombre("lorenzo");
            jpa.create(nombre);
            nombre.setNombre("legui");
            jpa.create(nombre);
            nombre.setNombre("papu");
            jpa.create(nombre);
            nombre.setNombre("kaka");
            jpa.create(nombre);
            nombre.setNombre("riky");
            jpa.create(nombre);
            nombre.setNombre("manu");
            jpa.create(nombre);
            nombre.setNombre("fafor");
            jpa.create(nombre);
            nombre.setNombre("raul");
            jpa.create(nombre);
            nombre.setNombre("pichy");
            jpa.create(nombre);
            nombre.setNombre("migui");
            jpa.create(nombre);
            nombre.setNombre("torco");
            jpa.create(nombre);
            nombre.setNombre("nahui");
            jpa.create(nombre);
            nombre.setNombre("carlitox");
            jpa.create(nombre);
            nombre.setNombre("gaston");
            jpa.create(nombre);
            nombre.setNombre("ernesto");
            jpa.create(nombre);
            nombre.setNombre("miguel");
            jpa.create(nombre);
            nombre.setNombre("angel");
            jpa.create(nombre);
            nombre.setNombre("mariano");
            jpa.create(nombre);
            nombre.setNombre("dreadmari");
            jpa.create(nombre);

            nombre.setNombre("jhonny");
            jpa.create(nombre);
            nombre.setNombre("falacia");
            jpa.create(nombre);
            nombre.setNombre("mike");
            jpa.create(nombre);
            nombre.setNombre("pable");
            jpa.create(nombre);
            nombre.setNombre("lorenzini");
            jpa.create(nombre);
            nombre.setNombre("button");
            jpa.create(nombre);

        } catch (PreexistingEntityException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void generarRandom() {
        DBManager db = new DBManager();
        System.out.println(db.randomBrutoName());
        System.out.println(db.randomBrutoName());
        System.out.println(db.randomBrutoName());
        System.out.println(db.randomBrutoName());
        System.out.println(db.randomBrutoName());
        System.out.println(db.randomBrutoName());
        System.out.println(db.randomBrutoName());
        System.out.println(db.randomBrutoName());
        System.out.println(db.randomBrutoName());
    }
}
