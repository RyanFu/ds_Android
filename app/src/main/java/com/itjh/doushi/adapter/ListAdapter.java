package com.itjh.doushi.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.itjh.doushi.R;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    List<String> mListData;

    public ListAdapter(List<String> mListData) {
        this.mListData = mListData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item,
                viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
//        myViewHolder.title.setText(mListData.get(i));
    }

    @Override
    public int getItemCount() {
        return mListData == null ? 0 : mListData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView my_image_view;
        TextView tv_video_list_title;

        public MyViewHolder(View itemView) {
            super(itemView);
            my_image_view = (SimpleDraweeView) itemView.findViewById(R.id.my_image_view);
            tv_video_list_title = (TextView) itemView.findViewById(R.id.tv_video_list_title);
        }
    }

}

