package com.example.drcreeper.awesomecalculator.asynktasks;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;

import com.example.drcreeper.awesomecalculator.historywriter.HistoryDatabaseOpenHelper;
import com.example.drcreeper.awesomecalculator.historywriter.HistoryDatabaseScheme;
import com.example.drcreeper.awesomecalculator.math.CalculatorHistoryItem;
import com.example.drcreeper.awesomecalculator.math.Operator;

import java.util.ArrayList;
import java.util.List;

public class GetOperationsListAsyncTask extends AsyncTask<Void,Void,List<CalculatorHistoryItem>> {

    private Context context;
    private AfterExecute callback;

    public AfterExecute getCallback() {
        return callback;
    }

    public void setCallback(AfterExecute callback) {
        this.callback = callback;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    protected List<CalculatorHistoryItem> doInBackground(Void... voids) {

        List<CalculatorHistoryItem> list = new ArrayList<>();

            SQLiteOpenHelper helper = new HistoryDatabaseOpenHelper(context);
            SQLiteDatabase database = helper.getReadableDatabase();
            String query = "SELECT first_operand , second_operand , operator , result FROM history";
            Cursor cursor = database.rawQuery(query, null);
            int idFirstOperand = cursor.getColumnIndex(HistoryDatabaseScheme.HISTORY_FIRST_OPERAND);
            int idSecondOperand = cursor.getColumnIndex(HistoryDatabaseScheme.HISTORY_SECOND_OPERAND);
            int idOperator = cursor.getColumnIndex(HistoryDatabaseScheme.HISTORY_OPERATOR);
            int idResult = cursor.getColumnIndex(HistoryDatabaseScheme.HISTORY_RESULT);
            while (cursor.moveToNext()) {
                CalculatorHistoryItem history = new CalculatorHistoryItem();
                history.setFirstOperand(cursor.getDouble(idFirstOperand));
                history.setSecondOperand(cursor.getDouble(idSecondOperand));
                history.setOperator(Operator.valueOf(cursor.getString(idOperator)));
                history.setResult(cursor.getDouble(idResult));
                list.add(history);
            }
            cursor.close();

        return  list;
    }

    @Override
    protected void onPostExecute(List<CalculatorHistoryItem> list) {
        callback.setAfterQuery(list);
    }
}
