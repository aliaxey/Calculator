package com.example.drcreeper.awesomecalculator.math;

public class Calculator {

    private static final int TEXT_FIELD_SIZE = 17;

    private String currentText = "0";
    private double firstOperand = 0;
    private double secondOperand = 0;
    private Operator operand = Operator.NONE;
    private CalculatorState state = CalculatorState.INITIAL;
    private boolean isDotAvailable = true;

    private String historyLog = "";
    private CalculatorHistoryItem history;


    public Calculator() {
        super();
    }

    public String getCurrentText() {
        return currentText;
    }

    public void setCurrentText(String currentText) {
        this.currentText = currentText;
    }

    public double getFirstOperand() {
        return firstOperand;
    }

    public void setFirstOperand(double firstOperand) {
        this.firstOperand = firstOperand;
    }

    public double getSecondOperand() {
        return secondOperand;
    }

    public CalculatorState getState() {
        return state;
    }

    public void setState(CalculatorState state) {
        this.state = state;
    }

    public void setSecondOperand(double secondOperand) {
        this.secondOperand = secondOperand;
    }

    public Operator getOperand() {
        return operand;
    }

    public void setOperand(Operator operand) {
        this.operand = operand;
    }

    public boolean isDotAvailable() {
        return isDotAvailable;
    }

    public void setDotAvailable(boolean dotAvailable) {
        isDotAvailable = dotAvailable;
    }

    public String getHistoryLog() {
        return historyLog;
    }

    public CalculatorHistoryItem getHistory() {
        return history;
    }

    public void setHistory(CalculatorHistoryItem history) {
        this.history = history;
    }

    /**
     *
     * @param historyLog
     * Don't use this method, it may destroy north korea!
     */
    public void setHistoryLog(String historyLog) {
        this.historyLog = historyLog;
    }

    private void toZero(){
        currentText = "0";
        isDotAvailable = true;
    }

    private double parseNum(){
        double result = 0;
        try {
            result = Double.parseDouble(currentText);
        }catch (NumberFormatException e){
            clear();
        }
        return result;
    }

    private void show(double s){
        String outputField = Double.toString(s);
        if(outputField.endsWith(".0")){
            outputField = outputField.substring(0,outputField.length() - 2);
        }
        if(outputField.contains(".")){
            while (outputField.endsWith("0")){
                outputField = outputField.substring(0,outputField.length() - 1);
            }
        }
        if(outputField.length()>TEXT_FIELD_SIZE){
            outputField = outputField.substring(0,TEXT_FIELD_SIZE);
        }
        currentText = outputField;
    }

    private double currentSolve(){
        double result;
        switch (operand){
            case ADD:
                result = firstOperand + secondOperand;
                break;
            case SUB:
                result = firstOperand - secondOperand;
                break;
            case MUL:
                result = firstOperand * secondOperand;
                break;
            case DIV:
                result = firstOperand / secondOperand;
                break;
            default:
                result = parseNum();
        }
        return result;
    }

    private String getOperatorSymbol(){
        String result = " ";
        switch (operand){
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

    private void setHistory(){
        history = new CalculatorHistoryItem();
        history.setFirstOperand(firstOperand);
        history.setOperator(operand);
        history.setSecondOperand(secondOperand);
        history.setResult(parseNum());
    }

    public void numPress(char c){
        if(state == CalculatorState.SOLVED){
            clear();
        }
        if(state == CalculatorState.OPERATOR_CHECKED){
            toZero();
            state = CalculatorState.SECOND_NUM_ENTERED;
        }
        if(currentText.length() < TEXT_FIELD_SIZE) {
            if(c == '.'){
                if(isDotAvailable){
                    currentText = currentText + ".";
                    isDotAvailable = false;
                }
            }else {
                if(currentText.equals("0")) {
                    currentText = "";
                }
                currentText = currentText + c;
            }
        }
    }

    public void operatorPress(Operator c){
        switch(state){
            case INITIAL:
                firstOperand = parseNum();
                toZero();
                state = CalculatorState.OPERATOR_CHECKED;
                operand = c;
                break;
            case OPERATOR_CHECKED:
                operand = c;
                secondOperand = parseNum();
                break;
            case SECOND_NUM_ENTERED:
                secondOperand = parseNum();
                show(currentSolve());
                firstOperand = parseNum();
                state = CalculatorState.OPERATOR_CHECKED;
                operand = c;
                break;
            case SOLVED:
                firstOperand = parseNum();
                toZero();
                state = CalculatorState.OPERATOR_CHECKED;
                operand = c;
        }
    }

    public void solvePress(){
        switch(state){
            case INITIAL:
                firstOperand = parseNum();
                show(firstOperand);
                historyLog = Double.toString(firstOperand) +  " = " + currentText;
                break;
            case OPERATOR_CHECKED:
                show(firstOperand);
                historyLog = Double.toString(firstOperand) +  " = " + currentText;
                break;
            case SECOND_NUM_ENTERED:
                secondOperand = parseNum();
                show(currentSolve());
                historyLog = Double.toString(firstOperand) + getOperatorSymbol() + Double.toString(secondOperand) + " = " + currentText;
                setHistory();
                firstOperand = parseNum();
                state = CalculatorState.SOLVED;
                break;
            case SOLVED:
                firstOperand = parseNum();
                show(currentSolve());
                historyLog = Double.toString(firstOperand) + getOperatorSymbol() + Double.toString(secondOperand) + " = " + currentText;
                setHistory();
                firstOperand = parseNum();
                break;
            case SOLVED_OPERATOR_CHECKED:
                secondOperand = parseNum();
                show(currentSolve());
                firstOperand = parseNum();
                state = CalculatorState.SOLVED;
        }
    }

    public void clear(){
        toZero();
        firstOperand = 0;
        secondOperand = 0;
        operand = Operator.NONE;
        state = CalculatorState.INITIAL;
        historyLog = "";
    }

    public void erasePress(){
        String oldText = currentText;
        if(oldText.endsWith(".")){
            isDotAvailable = true;
        }
        if(oldText.length() == 1) {
            toZero();
            if(state == CalculatorState.SECOND_NUM_ENTERED){
                state = CalculatorState.OPERATOR_CHECKED;
            }
        }else if (oldText.length() > 0){
            currentText = oldText.substring(0, oldText.length() - 1);
        }
    }
}
