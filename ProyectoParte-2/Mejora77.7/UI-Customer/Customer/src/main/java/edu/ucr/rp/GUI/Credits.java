package edu.ucr.rp.GUI;

import static edu.ucr.rp.GUI.ConstantsElements.*;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class Credits {

    /**
     *
     * @return Nos da la GUI que contiene información de los creadores del app
     */
    public GridPane getGraphicalUserInterfaceCredits() {
        GridPane gridPaneCredits = new GridPane();
        gridPaneCredits.setMinSize(GRID_WIDTH, GRID_HIGH);
        gridPaneCredits.setVgap(15);
        gridPaneCredits.setHgap(15);
        gridPaneCredits.setAlignment(Pos.TOP_LEFT);

        Text setTitle = new Text(200, 200, "Creditos de la aplicacion: Control de inventarios");
        setTitle.setFont(Font.font(20));
        setTitle.setOnMouseMoved(e -> {
            setTitle.setFill(Color.color(Math.random(), Math.random(), Math.random()));
        });
        gridPaneCredits.add(setTitle, 0, 0);

        Label labelCredits = new Label("Realizado por: \n"
                + "Diego Garita Abarca B83214 \nAlejandro Quesada Leiva B86205");
        labelCredits.setFont(Font.font("Rockwell", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 20));
        labelCredits.setTextFill(Color.GREENYELLOW);
        gridPaneCredits.add(labelCredits, 0, 1);
        labelCredits.setVisible(true);

        Button buttonClose = new Button("Cerrar");
        buttonClose.setTextFill(Color.WHITE);
        buttonClose.setStyle("-fx-background-color: BLACK");
        buttonClose.setFont(Font.font("Castellar", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 10));
        gridPaneCredits.add(buttonClose, 0, 4);
        buttonClose.setOnAction((event) -> {
            gridPaneCredits.getChildren().clear();
            gridPaneCredits.setBackground(Background.EMPTY);

        });//end btn cerrar

        return gridPaneCredits;
    }//end GridPane getGraphicalUserInterfaceCredits()
}//end class Credits 
