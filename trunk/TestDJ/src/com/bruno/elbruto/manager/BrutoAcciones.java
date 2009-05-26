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

    private MouseRobot robot;

    public BrutoAcciones() {
        robot = new MouseRobot();
    }

    public void maxizarBrowser() {
        robot.delay(1500);
        robot.mover(300, 147);
        robot.doubleClickIzquierdo();
        robot.delay(2000);
    }

    public void pelear(Bruto bruto, String rival) {
        robot.delay(1500);
        robot.mover(800, 78);
        robot.clickIzquierdo();
        robot.borrar();
        //http://nestornbloq.elbruto.es/vs/kascorro
        robot.escribir(bruto.getNombre() + ".elbruto.es/vs/" + rival);
        robot.enter();
        robot.delay(10000);
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
        robot.clickIzquierdo();
        robot.backspace();
        robot.delay(5000);
    }

    public void ponerPassword(Bruto bruto) {
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
        robot.mover(650, 534);
        robot.clickIzquierdo();
        robot.delay(5000);
        robot.mover(300, 350);
        robot.clickIzquierdo();
        robot.escribir(bruto.getPassword());
        robot.enter();
        robot.delay(5000);
    }

    public void crearPassword(Bruto bruto) {
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

    private boolean verificarPeleasCompletadas(Bruto bruto) {
        robot.delay(1500);
        robot.mover(800, 78);
        robot.clickIzquierdo();
        robot.borrar();
        robot.escribir(bruto.getNombre() + ".elbruto.es/cellule");
        robot.enter();
        robot.delay(15000);
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
        if (!verificarPeleasCompletadas(bruto)) {
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
}
