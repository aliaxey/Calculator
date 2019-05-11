package com.example.drcreeper.awesomecalculator.asynktasks;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.example.drcreeper.awesomecalculator.historywriter.HistoryDatabaseOpenHelper;
import com.example.drcreeper.awesomecalculator.historywriter.HistoryDatabaseScheme;
import com.example.drcreeper.awesomecalculator.math.CalculatorHistoryItem;

public class SaveOperationToHistoryAsyncTask extends AsyncTask<CalculatorHistoryItem, Void, Void> {

    private Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(CalculatorHistoryItem... histories) {
        HistoryDatabaseOpenHelper helper = new HistoryDatabaseOpenHelper(context);
        SQLiteDatabase database = helper.getWritableDatabase();
        String query = "INSERT INTO " + HistoryDatabaseScheme.HISTORY_TABLE +
                "(first_operand , second_operand , operator , result) VALUES (" +
                histories[0].getFirstOperand() + " , " +
                histories[0].getSecondOperand() + " , '" +
                histories[0].getOperator().name() + "' , " +
                histories[0].getResult() + ");";
        //Toast.makeText(context,query,Toast.LENGTH_LONG).show(); first_operand REAL , second_operand REAL , operator STRING , result REAL
        database.execSQL(query);
        return null;
    }
}
