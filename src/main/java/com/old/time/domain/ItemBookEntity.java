package com.old.time.domain;

import java.util.List;

public class ItemBookEntity {

    public static ItemBookEntity getInstance(String title, List<BookEntity> bookEntities) {
        ItemBookEntity itemBookEntity = new ItemBookEntity();
        itemBookEntity.setTitle(title);
        itemBookEntity.setBookEntities(bookEntities);

        return itemBookEntity;
    }

    private String title;
    private List<BookEntity> bookEntities;

    public List<BookEntity> getBookEntities() {
        return bookEntities;
    }

    public String getTitle() {
        return title;
    }

    public void setBookEntities(List<BookEntity> bookEntities) {
        this.bookEntities = bookEntities;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
