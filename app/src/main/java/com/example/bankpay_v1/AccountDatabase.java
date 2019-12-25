package com.example.bankpay_v1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * The SQL Lite Database for Bank Accounts which stores an ID, a name, and the balance
 */

public class AccountDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "ACCOUNTNAMES.db";
    public static final String TABLE_NAME = "nameTable";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "name";
    public static final int VERSION = 3;
    public static final String COL_3 = "Balance";

    //contstructor
    public AccountDatabase (Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    /*creates a table with column 1 with id's, which are autoincremented
    column 2 with the names
    column 3 with the balance
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " +TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, "+COL_2+" TEXT, "+COL_3+" DOUBLE)" );
    }

    //inserting a bank account with name into the database
    public boolean insertData(String name){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3,0);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1){
            return false;
        }

        return true;
    }

    //deleting an account (or all accounts) from the database
    public Integer deleteData (String id){
        SQLiteDatabase db = this.getWritableDatabase();

        if (Integer.parseInt(id) == -1){
            return db.delete(TABLE_NAME, null, null);
        } else {
            return db.delete(TABLE_NAME, "ID = ?", new String[]{id});
        }
    }

    //updating data for deposits
    public boolean updateDataDeposit(String id, double updated_balance, double base_balance){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_3, base_balance + updated_balance);

        db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{id});

        return true;
    }

    //updating data for withdrawals
    public boolean updateDataWithdrawal(String id, double updated_balance, double base_balance){

        if (updated_balance > base_balance){
            return false;
        }

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_3, base_balance - updated_balance);

        db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{id});

        return true;
    }


    //allows a cursor to pass through all of the elements in the database
    public Cursor getListContents(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    //upgrading version
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
}
