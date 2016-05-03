package com.itjh.doushi.Net;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * User: Axl_Jacobs(Axl.Jacobs@gmail.com)
 * Date: 2016-05-03
 * Time: 14:18
 * FIXME
 */
public class RestClient {

    VideoService videoService;

    public RestClient() {

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.NET_SERVER_ADD)
                .build();
        videoService = retrofit.create(VideoService.class);
    }

    public VideoService getVideoService() {
        return videoService;
    }
}
