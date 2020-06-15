package edu.ucr.rp.customer.GUI;

import static edu.ucr.rp.GUI.ConstantsElements.*;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class ShowCatalog {

    
    ComboBox comboBoxTool = new ComboBox();
    GridPane gridPaneShowCatalog = new GridPane();
    
    /**
     * 
     * @return Nos da la GUI que nos mostrará los .catálogos existentes
     */
    public BorderPane showExistingCatalogs() {

        BorderPane borderPaneShowExistingCatalogs = new BorderPane();
        borderPaneShowExistingCatalogs.setPrefSize(BORDER_WIDTH, BORDER_HIGH);

        gridPaneShowCatalog.setMinSize(GRID_WIDTH, GRID_HIGH);
        gridPaneShowCatalog.setVgap(15);
        gridPaneShowCatalog.setHgap(15);
        gridPaneShowCatalog.setStyle(("-fx-background-image:url('file:src/image/2.jpg');"
                + "-fx-background-repeat : no-repeat;"
                + "-fx-background-size: 920 920, 20 20, 20 20, 20 20, auto;"));
        gridPaneShowCatalog.setAlignment(Pos.CENTER);

        comboBoxTool.setValue("Herramientas");
        comboBoxTool.setStyle("-fx-background-color: lightblue");
        gridPaneShowCatalog.add(comboBoxTool, 0, 0);
      //  graphicalUserInterfaceLogic.createComboBox(comboBoxTool);

        Button buttonClose = new Button("Cerrar");
        buttonClose.setTextFill(Color.WHITE);//Color de la letra del boton
        buttonClose.setStyle("-fx-background-color: BLACK");//Color del fondo
        buttonClose.setFont(Font.font("Castellar", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 10));//Tipo de letra
        gridPaneShowCatalog.add(buttonClose, 0, 9);
        buttonClose.setOnAction((event) -> {
            gridPaneShowCatalog.getChildren().clear();
            gridPaneShowCatalog.setBackground(Background.EMPTY);

        });

        borderPaneShowExistingCatalogs.setTop(gridPaneShowCatalog);

        return borderPaneShowExistingCatalogs;
    }//end showExistingCatalogs

}//end ShowCatalog
