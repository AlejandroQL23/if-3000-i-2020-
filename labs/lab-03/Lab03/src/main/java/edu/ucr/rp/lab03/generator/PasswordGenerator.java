
package edu.ucr.rp.lab03.generator;

import java.util.Arrays;
import java.util.Collections;


public class PasswordGenerator {

    private Integer minSpecialChars;
    private Integer minUpperCase;
    private Integer minNumbers;
    private Integer minLength;
    private Integer maxLength;
    private char[] specialChars;

    public String[] carac = {"*", "@", "#", "%", "$"};// establecemos los caracteres por defecto
    public int numbers[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
    public String Uppers[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "Ã‘", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    public String Lowers[] = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "Ã±", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    public int randomNums = (int) (Math.random() * numbers.length - 1);
    public int randomUppers = (int) (Math.random() * Uppers.length - 1);
    public int faltantes = 0;

    public PasswordGenerator(Integer minSpecialChars, Integer minUpperCase, Integer minNumbers, Integer minLength, Integer maxLength, char[] specialChars) {
        this.minSpecialChars = minSpecialChars;
        this.minUpperCase = minUpperCase;
        this.minNumbers = minNumbers;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.specialChars = specialChars;
    }// end public passwordGenerator

    public String generate() {
        String password = "";// en esta variable es donde concatenaremos la contraseÃ±a final

        // ciertas validaciones a tomar en cuenta en el ingeso de los datos
        if (minLength >= maxLength) {
            return "ALERTA!: No se puede realizar esta contraseÃ±a, revice los valores de tamaÃ±o y ejecute de nuevo";
        }// end if
        else if (minNumbers > maxLength || minUpperCase > maxLength || minSpecialChars > maxLength) {
            return "ALERTA!: No coinciden las cantidades entre caractares pedidos y el tamaÃ±o maximo ingresado,revice los valores de tamaÃ±o y ejecute de nuevo";
        }//end else if 
        // else if (minSpecialChars > specialChars.length)
        //     return "La cantidad de caracteres especiales que necesita, no coincide con la cantidad de caracteres que tenemos disponibles";
        else {
            int x = (minNumbers + minSpecialChars + minUpperCase); //suma las cantidades de caracteres necesitadas por el usuario
            int y = minLength - x; //calcula si faltan valores
            faltantes = y + (int) (Math.random() * (maxLength - x) - 1);// juega con los valores faltantes y el tamaÃ±o maximo

            for (int i = 0; i < minNumbers; i++) {// controla la cantidad de numeros solicitados
                password += numbers[(int) (Math.random() * numbers.length - 1)];
            }// end primer for

            for (int i = 0; i < minUpperCase; i++) {// controla las mayusculas solicitadas
                password += Uppers[(int) (Math.random() * Uppers.length - 1)];
            }// end segundo for

            for (int i = 0; i < minSpecialChars; i++) {// controla elos caracteres especiales solicitados 
                password += carac[(int) (Math.random() * carac.length - 1)];
            }// end tercer for

            for (int i = 0; i < faltantes; i++) {// para llenar con minusculas, si es que hace falta llenar espacios 
                password += Lowers[(int) (Math.random() * Lowers.length - 1)];
            }// end cuarto for

            String arrayPassword[] = password.split("");
            Collections.shuffle(Arrays.asList(arrayPassword));
            password = "";
            for (int i = 0; arrayPassword.length > i; i++) {
                password = password + arrayPassword[i];
            }//end for 

            return password;

        }// end else

    }//TODO = por hacer

}// end public class passwordGenerator 
