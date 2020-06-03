package edu.ucr.rp.lab6.sockets.cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class Client {
       

    public static void enviarMensaje(String server, int port, String pregunta) {
        Socket clientSocket= null;
        try {

            clientSocket = new Socket(server, port);//
            Thread.sleep(5000);
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            out.writeObject(pregunta + Thread.currentThread().getName());
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

            System.out.println(in.readObject());
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }// end client
}
