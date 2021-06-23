package com.example.xiaojingshiping_app.sousuo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.xiaojingshiping_app.R;
import com.example.xiaojingshiping_app.activity.BoFangActivity;
import com.example.xiaojingshiping_app.activity.ShiPing;
import com.example.xiaojingshiping_app.shezhi.ShouchangActivity;
import com.example.xiaojingshiping_app.sql.Dao;

import java.util.ArrayList;
import java.util.List;

public class SouSuoActivity extends AppCompatActivity {

    private EditText sousuo;
    private RecyclerView list_sousuo;
    private Dao dao;
    private List<ShiPing> mData = new ArrayList<>();
    private SSAdapter adapter;
    private TextView quxao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sou_suo);
        initView();
        huoche();
    }

    private void huoche() {

        quxao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //设置键盘回车事件
        sousuo.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    if (sousuo.length()>=0){
                        Intent intent = new Intent(SouSuoActivity.this,ListActivity.class);
                        intent.putExtra("name",sousuo.getText().toString().trim());
                        startActivity(intent);
                    }
                }
                return false;
            }
        });
        //输入框变化后调用
        sousuo.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length()==0){
                    adapter.mData_create();
                }else {
                    huoquxianshi(s.toString());
                }
            }
        });
    }

    private void huoquxianshi(String a) {
        dao.open();
        mData = dao.query_sp(a);
        dao.close();
        adapter.mData_create();
        adapter.setmData(mData);
    }

    private void initView() {
        dao = new Dao(this);
        quxao = findViewById(R.id.quxiao);
        sousuo = findViewById(R.id.sousuo);
        list_sousuo = findViewById(R.id.list_sousuo);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SouSuoActivity.this);
        list_sousuo.setLayoutManager(layoutManager);
        adapter = new SSAdapter();
        adapter.setmData(mData);
        list_sousuo.setAdapter(adapter);
        adapter.setOnItemClickListener(new SSAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String id,String name) {
                sousuo.setText(name);
                Intent intent = new Intent(SouSuoActivity.this, BoFangActivity.class);
                intent.putExtra("xuanzhe",id);
                startActivity(intent);
            }
        });
    }



}