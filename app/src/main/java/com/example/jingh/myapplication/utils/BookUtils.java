package com.example.jingh.myapplication.utils;

import com.example.jingh.myapplication.entiy.BookInfo;
import com.google.gson.internal.LinkedTreeMap;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Package com.example.jingh.myapplication.utils
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
}
