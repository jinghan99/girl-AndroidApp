package com.example.jingh.myapplication;

import android.content.Context;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.TextPaint;
import android.util.Log;
import android.widget.TextView;
import com.example.jingh.myapplication.adapter.ChapterTextFragmentStatePagerAdapter;
import com.example.jingh.myapplication.adapter.MyPagerAdapter;
import com.example.jingh.myapplication.disk.DiskLruCacheHelper;
import com.example.jingh.myapplication.entiy.BookChapter;
import com.example.jingh.myapplication.entiy.BookInfo;
import com.example.jingh.myapplication.entiy.BookSource;
import com.example.jingh.myapplication.entiy.ChapterText;
import com.example.jingh.myapplication.fragment.ChapterTextFragment;
import com.example.jingh.myapplication.utils.BookUtils;
import com.example.jingh.myapplication.utils.Utils;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Package 章节分页显示
 * @Description: TODO
 * @author: jingh
 * @date 2019/1/10 11:12
 */
public class ChapterTextActivityNew extends FragmentActivity implements ViewPager.OnPageChangeListener {

    private ChapterTextFragmentStatePagerAdapter chapterTextFragmentStatePagerAdapter;
    private List<ChapterTextFragment> list;
    private ViewPager viewPager;

    private List<String> textList;

    private BookInfo bookInfo;

    private List<BookChapter> bookChapterList;

    private int chapterPostion = 0;

    private ChapterText chapterText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_text_view_pager);
        viewPager = (ViewPager) findViewById(R.id.text_view_pager);
        bookInfo = (BookInfo) getIntent().getSerializableExtra("bookInfo");
        changeListener();
        new ChapterTextActivityNew.BookDataTask().execute(bookInfo.get_id());

    }

    /**
     * 载入分页数据
     */
    private void initAdapter() {
       if(textList !=null ){
           list = new ArrayList<>();
           for(String str : textList){
               ChapterTextFragment textFragment = new ChapterTextFragment();
               Bundle bundle = new Bundle();
               bundle.putString("position", str);//这里的values就是我们要传的值
               bundle.putString("title",chapterText.getTitle());
               textFragment.setArguments(bundle);
               list.add(textFragment);
           }
           chapterTextFragmentStatePagerAdapter = new ChapterTextFragmentStatePagerAdapter(getSupportFragmentManager(), list);
           //设置监听
           viewPager.setAdapter(chapterTextFragmentStatePagerAdapter);
       }
    }

    /**
     * 加入监听
     */
    private void changeListener() {
        viewPager.addOnPageChangeListener(this);
    }


    /**
     * 该方法监听当前页滑动状态的改变，
     *
     * @param arg0 参数arg0有三种状态（0,1,2）
     *             <p>
     *             arg0 == 0 时，表示什么都没做。即没有翻页
     *             <p>
     *             arg0 == 1 时，表示正在滑动。
     *             <p>
     *             arg0 == 2 时，表示滑动完毕。
     */
    @Override
    public void onPageScrollStateChanged(int arg0) {
        System.out.println(arg0);

    }

    /**
     * 当页面在滑动时调用该方法
     * <p>
     * position表示当前页面的索引值
     * <p>
     * arg1     表示当前页面偏移量的百分比
     * <p>
     * arg2     表示当前页面偏移了多少像素
     *
     * @param position
     * @param arg1
     * @param arg2
     */
    @Override
    public void onPageScrolled(int position, float arg1, int arg2) {
        if(position >= textList.size()-1){
//
        }

    }

    /**
     * 该方法在翻页完成后调用
     *
     * @param position
     */
    @Override
    public void onPageSelected(int position) {
        System.out.println(position);

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
                        String link = bookChapterList.get(chapterPostion).getLink();
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
         * @param txt
         */
        @Override
        protected void onPostExecute(ChapterText txt) {
            super.onPostExecute(txt);
            if(txt !=null ){
                chapterText  = txt;
                setVp(txt);
                initAdapter();
            }
        }
    }
    private void setVp(ChapterText chapterText) {
        textList = new ArrayList<>();
        String body = chapterText.getBody();
        int pageSize = 400;
        int pageNum = body.length() / pageSize + 1;
        for (int i = 0; i < pageNum; i++) {
            if(body.length() >pageSize ){
                textList.add( body.substring(0,pageSize).replaceAll("\n","\n    "));
                body = body.substring(pageSize);
            }else{
                textList.add(body);
            }
        }

    }

}
