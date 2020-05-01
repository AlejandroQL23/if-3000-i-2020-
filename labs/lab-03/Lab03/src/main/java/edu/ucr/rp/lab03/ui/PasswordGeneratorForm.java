package edu.ucr.rp.lab03.ui;

import edu.ucr.rp.lab03.generator.PasswordGeneratorBuilder;
import static edu.ucr.rp.lab03.ui.UIConstants.INPUT_WITH;
import static edu.ucr.rp.lab03.ui.UIConstants.INPUT_WITH_MAX;
import static edu.ucr.rp.lab03.ui.UIConstants.LABEL_WITH;
import static edu.ucr.rp.lab03.ui.UIConstants.LABEL_WITH_MAX;

import javafx.geometry.Insets;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.stage.Window;

public class PasswordGeneratorForm extends Application {

    private TextField minNumberTextField;
    private TextField minUpperTextField;
    private TextField minSpecialCharsTextField;
    private TextField specialCharsTextField;
    private TextField minLengthTextField;
    private Button generateButton;
    private PasswordGeneratorBuilder builder = new PasswordGeneratorBuilder();
    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        // asi logramos tener algo mas limpio
        this.stage = stage;
        title(stage);
        GridPane pane = buildPane();
        setupControls(pane);
        addHandlers();//acciones
        stage.setScene(createScene(pane));
        stage.show();

    }// end Start

    private void title(Stage stage) {
        stage.setTitle("Bienvenido al generador de passwords");
    }// end title

    private GridPane buildPane() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(40, 40, 40, 40));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        ColumnConstraints columnOneConstraints = new ColumnConstraints(LABEL_WITH, LABEL_WITH, LABEL_WITH_MAX);
        columnOneConstraints.setHalignment(HPos.RIGHT);
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(INPUT_WITH, INPUT_WITH, INPUT_WITH_MAX);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        return gridPane;
    }// end gridPane

    private void setupControls(GridPane pane) {
        minNumberTextField = buildTextInput("Cantidad mínima de Mayúsculas: ", pane, 0);
        minUpperTextField = buildTextInput("Cantidad mínima de Números: ", pane, 1);
        minSpecialCharsTextField = buildTextInput("Cantidad mínima de caracteres especiales: ", pane, 2);
        specialCharsTextField = buildTextInput("Caracteres especiales: ", pane, 3);
        minLengthTextField = buildTextInput("Longitud Mínima: ", pane, 4);
        generateButton = buildGenerateButton("Generar", pane, 5);

    }// end setUpControl

    private TextField buildTextInput(String text, GridPane pane, int row) {
        Label minNumberLabel = new Label(text);
        pane.add(minNumberLabel, 0, row);
        TextField textField = new TextField();
        pane.add(textField, 1, row);
        return textField;
    }// end buildTextInput

    private Button buildGenerateButton(String label, GridPane pane, int row) {
        Button button = new Button(label);
        pane.add(button, 0, row, 2, 1);
        GridPane.setHalignment(button, HPos.CENTER);
        GridPane.setMargin(button, new Insets(20, 0, 20, 0));
        return button;
    }//end builGenerateButton

    private void addHandlers() {
        generateButton.setOnAction(actionEvent -> generatePassword());
    }// end handler

    private void generatePassword() {
        builder.withMinSpecialChars(Integer.parseInt(minSpecialCharsTextField.getText()));
        builder.withMinNumbers(Integer.parseInt(minNumberTextField.getText()));
        builder.withMinUpperCase(Integer.parseInt(minUpperTextField.getText()));
        builder.withSpecialChars(specialCharsTextField.getText().toCharArray());
        builder.withMinLength(Integer.parseInt(minLengthTextField.getText()));

        showAlert(Alert.AlertType.INFORMATION, stage, "Password Generado", "El password generado es:" + builder.build().generate());
    }//end generate password

    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }// showAlert

    private Scene createScene(Pane pane) {
        return new Scene(pane, 800, 500);
    }

    public void display() {
        launch();
    }

}

class UIConstants {

    public static final int LABEL_WITH = 250;
    public static final int LABEL_WITH_MAX = 500;
    public static final int INPUT_WITH = 200;
    public static final int INPUT_WITH_MAX = 500;
}
