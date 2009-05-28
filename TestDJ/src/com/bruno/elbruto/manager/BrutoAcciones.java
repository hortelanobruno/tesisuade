/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bruno.elbruto.manager;

import com.bruno.elbruto.util.MouseRobot;

/**
 *
 * @author Brunoli
 */
public class BrutoAcciones {

    private boolean done = false;
    private MouseRobot robot;

    public BrutoAcciones() {
        robot = new MouseRobot();
    }

    public synchronized void avisarDone() {
        System.out.println("LLego DONE");
        done = true;
    }

    public void waitForDone() {
        System.out.println("Entro waitForDone");
        done = false;
        while (!done) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
            }
        }
    }

    public void maxizarBrowser() {
        robot.delay(1500);
        robot.mover(300, 147);
        robot.doubleClickIzquierdo();
        robot.delay(2000);
    }

    public void pelear(Bruto bruto, String rival) {
        System.out.println("Peleando contra '" + rival + "'");
        robot.delay(1500);
        robot.mover(800, 78);
        robot.clickIzquierdo();
        robot.borrar();
        //http://nestornbloq.elbruto.es/vs/kascorro
        robot.escribir(bruto.getNombre() + ".elbruto.es/vs/" + rival);
        robot.enter();
        waitForDone();//robot.delay(10000);
//        robot.mover(1265, 100);
//        robot.clickIzquierdo(3000);
//        robot.mover(650, 670);
//        robot.clickIzquierdo();
//        robot.delay(6000);
//        robot.mover(650, 640);
//        robot.clickIzquierdo();
//        robot.escribir(rival);
//        robot.enter();
//        robot.delay(7000);
//        robot.mover(650, 675);
//        robot.clickIzquierdo();
//        robot.delay(3000);
        robot.mover(1265, 690);
        robot.clickIzquierdo(3000);
        robot.mover(650, 610);
        robot.clickIzquierdo();
        robot.delay(3000);
        irCellule(bruto);
//        robot.clickIzquierdo();
//        robot.backspace();
//        waitForDone();
    }

    public void ponerPassword(Bruto bruto) {
        System.out.println("Poniendo password");
        robot.delay(1500);
        robot.mover(800, 78);
        robot.clickIzquierdo();
        robot.borrar();
        robot.escribir(bruto.getNombre() + ".elbruto.es/login");
        robot.enter();
        waitForDone();//robot.delay(15000);
//        robot.mover(1265, 100);
//        robot.clickIzquierdo(3000);
//        robot.mover(650, 534);
//        robot.clickIzquierdo();
//        waitForDone();//robot.delay(5000);
        robot.mover(300, 350);
        robot.clickIzquierdo();
        robot.escribir(bruto.getPassword());
        robot.enter();
        waitForDone();//robot.delay(5000);
    }

    public void crearPassword(Bruto bruto) {
        System.out.println("Creando password");
        robot.delay(1500);
        robot.mover(800, 78);
        robot.clickIzquierdo();
        robot.borrar();
        robot.escribir(bruto.getNombre() + ".elbruto.es/cellule");
        robot.enter();
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
        System.out.println("Yendo a cellule");
        robot.delay(1500);
        robot.mover(800, 78);
        robot.clickIzquierdo();
        robot.borrar();
        robot.escribir(bruto.getNombre() + ".elbruto.es/cellule");
        robot.enter();
        waitForDone();//robot.delay(15000);
    }

    public Bruto crearBruto(String nombre) {
        //TODO
        return null;
    }

    private boolean verificarPeleasCompletadas() {
//        robot.delay(1500);
//        robot.mover(800, 78);
//        robot.clickIzquierdo();
//        robot.borrar();
//        robot.escribir(bruto.getNombre() + ".elbruto.es/cellule");
//        robot.enter();
//        robot.delay(15000);
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

    public int chequearPelea(Bruto bruto, String nombre) {
        //1 = gano 0 = perdio -1 = no se jugo
        //1026 276 858 262
        //1022 328 858 314
        //1029 382 860 366


        return -1;
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

    public void inscribirEnTorneo() {
        //637 455 y 638 548 y 638 411
        robot.delay(1500);
        robot.mover(1265, 690);
        robot.clickIzquierdo(4000);
        robot.mover(637, 455);
        robot.clickIzquierdo();
        robot.mover(637, 548);
        robot.clickIzquierdo();
        robot.mover(637, 411);
        robot.clickIzquierdo();
        waitForDone();
    }
}
