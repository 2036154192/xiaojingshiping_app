package com.example.xiaojingshiping_app.shouye;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xiaojingshiping_app.R;


import java.util.List;

public class SheZhiAdapter extends RecyclerView.Adapter<SheZhiAdapter.InnerHolder> {

    private Context context;
    private List<ItemBean> data;
    private OnItemClickListener mOnItemClickListener;

    public SheZhiAdapter(Context context, List<ItemBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  View.inflate(parent.getContext(), R.layout.shezhi_layout,null);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        holder.setData(data.get(position),position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        //设置监听
        this.mOnItemClickListener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public class InnerHolder extends RecyclerView.ViewHolder {

        private ImageView tupian;
        private TextView textView;
        private int mPostion;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            tupian = itemView.findViewById(R.id.imageView10);
            textView = itemView.findViewById(R.id.textView10);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener!=null){
                        mOnItemClickListener.onItemClick(mPostion);
                    }
                }
            });
        }

        public void setData(ItemBean itemBean,int postion) {
            this.mPostion = postion;
            tupian.setImageResource(itemBean.getImageView());
            textView.setText(itemBean.getName());
        }
    }
}
