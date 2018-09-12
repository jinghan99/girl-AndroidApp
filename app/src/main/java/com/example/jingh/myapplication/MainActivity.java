package com.example.jingh.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import com.example.jingh.myapplication.adapter.BookAdapter;
import com.example.jingh.myapplication.appConstant.appConstant;
import com.example.jingh.myapplication.entiy.UpdateBook;
import com.example.jingh.myapplication.utils.JSONUtils;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List bookList = new ArrayList<>();

    private ListView listView;


    private BookAdapter adapter;

    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        //绑定初始化ButterKnife


//        RxCache.init( context);//为RxCache提供Context

        setContentView(R.layout.activity_main);
        adapter = new BookAdapter(context, R.layout.activity_book, bookList);
        listView = (ListView) findViewById(R.id.list_book);
        listView.setAdapter(adapter);
        initData();
    }


    public void initData() {
        /**
         * 1. 创建一个OkHttp框架的核心的对象OkHttpClient
         */
        OkHttpClient okHttpClient = new OkHttpClient();
        /**
         * 2. 创建一个请求对象Request
         */
        Request request = new Request.Builder().
                url(appConstant.bookUpdateUrl)
                .build();
        /**
         * 3. 生成一个新的请求任务
         */
        Call call = okHttpClient.newCall(request);
        /**
         * 4. 执行任务 把call添加到任务栈，一添加进入就会被立即执行 异步的请求
         */
        call.enqueue(new Callback() {
            // 当服务器返回数据的时候，call对象要回调该方法
            // response 就代表了服务器给我们返回的所有的数据
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // 获取服务器返回的状态码
                if (response.code() == 200) {
                    String result = response.body().string();
                    bookList.addAll(JSONUtils.GsonToList(result, UpdateBook.class));

                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                        }
                    });
                } else {

                }
            }
            // 当请求失败时call回调该方法（网络超时，IP地址写错）
            @Override
            public void onFailure(Call call, IOException exception) {

            }
        });
    }
}
