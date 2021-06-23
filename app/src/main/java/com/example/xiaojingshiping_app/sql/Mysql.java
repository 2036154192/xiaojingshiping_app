package com.example.xiaojingshiping_app.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.xiaojingshiping_app.FinelConstants;

public class Mysql extends SQLiteOpenHelper {

    /*
    * context 上下文
    * name  数据库名称
    * factory   游标工厂
    * version   版本号
    * */
    public Mysql(@Nullable Context context) {
        super(context, FinelConstants.DATABASE_NAME, null, FinelConstants.VERSION_CODE);
    }
    //创建时的回调
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql2 = "create table "+FinelConstants.TABLE_NAME_PL+"(name varchar(20),text varchar(40),dizhi varchar(20))";
        String sql = "create table "+FinelConstants.TABLE_NAME+"(zhanghao varchar(20) PRIMARY KEY,mima varchar(20),xingming varchar(40),sex vachar(2))";
        String sql3 = "create table "+FinelConstants.TABLE_NAME_DQ+"(id varchar(20) PRIMARY KEY,zhangtai varchar(20))";
        String sql4 = "create table "+FinelConstants.TABLE_NAME_SC+"(zhanghao varchar(20),name varchar(20),zhuangtai varchar(20))";
        String sql5 = "create table "+FinelConstants.TABLE_NAME_LS+"(zhanghao varchar(20),name varchar(20),time varchar(20),image int)";
        String sql6 = "create table "+FinelConstants.TABLE_NAME_TX+"(zhanghao varchar(20),touxiangimage varcharr(100))";
        String sql7 = "create table "+FinelConstants.TABLE_NAME_SP+"(spname varchar(20),spid varchar(10))";
        db.execSQL(sql7);
        db.execSQL(sql6);
        db.execSQL(sql4);
        db.execSQL(sql);
        db.execSQL(sql3);
        db.execSQL(sql2);
        db.execSQL(sql5);
    }
    //升级数据库回调
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
