package com.old.time.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BookEntity {
    /**
     * "levelNum":"8.0",
     * "author":" 加西亚·马尔克斯",
     * "pubdate":"2012-9-1",
     * "binding":"精装",
     * "images_large":"http://open.6api.net/lpic/s11284102.jpg",
     * "publisher":"南海出版公司",
     * "isbn10":"7544258971",
     * "isbn13":"9787544258975",
     * "title":"霍乱时期的爱情",
     * "summary":"
     * 《霍乱时期的爱情》是加西亚•马尔克斯获得诺贝尔文学奖之后完成的第一部小说。讲述了一段跨越半个多世纪的爱情史诗，穷尽了所有爱情的可能性：忠贞的、隐秘的、粗暴的、羞怯的、柏拉图式的、放荡的、转瞬即逝的、生死相依的……再现了时光的无情流逝，被誉为“人类有史以来最伟大的爱情小说”，是20世纪最重要的经典文学巨著之一。",
     * "price":"39.50元"
     */

    public BookEntity() {

    }

    private BookEntity(String levelNum,
                       String author,
                       String pubdate,
                       String binding,
                       String images_large,
                       String publisher,
                       String isbn10,
                       String isbn13,
                       String title,
                       String summary,
                       String price) {
        this.levelNum = levelNum;
        this.author = author;
        this.pubdate = pubdate;
        this.binding = binding;
        this.images_large = images_large;
        this.publisher = publisher;
        this.isbn10 = isbn10;
        this.isbn13 = isbn13;
        this.title = title;
        this.summary = summary;
        this.price = price;


    }

    /**
     * 书本信息
     *
     * @param levelNum
     * @param author
     * @param pubdate
     * @param binding
     * @param images_large
     * @param publisher
     * @param isbn10
     * @param isbn13
     * @param title
     * @param summary
     * @param price
     * @return
     */
    public static BookEntity getInstance(String levelNum,
                                         String author,
                                         String pubdate,
                                         String binding,
                                         String images_large,
                                         String publisher,
                                         String isbn10,
                                         String isbn13,
                                         String title,
                                         String summary,
                                         String price) {

        return new BookEntity(levelNum,
                author,
                pubdate,
                binding,
                images_large,
                publisher,
                isbn10,
                isbn13,
                title,
                summary,
                price);
    }

    @Id
    @GeneratedValue
    private Integer id;

    private String levelNum;

    private String author;

    private String pubdate;

    private String binding;

    private String images_large;

    private String publisher;

    private String isbn10;

    private String isbn13;

    private String title;

    private String summary;

    private String price;

    private String filePath;

    /**
     * 图书类型
     * 男频 0：玄幻、1：奇幻、2：仙侠、3：悬疑、4：军旅、5：历史、6：灵异
     * 女频 10：现代言情、11：古代言情、12：穿越架空、13：总裁豪门、14：青春校园
     * 文学 20：经典文学
     */
    private String tabId;

    /**
     * 精选：00：重磅推荐、01：热门精选、02：都市·猎艳、03：暧昧·异能、04：小编·推荐
     * 男频：10：重磅推荐、11：热门精选、12：玄幻·奇幻、13：历史·军旅、14：游戏·竞技
     * 女频：20：重磅推荐、21：热门精选、22：现代·都市、23：灵异·穿越、24：美文·同人
     */
    private String weight;

    public String getTabId() {
        return tabId;
    }

    public void setTabId(String tabId) {
        this.tabId = tabId;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLevelNum() {
        return levelNum;
    }

    public void setLevelNum(String levelNum) {
        this.levelNum = levelNum;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public String getImages_large() {
        return images_large;
    }

    public void setImages_large(String images_large) {
        this.images_large = images_large;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
