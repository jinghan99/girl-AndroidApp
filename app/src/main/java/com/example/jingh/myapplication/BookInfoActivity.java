package com.example.jingh.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.jingh.myapplication.appConstant.AppConstant;
import com.example.jingh.myapplication.disk.DiskLruCacheHelper;
import com.example.jingh.myapplication.entiy.BookChapter;
import com.example.jingh.myapplication.entiy.BookInfo;
import com.example.jingh.myapplication.entiy.BookSource;
import com.example.jingh.myapplication.entiy.ChapterText;
import com.example.jingh.myapplication.helper.SwipeHelper;
import com.example.jingh.myapplication.utils.BookUtils;
import com.example.jingh.myapplication.utils.Utils;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @Package com.example.jingh.myapplication.activity
 * @Description: 书籍详情页
 * @author: jingh
 * @date 2019/1/8 15:10
 */
public class BookInfoActivity extends Activity {

    private BookInfo bookInfo;

    private Button addRead;

    private Button startRead;

    private Context context;

    private List idList;

    private List<BookChapter> bookChapterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        bookInfo = (BookInfo) getIntent().getSerializableExtra("bookInfo");
        setContentView(R.layout.book_info);

        initView();
        //加载图片
        Picasso.with(this).load(AppConstant.imgUrlFirst + bookInfo.getCover()).into((ImageView) findViewById(R.id.book_image));

        initListener();

        //加载章节信息
       new BookInfoActivity.BookDataTask().execute(bookInfo.get_id());

    }

    private void initView() {
        TextView title = (TextView) findViewById(R.id.book_name);
        title.setText(bookInfo.getTitle());
        ((TextView) findViewById(R.id.book_author)).setText(bookInfo.getAuthor());

        ((TextView) findViewById(R.id.book_majorCate)).setText(bookInfo.getMajorCate());
        ((TextView) findViewById(R.id.book_short_intro)).setText(bookInfo.getShortIntro());
    }

    private void initListener() {
        //绑定button按钮
        addRead = (Button) findViewById(R.id.add_read);
        startRead = (Button) findViewById(R.id.start_read);
        //监听button事件
        addRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                加入书架
                String ids = DiskLruCacheHelper.getInstance().getAsString(AppConstant.CACHE_BOOK_ID);
                if (ids != null) {
                    idList = Arrays.asList(ids.split(","));
                    if (bookInfo.get_id() != null && !idList.contains(bookInfo.get_id())) {
                        ids += "," + bookInfo.get_id();
                        DiskLruCacheHelper.getInstance().put(AppConstant.CACHE_BOOK_ID, ids);
                        Toast ss = Toast.makeText(context, "加入成功", Toast.LENGTH_LONG);
                        ss.show();
                    } else {
                        Toast ss = Toast.makeText(context, "已在书架", Toast.LENGTH_LONG);
                        ss.show();
                    }
                } else {
                    DiskLruCacheHelper.getInstance().put(AppConstant.CACHE_BOOK_ID, bookInfo.get_id());
                    Toast ss = Toast.makeText(context, "加入成功", Toast.LENGTH_LONG);
                    ss.show();
                }
            }
        });

        //监听button事件
        startRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                开始阅读
                Intent intent=new Intent(context, ChapterTextActivityNew .class);
                intent.putExtra("bookInfo", (Serializable) bookInfo);

                startActivity(intent);
            }
        });

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean processTouchEvent = SwipeHelper.instance().processTouchEvent(ev);
        if (processTouchEvent) {
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }

    /*
     * 定义内部类： <Params, Progress, Result>，实现网络异步访问
     */
    class BookDataTask extends AsyncTask<String, Void,List<BookChapter>> {
        /**
         * 异步处理 获取章节信息
         * @param   ids
         * @return
         */
        @Override
        protected List<BookChapter> doInBackground(String... ids) {
            try {
                List<BookSource> bookSource = BookUtils.getBookSource(bookInfo.get_id());
                int index = bookSource.size() > 0 ? 1 : 0;
                String sourceId =bookSource.get(index).get_id();
                bookChapterList = BookUtils.getBookChapterList(sourceId);
                return null;
            } catch (IOException e) {
                return null;
            }
        }

    }
}
