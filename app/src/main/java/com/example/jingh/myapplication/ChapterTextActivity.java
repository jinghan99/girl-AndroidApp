package com.example.jingh.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import com.example.jingh.myapplication.adapter.MyPagerAdapter;
import com.example.jingh.myapplication.entiy.BookChapter;
import com.example.jingh.myapplication.entiy.BookInfo;
import com.example.jingh.myapplication.entiy.BookSource;
import com.example.jingh.myapplication.entiy.ChapterText;
import com.example.jingh.myapplication.utils.BookUtils;
import com.example.jingh.myapplication.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Package 章节内容
 * @Description: 分页
 * @author: jingh
 * @date 2019/1/8 15:10
 */
public class ChapterTextActivity extends Activity {

    private BookInfo bookInfo;

    private Context context;

    private List<BookChapter> bookChapterList;

    private int chapterPostion = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        bookInfo = (BookInfo) getIntent().getSerializableExtra("bookInfo");
        new ChapterTextActivity.BookDataTask().execute(bookInfo.get_id());
        setContentView(R.layout.book_text_view_pager);

    }

    /*
     * 定义内部类： <Params, Progress, Result>，实现网络异步访问
     */
    class BookDataTask extends AsyncTask<String, Void,ChapterText> {
        /**
         * 异步处理 获取书信息
         * @param   ids
         * @return
         */
        @Override
        protected ChapterText doInBackground(String... ids) {
//            try {
//                List<BookSource> bookSource = BookUtils.getBookSource(bookInfo.get_id());
//                if(bookSource.size()>1){
//                    String sourceId =bookSource.get(1).get_id();
//                    bookChapterList = BookUtils.getBookChapterList(sourceId);
//                    if(bookChapterList !=null && bookChapterList.size()>0){
//                        String link = bookChapterList.get(chapterPostion).getLink();
//                        return BookUtils.getBookChapterText(Utils.getURLEncoderString(link)) ;
//                    }
//                }
//                return null;
//            } catch (IOException e) {
//                return null;
//            }
            return null;
        }
        /**
         * 执行完后台任务后更新UI
         * onPostExecute用于UI的更新.此方法的参数为doInBackground方法返回的值.
         * @param chapterText
         */
        @Override
        protected void onPostExecute(ChapterText chapterText) {
            super.onPostExecute(chapterText);
            if(chapterText !=null ){
//                setVp(chapterText);
            }
        }
    }
//    private void setVp(ChapterText chapterText) {
//        List<String> list = new ArrayList<>();
//        String body = chapterText.getBody();
//        int pageSize = 400;
//        int pageNum = body.length() / pageSize + 1;
//        for (int i = 0; i < pageNum; i++) {
//            if(body.length() >pageSize ){
//                list.add( body.substring(0,pageSize).replaceAll("\n","\n    "));
//                body = body.substring(pageSize);
//            }else{
//                list.add(body);
//            }
//        }
//        ViewPager vp = (ViewPager) findViewById(R.id.text_view_pager);
//        vp.setAdapter(new MyPagerAdapter(this,list,chapterText));
//    }
}
