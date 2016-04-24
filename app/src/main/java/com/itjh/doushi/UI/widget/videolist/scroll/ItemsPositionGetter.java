package com.itjh.doushi.UI.widget.videolist.scroll;

import android.view.View;

public interface ItemsPositionGetter {
    View getChildAt(int position);

    int indexOfChild(View view);

    int getChildCount();

    int getLastVisiblePosition();

    int getFirstVisiblePosition();
}