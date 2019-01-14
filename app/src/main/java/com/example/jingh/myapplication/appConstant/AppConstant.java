package com.example.jingh.myapplication.appConstant;

/**
 * @ClassName app常用
 * @Description
 * @Date 2018/7/18 16:04
 * @Author jinghan
 * @Version 1.0
 */
public class AppConstant {

    public static final String CACHE_BOOK_ID="cache_Book_Id";

    public static final String CACHE_BOOK_SOURCE_ID="CACHE_BOOK_SOURCE_ID";


    public static final String imgUrlFirst = "http://statics.zhuishushenqi.com";

    //小说连载 更新 信息
     public static final String bookUpdateUrl = "http://api.zhuishushenqi.com/book?view=updated&id=53e56ee335f79bb626a496c9,5b0d28378659ea1aab8ca218,59e2c2b08bde16e66f9e3b85,5816b415b06d1d32157790b1";

    public static final String[] MAN_TITLES = {"最热榜", "好评榜", "热搜榜", "潜力榜", "完结榜", "纵横榜", "和阅读榜"};


    /**
     * 书籍来源 bookId 替换为 bookId
     */
    public static final String BOOK_SOURCE ="http://api.zhuishushenqi.com/toc?view=summary&book=bookId";

    /**
     * bookId = 替换为 书籍来源id
     */
    public static final String BOOK_CHAPTER ="http://api.zhuishushenqi.com/btoc/bookId?view=chapters";

    /**
     * CHAPTER_TEXT = 替换为 书籍text
     */
    public static final String CHAPTER_TEXT ="http://chapter2.zhuishushenqi.com/chapter/CHAPTER_TEXT";
}
