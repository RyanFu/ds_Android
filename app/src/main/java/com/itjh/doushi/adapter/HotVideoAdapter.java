package com.itjh.doushi.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.itjh.doushi.R;
import com.itjh.doushi.Utils.ScreenUtils;
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

        VideoEntity videoEntity1 = getItem(position);

        if (!videoEntity1.isEmpty) {
            holder.getTextView(R.id.tv_video_list_title).setText(videoEntity1.pushTime);
            SimpleDraweeView simpleDraweeView2 = (SimpleDraweeView) holder.getView(R.id.my_image_view);
            simpleDraweeView2.setAspectRatio(1f);
            simpleDraweeView2.setImageURI(Uri.parse(videoEntity1.pic));
        } else {
            holder.getTextView(R.id.tv_video_list_title).setText("");
            SimpleDraweeView simpleDraweeView2 = (SimpleDraweeView) holder.getView(R.id.my_image_view);
            simpleDraweeView2.setAspectRatio(1f);
            simpleDraweeView2.setImageURI(Uri.parse("res:///" + android.R.color.white));
        }

        if (position % 3 != 0 && getHeaderId(position - position % 3) != -1) {
            LinearLayout linearLayout = (LinearLayout) holder.getView(R.id.ll_list_bg);
            linearLayout.setPadding(0, ScreenUtils.dip2px(linearLayout.getContext(), 48), 0, 0);
        } else {
            LinearLayout linearLayout = (LinearLayout) holder.getView(R.id.ll_list_bg);
            linearLayout.setPadding(0, 0, 0, 0);
        }
    }

    @Override
    public long getHeaderId(int position) {
        Log.e("position", position + "");
        if (position > 0)
            return position % 3 == 0 && !TextUtils.equals(getItem(position).pushTime, getItem(position - 3).pushTime) ? Math.abs(getItem(position).pushTime.hashCode()) : -1;
        else return Math.abs(getItem(position).pushTime.hashCode());
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

        SimpleDraweeView my_image_view1;
        TextView tv_video_list_title1;

        public MyViewHolder(View itemView) {
            super(itemView);
            my_image_view1 = (SimpleDraweeView) itemView.findViewById(R.id.my_image_view1);
            tv_video_list_title1 = (TextView) itemView.findViewById(R.id.tv_video_list_title1);
        }
    }

    class HeadHolder extends RecyclerView.ViewHolder {

        public HeadHolder(View itemView) {
            super(itemView);
        }
    }
}
