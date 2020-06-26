package edu.ucr.rp.server.Logic;

import edu.ucr.rp.server.Logic.Search;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.StringTokenizer;
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

    public ArrayList<String> createComboBox() {
        search.printFiles();
        int amount = 0;
        ArrayList<String> salida = new ArrayList<String>();
        System.out.println(search.counter + "Soy el contador");
        for (int i = 0; i < search.counter(); i++) {
            // if (!received.getItems().contains(search.catalogList.get(i))) {
            //     received.getItems().addAll(search.catalogList.get(i));
            //     System.out.println( received.getItems().addAll(search.catalogList.get(i)));
            // }//end if
            salida.add(search.catalogList.get(i) + "");
            amount++;
        }//end for
        salida.add(amount + "");
        return salida;
    }//end createComboBox

    public String deleteFiles(String fileName) {  //Se da cambio de void a String porque daba problema
        //  constantsElements.soundPlayer("aviso");
        //  constantsElements.alertConfirmation("Esta seguro derealizar esta accion?");
        //   Optional<ButtonType> result = constantsElements.alertConfirmation("").showAndWait();
        //   if (result.get() == ButtonType.OK) {
        File puntoCatalogo = new File("ArchTexto//" + fileName + " catalogo.txt");
        //       File puntoPropiedades = new File("ArchTexto//" + fileName + " propiedades.txt");  //--->> cuando se tenga se descomenta
        puntoCatalogo.delete();
        //      puntoPropiedades.delete();   //--->> Cunado se tenga se descomenta
//            if (!puntoPropiedades.exists() && !puntoCatalogo.exists()) {
//             //   constantsElements.soundPlayer("exito");
//              //  constantsElements.alertInformation("El elemento ingresado se borro con exito");
//            } else {
//                constantsElements.alertWarning("No se pudo borrar el archivo, intente de nuevo");
//            }
        //       }
        return "";

    }//end deleteFiles()

    public String showContent(String name) throws FileNotFoundException, IOException {
        String keeper = "";
        File newFile = new File("ArchTexto//" + name + " catalogo.txt");  // -- > Cambiar a propiedades.txt en este momento no esta
        FileReader fileReader = new FileReader(newFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String currentRegistry = bufferedReader.readLine();
        while (currentRegistry != null) {
            if (currentRegistry.contains(name)) {
                keeper += currentRegistry + "\n \n";
            }
            currentRegistry = bufferedReader.readLine();
        }
        return keeper;
    }//end showContent()
    
    
    
    public int linesAccount(String fileName) {
        int sizeLine = 0;
        File newFile = new File("ArchTexto//" + fileName + " catalogo.txt");
        try {
            FileInputStream fileInputStream = new FileInputStream(newFile);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader); //la unica clase cuyo objeto habilita el readLine
            String currentRegistry = bufferedReader.readLine();
            StringTokenizer stringTokenizer = new StringTokenizer(currentRegistry, "|");
            while (stringTokenizer.hasMoreTokens()) {
                stringTokenizer.nextToken();
                sizeLine++;
            }//end while
        }//end try//end try
        catch (FileNotFoundException fileNotFoundException) {
            JOptionPane.showMessageDialog(null, fileNotFoundException + "\nProblemas con el archivo");
        } catch (IOException IOException) {
            JOptionPane.showMessageDialog(null, IOException + "\nProblemas con el archivo");
        }//end catch's
        return sizeLine;
    }//end linesAccount

    public ArrayList<String> readProperties(String fileName, int sizeLine) {
        ArrayList<String> propertiesKeeper = new ArrayList<>();
        File newFile = new File("ArchTexto//" + fileName + " catalogo.txt");
        try {
            FileInputStream fileInputStream = new FileInputStream(newFile);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String currentRegistry = bufferedReader.readLine();
            StringTokenizer stringTokenizer = new StringTokenizer(currentRegistry, "|");
            int checkTokens = 0;
            while (stringTokenizer.hasMoreTokens()) {
                if (checkTokens > 0 && checkTokens < sizeLine - 1) {
                    propertiesKeeper.add(stringTokenizer.nextToken());
                } //end if
                else {
                    stringTokenizer.nextToken();
                }//end else
                checkTokens++;
            }//end while
        }//end try//end try//end try//end try//end try//end try//end try//end try
        catch (FileNotFoundException fileNotFoundException) {
            System.out.println(fileNotFoundException + ": Problemas con el archivo");
        } catch (IOException IOException) {
            System.out.println(IOException + ": Problemas con el archivo");
        }//end catch's
        return propertiesKeeper;
    }// end readProperties

    public void writeFileProperties(String recived) {
        StringTokenizer stringTokenizer = new StringTokenizer(recived, "|");
        String name = stringTokenizer.nextToken();
        stringTokenizer.nextToken();
        String properties = stringTokenizer.nextToken();
        String atributes = stringTokenizer.nextToken();
        File newFile = new File("ArchTexto//" + name + " propiedades.txt");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(newFile, true);
            PrintStream printStream = new PrintStream(fileOutputStream);
            printStream.print(name + "|");
            printStream.print(properties + "|");
            printStream.print(atributes + "|");
            printStream.println(" "); //cambio sin object.getTam
        }//end try//end try//end try//end try
        catch (FileNotFoundException fileNotFoundException) {
            JOptionPane.showMessageDialog(null, fileNotFoundException + "\nProblemas con el archivo");
        }//end catch
    }//end writeFileCatalogue()
    

}
