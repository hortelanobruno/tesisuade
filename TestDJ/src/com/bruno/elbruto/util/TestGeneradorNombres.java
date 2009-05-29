/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bruno.elbruto.util;

import com.bruno.elbruto.db.DBManager;
import com.bruno.elbruto.db.persistencia.controller.NombreJpaController;
import com.bruno.elbruto.db.persistencia.controller.exceptions.PreexistingEntityException;
import com.bruno.elbruto.db.persistencia.entities.Nombre;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class TestGeneradorNombres {

    public static void main(String[] args) {
        crearDefaultNombres();
    }

    private static void crearDefaultNombres() {
        List<String> nombres = new ArrayList<String>();
        nombres.add("brunoli");
        nombres.add("gambit");
        nombres.add("elpolaco");
        nombres.add("bruno");
        nombres.add("lorenzo");
        nombres.add("legui");
        nombres.add("papu");
        nombres.add("kaka");
        nombres.add("riky");
        nombres.add("manu");
        nombres.add("fafor");
        nombres.add("raul");
        nombres.add("pichy");
        nombres.add("migui");
        nombres.add("torco");
        nombres.add("nahui");
        nombres.add("carlitox");
        nombres.add("gaston");
        nombres.add("ernesto");
        nombres.add("miguel");
        nombres.add("angel");
        nombres.add("mariano");
        nombres.add("dreadmari");
        nombres.add("jhonny");
        nombres.add("falacia");
        nombres.add("mike");
        nombres.add("pable");
        nombres.add("lorenzini");
        nombres.add("button");
        nombres.add("graciela");
        nombres.add("mary");
        nombres.add("sa");
        nombres.add("sabru");
        nombres.add("wally");
        nombres.add("westy");
        nombres.add("tommy");
        nombres.add("alicie");
        nombres.add("jean");
        nombres.add("cindy");
        nombres.add("bruce");
        nombres.add("margaret");
        nombres.add("elizabet");
        nombres.add("ameli");
        nombres.add("andy");
        nombres.add("tim");
        nombres.add("charlie");
        nombres.add("berta");
        nombres.add("alan");
        nombres.add("jake");
        nombres.add("lincol");
        nombres.add("michael");
        nombres.add("sara");
        nombres.add("niki");
        nombres.add("hiro");
        nombres.add("edgar");
        nombres.add("nisan");
        nombres.add("peter");
        nombres.add("claire");
        nombres.add("mark");
        nombres.add("suresh");
        nombres.add("jack");
        nombres.add("tony");
        nombres.add("hurley");
        nombres.add("jhon");
        nombres.add("jacob");
        nombres.add("ben");
        NombreJpaController jpa = new NombreJpaController();
        Nombre nombre = new Nombre();
        for (String string : nombres) {
            try {
                nombre.setNombre(string);
                jpa.create(nombre);
            } catch (PreexistingEntityException ex) {
                System.out.println(ex.getMessage());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
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
