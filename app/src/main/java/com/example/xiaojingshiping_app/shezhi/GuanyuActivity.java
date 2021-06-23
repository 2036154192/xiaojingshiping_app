package com.example.xiaojingshiping_app.shezhi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiaojingshiping_app.R;

public class GuanyuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guanyu);
        biaotilan();
    }
    private void biaotilan() {
        ImageView fanhui = findViewById(R.id.imageView3);
        TextView biaoti = findViewById(R.id.textView3);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        biaoti.setText("关于");
    }
}