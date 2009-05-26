/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Brunoli
 */
public class BrutoThread implements Runnable {

    private TestRobot robot;
    private SimpleWebBrowserExample webBrowser;

    public BrutoThread(SimpleWebBrowserExample aThis) {
        this.webBrowser = aThis;
        robot = new TestRobot();
    }

    @Override
    public void run() {
        robot.delay(10000);
        List<String> rivales = new ArrayList<String>();
        rivales.add("neee");
        rivales.add("sexologo");
        rivales.add("camiloo");
        //iniciarRobot("qaswzx","hortelano",rivales);
        //System.out.println(webBrowser.getURL());
    }

    private void iniciarRobot(String bruto, String password, List<String> rivales) {
        //maxizarBrowser();
        System.out.println("Iniciando Robot......");
//        System.out.println("Creando passowrd...");
//        crearPassword("fdsavcxz", "hortelano");
//        System.out.println("Fin crear password.");
        System.out.println("Ingresando password...");
        ponerPassword(bruto, password);
        System.out.println("Fin ingresar password.");
        for (String rival : rivales) {
            System.out.println("Peleando....");
            pelear(bruto, rival);
        }
    }

    private void maxizarBrowser(){
        robot.delay(1500);
        robot.mover(300, 147);
        robot.doubleClickIzquierdo();
        robot.delay(2000);
    }

    private void pelear(String bruto, String rival) {
        robot.delay(1500);
        robot.mover(800, 78);
        robot.clickIzquierdo();
        robot.borrar();
        robot.escribir(bruto + ".elbruto.es/cellule");
        robot.enter();
        robot.delay(15000);
        robot.delay(1500);
        robot.mover(1265, 100);
        robot.clickIzquierdo(3000);
        robot.mover(650, 670);
        robot.clickIzquierdo();
        robot.delay(6000);
        robot.mover(650, 640);
        robot.clickIzquierdo();
        robot.escribir(rival);
        robot.enter();
        robot.delay(7000);
        robot.mover(650, 675);
        robot.clickIzquierdo();
        robot.delay(3000);
        robot.mover(1265, 690);
        robot.clickIzquierdo(3000);
        robot.mover(650, 610);
        robot.clickIzquierdo();
        robot.delay(3000);
        robot.clickIzquierdo();
        robot.backspace();
        robot.delay(5000);
    }

    private void ponerPassword(String bruto, String password) {
        robot.delay(1500);
        robot.mover(800, 78);
        robot.clickIzquierdo();
        robot.borrar();
        robot.escribir(bruto + ".elbruto.es/cellule");
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
        robot.escribir(password);
        robot.enter();
        robot.delay(5000);
    }

    private void crearPassword(String bruto, String password) {
        robot.delay(1500);
        robot.mover(800, 78);
        robot.clickIzquierdo();
        robot.borrar();
        robot.escribir(bruto + ".elbruto.es/cellule");
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
        robot.escribir(password);
        robot.mover(330, 500);
        robot.clickIzquierdo();
        robot.escribir(password);
        robot.mover(330, 530);
        robot.clickIzquierdo();
        robot.delay(5000);
    }


    public boolean verificarPeleasCompletadas(){
        robot.mover(550, 390);
        robot.mousePress();
        robot.mover(680, 420);
        robot.mouseRelease();
        robot.delay(1000);
        String clip = robot.getClipboard();
        if(clip.contains("3 nuevos")){
            return true;
        }else{
            return false;
        }
    }

}
