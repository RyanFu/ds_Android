package com.itjh.doushi.UI.widget.videolist.calculator;

import com.itjh.doushi.UI.widget.videolist.scroll.ItemsPositionGetter;

public interface ListItemsVisibilityCalculator {
    void onScrolled(ItemsPositionGetter itemsPositionGetter, int scrollState);
    void onScrollStateIdle(ItemsPositionGetter itemsPositionGetter);
}