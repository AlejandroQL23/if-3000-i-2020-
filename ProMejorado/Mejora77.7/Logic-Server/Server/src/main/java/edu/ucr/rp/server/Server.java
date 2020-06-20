package edu.ucr.rp.server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server {

    CRUD crud = new CRUD();
    ServerSocket serverSocket;

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Esperando peticion cliente");
            while (true) {
                Socket socket = serverSocket.accept();//esperando a que llegue una conexión
                System.out.println("peticion recibida");
          ///      ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
         ///       String message = (String) in.readObject();                             // ----> Trae lo que se recoge en el lado cliente
                //   System.out.println("Message Received: " + message);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                
                 ObjectOutputStream out2 = new ObjectOutputStream(socket.getOutputStream());
//////
//////                if (message.contains("*")) {
//////                    System.out.println("HOLA, GUARDO TXT");
//////                    crud.writeFileCatalog(crud.passTo(message.substring(1, message.length())));
//////
//////                } else if (message.contains("%")) {
//////                    System.out.println(" sirvio");
//////                }

              ///  ArrayList l = new ArrayList();
            ///    System.out.println("Mensaje recibido:" + message);
                //    out.writeObject("(Mensaje desde servidor) Recibido: " + l);//--> Esta linea se imprime del lado de UI
                out.writeObject(crud.createComboBox());
                out2.writeObject(crud.createComboBox());
            }
        } catch (IOException e) {
            e.printStackTrace();//Puede lanzar una excepción puesto que el puerto puede estar ocupado
        }

    }//end server(int port)
}//end server
