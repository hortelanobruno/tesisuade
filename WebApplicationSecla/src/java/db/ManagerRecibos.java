/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jpa.controllers.RecibosJpaController;
import jpa.controllers.UsuariosJpaController;
import jpa.controllers.exceptions.NonexistentEntityException;
import jpa.controllers.exceptions.PreexistingEntityException;
import jpa.entities.Recibos;
import jpa.entities.RecibosPK;
import jpa.entities.Usuarios;
import varios.Recibo;

/**
 *
 * @author Administrador
 */
public class ManagerRecibos {

    private RecibosJpaController recibosController;
    private UsuariosJpaController usuariosController;

    public ManagerRecibos() {
        recibosController = new RecibosJpaController();
        usuariosController = new UsuariosJpaController();
    }

    private String convertirDateAString(Date date) {
        if(date == null){
            return null;
        }else{
            int anio = 1900+date.getYear();
            int mes = date.getMonth()+1;
            String fecha = date.getDate() + "/" + mes + "/" + anio ;
            return fecha;
        }
    }

    
    private Date convertirStringADate(String fecha) {
        if(fecha == null){
            return null;
        }
        String[] aux = fecha.split("/");
        aux[0] = aux[0].trim();
        aux[1] = aux[1].trim();
        aux[2] = aux[2].trim();
        Date date = new Date();
        date.setDate(Integer.parseInt(aux[0]));
        date.setMonth(Integer.parseInt(aux[1]) - 1);
        date.setYear(Integer.parseInt(aux[2]) - 1900);
        return date;
    }

    public Recibo obtenerReciboAConfirmar(String numero) {
        Recibos rec = recibosController.findRecibosByNumero(numero);
        Recibo recibo = new Recibo();
        recibo.setNumero(rec.getRecibosPK().getNumero());
        recibo.setRazonSocial(rec.getRazonsocial());
        recibo.setFechaConfeccion(convertirDateAString(rec.getFechaconfeccion()));
        recibo.setMonto("" + rec.getMonto());
        recibo.setMotivo(rec.getMotivo());
        recibo.setEstadorecibo(rec.getEstadorecibo());
        recibo.setFechaDeVencimiento(convertirDateAString(rec.getFechavencimiento()));
        recibo.setNumeroCuota(rec.getNumerocuota());
        recibo.setBanco(rec.getBanco());
        recibo.setNumeroCheque(rec.getNumerocheque());
        recibo.setNumeroacta("" + rec.getNumeroacta());
        return recibo;
    }

    public List<Integer> obtenerRecibosConfirmados() {
        List<Integer> recibos = recibosController.obtenerRecibosConfirmados();
        return recibos;
    }

    public String anularRecibo(String numero, String motivo, String fecha) {
        try {
            Recibos recibo = recibosController.findRecibosByNumero(numero);
            recibo.setMotivo(motivo);
            recibo.setFechaconfeccion(convertirStringADate(fecha));
            recibo.setEstadorecibo("anulada");
            recibo.setEstadotransaccion("a confirmar");
            recibosController.edit(recibo);
            return "true";
        } catch (NonexistentEntityException ex) {
            return "false";
        } catch (Exception ex) {
            return "false";
        }
    }

    public boolean chequearBorrarResponsable(String responsable) {
        Usuarios usuario = usuariosController.findUsuariosByResponsable(responsable);
        int aux = recibosController.chequearBorrarResponsable(usuario.getUsuario());
        if (aux > 0) {
            return false;
        } else {
            return true;
        }
    }

    public List<Recibo> obtenerRecibosCompletadas(String usuario) {
        List<Recibos> recibos = recibosController.obtenerRecibosCompletadas(usuario);
        List<Recibo> mapa = new ArrayList<Recibo>();
        Recibo recibo;
        for(Recibos rec : recibos){
            recibo = new Recibo();
            recibo.setNumero(rec.getRecibosPK().getNumero());
            recibo.setFechaConfeccion(convertirDateAString(rec.getFechaconfeccion()));
            recibo.setRazonSocial(rec.getRazonsocial());
            recibo.setMonto(""+rec.getMonto());
            recibo.setEstadoTransaccion(rec.getEstadotransaccion());
            recibo.setBanco(rec.getBanco());
            recibo.setNumeroCheque(rec.getNumerocheque());
            recibo.setNumeroacta(""+rec.getNumeroacta());
            recibo.setFechaDeVencimiento(convertirDateAString(rec.getFechavencimiento()));
            mapa.add(recibo);
        }
        return mapa;
    }

    public List<Recibo> obtenerRecibosCompletadasRendidos(String usuario) {
        List<Recibo> mapa = new ArrayList<Recibo>();
        List<Recibos> recibos = recibosController.obtenerRecibosCompletadasRendidos(usuario);
        Recibo recibo;
        for(Recibos rec : recibos){
            recibo = new Recibo();
            recibo.setNumero(rec.getRecibosPK().getNumero());
            recibo.setFechaConfeccion(convertirDateAString(rec.getFechaconfeccion()));
            recibo.setRazonSocial(rec.getRazonsocial());
            recibo.setMonto(""+rec.getMonto());
            recibo.setEstadoTransaccion(rec.getEstadotransaccion());
            recibo.setBanco(rec.getBanco());
            recibo.setNumeroacta(""+rec.getNumeroacta());
            recibo.setNumeroCheque(rec.getNumerocheque());
            recibo.setFechaDeVencimiento(convertirDateAString(rec.getFechavencimiento()));
            mapa.add(recibo);
        }
        return mapa;
    }

    public List<Recibo> obtenerRecibosAnuladas(String usuario) {
        List<Recibos> recibos = recibosController.obtenerRecibosAnuladas(usuario);
        List<Recibo> mapa = new ArrayList<Recibo>();
        Recibo recibo;
        for(Recibos rec : recibos){
            recibo = new Recibo();
            recibo.setNumero(rec.getRecibosPK().getNumero());
            recibo.setFechaConfeccion(convertirDateAString(rec.getFechaconfeccion()));
            recibo.setMotivo(rec.getMotivo());
            mapa.add(recibo);
        }
        return mapa;
    }

    public List<Recibo> obtenerRecibosAnuladasRendidos(String usuario) {
        List<Recibos> recibos = recibosController.obtenerRecibosAnuladasRendidos(usuario);
        List<Recibo> mapa = new ArrayList<Recibo>();
        Recibo recibo;
        for(Recibos rec : recibos){
            recibo = new Recibo();
            recibo.setNumero(rec.getRecibosPK().getNumero());
            recibo.setFechaConfeccion(convertirDateAString(rec.getFechaconfeccion()));
            recibo.setMotivo(rec.getMotivo());
            mapa.add(recibo);
        }
        return mapa;
    }

    public List<Recibo> obtenerRecibosExtraviadas(String usuario) {
        List<Recibos> recibos = recibosController.obtenerRecibosExtraviadas(usuario);
        List<Recibo> mapa = new ArrayList<Recibo>();
        Recibo recibo;
        for(Recibos rec : recibos){
            recibo = new Recibo();
            recibo.setNumero(rec.getRecibosPK().getNumero());
            recibo.setFechaConfeccion(convertirDateAString(rec.getFechaconfeccion()));
            recibo.setMotivo(rec.getMotivo());
            mapa.add(recibo);
        }
        return mapa;
    }

    public List<Recibo> obtenerRecibosExtraviadasRendidos(String usuario) {
        List<Recibos> recibos = recibosController.obtenerRecibosExtraviadasRendidos(usuario);
        List<Recibo> mapa = new ArrayList<Recibo>();
        Recibo recibo;
        for(Recibos rec : recibos){
            recibo = new Recibo();
            recibo.setNumero(rec.getRecibosPK().getNumero());
            recibo.setFechaConfeccion(convertirDateAString(rec.getFechaconfeccion()));
            recibo.setMotivo(rec.getMotivo());
            mapa.add(recibo);
        }
        return mapa;
    }

    public String reciboExtraviada(String numero, String motivo, String fecha) {
        try {
            Recibos recibo = recibosController.findRecibosByNumero(numero);
            recibo.setMotivo(motivo);
            recibo.setFechaconfeccion(convertirStringADate(fecha));
            recibo.setEstadotransaccion("a confirmar");
            recibo.setEstadorecibo("extraviada");
            recibosController.edit(recibo);
            return "true";
        } catch (NonexistentEntityException ex) {
            return "false";
        } catch (Exception ex) {
            return "false";
        }
    }

    public String completarReciboChequePorOperador(Recibo rec) {
        try {
            Recibos recibo = recibosController.findRecibosByNumero("" + rec.getNumero());
            recibo.setFechaconfeccion(convertirStringADate(rec.getFechaConfeccion()));
            recibo.setFechavencimiento(convertirStringADate(rec.getFechaDeVencimiento()));
            recibo.setRazonsocial(rec.getRazonSocial());
            recibo.setMonto(Double.parseDouble(rec.getMonto()));
            recibo.setBanco(rec.getBanco());
            recibo.setNumerocheque(rec.getNumeroCheque());
            recibo.setNumerocuota(rec.getNumeroCuota());
            recibo.setEstadotransaccion("a confirmar");
            recibo.setEstadorecibo("completada");
            recibosController.edit(recibo);
            return "true";
        } catch (NonexistentEntityException ex) {
            return "false";
        } catch (Exception ex) {
            return "false";
        }
    }

    public String completarReciboEfectivoPorOperador(Recibo rec) {
        try {
            Recibos recibo = recibosController.findRecibosByNumero("" + rec.getNumero());
            recibo.setFechaconfeccion(convertirStringADate(rec.getFechaConfeccion()));
            recibo.setRazonsocial(rec.getRazonSocial());
            recibo.setMonto(Double.parseDouble(rec.getMonto()));
            recibo.setNumerocuota(rec.getNumeroCuota());
            recibo.setEstadotransaccion("a confirmar");
            recibo.setEstadorecibo("completada");
            recibosController.edit(recibo);
            return "true";
        } catch (NonexistentEntityException ex) {
            return "false";
        } catch (Exception ex) {
            return "false";
        }
    }

    public String actualizarRecibo(Recibo rec) {
        try {
            Recibos recibo = recibosController.findRecibosByNumero("" + rec.getNumero());
            recibo.setFechaconfeccion(convertirStringADate(rec.getFechaConfeccion()));
            recibo.setRazonsocial(rec.getRazonSocial());
            if(rec.getMonto() != null){
                recibo.setMonto(Double.parseDouble(rec.getMonto()));
            }
            recibo.setMotivo(rec.getMotivo());
            recibo.setBanco(rec.getBanco());
            recibo.setNumerocheque(rec.getNumeroCheque());
            recibo.setFechavencimiento(convertirStringADate(rec.getFechaDeVencimiento()));
            recibo.setNumerocuota(rec.getNumeroCuota());
            if(rec.getNumeroacta() != null){
                recibo.setNumeroacta(Integer.parseInt(rec.getNumeroacta()));
            }
            recibosController.edit(recibo);
            return "true";
        } catch (NonexistentEntityException ex) {
            return "false";
        } catch (Exception ex) {
            return "false";
        }
    }

    public String cargarRecibos(String responsable, String min, String max) {
        Usuarios usuario = usuariosController.findUsuariosByResponsable(responsable);
        long cant = recibosController.existeRecibo(min, max);
        if (cant == 0) {
            Recibos recibo;
            RecibosPK rPK;
            for (int i = Integer.parseInt(min); i < Integer.parseInt(max) + 1; i++) {
                recibo = new Recibos();
                rPK = new RecibosPK(usuario.getUsuario(), i);
                recibo.setRecibosPK(rPK);
                recibo.setEstadotransaccion("pendiente");
                try {
                    recibosController.create(recibo);
                } catch (PreexistingEntityException ex) {
                    return "false";
                } catch (Exception ex) {
                    return "false";
                }
            }
            return "true";
        } else {
            return "false";
        }
    }

    public void devolverRecibos(String responsable, String[] numeros) {
        Usuarios usuario = usuariosController.findUsuariosByResponsable(responsable);
        RecibosPK rPK;
        for (String numero : numeros) {
            try {
                rPK = new RecibosPK(usuario.getUsuario(), Integer.parseInt(numero));
                recibosController.destroy(rPK);
            } catch (NonexistentEntityException ex) {
            }
        }
    }

    public List<Integer> obtenerRecibosPendientes(String responsable) {
        Usuarios usuario = usuariosController.findUsuariosByResponsable(responsable);
        return recibosController.obtenerRecibosPendientes(usuario.getUsuario());
    }

    public List<Integer> obtenerRecibosPendientesDeUsuario(String usuario) {
        return recibosController.obtenerRecibosPendientes(usuario);
    }

    public void confirmarRecibos(List<Integer> recibos) {
        Recibos rec;
        for(Integer recibo : recibos){
            rec = recibosController.findRecibosByNumero(""+recibo);
            rec.setEstadotransaccion("rendida");
            try {
                recibosController.edit(rec);
            } catch (NonexistentEntityException ex) {
            } catch (Exception ex) {
            }
        }
    }

    public List<Recibo> obtenerRecibosAConfirmar(String responsable) {
        Usuarios usuario = usuariosController.findUsuariosByResponsable(responsable);
        List<Recibos> recibos = recibosController.obtenerRecibosAConfirmar(usuario.getUsuario());
        List<Recibo> mapa = new ArrayList<Recibo>();
        Recibo recibo;
        for(Recibos rec : recibos){
            recibo = new Recibo();
            recibo.setNumero(rec.getRecibosPK().getNumero());
            recibo.setRazonSocial(rec.getRazonsocial());
            recibo.setFechaConfeccion(convertirDateAString(rec.getFechaconfeccion()));
            recibo.setMonto(""+rec.getMonto());
            recibo.setMotivo(rec.getMotivo());
            recibo.setEstadorecibo(rec.getEstadorecibo());
            recibo.setBanco(rec.getBanco());
            recibo.setNumeroCuota(rec.getNumerocuota());
            recibo.setNumeroCheque(rec.getNumerocheque());
            recibo.setFechaDeVencimiento(convertirDateAString(rec.getFechavencimiento()));
            mapa.add(recibo);
        }
        return mapa;
    }

    public boolean isConnected() {
        Conexion con = new Conexion();
        Conexion.driverOdbc();
        if (con.abrirConexion()) {
            return true;
        }
        return false;
    }

    public String completarReciboChequePorInspector(Recibo rec) {
        try {
            Recibos recibo = recibosController.findRecibosByNumero("" + rec.getNumero());
            recibo.setFechaconfeccion(convertirStringADate(rec.getFechaConfeccion()));
            recibo.setFechavencimiento(convertirStringADate(rec.getFechaDeVencimiento()));
            recibo.setRazonsocial(rec.getRazonSocial());
            recibo.setMonto(Double.parseDouble(rec.getMonto()));
            recibo.setBanco(rec.getBanco());
            recibo.setNumerocheque(rec.getNumeroCheque());
            recibo.setNumerocuota(rec.getNumeroCuota());
            recibo.setNumeroacta(Integer.parseInt(rec.getNumeroacta()));
            recibo.setEstadotransaccion("a confirmar");
            recibo.setEstadorecibo("completada");
            recibosController.edit(recibo);
            return "true";
        } catch (NonexistentEntityException ex) {
            return "false";
        } catch (Exception ex) {
            return "false";
        }
    }

    public String completarReciboEfectivoPorInspector(Recibo rec) {
        try {
            Recibos recibo = recibosController.findRecibosByNumero("" + rec.getNumero());
            recibo.setFechaconfeccion(convertirStringADate(rec.getFechaConfeccion()));
            recibo.setRazonsocial(rec.getRazonSocial());
            recibo.setMonto(Double.parseDouble(rec.getMonto()));
            recibo.setNumerocuota(rec.getNumeroCuota());
            recibo.setNumeroacta(Integer.parseInt(rec.getNumeroacta()));
            recibo.setEstadotransaccion("a confirmar");
            recibo.setEstadorecibo("completada");
            recibosController.edit(recibo);
            return "true";
        } catch (NonexistentEntityException ex) {
            return "false";
        } catch (Exception ex) {
            return "false";
        }
    }

    public String totalEfectivoOperador(String usuario) {
        String total = "0";
        Double totalAux = recibosController.totalEfectivoOperador(usuario);
        if(totalAux != null){
            total = totalAux.toString();
        }
        return total;
    }

    public String totalChequeOperador(String usuario) {
        String total = "0";
        Double totalAux = recibosController.totalChequeOperador(usuario);
        if(totalAux != null){
            total = totalAux.toString();
        }
        return total;
    }

    public List<Recibo> obtenerRecibosAConfirmarPorUsuario(String usuario) {
        List<Recibos> recs = recibosController.obtenerRecibosAConfirmar(usuario);
        List<Recibo> mapa = new ArrayList<Recibo>();
        Recibo recibo;
        for(Recibos rec : recs){
            recibo = new Recibo();
            recibo.setNumero(rec.getRecibosPK().getNumero());
            recibo.setRazonSocial(rec.getRazonsocial());
            recibo.setFechaConfeccion(convertirDateAString(rec.getFechaconfeccion()));
            recibo.setMonto(""+rec.getMonto());
            recibo.setMotivo(rec.getMotivo());
            recibo.setEstadorecibo(rec.getEstadorecibo());
            recibo.setBanco(rec.getBanco());
            recibo.setNumeroCuota(rec.getNumerocuota());
            recibo.setNumeroCheque(rec.getNumerocheque());
            recibo.setFechaDeVencimiento(convertirDateAString(rec.getFechavencimiento()));
            mapa.add(recibo);
        }
        return mapa;
    }
}
