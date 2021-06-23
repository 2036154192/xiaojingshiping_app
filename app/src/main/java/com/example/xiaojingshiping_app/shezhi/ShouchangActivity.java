package com.example.xiaojingshiping_app.shezhi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiaojingshiping_app.R;
import com.example.xiaojingshiping_app.activity.BoFangActivity;
import com.example.xiaojingshiping_app.shouye.ItemBean;
import com.example.xiaojingshiping_app.sql.Dao;

import java.util.ArrayList;
import java.util.List;

public class ShouchangActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<ItemBean> mData;
    String[] b ={"xianjian","changhe","xiazhi","jingyu",
            "shida","liangjian","hanwu","woshi","dongbei","yingmi"};
    int[] im={R.drawable.ds0,R.drawable.ds1,R.drawable.ds2,R.drawable.ds3
            ,R.drawable.ds4,R.drawable.ds5,R.drawable.ds6,R.drawable.ds7,R.drawable.ds8,R.drawable.ds9};
    String[] names ={"仙剑奇侠传","长河落日","夏至末至","金玉满堂",
            "四大名捕","亮剑","汉武大帝","我是特征兵","东北一家人","隐秘的角落"};
    private SCAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shouchang);
        biaotilan();
        liebiao();
    }

    private void liebiao() {
        recyclerView = findViewById(R.id.recycler_sc);
        initData();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new SCAdapter(this,mData);
        recyclerView.setAdapter(adapter);
        initListnenr();
    }

    private void initListnenr() {
        adapter.setOnItemClickListener(new SCAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String table, int position) {
                Intent intent = new Intent(ShouchangActivity.this,BoFangActivity.class);
                intent.putExtra("xuanzhe",position);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        List a = new ArrayList();
        mData = new ArrayList<>();
        Dao dao = new Dao(this);
        dao.open();
        String zhanghao = dao.query_dq("1");
        a.addAll(dao.query_sc_all(zhanghao));
        dao.close();
        for (int k=0;k<a.size();k++){
            for (int i=0;i<b.length;i++){
                if (a.get(k).equals(b[i])){
                    ItemBean itemBean = new ItemBean();
                    itemBean.setName(names[i]);
                    itemBean.setImageView(im[i]);
                    itemBean.setText("十分大师傅士大夫");
                    mData.add(itemBean);
                }
            }
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
        biaoti.setText("收藏");
    }
}