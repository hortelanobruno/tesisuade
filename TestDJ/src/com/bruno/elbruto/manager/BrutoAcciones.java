/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bruno.elbruto.manager;

import com.bruno.elbruto.browser.SimpleWebBrowser;
import com.bruno.elbruto.util.LoggerClass;
import com.bruno.elbruto.util.MouseRobot;

/**
 *
 * @author Brunoli
 */
public class BrutoAcciones {

    private boolean done = false;
    private MouseRobot robot;
    private SimpleWebBrowser simpleWeb;
    private boolean navigateAManopla = true;
    private boolean inServidor = false;
    private int count;

    public BrutoAcciones() {
        robot = new MouseRobot();
    }

    public BrutoAcciones(SimpleWebBrowser simpleWeb) {
        robot = new MouseRobot();
        this.simpleWeb = simpleWeb;
    }

    public synchronized void avisarDone() {
        done = true;
    }

    public void waitForDone() {
        done = false;
        count = 0;
        while (!done) {
            count++;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
            }
            if (count == 60) {
                LoggerClass.getInstance().error("Se colgo la aplicacion en waitForDone");
                System.exit(0);
            }
        }
    }

    public void ponerURL(String url) {
        String aux = simpleWeb.getUrl();
        while (!aux.equalsIgnoreCase("http://" + url)) {
            if (inServidor) {
                robot.mover(711, 83);
            } else {
                robot.mover(800, 78);
            }
            robot.clickIzquierdo();
            robot.delay(500);
            robot.borrar();
            robot.delay(500);
            robot.escribir(url);
            robot.delay(500);
            robot.enter();
            waitForDone();
            aux = simpleWeb.getUrl();
        }
    }

    public void pelear(Bruto bruto, String rival) {
        robot.delay(1500);
        if (navigateAManopla) {
            ponerURL(bruto.getNombre() + ".elbruto.es/vs/" + rival);
        } else {
            simpleWeb.navigate(bruto.getNombre() + ".elbruto.es/vs/" + rival);
            waitForDone();
        }
        robot.delay(3000);
        if (inServidor) {
            robot.mover(1261, 698);
            robot.delay(500);
            robot.clickIzquierdo(3000);
            robot.delay(500);
            robot.mover(651, 611);
            robot.delay(500);
        } else {
            robot.mover(1265, 690);
            robot.clickIzquierdo(3000);
            robot.mover(650, 610);
        }
        robot.clickIzquierdo();
        robot.delay(1000);
        irCellule(bruto);
    }

    public void ponerPassword(Bruto bruto) {
        robot.delay(1500);
        if (navigateAManopla) {
            ponerURL(bruto.getNombre() + ".elbruto.es/login");
        } else {
            simpleWeb.navigate(bruto.getNombre() + ".elbruto.es/login");
            waitForDone();
        }
        if (inServidor) {
            robot.mover(370, 360);
        } else {
            robot.mover(300, 350);
        }
        robot.clickIzquierdo();
        robot.delay(500);
        robot.escribir(bruto.getPassword());
        robot.delay(500);
        robot.enter();
        waitForDone();
    }

    public void crearPassword(Bruto bruto) {
        robot.delay(1500);
        if (navigateAManopla) {
            ponerURL(bruto.getNombre() + ".elbruto.es/cellule");
        } else {
            simpleWeb.navigate(bruto.getNombre() + ".elbruto.es/cellule");
            waitForDone();
        }
        robot.delay(5000);
        if (inServidor) {
            robot.mover(1259, 107);
            robot.clickIzquierdo(3000);
            robot.mover(416, 355);
            robot.clickIzquierdo();
            robot.delay(5000);
            robot.mover(348, 454);
            robot.clickIzquierdo();
            robot.escribir(bruto.getPassword());
            robot.mover(348, 503);
            robot.clickIzquierdo();
            robot.escribir(bruto.getPassword());
            robot.mover(348, 540);
            robot.clickIzquierdo();
            robot.delay(5000);
        } else {
            robot.mover(1265, 100);
            robot.clickIzquierdo(3000);
            robot.mover(300, 358);
            robot.clickIzquierdo();
            robot.delay(5000);
            robot.mover(330, 450);
            robot.clickIzquierdo();
            robot.escribir(bruto.getPassword());
            robot.mover(330, 500);
            robot.clickIzquierdo();
            robot.escribir(bruto.getPassword());
            robot.mover(330, 530);
            robot.clickIzquierdo();
            robot.delay(5000);
        }
    }

    public void irCellule(Bruto bruto) {
        robot.delay(1500);
        if (navigateAManopla) {
            ponerURL(bruto.getNombre() + ".elbruto.es/cellule");
        } else {
            simpleWeb.navigate(bruto.getNombre() + ".elbruto.es/cellule");
            waitForDone();
        }
    }

    public Bruto crearBruto(String nombre, Bruto ancestro) {
        robot.delay(1500);
        ponerURL(ancestro.getNombre() + ".elbruto.es");
        if (inServidor) {
            robot.mover(1259, 107);
            robot.clickIzquierdo(3000);
            robot.mover(301, 384);
            robot.clickIzquierdo();
            robot.escribir(nombre);
            robot.mover(327, 644);
            robot.clickIzquierdo();
            robot.delay(10000);
        } else {
            robot.mover(1265, 100);
            robot.clickIzquierdo(3000);
            robot.mover(300, 370);
            robot.clickIzquierdo();
            robot.escribir(nombre);
            robot.mover(300, 630);
            robot.clickIzquierdo();
            robot.delay(10000);
        }
        String url = simpleWeb.getUrl();
        if (url.contains(nombre)) {
            Bruto br = new Bruto();
            br.setNombre(nombre);
            br.setPassword("hortelano");
            br.setNivel(1);
            br.setPropietario(true);
            return br;
        } else {
            return null;
        }
    }

    public void cambiarIPDelRouter() {
        //TODO
        robot.delay(1500);
        if (navigateAManopla) {
            ponerURL("192.168.0.1");
        } else {
            simpleWeb.navigate("192.168.0.1");
        }
        robot.delay(5000);
        if (inServidor) {
            robot.mover(645, 386);
            robot.clickIzquierdo();
            robot.escribir("tplink");
            robot.mover(637, 415);
            robot.clickIzquierdo();
            robot.escribir("hortelano");
            robot.mover(663, 503);
            robot.clickIzquierdo();
            robot.delay(5000);
            robot.mover(855, 700);
            robot.clickIzquierdo(3000);
            robot.mover(671, 490);
            robot.clickIzquierdo();
            robot.mover(855, 700);
            robot.clickIzquierdo(3000);
            robot.mover(671, 490);
            robot.clickIzquierdo();
            robot.delay(10000);
        } else {
            robot.mover(600, 404);
            robot.clickIzquierdo();
            robot.escribir("tplink");
            robot.mover(602, 433);
            robot.clickIzquierdo();
            robot.escribir("hortelano");
            robot.mover(718, 507);
            robot.clickIzquierdo();
            robot.delay(5000);
            robot.mover(855, 691);
            robot.clickIzquierdo(3000);
            robot.mover(655, 484);
            robot.clickIzquierdo();
            robot.mover(855, 691);
            robot.clickIzquierdo(3000);
            robot.mover(655, 484);
            robot.clickIzquierdo();
            robot.delay(10000);
        }
    }

    public void inscribirEnTorneo(Bruto bruto) {
        robot.delay(1500);
        if (navigateAManopla) {
            if (inServidor) {
                robot.mover(711, 83);
            } else {
                robot.mover(800, 78);
            }
            robot.clickIzquierdo();
            robot.delay(500);
            robot.borrar();
            robot.delay(500);
            robot.escribir(bruto.getNombre() + ".elbruto.es/sub");
            robot.delay(500);
            robot.enter();
        } else {
            simpleWeb.navigate(bruto.getNombre() + ".elbruto.es/sub");
        }
        waitForDone();
    }
}
