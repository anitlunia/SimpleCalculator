package com.example.borja.simplecalculator;


import android.os.Bundle;

import java.math.BigDecimal;

import static com.example.borja.simplecalculator.CalculatorArithmetic.*;

/**
 * Created by Oxana on 05/02/2015.
 */
public class CalculatorModel implements ICalculatorModel{

    BigDecimal lastResult = null;
    Operations pendingOp = null;

    String error = "Error";

    private String _state = "";
    private BigDecimal _memory = new BigDecimal(0);

    @Override
    public void inputValue(String number) {
        if(pendingOp != null){
            lastResult = operate(lastResult, pendingOp, new BigDecimal(number));
        }
        else {
            if(number == "Error")
                lastResult = new BigDecimal(0);
            else
                lastResult = new BigDecimal(number);
        }
    }

    @Override
    public void inputOperation(Operations op) {
        pendingOp = op;
    }

    @Override
    public String readScreen() {
        String result = CalculatorArithmetic.toString(lastResult, error);
        lastResult = null;
        pendingOp = null;
        return result;
    }

    @Override
    public void setState(String state) {
        String[] aux = state.split(":");

        lastResult = new BigDecimal(aux[0]);


        if(aux[1] == "!")
            pendingOp = null;
        if(aux[1] == "+")
            pendingOp = Operations.Addition;
        if(aux[1] == "-")
            pendingOp = Operations.Subtraction;
        if(aux[1] == "*")
            pendingOp = Operations.Product;
        if(aux[1] == "/")
            pendingOp = Operations.Division;
    }

    @Override
    public String getState() {
        String auxOp = "";
        String auxNumb;
        if(lastResult == null)
            auxNumb = "0";
        else
            auxNumb = lastResult.toString();
        if(pendingOp == null)
            return auxNumb + ":!";
        switch (pendingOp) {
            case Addition:
                auxOp = "+";
                break;
            case Subtraction:
                auxOp = "-";
                break;
            case Product:
                auxOp = "*";
                break;
            case Division:
                auxOp = "/";
                break;
        }
        return auxNumb + ":" + auxOp;
    }
}
