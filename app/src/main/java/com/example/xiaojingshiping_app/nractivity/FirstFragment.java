package com.example.xiaojingshiping_app.nractivity;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xiaojingshiping_app.R;
import com.example.xiaojingshiping_app.sousuo.SouSuoActivity;
import com.example.xiaojingshiping_app.shouye.Shouye_1_Fragment;
import com.example.xiaojingshiping_app.shouye.Shouye_2_Fragment;
import com.example.xiaojingshiping_app.shouye.Shouye_3_Fragment;
import com.example.xiaojingshiping_app.shouye.Shouye_4Fragment;
import com.example.xiaojingshiping_app.shouye.Shouye_5_Fragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class FirstFragment extends Fragment {

    private FirstViewModel mViewModel;
    private View view;
    private ViewPager2 viewPager2;
    private TabLayout tabLayout;
    private static String[] TAB_LABEL = {"热门","电视剧","电影","动漫","综艺"};
    private TextView sousuo;

    public static FirstFragment newInstance() {
        return new FirstFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.first_fragment, container,false);
        initView();
        initViewpage2();
        return view;
    }

    private void initViewpage2() {
        final ArrayList<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new Shouye_1_Fragment());
        fragmentList.add(new Shouye_2_Fragment());
        fragmentList.add(new Shouye_3_Fragment());
        fragmentList.add(new Shouye_4Fragment());
        fragmentList.add(new Shouye_5_Fragment());

        viewPager2.setAdapter(new FragmentStateAdapter(getParentFragmentManager(),getLifecycle()) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getItemCount() {
                return fragmentList.size();
            }
        });

        //关联并应用ViewPage2和Tab
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(TAB_LABEL[position]);
            }
        }).attach();
    }

    private void initView() {
        viewPager2 = view.findViewById(R.id.viwePager2);
        tabLayout = view.findViewById(R.id.tablayout);
        sousuo = view.findViewById(R.id.sousuo);
        sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SouSuoActivity.class));
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FirstViewModel.class);
        // TODO: Use the ViewModel

    }

}