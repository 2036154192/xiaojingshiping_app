package com.example.xiaojingshiping_app.shouye;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xiaojingshiping_app.R;


import java.util.List;

public class GridlayoutAdapter extends RecyclerView.Adapter<GridlayoutAdapter.InnerHolder> {

    private List<ItemBean> mData;
    private OnItemClickListener mOnItemClickListener;

    public GridlayoutAdapter(List<ItemBean> data){
        this.mData = data;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //创建条目Ui
        View view = View.inflate(parent.getContext(), R.layout.item_grid_view,null);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        //绑定数据
        holder.setData(mData.get(position),position);
    }

    @Override
    public int getItemCount() {
        if (mData != null){
            return mData.size();
        }
        return 0;
    }

    public void setOnItemClickListener(OnItemClickListener listerner) {
        //设置一个监听器，其实是设置一个接口，一个回调接口
        this.mOnItemClickListener = listerner;
    }

    /*
    * 编写回调的步骤
    * 创建这个接口
    * 定义接口内部方法
    * 提供设置接口的方法（实现）
    * 接口方法的使用
    * */

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public class InnerHolder extends RecyclerView.ViewHolder {

        private  TextView title;
        private  ImageView imageView;
        private int mPostion;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.grid_view_text);
            imageView = itemView.findViewById(R.id.grid_view_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener!=null){
                        mOnItemClickListener.onItemClick(mPostion);
                    }
                }
            });
        }
            //设置数据
        public void setData(ItemBean itemBean,int position) {
            this.mPostion = position;
            title.setText(itemBean.getName());
            imageView.setImageResource(itemBean.getImageView());
        }
    }
}
