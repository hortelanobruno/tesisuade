/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bruno.elbruto.manager;

import com.bruno.elbruto.browser.SimpleWebBrowser;
import com.bruno.elbruto.db.DBManager;
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
    private DBManager dbManager;

    public ElBrutoManager() {
    }

    public void init() {
        brutoAcciones = new BrutoAcciones();
        dbManager = new DBManager();
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

    private int chequearPelea(Bruto bruto, String rival) {
        //1 = gano 0 = perdio -1 = no se jugo
        String html = simpleWeb.getSourceCode();
        String vencido  = "<divclass='logs'>"+html.split("<divclass='logs'>")[1];
        vencido = vencido.split("</td></tr></table></div></div><div class='celluleFoot'></div>")[0];
        System.out.println(vencido);
        String[] asd = vencido.split("onmouseout");
        String a;
        for(int i=1;i<asd.length;i++){
            a = asd[i].toLowerCase();
            char[] chars = a.toCharArray();
            int index1 = 0,index2 = 0;
            for(int j=0; j<chars.length;j++){
                if(chars[j]=='>'){
                    index1=j;
                }
                if(chars[j]=='<'){
                    index2=j;
                    break;
                }
            }
            String pelea = a.substring(index1+1, index2-1);
            if(pelea.contains(rival.toLowerCase())){
                if(pelea.startsWith("tubruto")){
                    return 1;
                }else{
                    return 0;
                }
            }
        }
        return -1;
    }

    private void iniciarModo1() {
        chequearPelea(null, "");
        List<Bruto> brutos = dbManager.findBrutosPropietarios();
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
        return dbManager.findRivales(bruto, cant);
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
        int cantPeleas = dbManager.findCantPeleas(bruto, new Date());
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
                    dbManager.create(pelea);
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
                dbManager.edit(bruto);
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
