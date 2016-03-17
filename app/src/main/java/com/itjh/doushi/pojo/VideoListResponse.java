package com.itjh.doushi.pojo;

import java.util.List;

/**
 * User: Axl_Jacobs(Axl.Jacobs@gmail.com)
 * Date: 2016-02-29
 * Time: 17:27
 * FIXME
 */
public class VideoListResponse {

    /**
     * message : string
     * content : {}
     * request : string
     * statusCode : 0
     */

    public String message;
    public List<VideoEntity> content;
    public String request;
    public String statusCode;

    @Override
    public String toString() {
        return "VideoListResponse{" +
                "message='" + message + '\'' +
                ", content=" + content +
                ", request='" + request + '\'' +
                ", statusCode='" + statusCode + '\'' +
                '}';
    }
}
