package com.example.jingh.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import com.example.jingh.myapplication.adapter.BookAdapter;
import com.example.jingh.myapplication.entiy.BookInfo;
import com.example.jingh.myapplication.fragment.Fragment1;
import com.example.jingh.myapplication.fragment.Fragment2;
import com.example.jingh.myapplication.fragment.Fragment3;
import com.example.jingh.myapplication.utils.BizConstant;
import com.example.jingh.myapplication.utils.BottomBar;
import com.example.jingh.myapplication.utils.JSONUtils;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private static final String [] bookIds = {"592fe687c60e3c4926b040ca","53e56ee335f79bb626a496c9","5b0d28378659ea1aab8ca218","59e2c2b08bde16e66f9e3b85","5816b415b06d1d32157790b1"};

    private ListView listView;


    private BookAdapter adapter;

    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;

//        setContentView(R.layout.activity_main_old);
//        listView = (ListView) findViewById(R.id.list_book);
//        new BookDataTask().execute(bookIds);


        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottom_bar);
        bottomBar.setContainer(R.id.fl_container)
                .setTitleBeforeAndAfterColor("#999999", "#ff5d5e")
                .addItem(Fragment1.class,
                        "首页",
                        R.mipmap.item1_before,
                        R.mipmap.item1_after)
                .addItem(Fragment2.class,
                        "订单",
                        R.mipmap.item2_before,
                        R.mipmap.item2_after)
                .addItem(Fragment3.class,
                        "我的",
                        R.mipmap.item3_before,
                        R.mipmap.item3_after)
                .build();
    }



    /*
     * 定义内部类： <Params, Progress, Result>，实现网络异步访问
     */
    class BookDataTask extends AsyncTask<String, Void, List<BookInfo>> {
        /**
         * 异步处理 获取书信息
         * @param   ids
         * @return
         */
        @Override
        protected List<BookInfo> doInBackground(String... ids) {
            List<BookInfo> bookList = new ArrayList<>();
            for (String id : ids) {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder().url(BizConstant.bookInfo.getValue() + id).build();
                Call call = okHttpClient.newCall(request);
                try {
                    String result = call.execute().body().string();
                    bookList.add(JSONUtils.GsonToBean(result, BookInfo.class));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return bookList;
        }

        /**
         * 执行完后台任务后更新UI
         * onPostExecute用于UI的更新.此方法的参数为doInBackground方法返回的值.
         * @param bookList
         */
        @Override
        protected void onPostExecute(List<BookInfo> bookList) {
            super.onPostExecute(bookList);
            adapter = new BookAdapter(context, R.layout.activity_book, bookList);
            listView.setAdapter(adapter);
        }
    }

}
