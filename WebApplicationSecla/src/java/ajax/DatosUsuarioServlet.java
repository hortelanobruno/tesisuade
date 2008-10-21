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
public class DatosUsuarioServlet extends HttpServlet {
    
    private DBManager manager;
    
    public void init(ServletConfig config) throws ServletException {
        manager = new DBManager();
    }

    public  void doGet(HttpServletRequest request, HttpServletResponse  response)
    throws IOException, ServletException {
        String usuario = request.getParameter("usuario");
        List<String> datos = manager.datosUsuario(usuario);
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write("<usuario>");
        response.getWriter().write("<sede>"+datos.get(2)+"</sede>");
        response.getWriter().write("<sector>"+datos.get(3)+"</sector>");
        response.getWriter().write("<responsable>"+datos.get(1)+"</responsable>");
        response.getWriter().write("<digitos>"+datos.get(4)+"</digitos>");
        response.getWriter().write("<password>"+datos.get(0)+"</password>");
        response.getWriter().write("</usuario>");
    }
}
