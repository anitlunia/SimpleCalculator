package com.example.borja.simplecalculator;

import java.lang.Math;import java.lang.String;import java.math.BigDecimal;
import java.math.MathContext;
/**
 * Created by Oxana on 05/02/2015.
 */

public class CalculatorArithmetic {
    public static final int MAX_LENGTH_WITHOUT_POINT = 8;

    public static enum Operations {
        Addition, Subtraction, Product, Division
    }

    public static BigDecimal operate (BigDecimal arg1, Operations op, BigDecimal arg2) {
        if (arg1 == null || arg2 == null || op == null)
            return null;
        BigDecimal result = BigDecimal.ZERO;
        switch (op) {
            case Addition:
                result = arg1.add(arg2);
                break;
            case Subtraction:
                result = arg1.subtract(arg2);
                break;
            case Product:
                result = arg1.multiply(arg2);
                break;
            case Division:
                if (arg2.equals(BigDecimal.ZERO))
                    result = null;
                else
                    result = arg1.divide(arg2, MathContext.DECIMAL64);
                break;
        }
        return result;
    }

    public static String toString(BigDecimal value, String errorString) {
        if (value == null)
            return errorString;
        String integerPart = value.toBigInteger().toString();
        final int maxIntegerLength = MAX_LENGTH_WITHOUT_POINT + (value.signum() == -1 ? 1 : 0);
        final int integerLength = integerPart.length();
        if (integerLength > maxIntegerLength) {
            return errorString;
        }
        String completeNumber = value.toPlainString();
        int maxLength = maxIntegerLength;
        final boolean hasPoint = value.scale() > 0 && integerLength != maxIntegerLength;
        if (hasPoint)
            maxLength++;
        int finalLength = Math.min(completeNumber.length(), maxLength);
        if (hasPoint) { // Trim trailing zeroes
            for (; finalLength > integerLength && completeNumber.charAt(finalLength - 1) == '0';
                 finalLength--)
                ;
            if (completeNumber.charAt(finalLength - 1) == '.')
                finalLength --;
        }
        return completeNumber.substring(0, finalLength);
    }

}
