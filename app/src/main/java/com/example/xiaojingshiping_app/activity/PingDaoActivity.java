package com.example.xiaojingshiping_app.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiaojingshiping_app.R;
import com.example.xiaojingshiping_app.nractivity.SecondFragment;
import com.example.xiaojingshiping_app.shouye.GridlayoutAdapter;
import com.example.xiaojingshiping_app.shouye.ItemBean;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class PingDaoActivity extends AppCompatActivity {

    private TextView text_name;
    private String name;
    private ImageView imageView;
    private int pingdao;
    private RefreshLayout refreshlayout1;
    private List<ItemBean> mData;
    private RecyclerView rectangles1;
    private GridlayoutAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ping_dao);
        Intent intent =getIntent();
        name = intent.getStringExtra("name");
        pingdao = intent.getIntExtra("pingdao",0);
        initView();
        pdpdgzy();
        table_sezhi();
    }

    private void initData(int[] a,String[] b) {
        mData = new ArrayList<>();
        for (int i =0;i<10;i++){
            ItemBean itemBean = new ItemBean();
            itemBean.setImageView(a[i]);
            itemBean.setName(b[i]);
            mData.add(itemBean);
        }
    }

    private void showGrid(boolean isVertical , boolean isReverse) {
        //?????????????????????
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        //????????????????????????
        gridLayoutManager.setOrientation(isVertical ? GridLayoutManager.VERTICAL : GridLayoutManager.HORIZONTAL);
        //????????????
        gridLayoutManager.setReverseLayout(isReverse);
        //?????????????????????
        rectangles1.setLayoutManager(gridLayoutManager);
        //???????????????
        adapter = new GridlayoutAdapter(mData);
        //???????????????
        rectangles1.setAdapter(adapter);
    }

    private void pdpdgzy() {
        switch (pingdao){
            case 1:
                dianshiju();
                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;
            case 6:

                break;
            default:

                break;
        }
    }

    private void dianshiju() {
        //???????????????
        int[] a={R.drawable.ds0,R.drawable.ds1,R.drawable.ds2,R.drawable.ds3
                ,R.drawable.ds4,R.drawable.ds5,R.drawable.ds6,R.drawable.ds7,R.drawable.ds8,R.drawable.ds9};
        String[] b ={"???????????????","????????????","????????????","????????????",
                "????????????","??????","????????????","???????????????","???????????????","???????????????"};
        initData(a,b);
        //????????????????????????
        showGrid(true,false);
        //?????????????????????
        initListener();
        shanglashuaxin();
        xialajiazhai();
    }

    private void initListener() {
        adapter.setOnItemClickListener(new GridlayoutAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //????????????????????????
                Intent intent = new Intent(PingDaoActivity.this,BoFangActivity.class);
                intent.putExtra("pingdao",position);
                startActivity(intent);
            }
        });
    }

    private void shanglashuaxin(){
        refreshlayout1.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshlayout1.finishRefresh(true);
                Toast.makeText(PingDaoActivity.this,"??????",Toast.LENGTH_SHORT).show();
                refreshlayout1.finishRefresh(false);
            }
        });
    }

    private void xialajiazhai(){
        refreshlayout1.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshlayout1.finishLoadMore(2000);
                Toast.makeText(PingDaoActivity.this,"?????????",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void table_sezhi() {
        text_name.setText(name);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        rectangles1 = findViewById(R.id.rectangles1);
        refreshlayout1 = findViewById(R.id.refreshlayout1);
        text_name = findViewById(R.id.textView3);
        imageView = findViewById(R.id.imageView3);
    }
}