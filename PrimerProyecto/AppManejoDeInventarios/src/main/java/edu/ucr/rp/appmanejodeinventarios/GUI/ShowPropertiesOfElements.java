package edu.ucr.rp.appmanejodeinventarios.GUI;

import static edu.ucr.rp.appmanejodeinventarios.Logic.ConstantsElements.*;
import edu.ucr.rp.appmanejodeinventarios.Logic.SaveObject;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class ShowPropertiesOfElements {

    SaveObject saveObject = new SaveObject();

    Label labelShowContentOfFiles;
    TextField textFieldSearch = new TextField();
    GridPane gridPaneShowProperties = new GridPane();
    Button buttonSearch;

    /**
     *
     * @return Nos da la GUI que nos permite mostrar la información contenida en
     * el .propidades
     */
    public BorderPane showInformationByCatalog() {

        BorderPane borderPaneShowInformationByCatalogs = new BorderPane();
        borderPaneShowInformationByCatalogs.setPrefSize(BORDER_WIDTH, BORDER_HIGH);
        gridPaneShowProperties.setMinSize(GRID_WIDTH, GRID_HIGH);
        // determina el espacio entre columnas (vertical y horizontal)
        gridPaneShowProperties.setVgap(8);   //espacio
        gridPaneShowProperties.setHgap(8);    // espacio
        // alinear el grip
        gridPaneShowProperties.setAlignment(Pos.CENTER);
        gridPaneShowProperties.setStyle(("-fx-background-image:url('file:src/image/3.jpg');"
                + "-fx-background-repeat : no-repeat;"
                + "-fx-background-size: 920 920, 20 20, 20 20, 20 20, auto;"));

        textFieldSearch.setPromptText("Nombre del Catalogo");
        textFieldSearch.setStyle(
                "-fx-background-color: lightblue; "
                + "-fx-background-insets: 4; "
                +// tamano
                "-fx-background-radius: 4; "
                +// tamano
                "-fx-effect: dropshadow(three-pass-box, blue, 20, 0, 0, 0);");
        textFieldSearch.setFocusTraversable(false);
        gridPaneShowProperties.add(textFieldSearch, 0, 3); /// columna fila
        textFieldSearch.setOnKeyPressed((event) -> {
            buttonSearch.setDisable(false);
            gridPaneShowProperties.getChildren().removeAll(labelShowContentOfFiles);
        });

        buttonSearch = new Button("Buscar");
        buttonSearch.setTextFill(Color.WHITE);//Color de la letra del boton
        buttonSearch.setStyle("-fx-background-color: BLACK");//Color del fondo
        buttonSearch.setFont(Font.font("Castellar", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 10));//Tipo de letra
        gridPaneShowProperties.add(buttonSearch, 0, 4);
        buttonSearch.setDisable(false);
        buttonSearch.setDisable(true);
        buttonSearch.setOnAction((event) -> {

            String getName = textFieldSearch.getText();
            try {
                labelShowContentOfFiles = new Label(saveObject.showContent(getName).replace("|", " "));
                labelShowContentOfFiles.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 20));
                labelShowContentOfFiles.setTextFill(Color.WHITE);
                labelShowContentOfFiles.setStyle("-fx-background-color: rgb(41, 75, 152);");

            }//end try//end try//end try//end try//end try//end try//end try//end try//end try//end try//end try//end try//end try//end try//end try//end try
            catch (IOException IOException) {
                Logger.getLogger(ShowPropertiesOfElements.class.getName()).log(Level.SEVERE, null, IOException);
            }//end catch//end catch

            buttonSearch.setDisable(true);
            gridPaneShowProperties.add(labelShowContentOfFiles, 0, 5);

        });//end funcionalidad del boton buscar

        Button buttonClose = new Button("Cerrar");
        buttonClose.setTextFill(Color.WHITE);//Color de la letra del boton
        buttonClose.setStyle("-fx-background-color: BLACK");//Color del fondo
        buttonClose.setFont(Font.font("Castellar", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 10));//Tipo de letra
        gridPaneShowProperties.add(buttonClose, 0, 7);
        buttonClose.setOnAction((event) -> {
            gridPaneShowProperties.getChildren().clear();
            gridPaneShowProperties.setBackground(Background.EMPTY);  //limpia color para que quede el color
        });//end btn cerrar
        borderPaneShowInformationByCatalogs.setTop(gridPaneShowProperties);
        return borderPaneShowInformationByCatalogs;
    }//end showInformationByCatalog

}//end class ShowCatalog
