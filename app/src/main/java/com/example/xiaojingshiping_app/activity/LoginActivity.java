package com.example.xiaojingshiping_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.xiaojingshiping_app.R;
import com.example.xiaojingshiping_app.sql.Dao;

public class LoginActivity extends AppCompatActivity {

    private EditText zhanghao;
    private EditText mima;
    private Button denglu;
    private String a;
    private String b;
    private Dao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        buttonjianting();
    }


    private void buttonjianting() {
        denglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (panduan()){
                    Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(LoginActivity.this,"账号或密码错误",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        zhanghao = findViewById(R.id.editTextTextPersonName);
        mima = findViewById(R.id.editTextTextPassword2);
        denglu = findViewById(R.id.button3);
        dao = new Dao(this);
    }

    private boolean panduan(){
        a = zhanghao.getText().toString().trim();
        b = mima.getText().toString().trim();
        dao.open();
        if (!dao.query_zhanghao(a)){
            Toast.makeText(LoginActivity.this,"没有该账号请创建",Toast.LENGTH_SHORT).show();
            return false;
        }
        dao.close();
        if (a ==null || b ==null){
            Toast.makeText(LoginActivity.this,"账号或密码不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }else {
            dao.open();
            boolean c = dao.query(a, b);
            dao.close();
            return c;
        }
    }

}