package com.itjh.doushi.pojo;

/**
 * User: Axl_Jacobs(Axl.Jacobs@gmail.com)
 * Date: 2016-02-29
 * Time: 17:28
 * FIXME
 */
public class VideoEntity {
    public String id;
    public String title;
    public String videoUrl;
    public String pic;
    public String type;
    public String pushTime;
    public String isCollectStatus;

    @Override
    public String toString() {
        return "VideoEntity{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", pic='" + pic + '\'' +
                ", type='" + type + '\'' +
                ", pushTime='" + pushTime + '\'' +
                ", isCollectStatus='" + isCollectStatus + '\'' +
                '}';
    }
}
