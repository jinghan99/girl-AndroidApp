package com.example.jingh.myapplication.utils;

import com.example.jingh.myapplication.appConstant.AppConstant;
import com.example.jingh.myapplication.entiy.BookChapter;
import com.example.jingh.myapplication.entiy.BookInfo;
import com.example.jingh.myapplication.entiy.BookSource;
import com.example.jingh.myapplication.entiy.ChapterText;
import com.google.gson.internal.LinkedTreeMap;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Package  工具类
 * @Description: TODO
 * @author: jingh
 * @date 2019/1/8 13:47
 */
public class BookUtils {



    private static volatile OkHttpClient okHttpClient;



    /**
     * 获取 连接
     * @return
     */
    public static OkHttpClient getInstance() {
        if (okHttpClient == null) {
            synchronized (BookUtils.class) {
                if (okHttpClient == null) {
                    okHttpClient = new OkHttpClient();
                }
            }
        }
        return okHttpClient;
    }


    /**
     *  获取榜单 书籍信息
     * @param url
     * @throws IOException
     */
    public static List<BookInfo> getRankBookList(String url) throws IOException {
        List<BookInfo> bookList = new ArrayList<>();
        Request request = new Request.Builder().url(url).build();
        Call call = getInstance().newCall(request);
        String result = call.execute().body().string();
        Map<String, Object> stringObjectMap = JSONUtils.GsonToMaps(result);
        System.out.println(stringObjectMap.size());
        LinkedTreeMap ranking = (LinkedTreeMap) stringObjectMap.get("ranking");
        ArrayList<LinkedTreeMap> books = (ArrayList) ranking.get("books");
        for(LinkedTreeMap map :books){
            BookInfo bookInfo = JSONUtils.GsonToBean(JSONUtils.GsonString(map), BookInfo.class);
            bookList.add(bookInfo);
        }
        return bookList;
    }

    /**
     * 获取书籍来源
     * @param bookId
     * @return
     * @throws IOException
     */
    public static List<BookSource> getBookSource(String bookId) throws IOException {
        List<BookSource> sourceList = new ArrayList<>();
        Request request = new Request.Builder().url(AppConstant.BOOK_SOURCE.replace("bookId",bookId)).build();
        Call call = getInstance().newCall(request);
        String result = call.execute().body().string();
        List<Map<String, Object>> maps = JSONUtils.GsonToListMaps(result);
        if(maps !=null){
            for(Map<String,Object> map:maps){
                BookSource bookSource =JSONUtils.GsonObjToBean(map,BookSource.class) ;
                sourceList.add(bookSource);
            }
        }
        return sourceList;
    }


    /**
     * 获取章节信息 由书籍来源id 提供
     * @param bookId
     * @return
     */
    public static List<BookChapter> getBookChapterList(String bookId) throws IOException {
        List<BookChapter> chapterList = new ArrayList<>();
        Request request = new Request.Builder().url(AppConstant.BOOK_CHAPTER.replace("bookId",bookId)).build();
        Call call = getInstance().newCall(request);
        String result = call.execute().body().string();
        Map<String, Object> stringObjectMap = JSONUtils.GsonToMaps(result);
//        章节信息
        if(stringObjectMap !=null ){
            ArrayList<LinkedTreeMap> list = (ArrayList<LinkedTreeMap>) stringObjectMap.get("chapters");
            for(LinkedTreeMap linkedTreeMap:list){
                chapterList.add(JSONUtils.GsonObjToBean(linkedTreeMap,BookChapter.class));
            }
        }
        return chapterList;
    }
    /**
     * 获取章节内容
     * @param url
     * @return
     */
    public static ChapterText getBookChapterText(String url) throws IOException {

        Request request = new Request.Builder().url(AppConstant.CHAPTER_TEXT.replace("CHAPTER_TEXT",url)).build();
        Call call = getInstance().newCall(request);
        String result = call.execute().body().string();
        Map<String, Object> stringObjectMap = JSONUtils.GsonToMaps(result);
        return JSONUtils.GsonObjToBean( stringObjectMap.get("chapter"),ChapterText.class);
    }


}
