/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bruno.elbruto.manager;

import com.bruno.elbruto.browser.SimpleWebBrowser;
import com.bruno.elbruto.db.DBManager;
import com.bruno.elbruto.util.LoggerClass;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author Brunoli
 */
public class ElBrutoManagerForFigting extends ElBrutoManager {

    private SimpleWebBrowser simpleWeb;
    private BrutoAcciones brutoAcciones;
    private DBManager dbManager;
    private Properties config;

    public ElBrutoManagerForFigting() {
        super();
        java.io.FileInputStream fis = null;
        config = new Properties();
        try {
            fis = new java.io.FileInputStream(new java.io.File("./config/config.properties"));
            config.load(fis);
        } catch (IOException ex) {
            LoggerClass.getInstance().error("Error al abrir el properties de configuracion");
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                LoggerClass.getInstance().error("Error al abrir el properties de configuracion");
            }
        }
    }

    public void avisarDone() {
        brutoAcciones.avisarDone();
    }

    public void setWebBrowser(SimpleWebBrowser aThis) {
        this.simpleWeb = aThis;
    }

    public void init() {
        brutoAcciones = new BrutoAcciones(simpleWeb, config);
        dbManager = new DBManager();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
        }
        LoggerClass.getInstance().info("Iniciando proceso");
        LoggerClass.getInstance().info("Iniciando proceso en modo pelea");
        iniciarModoPeleas();
        LoggerClass.getInstance().info("Termino proceso en modo pelea");
        LoggerClass.getInstance().info("Terminando proceso");
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

    private int obtenerCantPeleasADisputar(String nombre) {
        String html = simpleWeb.getSourceCode();
        html = html.replace(" ", "");
        html = html.replace("\"", "");
        html = html.toLowerCase();
        if (html.contains("tequedan")) {
            html = html.split("tequedan")[1];
            if (html.contains("combatesparaeld")) {
                html = html.split("combatesparaeld")[0];
            } else {
                html = html.split("combateparaeld")[0];
            }
            return Integer.parseInt(html);
        } else {
            dbManager.crearPeleasCompletadas(nombre);
            return -1;
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
        Bruto bruto = dbManager.findBruto("pable698");
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

    private void iniciarModoPeleas() {
        List<Bruto> brutos = dbManager.findBrutosPropietarios();
        for (Bruto bruto : brutos) {
            LoggerClass.getInstance().info("Iniciando Proceso pelea con el bruto " + bruto.getNombre());
            pelearModo1(bruto);
            LoggerClass.getInstance().info("Terminando Proceso pelea con el bruto " + bruto.getNombre());
        }
    }

    private LinkedList<Bruto> obtenerRivalesPara(Bruto bruto, int cant) {
        return dbManager.findRivales(bruto, cant);
    }

    private void pelearModo1(Bruto bruto) {
        //Ingreso el password si lo tiene
        if (dbManager.tieneQPelear(bruto.getNombre())) {
            if (bruto.getPassword() != null) {
                if (!bruto.getPassword().trim().isEmpty()) {
                    LoggerClass.getInstance().info("Ingresando password para el bruto " + bruto.getNombre());
                    brutoAcciones.ponerPassword(bruto);
                } else {
                    LoggerClass.getInstance().info("Yendo a la cellula del bruto " + bruto.getNombre());
                    brutoAcciones.irCellule(bruto);
                }
            } else {
                LoggerClass.getInstance().info("Yendo a la cellula del bruto " + bruto.getNombre());
                brutoAcciones.irCellule(bruto);
            }
            int cantPeleas = obtenerCantPeleasADisputar(bruto.getNombre());
            LoggerClass.getInstance().info("El bruto " + bruto.getNombre() + " tiene " + cantPeleas + " por disputar");
            LinkedList<Bruto> rivales;
            Pelea pelea;
            Bruto rival;
            int nivel;
            int resultadoPelea;
            rivales = obtenerRivalesPara(bruto, 10);
            while (cantPeleas != -1) {
                while (true) {
                    rival = rivales.poll();
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
                    LoggerClass.getInstance().info("Peleando contra '" + rival + "'. Victoria");
                    pelea.setVictoria(true);
                } else {
                    LoggerClass.getInstance().info("Peleando contra '" + rival + "'. Derrota");
                    pelea.setVictoria(false);
                }
                dbManager.create(pelea);
                nivel = obtenerNivel();
                if (nivel != bruto.getNivel()) {
                    //actualizar nivel
                    bruto.setNivel(nivel);
                    dbManager.actualizarNivel(bruto);
                    LoggerClass.getInstance().info("El bruto " + bruto.getNombre() + " subio al nivel " + nivel);
                }
                cantPeleas = obtenerCantPeleasADisputar(bruto.getNombre());
            }
            nivel = obtenerNivel();
            if (nivel != bruto.getNivel()) {
                //actualizar nivel
                bruto.setNivel(nivel);
                dbManager.actualizarNivel(bruto);
                LoggerClass.getInstance().info("El bruto " + bruto.getNombre() + " subio al nivel " + nivel);
            }
            if (hayQueInscribirTorneo()) {
                brutoAcciones.inscribirEnTorneo(bruto);
                LoggerClass.getInstance().info("Inscribiendo al torneo al bruto " + bruto.getNombre());
            }
        }
    }
}
