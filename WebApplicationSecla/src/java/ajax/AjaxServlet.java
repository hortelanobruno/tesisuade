/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ajax;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrador
 */
public class AjaxServlet extends HttpServlet {
    
    private ServletContext context;
    
    public void init(ServletConfig config) throws ServletException {
        this.context = config.getServletContext();
    }

    public  void doGet(HttpServletRequest request, HttpServletResponse  response)
    throws IOException, ServletException {
        
        // Extract the data of the input form field whose name is "id"
        String targetId = request.getParameter("id");
        
        //  Send back either "<valid>true</valid>" or "<valid>false</valid>"
        //  XML message depending on the validity of the data that was entered.
        //  Note that the content type is "text/xml".
        //
        if ((targetId != null)) {
            response.setContentType("text/xml");
            response.setHeader("Cache-Control", "no-cache");
            response.getWriter().write("<valid>true</valid>");
        } else {
            response.setContentType("text/xml");
            response.setHeader("Cache-Control", "no-cache");
            response.getWriter().write("<valid>false</valid>");
        }
    }
    
    public  void doPost(HttpServletRequest request, HttpServletResponse  response)
    throws IOException, ServletException {
        
        String targetId = request.getParameter("id");
        if ((targetId != null)) {
            request.setAttribute("targetId", targetId);
            context.getRequestDispatcher("/success.jsp").forward(request, response);
        } else {
            context.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
