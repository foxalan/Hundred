package com.example.alan.hundred.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Function :
 * Author : Alan
 * Modify Date : 13/9/17
 * Issue : TODO
 * Whether solve :
 */

public class SQLHelper extends SQLiteOpenHelper {

    private static String sql_name = "sql_download.db";

    private String sql = "create table tb_dict(_id Integer primary key ,word varchar(50),detail varchar(50))";

    public SQLHelper(Context context) {
        super(context, sql_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
