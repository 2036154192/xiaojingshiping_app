package com.example.xiaojingshiping_app.imagepicker;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.xiaojingshiping_app.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class imageListAdapter extends RecyclerView.Adapter<imageListAdapter.InnerHolder> {

    private List<ImageItem> mImageItems = new ArrayList<>();
    private List<ImageItem> mSelectList = new ArrayList<>();
    private OnItemSelectedChangeListener mItemSelectedChangeListener = null;
    private static final int MAX_SELECTED_COUNT = 1;
    private int maxSelectedCount = MAX_SELECTED_COUNT;

    public int getMaxSelectedCount() {
        return maxSelectedCount;
    }

    public void setMaxSelectedCount(int maxSelectedCount) {
        this.maxSelectedCount = maxSelectedCount;
    }

    public List<ImageItem> getmSelectList() {
        return mSelectList;
    }

    public void setmSelectList(List<ImageItem> mSelectList) {
        this.mSelectList = mSelectList;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //加载
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        Point point = SizeUtls.getScreenSize(view.getContext());
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(point.x/3,point.y/3);
        view.setLayoutParams(layoutParams);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        //绑定数据
        View itemView = holder.itemView;
        ImageItem imageItem = mImageItems.get(position);
        CheckBox checkBox = itemView.findViewById(R.id.image_chec_box);
        View cover = itemView.findViewById(R.id.image_cover);
        ImageView imageView =itemView.findViewById(R.id.image_iv);
        Glide.with(imageView.getContext()).load(imageItem.getPath()).into(imageView);
        if (mSelectList.contains(imageItem)){
            //没有选上，选上
            mSelectList.add(imageItem);
            //修改UI
            cover.setVisibility(View.VISIBLE);
            checkBox.setChecked(false);
            checkBox.setButtonDrawable(itemView.getContext().getDrawable(R.mipmap.xuanzhong));
        }else {
            //已经选上，取消选择
            mSelectList.remove(imageItem);
            //修改UI
            cover.setVisibility(View.GONE);
            checkBox.setChecked(true);
            checkBox.setButtonDrawable(itemView.getContext().getDrawable(R.mipmap.weixuanzhong));
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSelectList.contains(imageItem)){
                    //已经选上，取消选择
                    mSelectList.remove(imageItem);
                    //修改UI
                    cover.setVisibility(View.GONE);
                    checkBox.setChecked(true);
                    checkBox.setButtonDrawable(itemView.getContext().getDrawable(R.mipmap.weixuanzhong));
                }else {
                    if (mSelectList.size()>=maxSelectedCount){
                        Toast toast = Toast.makeText(checkBox.getContext(),null,Toast.LENGTH_SHORT);
                        toast.setText("最大只能选择一张图片");
                        toast.show();
                        return;
                    }
                    //没有选上，选上
                    mSelectList.add(imageItem);
                    //修改UI
                    cover.setVisibility(View.VISIBLE);
                    checkBox.setChecked(false);
                    checkBox.setButtonDrawable(itemView.getContext().getDrawable(R.mipmap.xuanzhong));
                }
                if (mItemSelectedChangeListener!=null){
                    mItemSelectedChangeListener.onItemSelectedChange(mSelectList);
                }
            }
        });
    }

    public void setItemSelectedChangeListener(OnItemSelectedChangeListener listener){
        this.mItemSelectedChangeListener = listener;
    }

    public interface OnItemSelectedChangeListener{
        void onItemSelectedChange(List<ImageItem> selectedItems);
    }

    @Override
    public int getItemCount() {
        return mImageItems.size();
    }

    public void setData(List<ImageItem> mImageItem) {
        mImageItems.clear();
        mImageItems.addAll(mImageItem);
        notifyDataSetChanged();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
