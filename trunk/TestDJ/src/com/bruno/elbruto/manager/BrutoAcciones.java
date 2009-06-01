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

    public void maxizarBrowser() {
        robot.delay(1500);
        robot.mover(300, 147);
        robot.doubleClickIzquierdo();
        robot.delay(2000);
    }

    //http://nestornbloq.elbruto.es/vs/kascorro
    public void pelear(Bruto bruto, String rival) {
        robot.delay(1500);
        if (navigateAManopla) {
            robot.mover(800, 78);
            robot.clickIzquierdo();
            robot.borrar();
            robot.escribir(bruto.getNombre() + ".elbruto.es/vs/" + rival);
            robot.enter();
        } else {
            simpleWeb.navigate(bruto.getNombre() + ".elbruto.es/vs/" + rival);
        }
        waitForDone();
        robot.delay(3000);
        robot.mover(1265, 690);
        robot.clickIzquierdo(3000);
        robot.mover(650, 610);
        robot.clickIzquierdo();
        robot.delay(1000);
        irCellule(bruto);
    }

    public void ponerPassword(Bruto bruto) {
        robot.delay(1500);
        if (navigateAManopla) {
            robot.mover(800, 78);
            robot.clickIzquierdo();
            robot.borrar();
            robot.escribir(bruto.getNombre() + ".elbruto.es/login");
            robot.enter();
        } else {
            simpleWeb.navigate(bruto.getNombre() + ".elbruto.es/login");
        }
        waitForDone();
        robot.mover(300, 350);
        robot.clickIzquierdo();
        robot.escribir(bruto.getPassword());
        robot.enter();
        waitForDone();
    }

    public void crearPassword(Bruto bruto) {
        System.out.println("Creando password");
        robot.delay(1500);
        if (navigateAManopla) {
            robot.mover(800, 78);
            robot.clickIzquierdo();
            robot.borrar();
            robot.escribir(bruto.getNombre() + ".elbruto.es/cellule");
            robot.enter();
        } else {
            simpleWeb.navigate(bruto.getNombre() + ".elbruto.es/cellule");
        }
        robot.delay(15000);
        robot.delay(1500);
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

    public void irCellule(Bruto bruto) {
        robot.delay(1500);
        if (navigateAManopla) {
            robot.mover(800, 78);
            robot.clickIzquierdo();
            robot.borrar();
            robot.escribir(bruto.getNombre() + ".elbruto.es/cellule");
            robot.enter();
        } else {
            simpleWeb.navigate(bruto.getNombre() + ".elbruto.es/cellule");
        }
        waitForDone();
    }

    public Bruto crearBruto(String nombre, Bruto ancestro) {
        //TODO
        robot.delay(1500);
        robot.mover(800, 78);
        robot.clickIzquierdo();
        robot.borrar();
        robot.escribir(ancestro.getNombre() + ".elbruto.es");
        robot.enter();
        waitForDone();
        robot.mover(1265, 100);
        robot.clickIzquierdo(3000);
        robot.mover(300, 370);
        robot.clickIzquierdo();
        robot.escribir(nombre);
        robot.mover(300, 630);
        robot.clickIzquierdo();
        robot.delay(10000);
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
            robot.mover(800, 78);
            robot.clickIzquierdo();
            robot.borrar();
            robot.escribir("192.168.0.1");
            robot.enter();
        } else {
            simpleWeb.navigate("192.168.0.1");
        }
        robot.delay(5000);
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

    private boolean verificarPeleasCompletadas() {
        robot.delay(1500);
        robot.mover(550, 390);
        robot.mousePress();
        robot.mover(680, 420);
        robot.mouseRelease();
        robot.delay(1000);
        String clip = robot.getClipboard();
        if (clip.contains("3 nuevos")) {
            return true;
        } else {
            return false;
        }
    }

    public int obtenerCantidadPeleas(Bruto bruto) {
        System.out.println("Obteniendo cantidad de peleas");
        if (!verificarPeleasCompletadas()) {
            robot.delay(1500);
            robot.mover(1265, 690);
            robot.clickIzquierdo();
            robot.mover(545, 635);
            robot.mousePress();
            robot.mover(665, 635);
            robot.mouseRelease();
            robot.delay(1000);
            String clip = robot.getClipboard();
            return Integer.parseInt(clip.split(" ")[1]);
        } else {
            return 0;
        }
    }

    public int obtenerNivel() {
        //585 323
        robot.delay(1500);
        robot.mover(1265, 100);
        robot.clickIzquierdo(2000);
        robot.mover(585, 323);
        robot.doubleClickIzquierdo();
        String clip = robot.getClipboard();
        System.out.println("Nivel: " + clip);
        return Integer.parseInt(clip.trim());
    }

    public void inscribirEnTorneo(Bruto bruto) {
        //TODO CHE ACA HAY QUE VER XQ ME PARECE QUE CON /SUB TAMBIEN ANDA
        //637 455 y 638 548 y 638 411
        robot.delay(1500);
//        robot.mover(1265, 690);
//        robot.clickIzquierdo(4000);
//        robot.mover(637, 455);
//        robot.clickIzquierdo();
//        robot.mover(637, 548);
//        robot.clickIzquierdo();
//        robot.mover(637, 411);
//        robot.clickIzquierdo();
        if (navigateAManopla) {
            robot.mover(800, 78);
            robot.clickIzquierdo();
            robot.borrar();
            robot.escribir(bruto.getNombre() + ".elbruto.es/sub");
            robot.enter();
        } else {
            simpleWeb.navigate(bruto.getNombre() + ".elbruto.es/sub");
        }
        waitForDone();
    }
}
