package edu.ucr.rp.GUI;

import static edu.ucr.rp.GUI.ConstantsElements.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;

public class MainMenuBar {

    About about = new About();
    Credits credits = new Credits();
    Help help = new Help();
    CreateNewCatalog createNewCatalog = new CreateNewCatalog();
    DefineProperties defineProperties = new DefineProperties();
    DeleteCatalogs deleteCatalogs = new DeleteCatalogs();
    CatalogSearch catalogSearch = new CatalogSearch();
    ModifyProperties modifyCatalog = new ModifyProperties();
    ShowPropertiesOfElements showPropertiesOfElements = new ShowPropertiesOfElements();
    ShowCatalog showCatalog = new ShowCatalog();
    
     VBox vBoxWindows, vBoxMain;

     /**
      * 
      * @return Nos da la GUI que contiene todos los elementos por mostrar en la barra de menú
      */
    public Scene getMainScene(){

        vBoxMain = new VBox();
        vBoxMain.setStyle(("-fx-background-image:url('file:src/image/inicio1.jpg');"
                + "-fx-background-repeat : no-repeat;"
                + "-fx-background-size: 920 920, 20 20, 20 20, 20 20, auto;"));

        Scene scene = new Scene(vBoxMain, SCENE_WIDTH, SCENE_HIGH);
        vBoxWindows = new VBox();

        MenuBar menuBarMenu = new MenuBar();
        menuBarMenu.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5);"
                + "-fx-effect: dropshadow(gaussian, red, 50, 0, 0, 0);"
                + "-fx-background-insets: 50;");

        SeparatorMenuItem separator = new SeparatorMenuItem();
        //Menu Sistema
        Menu menuSystem = new Menu("Sistema", new ImageView(new Image("file:src/image/sistema.png")));
        menuSystem.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5);"
                + "-fx-effect: dropshadow(gaussian, red, 50, 0, 0, 0);"
                + "-fx-background-insets: 50;");
        //SubMenu de Sistema
        MenuItem menuItemAbout = new MenuItem("Acerca de", new ImageView(new Image("file:src/image/aD.png")));
        menuItemAbout.setAccelerator(KeyCombination.keyCombination("Ctrl+A"));
        MenuItem menuItemCredits = new MenuItem("Créditos", new ImageView(new Image("file:src/image/ojo.png")));
        menuItemCredits.setAccelerator(KeyCombination.keyCombination("Ctrl+C"));
        MenuItem menuItemPerformance = new MenuItem("Funcionamiento", new ImageView(new Image("file:src/image/preg.png")));
        MenuItem menuItemExit = new MenuItem("Salir", new ImageView(new Image("file:src/image/exit.png")));
        menuItemExit.setAccelerator(KeyCombination.keyCombination("Alt+S"));

        menuItemCredits.setOnAction((event) -> {
            vBoxWindows.getChildren().clear();
            vBoxWindows.getChildren().addAll(credits.getGraphicalUserInterfaceCredits());
        });

        menuItemAbout.setOnAction((event) -> {
            vBoxWindows.getChildren().clear();
            vBoxWindows.getChildren().addAll(about.getGraphicalUserInterfaceAbout());
        });

        menuItemPerformance.setOnAction((event) -> {
            vBoxWindows.getChildren().clear();
            vBoxWindows.getChildren().addAll(help.getGraphicalUserInterfaceHelper());
        });

        menuItemExit.setOnAction((event) -> Platform.exit());

        menuSystem.getItems().addAll(menuItemAbout, menuItemCredits, menuItemPerformance, menuItemExit);//agregados a m_SIstema

        Menu menuInventory = new Menu("Catalogo", new ImageView(new Image("file:src/image/catal.png")));
        menuInventory.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5);"
                + "-fx-effect: dropshadow(gaussian, red, 50, 0, 0, 0);"
                + "-fx-background-insets: 50;");
        MenuItem menuItemDefineCatalog = new MenuItem("Definir Catalogo", new ImageView(new Image("file:src/image/ADD.png")));
        menuItemDefineCatalog.setAccelerator(KeyCombination.keyCombination("Ctrl+D"));
        MenuItem menuItemDefineProperties = new MenuItem("Definir propiedades", new ImageView(new Image("file:src/image/prop.png")));

        menuItemDefineCatalog.setOnAction((event) -> {
            vBoxWindows.getChildren().clear();
            vBoxWindows.getChildren().addAll(createNewCatalog.createCatalog());
        });
        menuItemDefineProperties.setOnAction((event) -> {
            vBoxWindows.getChildren().clear();
            try {
                vBoxWindows.getChildren().addAll(defineProperties.defineProperties());
            } catch (IOException ex) {
                Logger.getLogger(MainMenuBar.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        menuInventory.getItems().addAll(menuItemDefineCatalog, menuItemDefineProperties);//agregados a m_Paises

        Menu menuMaintenance = new Menu("Mantenimiento", new ImageView(new Image("file:src/image/mantenimiento.png")));
        menuMaintenance.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5);"
                + "-fx-effect: dropshadow(gaussian, red, 50, 0, 0, 0);"
                + "-fx-background-insets: 50;");
        MenuItem menuItemDeleteCatalog = new MenuItem("Eliminar catalogos", new ImageView(new Image("file:src/image/elim.png")));
        //Funcionamiento
        menuItemDeleteCatalog.setOnAction((event) -> {
            vBoxWindows.getChildren().clear();
            vBoxWindows.getChildren().addAll(deleteCatalogs.deleteCatalogs());
        });
        menuMaintenance.getItems().addAll(menuItemDeleteCatalog);//agregado a menuMaintenance

        Menu menuReports = new Menu("Reportes", new ImageView(new Image("file:src/image/rep.png")));
        menuReports.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5);"
                + "-fx-effect: dropshadow(gaussian, red, 50, 0, 0, 0);"
                + "-fx-background-insets: 50;");

        Menu menuSubReports = new Menu("ListadosPorReportes", new ImageView(new Image("file:src/image/list.png")));
       // menuReports.getItems().addAll(menuSubReports); //* crear otra variable 
        MenuItem menuItemSearchCatalog = new MenuItem("Busqueda por catalogo", new ImageView(new Image("file:src/image/sear.png")));
        menuItemSearchCatalog.setAccelerator(KeyCombination.keyCombination("Ctrl+V"));
        MenuItem menuItemModifyCatalog = new MenuItem("Modificar catalogo", new ImageView(new Image("file:src/image/edita.png")));
        MenuItem menuItemPropertiesList = new MenuItem("Listado de propiedades por catalogo");
        MenuItem menuItemCatalogsList = new MenuItem("Listado de catalogos");

        menuItemSearchCatalog.setOnAction((event) -> {
            vBoxWindows.getChildren().clear();
            vBoxWindows.getChildren().addAll(catalogSearch.catalogSearching());
        });

        menuItemModifyCatalog.setOnAction((event) -> {
            vBoxWindows.getChildren().clear();
            vBoxWindows.getChildren().addAll(modifyCatalog.modifyProperties());
        });

        menuItemPropertiesList.setOnAction((event) -> {
            vBoxWindows.getChildren().clear();
            vBoxWindows.getChildren().addAll(showPropertiesOfElements.showInformationByCatalog());
        });

        menuItemCatalogsList.setOnAction((event) -> {
            vBoxWindows.getChildren().clear();
            vBoxWindows.getChildren().addAll(showCatalog.showExistingCatalogs());
        });

        menuSubReports.getItems().addAll(menuItemPropertiesList, menuItemCatalogsList);
        menuReports.getItems().addAll(menuItemSearchCatalog, menuItemModifyCatalog, separator, menuSubReports);//agregados a menuReports
        menuBarMenu.setOpacity(0.0);
        menuBarMenu.setOnMouseMoved((event) -> {
            menuBarMenu.setOpacity(0.9);
        });
        menuBarMenu.getMenus().addAll(menuSystem, menuInventory, menuMaintenance, menuReports);
        ((VBox) scene.getRoot()).getChildren().addAll(menuBarMenu, vBoxWindows);
        return scene;

    }//end Scene getMainScene()
}//end class MainMenuBar 
