
import org.apache.cactus.JspTestCase;
import org.apache.cactus.WebRequest;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Administrador
 */
public class IndexTest extends JspTestCase {

    
    public void beginXXX(WebRequest webRequest) {
        webRequest.addParameter("usuario", "admin");
        webRequest.addParameter("password", "admin");
        webRequest.setURL("localhost:8081", "WebApplicationSecla\\index.jsp", "", "", "");
    }

    public void testXXX() throws Exception
	{
        
        request.setAttribute("usuario", "admin");
        request.setAttribute("password", "admin");
        pageContext.forward("/index.jsp");
	}

}
