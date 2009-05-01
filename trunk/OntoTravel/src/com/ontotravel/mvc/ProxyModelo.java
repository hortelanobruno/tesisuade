/*
 * Ingenieria de Sistemas II - 1C2008
 * Marzo/2008
 * 
 * Ejercicio 3 - Framework Model-View-Controller con Patron Observer
 *  
 */

package com.ontotravel.mvc;

public abstract class ProxyModelo
{
    protected Vista vista = null;

    protected void setVista(Vista v)
    {
        this.vista = v;
    }

	public Vista getVista() 
	{
		return vista;
	}
}
