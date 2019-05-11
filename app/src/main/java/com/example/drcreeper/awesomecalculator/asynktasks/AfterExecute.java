package com.example.drcreeper.awesomecalculator.asynktasks;

import com.example.drcreeper.awesomecalculator.math.CalculatorHistoryItem;

import java.util.List;

public interface AfterExecute {
    void setAfterQuery(List<CalculatorHistoryItem> list);
}
