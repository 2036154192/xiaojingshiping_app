package com.example.xiaojingshiping_app.shezhi;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xiaojingshiping_app.R;
import com.example.xiaojingshiping_app.shouye.ItemBean;

import java.util.List;

public class SCAdapter extends RecyclerView.Adapter<SCAdapter.InnerHolder> {

    private Context context;
    private List<ItemBean> data;
    private OnItemClickListener mOnItemClickListener;

    public SCAdapter(android.content.Context context, List<ItemBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = View.inflate(parent.getContext(), R.layout.sc_layout,null);
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
        void onItemClick(String table, int position);
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private int mPostion;
        private ImageView imageView;
        private TextView table;
        private TextView neirong;
        private String mTable;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView13);
            table = itemView.findViewById(R.id.textView14);
            neirong = itemView.findViewById(R.id.textView16);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener!=null){
                        mOnItemClickListener.onItemClick(mTable,mPostion);
                    }
                }
            });
        }

        public void setData(ItemBean itemBean, int position) {
            this.mPostion = position;
            this.mTable = itemBean.getName();
            imageView.setImageResource(itemBean.getImageView());
            table.setText(itemBean.getName());
            neirong.setText(itemBean.getText());
        }
    }
}
