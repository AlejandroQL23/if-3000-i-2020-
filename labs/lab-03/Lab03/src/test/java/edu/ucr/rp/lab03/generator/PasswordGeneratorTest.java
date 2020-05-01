package edu.ucr.rp.lab03.generator;

import java.util.stream.Stream;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Ale
 */
public class PasswordGeneratorTest {

    PasswordGeneratorBuilder builder = new PasswordGeneratorBuilder();

    @Before   // para reiniciar precondiciones
    public void setUp() {

        builder
                .withSpecialChars(new char[]{'#', '@', ')'})
                .withMinNumbers(1)
                .withMinLength(8)
                .withMinSpecialChars(1)
                .withMinUpperCase(1);

    }

    /**
     * Scenario: Generar un password con al menos 1 mayúscula - Given: Cantidad
     * mínima de mayúscula -> Precondiciones
     *
     * - When: Generamos el password -> Acción a probar
     *
     * - Then: El password contiene al menos mayúscula -> Validaciones
     */
   // @Test
    public void givenAnUppercaseAPasswordIsGenerated() {
        builder.withSpecialChars(new char[]{'#'});
        PasswordGenerator passwordGenerator = builder.build();

        String password = passwordGenerator.generate();
        Assert.assertTrue("Invalid number of uppercase", getUpperCases(password) >= 2);
    }

    private long getUpperCases(String password) {
        //asde@$%f ->['a','s','d','e','@','$','%','f']
        return password.chars().filter(letter -> Character.isUpperCase(letter)).count();
    }

    /**
     * Scenario: Generar un password con al menos 1 caracter especial
     *
     * - Given: Cantidad mínima de un caracter especial
     *
     * - And: una lista de caracters
     *
     * - When: Generamos el password
     *
     * - Then: El password contiene al menos un caracter especial de la lista de
     * caracters
     */
   // @Test
    public void givenAnEspecialCharAPasswordIsGenerated() {

        PasswordGenerator passwordGenerator = builder.build();

        String password = passwordGenerator.generate();
        Assert.assertTrue("Invalid number of uppercase", getEspecialChar(password) >= 1);
        Assert.assertTrue("Invalid special char", containsSpecialChar(password, '#', '@', ')'));
    }

    private long getEspecialChar(String password) {
        //asde@$%f ->['a','s','d','e','@','$','%','f']
        return password.chars().filter(letter -> Character.isUpperCase(letter)).count();
    }

    private boolean containsSpecialChar(String password, Character... specialChar) {
        return password.chars()
                .filter(it -> Stream.of(specialChar).filter(param -> it == param)
                .findAny().isPresent()).findAny().isPresent();
    }
// otra forma de hacerlo 
//        private long getSpecialChar (String password){
//        int count = 0;
//        //{'@','&','#'}
//            for(int i=0;i<password.length(); i++){
//                if(password.charAt(i)=='@'||
//                password.charAt(i)=='&'||
//                password.charAt(i)=='#'){
//                count++;
//                }
//            }
//        return count;
//    }
}
