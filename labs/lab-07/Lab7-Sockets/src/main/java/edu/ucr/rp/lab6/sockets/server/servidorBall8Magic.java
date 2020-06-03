package edu.ucr.rp.lab6.sockets.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class servidorBall8Magic {

    ServerSocket serverSocket;

    String respuestas[] = new String[]{"Si",
        "No",
        "Quiza",
        "Como quiera"};

    public servidorBall8Magic(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Esperando conexión");
            while (true) {
                Socket socket = serverSocket.accept();//esperando a que llegue una conexión
                System.out.println("Conexión recibida");
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                String message = (String) in.readObject();
                System.out.println("Pregunta recibida: " + message);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

                
                int i = new Random().nextInt(respuestas.length-1);
                
                System.out.println("Respuestas enviada:" + respuestas[i]);
                out.writeObject(respuestas[i]);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();//Puede lanzar una excepción puesto que el puerto puede estar ocupado
        }

    }
}
