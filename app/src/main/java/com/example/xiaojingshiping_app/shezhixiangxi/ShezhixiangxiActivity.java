package com.example.xiaojingshiping_app.shezhixiangxi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiaojingshiping_app.R;
import com.example.xiaojingshiping_app.sql.Dao;

public class ShezhixiangxiActivity extends AppCompatActivity {

    private ImageView touxiang;
    private TextView xingming;
    private TextView zhanghao;
    private TextView xingbie;
    private Dao dao;
    private String[] xingxi;
    private int xingbietext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shezhixiangxi);
        initView();
        quzhi();
        fuzhi();
        danjishijian();
    }

    private void danjishijian() {

        xingming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShezhixiangxiActivity.this,XiugaiActivity.class);
                intent.putExtra("xiugai","xingming");
                startActivity(intent);
            }
        });
        xingbie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(ShezhixiangxiActivity.this)
                        .setTitle("请选择性别")
                        .setIcon(R.drawable.xingbie)
                        .setSingleChoiceItems(new String[]{"女","男","未知"}, xingbietext, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dao.open();
                                String xingbie3 = String.valueOf(which);
                                dao.update_xx_xingbie(xingxi[0],xingbie3);
                                dao.close();
                            }
                        })
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(ShezhixiangxiActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
                }
        });

    }

    private void fuzhi() {
        xingming.setText(xingxi[1]);
        zhanghao.setText(xingxi[0]);
        xingbietext = Integer.parseInt(xingxi[3]);
        if (xingbietext==0){
            xingbie.setText("女");
        }else if (xingbietext==1){
            xingbie.setText("男");
        }else {
            xingbie.setText("未知");
        }
    }

    private void quzhi() {
        dao.open();
        String zhanghao1 = dao.query_dq("1");
        if (dao.query_sc_all(zhanghao1)!=null){
            xingxi = dao.query_xx_all(zhanghao1);
        }
        dao.close();
    }

    private void initView() {
        touxiang = findViewById(R.id.imageView16);
        xingming = findViewById(R.id.textView18);
        zhanghao = findViewById(R.id.textView19);
        xingbie = findViewById(R.id.textView20);
        dao = new Dao(this);
    }

}