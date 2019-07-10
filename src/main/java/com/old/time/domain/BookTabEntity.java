package com.old.time.domain;

import java.util.ArrayList;
import java.util.List;

public class BookTabEntity {

    /**
     * 图书类型
     * 男频 0：玄幻、1：奇幻、2：仙侠、3：悬疑、4：军旅、5：历史、6：灵异
     * 女频 10：现代言情、11：古代言情、12：穿越架空、13：总裁豪门、14：青春校园
     * 文学 20：经典文学
     */
    public static BookTabEntity getInstance(int type) {
        BookTabEntity mBookTabEntity = new BookTabEntity();
        List<TabEntity> tabEntities = new ArrayList<>();
        switch (type) {
            case 0:
                mBookTabEntity.setTitle("男频");
                tabEntities.clear();
                tabEntities.add(TabEntity.getInstance(0, "玄幻", "https://www.xsjtxt.com/files/article/image/109/109642/109642s.jpg"));
                tabEntities.add(TabEntity.getInstance(1, "奇幻", "https://www.xsjtxt.com/files/article/image/111/111677/111677s.jpg"));
                tabEntities.add(TabEntity.getInstance(2, "仙侠", "https://www.xsjtxt.com/files/article/image/112/112935/112935s.jpg"));
                tabEntities.add(TabEntity.getInstance(3, "悬疑", "https://www.xsjtxt.com/files/article/image/92/92720/92720s.jpg"));
                tabEntities.add(TabEntity.getInstance(4, "军旅", "https://www.xsjtxt.com/files/article/image/65/65647/65647s.jpg"));
                tabEntities.add(TabEntity.getInstance(5, "历史", "https://www.xsjtxt.com/files/article/image/66/66049/66049s.jpg"));
                tabEntities.add(TabEntity.getInstance(6, "灵异", "https://www.xsjtxt.com/files/article/image/89/89197/89197s.jpg"));
                mBookTabEntity.setTabEntities(tabEntities);

                break;
            case 1:
                mBookTabEntity.setTitle("女频");
                tabEntities.clear();
                tabEntities.add(TabEntity.getInstance(10, "现代都市", "https://www.xsjtxt.com/files/article/image/112/112297/112297s.jpg"));
                tabEntities.add(TabEntity.getInstance(11, "古代言情", "https://www.xsjtxt.com/files/article/image/100/100212/100212s.jpg"));
                tabEntities.add(TabEntity.getInstance(12, "穿越架空", "https://www.xsjtxt.com/files/article/image/96/96925/96925s.jpg"));
                tabEntities.add(TabEntity.getInstance(13, "总裁豪门", "https://www.xsjtxt.com/files/article/image/99/99654/99654s.jpg"));
                tabEntities.add(TabEntity.getInstance(14, "青春校园", "https://www.xsjtxt.com/files/article/image/105/105727/105727s.jpg"));
                mBookTabEntity.setTabEntities(tabEntities);

                break;
            case 2:
                mBookTabEntity.setTitle("文学");
                tabEntities.clear();
                tabEntities.add(TabEntity.getInstance(20, "经典文学", "https://images.bookbao99.net/coverpic/596/596531.jpg"));

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


}
