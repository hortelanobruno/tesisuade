package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import varios.Recibo;
import varios.Usuario;
//boca
public class DBManager {

    private static DBManager manager = null;
    private ManagerUsuarios managerUsuarios = null;
    private ManagerRecibos managerRecibos = null;

    protected DBManager() {
        managerUsuarios = new ManagerUsuarios();
        managerRecibos = new ManagerRecibos();
    }

    public static DBManager getInstance() {
        if (manager == null) {
            manager = new DBManager();
        }
        return manager;
    }

    public boolean isConnected() {
        Conexion con = new Conexion();
        Conexion.driverOdbc();
        if (con.abrirConexion()) {
            return true;
        }
        return false;
    }

    public String borrarArea(String usuario) {
        return managerUsuarios.borrarArea(usuario);
    }

    public String actualizarArea(Usuario usuario) {
        return managerUsuarios.actualizarArea(usuario);
    }

    public String loginUsuario(String usuario, String password) {
        return managerUsuarios.loginUsuario(usuario,password);
    }

    public void confirmarRecibos(List<Integer> recibos) {
        managerRecibos.confirmarRecibos(recibos);
    }

    public List<Recibo> obtenerRecibosAConfirmar(String usuario) {
        return managerRecibos.obtenerRecibosAConfirmar(usuario);
    }

    public Recibo obtenerReciboAConfirmar(String numero) {
        return managerRecibos.obtenerReciboAConfirmar(numero);
    }

    public List<String> datosUsuario(Object usuario) {
        return managerUsuarios.datosUsuario(usuario);
    }

    public List<Integer> obtenerRecibosConfirmados() {
        return managerRecibos.obtenerRecibosConfirmados();
    }

    public List<Integer> obtenerRecibosEntregados() {
        return managerRecibos.obtenerRecibosEntregados();
    }

    public List<Integer> obtenerRecibosPendientes(String usuario) {
        return managerRecibos.obtenerRecibosPendientes(usuario);
    }

    public String addArea(Usuario usu) {
        return managerUsuarios.addArea(usu);
    }

    public String[] operatorInspectorCajeroList() {
        return managerUsuarios.operatorInspectorCajeroList();
    }

    public String[] operatorList() {
        return managerUsuarios.operatorList();
    }

    public String anularRecibo(String numero, String motivo, String fecha) {
        return managerRecibos.anularRecibo(numero,motivo,fecha);
    }

    public boolean chequearBorrarUsuario(String usuario) {
        return managerRecibos.chequearBorrarUsuario(usuario);
    }

    public HashMap<Integer, List<String>> obtenerRecibosCompletadas(String usuario) {
        return managerRecibos.obtenerRecibosCompletadas(usuario);
    }

    public HashMap<Integer, List<String>> obtenerRecibosCompletadasRendidos(String usuario) {
        return managerRecibos.obtenerRecibosCompletadasRendidos(usuario);
    }

    public HashMap<Integer, List<String>> obtenerRecibosAnuladas(String usuario) {
        return managerRecibos.obtenerRecibosAnuladas(usuario);
    }

    public HashMap<Integer, List<String>> obtenerRecibosAnuladasRendidos(String usuario) {
        return managerRecibos.obtenerRecibosAnuladasRendidos(usuario);
    }

    public HashMap<Integer, List<String>> obtenerRecibosExtraviadas(String usuario) {
        return managerRecibos.obtenerRecibosExtraviadas(usuario);
    }

    public HashMap<Integer, List<String>> obtenerRecibosExtraviadasRendidos(String usuario) {
        return managerRecibos.obtenerRecibosExtraviadasRendidos(usuario);
    }

    public String reciboExtraviada(String numero, String motivo, String fecha) {
        return managerRecibos.reciboExtraviada(numero,motivo,fecha);
    }

    public String completarReciboChequePorOperador(Recibo rec){
        return managerRecibos.completarReciboChequePorOperador(rec);
    }

    public String completarReciboEfectivoPorOperador(Recibo rec) {
        return managerRecibos.completarReciboEfectivoPorOperador(rec);
    }

    public String actualizarRecibo(Recibo rec) {
        return managerRecibos.actualizarRecibo(rec);
    }

    public String cargarRecibos(String op, String min, String max) {
        return managerRecibos.cargarRecibos(op,min,max);
    }

    public String devolverRecibos(String op, String min, String max) {
        return managerRecibos.devolverRecibos(op,min,max);
    }

    public String[] responsableList() {
        return managerUsuarios.responsableList();
    }

    public String[] sectorList() {
        return managerUsuarios.responsableList();
    }

    public String resetPassword(String usuario, String password) {
        return managerUsuarios.resetPassword(usuario,password);
    }
}
