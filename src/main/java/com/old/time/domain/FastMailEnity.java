package com.old.time.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class FastMailEnity {

    public FastMailEnity(){

    }

    @Id
    @GeneratedValue
    private String id;

    /**
     * 官网
     */
    private String url;

    /**
     * 图标
     */
    private String icon;

    /**
     * 名称
     */
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "FastMailBean{" + "id='" + id + '\'' + ", url='" + url + '\'' + ", icon='" + icon + '\'' + ", name='" + name + '\'' + '}';
    }

}
