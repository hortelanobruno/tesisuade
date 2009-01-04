/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import java.util.List;
import vo.CategoriaVO;
import vo.EventoVO;
import vo.MercaderiaVO;

/**
 *
 * @author Administrador
 */
public class DBManager {

    private static DBManager manager = null;
    private ManagerCategorias managerCategorias;
    private ManagerEventos managerEventos;
    private ManagerMercaderias managerMercaderias;

    protected DBManager() {
        managerCategorias = new ManagerCategorias();
        managerEventos = new ManagerEventos();
        managerMercaderias = new ManagerMercaderias();
    }

    public static DBManager getInstance() {
        if (manager == null) {
            manager = new DBManager();
        }
        return manager;
    }

    public boolean isConnected() {
        //TODO - Ver si se puede hacer con jpa
        Conexion con = new Conexion();
        Conexion.driverOdbc();
        if (con.abrirConexion()) {
            return true;
        }
        return false;
    }

    public List<CategoriaVO> getCategorias(){
        return managerCategorias.getCategorias();
    }

    public List<EventoVO> getEventos(){
        return managerEventos.getEventos();
    }

    public void actualizarEvento(String viejo, String nuevo) {
        managerEventos.actualizarEvento(viejo,nuevo);
    }

    public String createMercaderia(MercaderiaVO mercaderia){
        return managerMercaderias.createMercaderia(mercaderia);
    }

    public List<MercaderiaVO> getMercaderias(){
        return managerMercaderias.getMercaderias();
    }

    public String removeMercaderia(String mercaderia){
        return managerMercaderias.removeMercaderia(mercaderia);
    }
}
