package com.example.nour.sqlite;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DB_Sqlit extends SQLiteOpenHelper {
    private static final String DBname ="data.db";
    DB_Sqlit(Context context) {
        super(context, DBname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table mytable (id INTEGER PRIMARY KEY aUTOINCREMENT, name TEXT,email TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS mytable");
        onCreate(db);
    }
    public boolean insertData(String name , String email){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",name);
        contentValues.put("email",email);
        long result=db.insert("mytable",null,contentValues);
        return result != -1;
    }

    public ArrayList getAllrecord(){
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor res= db.rawQuery("Select * from mytable ", null);
        res.moveToFirst();
        while (res.isAfterLast()==false){
            String t1=res.getString(0);
            String t2=res.getString(1);
            String t3=res.getString(2);
            arrayList.add(t1 +" - "+t2+"\n"+t3);
            res.moveToNext();
        }
        return arrayList;
    }
    public boolean updatedata (String id, String name, String email){
      SQLiteDatabase db = this.getWritableDatabase();
      ContentValues contentValues = new ContentValues();
      contentValues.put("name",name);
      contentValues.put("email",email);
     db.update("mytable",contentValues,"id=?",new String[]{id});
     return true;
    }
public int delete (String id){
    SQLiteDatabase db = this.getWritableDatabase();
    return db.delete("mytable","id=?",new String[]{id});
}
}

