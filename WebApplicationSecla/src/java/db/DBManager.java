package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import varios.Recibo;
//boca
public class DBManager {

    public DBManager() {
        // TODO Auto-generated constructor stub
    }

    public boolean isConnected() {
        Conexion con = new Conexion();
        Conexion.driverOdbc();
        if (con.abrirConexion()) {
            return true;
        }
        return false;
    }

    public String borrarArea(String usuario){
        Conexion con = new Conexion();
        Conexion.driverOdbc();
        if (con.abrirConexion()) {
            Connection conn = con.getCon();
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement("update usuarios set habilitado = 0 where usuario = ?");
                stmt.setString(1, usuario);
                stmt.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("La base esta caida");
        }
        return "ok";
    }

    public String actualizarArea(String usuario, String responsable, String sector, String sede, String digarea, String digresp) {
        Conexion con = new Conexion();
        Conexion.driverOdbc();
        if (con.abrirConexion()) {
            Connection conn = con.getCon();
            PreparedStatement stmt;
            String digitos = digarea + digresp;
            try {
                stmt = conn.prepareStatement("SELECT responsable FROM usuarios where responsable = ?");
                stmt.setString(1, responsable);
                ResultSet srs = stmt.executeQuery();
                while (srs.next()) {
                    return "responsable";
                }
                stmt = conn.prepareStatement("SELECT digitos FROM usuarios where digitos = ?");
                stmt.setString(1, digitos);
                srs = stmt.executeQuery();
                while (srs.next()) {
                    return "digitos";
                }
                stmt = conn.prepareStatement("update usuarios set responsable = ?, sede = ?, sector = ?, digitos = ? where usuario = ?");
                stmt.setString(1, responsable);
                stmt.setString(2, sede);
                stmt.setString(3, sector);
                stmt.setString(4, digitos);
                stmt.setString(5, usuario);
                stmt.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("La base esta caida");
        }
        return "ok";
    }

    public String loginUsuario(String usuario, String password) {
        Conexion con = new Conexion();
        Conexion.driverOdbc();
        if (con.abrirConexion()) {
            Connection conn = con.getCon();
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement("SELECT tipocuenta FROM usuarios where usuario = ? and password = ? and habilitado = 1");
                stmt.setString(1, usuario);
                stmt.setString(2, password);
                ResultSet srs = stmt.executeQuery();
                while (srs.next()) {
                    String tipocuenta = srs.getString("tipocuenta");
                    return tipocuenta;
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            System.out.println("La base esta caida");
        }
        return null;
    }

    public void confirmarRecibos(List<Integer> recibos) {
        Conexion con = new Conexion();
        Conexion.driverOdbc();
        if (con.abrirConexion()) {
            Connection conn = con.getCon();
            PreparedStatement stmt;
            try {
                for (int i = 0; i < recibos.size(); i++) {
                    stmt = conn.prepareStatement("update recibos set estadotransaccion = 'rendida' where numero = ?");
                    stmt.setInt(1, recibos.get(i));
                    stmt.execute();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
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
                while (srs.next()) {
                    Recibo recibo = new Recibo();
                    recibo.setNumero(srs.getInt("numero"));
                    recibo.setBeneficiario(srs.getString("beneficiario"));
                    String fecha = srs.getDate("fecharendicion").toString();
                    String[] aux = fecha.split("-");
                    aux[0] = aux[0].trim();
                    aux[1] = aux[1].trim();
                    aux[2] = aux[2].trim();
                    fecha = aux[2] + "/" + aux[1] + "/" + aux[0];
                    recibo.setFecharendicion(fecha);
                    recibo.setMonto(srs.getInt("monto"));
                    recibo.setMotivo(srs.getString("motivo"));
                    recibo.setEstadorecibo(srs.getString("estadorecibo"));
                    recibos.add(recibo);
                }
            } catch (Exception e) {
            }
        }
        return recibos;
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
                while (srs.next()) {
                    recibo = new Recibo();
                    recibo.setNumero(srs.getInt("numero"));
                    recibo.setBeneficiario(srs.getString("beneficiario"));
                    String fecha = srs.getDate("fecharendicion").toString();
                    String[] aux = fecha.split("-");
                    aux[0] = aux[0].trim();
                    aux[1] = aux[1].trim();
                    aux[2] = aux[2].trim();
                    fecha = aux[2] + "/" + aux[1] + "/" + aux[0];
                    recibo.setFecharendicion(fecha);
                    recibo.setMonto(srs.getInt("monto"));
                    recibo.setMotivo(srs.getString("motivo"));
                    recibo.setEstadorecibo(srs.getString("estadorecibo"));
                }
            } catch (Exception e) {
            }
        }
        return recibo;
    }

    public List<String> datosUsuario(Object usuario) {
        ArrayList<String> datos = new ArrayList<String>();
        Conexion con = new Conexion();
        Conexion.driverOdbc();
        if (con.abrirConexion()) {
            Connection conn = con.getCon();
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement("SELECT * FROM usuarios where usuario = ?");
                stmt.setString(1, usuario.toString());
                ResultSet srs = stmt.executeQuery();
                while (srs.next()) {
                    datos.add(srs.getString("password"));
                    datos.add(srs.getString("responsable"));
                    datos.add(srs.getString("sede"));
                    datos.add(srs.getString("sector"));
                    datos.add(srs.getString("digitos"));
                }
            } catch (Exception e) {
            }
        }
        return datos;
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
            } catch (Exception e) {
            }
        }
        return recibos;
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
            } catch (Exception e) {
            }
        }
        return recibos;
    }

    public String addArea(String responsable, String sede, String sector, String digarea, String digresp, String usuario, String password1) {
        Conexion con = new Conexion();
        Conexion.driverOdbc();
        if (con.abrirConexion()) {
            Connection conn = con.getCon();
            PreparedStatement stmt;
            String digitos = new String();
            digitos = digarea + digresp;
            try {
                stmt = conn.prepareStatement("SELECT usuario FROM usuarios where usuario = ?");
                stmt.setString(1, usuario);
                ResultSet srs = stmt.executeQuery();
                while (srs.next()) {
                    return "usuario";
                }
                stmt = conn.prepareStatement("SELECT responsable FROM usuarios where responsable = ?");
                stmt.setString(1, responsable);
                srs = stmt.executeQuery();
                while (srs.next()) {
                    return "responsable";
                }
                stmt = conn.prepareStatement("SELECT digitos FROM usuarios where digitos = ?");
                stmt.setString(1, digitos);
                srs = stmt.executeQuery();
                while (srs.next()) {
                    return "digitos";
                }
                stmt = conn.prepareStatement("insert into usuarios values ( ? , ? , 'operador' , ? , ? , ? , ? , 1)");
                stmt.setString(1, usuario);
                stmt.setString(2, password1);
                stmt.setString(3, responsable);
                stmt.setString(4, sede);
                stmt.setString(5, sector);
                stmt.setString(6, digitos);
                stmt.execute();
                return "ok";
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("La base esta caida");
        }
        return null;
    }

    public String[] operatorList() {
        String list[] = null;
        Conexion con = new Conexion();
        Conexion.driverOdbc();
        if (con.abrirConexion()) {
            Connection conn = con.getCon();
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement("SELECT count(*) as count FROM usuarios where tipocuenta = 'operador' and habilitado = 1");
                ResultSet srs = stmt.executeQuery();
                srs.next();
                int cant = srs.getInt("count");
                list = new String[cant];
                stmt = conn.prepareStatement("SELECT usuario FROM usuarios where tipocuenta = 'operador' and habilitado = 1");
                srs = stmt.executeQuery();
                int aux = 0;
                while (srs.next()) {
                    list[aux] = srs.getString("usuario");
                    aux++;
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            System.out.println("La base esta caida");
        }
        return list;
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
                stmt = conn.prepareStatement("update recibos set motivo = ? , estadorecibo = 'anulada', estadotransaccion = 'a confirmar', fecharendicion = ? where numero = ?");
                stmt.setString(1, motivo);
                stmt.setString(2, fecha);
                stmt.setInt(3, Integer.parseInt(numero));
                stmt.execute();
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
                if(cant>0){
                    return false;
                }else{
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
                while (srs.next()) {
                    ArrayList<String> datos = new ArrayList<String>();
                    datos.add(srs.getString("numero"));
                    datos.add(srs.getDate("fecharendicion").toString());
                    datos.add(srs.getString("beneficiario"));
                    datos.add(srs.getString("monto"));
                    datos.add(srs.getString("estadotransaccion"));
                    mapa.put(aux, datos);
                    aux++;
                }
                return mapa;
            } catch (SQLException e) {
                // TODO Auto-generated catch block
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
                while (srs.next()) {
                    ArrayList<String> datos = new ArrayList<String>();
                    datos.add(srs.getString("numero"));
                    datos.add(srs.getDate("fecharendicion").toString());
                    datos.add(srs.getString("beneficiario"));
                    datos.add(srs.getString("monto"));
                    datos.add(srs.getString("estadotransaccion"));
                    mapa.put(aux, datos);
                    aux++;
                }
                return mapa;
            } catch (SQLException e) {
                // TODO Auto-generated catch block
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
                while (srs.next()) {
                    ArrayList<String> datos = new ArrayList<String>();
                    datos.add(srs.getString("numero"));
                    datos.add(srs.getDate("fecharendicion").toString());
                    datos.add(srs.getString("motivo"));
                    mapa.put(aux, datos);
                    aux++;
                }
                return mapa;
            } catch (SQLException e) {
                // TODO Auto-generated catch block
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
                while (srs.next()) {
                    ArrayList<String> datos = new ArrayList<String>();
                    datos.add(srs.getString("numero"));
                    datos.add(srs.getDate("fecharendicion").toString());
                    datos.add(srs.getString("motivo"));
                    mapa.put(aux, datos);
                    aux++;
                }
                return mapa;
            } catch (SQLException e) {
                // TODO Auto-generated catch block
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
                while (srs.next()) {
                    ArrayList<String> datos = new ArrayList<String>();
                    datos.add(srs.getString("numero"));
                    datos.add(srs.getDate("fecharendicion").toString());
                    datos.add(srs.getString("motivo"));
                    mapa.put(aux, datos);
                    aux++;
                }
                return mapa;
            } catch (SQLException e) {
                // TODO Auto-generated catch block
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
                while (srs.next()) {
                    ArrayList<String> datos = new ArrayList<String>();
                    datos.add(srs.getString("numero"));
                    datos.add(srs.getDate("fecharendicion").toString());
                    datos.add(srs.getString("motivo"));
                    mapa.put(aux, datos);
                    aux++;
                }
                return mapa;
            } catch (SQLException e) {
                // TODO Auto-generated catch block
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
                stmt = conn.prepareStatement("update recibos set motivo = ? , fecharendicion = ? , estadorecibo = 'extraviada', estadotransaccion = 'a confirmar' where numero = ?");
                stmt.setString(1, motivo);
                stmt.setString(2, fecha);
                stmt.setInt(3, Integer.parseInt(numero));
                stmt.execute();
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

    public String completarRecibo(String numero, String fecha, String beneficiario, String monto) {
        //08/21/2008
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
                monto = monto.replace(',', '.');
                stmt = conn.prepareStatement("update recibos set fecharendicion = ? , beneficiario = ?, monto = ?, estadorecibo = 'completada', estadotransaccion = 'a confirmar' where numero = ?");
                stmt.setString(1, fecha);
                stmt.setString(2, beneficiario);
                stmt.setString(3, monto);
                stmt.setInt(4, Integer.parseInt(numero));
                stmt.execute();
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

    public String actualizarRecibo(Recibo rec) {
        //08/21/2008 Ver como viene la fecha
        String[] aux = rec.getFecharendicion().split("/");
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
                String beneficiario = rec.getBeneficiario();
                int numero = rec.getNumero();
                String motivo = rec.getMotivo();
                String monto = "" + rec.getMonto();
                monto = monto.replace(',', '.');
                stmt = conn.prepareStatement("update recibos set fecharendicion = ? , beneficiario = ?, monto = ?, motivo = ? where numero = ?");
                stmt.setString(1, fecha);
                stmt.setString(2, beneficiario);
                stmt.setString(3, monto);
                stmt.setString(4, motivo);
                stmt.setInt(5, numero);
                stmt.execute();
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
                        stmt = conn.prepareStatement("insert into recibos values (?,?,'','','','','pendiente','')");
                        stmt.setString(1, usuario);
                        stmt.setInt(2, i);
                        stmt.execute();
                    }
                    return "true";
                } else {
                    return "false";
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
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
                stmt.setString(3,usuario);
                srs = stmt.executeQuery();
                String estado = "";
                int aux = 0;
                while(srs.next()){
                    estado = srs.getString("estadotransaccion");
                    if(!estado.equalsIgnoreCase("pendiente")){
                        aux++;
                    }
                }
                if (aux==0) {
                    stmt = conn.prepareStatement("delete from recibos where usuario = ? and (numero BETWEEN ? and ?)");
                    stmt.setString(1, usuario);
                    stmt.setInt(2, numMin);
                    stmt.setInt(3, numMax);
                    stmt.execute();
                    return "true";
                } else {
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

    public String[] responsableList() {
        String list[] = null;
        Conexion con = new Conexion();
        Conexion.driverOdbc();
        if (con.abrirConexion()) {
            Connection conn = con.getCon();
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement("SELECT count(*) as count FROM usuarios where tipocuenta = 'operador' and habilitado = 1");
                ResultSet srs = stmt.executeQuery();
                srs.next();
                int cant = srs.getInt("count");
                list = new String[cant];
                stmt = conn.prepareStatement("SELECT responsable FROM usuarios where tipocuenta = 'operador' and habilitado = 1");
                srs = stmt.executeQuery();
                int aux = 0;
                while (srs.next()) {
                    list[aux] = srs.getString("responsable");
                    aux++;
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            System.out.println("La base esta caida");
        }
        return list;
    }

    public String[] sectorList() {
        String list[] = null;
        Conexion con = new Conexion();
        Conexion.driverOdbc();
        if (con.abrirConexion()) {
            Connection conn = con.getCon();
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement("SELECT count(distinct(sector)) as count FROM usuarios where habilitado = 1");
                ResultSet srs = stmt.executeQuery();
                srs.next();
                int cant = srs.getInt("count");
                list = new String[cant];
                stmt = conn.prepareStatement("SELECT distinct(sector) as sector FROM usuarios where habilitado = 1");
                srs = stmt.executeQuery();
                int aux = 0;
                while (srs.next()) {
                    list[aux] = srs.getString("sector");
                    aux++;
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            System.out.println("La base esta caida");
        }
        return list;
    }

    public String resetPassword(String usuario, String password) {
        Conexion con = new Conexion();
        Conexion.driverOdbc();
        if (con.abrirConexion()) {
            Connection conn = con.getCon();
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement("update usuarios set password = ? where usuario = ?");
                stmt.setString(1, password);
                stmt.setString(2, usuario);
                stmt.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("La base esta caida");
        }
        return "ok";
    }
}
