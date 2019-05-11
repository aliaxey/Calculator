package com.example.drcreeper.awesomecalculator.historywriter;

public class HistoryDatabaseScheme {


    public static final String DB_NAME = "operations";
    public static final int VERSION = 2;

    public static final String HISTORY_TABLE = "history";

    public static final String HISTORY_ID = "_id";
    public static final String HISTORY_FIRST_OPERAND = "first_operand";
    public static final String HISTORY_SECOND_OPERAND = "second_operand";
    public static final String HISTORY_RESULT = "result";
    public static final String HISTORY_OPERATOR = "operator";
}