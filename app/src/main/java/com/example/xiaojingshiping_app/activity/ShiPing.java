package com.example.xiaojingshiping_app.activity;

public class ShiPing {

    private String spname;
    private String spid;

    public ShiPing(String spname, String spid) {
        this.spname = spname;
        this.spid = spid;
    }

    public String getSpname() {
        return spname;
    }

    public void setSpname(String spname) {
        this.spname = spname;
    }

    public String getSpid() {
        return spid;
    }

    public void setSpid(String spid) {
        this.spid = spid;
    }
}
