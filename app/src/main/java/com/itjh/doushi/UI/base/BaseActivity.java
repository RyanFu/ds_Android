package com.itjh.doushi.UI.base;

import android.os.Bundle;

import com.itjh.doushi.Net.Constant;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import io.vov.vitamio.Vitamio;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * User: Axl_Jacobs(Axl.Jacobs@gmail.com)
 * Date: 2016-02-29
 * Time: 15:47
 * FIXME
 */
public abstract class BaseActivity extends RxAppCompatActivity {

  protected   Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        before();
        if (layoutID() != 0) {
            setContentView(layoutID());
            ButterKnife.bind(this);
        }
        after();
        init();
        data();
    }

    public void before() {
        Vitamio.isInitialized(getApplicationContext());
    }

    public void after() {
        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.NET_SERVER_ADD)
                .build();
    }

    public abstract int layoutID();

    public abstract void init();

    public void data() {

    }
}
