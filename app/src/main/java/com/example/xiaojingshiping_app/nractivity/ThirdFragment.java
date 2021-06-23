package com.example.xiaojingshiping_app.nractivity;

import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.xiaojingshiping_app.MainActivity;
import com.example.xiaojingshiping_app.R;
import com.example.xiaojingshiping_app.User;
import com.example.xiaojingshiping_app.imagepicker.ImageItem;
import com.example.xiaojingshiping_app.imagepicker.PickerActivity;
import com.example.xiaojingshiping_app.imagepicker.PickerConfig;
import com.example.xiaojingshiping_app.shezhi.GuanyuActivity;
import com.example.xiaojingshiping_app.shezhi.LishijiluActivity;
import com.example.xiaojingshiping_app.shezhi.Shezhi1Activity;
import com.example.xiaojingshiping_app.shezhi.ShouchangActivity;
import com.example.xiaojingshiping_app.shouye.ItemBean;
import com.example.xiaojingshiping_app.shouye.SheZhiAdapter;
import com.example.xiaojingshiping_app.sql.Dao;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


public class ThirdFragment extends Fragment implements PickerConfig.OnImagesSelectedFinishedListener {

    private ThirdViewModel mViewModel;
    private View view;
    private TextView name;
    private ImageView tupian;
    private RecyclerView shezhi;
    private int[] images = {R.drawable.lishi,R.drawable.shouchang,R.drawable.shezhi,R.drawable.guanyu};
    private String[] biaoti = {"历史记录","收藏","设置","关于"};
    private List<ItemBean> mData;
    private SheZhiAdapter sheZhiAdapter;
    private static final int MAX_SELECTED_COUNT = 1;
    private int denglu = 0;
    private String txUrl;

    public static ThirdFragment newInstance() {
        return new ThirdFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.third_fragment, container, false);
        initView();
        xingxilan();
        dianjishijian();
        initPickerConfig();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (txUrl != null){
            Glide.with(getContext()).load(txUrl).into(tupian);
        }
    }

    private void initPickerConfig() {
        PickerConfig pickerConfig = PickerConfig.getInstance();
        pickerConfig.setMaxSelectedCount(MAX_SELECTED_COUNT);
        pickerConfig.setOnImagesSelectedFinishedListener(this);
    }

    private void dianjishijian() {
        tupian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (denglu == 1){
                    startActivity(new Intent(getContext(), PickerActivity.class));
                }else {
                    Toast.makeText(getContext(),"现在是游客模式",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void xingxilan() {
        Dao dao = new Dao(getContext());
        dao.open();
        String zhanghao = dao.query_dq("1");
        String a = dao.query_xingming(zhanghao);
        txUrl = dao.query_tx(zhanghao);
        dao.close();
        if (a!=null){
            name.setText(a);
            denglu = 1;
            if (txUrl.length()>0){
                Glide.with(getContext()).load(txUrl).into(tupian);
            }
        }else {
            name.setText("游客模式");
        }

    }

    private void initData() {
        mData = new ArrayList<>();
        for (int i=0;i<images.length;i++){
            ItemBean itemBean = new ItemBean();
            itemBean.setImageView(images[i]);
            itemBean.setName(biaoti[i]);
            mData.add(itemBean);
        }
    }

    private void initView() {
        name = view.findViewById(R.id.textView9);
        tupian = view.findViewById(R.id.imageView9);
        shezhi = view.findViewById(R.id.shezhi);
       initData();
       LinearLayoutManager manager = new LinearLayoutManager(getActivity());
       shezhi.setLayoutManager(manager);
        sheZhiAdapter = new SheZhiAdapter(getActivity(),mData);
        shezhi.setAdapter(sheZhiAdapter);
        initListnenr();
    }

    private void initListnenr() {
        sheZhiAdapter.setOnItemClickListener(new SheZhiAdapter.OnItemClickListener() {
            private Intent intent;

            @Override
            public void onItemClick(int position) {
                //这里处理点击事件
                switch (position){
                    case 0:
                         intent = new Intent(getContext(), LishijiluActivity.class);
                          startActivity(intent);
                        break;
                    case 1:
                         intent = new Intent(getContext(), ShouchangActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                         intent = new Intent(getContext(), Shezhi1Activity.class);
                        startActivity(intent);
                        break;
                    case 3:
                         intent = new Intent(getContext(), GuanyuActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ThirdViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onSelectedFinished(List<ImageItem> result) {
        //所选择的数据回来了
        for (ImageItem imageItem : result) {
            Log.d(TAG, "onSelectedFinished: "+imageItem);
        }
        if (result != null) {
            Glide.with(this).load(result.get(0).getPath()).into(tupian);
            Dao dao = new Dao(getContext());
            dao.open();
            String zhaoghao = dao.query_dq("1");
            dao.update_tx(zhaoghao,result.get(0).getPath());
            dao.close();
        }
    }
}