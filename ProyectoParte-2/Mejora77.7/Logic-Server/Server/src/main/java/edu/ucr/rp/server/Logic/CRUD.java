package edu.ucr.rp.server.Logic;

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

    Search search = new Search();

    /**
     * Metodo para escribir el archivo .catalogo
     *
     * @param object
     */
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

    /**
     * metodo que combierte texto(cliente) en objeto(server)
     *
     * @param recived
     * @return
     */
    public Mercancia passTo(String recived) {

        StringTokenizer stringTokenizer = new StringTokenizer(recived, "|");
        int counterTokens = 0;
        String name = "";
        int prop = 0;
        ArrayList<String> arrayPassTo = new ArrayList<>();  //---> atributo

        while (stringTokenizer.hasMoreTokens()) {
            if (counterTokens == 0) {
                name = stringTokenizer.nextToken();
                counterTokens++;
            } else {

                arrayPassTo.add(stringTokenizer.nextToken());
                counterTokens++;

            }//end else

        }//end while

        prop = Integer.parseInt(recived.substring(recived.length() - 1, recived.length()));
        Mercancia mercancia = new Mercancia(name, prop, arrayPassTo, new ArrayList<>());
        return mercancia;

    }//end passTo

    /**
     * Metodo que facilita la creacion y llenado de comboBox
     *
     * @return
     */
    public ArrayList<String> createComboBox() {
        search.printFiles();
        int amount = 0;
        ArrayList<String> salida = new ArrayList<String>();
        for (int i = 0; i < search.counter(); i++) {
            salida.add(search.catalogList.get(i) + "");
            amount++;
        }//end for
        salida.add(amount + "");
        return salida;
    }//end createComboBox

    /**
     * Metodo que elimina archivos
     *
     * @param fileName
     * @return
     */
    public String deleteFiles(String fileName) {

        File puntoCatalogo = new File("ArchTexto//" + fileName + " catalogo.txt");
        File puntoPropiedades = new File("ArchTexto//" + fileName + " propiedades.txt");
        puntoCatalogo.delete();
        puntoPropiedades.delete();
        return "";

    }//end deleteFiles()

    /**
     * Metodo utilizado en showProperties
     *
     * @param name
     * @return
     * @throws FileNotFoundException
     * @throws IOException
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

    /**
     * Metodo para contar lineas del .catalogo
     *
     * @param fileName
     * @return
     */
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

    public ArrayList<String> linesInProperties(String fileName) {
        ArrayList<String> saved = new ArrayList<>();
        File newFile = new File("ArchTexto//" + fileName + " propiedades.txt");
        try {
            FileInputStream fileInputStream = new FileInputStream(newFile);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader); //la unica clase cuyo objeto habilita el readLine
            String currentRegistry = bufferedReader.readLine();
            while (currentRegistry != null) {
                StringTokenizer stringTokenizer = new StringTokenizer(currentRegistry, "|");
                stringTokenizer.nextToken();
                String adder = "";
                while (stringTokenizer.hasMoreTokens()) {
                    stringTokenizer.nextToken();
                    adder += stringTokenizer.nextToken() + "|";
                }
                saved.add(adder);
                currentRegistry = bufferedReader.readLine();
            }//end while
        }//end try//end try//end try//end try
        catch (FileNotFoundException fileNotFoundException) {
            JOptionPane.showMessageDialog(null, fileNotFoundException + "\nProblemas con el archivo");
        } catch (IOException IOException) {
            JOptionPane.showMessageDialog(null, IOException + "\nProblemas con el archivo");
        }//end catch's
        return saved;
    }//end linesAccount

    /**
     * Metodo que lee las propiedades
     *
     * @param fileName
     * @param sizeLine
     * @return
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

    /**
     * Metodo para escribir el archivo .propiedades
     *
     * @param recived
     */
    public void writeFileProperties(String recived) {
        StringTokenizer stringTokenizer = new StringTokenizer(recived, "|");
        String name = stringTokenizer.nextToken();

        File newFile = new File("ArchTexto//" + name + " propiedades.txt");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(newFile, true);
            PrintStream printStream = new PrintStream(fileOutputStream);
            printStream.println(recived);
        }//end try
        catch (FileNotFoundException fileNotFoundException) {
            JOptionPane.showMessageDialog(null, fileNotFoundException + "\nProblemas con el archivo");
        }//end catch
    }//end writeFileCatalogue()

    /**
     * Leer atributos del .propiedades
     *
     * @param fileName
     * @param propertyName
     * @return
     */
    public ArrayList<String> readAtributes(String fileName, String propertyName) {
        ArrayList<String> atributesKeeper = new ArrayList<>();
        File newFile = new File("ArchTexto//" + fileName + " propiedades.txt");
        try {
            FileInputStream fileInputStream = new FileInputStream(newFile);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String currentRegistry = bufferedReader.readLine();

            int counter = 0;
            StringTokenizer stringTokenizer1 = new StringTokenizer(propertyName, "|");
            stringTokenizer1.nextToken();
            String keeper = stringTokenizer1.nextToken();
            while (currentRegistry != null) {
                if (currentRegistry.contains(keeper)) {
                    StringTokenizer stringTokenizer = new StringTokenizer(currentRegistry, "|");
                    stringTokenizer.nextToken();
                    while (stringTokenizer.hasMoreTokens()) {
                        if (counter % 2 != 0) {
                            atributesKeeper.add(stringTokenizer.nextToken());
                            counter++;
                        } else {
                            stringTokenizer.nextToken();
                            counter++;
                        }

                    }
                }
                currentRegistry = bufferedReader.readLine();
            }
        }//end try
        catch (FileNotFoundException fileNotFoundException) {
            System.out.println(fileNotFoundException + ": Problemas con el archivo");
        } catch (IOException IOException) {
            System.out.println(IOException + ": Problemas con el archivo");
        }//end catch's

        return atributesKeeper;
    }

    /**
     * Metodo para leer informacion de los .catalogos
     *
     * @param recived
     * @return
     */
    public String readCatalog(String recived) {
        String saved = "";
        StringTokenizer stringTokenizer = new StringTokenizer(recived, "|");
        saved = stringTokenizer.nextToken();
        return saved;
    }

    /**
     * Metodo que lee propiedades
     *
     * @param recived
     * @return
     */
    public String readProperty(String recived) {
        String saved = "";
        StringTokenizer stringTokenizer = new StringTokenizer(recived, "|");
        stringTokenizer.nextToken();
        saved = stringTokenizer.nextToken();
        return saved;

    }

    /**
     * Metodo para lectura de atributos
     *
     * @param recived
     * @return
     */
    public String readAtribute(String recived) {
        String saved = "";
        StringTokenizer stringTokenizer = new StringTokenizer(recived, "|");
        while (stringTokenizer.hasMoreTokens()) {
            saved = stringTokenizer.nextToken();
        }
        return saved;

    }

    /**
     * Metodo que ayudara para la modificacion de las propiedades
     *
     * @param fileName
     * @param toModify
     * @param modified
     */
    public void modify(String fileName, String toModify, String modified) {
        ArrayList<String> linesList = new ArrayList<>();

        File previousFile = new File("ArchTexto//" + fileName + " propiedades.txt");
        try {
            FileInputStream fileInputStream = new FileInputStream(previousFile);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String currentRegistry = bufferedReader.readLine();
            while (currentRegistry != null) {
                if (!currentRegistry.contains(toModify)) {
                    linesList.add(currentRegistry);
                }
                currentRegistry = bufferedReader.readLine();
            }//end while
            previousFile.deleteOnExit();
        } //end try
        catch (FileNotFoundException fileNotFoundException) {
            JOptionPane.showMessageDialog(null, fileNotFoundException + "\nProblemas con el archivo");
        } catch (IOException IOException) {
            JOptionPane.showMessageDialog(null, IOException + "\nProblemas con el archivo");
        }//end catch's
        File fileNew = new File("ArchTexto//" + fileName + " propiedades.txt");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileNew);
            PrintStream printStream = new PrintStream(fileOutputStream);
            for (int i = 0; i < linesList.size(); i++) {
                printStream.println(linesList.get(i));
            }//end for
            printStream.println(modified);
        }//end try
        catch (FileNotFoundException fileNotFoundException) {
            JOptionPane.showMessageDialog(null, fileNotFoundException + "\nProblemas con el archivo");
        }//end catch
    }//end removeLineFromFile

}//end CRUD
