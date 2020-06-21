package edu.ucr.rp.customer.GUI;

//import edu.ucr.rp.customer.Logic.SaveObject;
//import edu.ucr.rp.customer.Logic.Mercancia;
import edu.ucr.rp.GUI.ConstantsElements;
import static edu.ucr.rp.GUI.ConstantsElements.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
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

    //---
    //--
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

                out.writeObject("%");
                //          + stringProperties + textFieldPropertiesQuantity.getText()) ;//+ Thread.currentThread().getName()
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                //  ObjectInputStream ines = new ObjectInputStream(clientSocket.getInputStream());

                System.out.println("Entro a try pero mjm x3");
                //    System.out.println(in.readObject());
                System.out.println("Soy la ultima linea");
                catalogos = (ArrayList<String>) in.readObject();  //---> Porblemas 

                // / ///     catalogos.add("juju");
                //  /     catalogos.add("juja");
                //      catalogos.add("jujo");
                //  catalogos = (ArrayList<String>) ines.readObject();
                System.out.println(catalogos + " catalogoooooooos");
                //      System.out.println((ArrayList<String>) ines.readObject() + " read ");
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

    //  SaveObject saveObject = new SaveObject();
    ConstantsElements constantsElements = new ConstantsElements();
    // Mercancia object;

    ArrayList<Label> labelProperties;
    ArrayList<TextField> textFieldProperties;
    ArrayList<String> Properties = new ArrayList<>();
    ArrayList<String> Atributes = new ArrayList<>();
    ComboBox comboBoxTool = new ComboBox();
    Button buttonAcceptNumberOfProperties;
    Button buttonSaveProperties;

    /**
     *
     * @return Nos da la GUI que nos permite definir crear el archivo con las
     * propiedades y definir las mismas
     * @throws IOException
     */
    public GridPane defineProperties() throws IOException {

        GridPane gridPaneDefineProperties = new GridPane();
        gridPaneDefineProperties.setMinSize(GRID_WIDTH, GRID_HIGH);
        // determina el espacio entre columnas (vertical y horizontal)
        gridPaneDefineProperties.setVgap(15);   //espacio
        gridPaneDefineProperties.setHgap(15);    // espacio
        // alinear el grip
        gridPaneDefineProperties.setAlignment(Pos.CENTER);
        gridPaneDefineProperties.setStyle(("-fx-background-image:url('file:src/image/control-inventario.jpg');"
                + "-fx-background-repeat : no-repeat;"
                + "-fx-background-size: 920 920, 20 20, 20 20, 20 20, auto;"));

        comboBoxTool.setValue("Herramientas");
        comboBoxTool.setStyle("-fx-background-color: lightblue");
        gridPaneDefineProperties.add(comboBoxTool, 0, 0);
        //  graphicalUserInterfaceLogic.createComboBox(comboBoxTool);

        comboBoxTool.setOnMouseClicked((event) -> {

            ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.submit(() -> {
                System.out.println("Al princi");
                try {
                    Client osoArio = new Client("192.168.1.7", 12345);
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

            buttonAcceptNumberOfProperties.setDisable(false);
        });

//        comboBoxTool.setOnMouseEntered((event) -> {
//
//            ExecutorService executorService = Executors.newCachedThreadPool();
//            executorService.submit(() -> {
//                System.out.println("Al princi");
//                Client osoArio = new Client("192.168.1.7", 12345);
//                System.out.println(catalogos.size()+" Soy tama");
//                for (int i = 0; i < catalogos.size() - 1; i++) {
//                    comboBoxTool.getItems().addAll(catalogos.get(i));
//                }
//                System.out.println("Llegue al final");
//
//            });
//        });
        buttonAcceptNumberOfProperties = new Button("Aceptar");
        buttonAcceptNumberOfProperties.setTextFill(Color.WHITE);
        buttonAcceptNumberOfProperties.setStyle("-fx-background-color: BLACK");
        buttonAcceptNumberOfProperties.setFont(Font.font("Castellar", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 10));
        gridPaneDefineProperties.add(buttonAcceptNumberOfProperties, 0, 3);
        // buttonAcceptNumberOfProperties.setDisable(true);
        buttonAcceptNumberOfProperties.setOnAction((event) -> {

//---
////            try {
////                Client osoArio2 = new Client("192.168.1.7", 123);
////                 System.out.println(osoArio2.clientSocket.getOutputStream() + " Soy tama");
////            } catch (InterruptedException ex) {
////                Logger.getLogger(DefineProperties.class.getName()).log(Level.SEVERE, null, ex);
////            } catch (IOException ex) {
////                Logger.getLogger(DefineProperties.class.getName()).log(Level.SEVERE, null, ex);
////            }
            //---     
////            ExecutorService executorService = Executors.newCachedThreadPool();
////            executorService.submit(() -> {
////                System.out.println("Al princi");
////                Client osoArio = new Client("192.168.1.7", 123);
////                try {
////                    System.out.println(osoArio.clientSocket.getOutputStream() + "Holaaaaaaaaaaa");
////                } catch (IOException ex) {
////                    Logger.getLogger(DefineProperties.class.getName()).log(Level.SEVERE, null, ex);
////                }
////                for (int i = 0; i < catalogos.size() - 1; i++) {
////                    comboBoxTool.getItems().addAll(catalogos.get(i));
////                }
////                System.out.println("Llegue al final");
////
////            });
////            labelProperties = new ArrayList<Label>();
////            textFieldProperties = new ArrayList<TextField>();
////            for (int i = 0; i < saveObject.linesAccount(comboBoxTool.getValue().toString()) - 2; i++) {
////                Label labelNew = new Label();
////                labelNew.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 20));
////                labelNew.setTextFill(Color.WHITE);
////                labelNew.setStyle("-fx-background-color: rgb(41, 75, 152);");
////                labelNew.setText(saveObject.readProperties(comboBoxTool.getValue().toString(), saveObject.linesAccount(comboBoxTool.getValue().toString())).get(i));
////                labelProperties.add(i, labelNew);
////                gridPaneDefineProperties.add(labelProperties.get(i), 3, i + 1);
////                TextField textFieldNew = new TextField();
////                textFieldNew.setStyle(
////                        "-fx-background-color: lightblue; "
////                        + "-fx-background-insets: 4; "
////                        +// tamano
////                        "-fx-background-radius: 4; "
////                        +// tamano
////                        "-fx-effect: dropshadow(three-pass-box, blue, 20, 0, 0, 0);");
////                textFieldProperties.add(i, textFieldNew);
////
////                gridPaneDefineProperties.add(textFieldProperties.get(i), 4, i + 1);
////            }
////
////            buttonSaveProperties.setDisable(false);
////            buttonAcceptNumberOfProperties.setDisable(true);
////            comboBoxTool.setDisable(true);
        });//end setOnAction btn AcepBusque

        buttonSaveProperties = new Button("Guardar propiedades");
        buttonSaveProperties.setTextFill(Color.WHITE);
        buttonSaveProperties.setStyle("-fx-background-color: BLACK");
        buttonSaveProperties.setFont(Font.font("Castellar", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 10));
        gridPaneDefineProperties.add(buttonSaveProperties, 0, 7);
        buttonSaveProperties.setDisable(true);
        buttonSaveProperties.setOnAction((event) -> {

////            Properties = new ArrayList<>();
////            Atributes = new ArrayList<>();
////            for (int i = 0; i < saveObject.linesAccount(comboBoxTool.getValue().toString()) - 2; i++) {
////                Properties.add(textFieldProperties.get(i).getText());
////                Atributes.add(labelProperties.get(i).getText());
////            }
////
////            object = new Mercancia(comboBoxTool.getValue().toString(), saveObject.linesAccount(comboBoxTool.getValue().toString()) - 2,
////                    Properties, Atributes);
////            saveObject.writeFileProperties(object);
////            constantsElements.soundPlayer("noti");
////            constantsElements.alertInformation("Propiedad(es) ingresada(s) y guardada(s) correctamente");
////            gridPaneDefineProperties.getChildren().removeAll(textFieldProperties);
////            gridPaneDefineProperties.getChildren().removeAll(labelProperties);
////            buttonSaveProperties.setDisable(true);
////            comboBoxTool.setDisable(false);
        });//END BUTTON

        Button buttonClose = new Button("Cerrar");
        buttonClose.setTextFill(Color.WHITE);
        buttonClose.setStyle("-fx-background-color: BLACK");
        buttonClose.setFont(Font.font("Castellar", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 10));
        gridPaneDefineProperties.add(buttonClose, 0, 8);
        buttonClose.setOnAction((event) -> {
            gridPaneDefineProperties.getChildren().clear();
            gridPaneDefineProperties.setBackground(Background.EMPTY);

        });//end btn Cerrar

        return gridPaneDefineProperties;
    }//end GridPane defineProperties() 

}//edn class DefineProperties
