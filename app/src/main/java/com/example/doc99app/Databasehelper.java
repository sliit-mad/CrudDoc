package com.example.doc99app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.jar.Attributes;

public class Databasehelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="Doc99.db";
    public static final String TABLE_NAME="doctor_table";
    public static final String COL_1="ID";
    public static final String COL_2="NAME";
    public static final String COL_3="MEDICALID";
    public static final String COL_4="ASSISTEMAIL";
    public static final String COL_5="ASSISTCONTACT";


    public Databasehelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+ TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,MEDICALID TEXT,ASSISTEMAIL TEXT,ASSISTCONTACT INTEGER) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String name,String medicalId,String assistEmail,String assistContact){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,medicalId);
        contentValues.put(COL_4,assistEmail);
        contentValues.put(COL_5,assistContact);
        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public boolean updateData(String id,String assistContact){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_5,assistContact);
        db.update(TABLE_NAME,contentValues, "ID = ?",new String[] { id});
        return true;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ?",new String[] { id });
    }
}
