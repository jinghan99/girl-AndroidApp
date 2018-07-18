package com.example.jingh.myapplication.entiy;

/**
 * @ClassName UpdateBook
 * @Description
 * @Date 2018/7/18 16:19
 * @Author jinghan
 * @Version 1.0
 */
public class UpdateBook {
//    [
//  {
//    "_id": "53e56ee335f79bb626a496c9",
//    "author": "厌笔萧生",
//    "allowMonthly": false,
//    "referenceSource": "sogou",
//    "updated": "2018-07-18T02:30:07.259Z",
//    "chaptersCount": 3271,
//    "lastChapter": "第3288章 银甲战躯"
//  },
    private String _id;
    private String author;
    private String allowMonthly;
    private String referenceSource;
    private String updated;
    private String chaptersCount;
    private String lastChapter;


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAllowMonthly() {
        return allowMonthly;
    }

    public void setAllowMonthly(String allowMonthly) {
        this.allowMonthly = allowMonthly;
    }

    public String getReferenceSource() {
        return referenceSource;
    }

    public void setReferenceSource(String referenceSource) {
        this.referenceSource = referenceSource;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getChaptersCount() {
        return chaptersCount;
    }

    public void setChaptersCount(String chaptersCount) {
        this.chaptersCount = chaptersCount;
    }

    public String getLastChapter() {
        return lastChapter;
    }

    public void setLastChapter(String lastChapter) {
        this.lastChapter = lastChapter;
    }

    @Override
    public String toString() {
        return "UpdateBook{" +
                "_id='" + _id + '\'' +
                ", author='" + author + '\'' +
                ", allowMonthly='" + allowMonthly + '\'' +
                ", referenceSource='" + referenceSource + '\'' +
                ", updated='" + updated + '\'' +
                ", chaptersCount='" + chaptersCount + '\'' +
                ", lastChapter='" + lastChapter + '\'' +
                '}';
    }
}
