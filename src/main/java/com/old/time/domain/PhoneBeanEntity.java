package com.old.time.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PhoneBeanEntity {

    private String userId;

    private String photo;
    private String name;
    private String number;
    private String sortKey;

    @Id
    @GeneratedValue
    private String id;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSortKey() {
        return sortKey;
    }

    public void setSortKey(String sortKey) {
        this.sortKey = sortKey;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PhoneBeanEntity{" +
                "userId='" + userId + '\'' +
                ", photo='" + photo + '\'' +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", sortKey='" + sortKey + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
