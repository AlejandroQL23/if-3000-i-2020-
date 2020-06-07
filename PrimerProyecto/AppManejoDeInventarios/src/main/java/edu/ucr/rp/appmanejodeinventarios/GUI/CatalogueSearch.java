package edu.ucr.rp.appmanejodeinventarios.GUI;

import edu.ucr.rp.appmanejodeinventarios.Logic.Search;
import edu.ucr.rp.appmanejodeinventarios.Logic.GraphicalUserInterfaceLogic;
import java.util.*;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import static edu.ucr.rp.appmanejodeinventarios.Logic.ConstantsElements.*;

public class CatalogueSearch {

    Search search = new Search();
    GraphicalUserInterfaceLogic graphicalUserInterfaceLogic = new GraphicalUserInterfaceLogic();
    TextField textFieldProperties = new TextField();
    ComboBox comboBoxTool = new ComboBox();
    Label labelName = new Label();
    GridPane gridPaneSearch = new GridPane();
    List<Label> labelProperties;
    Button buttonSearch;
    String property = "";
    int position = 0;

    /**
     * 
     * @return Nos da la GUI que nos permite buscar en un catálogo
     */
    public BorderPane catalogueSearching() {

        BorderPane borderPaneCatalogueSearching = new BorderPane();
        borderPaneCatalogueSearching.setPrefSize(BORDER_WIDTH, BORDER_HIGH);

        gridPaneSearch.setMinSize(GRID_WIDTH, GRID_HIGH);
        gridPaneSearch.setVgap(8);   //espacio
        gridPaneSearch.setHgap(8);    // espacio
        gridPaneSearch.setAlignment(Pos.CENTER);
        gridPaneSearch.setStyle(("-fx-background-image:url('file:src/image/Bus.jpg');"
                + "-fx-background-repeat : no-repeat;"
                + "-fx-background-size: 920 920, 20 20, 20 20, 20 20, auto;"));

        comboBoxTool.setValue("Herramientas");
        comboBoxTool.setStyle("-fx-background-color: lightblue");
        gridPaneSearch.add(comboBoxTool, 0, 0);
        graphicalUserInterfaceLogic.createComboBox(comboBoxTool);
        comboBoxTool.setOnMouseClicked((event) -> {

            textFieldProperties.setDisable(false);
        });

        textFieldProperties.setPromptText("Nombre de la propiedad");
        textFieldProperties.setStyle(
                "-fx-background-color: lightblue; "
                + "-fx-background-insets: 4; "
                +// tamano
                "-fx-background-radius: 4; "
                +// tamano
                "-fx-effect: dropshadow(three-pass-box, blue, 20, 0, 0, 0);");
        textFieldProperties.setFocusTraversable(false);
        textFieldProperties.setDisable(true);
        gridPaneSearch.add(textFieldProperties, 0, 5); /// columna fila

        textFieldProperties.setOnKeyPressed((event) -> {

            buttonSearch.setDisable(false);
            labelName.setText("");
            gridPaneSearch.getChildren().removeAll(labelProperties);
        });

        buttonSearch = new Button("Buscar");
        buttonSearch.setTextFill(Color.WHITE);//Color de la letra del boton
        buttonSearch.setStyle("-fx-background-color: BLACK");//Color del fondo
        buttonSearch.setFont(Font.font("Castellar", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 10));//Tipo de letra
        gridPaneSearch.add(buttonSearch, 0, 6);
        buttonSearch.setDisable(true);
        buttonSearch.setOnAction((event) -> {
            searchFuncionality();
            textFieldProperties.setDisable(true);
            buttonSearch.setDisable(true);
        });//end funcionalidad del boton buscar

        Button buttonClose = new Button("Cerrar");
        buttonClose.setTextFill(Color.WHITE);//Color de la letra del boton
        buttonClose.setStyle("-fx-background-color: BLACK");//Color del fondo
        buttonClose.setFont(Font.font("Castellar", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 10));//Tipo de letra
        gridPaneSearch.add(buttonClose, 0, 7);
        buttonClose.setOnAction((event) -> {
            gridPaneSearch.getChildren().clear();
            gridPaneSearch.setBackground(Background.EMPTY);  //limpia color para que quede el color
        });//end btn cerrar
        borderPaneCatalogueSearching.setTop(gridPaneSearch);
        return borderPaneCatalogueSearching;
    }//end BorderPane catalogueSearching()

    /**
     * Metodo que contiene la funcionalidad del boton buscar
     */
    private void searchFuncionality() {

        if (search.exist(comboBoxTool.getValue().toString() + "") != false) {
            StringTokenizer stringTokenizer = new StringTokenizer(search.readLine(comboBoxTool.getValue().toString(), textFieldProperties.getText()), "|");
            int checkTokens = 0;
            position = 0;
            labelProperties = new ArrayList<Label>();

            while (stringTokenizer.hasMoreTokens()) {
                if (checkTokens == 0) {
                    labelName = new Label("Primer nombre: " + stringTokenizer.nextToken());
                    gridPaneSearch.add(labelName, 1, 3);
                }//end if medio
                else if (checkTokens > 0 && checkTokens < graphicalUserInterfaceLogic.tokensAccount(search.readLine(comboBoxTool.getValue().toString(),
                        textFieldProperties.getText()))) {
                    Label labelAdd = new Label();
                    labelAdd.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 20));
                    labelAdd.setTextFill(Color.WHITE);
                    labelAdd.setStyle("-fx-background-color: rgb(41, 75, 152);");
                    labelAdd.setText(property);
                    labelProperties.add(position, labelAdd);
                    if (position % 2 == 0) {
                        gridPaneSearch.add(labelProperties.get(position), 2, position - 1 + 5);
                    } //end if interno
                    else {
                        gridPaneSearch.add(labelProperties.get(position), 1, position + 5);
                    }//end else interno
                    labelName.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 20));
                    labelName.setTextFill(Color.WHITE);
                    labelName.setStyle("-fx-background-color: rgb(41, 75, 152);");
                    property = stringTokenizer.nextToken();
                    position++;
                }//end else if
                checkTokens++;
            }//end while
        }//end if grande
        textFieldProperties.clear();
    }//end searchFuncionality

}//edn CatalogueSearch
