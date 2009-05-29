/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bruno.elbruto.db;

import com.bruno.elbruto.db.persistencia.controller.BrutoJpaController;
import com.bruno.elbruto.db.persistencia.controller.PeleaJpaController;
import com.bruno.elbruto.db.persistencia.controller.exceptions.NonexistentEntityException;
import com.bruno.elbruto.db.persistencia.controller.exceptions.PreexistingEntityException;
import com.bruno.elbruto.db.persistencia.entities.PeleaPK;
import com.bruno.elbruto.manager.Bruto;
import com.bruno.elbruto.manager.Pelea;
import com.bruno.elbruto.util.LoggerClass;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class DBManager {

    private BrutoJpaController brutoJPA;
    private PeleaJpaController peleaJPA;

    public DBManager() {
        brutoJPA = new BrutoJpaController();
        peleaJPA = new PeleaJpaController();
    }

    public void create(Bruto bruto) {
        try {
            com.bruno.elbruto.db.persistencia.entities.Bruto br = new com.bruno.elbruto.db.persistencia.entities.Bruto();
            br.setNivel(bruto.getNivel());
            br.setNombre(bruto.getNombre());
            br.setPassword(bruto.getPassword());
            br.setPropietario(bruto.isPropietario());
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
        } catch (PreexistingEntityException ex) {
            LoggerClass.getInstance().error("Error al crear una pelea en la base", ex);
        } catch (Exception ex) {
            LoggerClass.getInstance().error("Error al crear una pelea en la base", ex);
        }
    }

    public void edit(Bruto bruto) {
        try {
            com.bruno.elbruto.db.persistencia.entities.Bruto br = new com.bruno.elbruto.db.persistencia.entities.Bruto();
            br.setNivel(bruto.getNivel());
            br.setNombre(bruto.getNombre());
            br.setPassword(bruto.getPassword());
            br.setPropietario(bruto.isPropietario());
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
}
