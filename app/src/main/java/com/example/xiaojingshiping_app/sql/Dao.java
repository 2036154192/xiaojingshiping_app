package com.example.xiaojingshiping_app.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.xiaojingshiping_app.FinelConstants;
import com.example.xiaojingshiping_app.activity.Comment;
import com.example.xiaojingshiping_app.activity.ShiPing;
import com.example.xiaojingshiping_app.shouye.ItemBean;

import java.util.ArrayList;
import java.util.List;

public class Dao {

    private Mysql mysql;
    Context context;
    private SQLiteDatabase db;

    public Dao(Context context){
      this.context = context;
    }

    //开启
    public void open() {
        mysql = new Mysql(context);
        db = mysql.getWritableDatabase();
    }

    //关闭
    public void close() {
        db.close();
        mysql.close();
    }

    public void insert_sp(String a,String b){
        ContentValues values = new ContentValues();
        values.put(FinelConstants.SP_NAME,a);
        values.put(FinelConstants.SP_ID,b);
        db.insert(FinelConstants.TABLE_NAME_SP,null,values);
    }

    public boolean insertpinglun(String a,String b,String c){
        ContentValues values = new ContentValues();
        values.put(FinelConstants.PL_NAME,a);
        values.put(FinelConstants.PL_TEXT,b);
        values.put("dizhi",c);
        boolean jieguo = db.insert(FinelConstants.TABLE_NAME_PL,null,values)>0;
        return jieguo;
    }

    public boolean insert(String a,String b,String c,String d){
        ContentValues values = new ContentValues();
        values.put(FinelConstants.TABLE_ZHANGHAO,a);
        values.put(FinelConstants.TABLE_MIMA,b);
        values.put(FinelConstants.XINGMING,c);
        values.put(FinelConstants.XINGBIE,d);
        boolean jieguo = db.insert(FinelConstants.TABLE_NAME,null,values)>0;
        return jieguo;
    }

    public void insert_dq(String a,String b){
        ContentValues values = new ContentValues();
        values.put(FinelConstants.DQ_ID,a);
        values.put(FinelConstants.DQ_ZHANGTAI,b);
        db.insert(FinelConstants.TABLE_NAME_DQ,null,values);
       }

    public void insert_sc(String c,String a,String b){
        ContentValues values = new ContentValues();
        values.put(FinelConstants.TABLE_ZHANGHAO,c);
        values.put(FinelConstants.SC_NAME,a);
        values.put(FinelConstants.SC_ZHUANGTAI,b);
        db.insert(FinelConstants.TABLE_NAME_SC,null,values);
    }

    public void insert_ls(String a,String b,String c,int d){
        ContentValues values = new ContentValues();
        values.put(FinelConstants.TABLE_ZHANGHAO,a);
        values.put(FinelConstants.LS_NAME,b);
        values.put(FinelConstants.LS_TIEM,c);
        values.put(FinelConstants.LS_IUMAGE,d);
        db.insert(FinelConstants.TABLE_NAME_LS,null,values);
    }

    public void insert_tx(String a,String b){
        ContentValues values = new ContentValues();
        values.put(FinelConstants.TABLE_ZHANGHAO,a);
        values.put(FinelConstants.TX_IMAGE,b);
        db.insert(FinelConstants.TABLE_NAME_TX,null,values);
    }

    public void delete_ls(String a,String b){
        String[] whereArgs = {a,b};
        String whereClause = FinelConstants.TABLE_ZHANGHAO+" =? and "+FinelConstants.LS_NAME+" =?";
        db.delete(FinelConstants.TABLE_NAME_LS,whereClause,whereArgs);
    }

    public void delete_ls_all(String a){
        String[] whereArgs = {a};
        String whereClause = FinelConstants.TABLE_ZHANGHAO+" =?";
        db.delete(FinelConstants.TABLE_NAME_LS,whereClause,whereArgs);
    }

    public boolean update(String zhanghao,String mima,String newmima){
        ContentValues values = new ContentValues();
        values.put(FinelConstants.TABLE_MIMA,newmima);
        String[] whereArgs ={zhanghao,mima};
        String whereClause = FinelConstants.TABLE_ZHANGHAO+" =? and "+FinelConstants.TABLE_MIMA+" =?";
        boolean jieguo =db.update(FinelConstants.TABLE_NAME,values,whereClause,whereArgs)>0;
        return jieguo;
    }

    public void update_dq(String a,String b){
        ContentValues values = new ContentValues();
        values.put(FinelConstants.DQ_ZHANGTAI,b);
        String[] whereArgs ={a};
        String whereClause = FinelConstants.DQ_ID+" =?";
        db.update(FinelConstants.TABLE_NAME_DQ,values,whereClause,whereArgs);
    }

    public void update_sc(String c,String a,String b){
        ContentValues values = new ContentValues();
        values.put(FinelConstants.SC_ZHUANGTAI,b);
        String[] whereArgs ={c,a};
        String whereClause = FinelConstants.TABLE_ZHANGHAO+" =? and "+FinelConstants.SC_NAME+" =?";
        db.update(FinelConstants.TABLE_NAME_SC,values,whereClause,whereArgs);
    }

    public void update_xx_xingming(String a,String b){
        ContentValues values = new ContentValues();
        values.put(FinelConstants.XINGMING,b);
        String[] whereArgs ={a};
        String whereClause = FinelConstants.TABLE_ZHANGHAO+" =?";
        db.update(FinelConstants.TABLE_NAME,values,whereClause,whereArgs);
    }

    public void update_xx_xingbie(String a,String b){
        ContentValues values = new ContentValues();
        values.put(FinelConstants.XINGBIE,b);
        String[] whereArgs ={a};
        String whereClause = FinelConstants.TABLE_ZHANGHAO+" =?";
        db.update(FinelConstants.TABLE_NAME,values,whereClause,whereArgs);
    }

    public void update_tx(String a,String b){
        ContentValues values = new ContentValues();
        values.put(FinelConstants.TX_IMAGE,b);
        String[] whereArgs ={a};
        String whereClause = FinelConstants.TABLE_ZHANGHAO+" =?";
        db.update(FinelConstants.TABLE_NAME_TX,values,whereClause,whereArgs);
    }

    public List<ShiPing> query_sp(String a){
        List<ShiPing> list_sp = new ArrayList<>();
        Cursor cursor = db.query(FinelConstants.TABLE_NAME_SP,null,"spname like '%"+a+"%'",null,null,null,null);
        while (cursor.moveToNext()){
            String spname = cursor.getString(0);
            String spid = cursor.getString(1);
            ShiPing shiPing = new ShiPing(spname,spid);
            list_sp.add(shiPing);
        }
        cursor.close();
        return list_sp;
    }

    public String query_xingming(String a){
        Cursor cursor = db.query(FinelConstants.TABLE_NAME,null,null,null,null,null,null);
        while (cursor.moveToNext()){
            String zhanghao =cursor.getString(0);
            String xingming = cursor.getString(2);
            if (a.equals(zhanghao)){
                cursor.close();
                return xingming;
            }
        }
        return null;
    }

    public String[] query_xx_all(String a){
        Cursor cursor = db.query(FinelConstants.TABLE_NAME,null,null,null,null,null,null);
        while (cursor.moveToNext()){
            String zhanghao = cursor.getString(0);
            String xingming = cursor.getString(2);
            String xingbie = cursor.getString(3);
            if (a.equals(zhanghao)){
                cursor.close();
                String[] xx = {zhanghao,xingming,xingbie};
                return xx;
            }
        }
        return null;
    }

    public boolean query_ls(String a, String b){
        Cursor cursor = db.query(FinelConstants.TABLE_NAME_LS,null,null,null,null,null,null);
        if (cursor !=null){
            while (cursor.moveToNext()){
                String name = cursor.getString(1);
                String zhanghao = cursor.getString(0);
                if (b.equals(name) && a.equals(zhanghao)){
                    cursor.close();
                    return true;
                }
            }
        }
        cursor.close();
        return false;
    }

    public List<ItemBean> query_ls_all(String a){
        List<ItemBean> data = new ArrayList<>();
        Cursor cursor = db.query(FinelConstants.TABLE_NAME_LS,null,null,null,null,null,"time desc");
            while (cursor.moveToNext()){
                String name = cursor.getString(1);
                String zhanghao = cursor.getString(0);
                String time = cursor.getString(2);
                int images = cursor.getInt(3);
                if (a.equals(zhanghao)){
                    ItemBean itemBean = new ItemBean();
                    itemBean.setImageView(images);
                    itemBean.setName(name);
                    itemBean.setText(time);
                    data.add(itemBean);
                }
            }
        if (data.size()>=0){
            cursor.close();
            return data;
        }
        cursor.close();
        return null;
    }

    public String query_sc(String a,String b){
        Cursor cursor = db.query(FinelConstants.TABLE_NAME_SC,null,null,null,null,null,null);
        if (cursor !=null){
            while (cursor.moveToNext()){
                String name = cursor.getString(1);
                String zhuangtai = cursor.getString(2);
                String dizhi = cursor.getString(0);
                if (a.equals(name) && b.equals(dizhi)){
                    cursor.close();
                    return zhuangtai;
                }
            }
        }
        cursor.close();
        return null;
    }

    public List query_sc_all(String b){
        List a = new ArrayList();
        Cursor cursor = db.query(FinelConstants.TABLE_NAME_SC,null,null,null,null,null,null);
        if (cursor !=null){
            while (cursor.moveToNext()){
                String zhanghao = cursor.getString(0);
                String zhuangtai = cursor.getString(2);
                String name = cursor.getString(1);
                if (b.equals(zhanghao) && zhuangtai.equals("shouchang")){
                    a.add(name);
                }
            }
            cursor.close();
            return a;
        }
        cursor.close();
        return null;
    }

    public String query_dq(String a){
        Cursor cursor= db.query(FinelConstants.TABLE_NAME_DQ,null,null,null,null,null,null);
        if (cursor != null){
            while (cursor.moveToNext()){
                String zhangtai = cursor.getString(1);
                String id = cursor.getString(0);
                if (a.equals(id)){
                    cursor.close();
                    return zhangtai;
                }
            }
        }
        cursor.close();
        return null;
    }

    public boolean query(String a,String b){
         Cursor cursor= db.query(FinelConstants.TABLE_NAME,null,null,null,null,null,null);
         while (cursor.moveToNext()){
             String zhanghao = cursor.getString(0);
             String mima = cursor.getString(1);
             if (zhanghao.equals(a) && mima.equals(b)){
                 cursor.close();
                 return true;
             }
         }
         cursor.close();
         return false;
    }

    public boolean query_zhanghao(String a){
        Cursor cursor= db.query(FinelConstants.TABLE_NAME,null,null,null,null,null,null);
        while (cursor.moveToNext()){
            String zhanghao = cursor.getString(0);
            if (zhanghao.equals(a)){
                cursor.close();
                return true;
            }
        }
        cursor.close();
        return false;
    }


    public List<Comment> querypl(String dizhi1){
        List<Comment> data = new ArrayList<>();
        Cursor cursor= db.query(FinelConstants.TABLE_NAME_PL,null,null,null,null,null,null);
        while (cursor.moveToNext()){
            String name = cursor.getString(0);
            String text = cursor.getString(1);
            String dizhi = cursor.getString(2);
            if (dizhi.equals(dizhi1)){
               Comment comment = new Comment();
               comment.setName(name);
               comment.setPinglun(text);
               data.add(comment);
            }
        }
        if (data.size()>=0){
            return data;
        }
        cursor.close();
        return null;
    }

    public String query_tx(String a){
        Cursor cursor = db.query(FinelConstants.TABLE_NAME_TX,null,null,null,null,null,null);
        while (cursor.moveToNext()){
            String zhanghao = cursor.getString(0);
            String txUrl = cursor.getString(1);
            if (a.equals(zhanghao)){
                cursor.close();
                return txUrl;
            }
        }
        return null;
    }


}
