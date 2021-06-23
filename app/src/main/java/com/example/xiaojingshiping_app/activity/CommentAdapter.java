package com.example.xiaojingshiping_app.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.xiaojingshiping_app.R;

import java.util.List;

public class CommentAdapter extends BaseAdapter {

    private Context context;
    private List<Comment> mData;

    public CommentAdapter(Context context,List<Comment> list){
        this.context = context;
        this.mData = list;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.pinglun_layout,null);
            holder.comment_name = convertView.findViewById(R.id.textView4);
            holder.comment_content = convertView.findViewById(R.id.textView8);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.comment_name.setText(mData.get(position).getName());
        holder.comment_content.setText(mData.get(position).getPinglun());

        return convertView;
    }
    public void addComment(Comment comment){
        mData.add(comment);
        notifyDataSetChanged();
    }

    public void addAll(List<Comment> data){
        mData.addAll(data);
    }

    public static class ViewHolder{
        TextView comment_name;
        TextView comment_content;
    }
}
