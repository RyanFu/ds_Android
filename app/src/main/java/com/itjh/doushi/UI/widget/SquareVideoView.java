package com.itjh.doushi.UI.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * User: Axl_Jacobs(Axl.Jacobs@gmail.com)
 * Date: 2016-03-08
 * Time: 17:54
 * FIXME
 */
public class SquareVideoView extends VideoView {

    public SquareVideoView(Context context) {
        super(context);
    }

    public SquareVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//
//        int width = getMeasuredWidth();
//        setMeasuredDimension(width, width);
//    }
}
