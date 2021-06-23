package com.example.xiaojingshiping_app.nractivity;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiaojingshiping_app.R;
import com.example.xiaojingshiping_app.activity.PingDaoActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SecondFragment extends Fragment {

    private SecondViewModel mViewModel;
    private static List<ImageView> maomi ;
    private View view;
    private List<View> dots;
    private ViewPager mViewPaper;
    private int currentItem;
    //记录上一次点的位置
    private int oldPosition = 0;
    //存放图片的id
    private int[] imageIds = new int[]{
            R.drawable.maomi1,
            R.drawable.maomi2,
            R.drawable.maomi3,
    };
    //存放图片的标题
    private String[] titles = new String[]{
            "轮播1",
            "轮播2",
            "轮播3"
    };
    private int[] imgae_ids = new int[]{R.id.pager_image1,R.id.pager_image2,R.id.pager_image3};
    private TextView title;
    private ViewPagerAdapter adapter;
    private ScheduledExecutorService scheduledExecutorService;
    private GridView gridView;


    public static SecondFragment newInstance() {
        return new SecondFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.second_fragment, container, false);
        setView();
        liebiao();
        return view;
    }

    private void setView(){
        mViewPaper = (ViewPager)view.findViewById(R.id.vp);

        //显示的图片
        maomi = new ArrayList<ImageView>();
        for(int i = 0; i < imageIds.length; i++){
            ImageView imageView = new ImageView(getActivity());
            imageView.setBackgroundResource(imageIds[i]);
            imageView.setId(imgae_ids[i]);
            imageView.setOnClickListener(new pagerImageOnClick());
            maomi.add(imageView);
        }
        //显示的小点
        dots = new ArrayList<View>();
        dots.add(view.findViewById(R.id.dot_0));
        dots.add(view.findViewById(R.id.dot_1));
        dots.add(view.findViewById(R.id.dot_2));
        title = (TextView) view.findViewById(R.id.title);
        title.setText(titles[0]);

        adapter = new ViewPagerAdapter();
        mViewPaper.setAdapter(adapter);
        mViewPaper.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                title.setText(titles[position]);
                dots.get(position).setBackgroundResource(R.drawable.ic_baseline_brightness_1_24);
                dots.get(oldPosition).setBackgroundResource(R.drawable.ic_baseline_brightness_1_23);

                oldPosition = position;
                currentItem = position;
            }
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }

        });

    }
    /*定义的适配器*/
    public class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return maomi.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup view, int position, Object object) {
            // TODO Auto-generated method stub
            view.removeView(maomi.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            // TODO Auto-generated method stub
            view.addView(maomi.get(position));
            return maomi.get(position);
        }

    }
    private class pagerImageOnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.pager_image1:
                    Toast.makeText(getContext(), "图片1被点击", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.pager_image2:
                    Toast.makeText(getContext(), "图片2被点击", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.pager_image3:
                    Toast.makeText(getContext(), "图片3被点击", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
    /**
     * 利用线程池定时执行动画轮播
     */
    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(
                new ViewPageTask(),
                2,
                2,
                TimeUnit.SECONDS);
    }
    /**
     * 图片轮播任务
     * @author liuyazhuang
     *
     */
    private class ViewPageTask implements Runnable{

        @Override
        public void run() {
            currentItem = (currentItem + 1) % imageIds.length;
            mHandler.sendEmptyMessage(0);
        }
    }
    /**
     * 接收子线程传递过来的数据
     */
    private Handler mHandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            mViewPaper.setCurrentItem(currentItem);
        };
    };
    @Override
    public void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        if(scheduledExecutorService != null){
            scheduledExecutorService.shutdown();
            scheduledExecutorService = null;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SecondViewModel.class);
        // TODO: Use the ViewModel
    }

    private void liebiao() {
        gridView = view.findViewById(R.id.gridVew1);
        //应用图片
         int[] mAppIcons = {R.drawable.dianshiju,R.drawable.dianying,R.drawable.dongman,R.drawable.jilupan,
                R.drawable.zhongyi,R.drawable.zhibo};
        //应用名字
         String[] mAppName = {"电视剧","电影","动漫","纪录片","综艺","直播"};

         List<Map<String,Object>> list = new ArrayList<>();
         for (int i=0;i< mAppIcons.length;i++){
             Map<String,Object> list1 = new HashMap<>();
             list1.put("icon",mAppIcons[i]);
             list1.put("name",mAppName[i]);
             list.add(list1);
         }
        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(),
                list,R.layout.layout_grid_item,new String[]{"icon","name"},new int[]{R.id.grid_IV_id,R.id.grid_text_id});
        gridView.setAdapter(simpleAdapter);
        gridView.setOnItemClickListener(((parent, view1, position, id) -> {
            Intent intent = new Intent(getContext(), PingDaoActivity.class);
            switch (position){
                case 0:
                    intent.putExtra("name","电视剧");
                    intent.putExtra("pingdao",1);
                    startActivity(intent);
                    break;
                case 1:
                    intent.putExtra("name","电影");
                    intent.putExtra("pingdao",2);
                    startActivity(intent);
                    break;
                case 2:
                    intent.putExtra("name","动漫");
                    intent.putExtra("pingdao",3);
                    startActivity(intent);
                    break;
                case 3:
                    intent.putExtra("name","纪录片");
                    intent.putExtra("pingdao",4);
                    startActivity(intent);
                    break;
                case 4:
                    intent.putExtra("name","综艺");
                    intent.putExtra("pingdao",5);
                    startActivity(intent);
                    break;
                case 5:
                    intent.putExtra("name","直播");
                    intent.putExtra("pingdao",6);
                    startActivity(intent);
                    break;
                default:
                    startActivity(intent);
                    break;
            }
        }));
    }

}