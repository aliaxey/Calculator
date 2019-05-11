package com.example.drcreeper.awesomecalculator.propertywork;

import android.content.SharedPreferences;

import com.example.drcreeper.awesomecalculator.math.Calculator;
import com.example.drcreeper.awesomecalculator.math.Operator;
import com.example.drcreeper.awesomecalculator.math.CalculatorState;

public class CalculatorWriter {

    public static void saveState(Calculator calculator, SharedPreferences sharedPreferences){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(CalculatorPreferencesContract.KEY_CURRENT_TEXT,calculator.getCurrentText());
        editor.putFloat(CalculatorPreferencesContract.KEY_FIRST_OPERAND,(float)calculator.getFirstOperand());
        editor.putFloat(CalculatorPreferencesContract.KEY_SECOND_OPERAND,(float)calculator.getSecondOperand());
        editor.putString(CalculatorPreferencesContract.KEY_OPERATOR,calculator.getOperand().name());
        editor.putString(CalculatorPreferencesContract.KEY_STATE,calculator.getState().name());
        editor.putBoolean(CalculatorPreferencesContract.KEY_DOT_AVAILABLE,calculator.isDotAvailable());
        editor.apply();
    }

    public static void restoreState(Calculator calculator, SharedPreferences sharedPreferences){
        calculator.setCurrentText(sharedPreferences.getString(CalculatorPreferencesContract.KEY_CURRENT_TEXT,"0"));
        calculator.setFirstOperand(sharedPreferences.getFloat(CalculatorPreferencesContract.KEY_FIRST_OPERAND,0));
        calculator.setSecondOperand(sharedPreferences.getFloat(CalculatorPreferencesContract.KEY_SECOND_OPERAND,0));
        calculator.setOperand(Operator.valueOf(sharedPreferences.getString(CalculatorPreferencesContract.KEY_OPERATOR,"NONE")));
        calculator.setState(CalculatorState.valueOf(sharedPreferences.getString(CalculatorPreferencesContract.KEY_STATE,"INITIAL")));
        calculator.setDotAvailable(sharedPreferences.getBoolean(CalculatorPreferencesContract.KEY_DOT_AVAILABLE,true));

    }

}
