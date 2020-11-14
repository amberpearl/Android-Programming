package com.example.androidassignments;

import android.content.Context;
import androidx.annotation.Nullable;
import android.util.Log;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ChatDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Messages.db";
    private static final double DATABASE_VERSION = 6.0;
    public static final String TABLE_NAME = "MessagesTable";
    private static final String KEY_ID = "_id";
    public static final String KEY_MESSAGE = "message";
    private static final String DATABASE_CREATE = "create table "
            +TABLE_NAME + "(" + KEY_ID
            + " integer primary key autoincrement, " + KEY_MESSAGE
            + " text not null);";

    public ChatDatabaseHelper(@Nullable Context context) {

        super(context, DATABASE_NAME, null, (int) DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE); //taken from week 9 textbook, chapter 11, page 351
        Log.i("ChatDatabaseHelper", "Calling onCreate");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        Log.i("ChatDatabaseHelper", "Calling onUpgrade, oldVersion=" + oldVersion +" newVersion=" + newVersion);
    }


}