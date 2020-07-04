package edu.ucr.rp.GUI;

import static edu.ucr.rp.GUI.ConstantsElements.*;
import java.util.*;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import java.io.*;
import java.net.Socket;
import java.util.concurrent.*;

public class CreateNewCatalog {

    ConstantsElements consElements = new ConstantsElements();
    ArrayList<TextField> textFieldProperties;
    ArrayList<String> Properties;
    TextField textFieldCatalogName;
    TextField textFieldPropertiesQuantity;
    Button buttonAcceptNumberOfProperties;
    Button buttonAdd;
    Button buttonClose;
    String stringProperties = "";
    int quantityOfProperties;

    /**
     *
     * @return Nos da la GUI que nos permite crear un nuevo catálogo
     */
    public GridPane createCatalog() {

        GridPane gridPaneNewCatalog = new GridPane();
        gridPaneNewCatalog.setMinSize(GRID_WIDTH, GRID_HIGH);
        gridPaneNewCatalog.setVgap(15);
        gridPaneNewCatalog.setHgap(15);
        gridPaneNewCatalog.setAlignment(Pos.CENTER);
        gridPaneNewCatalog.setStyle(("-fx-background-image:url('file:src/image/FCrear.jpg');"
                + "-fx-background-repeat : no-repeat;"
                + "-fx-background-size: 920 920, 20 20, 20 20, 20 20, auto;"));

        textFieldCatalogName = new TextField();
        textFieldCatalogName.setPromptText("Nombre del catalogo");
        textFieldCatalogName.setStyle(
                "-fx-background-color: lightblue; "
                + "-fx-background-insets: 4; "
                +// tamano
                "-fx-background-radius: 4; "
                +// tamano
                "-fx-effect: dropshadow(three-pass-box, blue, 20, 0, 0, 0);");
        gridPaneNewCatalog.add(textFieldCatalogName, 0, 1);
        textFieldCatalogName.setFocusTraversable(false);
        textFieldCatalogName.setOnKeyPressed((event) -> {
            textFieldPropertiesQuantity.setDisable(false);
        });

        textFieldPropertiesQuantity = new TextField();
        textFieldPropertiesQuantity.setPromptText("Numero de propiedades");
        textFieldPropertiesQuantity.setStyle(
                "-fx-background-color: lightblue; "
                + "-fx-background-insets: 4; "
                + "-fx-background-radius: 4; "
                + "-fx-effect: dropshadow(three-pass-box, blue, 20, 0, 0, 0);");
        gridPaneNewCatalog.add(textFieldPropertiesQuantity, 0, 2);
        textFieldPropertiesQuantity.setFocusTraversable(false);
        textFieldPropertiesQuantity.setDisable(true);

        textFieldPropertiesQuantity.setOnKeyPressed((event) -> {
            buttonAcceptNumberOfProperties.setDisable(false);
        });

        buttonAcceptNumberOfProperties = new Button("Definir cantidad");
        buttonAcceptNumberOfProperties.setTextFill(Color.WHITE);
        buttonAcceptNumberOfProperties.setStyle("-fx-background-color: BLACK");
        buttonAcceptNumberOfProperties.setFont(Font.font("Castellar", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 10));
        gridPaneNewCatalog.add(buttonAcceptNumberOfProperties, 0, 3);
        buttonAcceptNumberOfProperties.setDisable(true);
        buttonAcceptNumberOfProperties.setOnMouseEntered((event) -> {

            if (!textFieldCatalogName.getText().trim().equals("")) {

                if (!textFieldPropertiesQuantity.getText().trim().equals("")) {

                    char c = textFieldPropertiesQuantity.getText().charAt(0);
                    if (c >= 48 && c <= 57) {

                        if (Integer.parseInt(textFieldPropertiesQuantity.getText()) > 0 && Integer.parseInt(textFieldPropertiesQuantity.getText()) < 6) {

                            buttonAcceptNumberOfProperties.setOnAction((event1) -> {

                                buttonClose.setDisable(true);
                                quantityOfProperties = Integer.parseInt(textFieldPropertiesQuantity.getText());
                                textFieldProperties = new ArrayList<>();

                                for (int i = 0; i < quantityOfProperties; i++) {
                                    TextField textFieldNew = new TextField();
                                    textFieldNew.setStyle(
                                            "-fx-background-color: lightblue; "
                                            + "-fx-background-insets: 4; "
                                            + "-fx-background-radius: 4; "
                                            + "-fx-effect: dropshadow(three-pass-box, blue, 20, 0, 0, 0);");
                                    textFieldProperties.add(i, textFieldNew);
                                    gridPaneNewCatalog.add(textFieldProperties.get(i), 0, i + 4);
                                }// end for
                                textFieldProperties.get(textFieldProperties.size() - 1).setOnKeyPressed((t) -> {
                                    buttonAdd.setDisable(false);
                                });
                                textFieldPropertiesQuantity.setDisable(true);
                                textFieldCatalogName.setDisable(true);
                                buttonAcceptNumberOfProperties.setDisable(true);
                            });//end setOnAction

                        } else {
                            consElements.soundPlayer("noti");
                            consElements.alertWarning("Cantidad de propiedades invalida, intentelo de nuevo");
                            textFieldPropertiesQuantity.clear();

                        }

                    } else {
                        consElements.soundPlayer("noti");
                        consElements.alertWarning("La cantidad de propiedades deben ser números, intentelo de nuevo");
                        textFieldPropertiesQuantity.clear();

                    }
                } else {
                    consElements.soundPlayer("noti");
                    consElements.alertWarning("La cantidad de propiedades está vacía, intentelo de nuevo");
                    textFieldPropertiesQuantity.clear();
                }
            } else {
                consElements.soundPlayer("noti");
                consElements.alertWarning("El nombre del catalogo está vacío, intentelo de nuevo");
                textFieldCatalogName.clear();
            }

        });

        buttonAdd = new Button("Insertar");
        buttonAdd.setTextFill(Color.WHITE);
        buttonAdd.setStyle("-fx-background-color: BLACK");
        buttonAdd.setFont(Font.font("Castellar", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 10));
        gridPaneNewCatalog.add(buttonAdd, 2, 7);
        buttonAdd.setDisable(true);
        buttonAdd.setOnAction((event) -> {
            int counter = 0;

            for (int i = 0; i < quantityOfProperties; i++) {

                if (textFieldProperties.get(i).getText().trim().equals("")) {
                    counter++;
                }
            }

            if (counter == 0) {
                Properties = new ArrayList<>();
                for (int i = 0; i < quantityOfProperties; i++) {
                    stringProperties += textFieldProperties.get(i).getText() + "|";
                    Properties.add(textFieldProperties.get(i).getText());
                }

                ExecutorService executorService = Executors.newCachedThreadPool();
                executorService.submit(() -> {
                    Client user = new Client("127.0.0.1", 12345);
                });

                for (int i = 0; i < quantityOfProperties; i++) {
                    textFieldProperties.get(i).setVisible(false);
                }//end for

                consElements.soundPlayer("noti");
                consElements.alertInformation("Elemento ingresado y guardado correctamente");

                textFieldCatalogName.clear();
                textFieldPropertiesQuantity.clear();
                textFieldPropertiesQuantity.setDisable(true);
                textFieldCatalogName.setDisable(false);
                buttonAcceptNumberOfProperties.setDisable(true);
                buttonAdd.setDisable(true);
                buttonClose.setDisable(false);
            } else {

                consElements.soundPlayer("noti");
                consElements.alertWarning("Aún hay espacios vacíos, debe tener todos los espacios llenos");
                for (int i = 0; i < quantityOfProperties; i++) {
                    textFieldProperties.get(i).setText(textFieldProperties.get(i).getText().trim());
                }
            }

        });//END BUTTON

        buttonClose = new Button("Cerrar");
        buttonClose.setTextFill(Color.WHITE);
        buttonClose.setStyle("-fx-background-color: BLACK");
        buttonClose.setFont(Font.font("Castellar", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 10));
        gridPaneNewCatalog.add(buttonClose, 2, 8);
        buttonClose.setOnAction((event) -> {

            gridPaneNewCatalog.getChildren().clear();
            gridPaneNewCatalog.setBackground(Background.EMPTY);

        });//end btn cerrar

        return gridPaneNewCatalog;

    }//end GridPane createCatalog()

    //-------------------------------------------------------------------------------------------------------------------------------------
    /**
     * Clase que controla el envio de datos al servidor
     */
    public class Client {

        Socket clientSocket;

        public Client(String server, int port) {
            try {

                clientSocket = new Socket(server, port);
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                out.writeObject("createCatalog" + textFieldCatalogName.getText() + "|"
                        + stringProperties + textFieldPropertiesQuantity.getText());
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                stringProperties = "";
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

}// end class CreateNewCatalog

