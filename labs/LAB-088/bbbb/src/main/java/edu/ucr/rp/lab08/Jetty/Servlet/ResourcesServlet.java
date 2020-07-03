
package edu.ucr.rp.lab08.Jetty.Servlet;

import edu.ucr.rp.lab08.Jetty.FileUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ResourcesServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     String name = req.getParameter("name");// ejemplo : style.css
     // con esto el aerchivo va a ser buscado en el directorio
     // correspondiente
     FileUtil.readResource(name, resp.getOutputStream());
     
     
    
    

}// end doGet()
}