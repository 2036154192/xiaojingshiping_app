package com.example.xiaojingshiping_app.sousuo;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xiaojingshiping_app.R;
import com.example.xiaojingshiping_app.activity.ShiPing;

import java.util.List;

public class SSAdapter extends RecyclerView.Adapter<SSAdapter.InnerHolder> {

    private List<ShiPing> mData;
    private String id;
    private OnItemClickListener mOnItemClickListener;
    private String name1;

    public void mData_create(){
        this.mData.clear();
        notifyDataSetChanged();
    }

    public void setmData  (List<ShiPing> data){
        this.mData = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(),R.layout.sousuo_list_view,null);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        holder.setData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        if (mData!=null){
            return mData.size();
        }
        return 0;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(String id,String name);
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private TextView name;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView21);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener!=null){
                        mOnItemClickListener.onItemClick(id,name1);
                    }
                }
            });
        }

        public void setData(ShiPing shiPing) {
            name.setText(shiPing.getSpname());
            id = shiPing.getSpid();
            name1 = shiPing.getSpname();
        }
    }
}
