package edu.ucr.rp.GUI;

import static edu.ucr.rp.GUI.ConstantsElements.*;
import java.io.*;
import java.net.Socket;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.logging.*;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class DeleteCatalogs {

    Button buttonDelete;
    TextField textFieldToDelete;
    ConstantsElements constantsElements = new ConstantsElements();

    /**
     *
     * @return Nos da la GUI que nos permite eliminar el .catálogo y el
     * .propiedades indicado por el usuario
     */
    public GridPane deleteCatalogs() {

        GridPane gridPaneDelete = new GridPane();
        gridPaneDelete.setMinSize(GRID_WIDTH, GRID_HIGH);
        gridPaneDelete.setVgap(15);
        gridPaneDelete.setHgap(15);
        gridPaneDelete.setAlignment(Pos.CENTER);
        gridPaneDelete.setStyle(("-fx-background-image:url('file:src/image/elimi.png');"
                + "-fx-background-repeat : no-repeat;"
                + "-fx-background-size: 920 920, 20 20, 20 20, 20 20, auto;"));

        textFieldToDelete = new TextField();
        textFieldToDelete.setPromptText("Nombre del catalogo que desea eliminar");
        textFieldToDelete.setStyle(
                "-fx-background-color: lightblue; "
                + "-fx-background-insets: 4; "
                + "-fx-background-radius: 4; "
                + "-fx-effect: dropshadow(three-pass-box, blue, 20, 0, 0, 0);");
        textFieldToDelete.setFocusTraversable(false);
        textFieldToDelete.setOnKeyPressed((event) -> {
            buttonDelete.setDisable(false);
        });
        gridPaneDelete.add(textFieldToDelete, 0, 0);

        buttonDelete = new Button("Eliminar");
        buttonDelete.setTextFill(Color.WHITE);
        buttonDelete.setStyle("-fx-background-color: BLACK");
        buttonDelete.setFont(Font.font("Castellar", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 10));
        gridPaneDelete.add(buttonDelete, 0, 2);
        buttonDelete.setDisable(true);
        buttonDelete.setOnMouseClicked((event) -> {

            constantsElements.soundPlayer("aviso");
            constantsElements.alertConfirmation("Esta seguro derealizar esta accion?");
            Optional<ButtonType> result = constantsElements.alertConfirmation("").showAndWait();
            if (result.get() == ButtonType.OK) {

                ExecutorService executorService = Executors.newCachedThreadPool();
                executorService.submit(() -> {
                    try {
                        DeleteCatalogs.Client user = new DeleteCatalogs.Client("127.0.0.1", 12345);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ShowPropertiesOfElements.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ShowPropertiesOfElements.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });

                constantsElements.soundPlayer("exito");
            }//end if
            buttonDelete.setDisable(true);
        });

        Button buttonClose = new Button("Cerrar");
        buttonClose.setTextFill(Color.WHITE);
        buttonClose.setStyle("-fx-background-color: BLACK");
        buttonClose.setFont(Font.font("Castellar", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 10));
        gridPaneDelete.add(buttonClose, 0, 3);
        buttonClose.setOnAction((event) -> {
            textFieldToDelete.clear();
            gridPaneDelete.getChildren().clear();
            gridPaneDelete.setBackground(Background.EMPTY);  //limpia color para que quede el color
        });
        return gridPaneDelete;
    }//end deleteCatalogs() 

    //-------------------------------------------------------------------------------------------------------------------------------------
    /**
     * Clase encargada de la comunicacion con el server
     */
    public class Client {

        Socket clientSocket;

        public Client(String server, int port) throws InterruptedException, ClassNotFoundException {

            try {
                clientSocket = new Socket(server, port);
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                out.writeObject("delete" + textFieldToDelete.getText());
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
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

}//end DeleteCatalogs
