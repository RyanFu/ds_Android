package com.itjh.doushi.UI.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.TextureView;
import android.view.View;
import android.widget.RelativeLayout;

import com.itjh.doushi.R;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;


/**
 * User: Axl.Jacobs(axl.jacobs@gmail.com)
 * Date: 2016-03-22
 * Time: 22:29
 * FIXME
 */
public class VideoView extends RelativeLayout {

    private TextureView mTextureView;
    private IjkMediaPlayer ijkMediaPlayer;

    public VideoView(Context context) {
        super(context);
        init();
    }


    public VideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View view = inflate(getContext(), R.layout.view_progress_fab, this);
    }

}
