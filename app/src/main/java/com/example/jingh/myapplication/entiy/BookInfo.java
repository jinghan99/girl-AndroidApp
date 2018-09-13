package com.example.jingh.myapplication.entiy;

import java.util.Date;

/**
 * @Package com.example.jingh.myapplication.entiy
 * @Description: TODO
 * @author: jingh
 * @date 2018/9/13 16:21
 */
public class BookInfo {

//    {"_id":"57ceb2069acafda7326052ae",
// "title":"最强反套路系统",
// "author":"太上布衣",
// "longIntro":"“年轻人，当年我开始装逼的时候，你们还只是一滴液体！”“仙子，随在下走一趟，保证带你装逼带你飞，带你一起嘿嘿嘿！”身披狂徒铠甲，肩扛日炎斗篷。左手无尽之刃，右手诛仙神剑。横扫修仙界无敌手，就问一声还有谁？装逼如风，常伴吾身！长路漫漫，装逼相伴！徐缺踏上了一条装逼之路，每天不是在装逼，就是在去装逼的路上！ ",
// "cover":"/agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F1188355%2F1188355_9989e2c949d147f0ac423da594a54b3f.jpg%2F",
// "majorCate":"仙侠","minorCate":"幻想修仙","creater":"vivo vivo Y23L",
// "majorCateV2":"仙侠","minorCateV2":"奇幻修真","hiddenPackage":[],"apptype":[0,1,4],
// "rating":{"count":23924,"score":8.989,"isEffect":true},
// "hasCopyright":true,"buytype":2,
// "sizetype":-1,
// "superscript":"","currency":0,
// "contentType":"txt",
// "_le":false,
// "allowMonthly":false,
// "allowVoucher":true,
// "allowBeanVoucher":false,
// "hasCp":true,"postCount":45722,
// "latelyFollower":89760,"followerCount":0,"wordCount":3739594,
// "serializeWordCount":2962,
// "retentionRatio":"57.83",
// "updated":"2018-09-12T16:01:19.388Z",
// "isSerial":true,
// "chaptersCount":1471,
// "lastChapter":"第1471章 你有这个资格吗",
// "gender":["male"],"tags":["丹药","系统流","爆笑","医生"],
// "advertRead":true,
// "cat":"幻想修仙",
// "donate":false,
// "copyright":"阅文集团正版授权",
// "_gg":false,
// "isForbidForFreeApp":true,
// "discount":null,
// "limit":false}


    private String _id;
    private String title;
    private String author;
    private String longIntro;
    private String cover;
    private String majorCate;
    private String lastChapter;
    private Date   updated;
    private String chaptersCount;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLongIntro() {
        return longIntro;
    }

    public void setLongIntro(String longIntro) {
        this.longIntro = longIntro;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getMajorCate() {
        return majorCate;
    }

    public void setMajorCate(String majorCate) {
        this.majorCate = majorCate;
    }

    public String getLastChapter() {
        return lastChapter;
    }

    public void setLastChapter(String lastChapter) {
        this.lastChapter = lastChapter;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getChaptersCount() {
        return chaptersCount;
    }

    public void setChaptersCount(String chaptersCount) {
        this.chaptersCount = chaptersCount;
    }

    @Override
    public String toString() {
        return "BookInfo{" +
                "_id='" + _id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", longIntro='" + longIntro + '\'' +
                ", cover='" + cover + '\'' +
                ", majorCate='" + majorCate + '\'' +
                ", lastChapter='" + lastChapter + '\'' +
                ", updated='" + updated + '\'' +
                ", chaptersCount='" + chaptersCount + '\'' +
                '}';
    }
}
