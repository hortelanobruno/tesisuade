/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bruno.elbruto.db;

import com.bruno.elbruto.db.persistencia.controller.AlumnoJpaController;
import com.bruno.elbruto.db.persistencia.controller.BrutoJpaController;
import com.bruno.elbruto.db.persistencia.controller.NombreJpaController;
import com.bruno.elbruto.db.persistencia.controller.PeleaJpaController;
import com.bruno.elbruto.db.persistencia.controller.PeleasCompletadasJpaController;
import com.bruno.elbruto.db.persistencia.controller.exceptions.NonexistentEntityException;
import com.bruno.elbruto.db.persistencia.controller.exceptions.PreexistingEntityException;
import com.bruno.elbruto.db.persistencia.entities.Alumno;
import com.bruno.elbruto.db.persistencia.entities.PeleaPK;
import com.bruno.elbruto.db.persistencia.entities.PeleasCompletadas;
import com.bruno.elbruto.db.persistencia.entities.PeleasCompletadasPK;
import com.bruno.elbruto.manager.Bruto;
import com.bruno.elbruto.manager.Pelea;
import com.bruno.elbruto.util.LoggerClass;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Administrator
 */
public class DBManager {

    private BrutoJpaController brutoJPA;
    private PeleaJpaController peleaJPA;
    private NombreJpaController nombreJPA;
    private AlumnoJpaController alumnoJPA;
    private PeleasCompletadasJpaController peleasComJPA;

    public DBManager() {
        brutoJPA = new BrutoJpaController();
        peleaJPA = new PeleaJpaController();
        nombreJPA = new NombreJpaController();
        alumnoJPA = new AlumnoJpaController();
        peleasComJPA = new PeleasCompletadasJpaController();
    }

    public boolean chequearIPUsada(String ip, Bruto ancestro) {
        return alumnoJPA.chequearIPUsada(ip, ancestro);
    }

    public void crearPeleasCompletadas(String nombre) {
        try {
            PeleasCompletadas p = new PeleasCompletadas(new Date(), nombre);
            peleasComJPA.create(p);
        } catch (PreexistingEntityException ex) {
            LoggerClass.getInstance().error("Error al cargar en la base la pelea completada");
        } catch (Exception ex) {
            LoggerClass.getInstance().error("Error al cargar en la base la pelea completada");
        }
    }

    public void create(Alumno alumno) {
        try {
            alumnoJPA.create(alumno);
        } catch (PreexistingEntityException ex) {
            LoggerClass.getInstance().error("Error al crear el alumno en la base");
        } catch (Exception ex) {
            LoggerClass.getInstance().error("Error al crear el alumno en la base");
        }
    }

    public void create(Bruto bruto) {
        try {
            com.bruno.elbruto.db.persistencia.entities.Bruto br = new com.bruno.elbruto.db.persistencia.entities.Bruto();
            br.setNivel(bruto.getNivel());
            br.setNombre(bruto.getNombre());
            br.setPassword(bruto.getPassword());
            br.setPropietario(bruto.isPropietario());
            br.setVictorias(0L);
            brutoJPA.create(br);
        } catch (PreexistingEntityException ex) {
        } catch (Exception ex) {
        }
    }

    public void create(Pelea pelea) {
        try {
            com.bruno.elbruto.db.persistencia.entities.Pelea pel = new com.bruno.elbruto.db.persistencia.entities.Pelea();
            PeleaPK pk = new PeleaPK();
            pk.setFecha(pelea.getFecha());
            pk.setNombre(pelea.getBruto().getNombre());
            pk.setRival(pelea.getRival().getNombre());
            pel.setPeleaPK(pk);
            pel.setVictoria(pelea.isVictoria());
            peleaJPA.create(pel);
            if (pelea.isVictoria()) {
                //Gano bruto
                brutoJPA.aumentarVictoria(pelea.getBruto().getNombre());
                brutoJPA.disminuirDerrota(pelea.getRival().getNombre());
            } else {
                //Gano rival
                brutoJPA.aumentarVictoria(pelea.getRival().getNombre());
                brutoJPA.disminuirDerrota(pelea.getBruto().getNombre());
            }
        } catch (PreexistingEntityException ex) {
            LoggerClass.getInstance().error("Error al crear una pelea en la base", ex);
        } catch (Exception ex) {
            LoggerClass.getInstance().error("Error al crear una pelea en la base", ex);
        }
    }

    public void actualizarNivel(Bruto bruto) {
        try {
            com.bruno.elbruto.db.persistencia.entities.Bruto br = brutoJPA.findBruto(bruto.getNombre());
            br.setNivel(bruto.getNivel());
            brutoJPA.edit(br);
        } catch (NonexistentEntityException ex) {
            LoggerClass.getInstance().error("Error al editar al bruto en la base", ex);
        } catch (Exception ex) {
            LoggerClass.getInstance().error("Error al editar al bruto en la base", ex);
        }
    }

    public Bruto findAncestro() {
        com.bruno.elbruto.db.persistencia.entities.Bruto br = brutoJPA.findAncestro();
        Bruto bruto = new Bruto();
        bruto.setNivel(br.getNivel());
        bruto.setNombre(br.getNombre());
        bruto.setPassword(br.getPassword());
        bruto.setPropietario(br.getPropietario());
        return bruto;
    }

    public Bruto findBruto(String string) {
        com.bruno.elbruto.db.persistencia.entities.Bruto bruto = brutoJPA.findBruto(string);
        Bruto br = new Bruto();
        br.setNombre(bruto.getNombre());
        br.setNivel(bruto.getNivel());
        br.setPassword(bruto.getPassword());
        br.setPropietario(bruto.getPropietario());
        return br;
    }

    public List<Bruto> findBrutosPropietarios() {
        List<com.bruno.elbruto.db.persistencia.entities.Bruto> brutos = brutoJPA.findBrutosPropietarios();
        return convertirBrutos(brutos);
    }

    public int findCantPeleas(Bruto bruto, Date date) {
        return peleaJPA.findCantPeleas(bruto, date);
    }

    public LinkedList<Bruto> findRivales(Bruto bruto, int cant) {
        return new LinkedList<Bruto>(convertirBrutos(brutoJPA.findRivales(bruto, cant)));
    }

    public boolean tieneQPelear(String nombre) {
        if(peleasComJPA.findPeleasCompletadas(new PeleasCompletadasPK(new Date(),nombre))==null){
            return true;
        }else{
            return false;
        }
    }

    private List<Bruto> convertirBrutos(List<com.bruno.elbruto.db.persistencia.entities.Bruto> brutos) {
        List<Bruto> brs = new ArrayList<Bruto>();
        Bruto br;
        for (com.bruno.elbruto.db.persistencia.entities.Bruto bruto : brutos) {
            br = new Bruto();
            br.setNivel(bruto.getNivel());
            br.setNombre(bruto.getNombre());
            br.setPassword(bruto.getPassword());
            br.setPropietario(bruto.getPropietario());
            brs.add(br);
        }
        return brs;
    }

    public String randomBrutoName() {
        String name = nombreJPA.random();
        int cantChars = (int) ((Math.random() * 7));
        String str = new String("1234567890");
        StringBuffer sb = new StringBuffer();
        Random r = new Random();
        int te = 0;
        for (int i = 1; i <= cantChars; i++) {
            te = r.nextInt(10);
            sb.append(str.charAt(te));
        }
        return (name + sb.toString()).toLowerCase();
    }
}
