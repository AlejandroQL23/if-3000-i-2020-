package edu.ucr.rp.appmanejodeinventarios.GUI;

import edu.ucr.rp.appmanejodeinventarios.Logic.SaveObject;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import static edu.ucr.rp.appmanejodeinventarios.Logic.ConstantsElements.*;

public class DeleteCatalogues {

    SaveObject saveObject = new SaveObject();
    Button buttonDelete;

    /**
     *
     * @return Nos da la GUI que nos permite eliminar el .catálogo y el
     * .propiedades indicado por el usuario
     */
    public GridPane deleteCatalogues() {

        GridPane gridPaneDelete = new GridPane();
        gridPaneDelete.setMinSize(GRID_WIDTH, GRID_HIGH);
        gridPaneDelete.setVgap(15);
        gridPaneDelete.setHgap(15);
        gridPaneDelete.setAlignment(Pos.CENTER);
        gridPaneDelete.setStyle(("-fx-background-image:url('file:src/image/elimi.png');"
                + "-fx-background-repeat : no-repeat;"
                + "-fx-background-size: 920 920, 20 20, 20 20, 20 20, auto;"));

        TextField textFieldToDelete = new TextField();
        textFieldToDelete.setPromptText("Nombre del catalogo que desea eliminar");
        textFieldToDelete.setStyle(
                "-fx-background-color: lightblue; "
                + "-fx-background-insets: 4; "
                +// tamano
                "-fx-background-radius: 4; "
                +// tamano
                "-fx-effect: dropshadow(three-pass-box, blue, 20, 0, 0, 0);");
        textFieldToDelete.setFocusTraversable(false);
        textFieldToDelete.setOnKeyPressed((event) -> {
            buttonDelete.setDisable(false);
        });
        gridPaneDelete.add(textFieldToDelete, 0, 0);

        buttonDelete = new Button("Eliminar");
        buttonDelete.setTextFill(Color.WHITE);
        buttonDelete.setStyle("-fx-background-color: BLACK");
        buttonDelete.setFont(Font.font("Castellar", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 10));
        gridPaneDelete.add(buttonDelete, 0, 2);
        buttonDelete.setDisable(true);
        buttonDelete.setOnMouseClicked((event) -> {
            String fileName = textFieldToDelete.getText();
            saveObject.deleteFiles(fileName);
            textFieldToDelete.clear();
            buttonDelete.setDisable(true);
        });

        Button buttonClose = new Button("Cerrar");
        buttonClose.setTextFill(Color.WHITE);
        buttonClose.setStyle("-fx-background-color: BLACK");
        buttonClose.setFont(Font.font("Castellar", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 10));
        gridPaneDelete.add(buttonClose, 0, 3);
        buttonClose.setOnAction((event) -> {
            gridPaneDelete.getChildren().clear();
            gridPaneDelete.setBackground(Background.EMPTY);  //limpia color para que quede el color
        });
        return gridPaneDelete;
    }//end deleteCatalogues() 
}//end DeleteCatalogues
