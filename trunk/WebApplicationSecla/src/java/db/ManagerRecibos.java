/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import varios.Recibo;

/**
 *
 * @author Administrador
 */
public class ManagerRecibos {

    public ManagerRecibos() {
    }

    public Recibo obtenerReciboAConfirmar(String numero) {
        Recibo recibo = null;
        Conexion con = new Conexion();
        Conexion.driverOdbc();
        if (con.abrirConexion()) {
            Connection conn = con.getCon();
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement("SELECT * FROM recibos where numero = ? and estadotransaccion = 'a confirmar'");
                stmt.setInt(1, Integer.parseInt(numero));
                ResultSet srs = stmt.executeQuery();
                String fecha;
                String[] aux;
                while (srs.next()) {
                    recibo = new Recibo();
                    recibo.setNumero(srs.getInt("numero"));
                    recibo.setRazonSocial(srs.getString("razonsocial"));
                    fecha = srs.getDate("fechaconfeccion").toString();
                    aux = fecha.split("-");
                    aux[0] = aux[0].trim();
                    aux[1] = aux[1].trim();
                    aux[2] = aux[2].trim();
                    fecha = aux[2] + "/" + aux[1] + "/" + aux[0];
                    recibo.setFechaConfeccion(fecha);
                    recibo.setMonto(srs.getString("monto"));
                    recibo.setMotivo(srs.getString("motivo"));
                    recibo.setEstadorecibo(srs.getString("estadorecibo"));
                    fecha = srs.getDate("fechavencimiento").toString();
                    aux = fecha.split("-");
                    aux[0] = aux[0].trim();
                    aux[1] = aux[1].trim();
                    aux[2] = aux[2].trim();
                    fecha = aux[2] + "/" + aux[1] + "/" + aux[0];
                    recibo.setFechaDeVencimiento(fecha);
                    recibo.setNumeroCuota(srs.getString("numerocuota"));
                    recibo.setBanco(srs.getString("banco"));
                    recibo.setNumeroCheque(srs.getString("numerocheque"));
                }
                stmt.close();
                srs.close();
                con.cerrarConexion();
            } catch (Exception e) {
            }
        }
        return recibo;
    }

    public List<Integer> obtenerRecibosConfirmados() {
        List<Integer> recibos = new ArrayList<Integer>();
        Conexion con = new Conexion();
        Conexion.driverOdbc();
        if (con.abrirConexion()) {
            Connection conn = con.getCon();
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement("SELECT top 100 numero FROM recibos where estadotransaccion = 'rendida' order by numero desc");
                ResultSet srs = stmt.executeQuery();
                while (srs.next()) {
                    recibos.add(srs.getInt("numero"));
                }
                stmt.close();
                srs.close();
                con.cerrarConexion();
            } catch (Exception e) {
            }
        }
        return recibos;
    }

    public List<Integer> obtenerRecibosEntregados() {
        List<Integer> recibos = new ArrayList<Integer>();
        Conexion con = new Conexion();
        Conexion.driverOdbc();
        if (con.abrirConexion()) {
            Connection conn = con.getCon();
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement("SELECT top 100 numero FROM recibos order by numero desc");
                ResultSet srs = stmt.executeQuery();
                while (srs.next()) {
                    recibos.add(srs.getInt("numero"));
                }
                stmt.close();
                srs.close();
                con.cerrarConexion();
            } catch (Exception e) {
            }
        }
        return recibos;
    }

    public String anularRecibo(String numero, String motivo, String fecha) {
        String[] aux = fecha.split("/");
        aux[0] = aux[0].trim();
        aux[1] = aux[1].trim();
        aux[2] = aux[2].trim();
        fecha = aux[1] + "/" + aux[0] + "/" + aux[2];
        Conexion con = new Conexion();
        Conexion.driverOdbc();
        if (con.abrirConexion()) {
            Connection conn = con.getCon();
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement("update recibos set motivo = ? , estadorecibo = 'anulada', estadotransaccion = 'a confirmar', fechaconfeccion = ? where numero = ?");
                stmt.setString(1, motivo);
                stmt.setString(2, fecha);
                stmt.setInt(3, Integer.parseInt(numero));
                stmt.execute();
                stmt.close();
                con.cerrarConexion();
                return "true";
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            System.out.println("La base esta caida");
        }
        return "false";
    }

    public boolean chequearBorrarUsuario(String usuario) {
        Conexion con = new Conexion();
        Conexion.driverOdbc();
        if (con.abrirConexion()) {
            Connection conn = con.getCon();
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement("select count(*) as count from recibos where usuario = ? and estadotransaccion <> 'rendida'");
                stmt.setString(1, usuario);
                ResultSet srs = stmt.executeQuery();
                srs.next();
                int cant = srs.getInt("count");
                stmt.close();
                srs.close();
                con.cerrarConexion();
                if (cant > 0) {
                    return false;
                } else {
                    return true;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("La base esta caida");
        }
        return false;
    }

    public HashMap<Integer, List<String>> obtenerRecibosCompletadas(String usuario) {
        HashMap<Integer, List<String>> mapa = new HashMap<Integer, List<String>>();
        Conexion con = new Conexion();
        Conexion.driverOdbc();
        if (con.abrirConexion()) {
            Connection conn = con.getCon();
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement("select * from recibos where usuario = ? and estadorecibo = 'completada' and estadotransaccion <> 'rendida'");
                stmt.setString(1, usuario);
                ResultSet srs = stmt.executeQuery();
                int aux = 0;
                List<String> datos;
                while (srs.next()) {
                    datos = new ArrayList<String>();
                    datos.add(srs.getString("numero"));
                    String fecha = srs.getDate("fechaconfeccion").toString();
                    String[] aux2 = fecha.split("-");
                    aux2[0] = aux2[0].trim();
                    aux2[1] = aux2[1].trim();
                    aux2[2] = aux2[2].trim();
                    fecha = aux2[2] + "/" + aux2[1] + "/" + aux2[0];
                    datos.add(fecha);
                    datos.add(srs.getString("razonsocial"));
                    datos.add(srs.getString("monto"));
                    datos.add(srs.getString("estadotransaccion"));
                    datos.add(srs.getString("banco"));
                    datos.add(srs.getString("numerocheque"));
                    if(!srs.getString("fechavencimiento").toString().startsWith("1900")){
                        fecha = srs.getDate("fechavencimiento").toString();
                        aux2 = fecha.split("-");
                        aux2[0] = aux2[0].trim();
                        aux2[1] = aux2[1].trim();
                        aux2[2] = aux2[2].trim();
                        fecha = aux2[2] + "/" + aux2[1] + "/" + aux2[0];
                        datos.add(fecha);
                    }else{
                        datos.add("&nbsp;");
                    }
                    mapa.put(aux, datos);
                    aux++;
                }
                stmt.close();
                srs.close();
                con.cerrarConexion();
                return mapa;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("La base esta caida");
        }
        return null;
    }

    public HashMap<Integer, List<String>> obtenerRecibosCompletadasRendidos(String usuario) {
        HashMap<Integer, List<String>> mapa = new HashMap<Integer, List<String>>();
        Conexion con = new Conexion();
        Conexion.driverOdbc();
        if (con.abrirConexion()) {
            Connection conn = con.getCon();
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement("select top 50 * from recibos where usuario = ? and estadorecibo = 'completada' and estadotransaccion = 'rendida' order by numero desc");
                stmt.setString(1, usuario);
                ResultSet srs = stmt.executeQuery();
                int aux = 0;
                List<String> datos;
                while (srs.next()) {
                    datos = new ArrayList<String>();
                    datos.add(srs.getString("numero"));
                    String fecha = srs.getDate("fechaconfeccion").toString();
                    String[] aux2 = fecha.split("-");
                    aux2[0] = aux2[0].trim();
                    aux2[1] = aux2[1].trim();
                    aux2[2] = aux2[2].trim();
                    fecha = aux2[2] + "/" + aux2[1] + "/" + aux2[0];
                    datos.add(fecha);
                    datos.add(srs.getString("razonsocial"));
                    datos.add(srs.getString("monto"));
                    datos.add(srs.getString("estadotransaccion"));
                    datos.add(srs.getString("banco"));
                    datos.add(srs.getString("numerocheque"));
                    if(!srs.getString("fechavencimiento").toString().startsWith("1900")){
                        fecha = srs.getDate("fechavencimiento").toString();
                        aux2 = fecha.split("-");
                        aux2[0] = aux2[0].trim();
                        aux2[1] = aux2[1].trim();
                        aux2[2] = aux2[2].trim();
                        fecha = aux2[2] + "/" + aux2[1] + "/" + aux2[0];
                        datos.add(fecha);
                    }else{
                        datos.add("&nbsp;");
                    }
                    mapa.put(aux, datos);
                    aux++;
                }
                stmt.close();
                srs.close();
                con.cerrarConexion();
                return mapa;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("La base esta caida");
        }
        return null;
    }

    public HashMap<Integer, List<String>> obtenerRecibosAnuladas(String usuario) {
        HashMap<Integer, List<String>> mapa = new HashMap<Integer, List<String>>();
        Conexion con = new Conexion();
        Conexion.driverOdbc();
        if (con.abrirConexion()) {
            Connection conn = con.getCon();
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement("select * from recibos where usuario = ? and estadorecibo = 'anulada' and estadotransaccion <> 'rendida'");
                stmt.setString(1, usuario);
                ResultSet srs = stmt.executeQuery();
                int aux = 0;
                List<String> datos;
                while (srs.next()) {
                    datos = new ArrayList<String>();
                    datos.add(srs.getString("numero"));
                    datos.add(srs.getDate("fechaconfeccion").toString());
                    datos.add(srs.getString("motivo"));
                    mapa.put(aux, datos);
                    aux++;
                }
                stmt.close();
                srs.close();
                con.cerrarConexion();
                return mapa;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("La base esta caida");
        }
        return null;
    }

    public HashMap<Integer, List<String>> obtenerRecibosAnuladasRendidos(String usuario) {
        HashMap<Integer, List<String>> mapa = new HashMap<Integer, List<String>>();
        Conexion con = new Conexion();
        Conexion.driverOdbc();
        if (con.abrirConexion()) {
            Connection conn = con.getCon();
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement("select top 50 * from recibos where usuario = ? and estadorecibo = 'anulada' and estadotransaccion = 'rendida'  order by numero desc");
                stmt.setString(1, usuario);
                ResultSet srs = stmt.executeQuery();
                int aux = 0;
                List<String> datos;
                while (srs.next()) {
                    datos = new ArrayList<String>();
                    datos.add(srs.getString("numero"));
                    datos.add(srs.getDate("fechaconfeccion").toString());
                    datos.add(srs.getString("motivo"));
                    mapa.put(aux, datos);
                    aux++;
                }
                stmt.close();
                srs.close();
                con.cerrarConexion();
                return mapa;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("La base esta caida");
        }
        return null;
    }

    public HashMap<Integer, List<String>> obtenerRecibosExtraviadas(String usuario) {
        HashMap<Integer, List<String>> mapa = new HashMap<Integer, List<String>>();
        Conexion con = new Conexion();
        Conexion.driverOdbc();
        if (con.abrirConexion()) {
            Connection conn = con.getCon();
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement("select * from recibos where usuario = ? and estadorecibo = 'extraviada' and estadotransaccion <> 'rendida'");
                stmt.setString(1, usuario);
                ResultSet srs = stmt.executeQuery();
                int aux = 0;
                List<String> datos;
                while (srs.next()) {
                    datos = new ArrayList<String>();
                    datos.add(srs.getString("numero"));
                    datos.add(srs.getDate("fechaconfeccion").toString());
                    datos.add(srs.getString("motivo"));
                    mapa.put(aux, datos);
                    aux++;
                }
                stmt.close();
                srs.close();
                con.cerrarConexion();
                return mapa;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("La base esta caida");
        }
        return null;
    }

    public HashMap<Integer, List<String>> obtenerRecibosExtraviadasRendidos(String usuario) {
        HashMap<Integer, List<String>> mapa = new HashMap<Integer, List<String>>();
        Conexion con = new Conexion();
        Conexion.driverOdbc();
        if (con.abrirConexion()) {
            Connection conn = con.getCon();
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement("select top 50 * from recibos where usuario = ? and estadorecibo = 'extraviada' and estadotransaccion = 'rendida' order by numero desc");
                stmt.setString(1, usuario);
                ResultSet srs = stmt.executeQuery();
                int aux = 0;
                List<String> datos;
                while (srs.next()) {
                    datos = new ArrayList<String>();
                    datos.add(srs.getString("numero"));
                    datos.add(srs.getDate("fechaconfeccion").toString());
                    datos.add(srs.getString("motivo"));
                    mapa.put(aux, datos);
                    aux++;
                }
                stmt.close();
                srs.close();
                con.cerrarConexion();
                return mapa;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("La base esta caida");
        }
        return null;
    }

    public String reciboExtraviada(String numero, String motivo, String fecha) {
        String[] aux = fecha.split("/");
        aux[0] = aux[0].trim();
        aux[1] = aux[1].trim();
        aux[2] = aux[2].trim();
        fecha = aux[1] + "/" + aux[0] + "/" + aux[2];
        Conexion con = new Conexion();
        Conexion.driverOdbc();
        if (con.abrirConexion()) {
            Connection conn = con.getCon();
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement("update recibos set motivo = ? , fechaconfeccion = ? , estadorecibo = 'extraviada', estadotransaccion = 'a confirmar' where numero = ?");
                stmt.setString(1, motivo);
                stmt.setString(2, fecha);
                stmt.setInt(3, Integer.parseInt(numero));
                stmt.execute();
                stmt.close();
                con.cerrarConexion();
                return "true";
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("La base esta caida");
        }
        return "false";
    }

    public String completarReciboChequePorOperador(Recibo rec){
        String[] aux = rec.getFechaConfeccion().split("/");
        aux[0] = aux[0].trim();
        aux[1] = aux[1].trim();
        aux[2] = aux[2].trim();
        rec.setFechaConfeccion(aux[1] + "/" + aux[0] + "/" + aux[2]);
        aux = rec.getFechaDeVencimiento().split("/");
        aux[0] = aux[0].trim();
        aux[1] = aux[1].trim();
        aux[2] = aux[2].trim();
        rec.setFechaDeVencimiento(aux[1] + "/" + aux[0] + "/" + aux[2]);
        Conexion con = new Conexion();
        Conexion.driverOdbc();
        if (con.abrirConexion()) {
            Connection conn = con.getCon();
            PreparedStatement stmt;
            try {
                String monto = ""+rec.getMonto();
                monto = monto.replace(',', '.');
                stmt = conn.prepareStatement("update recibos set fechaconfeccion = ? , razonsocial = ?, monto = ?, banco = ?, numerocheque = ?, fechavencimiento = ?, numerocuota = ?, estadorecibo = 'completada', estadotransaccion = 'a confirmar' where numero = ?");
                stmt.setString(1, rec.getFechaConfeccion());
                stmt.setString(2, rec.getRazonSocial());
                stmt.setString(3, monto);
                stmt.setString(4, rec.getBanco());
                stmt.setString(5, rec.getNumeroCheque());
                stmt.setString(6, rec.getFechaDeVencimiento());
                stmt.setString(7, rec.getNumeroCuota());
                stmt.setInt(8, rec.getNumero());
                stmt.execute();
                stmt.close();
                con.cerrarConexion();
                return "true";
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("La base esta caida");
        }
        return "false";
    }

    public String completarReciboEfectivoPorOperador(Recibo rec) {
        String[] aux = rec.getFechaConfeccion().split("/");
        aux[0] = aux[0].trim();
        aux[1] = aux[1].trim();
        aux[2] = aux[2].trim();
        rec.setFechaConfeccion(aux[1] + "/" + aux[0] + "/" + aux[2]);
        Conexion con = new Conexion();
        Conexion.driverOdbc();
        if (con.abrirConexion()) {
            Connection conn = con.getCon();
            PreparedStatement stmt;
            try {
                String monto = ""+rec.getMonto();
                monto = monto.replace(',', '.');
                stmt = conn.prepareStatement("update recibos set fechaconfeccion = ? , razonsocial = ?, monto = ?, numerocuota = ?, estadorecibo = 'completada', estadotransaccion = 'a confirmar' where numero = ?");
                stmt.setString(1, rec.getFechaConfeccion());
                stmt.setString(2, rec.getRazonSocial());
                stmt.setString(3, monto);
                stmt.setString(4, rec.getNumeroCuota());
                stmt.setInt(5, rec.getNumero());
                stmt.execute();
                stmt.close();
                con.cerrarConexion();
                return "true";
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("La base esta caida");
        }
        return "false";
    }

    public String actualizarRecibo(Recibo rec) {
        //21/08/2008 Ver como viene la fecha
        String[] aux = rec.getFechaConfeccion().split("/");
        aux[0] = aux[0].trim();
        aux[1] = aux[1].trim();
        aux[2] = aux[2].trim();
        String fecha = aux[0] + "/" + aux[1] + "/" + aux[2];
        Conexion con = new Conexion();
        Conexion.driverOdbc();
        if (con.abrirConexion()) {
            Connection conn = con.getCon();
            PreparedStatement stmt;
            try {
                String razonsocial = rec.getRazonSocial();
                int numero = rec.getNumero();
                String motivo = rec.getMotivo();
                String monto = "" + rec.getMonto();
                monto = monto.replace(',', '.');
                String fecha2 = "";
                if(!rec.getBanco().equals("")){
                    aux = rec.getFechaDeVencimiento().split("/");
                    aux[0] = aux[0].trim();
                    aux[1] = aux[1].trim();
                    aux[2] = aux[2].trim();
                    fecha2 = aux[0] + "/" + aux[1] + "/" + aux[2];;
                }
                stmt = conn.prepareStatement("update recibos set fechaconfeccion = ? , razonsocial = ?, monto = ?, motivo = ?, banco = ?, numerocheque = ?, fechavencimiento = ?, numerocuota = ? where numero = ?");
                stmt.setString(1, fecha);
                stmt.setString(2, razonsocial);
                stmt.setString(3, monto);
                stmt.setString(4, motivo);
                stmt.setString(5, rec.getBanco());
                stmt.setString(6, rec.getNumeroCheque());
                stmt.setString(7, fecha2);
                stmt.setString(8, rec.getNumeroCuota());
                stmt.setInt(9, numero);
                stmt.execute();
                stmt.close();
                con.cerrarConexion();
                return "true";
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("La base esta caida");
        }
        return "false";
    }

    public String cargarRecibos(String op, String min, String max) {
        Conexion con = new Conexion();
        Conexion.driverOdbc();
        if (con.abrirConexion()) {
            Integer numMin = Integer.parseInt(min);
            Integer numMax = Integer.parseInt(max);
            Connection conn = con.getCon();
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement("SELECT usuario FROM usuarios where responsable like ?");
                stmt.setString(1, op);
                ResultSet srs = stmt.executeQuery();
                srs.next();
                String usuario = srs.getString("usuario");
                stmt = conn.prepareStatement("SELECT count(*) as count FROM recibos where numero BETWEEN  ? and ?");
                stmt.setInt(1, numMin);
                stmt.setInt(2, numMax);
                srs = stmt.executeQuery();
                srs.next();
                int cant = srs.getInt("count");
                if (cant == 0) {
                    for (int i = numMin; i < numMax + 1; i++) {
                        stmt = conn.prepareStatement("insert into recibos values (?,?,'','','','','pendiente','','','','','','')");
                        stmt.setString(1, usuario);
                        stmt.setInt(2, i);
                        stmt.execute();
                    }
                    stmt.close();
                    srs.close();
                    con.cerrarConexion();
                    return "true";
                } else {
                    stmt.close();
                    srs.close();
                    con.cerrarConexion();
                    return "false";
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("La base esta caida");
        }
        return "false";
    }

    public String devolverRecibos(String op, String min, String max) {
        Conexion con = new Conexion();
        Conexion.driverOdbc();
        if (con.abrirConexion()) {
            Integer numMin = Integer.parseInt(min);
            Integer numMax = Integer.parseInt(max);
            Connection conn = con.getCon();
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement("SELECT usuario FROM usuarios where responsable like ?");
                stmt.setString(1, op);
                ResultSet srs = stmt.executeQuery();
                srs.next();
                String usuario = srs.getString("usuario");
                stmt = conn.prepareStatement("SELECT estadotransaccion FROM recibos where (numero BETWEEN  ? and ?) and usuario = ?");
                stmt.setInt(1, numMin);
                stmt.setInt(2, numMax);
                stmt.setString(3, usuario);
                srs = stmt.executeQuery();
                String estado = "";
                int aux = 0;
                while (srs.next()) {
                    estado = srs.getString("estadotransaccion");
                    if (!estado.equalsIgnoreCase("pendiente")) {
                        aux++;
                    }
                }
                if (aux == 0) {
                    stmt = conn.prepareStatement("delete from recibos where usuario = ? and (numero BETWEEN ? and ?)");
                    stmt.setString(1, usuario);
                    stmt.setInt(2, numMin);
                    stmt.setInt(3, numMax);
                    stmt.execute();
                    stmt.close();
                    srs.close();
                    con.cerrarConexion();
                    return "true";
                } else {
                    stmt.close();
                    srs.close();
                    con.cerrarConexion();
                    return "false";
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("La base esta caida");
        }
        return "false";
    }

    public List<Integer> obtenerRecibosPendientes(String usuario) {
        ArrayList<Integer> recibos = new ArrayList<Integer>();
        Conexion con = new Conexion();
        Conexion.driverOdbc();
        if (con.abrirConexion()) {
            Connection conn = con.getCon();
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement("SELECT numero FROM recibos where usuario = ? and estadotransaccion = 'pendiente'");
                stmt.setString(1, usuario);
                ResultSet srs = stmt.executeQuery();
                while (srs.next()) {
                    recibos.add(srs.getInt("numero"));
                }
                stmt.close();
                srs.close();
                con.cerrarConexion();
            } catch (Exception e) {
            }
        }
        return recibos;
    }

    public void confirmarRecibos(List<Integer> recibos) {
        Conexion con = new Conexion();
        Conexion.driverOdbc();
        if (con.abrirConexion()) {
            Connection conn = con.getCon();
            PreparedStatement stmt = null;
            try {
                for (int i = 0; i < recibos.size(); i++) {
                    stmt = conn.prepareStatement("update recibos set estadotransaccion = 'rendida' where numero = ?");
                    stmt.setInt(1, recibos.get(i));
                    stmt.execute();
                }
                stmt.close();
                con.cerrarConexion();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("La base esta caida");
        }
    }

    public List<Recibo> obtenerRecibosAConfirmar(String usuario) {
        List<Recibo> recibos = new ArrayList<Recibo>();
        Conexion con = new Conexion();
        Conexion.driverOdbc();
        if (con.abrirConexion()) {
            Connection conn = con.getCon();
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement("SELECT * FROM recibos where usuario = ? and estadotransaccion = 'a confirmar'");
                stmt.setString(1, usuario);
                ResultSet srs = stmt.executeQuery();
                Recibo recibo;
                String fecha;
                String[] aux;
                while (srs.next()) {
                    recibo = new Recibo();
                    recibo.setNumero(srs.getInt("numero"));
                    recibo.setRazonSocial(srs.getString("razonsocial"));
                    fecha = srs.getDate("fechaconfeccion").toString();
                    aux = fecha.split("-");
                    aux[0] = aux[0].trim();
                    aux[1] = aux[1].trim();
                    aux[2] = aux[2].trim();
                    fecha = aux[2] + "/" + aux[1] + "/" + aux[0];
                    recibo.setFechaConfeccion(fecha);
                    recibo.setMonto(srs.getString("monto"));
                    recibo.setMotivo(srs.getString("motivo"));
                    recibo.setEstadorecibo(srs.getString("estadorecibo"));
                    recibo.setBanco(srs.getString("banco"));
                    recibo.setNumeroCuota(srs.getString("numerocuota"));
                    recibo.setNumeroCheque(srs.getString("numerocheque"));
                    fecha = srs.getDate("fechavencimiento").toString();
                    aux = fecha.split("-");
                    aux[0] = aux[0].trim();
                    aux[1] = aux[1].trim();
                    aux[2] = aux[2].trim();
                    fecha = aux[2] + "/" + aux[1] + "/" + aux[0];
                    recibo.setFechaDeVencimiento(fecha);
                    recibos.add(recibo);
                }
                stmt.close();
                srs.close();
                con.cerrarConexion();
            } catch (Exception e) {
            }
        }
        return recibos;
    }

    public boolean isConnected() {
        Conexion con = new Conexion();
        Conexion.driverOdbc();
        if (con.abrirConexion()) {
            return true;
        }
        return false;
    }
}
