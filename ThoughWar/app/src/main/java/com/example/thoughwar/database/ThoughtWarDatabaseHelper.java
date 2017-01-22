package com.example.thoughwar.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Raveen S on 07-01-2017.
 */

public class ThoughtWarDatabaseHelper extends SQLiteOpenHelper
{
    private final static String DATABASE_NAME   =   "thoughtwar.db";
    private final static int DATABASE_VERSION = 1;

    public ThoughtWarDatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);


    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {

        db.execSQL(ThoughtWarDatabaseUtils.SQL_CREATE_KING_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {

    }
}
