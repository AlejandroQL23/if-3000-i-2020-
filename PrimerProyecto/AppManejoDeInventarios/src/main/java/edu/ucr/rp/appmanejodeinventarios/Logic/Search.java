package edu.ucr.rp.appmanejodeinventarios.Logic;

import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Search {

    /**
     * Método que analiza si un archivo existe
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
     * @param fileName recibe el nombre del archivo para buscar especificamente en un archivo
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
        }
        catch (FileNotFoundException fileNotFoundException) {
           JOptionPane.showMessageDialog(null, fileNotFoundException+"\nProblemas con el archivo");
        } catch (IOException IOException) {
           JOptionPane.showMessageDialog(null, IOException+"\nProblemas con el archivo");
        }

        return lineKeeper;
    }//end readLine()

    /**
     * Método que remueve una propiedad del archivo, se utiliza para modificar
     * 
     * @param fileName recibe el nombre del archivo que será modificado y de donde se eliminará la línea especifica
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
        }
        catch (FileNotFoundException fileNotFoundException) {
            JOptionPane.showMessageDialog(null, fileNotFoundException+"\nProblemas con el archivo");
        } catch (IOException IOException) {
            JOptionPane.showMessageDialog(null, IOException+"\nProblemas con el archivo");
        }
        File fileNew = new File("ArchTexto//" + fileName + " propiedades.txt");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileNew);
            PrintStream printStream = new PrintStream(fileOutputStream);
            for (int i = 0; i < linesList.size(); i++) {
                printStream.println(linesList.get(i));
            }
        }
        catch (FileNotFoundException fileNotFoundException) {
            JOptionPane.showMessageDialog(null, fileNotFoundException+"\nProblemas con el archivo");
        }
    }//end removeLineFromFile()

   
}//end search
