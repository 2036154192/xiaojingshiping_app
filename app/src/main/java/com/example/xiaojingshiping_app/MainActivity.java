package com.example.xiaojingshiping_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiaojingshiping_app.activity.HomeActivity;
import com.example.xiaojingshiping_app.activity.LoginActivity;
import com.example.xiaojingshiping_app.activity.ZhuCheActivity;
import com.example.xiaojingshiping_app.sql.Dao;

public class MainActivity extends AppCompatActivity {

    private TextView youke;
    private Button zhuche;
    private Button denglu;
    private Dao dao;
    String[] sp_name ={"仙剑奇侠传","长河落日","夏至末至","金玉满堂",
            "四大名捕","亮剑","汉武大帝","我是特征兵","东北一家人","隐秘的角落"};
    String[] sp_id = {"0","1","2","3","4","5","6","7","8","9"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        dao = new Dao(this);
        dao.open();
        dao.insert_dq("1","");
        dao.close();
        initData();
        huoqushuju();
        buttonong();
        checkCalendarPermission();
    }

    private void initData() {
        dao.open();
        if (dao.query_sp("仙剑奇侠传").size()==0){
            for (int i = 0; i < sp_name.length; i++) {
                dao.insert_sp(sp_name[i],sp_id[i]);
            }
        }
        dao.close();
    }

    private void checkCalendarPermission() {
        int readPermission = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
        int writePermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (readPermission != PackageManager.PERMISSION_GRANTED && writePermission != PackageManager.PERMISSION_GRANTED){
            //去请求权限
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
    }

    //接收请求结果
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //判断结果
        if (requestCode == 1){
            if (grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
            }else {
                Toast.makeText(MainActivity.this,"无法更换头像",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void huoqushuju() {
        dao.open();
        String a = dao.query_dq("1");
        dao.close();
        if (a!=null && a.length()>0){
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
        }
    }

    private void buttonong() {
        denglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        zhuche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ZhuCheActivity.class);
                startActivity(intent);
            }
        });
        youke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }


    private void initView() {
        denglu = findViewById(R.id.button);
        zhuche = findViewById(R.id.button2);
        youke = findViewById(R.id.textView);
    }
}