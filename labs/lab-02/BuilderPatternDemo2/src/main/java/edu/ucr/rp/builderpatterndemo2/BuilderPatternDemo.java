package edu.ucr.rp.builderpatterndemo2;

import edu.ucr.rp.builderpatterndemo2.generator.passwordGenerator;
import edu.ucr.rp.builderpatterndemo2.generator.passwordGeneratorBuilder;

public class BuilderPatternDemo {

    public static void main(String... args) {

//Se llenan los datos de los metodos para crear la contrasena segun especificaiones
        passwordGeneratorBuilder builder = new passwordGeneratorBuilder();
        passwordGenerator generator = builder.withMinLength(8)
                .withMaxLength(10)
                .withMinNumbers(3)
                .withMinSpecialChars(4)
                .withMinUpperCase(3)
                .withSpecialChars(new char[]{'*', '@', '#', '%', '$'})
                .build();

        System.out.println(generator.generate());

    }// end public static void main(String... args)
}// end public class BuilderPatternDemo
