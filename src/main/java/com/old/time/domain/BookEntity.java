package com.old.time.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BookEntity {
    /**
     * "levelNum":"8.0",
     * "subtitle":"",
     * "author":" 加西亚·马尔克斯",
     * "pubdate":"2012-9-1",
     * "origin_title":El amor en los tiempos del cólera",
     * "binding":"精装",
     * "pages":"401",
     * "images_medium":"http://open.6api.net/mpic/s11284102.jpg",
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
                       String subtitle,
                       String author,
                       String pubdate,
                       String origin_title,
                       String binding,
                       String pages,
                       String images_medium,
                       String images_large,
                       String publisher,
                       String isbn10,
                       String isbn13,
                       String title,
                       String summary,
                       String price,
                       String url) {
        this.levelNum = levelNum;
        this.subtitle = subtitle;
        this.author = author;
        this.pubdate = pubdate;
        this.origin_title = origin_title;
        this.binding = binding;
        this.pages = pages;
        this.images_medium = images_medium;
        this.images_large = images_large;
        this.publisher = publisher;
        this.isbn10 = isbn10;
        this.isbn13 = isbn13;
        this.title = title;
        this.summary = summary;
        this.price = price;
        this.url = url;


    }

    /**
     * 书本信息
     *
     * @param levelNum
     * @param subtitle
     * @param author
     * @param pubdate
     * @param origin_title
     * @param binding
     * @param pages
     * @param images_medium
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
                                         String subtitle,
                                         String author,
                                         String pubdate,
                                         String origin_title,
                                         String binding,
                                         String pages,
                                         String images_medium,
                                         String images_large,
                                         String publisher,
                                         String isbn10,
                                         String isbn13,
                                         String title,
                                         String summary,
                                         String price,
                                         String url) {

        return new BookEntity(levelNum,
                subtitle,
                author,
                pubdate,
                origin_title,
                binding,
                pages,
                images_medium,
                images_large,
                publisher,
                isbn10,
                isbn13,
                title,
                summary,
                price,
                url);
    }

    @Id
    @GeneratedValue
    private Integer id;

    private String levelNum;

    private String subtitle;

    private String author;

    private String pubdate;

    private String origin_title;

    private String binding;

    private String pages;

    private String images_medium;

    private String images_large;

    private String publisher;

    private String isbn10;

    private String isbn13;

    private String title;

    private String summary;

    private String price;

    private String url;

    private String filePath;

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

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
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

    public String getOrigin_title() {
        return origin_title;
    }

    public void setOrigin_title(String origin_title) {
        this.origin_title = origin_title;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getImages_medium() {
        return images_medium;
    }

    public void setImages_medium(String images_medium) {
        this.images_medium = images_medium;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
