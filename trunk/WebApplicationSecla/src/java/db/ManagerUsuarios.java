/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.ArrayList;
import java.util.List;
import jpa.controllers.RecibosJpaController;
import jpa.controllers.UsuariosJpaController;
import jpa.controllers.exceptions.NonexistentEntityException;
import jpa.controllers.exceptions.PreexistingEntityException;
import jpa.entities.Recibos;
import jpa.entities.Usuarios;
import varios.Usuario;

/**
 *
 * @author Administrador
 */
public class ManagerUsuarios {

    private UsuariosJpaController usuariosController;
    private RecibosJpaController recibosController;

    public ManagerUsuarios() {
        usuariosController = new UsuariosJpaController();
        recibosController = new RecibosJpaController();
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
        ArrayList<String> usu = new ArrayList<String>();
        Usuarios admin = usuariosController.findUsuarios(usuario.toString());
        usu.add(admin.getPassword());
        usu.add(admin.getResponsable());
        usu.add(admin.getSede());
        usu.add(admin.getSector());
        usu.add(admin.getDigitos());
        usu.add(admin.getTipocuenta());
        return usu;
    }

    public String addArea(Usuario usu) {
        try {
            int cant = usuariosController.existsUsuarioByResponsable(usu.getResponsable());
            if (cant > 0) {
                return "responsable";
            }
            String digitos = usu.getDigarea() + usu.getDigresp();
            cant = usuariosController.existDigitos(digitos);
            if (cant > 0) {
                return "digitos";
            }
            Usuarios newUser = new Usuarios();
            newUser.setUsuario(usu.getUsuario());
            newUser.setPassword(usu.getPassword());
            newUser.setDigitos(digitos);
            newUser.setHabilitado(1);
            newUser.setResponsable(usu.getResponsable());
            newUser.setSector(usu.getSector());
            newUser.setSede(usu.getSede());
            newUser.setTipocuenta(usu.getTipoCuenta());
            usuariosController.create(newUser);
            return "ok";
        } catch (PreexistingEntityException ex) {
            return "usuario";
        } catch (Exception ex) {
        }
        return null;
    }

    public List<String> operatorInspectorCajeroList() {
        List<String> resps = usuariosController.operatorInspectorCajeroList();
        return resps;
    }

    public List<String> sectorList() {
        List<String> sectores = usuariosController.sectorList();
        return sectores;
    }

    public String resetPasswordDeUsuario(String usuario, String password) {
        try {
            Usuarios usu = usuariosController.findUsuarios(usuario);
            usu.setPassword(password);
            usuariosController.edit(usu);
            return "ok";
        } catch (NonexistentEntityException ex) {
            return "error";
        } catch (Exception ex) {
            return "error";
        }
    }

    public String resetPassword(String responsable, String password) {
        try {
            Usuarios usuario = usuariosController.findUsuariosByResponsable(responsable);
            usuario.setPassword(password);
            usuariosController.edit(usuario);
            return "ok";
        } catch (NonexistentEntityException ex) {
            return "error";
        } catch (Exception ex) {
            return "error";
        }
    }

    public String borrarArea(String usuario) {
        try {
            Usuarios usu = usuariosController.findUsuarios(usuario);
            usu.setHabilitado(0);
            usuariosController.edit(usu);
            return "ok";
        } catch (NonexistentEntityException ex) {
            return "error";
        } catch (Exception ex) {
            return "error";
        }
    }

    public String actualizarArea(String responsable, Usuario usuario) {
        try {
            int aux;
            if (!responsable.equals(usuario.getResponsable())) {
                aux = usuariosController.existsUsuarioByResponsable(usuario.getResponsable());
                if (aux > 0) {
                    return "responsable";
                }
            }
            String digitos = usuario.getDigarea() + usuario.getDigresp();
            aux = usuariosController.existUsuarioByDigitos(responsable,digitos);
            if (aux > 0) {
                return "digitos";
            }
            Usuarios usu = usuariosController.findUsuariosByResponsable(responsable);
            usu.setResponsable(usuario.getResponsable());
            usu.setSede(usuario.getSede());
            usu.setSector(usuario.getSector());
            usu.setDigitos(digitos);
            usuariosController.edit(usu);
            return "ok";
        } catch (NonexistentEntityException ex) {
            return "error";
        } catch (Exception ex) {
            return "error";
        }
    }

    public String loginUsuario(String usuario, String password) {
        Usuarios usu = usuariosController.findUsuarios(usuario);
        if (usu.getHabilitado() > 0) {
            if (usu.getPassword().equals(password)) {
                return usu.getTipocuenta();
            }
        }
        return null;
    }

    public List<String> operatorInspectorList() {
        List<String> list = usuariosController.operatorInspectorList();
        return list;
    }

    public String obtenerTipoUsuario(String numero) {
        Recibos rec = recibosController.findRecibosByNumero(numero);
        Usuarios usu = usuariosController.findUsuarios(rec.getRecibosPK().getUsuario());
        return usu.getTipocuenta();
    }

    public List<String> operatorInspectorListUsuario() {
        List<String> resps = usuariosController.operatorInspectorListUsuario();
        return resps;
    }

    public List<String> datosResponsable(Object responsable) {
        Usuarios usuario = usuariosController.findUsuariosByResponsable(responsable.toString());
        ArrayList<String> datos = new ArrayList<String>();
        datos.add(usuario.getPassword());
        datos.add(usuario.getResponsable());
        datos.add(usuario.getSede());
        datos.add(usuario.getSector());
        datos.add(usuario.getDigitos());
        datos.add(usuario.getTipocuenta());
        return datos;
    }
}
