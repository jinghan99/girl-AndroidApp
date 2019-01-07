package com.example.jingh.myapplication.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.example.jingh.myapplication.R;
import com.example.jingh.myapplication.adapter.BookAdapter;
import com.example.jingh.myapplication.entiy.BookInfo;
import com.example.jingh.myapplication.utils.BizConstant;
import com.example.jingh.myapplication.utils.JSONUtils;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 我的书架 底部导航栏
 */
public class MyBookBottomBar extends Fragment {

    private static final String [] bookIds = {"5a72f91a3f334879bf2a8275","5ab8999eba32357ab6ebab98","592fe687c60e3c4926b040ca","53e56ee335f79bb626a496c9","5b0d28378659ea1aab8ca218","59e2c2b08bde16e66f9e3b85","5816b415b06d1d32157790b1"};

    private ListView listView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.my_books_bottom, container, false);
        listView = (ListView)view.findViewById(R.id.my_book_list);
        new BookDataTask().execute(bookIds);
        return view;
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
            listView.setAdapter(new BookAdapter(getActivity(), R.layout.my_book_info, bookList));
        }
    }
}
