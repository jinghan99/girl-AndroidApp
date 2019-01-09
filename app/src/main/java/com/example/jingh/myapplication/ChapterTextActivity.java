package com.example.jingh.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import com.example.jingh.myapplication.entiy.BookChapter;
import com.example.jingh.myapplication.entiy.BookInfo;
import com.example.jingh.myapplication.entiy.BookSource;
import com.example.jingh.myapplication.entiy.ChapterText;
import com.example.jingh.myapplication.utils.BookUtils;
import com.example.jingh.myapplication.utils.Utils;

import java.io.IOException;
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

    private TextView titleView;

    private TextView bodyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        bookInfo = (BookInfo) getIntent().getSerializableExtra("bookInfo");
        setContentView(R.layout.book_text);
        titleView = (TextView) findViewById(R.id.chapter_text_title);
        bodyView = (TextView) findViewById(R.id.chapter_text_body);
        new ChapterTextActivity.BookDataTask().execute(bookInfo.get_id());
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
            try {
                List<BookSource> bookSource = BookUtils.getBookSource(bookInfo.get_id());
                if(bookSource.size()>1){
                    String sourceId =bookSource.get(1).get_id();
                    bookChapterList = BookUtils.getBookChapterList(sourceId);
                    if(bookChapterList !=null && bookChapterList.size()>0){
                        String link = bookChapterList.get(0).getLink();
                        return BookUtils.getBookChapterText(Utils.getURLEncoderString(link)) ;
                    }
                }
                return null;
            } catch (IOException e) {
                return null;
            }
        }
        /**
         * 执行完后台任务后更新UI
         * onPostExecute用于UI的更新.此方法的参数为doInBackground方法返回的值.
         * @param chapterText
         */
        @Override
        protected void onPostExecute(final ChapterText chapterText) {
            super.onPostExecute(chapterText);
            if(chapterText !=null ){
                titleView.setText(chapterText.getTitle());
                bodyView.setText(chapterText.getBody());
                bodyView.setMovementMethod(ScrollingMovementMethod.getInstance());
            }
        }
    }
}
