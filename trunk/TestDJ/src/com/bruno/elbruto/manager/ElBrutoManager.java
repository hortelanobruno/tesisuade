/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bruno.elbruto.manager;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Brunoli
 */
public class ElBrutoManager {

    
    private BrutoAcciones brutoAcciones;

    public ElBrutoManager() {
    }

    public void init() {
        brutoAcciones = new BrutoAcciones();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
        }
        System.out.println("Iniciando");
        List<Bruto> brutos = obtenerBrutosParaPelear();
        for (Bruto bruto : brutos) {
            //pelearModo1(bruto);
            inscribirEnTorneo(bruto);
        }
    }

    public void avisarDone() {
        brutoAcciones.avisarDone();
    }

    private List<Bruto> obtenerBrutosParaPelear() {
        List<Bruto> brutos = new ArrayList<Bruto>();
        brutos.add(new Bruto("qwerfdsa", "hortelano", 8));
        brutos.add(new Bruto("asdfvcxz", "", 8));
        brutos.add(new Bruto("brunoli2", "hortelano", 9));
        brutos.add(new Bruto("brunoli", "hortelano", 9));
        brutos.add(new Bruto("nestornbloq", "hortelano", 9));
        brutos.add(new Bruto("qaswzx", "hortelano", 3));
        brutos.add(new Bruto("fdsavcxz", "hortelano", 3));
        return brutos;
    }

    private LinkedList<String> obtenerRivalesPara(Bruto bruto, int cant) {
        LinkedList<String> rivales = new LinkedList<String>();
        //por nivel
        switch (bruto.getNivel()) {
            case 2:
                rivales.add("sexologo");
                rivales.add("camiloo");
                rivales.add("neee");
                break;
            case 8:
                rivales.add("guilios");
                rivales.add("l-shakas");
                rivales.add("azm");
                break;
            case 9:
                rivales.add("kroes");
                rivales.add("11clan11");
                rivales.add("m0renaa");
                break;
        }
        return rivales;
    }

    private void pelearModo1(Bruto bruto) {
        //Ingreso el password si lo tiene
        if (!bruto.getPassword().trim().isEmpty()) {
            brutoAcciones.ponerPassword(bruto);
        }else{
            brutoAcciones.irCellule(bruto);
        }
        //Obtengo la cantidad de peleas que tengo
        int cant = brutoAcciones.obtenerCantidadPeleas(bruto);
        if (cant > 0) {
            LinkedList<String> rivales = obtenerRivalesPara(bruto, cant);
            for (int i = 0; i < cant; i++) {
                brutoAcciones.pelear(bruto, rivales.poll());
            }
        }
        int nivel = brutoAcciones.obtenerNivel();
        if(nivel != bruto.getNivel()){
            //actualizar nivel
            bruto.setNivel(nivel);
        }
    }

    private void inscribirEnTorneo(Bruto bruto) {
        if (!bruto.getPassword().trim().isEmpty()) {
            brutoAcciones.ponerPassword(bruto);
        }else{
            brutoAcciones.irCellule(bruto);
        }
        brutoAcciones.inscribirEnTorneo();
    }
}
