package com.example.drcreeper.awesomecalculator.math;

public class CalculatorHistoryItem {
    private double firstOperand;
    private double secondOperand;
    private Operator operator;
    private double result;
    private boolean isChecked = false;

    public double getFirstOperand() {
        return firstOperand;
    }

    public void setFirstOperand(double firstOperand) {
        this.firstOperand = firstOperand;
    }

    public double getSecondOperand() {
        return secondOperand;
    }

    public void setSecondOperand(double secondOperand) {
        this.secondOperand = secondOperand;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getOperatorSymbol(){
        String result = " ";
        switch (operator){
            case ADD:
                result = " + ";
                break;
            case SUB:
                result = " - ";
                break;
            case MUL:
                result = " * ";
                break;
            case DIV:
                result = " / ";
        }
        return result;
    }

    @Override
    public String toString() {
        return firstOperand + getOperatorSymbol() + secondOperand + " = " + result;
    }
}

