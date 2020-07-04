package edu.ucr.rp.GUI;

import static edu.ucr.rp.GUI.ConstantsElements.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class CatalogSearch {

    ComboBox comboBoxTool = new ComboBox();
    ComboBox comboBoxProperties = new ComboBox();
    GridPane gridPaneSearch = new GridPane();
    List<Label> labelProperties;
    List<TextField> textFieldProperties;
    Button buttonSearch;
    Button buttonClose;
    CatalogSearch.Client user;
    int counter1 = 0;
    int counter2 = 0;

    /**
     *
     * @return Nos da la GUI que nos permite buscar en un catálogo
     */
    public BorderPane catalogSearching() {

        BorderPane borderPaneCatalogSearching = new BorderPane();
        borderPaneCatalogSearching.setPrefSize(BORDER_WIDTH, BORDER_HIGH);

        gridPaneSearch.setMinSize(GRID_WIDTH, GRID_HIGH);
        gridPaneSearch.setVgap(8);
        gridPaneSearch.setHgap(8);
        gridPaneSearch.setAlignment(Pos.CENTER);
        gridPaneSearch.setStyle(("-fx-background-image:url('file:src/image/Bus.jpg');"
                + "-fx-background-repeat : no-repeat;"
                + "-fx-background-size: 920 920, 20 20, 20 20, 20 20, auto;"));

        comboBoxTool.setValue("Herramientas");
        comboBoxTool.setStyle("-fx-background-color: lightblue");
        gridPaneSearch.add(comboBoxTool, 0, 0);
        comboBoxTool.setDisable(false);
        comboBoxTool.setOnMouseClicked((event) -> {
            if (counter1 == 0) {
                counter1++;
                buttonClose.setDisable(true);
                ExecutorService executorService = Executors.newCachedThreadPool();
                executorService.submit(() -> {
                    try {
                        user = new CatalogSearch.Client("127.0.0.1", 12345, 0);
                        for (int i = 0; i < user.catalogos.size() - 1; i++) {
                            if (!comboBoxTool.getItems().contains(user.catalogos.get(i))) {
                                comboBoxTool.getItems().addAll(user.catalogos.get(i));
                            }
                        }
                    } catch (InterruptedException | ClassNotFoundException ex) {
                        Logger.getLogger(DefineProperties.class.getName()).log(Level.SEVERE, null, ex);
                    }

                });
            }
        });
        comboBoxTool.setOnAction((t) -> {
            comboBoxTool.setDisable(true);
            comboBoxProperties.setDisable(false);

        });

        comboBoxProperties.setValue("Propiedades");
        comboBoxProperties.setStyle("-fx-background-color: lightblue");
        gridPaneSearch.add(comboBoxProperties, 0, 2);
        comboBoxProperties.setDisable(true);
        comboBoxProperties.setOnMouseEntered((event) -> {
            if (counter2 == 0) {
                counter2++;
                ExecutorService executorService = Executors.newCachedThreadPool();
                executorService.submit(() -> {
                    try {
                        user = new CatalogSearch.Client("127.0.0.1", 12345, 1);
                        for (int i = 0; i < user.propiedades.size(); i++) {
                            if (!comboBoxProperties.getItems().contains(user.propiedades.get(i))) {
                                comboBoxProperties.getItems().addAll(user.propiedades.get(i));
                            }
                        }
                    } catch (InterruptedException | ClassNotFoundException ex) {
                        Logger.getLogger(DefineProperties.class.getName()).log(Level.SEVERE, null, ex);
                    }

                });
            }

        });
        comboBoxProperties.setOnAction((t) -> {
            comboBoxProperties.setDisable(true);
            buttonSearch.setDisable(false);
        });

        buttonSearch = new Button("Buscar");
        buttonSearch.setTextFill(Color.WHITE);
        buttonSearch.setStyle("-fx-background-color: BLACK");
        buttonSearch.setFont(Font.font("Castellar", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 10));
        gridPaneSearch.add(buttonSearch, 0, 6);
        buttonSearch.setDisable(true);
        buttonSearch.setOnMouseEntered(event -> {

            ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.submit(() -> {
                try {
                    user = new CatalogSearch.Client("127.0.0.1", 12345, 2);
                    user.comboGettext = comboBoxTool.getValue().toString();

                    labelProperties = new ArrayList<>();
                    textFieldProperties = new ArrayList<>();
                    for (int i = 0; i < user.linesAccount - 2; i++) {
                        Label labelNew = new Label();
                        labelNew.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 20));
                        labelNew.setTextFill(Color.WHITE);
                        labelNew.setStyle("-fx-background-color: rgb(41, 75, 152);");
                        labelNew.setText(user.propierties.get(i));
                        labelProperties.add(i, labelNew);

                        TextField textFieldNew = new TextField();
                        textFieldNew.setEditable(false);
                        textFieldNew.setStyle(
                                "-fx-background-color: lightblue; "
                                + "-fx-background-insets: 4; "
                                + "-fx-background-radius: 4; "
                                + "-fx-effect: dropshadow(three-pass-box, blue, 20, 0, 0, 0);");
                        textFieldNew.setText(user.atributes.get(i));
                        textFieldProperties.add(i, textFieldNew);

                    }

                } catch (InterruptedException | ClassNotFoundException ex) {
                    Logger.getLogger(DefineProperties.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        });

        buttonSearch.setOnMouseClicked(event -> {
            for (int i = 0; i < user.linesAccount - 2; i++) {
                gridPaneSearch.add(labelProperties.get(i), 3, i + 1);
                gridPaneSearch.add(textFieldProperties.get(i), 4, i + 1);

            }
            buttonSearch.setDisable(true);
            buttonClose.setDisable(false);
        });

        buttonClose = new Button("Cerrar");
        buttonClose.setTextFill(Color.WHITE);
        buttonClose.setStyle("-fx-background-color: BLACK");
        buttonClose.setFont(Font.font("Castellar", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 10));
        gridPaneSearch.add(buttonClose, 0, 7);
        buttonClose.setOnAction((event) -> {
            comboBoxProperties.getItems().clear();
            counter1 = 0;
            counter2 = 0;
            gridPaneSearch.getChildren().clear();
            gridPaneSearch.setBackground(Background.EMPTY);
        });
        borderPaneCatalogSearching.setTop(gridPaneSearch);
        return borderPaneCatalogSearching;
    }

    //----------------------------------------------------------------------------------------------------------------------------------
    /**
     * Clase que se encarga de la gestion estre la clase y el servidor
     */
    public class Client {

        Socket clientSocket;
        ArrayList<String> catalogos = new ArrayList<>();
        ArrayList<String> propiedades = new ArrayList<>();
        String comboGettext;
        int linesAccount;
        ArrayList<String> propierties = new ArrayList<>();
        ArrayList<String> atributes = new ArrayList<>();

        public Client(String server, int port, int conector) throws InterruptedException, ClassNotFoundException {

            if (conector == 0) {
                try {
                    clientSocket = new Socket(server, port);
                    ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                    out.writeObject("search");
                    ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                    catalogos = (ArrayList<String>) in.readObject();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        clientSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            } else if (conector == 1) {
                try {
                    clientSocket = new Socket(server, port);
                    ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                    out.writeObject(comboBoxTool.getValue().toString());
                    ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                    propiedades = (ArrayList<String>) in.readObject();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        clientSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            } else if (conector == 2) {
                try {
                    clientSocket = new Socket(server, port);
                    ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                    out.writeObject(comboBoxTool.getValue().toString() + "|" + comboBoxProperties.getValue().toString());
                    ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                    linesAccount = (int) in.readObject();
                    ObjectInputStream in2 = new ObjectInputStream(clientSocket.getInputStream());
                    propierties = (ArrayList<String>) in2.readObject();
                    ObjectInputStream in3 = new ObjectInputStream(clientSocket.getInputStream());
                    atributes = (ArrayList<String>) in3.readObject();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        clientSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }//end cliente
}
