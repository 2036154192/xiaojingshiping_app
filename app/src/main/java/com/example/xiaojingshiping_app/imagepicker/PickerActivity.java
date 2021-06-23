package com.example.xiaojingshiping_app.imagepicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.dueeeke.videoplayer.util.L;
import com.example.xiaojingshiping_app.MainActivity;
import com.example.xiaojingshiping_app.R;

import java.util.ArrayList;
import java.util.List;

public class PickerActivity extends AppCompatActivity implements imageListAdapter.OnItemSelectedChangeListener {

    public static final int LOADER_ID = 1;
    private List<ImageItem> mImageItem = new ArrayList<>();
    private imageListAdapter adapter;
    private String TAG = "PickerActivity";
    private TextView finsish;
    private PickerConfig pickerConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picker);
        initLoaderManager();
        intiView();
        initEvent();
        initConfig();
    }

    private void initConfig() {
        pickerConfig = PickerConfig.getInstance();
        int maxSelectedCount = pickerConfig.getMaxSelectedCount();
        adapter.setMaxSelectedCount(maxSelectedCount);
    }

    private void initEvent() {
        adapter.setItemSelectedChangeListener(this);
        finsish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取到说选择的数据
                List<ImageItem> result = adapter.getmSelectList();
                //通知其他地方
                PickerConfig.OnImagesSelectedFinishedListener imagesSelectedFinishedListener = pickerConfig.getOnImagesSelectedFinishedListener();
                if (imagesSelectedFinishedListener != null) {
                    imagesSelectedFinishedListener.onSelectedFinished(result);
                }
                //结束界面
                finish();
            }
        });
    }

    private void intiView() {
        finsish = findViewById(R.id.finish_tv);
        RecyclerView listView = findViewById(R.id.image_list_view);
        listView.setLayoutManager(new GridLayoutManager(this,3));
        //设置适配器
        adapter = new imageListAdapter();
        listView.setAdapter(adapter);
    }

    private void initLoaderManager() {
        mImageItem.clear();
        LoaderManager loaderManager = LoaderManager.getInstance(this);
        loaderManager.initLoader(LOADER_ID, null, new LoaderManager.LoaderCallbacks<Cursor>() {
            @NonNull
            @Override
            public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
                if (id == LOADER_ID){
                    return new CursorLoader(PickerActivity.this, MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            new String[]{"_data","_display_name","date_added"},null,null,"date_added desc");
                }
                return null;
            }

            @Override
            public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
                if (cursor != null){
                    while (cursor.moveToNext()){
                        String pase = cursor.getString(0);
                        String tatie= cursor.getString(1);
                        long date = cursor.getLong(2);
                        ImageItem imageItem = new ImageItem(pase,tatie,date);
                        mImageItem.add(imageItem);
                    }
                    cursor.close();
                    adapter.setData(mImageItem);
                }
            }

            @Override
            public void onLoaderReset(@NonNull Loader<Cursor> loader) {

            }
        });
    }

    @Override
    public void onItemSelectedChange(List<ImageItem> selectedItems) {
        //所选择的数据发生变化
        finsish.setText("("+selectedItems.size()+"/"+adapter.getMaxSelectedCount()+")完成");
    }
}