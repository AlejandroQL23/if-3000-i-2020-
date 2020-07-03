
package edu.ucr.rp.lab08.Jetty.Server;

import javax.servlet.http.HttpServlet;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.util.thread.QueuedThreadPool;


public class JettyServer {

  private Server server;
    private ServletHandler handler;
    private Integer port;
    private final Integer maxThreads =180;
     private final Integer minThreads =18;
      private final Integer idleTimeout=120;


    public JettyServer(Integer port) {
        this.port = port;
        handler = new ServletHandler();
    }
    
     //------------------------------
    // inicializar nuestro server
    public void start() throws Exception {
        QueuedThreadPool threadPool = new QueuedThreadPool(maxThreads, minThreads, idleTimeout);
        server = new Server(threadPool);
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);
        server.setConnectors(new Connector[] {connector});
        server.setHandler(handler);

        server.start();
        server.join();
    }
    //------------------------------
    public void registerServlet(Class<? extends HttpServlet> servlet, String path) {
        handler.addServletWithMapping(servlet, path);
    }
}// end class()
