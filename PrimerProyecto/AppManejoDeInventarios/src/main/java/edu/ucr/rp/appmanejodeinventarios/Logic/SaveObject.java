package edu.ucr.rp.appmanejodeinventarios.Logic;

import java.io.*;
import java.util.*;
import javafx.scene.control.*;
import javax.swing.JOptionPane;

public class SaveObject {

  ConstantsElements constantsElements = new ConstantsElements(); //class
    
    public int sizeLine = 0;

    /**
     * Método para guardar en un archivo.txt el catalogo con sus propiedades,
     * separados por un token "|"
     *
     * @param object recibe el objeto que insertará con su respectivo nombre y
     * propiedades
     */
    public void writeFileCatalog(Object object) {

        File newFile = new File("ArchTexto//" + object.getName() + " catalogo.txt");

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(newFile, true);
            PrintStream printStream = new PrintStream(fileOutputStream);

            printStream.print(object.getName() + "|");

            for (int i = 0; i < object.getPropertiesQuantity(); i++) {
                printStream.print(object.getProperties().get(i).getText() + "|");
            }
            printStream.print(object.getPropertiesQuantity());

        } catch (FileNotFoundException fileNotFoundException) {
            JOptionPane.showMessageDialog(null, fileNotFoundException + "\nProblemas con el archivo");
        }
    }//end writeFileCatalog()

    /**
     * Método para guardar en un archivo.txt las propiedades respectivas de cada
     * catalogo, separados por un token "|"
     *
     * @param object recibe el objeto que insertará con sus respectivas
     * propiedades de cada catalogo
     */
    public void writeFileProperties(Object object) {

        File newFile = new File("ArchTexto//" + object.getName() + " propiedades.txt");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(newFile, true);
            PrintStream printStream = new PrintStream(fileOutputStream);
            printStream.print(object.getName() + "|");
            for (int i = 0; i < object.getPropertiesQuantity(); i++) {
                printStream.print(object.getAttribute().get(i).getText() + "|" + object.getProperties().get(i).getText() + "|"); //por ahora
            }
            printStream.println(" ");
        } catch (FileNotFoundException fileNotFoundException) {
            JOptionPane.showMessageDialog(null, fileNotFoundException + "\nProblemas con el archivo");
        }
    }//end writeFileCatalog()

    /**
     * Método que lee las propiedades de cada archivo
     *
     * @param fileName recibe el nombre del archivo para leer las propiedades de
     * su .catalogo
     * @param sizeLine recibe el tamaño de la línea para poder calcular cuántos
     * tokens hay por línea
     * @return retorna un ArrayList con las propiedades ingresadas, solo recibe las propiedades de manera lógica
     */
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
                } else {
                    stringTokenizer.nextToken();
                }
                checkTokens++;
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println(fileNotFoundException + ": Problemas con el archivo");
        } catch (IOException IOException) {
            System.out.println(IOException + ": Problemas con el archivo");
        }
        return propertiesKeeper;
    }// end readProperties()

    /**
     * Cuenta la cantidad de lineas en cada archivo
     *
     * @param fileName recibe el nombre del archivo que será analizado para poder contar las lineas de cada archivo en especifico
     * @return retorna la cantidad de líneas encontradas en un archivo
     */
    public int linesAccount(String fileName) {
        int sizeLine = 0;
        File newFile = new File("ArchTexto//" + fileName + " catalogo.txt");
        try {
            FileInputStream fileInputStream = new FileInputStream(newFile);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String currentRegistry = bufferedReader.readLine();
            StringTokenizer stringTokenizer = new StringTokenizer(currentRegistry, "|");
            while (stringTokenizer.hasMoreTokens()) {
                stringTokenizer.nextToken();
                sizeLine++;
            }
        }
        catch (FileNotFoundException fileNotFoundException) {
            JOptionPane.showMessageDialog(null, fileNotFoundException + "\nProblemas con el archivo");
        } catch (IOException IOException) {
            JOptionPane.showMessageDialog(null, IOException + "\nProblemas con el archivo");
        }
        return sizeLine;
    }//end linesAccount()

    /**
     * Método que se encarga de eliminar completamente un archivo
     * @param fileName recibe el nombre del archivo que será eliminado
     */
    public void deleteFiles(String fileName) {
        constantsElements.soundPlayer("aviso");
        constantsElements.alertConfirmation("Esta seguro derealizar esta accion?");
        Optional<ButtonType> result = constantsElements.alertConfirmation("").showAndWait();
        if (result.get() == ButtonType.OK) {
            File puntoCatalogo = new File("ArchTexto//" + fileName + " catalogo.txt");
            File puntoPropiedades = new File("ArchTexto//" + fileName + " propiedades.txt");
            puntoCatalogo.delete();
            puntoPropiedades.delete();
            if (!puntoPropiedades.exists() && !puntoCatalogo.exists()) {
                constantsElements.soundPlayer("exito");
                constantsElements.alertInformation("El elemento ingresado se borro con exito");
            }
            else {
                constantsElements.alertWarning("No se pudo borrar el archivo, intente de nuevo");
            }
        }
    }//end deleteFiles()
    
        /**
     * Método para mostrar contenido de los txt existentes
     * 
     * @param name recibe el nombre del respectivo catalogo 
     * @return devuelve el String con el contenido en el archivo
     * @throws FileNotFoundException tira la excepcion si no encuentra el archivo 
     * @throws IOException tira la excepcion de input y output con el archivo
     */
    
    public String showContent(String name) throws FileNotFoundException, IOException {
        String keeper = "";
        File newFile = new File("ArchTexto//" + name + " propiedades.txt");
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

}//end SaveObject
