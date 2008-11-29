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
        manager = new DBManager();
    }

    public  void doGet(HttpServletRequest request, HttpServletResponse  response)
    throws IOException, ServletException {
        String numero = request.getParameter("numero");
        Recibo recibo = manager.obtenerReciboAConfirmar(numero);
        String fecha = recibo.getFecharendicion();
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write("<recibo>");
        response.getWriter().write("<numero>"+recibo.getNumero()+"</numero>");
        response.getWriter().write("<estadoboleta>"+recibo.getEstadorecibo()+"</estadoboleta>");
        response.getWriter().write("<fecharendicion>"+fecha+"</fecharendicion>");
        response.getWriter().write("<beneficiario>"+recibo.getBeneficiario()+"</beneficiario>");
        response.getWriter().write("<motivo>"+recibo.getMotivo()+"</motivo>");
        response.getWriter().write("<monto>"+recibo.getMonto()+"</monto>");
        response.getWriter().write("</recibo>");
    }
}
