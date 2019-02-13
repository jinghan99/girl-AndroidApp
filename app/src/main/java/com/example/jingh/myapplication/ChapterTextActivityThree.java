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
import com.example.jingh.myapplication.entiy.TxtPage;
import com.example.jingh.myapplication.listener.TouchListener;
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
public class ChapterTextActivityThree extends Activity implements View.OnClickListener, TouchListener {
    //    textView 显示文字
    private String text = "";

    private int textLenght = 0;


    private static final int COUNT = 350;

    private int currentTopEndIndex = 0;

    private int currentShowEndIndex = 0;

    private int currentBottomEndIndex = 0;

    // 当前章节列表
    private List<BookChapter> bookChapterList;

    /**
     * 当前章节索引
     */
    private int chapterIndex = 0;


    /**
     * 书信息
     */
    private BookInfo bookInfo;

    /**
     * 上一章节
     */
    private ChapterText previousShowChapter;

    /**
     * 当前显示的章节
     */
    private ChapterText currentShowChapter;

    /**
     * 下一章节
     */
    private ChapterText nextShowChapter;

    // 上一章的页面列表缓存
    private List<TxtPage> mPrePageList;

    // 当前章节的页面列表
    private List<TxtPage> mCurPageList;

    // 下一章的页面列表缓存
    private List<TxtPage> mNextPageList;





    /**
     *  * @param currentBottomView 最底层的view，初始状态看不到
     * 	 * @param currentShowView 正在显示的View
     * 	 * @param currentTopView 最上层的View，初始化时滑出屏幕
     */
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            FlipperLayout rootLayout = (FlipperLayout) findViewById(R.id.book_container);

            View currentTopView = LayoutInflater.from(ChapterTextActivityThree.this).inflate(R.layout.book_text, null);
            View currentShowView = LayoutInflater.from(ChapterTextActivityThree.this).inflate(R.layout.book_text, null);
            View currentBottomView = LayoutInflater.from(ChapterTextActivityThree.this).inflate(R.layout.book_text, null);
            rootLayout.initFlipperViews(ChapterTextActivityThree.this, currentBottomView, currentShowView, currentTopView);
            textLenght = text.length();
            System.out.println("----textLenght----->" + textLenght);

            TextView textView = (TextView) currentShowView.findViewById(R.id.chapter_text_body);

            if (textLenght > COUNT) {
                textView.setText(text.subSequence(0, COUNT));
                textView = (TextView) currentBottomView.findViewById(R.id.chapter_text_body);
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

    /**
     * 初始化 章节内容
     */
    private void loadPageList(){

    }

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
     * @return
     */
    @Override
    public View createView(final int direction) {
        String txt = "";
        if (direction == TouchListener.MOVE_TO_LEFT) {
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
        boolean flag = currentShowEndIndex > COUNT;
        return flag;
    }

    /***
     * 当前页是否有下一页（用来判断可滑动性）
     *
     * @return
     */
    @Override
    public boolean whetherHasNextPage() {
        boolean flag =  currentShowEndIndex < textLenght;
        if(!flag){
//            若没有下一页 检测是否有下一章
            if(nextShowChapter !=null){
//                下一章有内容

            }
        }

        return flag;
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
                    bookChapterList = BookUtils.getBookChapterList(bookSource.get(1).get_id());
                    previousShowChapter =  BookUtils.getChapterTextByChapterList(bookSource.get(1).get_id(),chapterIndex-1);
                    currentShowChapter =  BookUtils.getChapterTextByChapterList(bookSource.get(1).get_id());
                    nextShowChapter = BookUtils.getChapterTextByChapterList(bookSource.get(1).get_id(), chapterIndex + 1);
                }else{
                    bookChapterList = BookUtils.getBookChapterList(bookSource.get(0).get_id());
                    previousShowChapter =  BookUtils.getChapterTextByChapterList(bookSource.get(0).get_id(),chapterIndex-1);
                    currentShowChapter =  BookUtils.getChapterTextByChapterList(bookSource.get(0).get_id());
                    nextShowChapter = BookUtils.getChapterTextByChapterList(bookSource.get(0).get_id(), chapterIndex + 1);
                }
                return currentShowChapter;
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
