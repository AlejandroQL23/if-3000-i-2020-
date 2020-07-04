package edu.ucr.rp.GUI;

import static edu.ucr.rp.GUI.ConstantsElements.*;
import java.io.*;
import java.net.Socket;
import java.util.concurrent.*;
import java.util.logging.*;
import javafx.event.ActionEvent;
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
    ConstantsElements consElements = new ConstantsElements();
    ShowPropertiesOfElements.Client user;

    /**
     *
     * @return Nos da la GUI que nos permite mostrar la información contenida en
     * el .propidades
     */
    public BorderPane showInformationByCatalog() {

        BorderPane borderPaneShowInformationByCatalogs = new BorderPane();
        borderPaneShowInformationByCatalogs.setPrefSize(BORDER_WIDTH, BORDER_HIGH);
        gridPaneShowProperties.setMinSize(GRID_WIDTH, GRID_HIGH);
        gridPaneShowProperties.setVgap(8);
        gridPaneShowProperties.setHgap(8);
        gridPaneShowProperties.setAlignment(Pos.CENTER);
        gridPaneShowProperties.setStyle(("-fx-background-image:url('file:src/image/3.jpg');"
                + "-fx-background-repeat : no-repeat;"
                + "-fx-background-size: 920 920, 20 20, 20 20, 20 20, auto;"));

        textFieldSearch.setPromptText("Nombre del Catalogo");
        textFieldSearch.setStyle(
                "-fx-background-color: lightblue; "
                + "-fx-background-insets: 4; "
                + "-fx-background-radius: 4; "
                + "-fx-effect: dropshadow(three-pass-box, blue, 20, 0, 0, 0);");
        textFieldSearch.setFocusTraversable(false);
        gridPaneShowProperties.add(textFieldSearch, 0, 3);
        textFieldSearch.setOnKeyPressed((event) -> {
            buttonSearch.setDisable(false);
            gridPaneShowProperties.getChildren().removeAll(labelShowContentOfFiles);
        });

        buttonSearch = new Button("Buscar");
        buttonSearch.setTextFill(Color.WHITE);
        buttonSearch.setStyle("-fx-background-color: BLACK");
        buttonSearch.setFont(Font.font("Castellar", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 10));
        gridPaneShowProperties.add(buttonSearch, 0, 4);
        buttonSearch.setDisable(false);
        buttonSearch.setDisable(true);
        buttonSearch.setOnMouseEntered((e) -> {

            if (!textFieldSearch.getText().trim().equals("")) {

                buttonSearch.setOnAction((ActionEvent event) -> {

                    labelShowContentOfFiles = new Label();
                    labelShowContentOfFiles.setText("");

                    ExecutorService executorService = Executors.newCachedThreadPool();
                    executorService.submit(() -> {
                        try {
                            user = new ShowPropertiesOfElements.Client("127.0.0.1", 12345);
                            labelShowContentOfFiles.setText(user.showContent.replace("|", " "));
                            labelShowContentOfFiles.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 20));
                            labelShowContentOfFiles.setTextFill(Color.WHITE);
                            labelShowContentOfFiles.setStyle("-fx-background-color: rgb(41, 75, 152);");

                        } catch (InterruptedException | ClassNotFoundException ex) {
                            Logger.getLogger(ShowPropertiesOfElements.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (java.lang.IllegalStateException gg) {

                        }
                    });

                    gridPaneShowProperties.add(labelShowContentOfFiles, 0, 5);
                    buttonSearch.setDisable(true);
                });//end funcionalidad del boton buscar
            } else {
                consElements.soundPlayer("noti");
                consElements.alertWarning("El nombre del catalogo está vacío, intentelo de nuevo");
                textFieldSearch.clear();
            }
        });

        Button buttonClose = new Button("Cerrar");
        buttonClose.setTextFill(Color.WHITE);
        buttonClose.setStyle("-fx-background-color: BLACK");
        buttonClose.setFont(Font.font("Castellar", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 10));
        gridPaneShowProperties.add(buttonClose, 0, 7);
        buttonClose.setOnAction((event) -> {
            //textFieldSearch.clear();
            gridPaneShowProperties.getChildren().clear();
            gridPaneShowProperties.setBackground(Background.EMPTY);
        });//end btn cerrar
        borderPaneShowInformationByCatalogs.setTop(gridPaneShowProperties);
        return borderPaneShowInformationByCatalogs;
    }//end showInformationByCatalog

    //--------------------------------------------------------------------------------------------------------------------------------
    /**
     * Clase encargada de la comunicacion con el server
     */
    public class Client {

        Socket clientSocket;
        String showContent;

        public Client(String server, int port) throws InterruptedException, ClassNotFoundException {
            try {
                clientSocket = new Socket(server, port);
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                out.writeObject("showProperties" + textFieldSearch.getText());
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                showContent = in.readObject() + "";
            }//end try 
            catch (IOException e) {
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

}//end class ShowCatalog
