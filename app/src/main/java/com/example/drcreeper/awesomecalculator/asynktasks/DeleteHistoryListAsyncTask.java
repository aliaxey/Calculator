package com.example.drcreeper.awesomecalculator.asynktasks;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;

import com.example.drcreeper.awesomecalculator.historywriter.HistoryDatabaseOpenHelper;
import com.example.drcreeper.awesomecalculator.historywriter.HistoryDatabaseScheme;

public class DeleteHistoryListAsyncTask extends AsyncTask<Void,Void,Void> {

    private Context context;
    private OnItemsDeleteCallback onCompleteListener;

    public DeleteHistoryListAsyncTask(Context context){
        super();
        this.context = context;
    }

    public OnItemsDeleteCallback getOnCompleteListener() {
        return onCompleteListener;
    }

    public void setOnCompleteListener(OnItemsDeleteCallback onCompleteListener) {
        this.onCompleteListener = onCompleteListener;
    }



    @Override
    protected Void doInBackground(Void... voids) {
        SQLiteOpenHelper helper = new HistoryDatabaseOpenHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("DELETE FROM " + HistoryDatabaseScheme.HISTORY_TABLE);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        onCompleteListener.onItemsDelete();
    }
}
