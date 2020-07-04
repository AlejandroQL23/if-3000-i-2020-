package edu.ucr.rp.GUI;

import static edu.ucr.rp.GUI.ConstantsElements.*;
import java.io.*;
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

public class DefineProperties {

    ArrayList<Label> labelProperties;
    ArrayList<TextField> textFieldProperties;
    ComboBox comboBoxTool = new ComboBox();
    Button buttonShowProperties;
    Button buttonSaveProperties;
    Button buttonClose;
    DefineProperties.Client user;
    int counter = 0;
    ConstantsElements constantsElements = new ConstantsElements();
    int counter1 = 0;

    /**
     *
     * @return Nos da la GUI que nos permite definir crear el archivo con las
     * propiedades y definir las mismas
     * @throws IOException
     */
    public GridPane defineProperties() throws IOException {

        GridPane gridPaneDefineProperties = new GridPane();
        gridPaneDefineProperties.setMinSize(GRID_WIDTH, GRID_HIGH);
        gridPaneDefineProperties.setVgap(15);
        gridPaneDefineProperties.setHgap(15);
        gridPaneDefineProperties.setAlignment(Pos.CENTER);
        gridPaneDefineProperties.setStyle(("-fx-background-image:url('file:src/image/control-inventario.jpg');"
                + "-fx-background-repeat : no-repeat;"
                + "-fx-background-size: 920 920, 20 20, 20 20, 20 20, auto;"));

        comboBoxTool.setValue("Herramientas");
        comboBoxTool.setDisable(false);
        comboBoxTool.setStyle("-fx-background-color: lightblue");
        gridPaneDefineProperties.add(comboBoxTool, 0, 0);
        comboBoxTool.setOnMouseClicked((event) -> {
            if (counter1 == 0) {
                counter1++;
                buttonClose.setDisable(true);
                ExecutorService executorService = Executors.newCachedThreadPool();
                executorService.submit(() -> {
                    try {
                        user = new DefineProperties.Client("127.0.0.1", 12345, 0);
                        for (int i = 0; i < user.catalogos.size() - 1; i++) {
                            if (!comboBoxTool.getItems().contains(user.catalogos.get(i))) {
                                comboBoxTool.getItems().addAll(user.catalogos.get(i));
                            }
                        }
                    } catch (InterruptedException ex) {
                        Logger.getLogger(DefineProperties.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(DefineProperties.class.getName()).log(Level.SEVERE, null, ex);
                    }

                });
            }
        });
        comboBoxTool.setOnAction((t) -> {
            comboBoxTool.setDisable(true);
            buttonShowProperties.setDisable(false);

        });

        buttonShowProperties = new Button("Mostrar");
        buttonShowProperties.setTextFill(Color.WHITE);
        buttonShowProperties.setStyle("-fx-background-color: BLACK");
        buttonShowProperties.setFont(Font.font("Castellar", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 10));
        gridPaneDefineProperties.add(buttonShowProperties, 1, 0);
        buttonShowProperties.setDisable(true);
        buttonShowProperties.setOnMouseEntered(event -> {

            ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.submit(() -> {
                try {
                    user = new DefineProperties.Client("127.0.0.1", 12345, 1);
                    user.comboGetText = comboBoxTool.getValue().toString();

                    labelProperties = new ArrayList<>();
                    textFieldProperties = new ArrayList<>();
                    for (int i = 0; i < user.linesAccount - 2; i++) {
                        Label labelNew = new Label();
                        labelNew.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 20));
                        labelNew.setTextFill(Color.WHITE);
                        labelNew.setStyle("-fx-background-color: rgb(41, 75, 152);");
                        labelNew.setText(user.prop.get(i));
                        labelProperties.add(i, labelNew);

                        TextField textFieldNew = new TextField();
                        textFieldNew.setStyle(
                                "-fx-background-color: lightblue; "
                                + "-fx-background-insets: 4; "
                                +// tamano
                                "-fx-background-radius: 4; "
                                +// tamano
                                "-fx-effect: dropshadow(three-pass-box, blue, 20, 0, 0, 0);");
                        textFieldProperties.add(i, textFieldNew);
                        counter++;

                    }
                    textFieldProperties.get(textFieldProperties.size() - 1).setOnKeyPressed((t) -> {
                        buttonSaveProperties.setDisable(false);
                    });

                    comboBoxTool.setDisable(true);

                } catch (InterruptedException | ClassNotFoundException ex) {
                    Logger.getLogger(DefineProperties.class.getName()).log(Level.SEVERE, null, ex);
                }

            });

        });//end setOnAction btn AcepBusque

        buttonShowProperties.setOnMouseClicked(event -> {

            for (int i = 0; i < user.linesAccount - 2; i++) {
                gridPaneDefineProperties.add(labelProperties.get(i), 3, i + 1);
                gridPaneDefineProperties.add(textFieldProperties.get(i), 4, i + 1);

            }
            buttonShowProperties.setDisable(true);

        });

        buttonSaveProperties = new Button("Guardar propiedades");
        buttonSaveProperties.setTextFill(Color.WHITE);
        buttonSaveProperties.setStyle("-fx-background-color: BLACK");
        buttonSaveProperties.setFont(Font.font("Castellar", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 10));
        gridPaneDefineProperties.add(buttonSaveProperties, 0, 7);
        buttonSaveProperties.setDisable(true);
        buttonSaveProperties.setOnAction((event) -> {

            int counter2 = 0;
            for (int i = 0; i < user.linesAccount - 2; i++) {
                if (textFieldProperties.get(i).getText().trim().equals("")) {
                    counter2++;
                }
            }
            if (counter2 == 0) {

                user.Properties = new String();
                for (int i = 0; i < counter; i++) {
                    user.Properties += labelProperties.get(i).getText() + "|" + textFieldProperties.get(i).getText() + "|";
                }
                counter = 0;
                ExecutorService executorService = Executors.newCachedThreadPool();
                executorService.submit(() -> {
                    try {
                        user = new DefineProperties.Client("127.0.0.1", 12345, 2);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(DefineProperties.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(DefineProperties.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });

                constantsElements.soundPlayer("noti");
                constantsElements.alertInformation("Propiedad(es) ingresada(s) y guardada(s) correctamente");
                gridPaneDefineProperties.getChildren().removeAll(textFieldProperties);
                gridPaneDefineProperties.getChildren().removeAll(labelProperties);
                comboBoxTool.setValue("Herramientas");
                buttonSaveProperties.setDisable(true);
                buttonShowProperties.setDisable(true);
                buttonClose.setDisable(false);
            } else {
                constantsElements.soundPlayer("noti");
                constantsElements.alertWarning("Aún hay espacios vacíos, debe tener todos los espacios llenos");
                for (int i = 0; i < user.linesAccount - 2; i++) {
                    textFieldProperties.get(i).setText(textFieldProperties.get(i).getText().trim());
                }

            }

        });//END BUTTON

        buttonClose = new Button("Cerrar");
        buttonClose.setTextFill(Color.WHITE);
        buttonClose.setStyle("-fx-background-color: BLACK");
        buttonClose.setFont(Font.font("Castellar", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 10));
        gridPaneDefineProperties.add(buttonClose, 0, 8);
        buttonClose.setOnAction((event) -> {
            counter1 = 0;
            gridPaneDefineProperties.getChildren().clear();
            gridPaneDefineProperties.setBackground(Background.EMPTY);

        });//end btn Cerrar

        return gridPaneDefineProperties;
    }//end GridPane defineProperties() 

    //----------------------------------------------------------------------------------------------------------------------------------------
    /**
     * Clase que s enecargara de la comuniacion esntre esta y el servidor
     */
    public class Client {

        Socket clientSocket;
        ArrayList<String> catalogos = new ArrayList<>();
        String comboGetText;
        int linesAccount;
        ArrayList<String> prop = new ArrayList<>();
        String Properties = new String();

        public Client(String server, int port, int conector) throws InterruptedException, ClassNotFoundException {

            if (conector == 0) {
                try {
                    clientSocket = new Socket(server, port);
                    ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                    out.writeObject("createProperties");
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
                }//end finally

            } else if (conector == 1) {
                try {
                    clientSocket = new Socket(server, port);
                    ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                    out.writeObject(comboBoxTool.getValue().toString());
                    ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                    linesAccount = (int) in.readObject();
                    ObjectInputStream in2 = new ObjectInputStream(clientSocket.getInputStream());
                    prop = (ArrayList<String>) in2.readObject();

                }//end try 
                catch (IOException e) {
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
                    clientSocket = new Socket(server, port);//
                    ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                    out.writeObject(comboBoxTool.getValue().toString() + "|" + user.Properties);
                }//end try 
                catch (IOException e) {
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
    }//end client

}//edn class 

