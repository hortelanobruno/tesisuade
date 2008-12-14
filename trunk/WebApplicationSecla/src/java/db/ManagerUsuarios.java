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
import java.util.List;
import varios.Usuario;

/**
 *
 * @author Administrador
 */
public class ManagerUsuarios {

    public ManagerUsuarios() {
    }

    public boolean isConnected() {
        Conexion con = new Conexion();
        Conexion.driverOdbc();
        if (con.abrirConexion()) {
            return true;
        }
        return false;
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
                    datos.add(srs.getString("tipocuenta"));
                }
                stmt.close();
                srs.close();
                con.cerrarConexion();
            } catch (Exception e) {
            }
        }
        return datos;
    }

    public String addArea(Usuario usu) {
        Conexion con = new Conexion();
        Conexion.driverOdbc();
        if (con.abrirConexion()) {
            Connection conn = con.getCon();
            PreparedStatement stmt;
            String digitos = new String();
            digitos = usu.getDigarea() + usu.getDigresp();
            try {
                stmt = conn.prepareStatement("SELECT usuario FROM usuarios where usuario = ?");
                stmt.setString(1, usu.getUsuario());
                ResultSet srs = stmt.executeQuery();
                while (srs.next()) {
                    stmt.close();
                    srs.close();
                    con.cerrarConexion();
                    return "usuario";
                }
                stmt = conn.prepareStatement("SELECT responsable FROM usuarios where responsable = ?");
                stmt.setString(1, usu.getResponsable());
                srs = stmt.executeQuery();
                while (srs.next()) {
                    stmt.close();
                    srs.close();
                    con.cerrarConexion();
                    return "responsable";
                }
                stmt = conn.prepareStatement("SELECT digitos FROM usuarios where digitos = ?");
                stmt.setString(1, digitos);
                srs = stmt.executeQuery();
                while (srs.next()) {
                    stmt.close();
                    srs.close();
                    con.cerrarConexion();
                    return "digitos";
                }
                stmt = conn.prepareStatement("insert into usuarios values ( ? , ? , ? , ? , ? , ? , ? , 1)");
                stmt.setString(1, usu.getUsuario());
                stmt.setString(2, usu.getPassword());
                stmt.setString(3, usu.getTipoCuenta());
                stmt.setString(4, usu.getResponsable());
                stmt.setString(5, usu.getSede());
                stmt.setString(6, usu.getSector());
                stmt.setString(7, digitos);
                stmt.execute();
                stmt.close();
                srs.close();
                con.cerrarConexion();
                return "ok";
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("La base esta caida");
        }
        return null;
    }

    public String[] operatorInspectorCajeroList() {
        String list[] = null;
        Conexion con = new Conexion();
        Conexion.driverOdbc();
        if (con.abrirConexion()) {
            Connection conn = con.getCon();
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement("SELECT count(*) as count FROM usuarios where tipocuenta <> 'administrador' and habilitado = 1");
                ResultSet srs = stmt.executeQuery();
                srs.next();
                int cant = srs.getInt("count");
                list = new String[cant];
                stmt = conn.prepareStatement("SELECT usuario FROM usuarios where tipocuenta <> 'administrador' and habilitado = 1");
                srs = stmt.executeQuery();
                int aux = 0;
                while (srs.next()) {
                    list[aux] = srs.getString("usuario");
                    aux++;
                }
                stmt.close();
                srs.close();
                con.cerrarConexion();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("La base esta caida");
        }
        return list;
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
                stmt.close();
                srs.close();
                con.cerrarConexion();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("La base esta caida");
        }
        return list;
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
                stmt.close();
                srs.close();
                con.cerrarConexion();
            } catch (SQLException e) {
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
                stmt.close();
                srs.close();
                con.cerrarConexion();
            } catch (SQLException e) {
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
                stmt.close();
                con.cerrarConexion();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("La base esta caida");
        }
        return "ok";
    }

    public String borrarArea(String usuario) {
        Conexion con = new Conexion();
        Conexion.driverOdbc();
        if (con.abrirConexion()) {
            Connection conn = con.getCon();
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement("update usuarios set habilitado = 0 where usuario = ?");
                stmt.setString(1, usuario);
                stmt.execute();
                stmt.close();
                con.cerrarConexion();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("La base esta caida");
        }
        return "ok";
    }

    public String actualizarArea(Usuario usuario) {
        Conexion con = new Conexion();
        Conexion.driverOdbc();
        if (con.abrirConexion()) {
            Connection conn = con.getCon();
            PreparedStatement stmt;
            String digitos = usuario.getDigarea() + usuario.getDigresp();
            try {
                stmt = conn.prepareStatement("SELECT responsable FROM usuarios where responsable = ? and usuario <> ?");
                stmt.setString(1, usuario.getResponsable());
                stmt.setString(2, usuario.getUsuario());
                ResultSet srs = stmt.executeQuery();
                while (srs.next()) {
                    stmt.close();
                    srs.close();
                    con.cerrarConexion();
                    return "responsable";
                }
                stmt = conn.prepareStatement("SELECT digitos FROM usuarios where digitos = ? and usuario <> ?");
                stmt.setString(1, digitos);
                stmt.setString(2, usuario.getUsuario());
                srs = stmt.executeQuery();
                while (srs.next()) {
                    stmt.close();
                    srs.close();
                    con.cerrarConexion();
                    return "digitos";
                }
                stmt = conn.prepareStatement("update usuarios set responsable = ?, sede = ?, sector = ?, digitos = ? where usuario = ?");
                stmt.setString(1, usuario.getResponsable());
                stmt.setString(2, usuario.getSede());
                stmt.setString(3, usuario.getSector());
                stmt.setString(4, digitos);
                stmt.setString(5, usuario.getUsuario());
                stmt.execute();
                stmt.close();
                srs.close();
                con.cerrarConexion();
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
                stmt = conn.prepareStatement("SELECT * FROM usuarios WHERE habilitado = 1");
                ResultSet srs = stmt.executeQuery();
                String tipocuenta = null;
                String usu = null;
                String pass = null;
                while (srs.next()) {
                    usu = srs.getString("usuario");
                    pass = srs.getString("password");
                    if ((usu.equals(usuario)) && (pass.equals(password))) {
                        tipocuenta = srs.getString("tipocuenta");
                        stmt.close();
                        srs.close();
                        con.cerrarConexion();
                        return tipocuenta;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("La base esta caida");
        }
        return null;
    }

    public String[] operatorInspectorList() {
        String list[] = null;
        Conexion con = new Conexion();
        Conexion.driverOdbc();
        if (con.abrirConexion()) {
            Connection conn = con.getCon();
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement("SELECT count(*) as count FROM usuarios where (tipocuenta = 'inspector' OR tipocuenta = 'operador' ) and habilitado = 1");
                ResultSet srs = stmt.executeQuery();
                srs.next();
                int cant = srs.getInt("count");
                list = new String[cant];
                stmt = conn.prepareStatement("SELECT responsable FROM usuarios where (tipocuenta = 'inspector' OR tipocuenta = 'operador' ) and habilitado = 1");
                srs = stmt.executeQuery();
                int aux = 0;
                while (srs.next()) {
                    list[aux] = srs.getString("responsable");
                    aux++;
                }
                stmt.close();
                srs.close();
                con.cerrarConexion();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("La base esta caida");
        }
        return list;
    }

    public String obtenerTipoUsuario(String numero) {
        Conexion con = new Conexion();
        Conexion.driverOdbc();
        if (con.abrirConexion()) {
            Connection conn = con.getCon();
            PreparedStatement stmt;
            String tipoCuenta;
            try {
                stmt = conn.prepareStatement("SELECT u.tipocuenta FROM (usuarios u INNER JOIN recibos r ON u.usuario = r.usuario) WHERE r.numero = ?");
                stmt.setString(1, numero);
                ResultSet srs = stmt.executeQuery();
                while (srs.next()) {
                    tipoCuenta = srs.getString("tipocuenta");
                    stmt.close();
                    srs.close();
                    con.cerrarConexion();
                    return tipoCuenta;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("La base esta caida");
        }
        return null;
    }

    public String[] operatorInspectorListUsuario() {
        String list[] = null;
        Conexion con = new Conexion();
        Conexion.driverOdbc();
        if (con.abrirConexion()) {
            Connection conn = con.getCon();
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement("SELECT count(*) as count FROM usuarios where (tipocuenta = 'inspector' OR tipocuenta = 'operador' ) and habilitado = 1");
                ResultSet srs = stmt.executeQuery();
                srs.next();
                int cant = srs.getInt("count");
                list = new String[cant];
                stmt = conn.prepareStatement("SELECT usuario FROM usuarios where (tipocuenta = 'inspector' OR tipocuenta = 'operador' ) and habilitado = 1");
                srs = stmt.executeQuery();
                int aux = 0;
                while (srs.next()) {
                    list[aux] = srs.getString("usuario");
                    aux++;
                }
                stmt.close();
                srs.close();
                con.cerrarConexion();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("La base esta caida");
        }
        return list;
    }
}
