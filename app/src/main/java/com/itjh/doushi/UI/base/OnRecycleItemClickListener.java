package com.itjh.doushi.UI.base;

import android.view.View;

public interface OnRecycleItemClickListener<T> {
    void onItemClick(View view, int Position, T object);
}