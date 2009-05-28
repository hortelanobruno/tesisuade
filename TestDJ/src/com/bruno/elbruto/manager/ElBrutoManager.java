/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bruno.elbruto.manager;

import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import com.bruno.elbruto.browser.SimpleWebBrowser;
import com.bruno.elbruto.db.persistencia.BrutoJpaController;
import com.bruno.elbruto.db.persistencia.PeleaJpaController;
import com.bruno.elbruto.db.persistencia.exceptions.NonexistentEntityException;
import com.bruno.elbruto.db.persistencia.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Brunoli
 */
public class ElBrutoManager {

    private SimpleWebBrowser simpleWeb;
    private BrutoAcciones brutoAcciones;
    private PeleaJpaController peleaJPA;
    private BrutoJpaController brutoJPA;

    public ElBrutoManager() {
    }

    public void init() {
        brutoAcciones = new BrutoAcciones();
        peleaJPA = new PeleaJpaController();
        brutoJPA = new BrutoJpaController();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
        }
        System.out.println("Iniciando");
        iniciarModo1();
//        List<Bruto> brutos = obtenerBrutosParaPelear();
//        for (Bruto bruto : brutos) {
//            //pelearModo1(bruto);
//            inscribirEnTorneo(bruto);
//        }
    }

    public void avisarDone() {
        brutoAcciones.avisarDone();
    }

    public void setWebBrowser(SimpleWebBrowser aThis) {
        this.simpleWeb = aThis;
    }

    private int chequearPelea(Bruto bruto, String nombre) {
        //1 = gano 0 = perdio -1 = no se jugo
        //String html = simpleWeb.getSourceCode();
        //html = html.split("<DIV class=logs>")[1];
        return 1;
    }

    private void iniciarModo1() {
        chequearPelea(null, "");
        List<Bruto> brutos = brutoJPA.findBrutosPropietarios();
        int cantPeleas;
        for (Bruto bruto : brutos) {
            pelearModo1(bruto);
        }
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

    private LinkedList<Bruto> obtenerRivalesPara(Bruto bruto, int cant) {
        return brutoJPA.findRivales(bruto, cant);
//        LinkedList<String> rivales = new LinkedList<String>();
//        //por nivel
//        switch (bruto.getNivel()) {
//            case 2:
//                rivales.add("sexologo");
//                rivales.add("camiloo");
//                rivales.add("neee");
//                break;
//            case 8:
//                rivales.add("guilios");
//                rivales.add("l-shakas");
//                rivales.add("azm");
//                break;
//            case 9:
//                rivales.add("kroes");
//                rivales.add("11clan11");
//                rivales.add("m0renaa");
//                break;
//        }
//        return rivales;
    }

    private void pelearModo1(Bruto bruto) {
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
        int cantPeleas = peleaJPA.findCantPeleas(bruto, new Date());
        if (cantPeleas < 3) {
            LinkedList<Bruto> rivales = obtenerRivalesPara(bruto, 3 - cantPeleas);
            Pelea pelea;
            Bruto rival;
            for (int i = 0; i < 3 - cantPeleas; i++) {
                try {
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
                    peleaJPA.create(pelea);
                } catch (PreexistingEntityException ex) {
                    System.out.println("ERROR AL GRABAR LA PELEA");
                } catch (Exception ex) {
                    System.out.println("ERROR AL GRABAR LA PELEA");
                }
            }
        }
        int nivel = brutoAcciones.obtenerNivel();
        if (nivel != bruto.getNivel()) {
            try {
                //actualizar nivel
                bruto.setNivel(nivel);
                brutoJPA.edit(bruto);
            } catch (NonexistentEntityException ex) {
                System.out.println("ERROR ACTUALIZAR BRUTO");
            } catch (Exception ex) {
                System.out.println("ERROR ACTUALIZAR BRUTO");
            }
        }
    }

    private void inscribirEnTorneo(Bruto bruto) {
        if (!bruto.getPassword().trim().isEmpty()) {
            brutoAcciones.ponerPassword(bruto);
        } else {
            brutoAcciones.irCellule(bruto);
        }
        brutoAcciones.inscribirEnTorneo();
    }
}
