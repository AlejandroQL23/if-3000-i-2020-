package edu.ucr.rp.appmanejodeinventarios.Logic;

import java.util.StringTokenizer;
import javafx.scene.control.ComboBox;


public class GraphicalUserInterfaceLogic {
        Search search = new Search();

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
        search.printFiles();
        for (int i = 0; i < search.counter(); i++) {
            if (!received.getItems().contains(search.catalogList.get(i))) {
                received.getItems().addAll(search.catalogList.get(i));
            }//end if
        }//end for
    }//end createComboBox
    
}//end GraphicalUserInterfaceLogic


