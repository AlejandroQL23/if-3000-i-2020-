package edu.ucr.rp.server.Logic;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

public class Search {

    public ArrayList<String> catalogList = new ArrayList();
    public int counter = 0;
    String file = "ArchTexto//";

    /**
     * Método que analiza si un archivo existe
     *
     * @param fileName recibe el nombre del archivo que será analizado
     * @return retorna true si existe y false si no existe
     */
    public boolean exist(String fileName) {
        File file = new File("ArchTexto//" + fileName + " propiedades.txt");
        if (file.exists()) {
            return true;
        }
        return false;
    }//end exist()

    /**
     * Método que lee la las líneas para buscar un String específico
     *
     * @param fileName recibe el nombre del archivo para buscar especificamente
     * en un archivo
     * @param searcher recibe el String a ser buscado línea a línea
     * @return retorna la línea en la que se encuentra el String a buscar
     */
    public String readLine(String fileName, String searcher) {
        String lineKeeper = "";

        File newFile = new File("ArchTexto//" + fileName + " propiedades.txt");
        try {
            FileInputStream fileInputStream = new FileInputStream(newFile);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String currentRegistry = bufferedReader.readLine();

            while (currentRegistry != null) {

                if (currentRegistry.contains(searcher)) {
                    lineKeeper = currentRegistry;
                }
                currentRegistry = bufferedReader.readLine();
            }
        } catch (FileNotFoundException fileNotFoundException) {
            JOptionPane.showMessageDialog(null, fileNotFoundException + "\nProblemas con el archivo");
        } catch (IOException IOException) {
            JOptionPane.showMessageDialog(null, IOException + "\nProblemas con el archivo");
        }

        return lineKeeper;
    }//end readLine()

    /**
     * Método que remueve una propiedad del archivo, se utiliza para modificar
     *
     * @param fileName recibe el nombre del archivo que será modificado y de
     * donde se eliminará la línea especifica
     * @param lineToRemove recibe el String que valida cuál línea será eliminada
     */
    public void removeLineFromFile(String fileName, String lineToRemove) {
        ArrayList<String> linesList = new ArrayList<>();

        File previousFile = new File("ArchTexto//" + fileName + " propiedades.txt");
        try {
            FileInputStream fileInputStream = new FileInputStream(previousFile);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String currentRegistry = bufferedReader.readLine();

            while (currentRegistry != null) {
                if (!currentRegistry.contains(lineToRemove)) {
                    linesList.add(currentRegistry);
                }
                currentRegistry = bufferedReader.readLine();
            }
            previousFile.deleteOnExit();
        } catch (FileNotFoundException fileNotFoundException) {
            JOptionPane.showMessageDialog(null, fileNotFoundException + "\nProblemas con el archivo");
        } catch (IOException IOException) {
            JOptionPane.showMessageDialog(null, IOException + "\nProblemas con el archivo");
        }
        File fileNew = new File("ArchTexto//" + fileName + " propiedades.txt");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileNew);
            PrintStream printStream = new PrintStream(fileOutputStream);
            for (int i = 0; i < linesList.size(); i++) {
                printStream.println(linesList.get(i));
            }
        } catch (FileNotFoundException fileNotFoundException) {
            JOptionPane.showMessageDialog(null, fileNotFoundException + "\nProblemas con el archivo");
        }
    }//end removeLineFromFile()

    /**
     * Metodo para traer los nombres de los txt existentes, solo los catalogos,
     * los almacena en un ArrayList global
     */
    public void printFiles() {
        File folder = new File(file);
        File[] fileNames = folder.listFiles();
        for (File file : fileNames) {

            try {
                if (file.getCanonicalPath().contains("catalogo.txt")) {
                    String name = file.getName();
                    name = name.replaceAll(" catalogo.txt", "");
                    catalogList.add(name);
                    counter++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     *
     * @return retorna la cantidad de elementos que hay en el Arraylist, la
     * cantidad que han sido agregadas
     */
    public int counter() {
        return counter;
    }//end counter()

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

}
