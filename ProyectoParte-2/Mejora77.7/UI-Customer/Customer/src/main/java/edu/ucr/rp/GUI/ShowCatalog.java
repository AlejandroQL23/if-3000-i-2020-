package edu.ucr.rp.GUI;

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
                try {
                    ShowCatalog.Client user = new ShowCatalog.Client("127.0.0.1", 12345);

                    for (int i = 0; i < user.catalogos.size() - 1; i++) {
                        if (!comboBoxTool.getItems().contains(user.catalogos.get(i))) {
                            comboBoxTool.getItems().addAll(user.catalogos.get(i));
                        }//end if
                    }//end for
                } catch (InterruptedException ex) {
                    Logger.getLogger(DefineProperties.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        });

        gridPaneShowCatalog.add(comboBoxTool, 0, 0);

        Button buttonClose = new Button("Cerrar");
        buttonClose.setTextFill(Color.WHITE);
        buttonClose.setStyle("-fx-background-color: BLACK");
        buttonClose.setFont(Font.font("Castellar", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 10));
        gridPaneShowCatalog.add(buttonClose, 0, 9);
        buttonClose.setOnAction((event) -> {
            gridPaneShowCatalog.getChildren().clear();
            gridPaneShowCatalog.setBackground(Background.EMPTY);
        });

        borderPaneShowExistingCatalogs.setTop(gridPaneShowCatalog);

        return borderPaneShowExistingCatalogs;
    }//end showExistingCatalogs

    //----------------------------------------------------------------------------------------------------------------------------------
    /**
     * Clase que se encarga de la comunicacion con el servidor
     */
    public class Client {

        Socket clientSocket;
        ArrayList<String> catalogos = new ArrayList<String>();

        public Client(String server, int port) throws InterruptedException {
            try {
                clientSocket = new Socket(server, port);
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                out.writeObject("showCatalog's");
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                catalogos = (ArrayList<String>) in.readObject();
            }//end try 
            catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }//end finally
        }//end client (server, port)
    }//end client

}//end ShowCatalog
