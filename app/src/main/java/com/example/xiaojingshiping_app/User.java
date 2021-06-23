package com.example.xiaojingshiping_app;

import androidx.lifecycle.ViewModel;

public class User extends ViewModel {
    private String zhanghao;
    private String mima;

    public User() { }

    public User(String zhanghao, String mima) {
        this.zhanghao = zhanghao;
        this.mima = mima;
    }

    public String getZhanghao() {
        return zhanghao;
    }

    public void setZhanghao(String zhanghao) {
        this.zhanghao = zhanghao;
    }

    public String getMima() {
        return mima;
    }

    public void setMima(String mima) {
        this.mima = mima;
    }
}
