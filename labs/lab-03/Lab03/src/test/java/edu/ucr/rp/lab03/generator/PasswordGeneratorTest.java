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
    //*****************************************************************************************
    /**
     * Primer test Scenario: Generar un password que se complete con letras
     * minusculas
     *
     * - Given: Cantidad mínima de una minuscula
     *
     *
     * - When: Generamos el password : accion
     *
     * - Then: El password rellena el resto del password faltante con minusculas
     */
    // @Test
    // el metodo generado en password generator genera minusculas
    // por defecto, asi que se valida que sean minimo 3
    public void givenALowercaseAPasswordIsGenerated() {
        PasswordGenerator passwordGenerator = builder.build();

        String password = passwordGenerator.generate();
        Assert.assertTrue("Invalid number of Lower cases", getLowerCases(password) >= 0);
    }// end givenALowercaseAPasswordIsGenerated

    private long getLowerCases(String password) {

        return password.chars().filter(lower -> Character.isLowerCase(lower)).count();
    }// 
    //*****************************************************************************************  

    /**
     * segundo test - Scenario: Generar un password con al menos 3 numeros
     *
     * - Given: cantidad minima de numeros
     *
     *
     * - When: Generamos el password : accion
     *
     * - Then: El password contiene al menos 3 numeros
     */
    //@Test
    public void givenANumbersIsGenerated() {
        PasswordGenerator passwordGenerator = builder.build();

        String password = passwordGenerator.generate();
        Assert.assertTrue("Invalid amount of numbers", getNumbers(password) >= 3);
    }// end givenANumbersIsGenerated

    private long getNumbers(String password) {

        return password.chars().filter(number -> Character.isDigit(number)).count();

    }//

    //*****************************************************************************************  
    /**
     * Tercer test - Scenario: Generar un password con un maximo de 5 mayusculas
     *
     * - Given: cantidad maxima de mayusculas
     *
     *
     * - When: Generamos el password : accion
     *
     * - Then: El password contiene como maximo 5 letraas
     */
    //@Test
    public void givenAnUppercaseMaxAPasswordIsGenerated() {
        PasswordGenerator passwordGenerator = builder.build();

        String password = passwordGenerator.generate();
        Assert.assertTrue("Invalid number of Upper cases", getMaxUpperCases(password) <= 5);
    }// end givenALowercaseAPasswordIsGenerated

    private long getMaxUpperCases(String password) {

        return password.chars().filter(Upper -> Character.isLowerCase(Upper)).count();
    }// 

    //*****************************************************************************************  
    /**
     * Cuarto test
     * - Scenario: Generar un password con un maximo de 2 mayusculas
     * y 3 numeros
     *
     * - Given: cantidad maxima de mayusculas y de numeros
     *
     * - When: Generamos el password : accion
     *
     * - Then: El password contiene como maximo 2 letras y 3 numeros
     */
    //@Test
    public void givenAnUppercaseAndNumbersMaxAPasswordIsGenerated() {
        PasswordGenerator passwordGenerator = builder.build();

        String password = passwordGenerator.generate();
        Assert.assertTrue("Invalid number of Upper cases", getMaxUpperCases(password) <= 2);
        Assert.assertTrue("Invalid number of number", getMaxNumbersCases(password) <= 3);
    }// end givenALowercaseAPasswordIsGenerated

    private long getMaxUpperCasess(String password) {

        return password.chars().filter(Upper -> Character.isLowerCase(Upper)).count();

    }//

    private long getMaxNumbersCases(String password) {

        return password.chars().filter(number -> Character.isLowerCase(number)).count();

    }// 

}
