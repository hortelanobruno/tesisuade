/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ajax;

import db.DBManager;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import varios.Recibo;

/**
 *
 * @author Administrador
 */
public class ModificarReciboServlet extends HttpServlet{

    private DBManager manager;

    public void init(ServletConfig config) throws ServletException {
        manager = DBManager.getInstance();
    }

    public  void doGet(HttpServletRequest request, HttpServletResponse  response)
    throws IOException, ServletException {
        String numero = request.getParameter("numero");
        Recibo recibo = manager.obtenerReciboAConfirmar(numero);
        String tipoUsuario = manager.obtenerTipoUsuario(numero);
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write("<?xml version='1.0' encoding='ISO-8859-1'?>");
        response.getWriter().write("<recibo>");
        response.getWriter().write("<numero>"+recibo.getNumero()+"</numero>");
        response.getWriter().write("<estadoboleta>"+recibo.getEstadorecibo()+"</estadoboleta>");
        response.getWriter().write("<fecharendicion>"+recibo.getFechaConfeccion()+"</fecharendicion>");
        response.getWriter().write("<beneficiario>"+recibo.getRazonSocial()+"</beneficiario>");
        response.getWriter().write("<motivo>"+recibo.getMotivo()+"</motivo>");
        response.getWriter().write("<monto>"+recibo.getMonto()+"</monto>");
        response.getWriter().write("<numerocuota>"+recibo.getNumeroCuota()+"</numerocuota>");
        response.getWriter().write("<banco>"+recibo.getBanco()+"</banco>");
        response.getWriter().write("<numerocheque>"+recibo.getNumeroCheque()+"</numerocheque>");
        response.getWriter().write("<fechavencimiento>"+recibo.getFechaDeVencimiento()+"</fechavencimiento>");
        if(tipoUsuario.equalsIgnoreCase("inspector")){
            response.getWriter().write("<numeroacta>"+recibo.getNumeroacta()+"</numeroacta>");
        }else{
            response.getWriter().write("<numeroacta></numeroacta>");
        }
        response.getWriter().write("</recibo>");
    }
}
