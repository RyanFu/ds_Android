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
    public boolean isEmpty;

    public VideoEntity() {
    }

    public VideoEntity(String title,String pic, String pushTime, boolean isEmpty) {
        this.title = title;
        this.pic = pic;
        this.pushTime = pushTime;
        this.isEmpty = isEmpty;
    }

    @Override
    public String toString() {
        return "VideoEntity{" +
                "id='" + id + '\'' +
                ", pushTime='" + pushTime + '\'' +
                ", isEmpty=" + isEmpty +
                '}';
    }
}
