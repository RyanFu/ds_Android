package com.itjh.doushi;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.itjh.doushi.Net.RestClient;
import com.itjh.doushi.Utils.ApplicationConstant;
import com.orhanobut.logger.Logger;

import net.youmi.android.AdManager;
import net.youmi.android.spot.SpotManager;


/**
 * User: Axl_Jacobs(Axl.Jacobs@gmail.com)
 * Date: 2016-02-29
 * Time: 17:57
 * FIXME
 */
public class DouShiApplication extends Application implements ApplicationConstant {
    private static RestClient restClient;

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init("zzy");
        Fresco.initialize(this);
        AdManager.getInstance(this).init(AD_APP_ID, AD_APP_SECRET, APP_DEBUG);
        SpotManager.getInstance(this).loadSpotAds();
        SpotManager.getInstance(this).setSpotOrientation(SpotManager.ORIENTATION_PORTRAIT);
        restClient = new RestClient();
    }

    public static RestClient getRestClient() {
        return restClient;
    }
}
