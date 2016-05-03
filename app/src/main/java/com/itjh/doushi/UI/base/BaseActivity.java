package com.itjh.doushi.UI.base;

import android.os.Bundle;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;

/**
 * User: Axl_Jacobs(Axl.Jacobs@gmail.com)
 * Date: 2016-02-29
 * Time: 15:47
 * FIXME
 */
public abstract class BaseActivity extends RxAppCompatActivity {


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

    }

    public void after() {
    }

    public abstract int layoutID();

    public abstract void init();

    public void data() {

    }
}
