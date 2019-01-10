package com.example.jingh.myapplication;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.example.jingh.myapplication.entiy.BookChapter;
import com.example.jingh.myapplication.entiy.BookInfo;
import com.example.jingh.myapplication.entiy.BookSource;
import com.example.jingh.myapplication.entiy.ChapterText;
import com.example.jingh.myapplication.utils.BookUtils;
import com.example.jingh.myapplication.utils.Utils;
import com.example.jingh.myapplication.view.FlipperLayout;

import java.io.IOException;
import java.util.List;

/**
 * @Package com.example.jingh.myapplication
 * @Description: TODO
 * @author: jingh
 * @date 2019/1/10 17:29
 */
public class ChapterTextActivityThree extends Activity implements View.OnClickListener, FlipperLayout.TouchListener {
    //    当前显示文字
    private String text       = "";
    private int    textLenght = 0;

    private static final int COUNT = 400;

    private int currentTopEndIndex = 0;

    private int currentShowEndIndex = 0;

    private int currentBottomEndIndex = 0;

    private List<BookChapter> bookChapterList;
    private BookInfo          bookInfo;

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            FlipperLayout rootLayout = (FlipperLayout) findViewById(R.id.book_container);
            View recoverView = LayoutInflater.from(ChapterTextActivityThree.this).inflate(R.layout.book_text, null);
            View view1 = LayoutInflater.from(ChapterTextActivityThree.this).inflate(R.layout.book_text, null);
            View view2 = LayoutInflater.from(ChapterTextActivityThree.this).inflate(R.layout.book_text, null);
            rootLayout.initFlipperViews(ChapterTextActivityThree.this, view2, view1, recoverView);
            textLenght = text.length();
            System.out.println("----textLenght----->" + textLenght);

            TextView textView = (TextView) view1.findViewById(R.id.chapter_text_body);
            if (textLenght > COUNT) {
                textView.setText(text.subSequence(0, COUNT));
                textView = (TextView) view2.findViewById(R.id.chapter_text_body);
                if (textLenght > (COUNT << 1)) {
                    textView.setText(text.subSequence(COUNT, COUNT * 2));
                    currentShowEndIndex = COUNT;
                    currentBottomEndIndex = COUNT << 1;
                } else {
                    textView.setText(text.subSequence(COUNT, textLenght));
                    currentShowEndIndex = textLenght;
                    currentBottomEndIndex = textLenght;
                }
            } else {
                textView.setText(text.subSequence(0, textLenght));
                currentShowEndIndex = textLenght;
                currentBottomEndIndex = textLenght;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_container);
        bookInfo = (BookInfo) getIntent().getSerializableExtra("bookInfo");
        new ChapterTextActivityThree.BookDataTask().execute(bookInfo.get_id());
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 创建一个承载Text的View
     *
     * @param direction
     *
     * @return
     */
    @Override
    public View createView(final int direction) {
        String txt = "";
        if (direction == FlipperLayout.TouchListener.MOVE_TO_LEFT) {
            currentTopEndIndex = currentShowEndIndex;
            final int nextIndex = currentBottomEndIndex + COUNT;
            currentShowEndIndex = currentBottomEndIndex;
            if (textLenght > nextIndex) {
                txt = text.substring(currentBottomEndIndex, nextIndex);
                currentBottomEndIndex = nextIndex;
            } else {
                txt = text.substring(currentBottomEndIndex, textLenght);
                currentBottomEndIndex = textLenght;
            }
        } else {
            currentBottomEndIndex = currentShowEndIndex;
            currentShowEndIndex = currentTopEndIndex;
            currentTopEndIndex = currentTopEndIndex - COUNT;
            txt = text.substring(currentTopEndIndex - COUNT, currentTopEndIndex);
        }

        View view = LayoutInflater.from(this).inflate(R.layout.book_text, null);
        TextView textView = (TextView) view.findViewById(R.id.chapter_text_body);
        textView.setText(txt);

        System.out.println("-top->" + currentTopEndIndex + "-show->" + currentShowEndIndex + "--bottom-->" + currentBottomEndIndex);
        return view;
    }
    /**
     * 当前页是否有上一页（用来判断可滑动性）
     *
     * @return
     */
    @Override
    public boolean whetherHasPreviousPage() {
        return currentShowEndIndex > COUNT;
    }
    /***
     * 当前页是否有下一页（用来判断可滑动性）
     *
     * @return
     */
    @Override
    public boolean whetherHasNextPage() {
        return currentShowEndIndex < textLenght;
    }
    /***
     * 当前页是否是第一页
     *
     * @return
     */
    @Override
    public boolean currentIsFirstPage() {
        boolean should = currentTopEndIndex > COUNT;
        if (!should) {
            currentBottomEndIndex = currentShowEndIndex;
            currentShowEndIndex = currentTopEndIndex;
            currentTopEndIndex = currentTopEndIndex - COUNT;
        }
        return should;
    }
    /***
     * 当前页是否是最后一页
     *
     * @return
     */
    @Override
    public boolean currentIsLastPage() {
        boolean should = currentBottomEndIndex < textLenght;
        if (!should) {
            currentTopEndIndex = currentShowEndIndex;
            final int nextIndex = currentBottomEndIndex + COUNT;
            currentShowEndIndex = currentBottomEndIndex;
            if (textLenght > nextIndex) {
                currentBottomEndIndex = nextIndex;
            } else {
                currentBottomEndIndex = textLenght;
            }
        }
        return should;
    }


    //    ====================================================================huoqu
    /*
     * 定义内部类： <Params, Progress, Result>，实现网络异步访问
     */
    class BookDataTask extends AsyncTask<String, Void, ChapterText> {
        /**
         * 异步处理 获取书信息
         *
         * @param ids
         * @return
         */
        @Override
        protected ChapterText doInBackground(String... ids) {
            try {
                List<BookSource> bookSource = BookUtils.getBookSource(bookInfo.get_id());
                if (bookSource.size() > 1) {
                    String sourceId = bookSource.get(1).get_id();
                    bookChapterList = BookUtils.getBookChapterList(sourceId);
                    if (bookChapterList != null && bookChapterList.size() > 0) {
                        String link = bookChapterList.get(0).getLink();
                        return BookUtils.getBookChapterText(Utils.getURLEncoderString(link));
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
         *
         * @param
         */
        @Override
        protected void onPostExecute(ChapterText chapterText) {
            super.onPostExecute(chapterText);
            if (chapterText != null) {
                text = chapterText.getBody();
                handler.sendEmptyMessage(0);

            }
        }
    }
}
