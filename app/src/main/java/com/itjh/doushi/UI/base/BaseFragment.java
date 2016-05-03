package com.itjh.doushi.UI.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * Created by win8 -1 on 2015/8/5.
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    public Activity mActivity;
    public View fragmentView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        before();
        if (fragmentView == null && layoutID() > 0) {
            fragmentView = inflater.inflate(layoutID(), container, false);
            ButterKnife.bind(this,fragmentView);
        }
        after(fragmentView);
        init(fragmentView);
        data();
        return fragmentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    protected void before() {
    }

    protected void after(View view) {
        mActivity = getActivity();
    }

    public abstract int layoutID();

    public abstract void init(View view);

    public void data() {

    }


    public void openActivity(Class<?> pClass) {
        openActivity(pClass, null);
    }

    public void openActivity(Class<?> pClass, Bundle pBundle) {
        Intent intent = new Intent(getActivity(), pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
//        overridePendingTransition(R.anim.slide_in_right,
//                R.anim.activity_close_enter);
    }


    /**
     * 启动Activity
     */
    protected void openActivity(String pAction) {
        openActivity(pAction, null);
    }

    /**
     * 启动Activity
     */
    protected void openActivity(String pAction, Bundle pBundle) {
        Intent intent = new Intent(pAction);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
//        overridePendingTransition(R.anim.slide_in_right,
//                R.anim.activity_close_enter);
    }

    /**
     * 短时间显示Toast
     *
     * @param info 显示的内容
     */
    public void showToast(String info) {
        Toast.makeText(getContext(), info, Toast.LENGTH_SHORT).show();
    }

    /**
     * 短时间显示Toast
     *
     * @param resId 显示的内容
     */
    public void showToast(int resId) {
        showToast(getString(resId));
    }
}
