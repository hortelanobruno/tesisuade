/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bruno.elbruto.util;

import test.*;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

/**
 *    En laptop:  Resolution: 1280 x 800
 *    Label nombre nuevo bruto: 300,370
 *    Validar nuevo bruto: 300,630
 *    Flechita subir browser:   1265, 100
 *
 *
 *
 *
 * @author Brunoli
 */
public class MouseRobot {

    /*
     * COMO CREAR USUARIO
     *
     *mover(1265, 100);
    clickIzquierdo(3000);
    mover(300, 370);
    clickIzquierdo();
    escribir("qwerqwer");
    mover(300,630);
    clickIzquierdo();

    //Para saber si me creo el bruto tengo q esperar un rato y preguntar si me cambio
    //la url en la barra de direcciones, si cambio es porque lo creo, sino cambio es
    //xq o hubo error o existe

     *
     *
     * //COMO CARGAR PASSWORD


    //1ero ir a la cellula del bruto
    delay(1500);
    mover(1265, 100);
    clickIzquierdo(3000);
    mover(650, 534);
    clickIzquierdo();
    delay(5000);
    mover(300,350);
    clickIzquierdo();
    escribir("hortelano");
    enter();
     *
     *
     *      CREAR PASSWORD
     *
     *     delay(1500);
    mover(1265, 100);
    clickIzquierdo(3000);
    mover(300, 358);
    clickIzquierdo();
    delay(5000);
    mover(330,450);
    clickIzquierdo();
    escribir("hortelano");
    mover(330,500);
    clickIzquierdo();
    escribir("hortelano");
    mover(330,530);
    clickIzquierdo();
    delay(5000);
     *
     *
     *      PELEAR
     *
     *
     * delay(1500);
    mover(1265, 100);
    clickIzquierdo(3000);
    mover(650, 670);
    clickIzquierdo();
    delay(3000);
    mover(650, 640);
    clickIzquierdo();
    escribir("hulk");
    enter();
    delay(7000);
    mover(650, 675);
    clickIzquierdo();
    delay(3000);
    mover(1265, 690);
    clickIzquierdo(3000);
    mover(650, 610);
    clickIzquierdo();
    delay(3000);
    clickIzquierdo();
    backspace();
     *
     *
     */
    private Robot robot;
    private Map<String, Integer> abc;
    private int delayTyping = 100;
    private int delayMoving = 100;
    private int delayClick = 100;
    private TextTransfer textTransfer;

    public MouseRobot() {
        cargarAbc();
        try {
            robot = new Robot();
            textTransfer = new TextTransfer();
            delay(1500);



        } catch (AWTException ex) {
            System.out.println("Error robot");
        }
    }

    public MouseRobot(int delayTyping, int delayMoving, int delayClick) {
        this.delayMoving = delayMoving;
        this.delayTyping = delayTyping;
        this.delayClick = delayClick;
        cargarAbc();
        try {
            robot = new Robot();
        } catch (AWTException ex) {
            System.out.println("Error robot");
        }
    }

    public void doubleClickIzquierdo() {
        clickIzquierdo();
        clickIzquierdo();
    }

    public String getClipboard() {
        robot.keyPress(KeyEvent.VK_CONTROL);
        delay(1000);
        robot.keyPress(KeyEvent.VK_C);
        delay(1000);
        robot.keyRelease(KeyEvent.VK_C);
        delay(1000);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        delay(1000);
        return textTransfer.getClipboardContents();
    }

    public void clickIzquierdo() {
        delay(delayClick);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        delay(delayClick);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        delay(delayClick);
    }

    public void clickIzquierdo(int delayHolding) {
        delay(delayClick);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        delay(delayHolding);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        delay(delayClick);
    }

    public void delay(int delay) {
        robot.delay(delay);
    }

    public void mousePress() {
        robot.mousePress(InputEvent.BUTTON1_MASK);
    }

    public void mouseRelease() {
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    public void mover(int x, int y) {
        delay(delayMoving);
        robot.mouseMove(x, y);
        delay(delayMoving);
    }

    public void enter() {
        delay(delayTyping);
        robot.keyPress(KeyEvent.VK_ENTER);
        delay(delayTyping);
        robot.keyRelease(KeyEvent.VK_ENTER);
        delay(delayTyping);
    }

    public void backspace() {
        delay(delayTyping);
        robot.keyPress(KeyEvent.VK_BACK_SPACE);
        delay(delayTyping);
        robot.keyRelease(KeyEvent.VK_BACK_SPACE);
        delay(delayTyping);
    }

    public void escribir(String texto) {
        char[] letras = texto.toCharArray();
        if (letras.length > 0) {
            delay(delayTyping);
            char letra;
            for (int i = 0; i < letras.length; i++) {
                letra = letras[i];
                int code = Character.getNumericValue(letra);
                if (code < 10) {
                    //es numero
                    String le = new String("" + letra);
                    code = abc.get(le);
                    robot.keyPress(code);
                    delay(delayTyping);
                    robot.keyRelease(code);
                    delay(delayTyping);
                } else {
                    //es letra
                    String le = new String("" + letra);
                    le = le.toUpperCase();
                    code = abc.get(le);
                    robot.keyPress(code);
                    delay(delayTyping);
                    robot.keyRelease(code);
                    delay(delayTyping);
                }
            }
        }
    }

    public void borrar() {
        for (int i = 0; i < 100; i++) {
            robot.keyPress(KeyEvent.VK_BACK_SPACE);
            robot.keyRelease(KeyEvent.VK_BACK_SPACE);
        }
    }

    private void cargarAbc() {
        abc = new HashMap<String, Integer>();
        abc.put("A", 0x41);
        abc.put("B", 0x42);
        abc.put("C", 0x43);
        abc.put("D", 0x44);
        abc.put("E", 0x45);
        abc.put("F", 0x46);
        abc.put("G", 0x47);
        abc.put("H", 0x48);
        abc.put("I", 0x49);
        abc.put("J", 0x4A);
        abc.put("K", 0x4B);
        abc.put("L", 0x4C);
        abc.put("M", 0x4D);
        abc.put("N", 0x4E);
        abc.put("O", 0x4F);
        abc.put("P", 0x50);
        abc.put("Q", 0x51);
        abc.put("R", 0x52);
        abc.put("S", 0x53);
        abc.put("T", 0x54);
        abc.put("U", 0x55);
        abc.put("V", 0x56);
        abc.put("W", 0x57);
        abc.put("X", 0x58);
        abc.put("Y", 0x59);
        abc.put("Z", 0x5A);
        abc.put("0", 0x30);
        abc.put("1", 0x31);
        abc.put("2", 0x32);
        abc.put("3", 0x33);
        abc.put("4", 0x34);
        abc.put("5", 0x35);
        abc.put("6", 0x36);
        abc.put("7", 0x37);
        abc.put("8", 0x38);
        abc.put("9", 0x39);
        abc.put(":", 0x0201);
        abc.put("/", 0x6F);
        abc.put(".", 0x2E);
        abc.put("-", 0x2D);
    }
}
