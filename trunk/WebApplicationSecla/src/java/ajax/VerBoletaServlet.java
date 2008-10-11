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
import varios.Boleta;

/**
 *
 * @author Administrador
 */
public class VerBoletaServlet extends HttpServlet {
    
    private DBManager manager;
    
    public void init(ServletConfig config) throws ServletException {
        manager = new DBManager();
    }

    public  void doGet(HttpServletRequest request, HttpServletResponse  response)
    throws IOException, ServletException {
        String usuario = request.getParameter("usuario");
        List<Boleta> boletas = manager.obtenerBoletasAConfirmar(usuario);
        if(boletas.isEmpty()){
            response.setContentType("text/xml");
            response.setHeader("Cache-Control", "no-cache");
            response.getWriter().write("<estado>vacio</estado>");
        }else{
            response.setContentType("text/xml");
            response.setHeader("Cache-Control", "no-cache");
            response.getWriter().write("<boletas>");
            response.getWriter().write("<cantidad>"+boletas.size()+"</cantidad>");
            for(int i=0 ; i < boletas.size() ; i++){
                Boleta boleta = boletas.get(i);
                response.getWriter().write("<boleta"+(i+1)+">");
                response.getWriter().write("<beneficiario>"+boleta.getBeneficiario()+"</beneficiario>");
                response.getWriter().write("<estadoboleta>"+boleta.getEstadoboleta()+"</estadoboleta>");
                response.getWriter().write("<fecharendicion>"+boleta.getFecharendicion()+"</fecharendicion>");
                response.getWriter().write("<motivo>"+boleta.getMotivo()+"</motivo>");
                response.getWriter().write("<monto>"+boleta.getMonto()+"</monto>");
                response.getWriter().write("<numero>"+boleta.getNumero()+"</numero>");
                response.getWriter().write("</boleta"+(i+1)+">");
            }
            response.getWriter().write("</boletas>");
        }   
    }
}
