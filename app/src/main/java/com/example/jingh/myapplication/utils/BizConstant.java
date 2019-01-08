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

    //榜单url
    RANK_HOT_MAN("http://api.zhuishushenqi.com/ranking/564d820bc319238a644fb408","最热榜 男"),
    RANK_GOOD_MAN("http://api.zhuishushenqi.com/ranking/5a6844aafc84c2b8efaa6b6e","好评榜 男"),
    RANK_SEARCH_MAN("http://api.zhuishushenqi.com/ranking/5a6844f8fc84c2b8efaa8bc5","热搜榜 男"),
    RANK_POTENTIAL_MAN("http://api.zhuishushenqi.com/ranking/54d42e72d9de23382e6877fb","潜力榜 男"),
    RANK_END_MAN("http://api.zhuishushenqi.com/ranking/564eb878efe5b8e745508fde","完结榜 男"),
    RANK_ZH_MAN("http://api.zhuishushenqi.com/ranking/54d430962c12d3740e4a3ed2","纵横榜 男"),
    RANK_AND_READ_MAN("http://api.zhuishushenqi.com/ranking/54d4312d5f3c22ae137255a1","和阅读榜 男"),

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
