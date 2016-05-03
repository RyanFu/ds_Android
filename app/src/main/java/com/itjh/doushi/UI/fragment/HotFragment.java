package com.itjh.doushi.UI.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.itjh.doushi.DouShiApplication;
import com.itjh.doushi.R;
import com.itjh.doushi.UI.DetailActivity;
import com.itjh.doushi.UI.base.BaseActivity;
import com.itjh.doushi.UI.base.BaseFragment;
import com.itjh.doushi.adapter.HotVideoAdapter;
import com.itjh.doushi.adapter.base.BaseQuickAdapter;
import com.itjh.doushi.pojo.VideoEntity;
import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.orhanobut.logger.Logger;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

import java.util.List;

import butterknife.Bind;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HotFragment extends BaseFragment implements OnMoreListener, BaseQuickAdapter.OnItemClickListener<VideoEntity> {

    private String videoType = "0";

    @Bind(R.id.fragment_list_rv)
    SuperRecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        videoType = getArguments().getString("videoType");
    }

    private HotVideoAdapter mAdapter;
//    private BaseQuickAdapter mAdapter;

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
        DouShiApplication.getRestClient().getVideoService().listRepos("0", "20", videoType, "0").subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(videoListResponse -> {
            Logger.i(videoListResponse.toString());
            mAdapter.appendToList(arrangeData(videoListResponse.content));
        }, throwable -> {
            Logger.e(throwable.getMessage());
        });

        mAdapter = new HotVideoAdapter();

//                new BaseQuickAdapter<VideoEntity, BaseAdapterHelper>(getActivity(), R.layout.list_item) {
//            @Override
//            protected void convert(BaseAdapterHelper helper, VideoEntity item) {
//                helper.getTextView(R.id.tv_video_list_title).setText(item.title);
//                SimpleDraweeView simpleDraweeView = (SimpleDraweeView) helper.getView(R.id.my_image_view);
//                simpleDraweeView.setAspectRatio(1f);
////                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) simpleDraweeView.getLayoutParams();
//////                layoutParams.height = (gridLayoutManager.getWidth() - ScreenUtils.dip2px(getContext(), 16)) / 2;
////                layoutParams.height =  helper.getView(R.id.rl_list_bottom_bg).getWidth();
////                simpleDraweeView.setLayoutParams(layoutParams);
//                simpleDraweeView.setImageURI(Uri.parse(item.pic));
//            }
//        };
//        mAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(mAdapter);

        recyclerView.addItemDecoration(new StickyRecyclerHeadersDecoration(mAdapter));
    }

    @Override
    public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {
        DouShiApplication.getRestClient().getVideoService().listRepos(((VideoEntity) mAdapter.getItem(mAdapter.getItemCount() - 1)).id, "20", videoType, "0").subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(videoListResponse -> {
            Logger.i(videoListResponse.toString());
            mAdapter.appendToList(arrangeData(videoListResponse.content));
        }, throwable -> {
            Logger.e(throwable.getMessage());
        });
    }

    @Override
    public void onClick(View v) {

    }

    String timep;

    private List<VideoEntity> arrangeData(List<VideoEntity> entities) {
        for (int i = 0; i < entities.size(); i++) {
            VideoEntity now = entities.get(i);
            VideoEntity next = null;
            if (i + 1 < entities.size())
                next = entities.get(i + 1);

            if (now != null && next != null && now.pushTime.startsWith("2016") && next.pushTime.startsWith("2016") && !TextUtils.equals(now.pushTime, next.pushTime) && i % 3 != 2) {
                if (i % 3 == 0) {
                    entities.add(i + 1, new VideoEntity(now.title, now.pic, now.pushTime, true));
                    entities.add(i + 2, new VideoEntity(now.title, now.pic, now.pushTime, true));
                } else if (i % 3 == 1) {
                    entities.add(i + 1, new VideoEntity(now.title, now.pic, now.pushTime, true));
                }
            }

        }
        return entities;

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