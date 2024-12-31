package com.example.uas_mobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String Database_Name = "register.db";
    public static final String Table_Name = "registerUser";
    public static final String COL1 = "ID";
    public static final String COL2 = "username";
    public static final String COL3 = "password";

    public DBHelper(@Nullable Context context) {
        super(context, Database_Name, null, 1 );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + Table_Name +"(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +COL2 + " TEXT, " + COL3 + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        onCreate(sqLiteDatabase);
    }

    public long addUser(String username, String password) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, username);
        contentValues.put(COL3, password);

        long res = sqLiteDatabase.insert(Table_Name, null, contentValues);
        sqLiteDatabase.close();
        return res;
    }

    public boolean checkUser(String username, String password) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String[] columns = {COL1};
        String selection = COL2 + "=? AND " + COL3 + "=?";
        String[] selectionArg = {username, password};
        Cursor cursor = sqLiteDatabase.query
                (Table_Name, columns, selection, selectionArg, null, null, null );

        int count = cursor.getCount();
        sqLiteDatabase.close();
        return count > 0;
    }
}