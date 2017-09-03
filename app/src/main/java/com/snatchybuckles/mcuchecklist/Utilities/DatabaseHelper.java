package com.snatchybuckles.mcuchecklist.Utilities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

/**
 * Created by Ben on 30/08/2017.
 */

public class DatabaseHelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "mcu.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public LinkedHashMap<String, List<String>> getAllContent() {

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String [] sqlSelect = {"0 _id", "Entry_by_Canon", "Release_Date", "Name", "Medium", "Runtime_mins", "Phase", "Watched", "Series_Name", "Season", "Episode", "Synopsis"};
        String sqlTables = "MCU_Releases";

        qb.setTables(sqlTables);

        Cursor c = qb.query(db, sqlSelect, null, null, null, null, "Entry_by_Canon ASC");

        LinkedHashMap<String, List<String>> result = new LinkedHashMap<>();

        if(c.moveToFirst()) {
            do {

                List<String> list = new ArrayList<String>();
                list.add(c.getString(c.getColumnIndex("_id")));
                list.add(c.getString(c.getColumnIndex("Entry_By_Canon")));
                list.add(c.getString(c.getColumnIndex("Release_Date")));
                String n = c.getString(c.getColumnIndex("Name"));
                list.add(n);
                list.add(c.getString(c.getColumnIndex("Medium")));
                list.add(c.getString(c.getColumnIndex("Runtime_mins")));
                list.add(c.getString(c.getColumnIndex("Phase")));
                list.add(c.getString(c.getColumnIndex("Watched")));
                list.add(c.getString(c.getColumnIndex("Series_Name")));
                list.add(c.getString(c.getColumnIndex("Season")));
                list.add(c.getString(c.getColumnIndex("Episode")));
                list.add(c.getString(c.getColumnIndex("Synopsis")));
                result.put(n, list);

            } while (c.moveToNext());
        }
        c.close();

        return result;
    }

    public void updateWatchedStatus(int id, boolean status) {
        SQLiteDatabase db = getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Watched", status ? 1 : 0);
        db.update("MCU_Releases", cv, "_id=" + id, null);
    }
}
