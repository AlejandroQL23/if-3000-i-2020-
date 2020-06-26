package edu.ucr.rp.server;

import java.io.*;
import java.net.*;
import edu.ucr.rp.server.Logic.CRUD;
public class Server {

    CRUD crud = new CRUD();
    ServerSocket serverSocket;

    public Server(int port) throws ClassNotFoundException {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Esperando peticion cliente");
            while (true) {
                Socket socket = serverSocket.accept();//esperando a que llegue una conexión
                System.out.println("peticion recibida");
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                String message = (String) in.readObject();                             // ----> Trae lo que se recoge en el lado cliente
                System.out.println("Message Received: " + message);
                //ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

                //ObjectOutputStream out2 = new ObjectOutputStream(socket.getOutputStream());
                if (message.contains("*")) {
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    System.out.println("Cliente solicito servicio de creacion de catalogo");
                    crud.writeFileCatalog(crud.passTo(message.substring(1, message.length())));
                    out.writeObject("(Mensaje desde servidor) Recibido: " + message);
                } else if (message.contains("%")) {

                    System.out.println("peticion recibida");
                    ObjectOutputStream outWriteComboBox = new ObjectOutputStream(socket.getOutputStream());
                    outWriteComboBox.writeObject(crud.createComboBox()); //-----> Manda mensaje
                    ////////////
                    Socket socketBringName = serverSocket.accept();
                    ObjectInputStream inBringName = new ObjectInputStream(socketBringName.getInputStream());
                    String catalogName = (String) inBringName.readObject();  // ----> Trae lo que se recoge en el lado cliente
                    System.out.println(catalogName);
                    ObjectOutputStream outSendQuantity = new ObjectOutputStream(socketBringName.getOutputStream());
                    outSendQuantity.writeObject(crud.linesAccount(catalogName)); //-----> Manda mensaje
                    ObjectOutputStream sendProperties = new ObjectOutputStream(socketBringName.getOutputStream());
                    sendProperties.writeObject(crud.readProperties(catalogName, crud.linesAccount(catalogName))); //-----> Manda mensaje
                    /////////////
                    Socket socketBringProperties = serverSocket.accept();//esperando a que llegue una conexión
                    ObjectInputStream outBringProperties = new ObjectInputStream(socketBringProperties.getInputStream());
                    String properties = (String) outBringProperties.readObject();  // ----> Trae lo que se recoge en el lado cliente
                    crud.writeFileProperties(properties);

                } else if (message.contains("&")) {
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    System.out.println(" Cliente solicito servicio mostrar .catalogos existentes");
                    out.writeObject(crud.createComboBox());
                } else if (message.contains("$")) {
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    System.out.println(" Cliente solicito servicio mostrar todo el contenido de .propiedades especifico");
                    System.out.println(message.substring(1, message.length()) + " <---Lo que puse");
                    out.writeObject(crud.showContent(message.substring(1, message.length())));  // message.subtri(1, mesagge.length)
                } else if (message.contains("@")) {
                    System.out.println("Cliente solicito servicio de busqueda");
                } else if (message.contains("^")) {
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    System.out.println("Cliente solicito servicio de eliminar .catalogo y .propiedades");
                    out.writeObject(crud.deleteFiles(message.substring(1, message.length())));
                }

                ///  ArrayList l = new ArrayList();
                ///    System.out.println("Mensaje recibido:" + message);
                //   out.writeObject("(Mensaje desde servidor) Recibido: " + message);//--> Esta linea se imprime del lado de UI
                //     out.writeObject(crud.createComboBox());
                //    out2.writeObject(crud.createComboBox());
            }
        } catch (IOException e) {
            e.printStackTrace();//Puede lanzar una excepción puesto que el puerto puede estar ocupado
        }

    }//end server(int port)
}//end server
