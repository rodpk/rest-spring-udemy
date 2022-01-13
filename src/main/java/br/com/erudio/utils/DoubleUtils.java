package br.com.erudio.utils;

import br.com.erudio.exception.UnsupportedMathOperationException;

public class DoubleUtils {
    public boolean isNumeric(String strNumber) {
        if (strNumber == null) return false;
        String number = strNumber.replaceAll(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }


    public Double convertToDouble(String strNumber) {
        if (strNumber == null) return 0D;
        String number = strNumber.replaceAll(",", ".");

        if (isNumeric(number)) return Double.parseDouble(number);
        return 0D;
    }


    public void checkIfValueIsValid(String numberOne) {
        if (!this.isNumeric(numberOne)) {
            throw new UnsupportedMathOperationException("Please, set a numeric value.");
        }
    }

    public void checkIfValuesIsValid(String numberOne, String numberTwo) {
        if (!this.isNumeric(numberOne) || !this.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please, set a numeric value.");
        }
    }
}
