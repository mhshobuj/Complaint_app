package com.mhs.complaint.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "complaint.db";

    public DataBaseHelper(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table user_registration(id INTEGER primary key autoincrement, name TEXT,email TEXT, password TEXT)");
        db.execSQL("create Table add_complaint(id INTEGER primary key autoincrement, title TEXT, description TEXT, status TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists order_info");
    }

    public Boolean insertUserRegistrationData(String name, String email, String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("password", password);

        long result = myDB.insert("user_registration",null, contentValues);

        if (result == -1) return false;
        else
            return true;
    }

    public Boolean updateComplaintData(String id, String title, String des, String status){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("description", des);
        contentValues.put("status", status);

        Cursor cursor = myDB.rawQuery("Select * from add_complaint where id = ?", new String[] {id});
        if(cursor.getCount() > 0){
            long result = myDB.update("add_complaint", contentValues, "id=?", new String[] {id});

            if (result == -1) return false;
            else
                return true;
        }
        else {
            return false;
        }
    }

    public Boolean insertNewComplaint(String title, String description, String status){
        SQLiteDatabase myDb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title",title);
        contentValues.put("description",description);
        contentValues.put("status",status);

        long result = myDb.insert("add_complaint", null, contentValues);
        if (result == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public Cursor getData(){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from add_complaint order by id desc", null);
        return cursor;
    }

    public Cursor getDataByID(String id){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from add_complaint where id= ?", new String[] {id});
        return cursor;
    }

    public Boolean checkEmail(String email){
        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor cursor = myDb.rawQuery("select * from user_registration where email = ?", new String[] {email});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkedEmailPassword(String email, String password){
        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor cursor = myDb.rawQuery("select * from user_registration where email = ? and password = ? ", new String[] {email,password});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }
}
