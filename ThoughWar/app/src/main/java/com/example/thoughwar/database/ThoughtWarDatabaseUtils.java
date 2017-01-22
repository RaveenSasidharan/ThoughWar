package com.example.thoughwar.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import com.example.thoughwar.model.ThoughtWarKingDataModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Raveen S on 07-01-2017.
 */

public class ThoughtWarDatabaseUtils
{
    private final static String TAG     =   ThoughtWarDatabaseUtils.class.getSimpleName();
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    ///
    //create table query
    ///
    public static final String SQL_CREATE_KING_ENTRIES  =
            "CREATE TABLE " + KingsTable.TABLE_NAME + "(" +
                    KingsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    KingsTable.NAME + TEXT_TYPE + COMMA_SEP +
                    KingsTable.RATING + TEXT_TYPE + COMMA_SEP +
                    KingsTable.BATTLES_WON + TEXT_TYPE + COMMA_SEP +
                    KingsTable.BATTLES_LOSS + TEXT_TYPE + COMMA_SEP +
                    KingsTable.STRENGTH_TYPE+ TEXT_TYPE + COMMA_SEP +
                    KingsTable.STRENGTH+ TEXT_TYPE + COMMA_SEP +
                    KingsTable.TIMESTAMP + TEXT_TYPE +
                    ")";


    ///
    //db schema structure
    ///
    public static abstract class KingsTable  implements BaseColumns
    {
        public static final String TABLE_NAME = "KingsTable";
        public static final String NAME  = "Name";
        public static final String RATING = "Rate";
        public static final String BATTLES_WON = "BattleWon";
        public static final String BATTLES_LOSS = "BattleLoss";
        public static final String STRENGTH_TYPE = "StrengthType";
        public static final String STRENGTH = "Strength";
        public static final String TIMESTAMP = "Timestamp";


    }

    ///
    //inserting new entry into db
    ///
    public static void insertKingItemintoLocalStorage(Context context, ThoughtWarKingDataModel item)
    {
        Log.i(TAG,"insertKingItemDateintoLocalStorage");
        ThoughtWarDatabaseHelper  dbHelper = new ThoughtWarDatabaseHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        if (database.isOpen())
        {
            ContentValues cv = new ContentValues();
            cv.put(KingsTable.NAME, item.mName);
            cv.put(KingsTable.RATING, item.mRating);
            cv.put(KingsTable.BATTLES_WON, item.mWinCount);
            cv.put(KingsTable.BATTLES_LOSS, item.mLossCount);
            cv.put(KingsTable.STRENGTH, item.mStrength);
            cv.put(KingsTable.STRENGTH_TYPE, item.mBattleType);
            SimpleDateFormat dateFormat = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            Date date = new Date();
            String dateText = dateFormat.format(date);
            cv.put(KingsTable.TIMESTAMP, dateText);
            database.insert(KingsTable.TABLE_NAME, null, cv);
            dbHelper.close();

        }
        dbHelper.close();
        database.close();

    } //end insertKingItemintoLocalStorage



    ////
    //updating column in database
    ///
    public static void updateKingItemIntoDataBase(Context context, ThoughtWarKingDataModel item)
    {
        Log.i(TAG,"update KingItemDateintoLocalStorage");
        ThoughtWarDatabaseHelper  dbHelper = new ThoughtWarDatabaseHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        if (database.isOpen())
        {
            ContentValues cv = new ContentValues();
            cv.put(KingsTable.NAME, item.mName);
            cv.put(KingsTable.RATING, item.mRating);
            cv.put(KingsTable.BATTLES_WON, item.mWinCount);
            cv.put(KingsTable.BATTLES_LOSS, item.mLossCount);
            cv.put(KingsTable.STRENGTH, item.mStrength);
            cv.put(KingsTable.STRENGTH_TYPE, item.mBattleType);
            SimpleDateFormat dateFormat = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            Date date = new Date();
            String dateText = dateFormat.format(date);
            cv.put(KingsTable.TIMESTAMP, dateText);
            database.update(KingsTable.TABLE_NAME,cv,KingsTable.NAME+" = '"+ item.mName + "'", null);
            dbHelper.close();

        }
        dbHelper.close();
        database.close();


    } // updateKingItemIntoDataBase



    ///
    // fetching video items from local storage
    ///
    public static List<ThoughtWarKingDataModel> fetchKingItemDataFromLocalStorage(Context context)
    {
        List<ThoughtWarKingDataModel> kingItemList   =   new ArrayList<>();
        SQLiteDatabase db = new ThoughtWarDatabaseHelper(context).getWritableDatabase();

        String query = "SELECT * FROM KingsTable ORDER BY Rate DESC" ;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
           ThoughtWarKingDataModel currentItem = fetchCurrentItem(cursor);
           kingItemList.add(currentItem);
            cursor.moveToNext();
        }
        cursor.close();

        return kingItemList;

    }// fetchKingItemDataFromLocalStorage




    ///
    //returning row data for particular king, if present
    ///
    public static ThoughtWarKingDataModel returnKingData(Context context, String tableName,
                                                         String dbfield, String fieldValue)
    {
        ThoughtWarKingDataModel kingItem = null;
        ThoughtWarDatabaseHelper  dbHelper = new ThoughtWarDatabaseHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        String query = "Select * from " + tableName + " where " + dbfield + " = " + fieldValue;
        Cursor cursor = database.rawQuery(query, null);
        if(cursor.getCount() <= 0)
        {
            cursor.close();
            kingItem = null;
        }
        else
        {
            kingItem = fetchCurrentItem(cursor);
        }

        cursor.close();
        return kingItem;
    } // returnKingData


    private static ThoughtWarKingDataModel fetchCurrentItem(Cursor cursor)
    {
        ThoughtWarKingDataModel kingItem = new ThoughtWarKingDataModel();
        kingItem.mName      =   cursor.getString(1);
        kingItem.mRating    =   cursor.getString(2);
        kingItem.mWinCount  =   cursor.getString(3);
        kingItem.mLossCount =   cursor.getString(4);
        kingItem.mStrength  =   cursor.getString(5);
        kingItem.mBattleType=   cursor.getString(6);
        return kingItem;
    }

}
