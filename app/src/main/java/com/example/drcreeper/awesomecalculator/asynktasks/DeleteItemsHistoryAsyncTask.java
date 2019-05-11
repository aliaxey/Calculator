package com.example.drcreeper.awesomecalculator.asynktasks;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.example.drcreeper.awesomecalculator.historywriter.HistoryDatabaseOpenHelper;
import com.example.drcreeper.awesomecalculator.historywriter.HistoryDatabaseScheme;

import java.util.ArrayList;
import java.util.List;

public class DeleteItemsHistoryAsyncTask extends AsyncTask<Void,Void,Void> {

    private Context context;
    private OnItemsDeleteCallback onItemsDeleteCallback;
    private List<Integer> input;

    public DeleteItemsHistoryAsyncTask() {
    }

    public DeleteItemsHistoryAsyncTask(Context context) {
        this.context = context;
    }

    public DeleteItemsHistoryAsyncTask(Context context, OnItemsDeleteCallback onItemsDeleteCallback) {
        this.context = context;
        this.onItemsDeleteCallback = onItemsDeleteCallback;
    }

    public OnItemsDeleteCallback getOnItemsDeleteCallback() {
        return onItemsDeleteCallback;
    }

    public void setOnItemsDeleteCallback(OnItemsDeleteCallback onItemsDeleteCallback) {
        this.onItemsDeleteCallback = onItemsDeleteCallback;
    }

    public void setInput(List<Integer> input) {
        this.input = input;
    }
    public List<Integer> getInput() {
        return input;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        HistoryDatabaseOpenHelper helper = new HistoryDatabaseOpenHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor list = db.rawQuery("SELECT _id FROM history", null);
        List<Integer> ids = new ArrayList<>();
        while (list.moveToNext()){
            ids.add(list.getInt(list.getColumnIndex("_id")));
        }
        list.close();

        try {
            db = helper.getWritableDatabase();
            db.beginTransaction();
            for (int i : input) {
                int target = ids.get(i);
                db.delete(HistoryDatabaseScheme.HISTORY_TABLE,HistoryDatabaseScheme.HISTORY_ID + " = " + Integer.toString(target),null);
                //db.execSQL("DELETE FROM " + HistoryDatabaseScheme.HISTORY_TABLE + " WHERE " + HistoryDatabaseScheme.HISTORY_ID +
                //        " = " + Integer.toString(target) + ";");

            }
            db.setTransactionSuccessful();
        }catch (SQLException e){

        }finally {
            db.endTransaction();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        onItemsDeleteCallback.onItemsDelete();
    }
}
