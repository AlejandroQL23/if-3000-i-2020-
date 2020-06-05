package edu.ucr.rp.appmanejodeinventarios.Logic;

import java.util.StringTokenizer;
import javafx.scene.control.ComboBox;


public class GraphicalUserInterfaceLogic {
        ListFile listFile = new ListFile();

    /**
     * Cuenta los tokens que hay por linea
     *
     * @param line
     * @return tamaño
     */
    public int tokensAccount(String line) {
        int sizeLine = 0;
        String currentRegistry = line;
        StringTokenizer StringTokenizer = new StringTokenizer(currentRegistry, "|");
        while (StringTokenizer.hasMoreTokens()) {
            StringTokenizer.nextToken();
            sizeLine++;
        }//end while
        return sizeLine;
    }//end sizeE

    /**
     * Metodo que define la informacion que se mostrara en el comboBox
     *
     * @param received
     */
    public void createComboBox(ComboBox received) {
        listFile.printFiles();
        for (int i = 0; i < listFile.counter(); i++) {
            if (!received.getItems().contains(listFile.catalogueList.get(i))) {
                received.getItems().addAll(listFile.catalogueList.get(i));
            }//end if
        }//end for
    }//end createComboBox
    
}//end GraphicalUserInterfaceLogic


