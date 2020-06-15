package edu.ucr.rp.customer.GUI;


//import edu.ucr.rp.customer.Logic.SaveObject;
//import edu.ucr.rp.customer.Logic.Mercancia;
import edu.ucr.rp.GUI.ConstantsElements;
import static edu.ucr.rp.GUI.ConstantsElements.*;
import java.io.*;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class DefineProperties {

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
            buttonAcceptNumberOfProperties.setDisable(false);
        });

        buttonAcceptNumberOfProperties = new Button("Aceptar");
        buttonAcceptNumberOfProperties.setTextFill(Color.WHITE);
        buttonAcceptNumberOfProperties.setStyle("-fx-background-color: BLACK");
        buttonAcceptNumberOfProperties.setFont(Font.font("Castellar", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 10));
        gridPaneDefineProperties.add(buttonAcceptNumberOfProperties, 0, 3);
        buttonAcceptNumberOfProperties.setDisable(true);
        buttonAcceptNumberOfProperties.setOnAction((event) -> {

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
