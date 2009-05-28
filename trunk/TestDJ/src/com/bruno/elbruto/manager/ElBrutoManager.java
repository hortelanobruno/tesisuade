/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bruno.elbruto.manager;

import com.bruno.elbruto.browser.SimpleWebBrowser;
import com.bruno.elbruto.db.DBManager;
import com.bruno.elbruto.util.LoggerClass;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Brunoli
 */
public class ElBrutoManager {

    private SimpleWebBrowser simpleWeb;
    private BrutoAcciones brutoAcciones;
    private DBManager dbManager;
    private boolean inscribirTorneo = false;//TODO FALTA VER CUANDO SETEAR ESTO

    public ElBrutoManager() {
    }

    public void init() {
        brutoAcciones = new BrutoAcciones();
        dbManager = new DBManager();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
        }
        LoggerClass.getInstance().info("Iniciando proceso de peleas");
        iniciarModoPeleas();
        LoggerClass.getInstance().info("Termino proceso de peleas");
        LoggerClass.getInstance().info("Iniciando proceso de creacion de nuevas cuentas");
        iniciarModoCrearCuentas();
        LoggerClass.getInstance().info("Termino proceso de creacion de nuevas cuentas");
    }

    public void avisarDone() {
        brutoAcciones.avisarDone();
    }

    public void setWebBrowser(SimpleWebBrowser aThis) {
        this.simpleWeb = aThis;
    }

    private int chequearPelea(Bruto bruto, String rival) {
        //1 = gano 0 = perdio -1 = no se jugo
        String html = simpleWeb.getSourceCode();
        String vencido = "<divclass='logs'>" + html.split("<divclass='logs'>")[1];
        vencido = vencido.split("</td></tr></table></div></div><div class='celluleFoot'></div>")[0];
        System.out.println(vencido);
        String[] asd = vencido.split("onmouseout");
        String a;
        for (int i = 1; i < asd.length; i++) {
            a = asd[i].toLowerCase();
            char[] chars = a.toCharArray();
            int index1 = 0, index2 = 0;
            for (int j = 0; j < chars.length; j++) {
                if (chars[j] == '>') {
                    index1 = j;
                }
                if (chars[j] == '<') {
                    index2 = j;
                    break;
                }
            }
            String pelea = a.substring(index1 + 1, index2 - 1);
            if (pelea.contains(rival.toLowerCase())) {
                if (pelea.startsWith("tubruto")) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
        return -1;
    }

    private String generarNombreParaBruto() {
        int cantChars = (int) ((Math.random() * 5)) + 4;
        String str = new String("QAa0bcLdUK2eHfJgTP8XhiFj61DOklNm9nBoI5pGqYVrs3CtSuMZvwWx4yE7zR");
        StringBuffer sb = new StringBuffer();
        Random r = new Random();
        int te = 0;
        for (int i = 1; i <= cantChars; i++) {
            te = r.nextInt(62);
            sb.append(str.charAt(te));
        }
        System.out.println();
        return sb.toString().toLowerCase();
    }

    private void iniciarModoCrearCuentas() {
        String nombre;
        Bruto bruto;
        for (int i = 0; i < 10; i++) {
            nombre = generarNombreParaBruto();
            bruto = null;
            while (true) {
                bruto = brutoAcciones.crearBruto(nombre);
                if (bruto != null) {
                    break;
                }
            }
            brutoAcciones.crearPassword(bruto);
            brutoAcciones.ponerPassword(bruto);
            //aca hay q ver cuantas peleas tengo, creo que siempre son 6
            for (int j = 0; j < 6; j++) {
                LinkedList<Bruto> rivales = obtenerRivalesPara(bruto, 6);
                Pelea pelea;
                Bruto rival;
                rival = rivales.poll();
                int resultadoPelea;
                while (true) {
                    brutoAcciones.pelear(bruto, rival.getNombre());
                    resultadoPelea = chequearPelea(bruto, rival.getNombre());
                    if (resultadoPelea != -1) {
                        break;
                    }
                }
                pelea = new Pelea();
                pelea.setBruto(bruto);
                pelea.setRival(rival);
                pelea.setFecha(new Date());
                if (resultadoPelea == 1) {
                    pelea.setVictoria(true);
                } else {
                    pelea.setVictoria(false);
                }
                dbManager.create(pelea);
            }
            int nivel = brutoAcciones.obtenerNivel();
            if (nivel != bruto.getNivel()) {
                //actualizar nivel
                bruto.setNivel(nivel);
                dbManager.edit(bruto);
            }
        }

    }

    private void iniciarModoPeleas() {
        List<Bruto> brutos = dbManager.findBrutosPropietarios();
        for (Bruto bruto : brutos) {
            LoggerClass.getInstance().info("Proceso pelea con " + bruto.getNombre());
            pelearModo1(bruto);
            if (inscribirTorneo) {
                brutoAcciones.inscribirEnTorneo();
            }
        }
    }

    private LinkedList<Bruto> obtenerRivalesPara(Bruto bruto, int cant) {
        return dbManager.findRivales(bruto, cant);
    }

    private void pelearModo1(Bruto bruto) {
        //Ingreso el password si lo tiene
        if (bruto.getPassword() != null) {
            if (!bruto.getPassword().trim().isEmpty()) {
                brutoAcciones.ponerPassword(bruto);
            } else {
                brutoAcciones.irCellule(bruto);
            }
        } else {
            brutoAcciones.irCellule(bruto);
        }
        int cantPeleas = dbManager.findCantPeleas(bruto, new Date());
        if (cantPeleas < 3) {
            LinkedList<Bruto> rivales = obtenerRivalesPara(bruto, 3 - cantPeleas);
            Pelea pelea;
            Bruto rival;
            for (int i = 0; i < 3 - cantPeleas; i++) {
                rival = rivales.poll();
                int resultadoPelea;
                while (true) {
                    brutoAcciones.pelear(bruto, rival.getNombre());
                    resultadoPelea = chequearPelea(bruto, rival.getNombre());
                    if (resultadoPelea != -1) {
                        break;
                    }
                }
                pelea = new Pelea();
                pelea.setBruto(bruto);
                pelea.setRival(rival);
                pelea.setFecha(new Date());
                if (resultadoPelea == 1) {
                    pelea.setVictoria(true);
                } else {
                    pelea.setVictoria(false);
                }
                dbManager.create(pelea);
            }
        }
        int nivel = brutoAcciones.obtenerNivel();
        if (nivel != bruto.getNivel()) {
            //actualizar nivel
            bruto.setNivel(nivel);
            dbManager.edit(bruto);
        }
    }
}
