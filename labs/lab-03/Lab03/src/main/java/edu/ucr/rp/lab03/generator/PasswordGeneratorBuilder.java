package edu.ucr.rp.lab03.generator;

public class PasswordGeneratorBuilder {

    private Integer minSpecialChars;
    private Integer minUpperCase;
    private Integer minNumbers;
    private Integer minLength;
    private Integer maxLength;
    private char[] specialChars;

    //Estos metodos son para agilizar/facilitar la implementacion de nuevos parametros/variables
    public PasswordGeneratorBuilder withMinSpecialChars(Integer minSpecialChars) {
        this.minSpecialChars = minSpecialChars;
        return this;
    }// end passwordGeneratorBuilder withMinSpecialChars

    public PasswordGeneratorBuilder withMinUpperCase(Integer minUpperCase) {
        this.minUpperCase = minUpperCase;
        return this;
    }// end passwordGeneratorBuilder withMinUpperCase

    public PasswordGeneratorBuilder withMinNumbers(Integer minNumbers) {
        this.minNumbers = minNumbers;
        return this;
    }// end passwordGeneratorBuilder withMinNumbers

    public PasswordGeneratorBuilder withMinLength(Integer minLength) {
        this.minLength = minLength;
        return this;
    }// end passwordGeneratorBuilder withMinLength

    public PasswordGeneratorBuilder withSpecialChars(char[] specialChars) {
        this.specialChars = specialChars;
        return this;
    }// end passwordGeneratorBuilder withSpecialChars

    public PasswordGeneratorBuilder withMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
        return this;
    }// end passwordGeneratorBuilder withMaxLength

    public PasswordGenerator build() {
        if (maxLength == null) {
            maxLength = Integer.MAX_VALUE;
        }
        return new PasswordGenerator(minSpecialChars, minUpperCase, minNumbers, minLength, maxLength, specialChars);
    }// end passwordGenerator build()

}// end public class passwordGeneratorBuilder
