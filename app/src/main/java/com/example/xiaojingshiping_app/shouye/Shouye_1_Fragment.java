package com.example.xiaojingshiping_app.shouye;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xiaojingshiping_app.R;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Shouye_1_Fragment extends Fragment {

    private Shouye1ViewModel mViewModel;
    private View view;
    private RecyclerView recyclerView;
    String[] name = {"小金","小银","小芳","小溪","小赵","张山","李四","丑金"};
    String[] tatle = {"这个好也","很好看","飞洒发生的方法","士大夫士大夫发射点","阿尔文广东省","很忐忑他","爱的色放","手动阀","大师风范"};
    int[] images={R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,R.drawable.f,R.drawable.h,R.drawable.i,R.drawable.g};
    private RefreshLayout refreshLayout;
    private VideoAdapter videoAdapter;

    public static Shouye_1_Fragment newInstance() {
        return new Shouye_1_Fragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.shouye_1__fragment, container, false);
        initView();
        return view;
    }

    private void initView() {
        recyclerView = view.findViewById(R.id.rectangles);
        refreshLayout = view.findViewById(R.id.refreshlayout);
        //布局管理器
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        //排列方式垂直
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        shezhishuju(0);
        xianlashuaxing();
    }

    private void xianlashuaxing() {
        //下拉刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                //刷新动画时间
                refreshLayout.finishRefresh(true);
                shezhishuju(0);
                refreshLayout.finishRefresh(false);
            }
        });
        //上拉加载
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(2000);
                int a = 1;
                shezhishuju(0);
                videoAdapter.setData(listss());
                a++;
            }
        });
    }

    private List<VideoEntiy> listss(){
        List<VideoEntiy> lists = new ArrayList<>();
        Random random  = new Random();
        for (int i=0;i<8;i++){
            int shiuji = random.nextInt(8);
            VideoEntiy entiy = new VideoEntiy();
            entiy.setName(name[shiuji]);
            entiy.setText(tatle[shiuji]);
            entiy.setImages(images[shiuji]);
            lists.add(entiy);
        }
        return lists;
    }
    private void shezhishuju(int a){
        List<VideoEntiy> lists = new ArrayList<>();
        Random random  = new Random();
        for (int i=0;i<8+a;i++){
            int shiuji = random.nextInt(8);
            VideoEntiy entiy = new VideoEntiy();
            entiy.setName(name[shiuji]);
            entiy.setText(tatle[shiuji]);
            entiy.setImages(images[shiuji]);
            lists.add(entiy);
        }
        videoAdapter = new VideoAdapter(getActivity(),lists);
        recyclerView.setAdapter(videoAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(Shouye1ViewModel.class);
        // TODO: Use the ViewModel
    }

}