package edu.ucr.rp.lab05.GUI;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;


public class SmsUI extends Application {
    

    private TextField amountTextField;
    private TextField serviceTextField;
    private Button rechargeButton;
    private ProgressBar rechargeProgressBar;


    @Override
    public void start(Stage stage) {
        title(stage);
        GridPane pane = buildPane();
        setupControls(pane);
        stage.setScene(createScene(pane));
        handlers();
        stage.show();

    }

    private void handlers() {
        rechargeButton.setOnAction(actionEvent -> recharge());
    }

    private void recharge() {

    }

    private GridPane buildPane() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(40, 40, 40, 40));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        ColumnConstraints columnOneConstraints = new ColumnConstraints(250, 250, 500);
        columnOneConstraints.setHalignment(HPos.RIGHT);
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(200, 200, 500);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        return gridPane;
    }

    private void setupControls(GridPane pane) {
        amountTextField = buildTextInput("Monto: ", pane, 0);
        serviceTextField = buildTextInput("Servicio: ", pane, 1);
        rechargeButton = buildGenerateButton("Recargar", pane, 2);
        rechargeProgressBar = new ProgressBar(0);
        pane.add(rechargeProgressBar, 0, 3, 2, 1);

    }

    private Button buildGenerateButton(String label, GridPane pane, int row) {
        Button button = new Button(label);
        pane.add(button, 0, row, 2, 1);
        GridPane.setHalignment(button, HPos.CENTER);
        GridPane.setMargin(button, new Insets(20, 0, 20, 0));
        return button;
    }

    private TextField buildTextInput(String text, GridPane pane, int row) {
        Label minNumberLabel = new Label(text);
        pane.add(minNumberLabel, 0, row);
        TextField textField = new TextField();
        pane.add(textField, 1, row);
        return textField;
    }

    private void title(Stage stage) {
        stage.setTitle("Bienvenido al generador de passwords");
    }

    private Scene createScene(Pane pane) {
        return new Scene(pane, 800, 500);
    }

    public static void main(String[] args) {
        launch(args);
    }
}