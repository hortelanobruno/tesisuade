/*
 * Ingenieria de Sistemas II - 1C2008
 * Marzo/2008
 * 
 * Ejercicio 3 - Framework Model-View-Controller con Patron Observer
 *  
 */

package mvcframework;

public abstract class Vista
{
    protected ProxyModelo modelo;
    protected Controlador controlador;

    public Vista(ProxyModelo bd) 
    {
        modelo = bd;
        bd.setVista(this);
    }

    public void addControlador(Controlador cp)
    {
        controlador = cp;
    }

    public ProxyModelo getModelo() 
    {
        return modelo;
    }

    public Controlador getControlador() 
    {
        return controlador;
    }
    
    public abstract void actualizar(); 
}
