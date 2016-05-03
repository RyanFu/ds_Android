package com.itjh.doushi.UI.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.itjh.doushi.DouShiApplication;
import com.itjh.doushi.R;
import com.itjh.doushi.UI.DetailActivity;
import com.itjh.doushi.UI.base.BaseActivity;
import com.itjh.doushi.UI.base.BaseFragment;
import com.itjh.doushi.adapter.base.BaseAdapterHelper;
import com.itjh.doushi.adapter.base.BaseQuickAdapter;
import com.itjh.doushi.pojo.VideoEntity;
import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.orhanobut.logger.Logger;

import butterknife.Bind;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TabFragment extends BaseFragment implements OnMoreListener, BaseQuickAdapter.OnItemClickListener<VideoEntity> {

    private String videoType = "0";

    @Bind(R.id.fragment_list_rv)
    SuperRecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        videoType = getArguments().getString("videoType");
    }

    private BaseQuickAdapter mAdapter;

    @Override
    public int layoutID() {
        return R.layout.fragment;
    }


    @Override
    public void init(View view) {
        recyclerView.setOnMoreListener(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);


        Logger.e(videoType);
        DouShiApplication.getRestClient().getVideoService().listRepos("0", "10", videoType, "0").subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(videoListResponse -> {
            Logger.i(videoListResponse.toString());
            mAdapter.appendToList(videoListResponse.content);
        }, throwable -> {
            Logger.e(throwable.getMessage());
        });

        mAdapter = new BaseQuickAdapter<VideoEntity, BaseAdapterHelper>(getActivity(), R.layout.list_item) {
            @Override
            protected void convert(BaseAdapterHelper helper, VideoEntity item) {
                helper.getTextView(R.id.tv_video_list_title).setText(item.title);
                SimpleDraweeView simpleDraweeView = (SimpleDraweeView) helper.getView(R.id.my_image_view);
                simpleDraweeView.setAspectRatio(1f);
//                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) simpleDraweeView.getLayoutParams();
////                layoutParams.height = (gridLayoutManager.getWidth() - ScreenUtils.dip2px(getContext(), 16)) / 2;
//                layoutParams.height =  helper.getView(R.id.rl_list_bottom_bg).getWidth();
//                simpleDraweeView.setLayoutParams(layoutParams);
                simpleDraweeView.setImageURI(Uri.parse(item.pic));
            }
        };
        mAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {
        DouShiApplication.getRestClient().getVideoService().listRepos(((VideoEntity) mAdapter.getItem(mAdapter.getItemCount() - 1)).id, "20", videoType, "0").subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(videoListResponse -> {
            Logger.i(videoListResponse.toString());
            mAdapter.appendToList(videoListResponse.content);
        }, throwable -> {
            Logger.e(throwable.getMessage());
        });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(View view, int position, VideoEntity object) {
//        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
//                getActivity(), view, DetailActivity.EXTRA_IMAGE);
//        ActivityCompat.startActivity(getActivity(), new Intent(getContext(), DetailActivity.class),
//                options.toBundle());

        DetailActivity.navigate((BaseActivity) getActivity(), view.findViewById(R.id.my_image_view), object);
//        startActivity(new Intent(view.getContext(), TestActivity.class));
    }
}