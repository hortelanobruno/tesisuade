/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bruno.elbruto.manager;

/**
 *
 * @author Brunoli
 */
public class Bruto {

    private String nombre;
    private String password;
    private int nivel;
    private boolean cambioCoordenadaPorClasificacion;

    public Bruto() {
    }

    public Bruto(String nombre, String password, int nivel) {
        this.nombre = nombre;
        this.password = password;
        this.nivel = nivel;
    }

    public boolean isCambioCoordenadaPorClasificacion() {
        return cambioCoordenadaPorClasificacion;
    }

    public void setCambioCoordenadaPorClasificacion(boolean cambioCoordenadaPorClasificacion) {
        this.cambioCoordenadaPorClasificacion = cambioCoordenadaPorClasificacion;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getNivel() {
        return nivel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
