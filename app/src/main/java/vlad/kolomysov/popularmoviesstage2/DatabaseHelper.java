package vlad.kolomysov.popularmoviesstage2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Copyright (C) Created by Vlad Kolomysov on 25.11.15.
 * All rights reserved. United Financial Capital Bank, Moscow
 */
public class DatabaseHelper extends SQLiteOpenHelper implements BaseColumns {

    private static final String DATABASE_NAME = "appdatabase.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_PUSH = "branch_table";
    public static final String BRANCH_ID = "branch_ID";

    private static final String SQL_CREATE_TABLE_PUSH = "CREATE TABLE IF NOT EXISTS "
            + TABLE_PUSH + " (" + DatabaseHelper._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + BRANCH_ID + " TEXT)";

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
