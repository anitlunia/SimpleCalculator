package com.example.borja.simplecalculator;

import static com.example.borja.simplecalculator.CalculatorArithmetic.*;

/**
 * Created by Oxana on 05/02/2015.
 */
public interface ICalculatorModel {
    void inputValue(String number);
    void inputOperation(Operations op);

    String readScreen();
    String getState();
    void setState(String state);
}
