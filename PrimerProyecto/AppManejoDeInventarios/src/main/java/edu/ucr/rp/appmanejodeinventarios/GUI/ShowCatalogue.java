package edu.ucr.rp.appmanejodeinventarios.GUI;

import edu.ucr.rp.appmanejodeinventarios.Logic.GraphicalUserInterfaceLogic;
import static edu.ucr.rp.appmanejodeinventarios.Logic.ConstantsElements.*;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class ShowCatalogue {

    GraphicalUserInterfaceLogic graphicalUserInterfaceLogic = new GraphicalUserInterfaceLogic();
    
    ComboBox comboBoxTool = new ComboBox();
    GridPane gridPaneShowCatalogue = new GridPane();
    
    /**
     * 
     * @return Nos da la GUI que nos mostrará los .catálogos existentes
     */
    public BorderPane showExistingCatalogues() {

        BorderPane borderPaneShowExistingCatalogues = new BorderPane();
        borderPaneShowExistingCatalogues.setPrefSize(BORDER_WIDTH, BORDER_HIGH);

        gridPaneShowCatalogue.setMinSize(GRID_WIDTH, GRID_HIGH);
        gridPaneShowCatalogue.setVgap(15);
        gridPaneShowCatalogue.setHgap(15);
        gridPaneShowCatalogue.setStyle(("-fx-background-image:url('file:src/image/2.jpg');"
                + "-fx-background-repeat : no-repeat;"
                + "-fx-background-size: 920 920, 20 20, 20 20, 20 20, auto;"));
        gridPaneShowCatalogue.setAlignment(Pos.CENTER);

        comboBoxTool.setValue("Herramientas");
        comboBoxTool.setStyle("-fx-background-color: lightblue");
        gridPaneShowCatalogue.add(comboBoxTool, 0, 0);
        graphicalUserInterfaceLogic.createComboBox(comboBoxTool);

        Button buttonClose = new Button("Cerrar");
        buttonClose.setTextFill(Color.WHITE);//Color de la letra del boton
        buttonClose.setStyle("-fx-background-color: BLACK");//Color del fondo
        buttonClose.setFont(Font.font("Castellar", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 10));//Tipo de letra
        gridPaneShowCatalogue.add(buttonClose, 0, 9);
        buttonClose.setOnAction((event) -> {
            gridPaneShowCatalogue.getChildren().clear();
            gridPaneShowCatalogue.setBackground(Background.EMPTY);

        });

        borderPaneShowExistingCatalogues.setTop(gridPaneShowCatalogue);

        return borderPaneShowExistingCatalogues;
    }//end showExistingCatalogues

}//end ShowCatalogue
