package edu.ucr.rp.customer.GUI;

//import edu.ucr.rp.customer.Logic.SaveObject;
//import edu.ucr.rp.customer.Logic.Mercancia;
import edu.ucr.rp.GUI.ConstantsElements;
import static edu.ucr.rp.GUI.ConstantsElements.*;
import java.util.*;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CreateNewCatalog {

    // SaveObject saveObject = new SaveObject();
    // ConstantsElements constantsElements = new ConstantsElements();
    // Mercancia object;
    ConstantsElements consElements = new ConstantsElements();
    ArrayList<TextField> textFieldProperties;
    ArrayList<String> Properties;
    TextField textFieldCatalogName;
    TextField textFieldPropertiesQuantity;
    Button buttonAcceptNumberOfProperties;
    Button buttonAdd;
    String stringProperties = "";
    int quantityOfProperties;

    /**
     *
     * @return Nos da la GUI que nos permite crear un nuevo catálogo
     */
    public GridPane createCatalog() {

        GridPane gridPaneNewCatalog = new GridPane();
        gridPaneNewCatalog.setMinSize(GRID_WIDTH, GRID_HIGH);
        // determina el espacio entre columnas (vertical y horizontal)
        gridPaneNewCatalog.setVgap(15);   //espacio
        gridPaneNewCatalog.setHgap(15);    // espacio
        // alinear el grip
        gridPaneNewCatalog.setAlignment(Pos.CENTER);
        // gridPaneNewCatalog.setStyle("-fx-background-color: dodgerblue");
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
                +// tamano
                "-fx-background-radius: 4; "
                +// tamano
                "-fx-effect: dropshadow(three-pass-box, blue, 20, 0, 0, 0);");
        gridPaneNewCatalog.add(textFieldPropertiesQuantity, 0, 2); /// columna fila
        textFieldPropertiesQuantity.setFocusTraversable(false);
        textFieldPropertiesQuantity.setDisable(true);

        textFieldPropertiesQuantity.setOnKeyPressed((event) -> {
            buttonAcceptNumberOfProperties.setDisable(false);
        });

        buttonAcceptNumberOfProperties = new Button("Definir cantidad");
        buttonAcceptNumberOfProperties.setTextFill(Color.WHITE);//Color de la letra del boton
        buttonAcceptNumberOfProperties.setStyle("-fx-background-color: BLACK");//Color del fondo
        buttonAcceptNumberOfProperties.setFont(Font.font("Castellar", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 10));//Tipo de letra
        gridPaneNewCatalog.add(buttonAcceptNumberOfProperties, 0, 3);
        buttonAcceptNumberOfProperties.setDisable(true);
        buttonAcceptNumberOfProperties.setOnMouseClicked((event) -> {
            buttonAdd.setDisable(false);
        });

        buttonAcceptNumberOfProperties.setOnAction((event) -> {
            quantityOfProperties = Integer.parseInt(textFieldPropertiesQuantity.getText());
            textFieldProperties = new ArrayList<TextField>();
            if (quantityOfProperties > 5 || quantityOfProperties < 1) {
                consElements.soundPlayer("noti");
                consElements.alertWarning("Cantidad de propiedades invalida, intentelo de nuevo");
                textFieldPropertiesQuantity.clear();
                textFieldCatalogName.clear();
            }//end if
            else {
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
            }//end else
            textFieldPropertiesQuantity.setDisable(true);
            textFieldCatalogName.setDisable(true);
            buttonAcceptNumberOfProperties.setDisable(true);
        });//end setOnAction

        buttonAdd = new Button("Insertar");
        buttonAdd.setTextFill(Color.WHITE);//Color de la letra del boton
        buttonAdd.setStyle("-fx-background-color: BLACK");//Color del fondo
        buttonAdd.setFont(Font.font("Castellar", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 10));//Tipo de letra
        gridPaneNewCatalog.add(buttonAdd, 2, 7);
        buttonAdd.setDisable(true);

        buttonAdd.setOnAction((event) -> {

            Properties = new ArrayList<>();
            for (int i = 0; i < quantityOfProperties; i++) {
                stringProperties += textFieldProperties.get(i).getText() + "|";
                Properties.add(textFieldProperties.get(i).getText());
            }

            ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.submit(() -> {
                Client osoArio = new Client("192.168.1.7", 12345);
            });
////
////            object = new Mercancia(textFieldCatalogName.getText().trim(), quantityOfProperties, Properties, new ArrayList<String>());//**VECES QUE ENTRA
////            saveObject.writeFileCatalog(object);
////            textFieldCatalogName.clear();
////            textFieldPropertiesQuantity.clear();
////            gridPaneNewCatalog.getChildren().removeAll(textFieldProperties);
////
////            constantsElements.soundPlayer("noti");
////            constantsElements.alertInformation("Elemento ingresado y guardado correctamente");
////
////            textFieldPropertiesQuantity.setDisable(true);
////            textFieldCatalogName.setDisable(false);
////            buttonAcceptNumberOfProperties.setDisable(true);
////            buttonAdd.setDisable(true);
        });//END BUTTON

        Button buttonClose = new Button("Cerrar");
        buttonClose.setTextFill(Color.WHITE);//Color de la letra del boton
        buttonClose.setStyle("-fx-background-color: BLACK");//Color del fondo
        buttonClose.setFont(Font.font("Castellar", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 10));//Tipo de letra
        gridPaneNewCatalog.add(buttonClose, 2, 8);
        buttonClose.setOnAction((event) -> {

            gridPaneNewCatalog.getChildren().clear();
            gridPaneNewCatalog.setBackground(Background.EMPTY);  //limpia color para que quede el color

        });//end btn cerrar

        return gridPaneNewCatalog;

    }//end GridPane createCatalog()

    public class Client {

        Socket clientSocket;

        public Client(String server, int port) {
            try {

                clientSocket = new Socket(server, port);//
                Thread.sleep(5000);
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                
                out.writeObject(textFieldCatalogName.getText() + "|"
                        + stringProperties + textFieldPropertiesQuantity.getText()) ;//+ Thread.currentThread().getName()
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

                System.out.println(in.readObject());
                stringProperties="";
//                out.writeObject(textFieldCatalogName.getText() + "<>\n" + textFieldPropertiesQuantity.getText() + "<>\n"
//                        + Properties + "<>\n" + Thread.currentThread().getName()); // ---- >  Manda
//
//                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream()); //  < ---- trae
               // System.out.println(in.readObject()); //<--- esto imprime el mensaje que viene desde server
            }//end try 
            catch (IOException | ClassNotFoundException | InterruptedException e) {
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

