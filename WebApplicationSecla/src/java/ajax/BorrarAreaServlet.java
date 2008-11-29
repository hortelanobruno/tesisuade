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

/**
 *
 * @author Administrador
 */
public class BorrarAreaServlet extends HttpServlet {

    private DBManager manager;

    public void init(ServletConfig config) throws ServletException {
        manager = new DBManager();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String usuario = request.getParameter("usuario");
        boolean borrar = manager.chequearBorrarUsuario(usuario);
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write("<usuario>");
        response.getWriter().write("<borrar>" + borrar + "</borrar>");
        response.getWriter().write("</usuario>");
    }
}
