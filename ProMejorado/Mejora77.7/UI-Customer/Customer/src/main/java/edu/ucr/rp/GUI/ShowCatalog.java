package edu.ucr.rp.customer.GUI;

import static edu.ucr.rp.GUI.ConstantsElements.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.logging.*;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class ShowCatalog {

    public class Client {

        Socket clientSocket;
        //  Socket clientSocketx;
        ArrayList<String> catalogos = new ArrayList<String>();

        public Client(String server, int port) throws InterruptedException {
            // ArrayList<String> catalogos = new ArrayList<String>();
            try {
                System.out.println("Entro a try pero mjm");
                clientSocket = new Socket(server, port);//
                System.out.println("Entro a try pero mjm x2");
                Thread.sleep(1000);
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());

                out.writeObject("&");
                //          + stringProperties + textFieldPropertiesQuantity.getText()) ;//+ Thread.currentThread().getName()
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                //ObjectInputStream ines = new ObjectInputStream(clientSocket.getInputStream());

                System.out.println("Entro a try pero mjm x3");
                //    System.out.println(in.readObject());
                System.out.println("Soy la ultima linea");
                catalogos = (ArrayList<String>) in.readObject();  //---> Porblemas 

                // / ///     catalogos.add("juju");
                //  /     catalogos.add("juja");
                //      catalogos.add("jujo");
                //  catalogos = (ArrayList<String>) ines.readObject();
                System.out.println(catalogos + " catalogoooooooos");
                //   System.out.println((ArrayList<String>) ines.readObject() + " read ");
                // stringProperties="";
//                out.writeObject(textFieldCatalogName.getText() + "<>\n" + textFieldPropertiesQuantity.getText() + "<>\n"
//                        + Properties + "<>\n" + Thread.currentThread().getName()); // ---- >  Manda
//
//                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream()); //  < ---- trae
                // System.out.println(in.readObject()); //<--- esto imprime el mensaje que viene desde server
            }//end try 
            catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("CAch 1");
            } finally {
                System.out.println("finally");
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("CAch 2");
                }
            }//end finally
        }//end client (server, port)
    }//end client

    ComboBox comboBoxTool = new ComboBox();
    GridPane gridPaneShowCatalog = new GridPane();

    /**
     *
     * @return Nos da la GUI que nos mostrará los .catálogos existentes
     */
    public BorderPane showExistingCatalogs() {

        BorderPane borderPaneShowExistingCatalogs = new BorderPane();
        borderPaneShowExistingCatalogs.setPrefSize(BORDER_WIDTH, BORDER_HIGH);

        gridPaneShowCatalog.setMinSize(GRID_WIDTH, GRID_HIGH);
        gridPaneShowCatalog.setVgap(15);
        gridPaneShowCatalog.setHgap(15);
        gridPaneShowCatalog.setStyle(("-fx-background-image:url('file:src/image/2.jpg');"
                + "-fx-background-repeat : no-repeat;"
                + "-fx-background-size: 920 920, 20 20, 20 20, 20 20, auto;"));
        gridPaneShowCatalog.setAlignment(Pos.CENTER);

        comboBoxTool.setValue("Herramientas");
        comboBoxTool.setStyle("-fx-background-color: lightblue");
        comboBoxTool.setOnMouseClicked((event) -> {

            ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.submit(() -> {
                System.out.println("Al princi");
                try {
                    ShowCatalog.Client osoArio = new ShowCatalog.Client("192.168.1.7", 12345);
                    //   System.out.println(osoArio.clientSocket.getOutputStream() + " Soy tama");

                    for (int i = 0; i < osoArio.catalogos.size() - 1; i++) {
                        if (!comboBoxTool.getItems().contains(osoArio.catalogos.get(i))) {
                            comboBoxTool.getItems().addAll(osoArio.catalogos.get(i));
                        }//end if
                    }//end for
                    System.out.println("Llegue al final");

                } catch (InterruptedException ex) {
                    Logger.getLogger(DefineProperties.class.getName()).log(Level.SEVERE, null, ex);
                }

            });

        });

        gridPaneShowCatalog.add(comboBoxTool, 0, 0);
        //  graphicalUserInterfaceLogic.createComboBox(comboBoxTool);

        Button buttonClose = new Button("Cerrar");
        buttonClose.setTextFill(Color.WHITE);//Color de la letra del boton
        buttonClose.setStyle("-fx-background-color: BLACK");//Color del fondo
        buttonClose.setFont(Font.font("Castellar", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 10));//Tipo de letra
        gridPaneShowCatalog.add(buttonClose, 0, 9);
        buttonClose.setOnAction((event) -> {
            gridPaneShowCatalog.getChildren().clear();
            gridPaneShowCatalog.setBackground(Background.EMPTY);

        });

        borderPaneShowExistingCatalogs.setTop(gridPaneShowCatalog);

        return borderPaneShowExistingCatalogs;
    }//end showExistingCatalogs

}//end ShowCatalog
