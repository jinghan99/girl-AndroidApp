package com.example.jingh.myapplication.entiy;

import java.io.Serializable;

/**
 * @Package
 * @Description: 小说章节信息
 * @author: jingh
 * @date 2019/1/9 16:22
 */
public class BookChapter implements Serializable {

//    {
//        "title": "第2章 装完逼就跑真他妈刺激",
//            "link": "http://book.my716.com/getBooks.aspx?method=content&bookId=1188355&chapterFile=U_1273715_201610131701286563_9415_2.txt",
//            "time": 0,
//            "chapterCover": "",
//            "totalpage": 0,
//            "partsize": 0,
//            "order": 0,
//            "currency": 0,
//            "unreadble": false,
//            "isVip": false
//    },
    private String title;
    private String link;
    private String chapterCover;
    private String totalpage;
    private String partsize;
    private String order;
    private String currency;
    private String unreadble;
    private String isVip;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getChapterCover() {
        return chapterCover;
    }

    public void setChapterCover(String chapterCover) {
        this.chapterCover = chapterCover;
    }

    public String getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(String totalpage) {
        this.totalpage = totalpage;
    }

    public String getPartsize() {
        return partsize;
    }

    public void setPartsize(String partsize) {
        this.partsize = partsize;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getUnreadble() {
        return unreadble;
    }

    public void setUnreadble(String unreadble) {
        this.unreadble = unreadble;
    }

    public String getIsVip() {
        return isVip;
    }

    public void setIsVip(String isVip) {
        this.isVip = isVip;
    }

    @Override
    public String toString() {
        return "BookChapter{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", chapterCover='" + chapterCover + '\'' +
                ", totalpage='" + totalpage + '\'' +
                ", partsize='" + partsize + '\'' +
                ", order='" + order + '\'' +
                ", currency='" + currency + '\'' +
                ", unreadble='" + unreadble + '\'' +
                ", isVip='" + isVip + '\'' +
                '}';
    }
}
