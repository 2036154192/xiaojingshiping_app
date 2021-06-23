package com.example.xiaojingshiping_app.shezhixiangxi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.xiaojingshiping_app.R;
import com.example.xiaojingshiping_app.sql.Dao;

public class XiugaiActivity extends AppCompatActivity {

    private TextView xiugainame;
    private EditText xiugaitext;
    private Button queren;
    private String xiugai;
    private Dao dao;
    private String zhanghao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiugai);
        Intent intent = getIntent();
        xiugai = intent.getStringExtra("xiugai");
        initView();
        panduan();
        danjishijian();
    }

    private void danjishijian() {
        queren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (xiugaitext.getText().toString().trim()!=null && xiugaitext.getText().toString().trim().length()>0){
                    if (xiugai.equals("xingming")){
                        dao.open();
                        dao.update_xx_xingming(zhanghao,xiugaitext.getText().toString().trim());
                        dao.close();
                    }
                }
            }
        });
    }

    private void panduan() {
        if (xiugai.equals("xingming")){
            xiugainame.setText("姓名：");
        }
    }

    private void initView() {
        xiugainame = findViewById(R.id.textView24);
        xiugaitext = findViewById(R.id.editTextTextPersonName2);
        queren = findViewById(R.id.button8);
        dao = new Dao(this);
        dao.open();
        zhanghao = dao.query_dq("1");
        dao.close();
    }
}