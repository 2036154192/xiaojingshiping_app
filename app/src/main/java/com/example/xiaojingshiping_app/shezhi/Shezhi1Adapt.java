package com.example.xiaojingshiping_app.shezhi;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xiaojingshiping_app.R;
import com.example.xiaojingshiping_app.shouye.ItemBean;

import java.util.List;

public class Shezhi1Adapt extends RecyclerView.Adapter<Shezhi1Adapt.InnterHolder> {

    private List<ItemBean> mData;
    private OnItemClickListener mOnItemClickListTener;

    public Shezhi1Adapt(List<ItemBean> mData) {
        this.mData = mData;
    }

    @NonNull
    @Override
    public InnterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.shezhi1_layout,null);
        return new InnterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnterHolder holder, int position) {
        holder.setData(mData.get(position),position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListTener = listener;
    }
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public class InnterHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private int mPostin;

        public InnterHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView13);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListTener!=null){
                        mOnItemClickListTener.onItemClick(mPostin);
                    }
                }
            });
        }

        public void setData(ItemBean itemBean,int postion) {
            this.mPostin = postion;
            textView.setText(itemBean.getText());
        }
    }
}
