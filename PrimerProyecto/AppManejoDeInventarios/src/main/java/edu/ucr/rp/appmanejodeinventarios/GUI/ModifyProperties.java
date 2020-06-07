package edu.ucr.rp.appmanejodeinventarios.GUI;

import edu.ucr.rp.appmanejodeinventarios.Logic.Object;
import edu.ucr.rp.appmanejodeinventarios.Logic.ConstantsElements;
import edu.ucr.rp.appmanejodeinventarios.Logic.GraphicalUserInterfaceLogic;
import edu.ucr.rp.appmanejodeinventarios.Logic.SaveObject;
import edu.ucr.rp.appmanejodeinventarios.Logic.Search;
import static edu.ucr.rp.appmanejodeinventarios.Logic.ConstantsElements.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class ModifyProperties {
    Search search = new Search();
    SaveObject saveObject = new SaveObject();
    ConstantsElements constantsElements = new ConstantsElements();
    GraphicalUserInterfaceLogic graphicalUserInterfaceLogic = new GraphicalUserInterfaceLogic();
    Object object;

    List<Label> labelProperties;
    List<TextField> textFieldProperties;
    ArrayList<Label> labelDefineProperties;
    ArrayList<TextField> textFieldDefineProperties;
    TextField textFieldGetProperties = new TextField();
    TextField textFieldEnableProperty = new TextField();
    ComboBox comboBoxTool = new ComboBox();
    Button buttonModify;
    Button buttonFill;
    Button buttonEnable;
    GridPane gridPaneModify = new GridPane();
    int position = 0;

    /**
     * 
     * @return Nos da la GUI que nos permite realizar modificaciones en el .propiedades
     */
    public BorderPane modifyProperties() {

        BorderPane borderPaneModifyProperties = new BorderPane();
        borderPaneModifyProperties.setPrefSize(BORDER_WIDTH, BORDER_HIGH);

        gridPaneModify.setMinSize(GRID_WIDTH, GRID_HIGH);
        gridPaneModify.setVgap(15);
        gridPaneModify.setHgap(15);
        gridPaneModify.setStyle(("-fx-background-image:url('file:src/image/6.jpg');"
                + "-fx-background-repeat : no-repeat;"
                + "-fx-background-size: 920 920, 20 20, 20 20, 20 20, auto;"));
        gridPaneModify.setAlignment(Pos.CENTER);

        comboBoxTool.setValue("Herramientas");
        comboBoxTool.setStyle("-fx-background-color: lightblue");
        gridPaneModify.add(comboBoxTool, 0, 0);
        graphicalUserInterfaceLogic.createComboBox(comboBoxTool);
        comboBoxTool.setOnMouseClicked((event) -> {
            textFieldGetProperties.setDisable(false);
        });

        labelDefineProperties = new ArrayList<Label>();
        textFieldDefineProperties = new ArrayList<TextField>();
        textFieldGetProperties.setPromptText("Nombre de la propiedad");
        textFieldGetProperties.setStyle(
                "-fx-background-color: lightblue; "
                + "-fx-background-insets: 4; "
                +// tamano
                "-fx-background-radius: 4; "
                +// tamano
                "-fx-effect: dropshadow(three-pass-box, blue, 20, 0, 0, 0);");
        textFieldGetProperties.setFocusTraversable(false);
        textFieldGetProperties.setDisable(true);
        gridPaneModify.add(textFieldGetProperties, 0, 2); /// columna fila
        textFieldGetProperties.setOnKeyPressed((event) -> {
            buttonFill.setDisable(false);
        });

        buttonFill = new Button("Mostrar");
        buttonFill.setTextFill(Color.WHITE);//Color de la letra del boton
        buttonFill.setStyle("-fx-background-color: BLACK");//Color del fondo
        buttonFill.setFont(Font.font("Castellar", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 10));//Tipo de letra
        gridPaneModify.add(buttonFill, 0, 3);
        buttonFill.setDisable(true);
        buttonFill.setOnAction((event) -> {
            fillFunctionality();
            buttonFill.setDisable(true);
            textFieldGetProperties.setDisable(true);
            textFieldEnableProperty.setDisable(false);
            comboBoxTool.setDisable(true);

        });

        textFieldEnableProperty.setPromptText("Habilitar propiedad");
        textFieldEnableProperty.setStyle(
                "-fx-background-color: lightblue; "
                + "-fx-background-insets: 4; "
                +// tamano
                "-fx-background-radius: 4; "
                +// tamano
                "-fx-effect: dropshadow(three-pass-box, blue, 20, 0, 0, 0);");
        textFieldEnableProperty.setFocusTraversable(false);
        gridPaneModify.add(textFieldEnableProperty, 0, 5); /// columna fila
        textFieldEnableProperty.setDisable(true);
        textFieldEnableProperty.setOnKeyPressed((event) -> {
            buttonEnable.setDisable(false);
        });

        buttonEnable = new Button("Habilitar");
        buttonEnable.setTextFill(Color.WHITE);//Color de la letra del boton
        buttonEnable.setStyle("-fx-background-color: BLACK");//Color del fondo
        buttonEnable.setFont(Font.font("Castellar", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 10));//Tipo de letra
        gridPaneModify.add(buttonEnable, 0, 6);
        buttonEnable.setDisable(true);
        buttonEnable.setOnAction((event) -> {
            buttonModify.setDisable(false);
            String Enable = textFieldEnableProperty.getText();
            for (int i = 0; i < labelDefineProperties.size(); i++) {
                if (Enable.equals(textFieldDefineProperties.get(i).getText())) {
                    textFieldDefineProperties.get(i).setDisable(false);

                }
            }

        });

        buttonModify = new Button("Modificar");
        buttonModify.setTextFill(Color.WHITE);//Color de la letra del boton
        buttonModify.setStyle("-fx-background-color: BLACK");//Color del fondo
        buttonModify.setFont(Font.font("Castellar", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 10));//Tipo de letra
        buttonModify.setDisable(true);
        gridPaneModify.add(buttonModify, 0, 8);
        buttonModify.setOnAction((event) -> {

            object = new edu.ucr.rp.appmanejodeinventarios.Logic.Object(comboBoxTool.getValue().toString(),
                    saveObject.linesAccount(comboBoxTool.getValue().toString()) - 2, textFieldDefineProperties, labelDefineProperties);
            search.removeLineFromFile(comboBoxTool.getValue().toString(), textFieldEnableProperty.getText());
            saveObject.writeFileProperties(object);
            constantsElements.soundPlayer("noti");
            constantsElements.alertInformation("Propiedad(es) modificada(s) correctamente");
            gridPaneModify.getChildren().removeAll(textFieldDefineProperties);
            gridPaneModify.getChildren().removeAll(labelDefineProperties);
            textFieldGetProperties.clear();
            textFieldGetProperties.setDisable(true);
            textFieldEnableProperty.clear();
            textFieldEnableProperty.setDisable(true);
            buttonEnable.setDisable(true);
            buttonFill.setDisable(true);
            buttonModify.setDisable(true);
            comboBoxTool.setDisable(false);
        });

        Button buttonClose = new Button("Cerrar");
        buttonClose.setTextFill(Color.WHITE);//Color de la letra del boton
        buttonClose.setStyle("-fx-background-color: BLACK");//Color del fondo
        buttonClose.setFont(Font.font("Castellar", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 10));//Tipo de letra
        gridPaneModify.add(buttonClose, 1, 8);
        buttonClose.setOnAction((event) -> {
            gridPaneModify.getChildren().clear();
            gridPaneModify.setBackground(Background.EMPTY);
        });

        borderPaneModifyProperties.setTop(gridPaneModify);

        return borderPaneModifyProperties;
    }//end  modifyProperties()

    /**
     * metodo que contiene la funcionalidad del boton llenar
     */
    public void fillFunctionality() {
        StringTokenizer stringTokenizer = new StringTokenizer(search.readLine(comboBoxTool.getValue().toString(), textFieldGetProperties.getText()), "|");
        int checkTokens = 0;
        position = 0;
        int labelNumber = 0;
        int textFieldNumber = 0;

        labelProperties = new ArrayList<>();
        textFieldProperties = new ArrayList<>();

        while (stringTokenizer.hasMoreTokens()) {

            if (checkTokens == 0) {

                stringTokenizer.nextToken();

            } else if (checkTokens > 0 && checkTokens < graphicalUserInterfaceLogic.tokensAccount(search.readLine(comboBoxTool.getValue().toString(),
                    textFieldGetProperties.getText()))) {

                if (position % 2 == 0) {
                    Label newLabel = new Label();
                    newLabel.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 20));
                    newLabel.setTextFill(Color.WHITE);
                    newLabel.setStyle("-fx-background-color: rgb(41, 75, 152);");
                    newLabel.setText(stringTokenizer.nextToken());
                    labelProperties.add(position, newLabel);
                    labelDefineProperties.add(labelProperties.get(labelNumber));
                    labelNumber++;
                    textFieldProperties.add(position, null);
                    textFieldNumber++;
                }//end if
                else {
                    TextField newTextField = new TextField(stringTokenizer.nextToken());
                    newTextField.setDisable(true);
                    newTextField.setStyle(
                            "-fx-background-color: lightblue; "
                            + "-fx-background-insets: 4; "
                            +// tamano
                            "-fx-background-radius: 4; "
                            +// tamano
                            "-fx-effect: dropshadow(three-pass-box, blue, 20, 0, 0, 0);");
                    textFieldProperties.add(position, newTextField);
                    textFieldDefineProperties.add(textFieldProperties.get(textFieldNumber));
                    textFieldNumber++;
                    labelProperties.add(position, null);
                    labelNumber++;
                }//end else

                if (position % 2 == 1) {
                    gridPaneModify.add(textFieldProperties.get(position), 3, position - 1 + 5);
                } //end if
                else {
                    gridPaneModify.add(labelProperties.get(position), 2, position + 5);

                }//end else

                position++;
            }//end else if

            checkTokens++;
        }//while
    }//end fillFunctionality 

}// end ModifyProperties 