package edu.ucr.rp.appmanejodeinventarios.Logic;

import java.io.*;
import java.util.ArrayList;

public class ListFile {

    public ArrayList<String> catalogueList = new ArrayList();
    public int counter = 0;
    String file = "ArchTexto//";

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
                    catalogueList.add(name);
                    counter++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     *
     * @return retorna la cantidad de elementos que hay en el Arraylist, la cantidad que han sido agregadas
     */
    public int counter() {
        return counter;
    }//end counter()

}//end ListFile

