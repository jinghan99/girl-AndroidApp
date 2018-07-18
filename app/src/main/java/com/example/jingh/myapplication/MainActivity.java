package com.example.jingh.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;
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

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:
                    String str = (String) msg.obj;
                    bookList.addAll(JSONUtils.GsonToList(str,UpdateBook.class)) ;
                    adapter.notifyDataSetChanged();
                    break;
                case 2:
                    Toast.makeText(MainActivity.this, "状态码不正确：" + msg.obj,
                            Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    Toast.makeText(MainActivity.this, "异常啦：" + msg.obj,
                            Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        initFruits(); // 初始化数据
        get();
        adapter = new BookAdapter(MainActivity.this, R.layout.activity_book, bookList);
        listView = (ListView) findViewById(R.id.list_book);
        listView.setAdapter(adapter);

    }


    public void get() {
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
            public void onResponse(Call call, Response response)
                    throws IOException {
                Log.d("tag", "ThreadName=" + Thread.currentThread().getName());
                // 获取服务器返回的状态码
                if (response.code() == 200) {
                    String result = response.body().string();
                    handler.obtainMessage(1, result).sendToTarget();
                } else {
                    handler.obtainMessage(2, response.code()).sendToTarget();
                }
            }
            // 当请求失败时call回调该方法（网络超时，IP地址写错）
            @Override
            public void onFailure(Call call, IOException exception) {
                handler.obtainMessage(3, exception.toString()).sendToTarget();
            }
        });
    }

//    public void initFruits() {
//        //创建OKHttpClient对象
//        OKHttpUitls okHttpUitls = new OKHttpUitls();
//        okHttpUitls.get(appConstant.bookUpdateUrl);
//
//        okHttpUitls.setOnOKHttpGetListener(new OKHttpUitls.OKHttpGetListener() {
//            @Override
//            public void error(String error) {
//                Toast.makeText(MainActivity.this,error,Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void success(String json) {
//                bookList = JSONUtils.GsonToList(json,UpdateBook.class);
//            }
//        });
//    }

}
