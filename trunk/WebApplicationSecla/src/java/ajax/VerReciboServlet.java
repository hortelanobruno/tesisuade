/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ajax;

import db.DBManager;
import java.io.IOException;
import java.util.List;
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
public class VerReciboServlet extends HttpServlet {

    private DBManager manager;

    public void init(ServletConfig config) throws ServletException {
        manager = DBManager.getInstance();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String usuario = request.getParameter("usuario");
        List<Recibo> boletas = manager.obtenerRecibosAConfirmar(usuario);
        if (boletas.isEmpty()) {
            response.setContentType("text/xml");
            response.setHeader("Cache-Control", "no-cache");
            response.getWriter().write("<estado>vacio</estado>");
        } else {
            response.setContentType("text/xml");
            response.setHeader("Cache-Control", "no-cache");
            response.getWriter().write("<?xml version='1.0' encoding='ISO-8859-1'?>");
            response.getWriter().write("<boletas>");
            response.getWriter().write("<cantidad>" + boletas.size() + "</cantidad>");
            response.getWriter().write("<estado>lleno</estado>");
            Recibo boleta;
            for (int i = 0; i < boletas.size(); i++) {
                boleta = (Recibo) boletas.get(i);
                response.getWriter().write("<boleta>");
                response.getWriter().write("<numero>" + boleta.getNumero() + "</numero>");
                response.getWriter().write("<estadoboleta>" + boleta.getEstadorecibo() + "</estadoboleta>");
                response.getWriter().write("<fecharendicion>" + boleta.getFechaConfeccion() + "</fecharendicion>");
                response.getWriter().write("<beneficiario>" + boleta.getRazonSocial() + "</beneficiario>");
                response.getWriter().write("<monto>" + boleta.getMonto() + "</monto>");
                response.getWriter().write("<motivo>" + boleta.getMotivo() + "</motivo>");
                response.getWriter().write("<numerocuota>" + boleta.getNumeroCuota() + "</numerocuota>");
                response.getWriter().write("<banco>" + boleta.getBanco() + "</banco>");
                response.getWriter().write("<numerocheque>" + boleta.getNumeroCheque() + "</numerocheque>");
                response.getWriter().write("<fechavencimiento>" + boleta.getFechaDeVencimiento() + "</fechavencimiento>");
                response.getWriter().write("</boleta>");
            }
            response.getWriter().write("</boletas>");
        }
    }
}
