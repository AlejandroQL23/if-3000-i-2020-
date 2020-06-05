package edu.ucr.rp.appmanejodeinventarios.Logic;

import java.io.*;

public class ListArchivist {

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

}//end listRegis
