package db;

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

    public String actualizarArea(String resposnable, Usuario usuario) {
        return managerUsuarios.actualizarArea(resposnable, usuario);
    }

    public String loginUsuario(String usuario, String password) {
        return managerUsuarios.loginUsuario(usuario, password);
    }

    public void confirmarRecibos(List<Integer> recibos) {
        managerRecibos.confirmarRecibos(recibos);
    }

    public String totalEfectivoOperador(String usuario) {
        return managerRecibos.totalEfectivoOperador(usuario);
    }

    public String totalChequeOperador(String usuario) {
        return managerRecibos.totalChequeOperador(usuario);
    }

    public List<Recibo> obtenerRecibosAConfirmar(String responsable) {
        return managerRecibos.obtenerRecibosAConfirmar(responsable);
    }

    public List<Recibo> obtenerRecibosAConfirmarPorUsuario(String usuario) {
        return managerRecibos.obtenerRecibosAConfirmarPorUsuario(usuario);
    }

    public Recibo obtenerReciboAConfirmar(String numero) {
        return managerRecibos.obtenerReciboAConfirmar(numero);
    }

    public List<String> datosUsuario(Object usuario) {
        return managerUsuarios.datosUsuario(usuario);
    }

    public List<String> datosResponsable(Object responsable) {
        return managerUsuarios.datosResponsable(responsable);
    }

    public List<Integer> obtenerRecibosConfirmados() {
        return managerRecibos.obtenerRecibosConfirmados();
    }

    public List<Integer> obtenerRecibosEntregados() {
        return managerRecibos.obtenerRecibosEntregados();
    }

    public List<Integer> obtenerRecibosPendientes(String responsable) {
        return managerRecibos.obtenerRecibosPendientes(responsable);
    }

    public List<Integer> obtenerRecibosPendientesDeUsuario(String usuario) {
        return managerRecibos.obtenerRecibosPendientesDeUsuario(usuario);
    }

    public String addArea(Usuario usu) {
        return managerUsuarios.addArea(usu);
    }

    public String obtenerTipoUsuario(String numero) {
        return managerUsuarios.obtenerTipoUsuario(numero);
    }

    public String[] operatorInspectorCajeroList() {
        return managerUsuarios.operatorInspectorCajeroList();
    }

    public String[] operatorInspectorListUsuario(){
        return managerUsuarios.operatorInspectorListUsuario();
    }

    public String[] operatorInspectorList() {
        return managerUsuarios.operatorInspectorList();
    }

    public String[] operatorList() {
        return managerUsuarios.operatorList();
    }

    public String anularRecibo(String numero, String motivo, String fecha) {
        return managerRecibos.anularRecibo(numero, motivo, fecha);
    }

    public boolean chequearBorrarResponsable(String responsable) {
        return managerRecibos.chequearBorrarResponsable(responsable);
    }

    public List<Recibo> obtenerRecibosCompletadas(String usuario) {
        return managerRecibos.obtenerRecibosCompletadas(usuario);
    }

    public List<Recibo> obtenerRecibosCompletadasRendidos(String usuario) {
        return managerRecibos.obtenerRecibosCompletadasRendidos(usuario);
    }

    public List<Recibo> obtenerRecibosAnuladas(String usuario) {
        return managerRecibos.obtenerRecibosAnuladas(usuario);
    }

    public List<Recibo> obtenerRecibosAnuladasRendidos(String usuario) {
        return managerRecibos.obtenerRecibosAnuladasRendidos(usuario);
    }

    public List<Recibo> obtenerRecibosExtraviadas(String usuario) {
        return managerRecibos.obtenerRecibosExtraviadas(usuario);
    }

    public List<Recibo> obtenerRecibosExtraviadasRendidos(String usuario) {
        return managerRecibos.obtenerRecibosExtraviadasRendidos(usuario);
    }

    public String reciboExtraviada(String numero, String motivo, String fecha) {
        return managerRecibos.reciboExtraviada(numero, motivo, fecha);
    }

    public String completarReciboChequePorOperador(Recibo rec) {
        return managerRecibos.completarReciboChequePorOperador(rec);
    }

    public String completarReciboChequePorInspector(Recibo rec) {
        return managerRecibos.completarReciboChequePorInspector(rec);
    }

    public String completarReciboEfectivoPorOperador(Recibo rec) {
        return managerRecibos.completarReciboEfectivoPorOperador(rec);
    }

    public String completarReciboEfectivoPorInspector(Recibo rec) {
        return managerRecibos.completarReciboEfectivoPorInspector(rec);
    }

    public String actualizarRecibo(Recibo rec) {
        return managerRecibos.actualizarRecibo(rec);
    }

    public String cargarRecibos(String op, String min, String max) {
        return managerRecibos.cargarRecibos(op, min, max);
    }

    public void devolverRecibos(String op, String[] numeros) {
        managerRecibos.devolverRecibos(op, numeros);
    }

    public String[] responsableList() {
        return managerUsuarios.responsableList();
    }

    public String[] sectorList() {
        return managerUsuarios.sectorList();
    }

    public String resetPassword(String usuario, String password) {
        return managerUsuarios.resetPassword(usuario, password);
    }
}
