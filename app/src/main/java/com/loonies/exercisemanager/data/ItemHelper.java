package com.loonies.exercisemanager.data;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
/**
 * Created by Õı∫∆÷€ on 2015/8/2.
 */
public class ItemHelper extends SQLiteOpenHelper {
    private final String CREATE_TABLE_OXY="create table ItemDb(id integer primary key autoincrement,item text not" +
            " null,hours text,minutes text,groups text,datetime text not null,isdone integer)";
    public ItemHelper(Context context) {
        super(context, "ItemDb", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE_OXY);
    }
    public void execSQL(){

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
