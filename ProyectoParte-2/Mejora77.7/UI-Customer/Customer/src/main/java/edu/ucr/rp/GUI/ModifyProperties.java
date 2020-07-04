package edu.ucr.rp.GUI;

import static edu.ucr.rp.GUI.ConstantsElements.*;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModifyProperties {

    GridPane gridPaneModify = new GridPane();
    ConstantsElements constantsElements = new ConstantsElements();
    List<Label> labelProperties;
    List<TextField> textFieldProperties;
    ComboBox comboBoxTool = new ComboBox();
    ComboBox comboBoxProp = new ComboBox();
    Button buttonModify;
    Button buttonCharge;
    Button buttonClose;
    int counter = 0;
    ModifyProperties.Client user;
    int counter1 = 0;
    int counter2 = 0;

    /**
     *
     * @return Nos da la GUI que nos permite realizar modificaciones en el
     * .propiedades
     */
    public BorderPane modifyProperties() {

        BorderPane borderPaneModifyProperties = new BorderPane();
        borderPaneModifyProperties.setPrefSize(BORDER_WIDTH, BORDER_HIGH);

        gridPaneModify.setMinSize(GRID_WIDTH, GRID_HIGH);
        gridPaneModify.setVgap(15);
        gridPaneModify.setHgap(15);
        gridPaneModify.setStyle(("-fx-background-image:url('file:src/image/6.jpg');"
                + "-fx-background-repeat : no-repeat;"
                + "-fx-background-size: 920 920, 20 20, 20 20, 20 20, auto;"));
        gridPaneModify.setAlignment(Pos.CENTER);

        comboBoxTool.setValue("Herramientas");
        comboBoxTool.setStyle("-fx-background-color: lightblue");
        gridPaneModify.add(comboBoxTool, 0, 0);
        comboBoxTool.setDisable(false);
        comboBoxTool.setOnMouseClicked((event) -> {
            if (counter1 == 0) {
                counter1++;
                buttonClose.setDisable(true);
                ExecutorService executorService = Executors.newCachedThreadPool();
                executorService.submit(() -> {
                    try {
                        user = new ModifyProperties.Client("127.0.0.1", 12345, 0);
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
            comboBoxProp.setDisable(false);

        });

        comboBoxProp.setValue("Propiedades");
        comboBoxProp.setStyle("-fx-background-color: lightblue");
        gridPaneModify.add(comboBoxProp, 0, 2);
        comboBoxProp.setDisable(true);
        comboBoxProp.setOnMouseEntered((event) -> {
            if (counter2 == 0) {
                counter2++;
                ExecutorService executorService = Executors.newCachedThreadPool();
                executorService.submit(() -> {
                    try {
                        user = new ModifyProperties.Client("127.0.0.1", 12345, 1);
                        for (int i = 0; i < user.propied.size(); i++) {
                            if (!comboBoxProp.getItems().contains(user.propied.get(i))) {
                                comboBoxProp.getItems().addAll(user.propied.get(i));
                            }
                        }
                    } catch (InterruptedException | ClassNotFoundException ex) {
                        Logger.getLogger(DefineProperties.class.getName()).log(Level.SEVERE, null, ex);
                    }

                });
            }

        });
        comboBoxProp.setOnAction((t) -> {
            comboBoxProp.setDisable(true);
            buttonCharge.setDisable(false);
        });

        buttonCharge = new Button("Cargar");
        buttonCharge.setTextFill(Color.WHITE);
        buttonCharge.setStyle("-fx-background-color: BLACK");
        buttonCharge.setFont(Font.font("Castellar", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 10));
        gridPaneModify.add(buttonCharge, 0, 4);
        buttonCharge.setDisable(true);
        buttonCharge.setOnMouseEntered(event -> {

            ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.submit(() -> {
                try {
                    user = new ModifyProperties.Client("127.0.0.1", 12345, 2);
                    user.comboGetText = comboBoxTool.getValue().toString();

                    labelProperties = new ArrayList<>();
                    textFieldProperties = new ArrayList<>();
                    for (int i = 0; i < user.linesAccount - 2; i++) {
                        Label labelNew = new Label();
                        labelNew.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 20));
                        labelNew.setTextFill(Color.WHITE);
                        labelNew.setStyle("-fx-background-color: rgb(41, 75, 152);");
                        labelNew.setText(user.properties.get(i));
                        labelProperties.add(i, labelNew);

                        TextField textFieldNew = new TextField();
                        textFieldNew.setStyle(
                                "-fx-background-color: lightblue; "
                                + "-fx-background-insets: 4; "
                                + "-fx-background-radius: 4; "
                                + "-fx-effect: dropshadow(three-pass-box, blue, 20, 0, 0, 0);");
                        textFieldNew.setText(user.atributes.get(i));
                        textFieldProperties.add(i, textFieldNew);

                        counter++;
                    }

                } catch (InterruptedException | ClassNotFoundException ex) {
                    Logger.getLogger(DefineProperties.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        });

        buttonCharge.setOnMouseClicked(event -> {
            for (int i = 0; i < user.linesAccount - 2; i++) {
                gridPaneModify.add(labelProperties.get(i), 3, i + 1);
                gridPaneModify.add(textFieldProperties.get(i), 4, i + 1);

            }
            buttonCharge.setDisable(true);
            buttonModify.setDisable(false);
        });

        buttonModify = new Button("Modificar");
        buttonModify.setTextFill(Color.WHITE);
        buttonModify.setStyle("-fx-background-color: BLACK");
        buttonModify.setFont(Font.font("Castellar", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 10));
        gridPaneModify.add(buttonModify, 0, 5);
        buttonModify.setDisable(true);
        buttonModify.setOnAction((event) -> {

            int counter3 = 0;
            for (int i = 0; i < user.linesAccount - 2; i++) {
                if (textFieldProperties.get(i).getText().trim().equals("")) {
                    counter3++;
                }
            }
            if (counter3 == 0) {
                user.Properties = new String();
                for (int i = 0; i < counter; i++) {
                    user.Properties += labelProperties.get(i).getText() + "|" + textFieldProperties.get(i).getText() + "|";
                }
                counter = 0;
                ExecutorService executorService = Executors.newCachedThreadPool();
                executorService.submit(() -> {
                    try {
                        user = new ModifyProperties.Client("127.0.0.1", 12345, 3);
                    } catch (InterruptedException | ClassNotFoundException ex) {
                        Logger.getLogger(DefineProperties.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });

                constantsElements.alertConfirmation("Elemento modificado correctamente");
                gridPaneModify.getChildren().removeAll(textFieldProperties);
                gridPaneModify.getChildren().removeAll(labelProperties);
                buttonModify.setDisable(true);
                buttonClose.setDisable(false);

            } else {
                constantsElements.soundPlayer("noti");
                constantsElements.alertWarning("Aún hay espacios vacíos, debe tener todos los espacios llenos");
                for (int i = 0; i < user.linesAccount - 2; i++) {
                    textFieldProperties.get(i).setText(textFieldProperties.get(i).getText().trim());
                }

            }

        });

        buttonClose = new Button("Cerrar");
        buttonClose.setTextFill(Color.WHITE);
        buttonClose.setStyle("-fx-background-color: BLACK");
        buttonClose.setFont(Font.font("Castellar", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 10));
        gridPaneModify.add(buttonClose, 1, 5);
        buttonClose.setOnAction((event) -> {
            comboBoxProp.getItems().clear();
            counter1 = 0;
            counter2 = 0;
            gridPaneModify.getChildren().clear();
            gridPaneModify.setBackground(Background.EMPTY);
        });

        borderPaneModifyProperties.setTop(gridPaneModify);

        return borderPaneModifyProperties;
    }

    //-----------------------------------------------------------------------------------------------------------------------------
    /**
     * Clase encargada de gestionar las interacciones con el servidor
     */
    public class Client {

        Socket clientSocket;
        ArrayList<String> catalogos = new ArrayList<>();
        ArrayList<String> propied = new ArrayList<>();
        String comboGetText;
        int linesAccount;
        ArrayList<String> properties = new ArrayList<>();
        ArrayList<String> atributes = new ArrayList<>();
        String Properties = new String();

        public Client(String server, int port, int conector) throws InterruptedException, ClassNotFoundException {

            if (conector == 0) {
                try {
                    clientSocket = new Socket(server, port);
                    ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                    out.writeObject("modify");
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
                    propied = (ArrayList<String>) in.readObject();
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
                    out.writeObject(comboBoxTool.getValue().toString() + "|" + comboBoxProp.getValue().toString());
                    ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                    linesAccount = (int) in.readObject();
                    ObjectInputStream in2 = new ObjectInputStream(clientSocket.getInputStream());
                    properties = (ArrayList<String>) in2.readObject();
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
            } else if (conector == 3) {
                try {
                    clientSocket = new Socket(server, port);
                    ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                    out.writeObject(comboBoxTool.getValue().toString() + "|" + user.Properties);
                    ObjectOutputStream out2 = new ObjectOutputStream(clientSocket.getOutputStream());
                    out2.writeObject(comboBoxProp.getValue().toString());
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
