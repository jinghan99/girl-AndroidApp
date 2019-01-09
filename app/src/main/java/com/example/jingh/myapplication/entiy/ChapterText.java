package com.example.jingh.myapplication.entiy;

import java.io.Serializable;

/**
 * @Package com.example.jingh.myapplication.entiy
 * @Description: 章节 内容 文字
 * @author: jingh
 * @date 2019/1/9 16:38
 */
public class ChapterText implements Serializable {
//    {
//        "ok": true,
//            "chapter": {
//        "title": ".",
//                "body": "篇\n技能：龙腾九变（进度0%，已掌握第一变）\n职业：无\n地位：东荒大陆火元国驸马\n……”\n徐缺心满意足的点点头，摸了摸下巴，这二十点装逼值花得一点都不心疼。\n至于剩下的五点装逼值，他决定暂时不动，万一哪天又受伤了，还得靠它来开启修复功能疗伤呢！\n搞定了法诀，徐缺又打开了系统包裹，心中再次充满期待。\n那个新奖励的成长大礼包，会赠送什么呢？"
//    }
//    }
    private String title;
    private String body;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
