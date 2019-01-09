package com.example.jingh.myapplication.entiy;

import java.io.Serializable;

/**
 * @Description: 书籍来源
 * @author: jingh
 * @date 2019/1/9 17:16
 */
public class BookSource implements Serializable{
//    {
//        "_id": "56b1c6cf86d1628e1b1ad957",
//            "source": "zhuishuvip",
//            "name": "优质书源",
//            "link": "http://vip.zhuishushenqi.com/toc/56b1c6cf86d1628e1b1ad957",
//            "lastChapter": "第3448章　处理人皇子",
//            "isCharge": false,
//            "chaptersCount": 3448,
//            "updated": "2019-01-08T16:24:12.161Z",
//            "starting": true,
//            "host": "vip.zhuishushenqi.com"
//    },
    private String _id;
    private String source;
    private String name;
    private String link;
    private String lastChapter;
    private String isCharge;
    private String chaptersCount;
    private String starting;
    private String host;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLastChapter() {
        return lastChapter;
    }

    public void setLastChapter(String lastChapter) {
        this.lastChapter = lastChapter;
    }

    public String getIsCharge() {
        return isCharge;
    }

    public void setIsCharge(String isCharge) {
        this.isCharge = isCharge;
    }

    public String getChaptersCount() {
        return chaptersCount;
    }

    public void setChaptersCount(String chaptersCount) {
        this.chaptersCount = chaptersCount;
    }

    public String getStarting() {
        return starting;
    }

    public void setStarting(String starting) {
        this.starting = starting;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
