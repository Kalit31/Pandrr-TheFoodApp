package com.example.foodapp.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.foodapp.models.Item;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static String DATABASE_NAME = "Food.db";
    private static int DATABASE_VERSION = 1;
    private static String TABLE_NAME = "FoodItems";
    private static String[] COLUMNS = new String[]{"ID", "CODE", "NAME", "COUNTER", "QTY", "PRICE"};


    public DatabaseHelper(Context context) {

        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public DatabaseHelper(Context context, String name, int version)
    {
        super(context,name,null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

        String query="CREATE TABLE "+TABLE_NAME+" ("+COLUMNS[0]+" INTEGER PRIMARY KEY, "+
                COLUMNS[1]+" TEXT, "+COLUMNS[2]+" TEXT, "+COLUMNS[3]+" TEXT, "+COLUMNS[4]+" INTEGER, "+
                COLUMNS[5]+" TEXT);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public void putElement(Item item)
    {
        ContentValues cv=new ContentValues();
        cv.put(COLUMNS[1],item.getCode());
        cv.put(COLUMNS[2],item.getName());
        cv.put(COLUMNS[3],item.getCounter());
        cv.put(COLUMNS[4],item.getItemCount());
        cv.put(COLUMNS[5],item.getPrice());

        SQLiteDatabase db=this.getWritableDatabase();
        db.insert(TABLE_NAME,null,cv);

    }

    public Cursor getElements(){

        SQLiteDatabase db =this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
    }

    public Cursor getElementByCode(String code)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE CODE =? ",new String[]{code});
    }

    public boolean updateItem(String code, int quantity)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMNS[1],code);
        cv.put(COLUMNS[4],quantity);
        db.update(TABLE_NAME,cv,"CODE = ?",new String[]{code});
        return true;
    }
    public Integer deleteData(String code)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"CODE = ?",new String[]{code});
    }
}
