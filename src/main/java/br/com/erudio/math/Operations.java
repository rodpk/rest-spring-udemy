package br.com.erudio.math;

import br.com.erudio.utils.DoubleUtils;

public class Operations {
    
    DoubleUtils doubleUtils = new DoubleUtils();

    public Double sum(String numberOne, String numberTwo) {
        return doubleUtils.convertToDouble(numberOne) + doubleUtils.convertToDouble(numberTwo);
    }

    public Double sub(String numberOne, String numberTwo) {
        return doubleUtils.convertToDouble(numberOne) - doubleUtils.convertToDouble(numberTwo);
    }

    public Double multi(String numberOne, String numberTwo) {
        return doubleUtils.convertToDouble(numberOne) * doubleUtils.convertToDouble(numberTwo);
    }

    public Double div(String numberOne, String numberTwo) {
        return doubleUtils.convertToDouble(numberOne) / doubleUtils.convertToDouble(numberTwo);
    }

    public Double average(String numberOne, String numberTwo) {
        return (doubleUtils.convertToDouble(numberOne) + doubleUtils.convertToDouble(numberTwo)) / 2;
    }

    public Double squareRoot(String numberOne) {
        return Math.sqrt(doubleUtils.convertToDouble(numberOne));
    }


}
