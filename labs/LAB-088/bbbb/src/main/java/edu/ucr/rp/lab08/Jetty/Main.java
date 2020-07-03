
package edu.ucr.rp.lab08.Jetty;

import edu.ucr.rp.lab08.Jetty.Server.JettyServer;
import edu.ucr.rp.lab08.Jetty.Servlet.ResourcesServlet;
import edu.ucr.rp.lab08.Jetty.Servlet.RoomServlet;
import edu.ucr.rp.lab08.Jetty.Servlet.WelcomeServlet;


public class Main {

   
    public static void main(String[] args) throws Exception {
    
        JettyServer server = new JettyServer(8080);// implicitamente es local host      
        server.registerServlet(WelcomeServlet.class, "/welcome");
        server.registerServlet(ResourcesServlet.class,"/resources");
        server.registerServlet(RoomServlet.class,"/room");
        server.start();
    
    
    
    }
    
}
