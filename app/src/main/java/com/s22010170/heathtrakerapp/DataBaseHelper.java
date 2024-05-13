package com.s22010170.heathtrakerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    // database name and version
    public static final String DATABASE_NAME = "HeathTracker.db";
    public static final int DATABASE_VERSION = 1;
    // table names
    public static final String AUTH_TABLE_NAME = "auth_table";
    // column names
    // for auth table
    public static final String auth_COL_1 = "id";
    public static final String auth_COL_2 = "username";
    public static final String auth_COL_3 = "email";
    public static final String auth_COL_4 = "password";
    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // crate database and table
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create auth table
        db.execSQL("CREATE TABLE " + AUTH_TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT, EMAIL TEXT, PASSWORD TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop table if exists
        db.execSQL("DROP TABLE IF EXISTS " + AUTH_TABLE_NAME);
        // create table
        onCreate(db);

    }
    // sign up method
    public boolean signUp(String username, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(auth_COL_2, username);
        contentValues.put(auth_COL_3, email);
        contentValues.put(auth_COL_4, password);

        long result = db.insert(AUTH_TABLE_NAME, null, contentValues);

        return result != -1;
    }
    // sign in method
    public Cursor signIn(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + AUTH_TABLE_NAME + " WHERE EMAIL = '" + email + "' AND PASSWORD = '" + password + "'", null);
    }
    // reset password method
    public boolean resetPassword(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(auth_COL_4, password);

        long result = db.update(AUTH_TABLE_NAME, contentValues, "EMAIL = ?", new String[]{email});

        return result != -1;
    }
    // delete account method
    public boolean deleteAccount(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(AUTH_TABLE_NAME, "EMAIL = ?", new String[]{email});

        return result != -1;
    }
    // get user data
    public Cursor getUserData(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + AUTH_TABLE_NAME + " WHERE EMAIL = '" + email + "'", null);
    }

    // get all users
    public Cursor getAllUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + AUTH_TABLE_NAME, null);
    }
}
