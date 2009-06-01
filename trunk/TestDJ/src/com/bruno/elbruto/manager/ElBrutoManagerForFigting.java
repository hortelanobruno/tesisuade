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

/**
 *
 * @author Brunoli
 */
public class ElBrutoManagerForFigting extends ElBrutoManager {

    private SimpleWebBrowser simpleWeb;
    private BrutoAcciones brutoAcciones;
    private DBManager dbManager;

    public ElBrutoManagerForFigting() {
        super();
    }

    public void avisarDone() {
        brutoAcciones.avisarDone();
    }

    public void setWebBrowser(SimpleWebBrowser aThis) {
        this.simpleWeb = aThis;
    }

    public void init() {
        brutoAcciones = new BrutoAcciones(simpleWeb);
        dbManager = new DBManager();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
        }
        LoggerClass.getInstance().info("Iniciando proceso de peleas");
        iniciarModoPeleas();
        LoggerClass.getInstance().info("Termino proceso de peleas");
       // soloPeleaCuandoSePicaYQuedoSinPelear();
//        LoggerClass.getInstance().info("Iniciando proceso de creacion de nuevas cuentas");
//        iniciarModoCrearCuentas();
//        LoggerClass.getInstance().info("Termino proceso de creacion de nuevas cuentas");
    }

    private int obtenerNivel() {
        String html = simpleWeb.getSourceCode();
        html = html.replace(" ", "");
        html = html.replace("\"", "");
        html = html.toLowerCase();
        String vencido = html.split("<span>nivel")[1];
        vencido = vencido.split("<")[0];
        return Integer.parseInt(vencido);
    }

    private boolean hayQueInscribirTorneo() {
        //TODO TERMINAR
        String html = simpleWeb.getSourceCode();
        html = html.replace(" ", "");
        html = html.replace("\"", "");
        html = html.toLowerCase();
        if (html.contains("inscribetubrutoparael")) {
            return true;
        } else {
            return false;
        }
    }

    private int chequearPelea(Bruto bruto, String rival) {
        //1 = gano 0 = perdio -1 = no se jugo
        String html = simpleWeb.getSourceCode();
        html = html.replace(" ", "");
        html = html.replace("\"", "");
        html = html.toLowerCase();
        String vencido = "<divclass=logs>" + html.split("<divclass=logs>")[1];
        vencido = vencido.split("</td></tr></table></div></div><div class=celluleFoot></div>")[0];
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

    public void soloPeleaCuandoSePicaYQuedoSinPelear() {
        Bruto bruto = dbManager.findBruto("jaime2323");
        brutoAcciones.ponerPassword(bruto);
        //aca hay q ver cuantas peleas tengo, creo que siempre son 6
        LinkedList<Bruto> rivales = obtenerRivalesPara(bruto, 6);
        for (int j = 0; j < 6; j++) {
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
        int nivel = obtenerNivel();
        if (nivel != bruto.getNivel()) {
            //actualizar nivel
            bruto.setNivel(nivel);
            dbManager.actualizarNivel(bruto);
        }
        if (hayQueInscribirTorneo()) {
            brutoAcciones.inscribirEnTorneo(bruto);
        }
    }

    private void iniciarModoCrearCuentas() {
        String nombre;
        Bruto bruto;
        Bruto ancestro = dbManager.findAncestro();
        for (int i = 0; i < 100; i++) {
            bruto = null;
            while (true) {
                nombre = dbManager.randomBrutoName();
                bruto = brutoAcciones.crearBruto(nombre, ancestro);
                if (bruto != null) {
                    break;
                }
            }
            dbManager.create(bruto);
            brutoAcciones.crearPassword(bruto);
            brutoAcciones.ponerPassword(bruto);
            //aca hay q ver cuantas peleas tengo, creo que siempre son 6
            Pelea pelea;
            Bruto rival;
            LinkedList<Bruto> rivales = obtenerRivalesPara(bruto, 6);
            for (int j = 0; j < 6; j++) {
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
                if(j==2){
                    System.out.println("aaa");
                }
            }
            int nivel = obtenerNivel();
            if (nivel != bruto.getNivel()) {
                //actualizar nivel
                bruto.setNivel(nivel);
                dbManager.actualizarNivel(bruto);
            }
            if (hayQueInscribirTorneo()) {
                brutoAcciones.inscribirEnTorneo(bruto);
            }
        }

    }

    private void iniciarModoPeleas() {
        //NO TOCARLO, ASI FUNCIONA BIEN, PERO CHEQUEAR QUE AVECES NO BORRA BIEN LA URL
        List<Bruto> brutos = dbManager.findBrutosPropietarios();
        for (Bruto bruto : brutos) {
            LoggerClass.getInstance().info("Proceso pelea con " + bruto.getNombre());
            pelearModo1(bruto);
        }
    }

    private LinkedList<Bruto> obtenerRivalesPara(Bruto bruto, int cant) {
        return dbManager.findRivales(bruto, cant);
    }

    private void pelearModo1(Bruto bruto) {
        int cantPeleas = dbManager.findCantPeleas(bruto, new Date());
        if (cantPeleas < 3) {
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
            LinkedList<Bruto> rivales = obtenerRivalesPara(bruto, 3 - cantPeleas);
            Pelea pelea;
            Bruto rival;
            for (int i = 0; i < 3 - cantPeleas; i++) {
                if (!rivales.isEmpty()) {
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
            int nivel = obtenerNivel();
            if (nivel != bruto.getNivel()) {
                //actualizar nivel
                bruto.setNivel(nivel);
                dbManager.actualizarNivel(bruto);
            }
            if (hayQueInscribirTorneo()) {
                brutoAcciones.inscribirEnTorneo(bruto);
            }
        }
    }
}
