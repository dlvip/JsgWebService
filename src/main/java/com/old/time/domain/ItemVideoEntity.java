package com.old.time.domain;

import java.util.List;

public class ItemVideoEntity {
    public static ItemVideoEntity getInstance(String title, List<VideoEntity> videoEntities) {
        ItemVideoEntity itemVideoEntity = new ItemVideoEntity();
        itemVideoEntity.setTitle(title);
        itemVideoEntity.setVideoEntities(videoEntities);

        return itemVideoEntity;
    }

    private String title;
    private List<VideoEntity> videoEntities;

    public List<VideoEntity> getVideoEntities() {
        return videoEntities;
    }

    public String getTitle() {
        return title;
    }

    public void setVideoEntities(List<VideoEntity> videoEntities) {
        this.videoEntities = videoEntities;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
