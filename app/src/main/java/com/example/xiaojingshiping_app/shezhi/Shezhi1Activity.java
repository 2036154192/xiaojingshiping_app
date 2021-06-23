package com.example.xiaojingshiping_app.shezhi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiaojingshiping_app.MainActivity;
import com.example.xiaojingshiping_app.R;
import com.example.xiaojingshiping_app.shezhixiangxi.ShezhixiangxiActivity;
import com.example.xiaojingshiping_app.shouye.ItemBean;
import com.example.xiaojingshiping_app.sql.Dao;

import java.util.ArrayList;
import java.util.List;

public class Shezhi1Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private String[] text = {"账户信息"};
    private List<ItemBean> mData;
    private Shezhi1Adapt adapt;
    private Dao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shezhi);
        biaotilan();
        initView();
        tuicudonglu();
    }

    private void tuicudonglu() {
        Button tuichudonglu = findViewById(R.id.button5);
        dao = new Dao(Shezhi1Activity.this);
        dao.open();
        String a = dao.query_dq("1");
        dao.close();
        if (a!=null && a.length()>0){
            tuichudonglu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog dialog;
                    dialog = new AlertDialog.Builder(Shezhi1Activity.this).setTitle("提示框")
                            .setMessage("确认退出登录")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dao.open();
                                    dao.update_dq("1","");
                                    dao.close();
                                    finishAffinity();
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .create();
                    dialog.show();
                }
            });
        }else {
            Toast.makeText(Shezhi1Activity.this,"现在是游客模式",Toast.LENGTH_SHORT).show();
        }
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerView);
        initData();
        LinearLayoutManager manager = new LinearLayoutManager(Shezhi1Activity.this);
        recyclerView.setLayoutManager(manager);
        adapt = new Shezhi1Adapt(mData);
        recyclerView.setAdapter(adapt);
        initListener();
    }

    private void initListener() {
        adapt.setOnItemClickListener(new Shezhi1Adapt.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(Shezhi1Activity.this, ShezhixiangxiActivity.class);
                startActivity(intent);
            }
        });
    }


    private void initData() {
        mData = new ArrayList<>();
        for (int i=0;i<text.length;i++){
            ItemBean itemBean = new ItemBean();
            itemBean.setText(text[i]);
            mData.add(itemBean);
        }
    }

    private void biaotilan() {
        ImageView fanhui = findViewById(R.id.imageView3);
        TextView biaoti = findViewById(R.id.textView3);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        biaoti.setText("设置");
    }
}