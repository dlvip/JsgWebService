package com.old.time.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TabEntity {

    public TabEntity() {

    }

    public static TabEntity getInstance(Integer tabId, String name, String pic) {
        TabEntity tabEntity = new TabEntity();
        tabEntity.setTabId(tabId);
        tabEntity.setName(name);
        tabEntity.setPic(pic);

        return tabEntity;
    }


    @GeneratedValue
    @Id
    private Integer id;


    /**
     * 图书类型
     * 男频 0：玄幻、1：奇幻、2：仙侠、3：悬疑、4：军旅、5：历史、6：灵异
     * 女频 10：现代言情、11：古代言情、12：穿越架空、13：总裁豪门、14：青春校园
     * 文学 20：文学经典
     */
    private Integer tabId;

    private String name;

    private String pic;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTabId() {
        return tabId;
    }

    public void setTabId(Integer tabId) {
        this.tabId = tabId;
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
