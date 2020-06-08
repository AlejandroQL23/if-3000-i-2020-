package edu.ucr.rp.appmanejodeinventarios.GUI;

import static edu.ucr.rp.appmanejodeinventarios.Logic.ConstantsElements.*;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class Help {

    Label labelCatalog = new Label("");
    Label labelMaintenance = new Label("");
    Label labelReports = new Label("");
    Button buttonCatalog;
    Button buttonMaintenance;
    Button buttonReports;

    /**
     * 
     * @return Nos da la GUI que contiene información de cada una de las funcionalidades del app
     */
    public GridPane getGraphicalUserInterfaceHelper() {
        GridPane gridPaneHelper = new GridPane();
        gridPaneHelper.setMinSize(GRID_WIDTH, GRID_HIGH);
        gridPaneHelper.setVgap(15);
        gridPaneHelper.setHgap(15);
        gridPaneHelper.setAlignment(Pos.TOP_LEFT);

        Label labelFunctionality = new Label("¿ Y cómo funciona cada una de las opciones del app Control de inventarios ?");
        labelFunctionality.setFont(Font.font("Rockwell", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 20));
        labelFunctionality.setTextFill(Color.POWDERBLUE);
        gridPaneHelper.add(labelFunctionality, 0, 1);

        buttonCatalog = new Button("Crear Catalogo");
        buttonCatalog.setTextFill(Color.WHITE);
        buttonCatalog.setStyle("-fx-background-color: BLACK");
        buttonCatalog.setFont(Font.font("Castellar", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 10));
        gridPaneHelper.add(buttonCatalog, 0, 2);
        buttonCatalog.setOnAction((event) -> {
            labelReports.setVisible(false);
            labelMaintenance.setVisible(false);
            labelCatalog = new Label("Crear catalogo: \n Es donde se define el nombre del objeto y las propiedades de este \n "
                    + "Definir propiedades: \n Aquí podra asignar las propiedades anteriormente definidas \n");
            labelCatalog.setFont(Font.font("Rockwell", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 20));  // tipo y tamaño de letra
            labelCatalog.setStyle("-fx-background-color: rgb(41, 75, 152);");
            labelCatalog.setTextFill(Color.POWDERBLUE);
            labelCatalog.setVisible(true);
            gridPaneHelper.add(labelCatalog, 0, 7);
            buttonMaintenance.setDisable(false);
            buttonReports.setDisable(false);
            buttonCatalog.setDisable(true);

        }); //end action Paises

        buttonReports = new Button("Reporte");
        buttonReports.setTextFill(Color.WHITE);
        buttonReports.setStyle("-fx-background-color: BLACK");
        buttonReports.setFont(Font.font("Castellar", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 10));
        gridPaneHelper.add(buttonReports, 0, 4);
        buttonReports.setOnMouseClicked((event) -> {
            labelMaintenance.setVisible(false);
            labelCatalog.setVisible(false);

            labelReports = new Label("Búsqueda por catalogo: \n Es donde se muestran los distintos objetos que tiene un catalogo en específico \n "
                    + "Modificar catalogo: \n Aquí podrás modificar \n"
                    + "Listado de catalogos: Aquí se muestra la lista de catalogos existentes ");
            labelReports.setTextFill(Color.POWDERBLUE);
            labelReports.setFont(Font.font("Rockwell", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 20));
            labelReports.setStyle("-fx-background-color: rgb(41, 75, 152);");
            labelReports.setVisible(true);
            gridPaneHelper.add(labelReports, 0, 7);
            buttonMaintenance.setDisable(false);
            buttonCatalog.setDisable(false);
            buttonReports.setDisable(true);

        }); //end action report

        buttonMaintenance = new Button("Mantenimiento");
        buttonMaintenance.setTextFill(Color.WHITE);
        buttonMaintenance.setStyle("-fx-background-color: BLACK");
        buttonMaintenance.setFont(Font.font("Castellar", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 10));
        gridPaneHelper.add(buttonMaintenance, 0, 3);
        buttonMaintenance.setOnMouseClicked((event) -> {

            labelCatalog.setVisible(false);
            labelReports.setVisible(false);
            labelMaintenance = new Label("Aquí podrás eliminar por completo todos tus registros ");
            labelMaintenance.setTextFill(Color.POWDERBLUE);
            labelMaintenance.setFont(Font.font("Rockwell", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 20));
            labelMaintenance.setStyle("-fx-background-color: rgb(41, 75, 152);");
            labelMaintenance.setVisible(true);
            gridPaneHelper.add(labelMaintenance, 0, 7);
            buttonCatalog.setDisable(false);
            buttonReports.setDisable(false);
            buttonMaintenance.setDisable(true);
        }); //end action Mant

        Button buttonClose = new Button("Cerrar");
        buttonClose.setTextFill(Color.WHITE);
        buttonClose.setStyle("-fx-background-color: BLACK");
        buttonClose.setFont(Font.font("Castellar", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 10));
        gridPaneHelper.add(buttonClose, 0, 5);
        buttonClose.setOnAction((event) -> {
            gridPaneHelper.getChildren().clear();
            gridPaneHelper.setBackground(Background.EMPTY);
        });//end action Cerrar

        return gridPaneHelper;
    }//end method getGraphicalUserInterfaceHelper
}//end class Help
