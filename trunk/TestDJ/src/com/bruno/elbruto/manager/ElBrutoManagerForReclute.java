/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bruno.elbruto.manager;

import com.bruno.elbruto.browser.SimpleWebBrowser;
import com.bruno.elbruto.db.DBManager;
import com.bruno.elbruto.db.persistencia.entities.Alumno;
import com.bruno.elbruto.util.LoggerClass;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.Properties;

/**
 *
 * @author Brunoli
 */
public class ElBrutoManagerForReclute extends ElBrutoManager {

    private SimpleWebBrowser simpleWeb;
    private BrutoAcciones brutoAcciones;
    private DBManager dbManager;
    private Properties config;

    public ElBrutoManagerForReclute() {
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

    @Override
    public void init() {
        LoggerClass.getInstance().info("Iniciando proceso de reclutamiento");
        brutoAcciones = new BrutoAcciones(simpleWeb, config);
        dbManager = new DBManager();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
        }
        LoggerClass.getInstance().info("Buscando ancestro....");
        String anc = config.getProperty("ancestro");
        Bruto ancestro = dbManager.findAncestro(anc);
        if (ancestro != null) {
            LoggerClass.getInstance().info("El ancestro es " + ancestro.getNombre());
            int cantReclutantes = 5;
            String ip = obtenerIPPublica();
            String nombre;
            int delayRecluting = Integer.parseInt(config.getProperty("delayRecluting"));
            for (int i = 0; i < cantReclutantes; i++) {
                try {
                    LoggerClass.getInstance().info("Buscando reclutante numero " + new Integer(i + 1));
                    while (true) {
                        if (chequearIPUsada(ip, ancestro)) {
                            ip = cambiarIP(ip);
                            LoggerClass.getInstance().info("IP usada, cambiando ip a: " + ip);
                        }
                        try {
                            LoggerClass.getInstance().info("Creando alumno...");
                            nombre = crearAlumno(ip, ancestro);
                            if (nombre != null) {
                                break;
                            } else {
                                LoggerClass.getInstance().info("Cambiando ip " + ip + " debido a que el alumno no fue productivo.");
                                ip = cambiarIP(ip);
                                LoggerClass.getInstance().info("La nueva ip es " + ip);
                            }
                        } catch (Exception ex) {
                            LoggerClass.getInstance().error("Error al crear el alumno.");
                            init();
                        }
                    }
                    soloPeleaCuandoSePicaYQuedoSinPelear(nombre);
                    LoggerClass.getInstance().info("Durmiendo " + delayRecluting + " minutos.");
                    Thread.sleep(delayRecluting * 1000);
                } catch (InterruptedException ex) {
                }
            }
            LoggerClass.getInstance().info("Terminando proceso de reclutamiento");
        } else {
            LoggerClass.getInstance().info("No se encontro al ancestro '" + anc + "'");
        }
    }

    private String cambiarIP(String ip) {
        String aux = ip.toString();
        while (aux.equalsIgnoreCase(ip)) {
            brutoAcciones.cambiarIPDelRouter();
            aux = obtenerIPPublica();
        }
        return aux;
    }

    /*<div class="log log-child">
    <div class="lmain">
    Nuevo alumno: charlie398.
    </div><divclass='ldetails'>1puntodeexperienciaganado.</div>
    </div>
     */
    private boolean chequearAncestroProductivo(Bruto bruto, Bruto ancestro) {
        brutoAcciones.irCellule(ancestro);
        String html = simpleWeb.getSourceCode();
        html = html.replace(" ", "");
        html = html.replace("\"", "");
        html = html.toLowerCase();
        html = html.split("nuevoalumno:" + bruto.getNombre().toLowerCase() + ".")[1];
        html = html.substring(0, 58);
        if (html.contains("1puntodeexperienciaganado")) {
            return true;
        } else {
            return false;
        }
    }

    private boolean chequearIPUsada(String ip, Bruto ancestro) {
        return dbManager.chequearIPUsada(ip, ancestro);
    }

    private String obtenerIPPublica() {
        String ip;
        while (true) {
            try {
                ip = simpleWeb.getIPInternet();
                if (ip != null) {
                    return ip;
                }
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
            }
        }
    }

    @Override
    public void avisarDone() {
        brutoAcciones.avisarDone();
    }

    @Override
    public void setWebBrowser(SimpleWebBrowser aThis) {
        this.simpleWeb = aThis;
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

    public void soloPeleaCuandoSePicaYQuedoSinPelear(String name) {
        Bruto bruto = dbManager.findBruto(name);
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
                try {
                    resultadoPelea = chequearPelea(bruto, rival.getNombre());
                    if (resultadoPelea != -1) {
                        break;
                    }
                } catch (Exception ex) {
                    LoggerClass.getInstance().error("Error al chequear la pelea.");
                    init();
                }
            }
            pelea = new Pelea();
            pelea.setBruto(bruto);
            pelea.setRival(rival);
            pelea.setFecha(new Date());
            if (resultadoPelea == 1) {
                pelea.setVictoria(true);
                LoggerClass.getInstance().info("Peleando contra " + rival.getNombre() + ". Victoria");
            } else {
                pelea.setVictoria(false);
                LoggerClass.getInstance().info("Peleando contra " + rival.getNombre() + ". Derrota");
            }
            dbManager.create(pelea);
        }
        int nivel = obtenerNivel();
        if (nivel != bruto.getNivel()) {
            //actualizar nivel
            bruto.setNivel(nivel);
            dbManager.actualizarNivel(bruto);
            LoggerClass.getInstance().info("Subio de nivel, a " + nivel);
        }
        if (hayQueInscribirTorneo()) {
            brutoAcciones.inscribirEnTorneo(bruto);
            LoggerClass.getInstance().info("Inscribiendo al torneo");
        }
    }

    private String crearAlumno(String ip, Bruto ancestro) {
        //true si se creo bien y dio experiencia
        String nombre;
        Bruto bruto;
        while (true) {
            nombre = dbManager.randomBrutoName();
            if (!nombre.equalsIgnoreCase(ancestro.getNombre())) {
                bruto = brutoAcciones.crearBruto(nombre, ancestro);
                if (bruto != null) {
                    break;
                }
            }
        }
        LoggerClass.getInstance().info("El alumno es " + nombre);
        brutoAcciones.crearPassword(bruto);
        if (chequearAncestroProductivo(bruto, ancestro)) {
            LoggerClass.getInstance().info("El alumno resulto productivo");
            dbManager.create(bruto);
            Alumno alumno = new Alumno();
            alumno.setAncestro(ancestro.getNombre());
            alumno.setFecha(new Date());
            alumno.setIp(ip);
            alumno.setNombre(nombre);
            dbManager.create(alumno);
            return nombre;
        } else {
            LoggerClass.getInstance().info("El alumno NO resulto productivo");
            return null;
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
