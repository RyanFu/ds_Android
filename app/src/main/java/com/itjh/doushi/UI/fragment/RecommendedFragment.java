package com.itjh.doushi.UI.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.itjh.doushi.Net.VideoService;
import com.itjh.doushi.R;
import com.itjh.doushi.UI.base.BaseFragment;
import com.itjh.doushi.UI.widget.videolist.calculator.DefaultSingleItemCalculatorCallback;
import com.itjh.doushi.UI.widget.videolist.calculator.ListItemsVisibilityCalculator;
import com.itjh.doushi.UI.widget.videolist.calculator.SingleListViewItemActiveCalculator;
import com.itjh.doushi.UI.widget.videolist.scroll.RecyclerViewItemPositionGetter;
import com.itjh.doushi.adapter.RecommendedAdapter;
import com.itjh.doushi.adapter.base.BaseAdapterHelper;
import com.itjh.doushi.adapter.base.BaseQuickAdapter;
import com.itjh.doushi.pojo.VideoListItem;
import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.orhanobut.logger.Logger;

import java.io.File;

import butterknife.Bind;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RecommendedFragment extends BaseFragment implements OnMoreListener, BaseQuickAdapter.OnItemClickListener<VideoListItem> {

    private String videoType = "0";

    @Bind(R.id.fragment_list_rv)
    SuperRecyclerView recyclerView;

    private int mScrollState;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        videoType = getArguments().getString("videoType");
    }

    private BaseQuickAdapter<VideoListItem, BaseAdapterHelper> mAdapter;

    @Override
    public int layoutID() {
        return R.layout.fragment;
    }


    @Override
    public void init(View view) {
        recyclerView.setOnMoreListener(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);


        Logger.e(videoType);
        retrofit.create(VideoService.class).listRepos("0", "10", videoType, "0").subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(videoListResponse -> Observable.from(videoListResponse.content)
                        .map(VideoListItem::new).toList())
                .subscribe(videoListResponse -> {
                    Logger.i(videoListResponse.toString());
                    mAdapter.appendToList(videoListResponse);
                }, throwable -> {
                    Logger.e(throwable.getMessage());
                });

        mAdapter = new RecommendedAdapter(getActivity(), R.layout.list_item);

        ListItemsVisibilityCalculator calculator = new SingleListViewItemActiveCalculator(new
                DefaultSingleItemCalculatorCallback(), mAdapter.getList());

        RecyclerViewItemPositionGetter mItemsPositionGetter = new RecyclerViewItemPositionGetter(gridLayoutManager, recyclerView.getRecyclerView());

        recyclerView.getRecyclerView().addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                mScrollState = newState;
                if (newState == RecyclerView.SCROLL_STATE_IDLE && !mAdapter.getList().isEmpty()) {
                    calculator.onScrollStateIdle(mItemsPositionGetter);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                calculator.onScrolled(mItemsPositionGetter, mScrollState);
            }
        });
        mAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {
        retrofit.create(VideoService.class).listRepos((mAdapter.getItem(mAdapter.getItemCount() - 1)).videoEntity.id, "20", videoType, "0").subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).flatMap(videoListResponse -> Observable.from(videoListResponse.content)
                .map(VideoListItem::new).toList())
                .subscribe(videoListResponse -> {
                    Logger.i(videoListResponse.toString());
                    mAdapter.appendToList(videoListResponse);
                }, throwable -> {
                    Logger.e(throwable.getMessage());
                });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(View view, int position, VideoListItem object) {
        if (object.getmState() == VideoListItem.STATE_ACTIVED)
            object.deactivate(view, position);
        else if (object.getmState() == VideoListItem.STATE_DEACTIVED) {
            object.setActive(view, position);
        }
    }

    File[] folders;
    long filesize = 0;

}