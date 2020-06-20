package edu.ucr.rp.server;

import edu.ucr.rp.server.Logic.Search;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javafx.scene.control.ComboBox;
import javax.swing.JOptionPane;

public class CRUD {

    public void writeFileCatalog(Mercancia object) {

        File newFile = new File("ArchTexto//" + object.getName() + " catalogo.txt");

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(newFile, true);
            PrintStream printStream = new PrintStream(fileOutputStream);

            printStream.print(object.getName() + "|");

            for (int i = 0; i < object.getPropertiesQuantity(); i++) {
                printStream.print(object.getProperties().get(i) + "|");
            }
            printStream.print(object.getPropertiesQuantity());

        } catch (FileNotFoundException fileNotFoundException) {
            JOptionPane.showMessageDialog(null, fileNotFoundException + "\nProblemas con el archivo");
        }
    }//end writeFileCatalog()

    public Mercancia passTo(String recived) {

        StringTokenizer stringTokenizer = new StringTokenizer(recived, "|");
        int counterTokens = 0;
        String name = "";
        int prop = 0;
        ArrayList<String> a = new ArrayList<>();  //---> atributo

        while (stringTokenizer.hasMoreTokens()) {
            if (counterTokens == 0) {
                name = stringTokenizer.nextToken();
                counterTokens++;
            } else {

                a.add(stringTokenizer.nextToken());
                counterTokens++;

            }//end else

        }//end while

        prop = Integer.parseInt(recived.substring(recived.length() - 1, recived.length()));

        Mercancia mercancia = new Mercancia(name, prop, a, new ArrayList<>());
        return mercancia;

    }//end passTo

    Search search = new Search();

    public ArrayList<String>  createComboBox() {
        search.printFiles();
        int amount =0;
        ArrayList<String> salida = new ArrayList<String>();
        System.out.println(search.counter+"Soy el contador");
        for (int i = 0; i < search.counter(); i++) {
           // if (!received.getItems().contains(search.catalogList.get(i))) {
           //     received.getItems().addAll(search.catalogList.get(i));
           //     System.out.println( received.getItems().addAll(search.catalogList.get(i)));
           // }//end if
           salida.add(search.catalogList.get(i)+"  ");
           amount++;
        }//end for
        salida.add(amount+"");
        return salida;
    }//end createComboBox

}
