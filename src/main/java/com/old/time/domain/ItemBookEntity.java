package com.old.time.domain;

import java.util.List;

public class ItemBookEntity {

    public static ItemBookEntity getInstance(String title, List<RBookEntity> bookEntities) {
        ItemBookEntity itemBookEntity = new ItemBookEntity();
        itemBookEntity.setTitle(title);
        itemBookEntity.setBookEntities(bookEntities);

        return itemBookEntity;
    }

    private String title;
    private List<RBookEntity> bookEntities;

    public List<RBookEntity> getBookEntities() {
        return bookEntities;
    }

    public String getTitle() {
        return title;
    }

    public void setBookEntities(List<RBookEntity> bookEntities) {
        this.bookEntities = bookEntities;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
