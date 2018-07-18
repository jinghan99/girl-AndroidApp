package com.example.jingh.myapplication.entiy;

/**
 * @ClassName 书籍实体
 * @Description
 * @Date 2018/7/18 14:36
 * @Author jinghan
 * @Version 1.0
 */
public class BookEntiy {

    private String bookImage;

    private String bookName;
    private String bookType;
    private String updateTime;

    private String bookUpdateChapter;

    public BookEntiy(String bookImage, String bookName, String bookType, String updateTime, String bookUpdateChapter) {
        this.bookImage = bookImage;
        this.bookName = bookName;
        this.bookType = bookType;
        this.updateTime = updateTime;
        this.bookUpdateChapter = bookUpdateChapter;
    }

    public BookEntiy() {
    }

    public String getBookImage() {
        return bookImage;
    }

    public void setBookImage(String bookImage) {
        this.bookImage = bookImage;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getBookUpdateChapter() {
        return bookUpdateChapter;
    }

    public void setBookUpdateChapter(String bookUpdateChapter) {
        this.bookUpdateChapter = bookUpdateChapter;
    }
}
