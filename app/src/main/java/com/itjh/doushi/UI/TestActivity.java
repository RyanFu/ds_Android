package com.itjh.doushi.UI;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.itjh.doushi.DouShiApplication;
import com.itjh.doushi.R;
import com.itjh.doushi.UI.base.BaseActivity;
import com.itjh.doushi.adapter.base.BaseAdapterHelper;
import com.itjh.doushi.adapter.base.BaseQuickAdapter;
import com.itjh.doushi.pojo.VideoEntity;
import com.orhanobut.logger.Logger;

import butterknife.Bind;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * User: Axl_Jacobs(Axl.Jacobs@gmail.com)
 * Date: 2016-03-15
 * Time: 14:36
 * FIXME
 */
public class TestActivity extends BaseActivity {

    @Bind(R.id.rv_test)
    RecyclerView recyclerView;


    @Bind(R.id.iv_test)
    ImageView iv_test;

    private BaseQuickAdapter mAdapter;

    @Override
    public int layoutID() {
        return R.layout.activity_test;
    }

    @Override
    public void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new BaseQuickAdapter<VideoEntity, BaseAdapterHelper>(this, R.layout.list_item) {
            @Override
            protected void convert(BaseAdapterHelper helper, VideoEntity item) {
                helper.getTextView(R.id.tv_video_list_title).setText(item.title);
            }
        };
        recyclerView.setAdapter(mAdapter);

        DouShiApplication.getRestClient().getVideoService().listRepos("0", "40", "0", "0").subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(videoListResponse -> {
            Logger.i(videoListResponse.toString());
            mAdapter.appendToList(videoListResponse.content);
        }, throwable -> {
            Logger.e(throwable.getMessage());
        });

        iv_test.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                Logger.d("left:" + left + " top:" + top + " right:" + right + " bottom:" + bottom);
            }
        });

    }
}
