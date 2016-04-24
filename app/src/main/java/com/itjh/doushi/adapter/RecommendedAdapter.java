package com.itjh.doushi.adapter;


import android.content.Context;
import android.net.Uri;
import android.widget.FrameLayout;

import com.facebook.drawee.view.SimpleDraweeView;
import com.itjh.doushi.R;
import com.itjh.doushi.UI.widget.videolist.TextureVideoView;
import com.itjh.doushi.Utils.ScreenUtils;
import com.itjh.doushi.adapter.base.BaseAdapterHelper;
import com.itjh.doushi.adapter.base.BaseQuickAdapter;
import com.itjh.doushi.pojo.VideoListItem;

/**
 * User: Axl.Jacobs(axl.jacobs@gmail.com)
 * Date: 2016-04-24
 * Time: 12:44
 * FIXME
 */
public class RecommendedAdapter extends BaseQuickAdapter<VideoListItem, BaseAdapterHelper> {

    public RecommendedAdapter(Context context, int layoutResId) {
        super(context, layoutResId);
    }

    @Override
    protected void convert(BaseAdapterHelper helper, VideoListItem item) {

        helper.getTextView(R.id.tv_video_list_title).setText(item.videoEntity.title);
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) helper.getView(R.id.my_image_view);
        simpleDraweeView.setAspectRatio(1f);
        TextureVideoView textureVideoView = (TextureVideoView) helper.getView(R.id.tvv_list_video);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) textureVideoView.getLayoutParams();
        layoutParams.height = ScreenUtils.getScreenWidth(textureVideoView.getContext());
        textureVideoView.setLayoutParams(layoutParams);
        simpleDraweeView.setImageURI(Uri.parse(item.videoEntity.pic));
        item.bindView(textureVideoView, simpleDraweeView);
        item.setVideoPath(item.getVideoUrl());
    }

}
