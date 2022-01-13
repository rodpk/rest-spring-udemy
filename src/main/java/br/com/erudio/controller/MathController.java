package br.com.erudio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.math.Operations;
import br.com.erudio.utils.DoubleUtils;

@RestController
public class MathController {

    @Autowired
    DoubleUtils doubleUtils;
    
    @Autowired
    Operations operations;
    //
    // minor add to test token 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

    @RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double sum(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {

        doubleUtils.checkIfValuesIsValid(numberOne, numberTwo);
        return operations.sum(numberOne, numberTwo);

    }

    @RequestMapping(value = "/sub/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double subt(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
        doubleUtils.checkIfValuesIsValid(numberOne, numberTwo);

        return operations.sub(numberOne, numberTwo);
    }

    @RequestMapping(value = "/multi/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double multi(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
        doubleUtils.checkIfValuesIsValid(numberOne, numberTwo);

        return operations.multi(numberOne, numberTwo);
    }

    @RequestMapping(value = "/div/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double div(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
        doubleUtils.checkIfValuesIsValid(numberOne, numberTwo);

        return operations.div(numberOne, numberTwo);
    }

    @RequestMapping(value = "/average/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double average(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
        doubleUtils.checkIfValuesIsValid(numberOne, numberTwo);
        return operations.average(numberOne, numberTwo);
    }

    @RequestMapping(value = "/square-root/{numberOne}/", method = RequestMethod.GET)
    public Double squareRoot(@PathVariable("numberOne") String numberOne) throws Exception {
        doubleUtils.checkIfValueIsValid(numberOne);
        return operations.squareRoot(numberOne);
    }

}
