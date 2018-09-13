package com.example.jingh.myapplication.utils;

/**
 * @ClassName 常量值
 * @Description
 * @Date 2018/8/8 14:43
 * @Author jinghan
 * @Version 1.0
 */
public enum BizConstant {

    //字典
    imgUrl("http://statics.zhuishushenqi.com/","小说图片前缀"),
    bookInfo("http://api.zhuishushenqi.com/book/","小说信息"),//后加小说id ： http://api.zhuishushenqi.com/book/57ceb2069acafda7326052ae

    bookTitle("bookTitle","小说书名前缀缓存"),
    bookImg("bookImg","小说图片前缀缓存"),






    ;

    private String value; //value
    private String name;  //内容

    BizConstant(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }


    public String getName() {
        return name;
    }

}
