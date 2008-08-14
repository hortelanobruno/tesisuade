/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vistas;

import gui.FramePrincipal;
import modelo.BusinessDelegate;
import mvcframework.Vista;

/**
 *
 * @author Administrador
 */
public class VistaSinonimos extends Vista{

    private FramePrincipal ref;

    public VistaSinonimos(BusinessDelegate mod) {
            super(mod);
    }

    public void actualizar() {
            getRef().getPanelSinonimos().update();
    }

    public void agregarSin(boolean b){
        ref.getPanelSinonimos().setAgregarSinonimo(b);
        ref.getPanelSinonimos().setAgregarTraduccion(false);
        ref.getPanelSinonimos().setRemoverSinonimo(false);
        ref.getPanelSinonimos().setRemoverTraduccion(false);
    }
    
    public void agregarTra(boolean b){
        ref.getPanelSinonimos().setAgregarSinonimo(false);
        ref.getPanelSinonimos().setAgregarTraduccion(b);
        ref.getPanelSinonimos().setRemoverSinonimo(false);
        ref.getPanelSinonimos().setRemoverTraduccion(false);
    }
    
    public void removerSin(boolean b){
        ref.getPanelSinonimos().setAgregarSinonimo(false);
        ref.getPanelSinonimos().setAgregarTraduccion(false);
        ref.getPanelSinonimos().setRemoverSinonimo(b);
        ref.getPanelSinonimos().setRemoverTraduccion(false);
    }
    
    public void removerTra(boolean b){
        ref.getPanelSinonimos().setAgregarSinonimo(false);
        ref.getPanelSinonimos().setAgregarTraduccion(false);
        ref.getPanelSinonimos().setRemoverSinonimo(false);
        ref.getPanelSinonimos().setRemoverTraduccion(b);
    }
    
    public void cargarInstancia(boolean instancia){
        ref.getPanelSinonimos().setCargarInstancia(instancia);
    }
    
    public void cargarArbol(boolean b){
        ref.getPanelSinonimos().setCargarArbol(b);
    }
    
    public FramePrincipal getRef() {
        return ref;
    }

    public void setRef(FramePrincipal ref) {
        this.ref = ref;
    }
}
