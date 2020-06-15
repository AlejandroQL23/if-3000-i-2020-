package edu.ucr.rp.customer.GUI;


import static edu.ucr.rp.GUI.ConstantsElements.*;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class About {
 /**
     * 
     * @return Nos da la GUI que contiene información básica del app
     */
    public GridPane getGraphicalUserInterfaceAbout() {
        GridPane about = new GridPane();
        about.setMinSize(GRID_WIDTH, GRID_HIGH);
        about.setVgap(15); 
        about.setHgap(15);
        about.setAlignment(Pos.TOP_LEFT);
        about.setStyle("-fx-background-image:url(aCd.png);" 
                + "-fx-background-position:center;"
                + "-fx-background-repeat : no-repeat;" 
                + "-fx-background-size: auto-size");


        Text title = new Text(200, 200, "Acerca de la aplicación: Control de inventarios");
        title.setFont(Font.font(20));
        title.setOnMouseMoved(e -> {
            title.setFill(Color.color(Math.random(), Math.random(), Math.random()));
        });
        about.add(title, 0, 0);

        Label lBAbout = new Label("Aplicación control de inventarios 2020 \n"
                + " Desarrollado en: Apache NetBeans IDE 11.3 \n Lenguaje Java, version 14 \n 2020");
        lBAbout.setFont(Font.font("Rockwell", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 20));
        lBAbout.setTextFill(Color.DEEPSKYBLUE);
        about.add(lBAbout, 0, 1);
        lBAbout.setVisible(true);

        Button buttonClose = new Button("Cerrar");
        buttonClose.setTextFill(Color.WHITE);
        buttonClose.setStyle("-fx-background-color: BLACK");
        buttonClose.setFont(Font.font("Castellar", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 10));
        about.add(buttonClose, 0, 2);
        buttonClose.setOnAction((event) -> {
            about.getChildren().clear();
            about.setBackground(Background.EMPTY);
        });
        return about;
    }//end method acercaDe
}//end about
