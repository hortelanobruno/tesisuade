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
        manager = new DBManager();
    }

    public  void doGet(HttpServletRequest request, HttpServletResponse  response)
    throws IOException, ServletException {
        String usuario = request.getParameter("usuario");
        System.out.println("1");
        List<Recibo> boletas = manager.obtenerRecibosAConfirmar(usuario);
        System.out.println("2");
        if(boletas.isEmpty()){
            System.out.println("3");
            response.setContentType("text/xml");
            response.setHeader("Cache-Control", "no-cache");
            response.getWriter().write("<estado>vacio</estado>");
        }else{
            System.out.println("4");
            response.setContentType("text/xml");
            response.setHeader("Cache-Control", "no-cache");
            response.getWriter().write("<boletas>");
            response.getWriter().write("<cantidad>"+boletas.size()+"</cantidad>");
            response.getWriter().write("<estado>lleno</estado>");
            for(int i=0 ; i < boletas.size() ; i++){
                Recibo boleta = (Recibo) boletas.get(i);
                response.getWriter().write("<boleta>");
                response.getWriter().write("<numero>"+boleta.getNumero()+"</numero>");
                response.getWriter().write("<estadoboleta>"+boleta.getEstadorecibo()+"</estadoboleta>");
                response.getWriter().write("<fecharendicion>"+boleta.getFecharendicion()+"</fecharendicion>");
                response.getWriter().write("<beneficiario>"+boleta.getBeneficiario()+"</beneficiario>");
                response.getWriter().write("<monto>"+boleta.getMonto()+"</monto>");
                response.getWriter().write("<motivo>"+boleta.getMotivo()+"</motivo>");
                response.getWriter().write("</boleta>");
            }
            response.getWriter().write("</boletas>");
        }   
    }
}
