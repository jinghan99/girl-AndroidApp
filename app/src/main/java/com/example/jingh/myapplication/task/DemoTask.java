package com.example.jingh.myapplication.task;

import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.jingh.myapplication.MainActivity;
import com.example.jingh.myapplication.R;
import com.example.jingh.myapplication.entiy.BookInfo;
import com.example.jingh.myapplication.utils.BizConstant;
import com.example.jingh.myapplication.utils.FormatUtils;
import com.example.jingh.myapplication.utils.JSONUtils;
import com.squareup.picasso.Picasso;
import okhttp3.*;

import java.io.IOException;

/**
 * @Package  获取每本书的信息
 * @Description: TODO
 * @author: jingh
 * @date 2018/12/28 11:42
 * Params, Progress, Result
 */
public class DemoTask extends AsyncTask<String, Integer, BookInfo> {
    //执行后台任务前做一些UI操作
    @Override
    protected void onPreExecute() {

    }
    //执行后台任务（耗时操作）,不可在此方法内修改UI
    @Override
    protected BookInfo doInBackground(String... params) {
        //获取传进来的 书的 id 参数
        String id = params[0];


        /**
         * 1. 创建一个OkHttp框架的核心的对象OkHttpClient
         */
        OkHttpClient okHttpClient = new OkHttpClient();
        /**
         * 2. 创建一个请求对象Request
         */
        Request request = new Request.Builder().
                url(BizConstant.bookInfo.getValue()+id)
                .build();
        /**
         * 3. 生成一个新的请求任务
         */
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            String result = response.body().string();
            return JSONUtils.GsonToBean(result, BookInfo.class);
        } catch (IOException e) {
            return null;
        }
    }

    //更新进度信息
    @Override
    protected void onProgressUpdate(Integer... progresses) {

    }

    /**
     * 执行完后台任务后更新UI
     * onPostExecute用于UI的更新.此方法的参数为doInBackground方法返回的值.
     * @param result
     */
    @Override
    protected void onPostExecute(BookInfo result) {


    }

    //取消执行中的任务时更改UI
    @Override
    protected void onCancelled() {

    }
}
