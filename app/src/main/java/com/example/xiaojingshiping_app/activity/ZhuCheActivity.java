package com.example.xiaojingshiping_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.xiaojingshiping_app.R;
import com.example.xiaojingshiping_app.sql.Dao;
import com.example.xiaojingshiping_app.sql.Mysql;

public class ZhuCheActivity extends AppCompatActivity {

    private EditText zhanghao;
    private EditText mima1;
    private EditText mima2;
    private Button zhuche;
    private String a;
    private String b;
    private String c;
    private String f;
    private String e = "2";
    private EditText name;
    private RadioGroup esc;
    private RadioButton nan;
    private RadioButton weizhi;
    private RadioButton nv;
    String[] d ={"xianjian","changhe","xiazhi","jingyu",
            "shida","liangjian","hanwu","woshi","dongbei","yingmi"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu_che);
        initView();
        buttonshijian();
        esc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nan.isChecked()){
                    e = "1";
                }
                if (nv.isChecked()){
                    e = "0";
                }
            }
        });
    }

    private void buttonshijian() {
        zhuche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (panduan()){
                    changjianxiangguanshujuku();
                    Toast.makeText(ZhuCheActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    private void changjianxiangguanshujuku() {
        Dao dao = new Dao(this);
        dao.open();
        for (int i=0;i<d.length;i++){
            dao.insert_sc(zhanghao.getText().toString().trim(),d[i],"meixuan");
        }
        dao.close();
    }

    private boolean panduan() {
        a = zhanghao.getText().toString().trim();
        b = mima1.getText().toString().trim();
        c = mima2.getText().toString().trim();
        f = name.getText().toString().trim();
        if (a ==null || a.length()<=0){
            Toast.makeText(ZhuCheActivity.this,"账号不能为空",Toast.LENGTH_SHORT).show();
        }
        if (b ==null || b.length()<=0){
            Toast.makeText(ZhuCheActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
        }
        if (c ==null || c.length()<=0){
            Toast.makeText(ZhuCheActivity.this,"再次确认密码不能为空",Toast.LENGTH_SHORT).show();
        }
        if (f == null || f.length()<=0){
             f= "默认小金";
        }
        if (b.equals(c)){
            Dao dao = new Dao(this);
            dao.open();
            if (!dao.query_zhanghao(a)){
                boolean jieguo= dao.insert(a,b,f,e);
                dao.insert_tx(a,"");
                dao.close();
                return jieguo;
            }else {
                Toast.makeText(ZhuCheActivity.this,"该账号已被注册",Toast.LENGTH_SHORT).show();
            }
       }else {
            Toast.makeText(ZhuCheActivity.this,"两个密码不相等",Toast.LENGTH_SHORT).show();
        }
        return false;
    }



    private void initView() {
        zhanghao = findViewById(R.id.zhanghao1);
        mima1 = findViewById(R.id.editTextTextPassword1);
        mima2 = findViewById(R.id.editTextTextPassword2);
        zhuche = findViewById(R.id.button4);
        name = findViewById(R.id.editTextTextPersonName3);
        esc = findViewById(R.id.esc);
        nan = findViewById(R.id.nan);
        weizhi = findViewById(R.id.weizhi);
        nv = findViewById(R.id.nv);
    }
}