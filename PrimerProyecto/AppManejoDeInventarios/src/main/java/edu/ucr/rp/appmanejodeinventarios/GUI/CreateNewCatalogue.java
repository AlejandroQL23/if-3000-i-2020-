package edu.ucr.rp.appmanejodeinventarios.GUI;

import edu.ucr.rp.appmanejodeinventarios.Logic.ConstantsElements;
import edu.ucr.rp.appmanejodeinventarios.Logic.SaveObject;
import edu.ucr.rp.appmanejodeinventarios.Logic.Object;
import java.io.File;
import java.util.*;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import static edu.ucr.rp.appmanejodeinventarios.Logic.ConstantsElements.*;

public class CreateNewCatalogue {

    SaveObject saveObject = new SaveObject();
    ConstantsElements constantsElements = new ConstantsElements();
    Object object;

    ArrayList<TextField> textFieldProperties;
    TextField textFieldCatalogueName;
    TextField textFieldPropertiesQuantity;
    Button buttonAcceptNumberOfProperties;
    Button buttonAdd;
    String fileName;
    int quantityOfProperties;

    public CreateNewCatalogue(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 
     * @return Nos da la GUI que nos permite crear un nuevo catálogo
     */
    public GridPane createCatalogue() {

        File file = new File(fileName);
        GridPane gridPaneNewCatalogue = new GridPane();
        gridPaneNewCatalogue.setMinSize(GRID_WIDTH, GRID_HIGH);
        // determina el espacio entre columnas (vertical y horizontal)
        gridPaneNewCatalogue.setVgap(15);   //espacio
        gridPaneNewCatalogue.setHgap(15);    // espacio
        // alinear el grip
        gridPaneNewCatalogue.setAlignment(Pos.CENTER);
        // gridPaneNewCatalogue.setStyle("-fx-background-color: dodgerblue");
        gridPaneNewCatalogue.setStyle(("-fx-background-image:url('file:src/image/FCrear.jpg');"
                + "-fx-background-repeat : no-repeat;"
                + "-fx-background-size: 920 920, 20 20, 20 20, 20 20, auto;"));

        textFieldCatalogueName = new TextField();
        textFieldCatalogueName.setPromptText("Nombre del catalogo");
        textFieldCatalogueName.setStyle(
                "-fx-background-color: lightblue; "
                + "-fx-background-insets: 4; "
                +// tamano
                "-fx-background-radius: 4; "
                +// tamano
                "-fx-effect: dropshadow(three-pass-box, blue, 20, 0, 0, 0);");
        gridPaneNewCatalogue.add(textFieldCatalogueName, 0, 1);
        textFieldCatalogueName.setFocusTraversable(false);
        textFieldCatalogueName.setOnKeyPressed((event) -> {
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
        gridPaneNewCatalogue.add(textFieldPropertiesQuantity, 0, 2); /// columna fila
        textFieldPropertiesQuantity.setFocusTraversable(false);
        textFieldPropertiesQuantity.setDisable(true);

        textFieldPropertiesQuantity.setOnKeyPressed((event) -> {
            buttonAcceptNumberOfProperties.setDisable(false);
        });

        buttonAcceptNumberOfProperties = new Button("Definir cantidad");
        buttonAcceptNumberOfProperties.setTextFill(Color.WHITE);//Color de la letra del boton
        buttonAcceptNumberOfProperties.setStyle("-fx-background-color: BLACK");//Color del fondo
        buttonAcceptNumberOfProperties.setFont(Font.font("Castellar", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 10));//Tipo de letra
        gridPaneNewCatalogue.add(buttonAcceptNumberOfProperties, 0, 3);
        buttonAcceptNumberOfProperties.setDisable(true);
        buttonAcceptNumberOfProperties.setOnMouseClicked((event) -> {
            buttonAdd.setDisable(false);
        });

        buttonAcceptNumberOfProperties.setOnAction((event) -> {
            quantityOfProperties = Integer.parseInt(textFieldPropertiesQuantity.getText());
            textFieldProperties = new ArrayList<TextField>();
            if (quantityOfProperties > 5 || quantityOfProperties < 1) {
                constantsElements.soundPlayer("noti");
                constantsElements.alertWarning("Cantidad de propiedades invalida, intentelo de nuevo");
                textFieldPropertiesQuantity.clear();
                textFieldCatalogueName.clear();
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
                    gridPaneNewCatalogue.add(textFieldProperties.get(i), 0, i + 4);
                }// end for
            }//end else
            textFieldPropertiesQuantity.setDisable(true);
            textFieldCatalogueName.setDisable(true);
            buttonAcceptNumberOfProperties.setDisable(true);
        });//end setOnAction

        buttonAdd = new Button("Insertar");
        buttonAdd.setTextFill(Color.WHITE);//Color de la letra del boton
        buttonAdd.setStyle("-fx-background-color: BLACK");//Color del fondo
        buttonAdd.setFont(Font.font("Castellar", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 10));//Tipo de letra
        gridPaneNewCatalogue.add(buttonAdd, 2, 7);
        buttonAdd.setDisable(true);
        buttonAdd.setOnAction((event) -> {
            object = new Object(textFieldCatalogueName.getText().trim(), quantityOfProperties, textFieldProperties, new ArrayList<Label>());//**VECES QUE ENTRA
            saveObject.writeFileCatalogue(object);
            textFieldCatalogueName.clear();
            textFieldPropertiesQuantity.clear();
            gridPaneNewCatalogue.getChildren().removeAll(textFieldProperties);

            constantsElements.soundPlayer("noti");
            constantsElements.alertInformation("Elemento ingresado y guardado correctamente");

            textFieldPropertiesQuantity.setDisable(true);
            textFieldCatalogueName.setDisable(false);
            buttonAcceptNumberOfProperties.setDisable(true);
            buttonAdd.setDisable(true);
        });//END BUTTON

        Button buttonClose = new Button("Cerrar");
        buttonClose.setTextFill(Color.WHITE);//Color de la letra del boton
        buttonClose.setStyle("-fx-background-color: BLACK");//Color del fondo
        buttonClose.setFont(Font.font("Castellar", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 10));//Tipo de letra
        gridPaneNewCatalogue.add(buttonClose, 2, 8);
        buttonClose.setOnAction((event) -> {

            gridPaneNewCatalogue.getChildren().clear();
            gridPaneNewCatalogue.setBackground(Background.EMPTY);  //limpia color para que quede el color

        });//end btn cerrar

        return gridPaneNewCatalogue;
    }//end GridPane createCatalogue()

}// end class CreateNewCatalogue
