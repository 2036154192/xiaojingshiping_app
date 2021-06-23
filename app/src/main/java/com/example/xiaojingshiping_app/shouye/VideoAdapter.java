package com.example.xiaojingshiping_app.shouye;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xiaojingshiping_app.R;

import java.util.List;


public class VideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<VideoEntiy> mLists;

    public VideoAdapter(Context context, List<VideoEntiy> lists) {
        this.mContext = context;
        this.mLists = lists;
    }

    public void setData(List<VideoEntiy> lists){
        if (lists!=null && lists.size()>0){
            mLists.addAll(lists);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.tuijian_nuirong,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        VideoEntiy entiy = mLists.get(position);
        viewHolder.biaoti.setText(entiy.getText());
        viewHolder.name.setText(entiy.getName());
        viewHolder.imageView.setImageResource(entiy.getImages());
    }

    @Override
    public int getItemCount() {
        return mLists.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private  TextView name;
        private  TextView biaoti;
        private    ImageView imageView;

        public ViewHolder(@NonNull View view) {
            super(view);
            biaoti = view.findViewById(R.id.textView5);
            name = view.findViewById(R.id.textView7);
            imageView = view.findViewById(R.id.imageView7);
        }
    }
}
