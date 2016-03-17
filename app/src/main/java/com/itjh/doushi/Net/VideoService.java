package com.itjh.doushi.Net;

import com.itjh.doushi.pojo.VideoListResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * User: Axl_Jacobs(Axl.Jacobs@gmail.com)
 * Date: 2016-02-29
 * Time: 17:34
 * FIXME
 */
public interface VideoService {

    @GET("video/getVideosByType/{videoId}/{count}/{type}/{userId}")
    Observable<VideoListResponse> listRepos(@Path("videoId") String videoId,
                                            @Path("count") String count,
                                            @Path("type") String type,
                                            @Path("userId") String userId);

}
