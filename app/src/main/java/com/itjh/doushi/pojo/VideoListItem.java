package com.itjh.doushi.pojo;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.itjh.doushi.UI.widget.videolist.TextureVideoView;
import com.itjh.doushi.UI.widget.videolist.item.ListItem;

public class VideoListItem implements ListItem {

    public static final int STATE_IDLE = 0;
    public static final int STATE_ACTIVED = 1;
    public static final int STATE_DEACTIVED = 2;

    private int mState = STATE_IDLE;

    private String mCoverUrl;
    private String mVideoUrl;
    private String mVideoPath;
    public VideoEntity videoEntity;
    private SimpleDraweeView mVideoCover;
    private TextureVideoView mVideoView;
    private final Rect mCurrentViewRect = new Rect();


    public VideoListItem(VideoEntity videoEntity) {
        this.videoEntity = videoEntity;
        mCoverUrl = videoEntity.pic;//coverUrl;
        mVideoUrl = videoEntity.videoUrl;
    }

    public String getCoverUrl() {
        return mCoverUrl;
    }

    public void bindView(TextureVideoView videoView, SimpleDraweeView videoCover) {
        mVideoView = videoView;
        mVideoCover = videoCover;
    }

    public void setVideoPath(String videoPath) {
        mVideoPath = videoPath;
        if (videoPath != null) {
            mVideoView.setVideoPath(videoPath);
            if (mState == STATE_ACTIVED) {
                mVideoView.start();
            }
        }
    }

    public String getVideoUrl() {
        return mVideoUrl;
    }

    private boolean viewIsPartiallyHiddenBottom(int height) {
        return mCurrentViewRect.bottom > 0 && mCurrentViewRect.bottom < height;
    }

    private boolean viewIsPartiallyHiddenTop() {
        return mCurrentViewRect.top > 0;
    }

    @Override
    public int getVisibilityPercents(View currentView) {
        int percents = 100;

        currentView.getLocalVisibleRect(mCurrentViewRect);

        int height = currentView.getHeight();

        if (viewIsPartiallyHiddenTop()) {
            // view is partially hidden behind the top edge
            percents = (height - mCurrentViewRect.top) * 100 / height;
        } else if (viewIsPartiallyHiddenBottom(height)) {
            percents = mCurrentViewRect.bottom * 100 / height;
        }

        return percents;
    }

    public int getmState() {
        return mState;
    }

    @Override
    public void setActive(View currentView, int newActiveViewPosition) {
        Log.e("VideoListItem", "setActive " + newActiveViewPosition + " path " + mVideoPath);
        mState = STATE_ACTIVED;
        if (mVideoPath != null) {
            mVideoView.setVideoPath(mVideoPath);
            mVideoView.start();
        }
    }

    @Override
    public void deactivate(View currentView, int position) {
        Log.e("VideoListItem", "deactivate " + position);
        mState = STATE_DEACTIVED;
        mVideoView.stop();
        mVideoCover.setVisibility(View.VISIBLE);
        mVideoCover.setAlpha(1.f);
    }
}