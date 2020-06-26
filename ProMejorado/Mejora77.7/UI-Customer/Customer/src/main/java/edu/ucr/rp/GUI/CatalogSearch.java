package edu.ucr.rp.customer.GUI;

//import edu.ucr.rp.customer.Logic.Search;
import static edu.ucr.rp.GUI.ConstantsElements.*;
import java.io.*;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.*;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class CatalogSearch {

    //  Search search = new Search();
    TextField textFieldProperties = new TextField();
    ComboBox comboBoxTool = new ComboBox();
    Label labelName = new Label();
    GridPane gridPaneSearch = new GridPane();
    List<Label> labelProperties;
    Button buttonSearch;
    String property = "";
    int position = 0;

    public class Client {

        Socket clientSocket;
        //  Socket clientSocketx;
        //  ArrayList<String> catalogos = new ArrayList<String>();

        public Client(String server, int port) throws InterruptedException {
            // ArrayList<String> catalogos = new ArrayList<String>();
            try {
                System.out.println("Entro a try pero mjm");
                clientSocket = new Socket(server, port);//
                System.out.println("Entro a try pero mjm x2");
                Thread.sleep(1000);
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());

                out.writeObject("@");
                //          + stringProperties + textFieldPropertiesQuantity.getText()) ;//+ Thread.currentThread().getName()
                //   ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                //  ObjectInputStream ines = new ObjectInputStream(clientSocket.getInputStream());

                System.out.println("Entro a try pero mjm x3");
                //    System.out.println(in.readObject());
                System.out.println("Soy la ultima linea");
                //    catalogos = (ArrayList<String>) in.readObject();  //---> Porblemas 

                // / ///     catalogos.add("juju");
                //  /     catalogos.add("juja");
                //      catalogos.add("jujo");
                //  catalogos = (ArrayList<String>) ines.readObject();
                ///    System.out.println(catalogos + " catalogoooooooos");
                //      System.out.println((ArrayList<String>) ines.readObject() + " read ");
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

    /**
     *
     * @return Nos da la GUI que nos permite buscar en un catálogo
     */
    public BorderPane catalogSearching() {

        BorderPane borderPaneCatalogSearching = new BorderPane();
        borderPaneCatalogSearching.setPrefSize(BORDER_WIDTH, BORDER_HIGH);

        gridPaneSearch.setMinSize(GRID_WIDTH, GRID_HIGH);
        gridPaneSearch.setVgap(8);   //espacio
        gridPaneSearch.setHgap(8);    // espacio
        gridPaneSearch.setAlignment(Pos.CENTER);
        gridPaneSearch.setStyle(("-fx-background-image:url('file:src/image/Bus.jpg');"
                + "-fx-background-repeat : no-repeat;"
                + "-fx-background-size: 920 920, 20 20, 20 20, 20 20, auto;"));

        comboBoxTool.setValue("Herramientas");
        comboBoxTool.setStyle("-fx-background-color: lightblue");
        gridPaneSearch.add(comboBoxTool, 0, 0);
        //   graphicalUserInterfaceLogic.createComboBox(comboBoxTool);
        comboBoxTool.setOnMouseClicked((event) -> {

            textFieldProperties.setDisable(false);
        });

        textFieldProperties.setPromptText("Nombre de la propiedad");
        textFieldProperties.setStyle(
                "-fx-background-color: lightblue; "
                + "-fx-background-insets: 4; "
                +// tamano
                "-fx-background-radius: 4; "
                +// tamano
                "-fx-effect: dropshadow(three-pass-box, blue, 20, 0, 0, 0);");
        textFieldProperties.setFocusTraversable(false);
        textFieldProperties.setDisable(true);
        gridPaneSearch.add(textFieldProperties, 0, 5); /// columna fila

        textFieldProperties.setOnMouseClicked((event) -> {

            buttonSearch.setDisable(false);
            labelName.setText("");
            gridPaneSearch.getChildren().removeAll(labelProperties); //*Genera warnings porque los labels están vacíos y los
            //reconoce como nulos pero no afecta al funcionamiento de la aplicación 
        });

        buttonSearch = new Button("Buscar");
        buttonSearch.setTextFill(Color.WHITE);//Color de la letra del boton
        buttonSearch.setStyle("-fx-background-color: BLACK");//Color del fondo
        buttonSearch.setFont(Font.font("Castellar", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 10));//Tipo de letra
        gridPaneSearch.add(buttonSearch, 0, 6);
        // buttonSearch.setDisable(true);
        buttonSearch.setOnAction((event) -> {
            
            ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.submit(() -> {
                System.out.println("Al princi");
                try {
                    CatalogSearch.Client osoArio = new CatalogSearch.Client("192.168.1.7", 12345);
                    //   System.out.println(osoArio.clientSocket.getOutputStream() + " Soy tama");

//                    for (int i = 0; i < osoArio.catalogos.size() - 1; i++) {
//                        if (!comboBoxTool.getItems().contains(osoArio.catalogos.get(i))) {
//                            comboBoxTool.getItems().addAll(osoArio.catalogos.get(i));
//                        }//end if
//                    }//end for
                    System.out.println("Llegue al final");

                } catch (InterruptedException ex) {
                    Logger.getLogger(DefineProperties.class.getName()).log(Level.SEVERE, null, ex);
                }

            });
            // searchFuncionality();
            textFieldProperties.setDisable(true);
            buttonSearch.setDisable(true);
        });//end funcionalidad del boton buscar

        Button buttonClose = new Button("Cerrar");
        buttonClose.setTextFill(Color.WHITE);//Color de la letra del boton
        buttonClose.setStyle("-fx-background-color: BLACK");//Color del fondo
        buttonClose.setFont(Font.font("Castellar", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 10));//Tipo de letra
        gridPaneSearch.add(buttonClose, 0, 7);
        buttonClose.setOnAction((event) -> {
            gridPaneSearch.getChildren().clear();
            gridPaneSearch.setBackground(Background.EMPTY);
        });//end btn cerrar
        borderPaneCatalogSearching.setTop(gridPaneSearch);
        return borderPaneCatalogSearching;
    }//end BorderPane catalogSearching()

    /**
     * Metodo que contiene la funcionalidad del boton buscar
     */
    private void searchFuncionality() {

////        if (search.exist(comboBoxTool.getValue().toString() + "") != false) {
////            StringTokenizer stringTokenizer = new StringTokenizer(search.readLine(comboBoxTool.getValue().toString(), textFieldProperties.getText()), "|");
////            int checkTokens = 0;
////            position = 0;
////            labelProperties = new ArrayList<Label>();
////
////            while (stringTokenizer.hasMoreTokens()) {
////                if (checkTokens == 0) {
////                    labelName = new Label("Primer nombre: " + stringTokenizer.nextToken());
////                    gridPaneSearch.add(labelName, 1, 3);
////                }//end if medio
////                else if (checkTokens > 0 && checkTokens < graphicalUserInterfaceLogic.tokensAccount(search.readLine(comboBoxTool.getValue().toString(),
////                        textFieldProperties.getText()))) {
////                    Label labelAdd = new Label();
////                    labelAdd.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 20));
////                    labelAdd.setTextFill(Color.WHITE);
////                    labelAdd.setStyle("-fx-background-color: rgb(41, 75, 152);");
////                    labelAdd.setText(property);
////                    labelProperties.add(position, labelAdd);
////                    if (position % 2 == 0) {
////                        gridPaneSearch.add(labelProperties.get(position), 2, position - 1 + 5);
////                    } //end if interno
////                    else {
////                        gridPaneSearch.add(labelProperties.get(position), 1, position + 5);
////                    }//end else interno
////                    labelName.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 20));
////                    labelName.setTextFill(Color.WHITE);
////                    labelName.setStyle("-fx-background-color: rgb(41, 75, 152);");
////                    property = stringTokenizer.nextToken();
////                    position++;
////                }//end else if
////                checkTokens++;
////            }//end while
////        }//end if grande
////        textFieldProperties.clear();
    }//end searchFuncionality

}//edn CatalogSearch
