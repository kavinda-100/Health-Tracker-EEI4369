package com.s22010170.heathtrakerapp.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.Blob;

public class DataBaseHelper extends SQLiteOpenHelper {
    // database name and version
    public static final String DATABASE_NAME = "HeathTracker.db";
    public static final int DATABASE_VERSION = 1;
    // table names
    public static final String USER_TABLE_NAME = "user_table";
    // column names
    // for user table
    public static final String user_COL_2 = "username";
    public static final String user_COL_3 = "email";
    public static final String user_COL_4 = "password";
    public static final String user_COL_5 = "imgAvatar";
    public static final String user_COL_6 = "imgBackground";
    public static final String user_COL_7 = "isLoggedIn";
    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // crate database and table
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create user table
        db.execSQL("CREATE TABLE " + USER_TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT, EMAIL TEXT, PASSWORD TEXT, IMGAVATAR BLOB, IMGBACKGROUND BLOB, ISLOGGEDIN TEXT DEFAULT 'false')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop table if exists
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        // create table
        onCreate(db);

    }
    // sign up method
    public boolean signUp(String username, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(user_COL_2, username);
        contentValues.put(user_COL_3, email);
        contentValues.put(user_COL_4, password);
        contentValues.put(user_COL_7, "true");

        long result = db.insert(USER_TABLE_NAME, null, contentValues);

        return result != -1;
    }
    // sign in method
    public Cursor signIn(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + USER_TABLE_NAME + " WHERE EMAIL = '" + email + "' AND PASSWORD = '" + password + "'" , null);
    }
    // update user login status
    public boolean updateLoginStatus(String email, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(user_COL_7, status);

        long result = db.update(USER_TABLE_NAME, contentValues, "EMAIL = ?", new String[]{email});

        return result != -1;
    }
    // reset password method
    public boolean resetPassword(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(user_COL_4, password);

        long result = db.update(USER_TABLE_NAME, contentValues, "EMAIL = ?", new String[]{email});

        return result != -1;
    }
    // delete account method
    public boolean deleteAccount(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(USER_TABLE_NAME, "EMAIL = ?", new String[]{email});

        return result != -1;
    }
    // get user data
    public Cursor getUserData(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + USER_TABLE_NAME + " WHERE EMAIL = '" + email + "'", null);
    }
    // update user data
    public boolean updateUserData(String oldEmail, String email, String username, String password, byte[] imgAvatar, byte[] imgBackground) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(user_COL_2, username);
        contentValues.put(user_COL_3, email);
        contentValues.put(user_COL_4, password);
        contentValues.put(user_COL_5, imgAvatar);
        contentValues.put(user_COL_6, imgBackground);
        contentValues.put(user_COL_7, "true");

        long result = db.update(USER_TABLE_NAME, contentValues, "EMAIL = ?", new String[]{oldEmail});

        return result != -1;
    }
}
