package com.example.xiaojingshiping_app.shouye;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xiaojingshiping_app.R;

public class Shouye_2_Fragment extends Fragment {

    private Shouye2ViewModel mViewModel;

    public static Shouye_2_Fragment newInstance() {
        return new Shouye_2_Fragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.shouye_2__fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(Shouye2ViewModel.class);
        // TODO: Use the ViewModel
    }

}