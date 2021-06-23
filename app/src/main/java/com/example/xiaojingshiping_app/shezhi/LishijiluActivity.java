package com.example.xiaojingshiping_app.shezhi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiaojingshiping_app.R;
import com.example.xiaojingshiping_app.activity.BoFangActivity;
import com.example.xiaojingshiping_app.shouye.ItemBean;
import com.example.xiaojingshiping_app.sql.Dao;

import java.util.ArrayList;
import java.util.List;

public class LishijiluActivity extends AppCompatActivity {

    int[] im={R.drawable.ds0,R.drawable.ds1,R.drawable.ds2,R.drawable.ds3
            ,R.drawable.ds4,R.drawable.ds5,R.drawable.ds6,R.drawable.ds7,R.drawable.ds8,R.drawable.ds9};
    String[] names ={"仙剑奇侠传","长河落日","夏至末至","金玉满堂",
            "四大名捕","亮剑","汉武大帝","我是特征兵","东北一家人","隐秘的角落"};
    private RecyclerView recyclerView;
    private LSAdapter adapter;
    private List<ItemBean> mData = new ArrayList<>();
    private Dao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lishijilu);
        biaotilan();
        liebiao();
        qingkongshuju();
    }

    private void qingkongshuju() {
        Button button = findViewById(R.id.button7);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog;
                dialog = new AlertDialog.Builder(LishijiluActivity.this).setTitle("提示框")
                        .setMessage("确认清空记录")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dao.open();
                                String name = dao.query_dq("1");
                                dao.delete_ls_all(name);
                                dao.close();
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
    }

    private void liebiao() {
        recyclerView = findViewById(R.id.recycler_ls);
        initData();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new LSAdapter(this,mData);
        recyclerView.setAdapter(adapter);
        initListnenr();
    }

    private void initData() {
        mData = new ArrayList<>();
        List<ItemBean> a = new ArrayList();
        dao = new Dao(this);
        dao.open();
        String name = dao.query_dq("1");
        if (dao.query_ls_all(name)!=null){
            a.addAll(dao.query_ls_all(name));
            for (int i=0;i<a.size();i++){
                ItemBean itemBean = a.get(i);
                int xiabiao = itemBean.getImageView();
                itemBean.setImageView(im[xiabiao]);
                itemBean.setName(names[xiabiao]);
                mData.add(itemBean);
            }
        }
        dao.close();
    }

    private void initListnenr() {
        adapter.setOnItemClickListener(new LSAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String table, int position) {
                Intent intent = new Intent(LishijiluActivity.this, BoFangActivity.class);
                intent.putExtra("xuanzhe",position);
                startActivity(intent);
            }
        });
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
        biaoti.setText("历史记录");
    }
}