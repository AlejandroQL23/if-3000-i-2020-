package edu.ucr.rp.builderpatterndemo2.generator;

public class passwordGeneratorBuilder {

    private Integer minSpecialChars;
    private Integer minUpperCase;
    private Integer minNumbers;
    private Integer minLength;
    private Integer maxLength;
    private char[] specialChars;

    //Estos metodos son para agilizar/facilitar la implementacion de nuevos parametros/variables
    public passwordGeneratorBuilder withMinSpecialChars(Integer minSpecialChars) {
        this.minSpecialChars = minSpecialChars;
        return this;
    }// end passwordGeneratorBuilder withMinSpecialChars

    public passwordGeneratorBuilder withMinUpperCase(Integer minUpperCase) {
        this.minUpperCase = minUpperCase;
        return this;
    }// end passwordGeneratorBuilder withMinUpperCase

    public passwordGeneratorBuilder withMinNumbers(Integer minNumbers) {
        this.minNumbers = minNumbers;
        return this;
    }// end passwordGeneratorBuilder withMinNumbers

    public passwordGeneratorBuilder withMinLength(Integer minLength) {
        this.minLength = minLength;
        return this;
    }// end passwordGeneratorBuilder withMinLength

    public passwordGeneratorBuilder withSpecialChars(char[] specialChars) {
        this.specialChars = specialChars;
        return this;
    }// end passwordGeneratorBuilder withSpecialChars

    public passwordGeneratorBuilder withMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
        return this;
    }// end passwordGeneratorBuilder withMaxLength

    public passwordGenerator build() {
        if (maxLength == null) {
            maxLength = Integer.MAX_VALUE;
        }
        return new passwordGenerator(minSpecialChars, minUpperCase, minNumbers, minLength, maxLength, specialChars);
    }// end passwordGenerator build()

}// end public class passwordGeneratorBuilder
