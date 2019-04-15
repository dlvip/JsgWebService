package com.old.time.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TopicVideoBookEntry {

    public TopicVideoBookEntry(){

    }

    /**
     *
     * @param videoId
     * @param topicId
     * @param bookId
     * @return
     */
    public static TopicVideoBookEntry instance(Integer videoId, Integer topicId, Integer bookId) {

        return new TopicVideoBookEntry(videoId, topicId, bookId);
    }

    private TopicVideoBookEntry(Integer videoId, Integer topicId, Integer bookId) {
        this.videoId = videoId;
        this.topicId = topicId;
        this.bookId = bookId;

    }

    @Id
    @GeneratedValue
    private Integer id;

    private Integer videoId;

    private Integer topicId;

    private Integer bookId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
}
