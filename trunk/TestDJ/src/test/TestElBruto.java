/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import com.bruno.elbruto.manager.Bruto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Brunoli
 */
public class TestElBruto {

    private List<Bruto> brutos;
    private List<Bruto> rivales;
    
    public TestElBruto() {
        cargarBrutos();
        cargarRivales();
    }



    public static void main(String[] args) {
        new TestElBruto();
    }

    //http://fdsavcxz.elbruto.es/setPass
    private void cargarBrutos() {
        brutos = new ArrayList<Bruto>();
        cargarBruto("qwerfdsa", 8);
        cargarBruto("asdfvcxz", 8);
        cargarBruto("brunoli", 9);
        cargarBruto("brunoli2", 9);
        cargarBruto("nestornbloq", 9);
        cargarBruto("fdsavcxz", 1);
        cargarBruto("qaswzx", 1);
    }

    private void cargarRivales() {
        rivales = new ArrayList<Bruto>();
        cargarRival("m0renaa",7);
        cargarRival("11clan11",7);
        cargarRival("kroes",7);
        cargarRival("azm",6);
        cargarRival("guilios",6);
        cargarRival("l-shakas",6);
        /*nivel1:

gooll
ednna
hulk
         nivel 1:

mamar
restar
gabarro
camiloo
neee
sexologo*/

    }

    private void cargarBruto(String nombre, int nivel){
        Bruto b = new Bruto();
        b.setNombre(nombre);
        b.setNivel(nivel);
        brutos.add(b);
    }

    private void cargarRival(String nombre, int nivel){
        Bruto b = new Bruto();
        b.setNombre(nombre);
        b.setNivel(nivel);
        rivales.add(b);
    }
}
