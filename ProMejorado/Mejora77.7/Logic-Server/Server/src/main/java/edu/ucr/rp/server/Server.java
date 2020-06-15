package edu.ucr.rp.server;

import com.leoc.sockets.multithread.server.*;
import java.io.*;
import java.net.*;

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
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                String message = (String) in.readObject();                             // ----> Trae lo que se recoge en el lado cliente
                //   System.out.println("Message Received: " + message);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

                crud.writeFileCatalog(crud.passTo(message));
                
                System.out.println("Mensaje recibido:" + message);
                out.writeObject("(Mensaje desde servidor) Recibido: " + message);//--> Esta linea se imprime del lado de UI
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();//Puede lanzar una excepción puesto que el puerto puede estar ocupado
        }

    }//end server(int port)
}//end server
