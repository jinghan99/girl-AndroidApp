package com.example.jingh.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.jingh.myapplication.appConstant.appConstant;
import com.example.jingh.myapplication.entiy.BookInfo;
import com.example.jingh.myapplication.helper.SwipeHelper;
import com.squareup.picasso.Picasso;

/**
 * @Package com.example.jingh.myapplication.activity
 * @Description: TODO
 * @author: jingh
 * @date 2019/1/8 15:10
 */
public class BookInfoActivity extends Activity {

    private BookInfo bookInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bookInfo= (BookInfo) getIntent().getSerializableExtra("bookInfo");
        setContentView(R.layout.book_info);


        TextView title = (TextView) findViewById(R.id.book_name);
        title.setText(bookInfo.getTitle());
        ((TextView)findViewById(R.id.book_author)).setText(bookInfo.getAuthor());

        ((TextView)findViewById(R.id.book_majorCate)).setText(bookInfo.getMajorCate());

        //加载图片
        Picasso.with(this).load(appConstant.imgUrlFirst+bookInfo.getCover()).into((ImageView)findViewById(R.id.book_image));

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean processTouchEvent = SwipeHelper.instance().processTouchEvent(ev);
        if (processTouchEvent) {
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }
}
