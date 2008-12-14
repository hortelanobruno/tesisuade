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

/**
 *
 * @author Administrador
 */
public class BorrarAreaServlet extends HttpServlet {

    private DBManager manager;

    public void init(ServletConfig config) throws ServletException {
        manager = DBManager.getInstance();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        String responsable = request.getParameter("responsable");
        boolean borrar = manager.chequearBorrarResponsable(responsable);
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write("<?xml version='1.0' encoding='ISO-8859-1'?>");
        response.getWriter().write("<usuario>");
        response.getWriter().write("<borrar>" + borrar + "</borrar>");
        response.getWriter().write("</usuario>");
    }
}
