package com.itjh.doushi.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.itjh.doushi.R;
import com.itjh.doushi.adapter.base.BaseAdapter;
import com.itjh.doushi.adapter.base.BaseAdapterHelper;
import com.itjh.doushi.pojo.VideoEntity;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

/**
 * User: Axl.Jacobs(axl.jacobs@gmail.com)
 * Date: 2016-03-22
 * Time: 00:18
 * FIXME
 */
public class HotVideoAdapter extends BaseAdapter<VideoEntity, HotVideoAdapter.MyViewHolder> implements StickyRecyclerHeadersAdapter<HotVideoAdapter.HeadHolder> {


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(View.inflate(parent.getContext(), R.layout.list_item, null));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.getTextView(R.id.tv_video_list_title).setText(getItem(position).title);
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) holder.getView(R.id.my_image_view);
        simpleDraweeView.setAspectRatio(1f);
        simpleDraweeView.setImageURI(Uri.parse(getItem(position).pic));
    }

    @Override
    public long getHeaderId(int position) {
            return getItem(position).pushTime.startsWith("2016")?Math.abs(getItem(position).pushTime.hashCode()):Math.abs(getItem(position).pushTime.hashCode());
    }

    @Override
    public HeadHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_header, parent, false);
        return new HeadHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(HeadHolder holder, int position) {
        TextView textView = (TextView) holder.itemView;
        textView.setText(String.valueOf(getItem(position).pushTime));
    }

    class MyViewHolder extends BaseAdapterHelper {

        SimpleDraweeView my_image_view;
        TextView tv_video_list_title;

        public MyViewHolder(View itemView) {
            super(itemView);
            my_image_view = (SimpleDraweeView) itemView.findViewById(R.id.my_image_view);
            tv_video_list_title = (TextView) itemView.findViewById(R.id.tv_video_list_title);
        }
    }

    class HeadHolder extends RecyclerView.ViewHolder {

        public HeadHolder(View itemView) {
            super(itemView);
        }
    }


}
