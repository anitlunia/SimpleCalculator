package com.example.borja.simplecalculator;

import android.util.Log;

import static com.example.borja.simplecalculator.CalculatorArithmetic.Operations.Addition;
import static com.example.borja.simplecalculator.CalculatorArithmetic.Operations.Division;
import static com.example.borja.simplecalculator.CalculatorArithmetic.Operations.Product;
import static com.example.borja.simplecalculator.CalculatorArithmetic.Operations.Subtraction;

/**
 * Created by Oxana on 05/02/2015.
 */
public class CalculatorViewModel{
    private final ICalculatorModel model;
    private final ICalculatorView view;

    private String nextNumber = "F";

    public String screen = "";

    public CalculatorViewModel(ICalculatorModel model, ICalculatorView view){
        this.model = model;
        this.view = view;

    }

    public void onNumericKeyPressed(char digit){
        if(nextNumber == "T"){
            screen = "";
            nextNumber = "F";
        }

        screen += digit;
    }
    public void onDecimalPointPressed(){
        if (screen.contains(".") == false ){
            screen += ".";
            //Log.d("CHACHICALCULATOR","readScreenchachi:"+ model.readScreen());
        }
    }

    public void onOperatorKeyPressed(char opChar){
        model.inputValue(screen);
        switch (opChar){
            case '+':
                model.inputOperation(Addition);
                break;
            case '-':
                model.inputOperation(Subtraction);
                break;
            case '*':
                model.inputOperation(Product);
                break;
            case '/':
                model.inputOperation(Division);
                break;
        }
        screen = "";
    }

    public void onEqualKeyPressed(){
        model.inputValue(screen);
        screen = model.readScreen();
        nextNumber = "T";
    }

    public void saveMemory(){
        model.inputValue(screen);
    }

    public void setState(String state){
        String[] aux = state.split(":");
        nextNumber = aux[0];
        screen = aux[1];
        model.setState(aux[2] + ":" + aux[3]);
    }

    public String getState(){
        String aux = "" + nextNumber + ":" + screen + ":" + model.getState();
        return aux;
    }
}
