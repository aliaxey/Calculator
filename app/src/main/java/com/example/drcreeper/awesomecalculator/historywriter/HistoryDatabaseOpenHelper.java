package com.example.drcreeper.awesomecalculator.historywriter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.drcreeper.awesomecalculator.math.CalculatorHistoryItem;
import com.example.drcreeper.awesomecalculator.math.Operator;

import java.util.ArrayList;
import java.util.List;

public class HistoryDatabaseOpenHelper extends SQLiteOpenHelper {

    public HistoryDatabaseOpenHelper(Context context){
        super(context, HistoryDatabaseScheme.DB_NAME,null, HistoryDatabaseScheme.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE history" +
                    "(_id INTEGER PRIMARY KEY AUTOINCREMENT , " +
                    "first_operand REAL , " +
                    "second_operand REAL , " +
                    "operator STRING , " +
                    "result REAL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            try {

                List<String> oldStrings = new ArrayList<>();
                String query = "SELECT solve FROM " +
                        HistoryDatabaseScheme.HISTORY_TABLE + ";";
                Cursor oldData = db.rawQuery(query,null);
                //Cursor oldData = db.query(HistoryDatabaseScheme.HISTORY_TABLE, new String[]{"solve"}, null, null, null, null, null);
                int solveId = oldData.getColumnIndex("solve");
                while (oldData.moveToNext()) {
                    oldStrings.add(oldData.getString(solveId));
                }
                oldData.close();
                db.beginTransaction();
                db.execSQL("DROP TABLE " + HistoryDatabaseScheme.HISTORY_TABLE);
                db.execSQL("CREATE TABLE history(_id INTEGER PRIMARY KEY AUTOINCREMENT , " +
                        "first_operand REAL ," +
                        " second_operand REAL ," +
                        " operator STRING ," +
                        " result REAL);");

                for (int i = 0; i < oldStrings.size(); i++) {
                    try {
                        String[] parts = oldStrings.get(i).split(" ");
                        if (parts.length == 5) {
                            CalculatorHistoryItem calculatorItem = new CalculatorHistoryItem();
                            calculatorItem.setFirstOperand(Double.parseDouble(parts[0]));
                            calculatorItem.setSecondOperand(Double.parseDouble(parts[2]));
                            calculatorItem.setResult(Double.parseDouble(parts[4]));
                            switch (parts[1]) {
                                case "+":
                                    calculatorItem.setOperator(Operator.ADD);
                                    break;
                                case "-":
                                    calculatorItem.setOperator(Operator.SUB);
                                    break;
                                case "*":
                                    calculatorItem.setOperator(Operator.MUL);
                                    break;
                                case "/":
                                    calculatorItem.setOperator(Operator.DIV);
                                    break;
                                default:
                                    calculatorItem.setOperator(Operator.NONE);
                            }
                            query = "INSERT INTO " + HistoryDatabaseScheme.HISTORY_TABLE +
                                    "(first_operand , second_operand , operator , result) VALUES (" +
                                    calculatorItem.getFirstOperand() + " , " +
                                    calculatorItem.getSecondOperand() + " , '" +
                                    calculatorItem.getOperator().name() + "' , " +
                                    calculatorItem.getResult() + ");";
                            db.execSQL(query);
                        }

                    } catch (Exception e) {
                    }
                }
                db.setTransactionSuccessful();

            }finally {
                db.endTransaction();
            }



        }



}
