package com.example.xiaojingshiping_app.activity;

import androidx.annotation.NonNull;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dueeeke.videocontroller.StandardVideoController;
import com.dueeeke.videoplayer.player.VideoView;
import com.example.xiaojingshiping_app.R;
import com.example.xiaojingshiping_app.sql.Dao;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class BoFangActivity extends Activity {

    private ImageView imageView;
    private TextView table;
    private EditText pinglun;
    private Button button;
    private SmartRefreshLayout bo_fang_refreshLayout;
    private ListView comment_list;
    private List<Comment> data1;
    private CommentAdapter adapterComment;
    private VideoView videoView;
    private static final String url = "https://vfx.mtime.cn/Video/2019/02/04/mp4/190204084208765161.mp4";
    private String[] shiping = {"https://vfx.mtime.cn/Video/2019/02/04/mp4/190204084208765161.mp4","http://vfx.mtime.cn/Video/2019/03/19/mp4/190319212559089721.mp4"
    ,"http://vfx.mtime.cn/Video/2019/03/18/mp4/190318231014076505.mp4","http://vfx.mtime.cn/Video/2019/03/18/mp4/190318214226685784.mp4"
    ,"http://vfx.mtime.cn/Video/2019/03/19/mp4/190319104618910544.mp4","http://vfx.mtime.cn/Video/2019/03/19/mp4/190319125415785691.mp4"
    ,"http://vfx.mtime.cn/Video/2019/03/17/mp4/190317150237409904.mp4","http://vfx.mtime.cn/Video/2019/03/14/mp4/190314102306987969.mp4"
    ,"http://vfx.mtime.cn/Video/2019/03/13/mp4/190313094901111138.mp4","http://vfx.mtime.cn/Video/2019/03/12/mp4/190312143927981075.mp4"
    ,"http://vfx.mtime.cn/Video/2019/03/12/mp4/190312083533415853.mp4","https://vfx.mtime.cn/Video/2019/02/04/mp4/190204084208765161.mp4"};
    String[] d ={"xianjian","changhe","xiazhi","jingyu",
            "shida","liangjian","hanwu","woshi","dongbei","yingmi"};
    String[] b ={"仙剑奇侠传","长河落日","夏至末至","金玉满堂",
            "四大名捕","亮剑","汉武大帝","我是特征兵","东北一家人","隐秘的角落"};
    private Dao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bo_fang);
        initView();
        Intent intent = getIntent();
        int pingdao = intent.getIntExtra("pingdao",0);
        int xuanzhe = intent.getIntExtra("xuanzhe",100);
        if (xuanzhe!=100){
            panduanshiping(xuanzhe);
        }
        panduanshiping(pingdao);

    }


    private void bofangqi(int url) {
        videoView.setUrl(shiping[url]); //设置视频地址
        StandardVideoController controller = new StandardVideoController(this);
        controller.addDefaultControlComponent(b[url], false);
        videoView.setVideoController(controller); //设置控制器
        videoView.start(); //开始播放，不调用则不自动播放
    }
    @Override
    protected void onPause() {
        super.onPause();
        videoView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoView.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoView.release();
    }


    @Override
    public void onBackPressed() {
        if (!videoView.onBackPressed()) {
            super.onBackPressed();
        }
    }

    public void panduanshiping(int pingdao) {
        switch (pingdao){
            case 0:
                shoping1(d[0],0);
                break;
            case 1:
                shoping1(d[1],1);
                break;
            case 2:
                shoping1(d[2],2);
                break;
            case 3:
                shoping1(d[3],3);
                break;
            case 4:
                shoping1(d[4],4);
                break;
            case 5:
                shoping1(d[5],5);
                break;
            case 6:
                shoping1(d[6],6);
                break;
            case 7:
                shoping1(d[7],7);
                break;
            case 8:
                shoping1(d[8],8);
                break;
            case 9:
                shoping1(d[9],9);
                break;
            default:
        }
    }

    private void shoping1(String name,int url) {
        initData(name);
        tianjiashuju(name);
        shanglashuaxin();
        xialajiazhai();
        bofangqi(url);
        shouchang(url);
        lishijilu(name,url);
    }

    private void lishijilu(String name,int url) {
        dao.open();
        String zhaohang = dao.query_dq("1");
        Date date = new Date();
        String time = date.toLocaleString();
        if (dao.query_ls(zhaohang,name)){
            dao.delete_ls(zhaohang,name);
            dao.insert_ls(zhaohang,name,time,url);
        }else {
            dao.insert_ls(zhaohang,name,time,url);
        }
        dao.close();
    }

    private void shouchang(int url) {
        ImageView shouchang = findViewById(R.id.imageView14);
        int[] images = {R.drawable.shouchang,R.drawable.shouchangchengg};
        int b = 0;
        dao = new Dao(BoFangActivity.this);
        dao.open();
        String dizhi = dao.query_dq("1");
        String a = dao.query_sc(d[url],dizhi);
        if (dizhi.length()>0){
            if (a.equals("meixuan")){
                shouchang.setImageResource(images[0]);
                b=2;
            }else {
                shouchang.setImageResource(images[1]);
                b=1;
            }
        }
        dao.close();
        int finalB = b;
        shouchang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dizhi.length()>0){
                    Toast.makeText(BoFangActivity.this,"现在是游客模式",Toast.LENGTH_SHORT).show();
                }else {
                    switch (finalB){
                        case 1:
                            shouchang.setImageResource(images[0]);
                            dao.open();
                            String a = dao.query_dq("1");
                            dao.update_sc(a,d[url],"meixuan");
                            dao.close();
                            break;
                        case 2:
                            shouchang.setImageResource(images[1]);
                            dao.open();
                            String a1 = dao.query_dq("1");
                            dao.update_sc(a1,d[url],"shouchang");
                            dao.close();
                            break;
                    }
                }
            }
        });
    }
    private void initData(String name) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendComment(name);
            }
        });

    }
    /**
     * 发送评论
     */
    public void sendComment(String name) {
        if (pinglun.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "评论不能为空！", Toast.LENGTH_SHORT).show();
        } else {
            // 生成评论数据
            Dao dao = new Dao(this);
            dao.open();
            String user = dao.query_dq("1");
            dao.close();
            Comment comment = new Comment();
            comment.setName(user + "：");
            comment.setPinglun(pinglun.getText().toString());
            adapterComment.addComment(comment);
            String b = pinglun.getText().toString().trim();
            // 发送完，清空输入框;
            pinglun.setText("");
            Toast.makeText(getApplicationContext(), "评论成功！", Toast.LENGTH_SHORT).show();
            dao.open();
            if (user !=null && user.length()>0){
                user = dao.query_dq("1");
            }else {
                user="xiaojin";
            }
            dao.insertpinglun(user,b,name);
            dao.close();
        }
    }


    private void initView() {
        imageView = findViewById(R.id.imageView3);
        table = requireViewById(R.id.textView3);
        videoView = findViewById(R.id.prepare_view);
        table.setText("播放");
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        pinglun = findViewById(R.id.textView6);
        button = findViewById(R.id.button6);
        bo_fang_refreshLayout = findViewById(R.id.bo_fang_refreshLayout);
        //初始化评论列表
        comment_list = findViewById(R.id.list_item_view);
        //初始化数据
        data1 = new ArrayList<>();
        //初始化适配器
        adapterComment = new CommentAdapter(getApplicationContext(), data1);
        //为评论列表设置适配器
        comment_list.setAdapter(adapterComment);
    }

    private void tianjiashuju(String dizhi) {
        Dao dao = new Dao(this);
        dao.open();
        if (dao.querypl(dizhi)!=null){
            List<Comment> data = new ArrayList<>();
            data.addAll(dao.querypl(dizhi));
            adapterComment.addAll(data);
        }
        dao.close();
    }

    private void shanglashuaxin(){
        bo_fang_refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                bo_fang_refreshLayout.finishRefresh(true);
                Toast.makeText(BoFangActivity.this,"刷新",Toast.LENGTH_SHORT).show();
                bo_fang_refreshLayout.finishRefresh(false);
            }
        });
    }

    private void xialajiazhai(){
        bo_fang_refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                bo_fang_refreshLayout.finishLoadMore(2000);
                Toast.makeText(BoFangActivity.this,"到底了",Toast.LENGTH_SHORT).show();
            }
        });
    }


}