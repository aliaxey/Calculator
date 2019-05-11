package com.example.drcreeper.awesomecalculator.historywriter;

import android.widget.TextView;

public class ViewHolder {

    TextView firstOperand;
    TextView secondOperand;
    TextView operator;
    TextView result;

    public ViewHolder() {
    }
    public TextView getFirstOperand() {
        return firstOperand;
    }

    public void setFirstOperand(TextView firstOperand) {
        this.firstOperand = firstOperand;
    }

    public TextView getSecondOperand() {
        return secondOperand;
    }

    public void setSecondOperand(TextView secondOperand) {
        this.secondOperand = secondOperand;
    }

    public TextView getOperator() {
        return operator;
    }

    public void setOperator(TextView operator) {
        this.operator = operator;
    }

    public TextView getResult() {
        return result;
    }

    public void setResult(TextView result) {
        this.result = result;
    }


}
