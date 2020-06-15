package com.leoc.sockets.multithread.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
        ArrayList<String> a = new ArrayList<>();

        while (stringTokenizer.hasMoreTokens()) {
            if (counterTokens == 0) {
                name = stringTokenizer.nextToken();
                counterTokens++;
            } else {

                a.add(stringTokenizer.nextToken());
                counterTokens++;

            }

        }

        prop = Integer.parseInt(recived.substring(recived.length() - 1, recived.length()));

        Mercancia mercancia = new Mercancia(name, prop, a, new ArrayList<>());
        return mercancia;

    }//end passTo
    
}
