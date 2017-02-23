package com.zdd.assistant.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Project Name: MyAssistant
 * File Name:    DiaryDatabaseHelper.java
 * ClassName:    DiaryDatabaseHelper
 *
 * Description: 记事本数据库帮助类
 *
 * @author ZDD
 * @date 2017年02月23日 17:18
 *
 */
public class DiaryDatabaseHelper extends SQLiteOpenHelper
{

    public static final String CREATE_DIARY = "create table Diary("
            + "id integer primary key autoincrement, "
            + "date text, "
            + "title text, "
            + "content text)";

    private Context mContext;
    public DiaryDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
        mContext = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DIARY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists Diary");
        onCreate(db);
    }
}
