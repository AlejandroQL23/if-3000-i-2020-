package edu.ucr.rp.server;

import java.io.*;
import java.net.*;
import edu.ucr.rp.server.Logic.CRUD;

public class Server {

    CRUD crud = new CRUD();
    ServerSocket serverSocket;
    Socket socket;
    String message;

    public Server(int port) throws ClassNotFoundException {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Esperando peticion cliente");
            while (true) {
                socket = serverSocket.accept();
                System.out.println("peticion recibida");
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                message = (String) in.readObject();
                System.out.println("Message Received: " + message);
                if (message.contains("createCatalog")) {
                    createCatalog();
                } else if (message.contains("createProperties")) {
                    createProperties();
                } else if (message.contains("showCatalog's")) {
                    showCatalog();
                } else if (message.contains("showProperties")) {
                    showProperties();
                } else if (message.contains("search")) {
                    searchProp();
                } else if (message.contains("delete")) {
                    delete();
                } else if (message.contains("modify")) {
                    modifyProper();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();//Puede lanzar una excepción puesto que el puerto puede estar ocupado
        }

    }//end server(int port)

    private void createCatalog() throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        System.out.println("Cliente solicito servicio de creacion de catalogo");
        crud.writeFileCatalog(crud.passTo(message.substring(13, message.length())));
        out.writeObject("(Mensaje desde servidor) Recibido: " + message);
    }//end createCatalog

    private void createProperties() throws IOException, ClassNotFoundException {
        ObjectOutputStream outWriteComboBox = new ObjectOutputStream(socket.getOutputStream());
        System.out.println(" Cliente solicito servicio crear .propiedades");
        outWriteComboBox.writeObject(crud.createComboBox()); //-----> Manda mensaje
        Socket socketBringName = serverSocket.accept();
        ObjectInputStream inBringName = new ObjectInputStream(socketBringName.getInputStream());
        String catalogName = (String) inBringName.readObject();  // ----> Trae lo que se recoge en el lado cliente
        ObjectOutputStream outSendQuantity = new ObjectOutputStream(socketBringName.getOutputStream());
        outSendQuantity.writeObject(crud.linesAccount(catalogName)); //-----> Manda mensaje
        ObjectOutputStream sendProperties = new ObjectOutputStream(socketBringName.getOutputStream());
        sendProperties.writeObject(crud.readProperties(catalogName, crud.linesAccount(catalogName))); //-----> Manda mensaje
        Socket socketBringProperties = serverSocket.accept();//esperando a que llegue una conexión
        ObjectInputStream outBringProperties = new ObjectInputStream(socketBringProperties.getInputStream());
        String properties = (String) outBringProperties.readObject();  // ----> Trae lo que se recoge en el lado cliente
        crud.writeFileProperties(properties);
    }//end createProperties

    private void showCatalog() throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        System.out.println(" Cliente solicito servicio mostrar .catalogos existentes");
        out.writeObject(crud.createComboBox());
    }

    private void showProperties() throws IOException {

        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        System.out.println(" Cliente solicito servicio mostrar todo el contenido de .propiedades especifico");
        out.writeObject(crud.showContent(message.substring(14, message.length())));  // message.subtri(1, mesagge.length) 

    }//end showProperties

    private void delete() throws IOException {

        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        System.out.println("Cliente solicito servicio de eliminar .catalogo y .propiedades");
        out.writeObject(crud.deleteFiles(message.substring(6, message.length())));

    }

    private void modifyProper() throws IOException, ClassNotFoundException {

        ObjectOutputStream outWriteComboBox = new ObjectOutputStream(socket.getOutputStream());
        System.out.println(" Cliente solicito servicio modificar propiedades");
        outWriteComboBox.writeObject(crud.createComboBox());

        Socket socketComb = serverSocket.accept();
        ObjectInputStream inComb = new ObjectInputStream(socketComb.getInputStream());
        String comboString = (String) inComb.readObject();
        ObjectOutputStream outSendComb = new ObjectOutputStream(socketComb.getOutputStream());
        outSendComb.writeObject((crud.linesInProperties(comboString)));

        Socket socketBringName = serverSocket.accept();
        ObjectInputStream inBringName = new ObjectInputStream(socketBringName.getInputStream());
        String catalogName = (String) inBringName.readObject();

        ObjectOutputStream outSendQuantity = new ObjectOutputStream(socketBringName.getOutputStream());
        outSendQuantity.writeObject((crud.linesAccount(crud.readCatalog(catalogName))));

        ObjectOutputStream sendProperties = new ObjectOutputStream(socketBringName.getOutputStream());
        sendProperties.writeObject(crud.readProperties(crud.readCatalog(catalogName), crud.linesAccount(crud.readCatalog(catalogName))));

        ObjectOutputStream sendAtributes = new ObjectOutputStream(socketBringName.getOutputStream());
        sendAtributes.writeObject(crud.readAtributes(crud.readCatalog(catalogName), catalogName));

        Socket socketBringProperties = serverSocket.accept();
        ObjectInputStream outBringProperties = new ObjectInputStream(socketBringProperties.getInputStream());
        String properties = (String) outBringProperties.readObject();
        ObjectInputStream outLastProperties = new ObjectInputStream(socketBringProperties.getInputStream());
        String last = (String) outLastProperties.readObject();
        crud.modify(crud.readCatalog(properties), crud.readAtribute(last), properties);
    }//end modify

    private void searchProp() throws IOException, ClassNotFoundException {

        ObjectOutputStream outWriteComboBox = new ObjectOutputStream(socket.getOutputStream());
        System.out.println(" Cliente solicito servicio busqueda por propiedads");
        outWriteComboBox.writeObject(crud.createComboBox());
        Socket socketComb = serverSocket.accept();
        ObjectInputStream inComb = new ObjectInputStream(socketComb.getInputStream());
        String comboString = (String) inComb.readObject();
        ObjectOutputStream outSendComb = new ObjectOutputStream(socketComb.getOutputStream());
        outSendComb.writeObject((crud.linesInProperties(comboString)));

        Socket socketBringName = serverSocket.accept();
        ObjectInputStream inBringName = new ObjectInputStream(socketBringName.getInputStream());
        String catalogName = (String) inBringName.readObject();
        ObjectOutputStream outSendQuantity = new ObjectOutputStream(socketBringName.getOutputStream());
        outSendQuantity.writeObject((crud.linesAccount(crud.readCatalog(catalogName))));
        ObjectOutputStream sendProperties = new ObjectOutputStream(socketBringName.getOutputStream());
        sendProperties.writeObject(crud.readProperties(crud.readCatalog(catalogName), crud.linesAccount(crud.readCatalog(catalogName))));
        ObjectOutputStream sendAtributes = new ObjectOutputStream(socketBringName.getOutputStream());
        sendAtributes.writeObject(crud.readAtributes(crud.readCatalog(catalogName), catalogName));

    }

}//end server
