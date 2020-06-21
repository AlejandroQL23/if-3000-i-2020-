package edu.ucr.rp.customer.GUI;

import static edu.ucr.rp.GUI.ConstantsElements.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class ShowPropertiesOfElements {

    Label labelShowContentOfFiles;
    TextField textFieldSearch = new TextField();
    GridPane gridPaneShowProperties = new GridPane();
    Button buttonSearch;
    ShowPropertiesOfElements.Client osoArio;

    /**
     *
     * @return Nos da la GUI que nos permite mostrar la información contenida en
     * el .propidades
     */
    public BorderPane showInformationByCatalog() {

        BorderPane borderPaneShowInformationByCatalogs = new BorderPane();
        borderPaneShowInformationByCatalogs.setPrefSize(BORDER_WIDTH, BORDER_HIGH);
        gridPaneShowProperties.setMinSize(GRID_WIDTH, GRID_HIGH);
        // determina el espacio entre columnas (vertical y horizontal)
        gridPaneShowProperties.setVgap(8);   //espacio
        gridPaneShowProperties.setHgap(8);    // espacio
        // alinear el grip
        gridPaneShowProperties.setAlignment(Pos.CENTER);
        gridPaneShowProperties.setStyle(("-fx-background-image:url('file:src/image/3.jpg');"
                + "-fx-background-repeat : no-repeat;"
                + "-fx-background-size: 920 920, 20 20, 20 20, 20 20, auto;"));

        textFieldSearch.setPromptText("Nombre del Catalogo");
        textFieldSearch.setStyle(
                "-fx-background-color: lightblue; "
                + "-fx-background-insets: 4; "
                +// tamano
                "-fx-background-radius: 4; "
                +// tamano
                "-fx-effect: dropshadow(three-pass-box, blue, 20, 0, 0, 0);");
        textFieldSearch.setFocusTraversable(false);
        gridPaneShowProperties.add(textFieldSearch, 0, 3); /// columna fila
        textFieldSearch.setOnKeyPressed((event) -> {
            buttonSearch.setDisable(false);
            gridPaneShowProperties.getChildren().removeAll(labelShowContentOfFiles);
        });

        buttonSearch = new Button("Buscar");
        buttonSearch.setTextFill(Color.WHITE);//Color de la letra del boton
        buttonSearch.setStyle("-fx-background-color: BLACK");//Color del fondo
        buttonSearch.setFont(Font.font("Castellar", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 10));//Tipo de letra
        gridPaneShowProperties.add(buttonSearch, 0, 4);
        buttonSearch.setDisable(false);
        buttonSearch.setDisable(true);

        buttonSearch.setOnAction((event) -> {

            ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.submit(() -> {
                try {
                    osoArio = new ShowPropertiesOfElements.Client("192.168.1.7", 12345);

                    // labelShowContentOfFiles.setText("Hola--> " + osoArio.showContent.replace("|", " "));
                    //   System.out.println("esto es lo que debria poner en UI " + osoArio.showContent.replace("|", " "));
                    //  System.out.println("Hola soy cnor hilo y si me dispare");
                    labelShowContentOfFiles = new Label();
                    labelShowContentOfFiles.setText(osoArio.showContent.replace("|", " "));
                    labelShowContentOfFiles.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 20));
                    labelShowContentOfFiles.setTextFill(Color.WHITE);
                    labelShowContentOfFiles.setStyle("-fx-background-color: rgb(41, 75, 152);");

                } catch (InterruptedException ex) {
                    Logger.getLogger(ShowPropertiesOfElements.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ShowPropertiesOfElements.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            gridPaneShowProperties.add(labelShowContentOfFiles, 0, 5);

            //   labelShowContentOfFiles.setText("Hola yo si sirvo por estar fuera deel hilo");  //--> solo sirvo con todos mis
            // compas aqui
//            String getName = textFieldSearch.getText();
//labelShowContentOfFiles = new Label("TODO REPLACE LOGIC HERE".replace("|", " ")); //end try//end try//end try//end try//end try//end try//end try//end try//end try//end try//end try//end try//end try//end try//end try//end try
            //end catch//end catch
//
//            buttonSearch.setDisable(true);
        });//end funcionalidad del boton buscar

        Button buttonClose = new Button("Cerrar");
        buttonClose.setTextFill(Color.WHITE);//Color de la letra del boton
        buttonClose.setStyle("-fx-background-color: BLACK");//Color del fondo
        buttonClose.setFont(Font.font("Castellar", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 10));//Tipo de letra
        gridPaneShowProperties.add(buttonClose, 0, 7);
        buttonClose.setOnAction((event) -> {
            gridPaneShowProperties.getChildren().clear();
            gridPaneShowProperties.setBackground(Background.EMPTY);  //limpia color para que quede el color
        });//end btn cerrar
        borderPaneShowInformationByCatalogs.setTop(gridPaneShowProperties);
        return borderPaneShowInformationByCatalogs;
    }//end showInformationByCatalog

    public class Client {

        Socket clientSocket;
        //  Socket clientSocketx;
        ArrayList<String> catalogos = new ArrayList<String>();
        String showContent;

        public Client(String server, int port) throws InterruptedException, ClassNotFoundException {
            // ArrayList<String> catalogos = new ArrayList<String>();
            try {
                System.out.println("Entro a try pero mjm");
                clientSocket = new Socket(server, port);//
                System.out.println("Entro a try pero mjm x2");
                //    Thread.sleep(1000);   //--> no es por el sleep pero quedo comentado igual
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());

                out.writeObject("$" + textFieldSearch.getText());//--> si meto espacios se cae :c
                //          + stringProperties + textFieldPropertiesQuantity.getText()) ;//+ Thread.currentThread().getName()
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                //ObjectInputStream ines = new ObjectInputStream(clientSocket.getInputStream());

                System.out.println("Entro a try pero mjm x3");
                //    System.out.println(in.readObject());
                System.out.println("Soy la ultima linea");
                // catalogos = (ArrayList<String>) in.readObject();  //---> Porblemas 

                showContent = in.readObject() + "";
                // System.out.println(showContent = in.readObject() + "");  //---> Aqui se trae un null--> era por el +=
                //Nota no concatenar

                System.out.println("mentira juju yo si lo soy Soy la ultima linea");
                // / ///     catalogos.add("juju");
                //  /     catalogos.add("juja");
                //      catalogos.add("jujo");
                //  catalogos = (ArrayList<String>) ines.readObject();
                //   System.out.println(catalogos + " catalogoooooooos");
                //   System.out.println((ArrayList<String>) ines.readObject() + " read ");
                // stringProperties="";
//                out.writeObject(textFieldCatalogName.getText() + "<>\n" + textFieldPropertiesQuantity.getText() + "<>\n"
//                        + Properties + "<>\n" + Thread.currentThread().getName()); // ---- >  Manda
//
//                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream()); //  < ---- trae
                // System.out.println(in.readObject()); //<--- esto imprime el mensaje que viene desde server
            }//end try 
            catch (IOException e) {
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

}//end class ShowCatalog
