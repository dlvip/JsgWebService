package com.old.time.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TabEntity {

    public static TabEntity getInstance(Integer id, String name, String pic) {
        TabEntity tabEntity = new TabEntity();
        tabEntity.setId(id);
        tabEntity.setName(name);
        tabEntity.setPic(pic);

        return tabEntity;
    }


    @GeneratedValue
    @Id
    private Integer id;

    private String name;

    private String pic;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

}
