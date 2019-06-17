package com.old.time.domain;

import java.util.ArrayList;
import java.util.List;

public class BookTabEntity {

    /**
     * 图书类型
     * 男频 0：玄幻、1：奇幻、2：仙侠、3：悬疑、4：军旅、5：历史、6：灵异
     * 女频 10：现代言情、11：古代言情、12：穿越架空、13：总裁豪门、14：青春校园
     * 20：
     */
    public static BookTabEntity getInstance(int type) {
        BookTabEntity mBookTabEntity = new BookTabEntity();
        List<TabEntity> tabEntities = new ArrayList<>();
        switch (type) {
            case 0:
                mBookTabEntity.setTitle("男频");
                tabEntities.clear();
                tabEntities.add(TabEntity.getInstance("0", "玄幻", ""));
                tabEntities.add(TabEntity.getInstance("1", "奇幻", ""));
                tabEntities.add(TabEntity.getInstance("2", "仙侠", ""));
                tabEntities.add(TabEntity.getInstance("3", "悬疑", ""));
                tabEntities.add(TabEntity.getInstance("4", "军旅", ""));
                tabEntities.add(TabEntity.getInstance("5", "历史", ""));
                tabEntities.add(TabEntity.getInstance("6", "灵异", ""));
                mBookTabEntity.setTabEntities(tabEntities);

                break;
            case 1:
                mBookTabEntity.setTitle("女频");
                tabEntities.clear();
                tabEntities.add(TabEntity.getInstance("10", "现代言情", ""));
                tabEntities.add(TabEntity.getInstance("11", "古代言情", ""));
                tabEntities.add(TabEntity.getInstance("12", "穿越架空", ""));
                tabEntities.add(TabEntity.getInstance("13", "总裁豪门", ""));
                tabEntities.add(TabEntity.getInstance("14", "青春校园", ""));
                mBookTabEntity.setTabEntities(tabEntities);

                break;
        }
        return mBookTabEntity;
    }

    private String title;

    private List<TabEntity> tabEntities;

    public List<TabEntity> getTabEntities() {
        return tabEntities;
    }

    public void setTabEntities(List<TabEntity> tabEntities) {
        this.tabEntities = tabEntities;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static class TabEntity {

        public static TabEntity getInstance(String id, String name, String pic) {
            TabEntity tabEntity = new TabEntity();
            tabEntity.setId(id);
            tabEntity.setName(name);
            tabEntity.setPic(pic);
            return tabEntity;
        }


        private String id;

        private String name;

        private String pic;

        public String getId() {
            return id;
        }

        public void setId(String id) {
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

}
