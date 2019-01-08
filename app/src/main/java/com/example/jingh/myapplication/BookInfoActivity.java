package com.example.jingh.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.jingh.myapplication.appConstant.appConstant;
import com.example.jingh.myapplication.disk.DiskLruCacheHelper;
import com.example.jingh.myapplication.entiy.BookInfo;
import com.example.jingh.myapplication.helper.SwipeHelper;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @Package com.example.jingh.myapplication.activity
 * @Description: TODO
 * @author: jingh
 * @date 2019/1/8 15:10
 */
public class BookInfoActivity extends Activity {

    private BookInfo bookInfo;

    private Button             addRead;

    private DiskLruCacheHelper diskLruCacheHelper ;

    private Context context;

    private List idList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        bookInfo= (BookInfo) getIntent().getSerializableExtra("bookInfo");
        setContentView(R.layout.book_info);
        try {
            diskLruCacheHelper = new DiskLruCacheHelper(this);
        } catch (IOException e) {
            Log.e("diskLruCacheHelper", e.toString());
        }

        TextView title = (TextView) findViewById(R.id.book_name);
        title.setText(bookInfo.getTitle());
        ((TextView)findViewById(R.id.book_author)).setText(bookInfo.getAuthor());

        ((TextView)findViewById(R.id.book_majorCate)).setText(bookInfo.getMajorCate());
        ((TextView)findViewById(R.id.book_short_intro)).setText(bookInfo.getShortIntro());
        //加载图片
        Picasso.with(this).load(appConstant.imgUrlFirst+bookInfo.getCover()).into((ImageView)findViewById(R.id.book_image));

        /*
         * 方法一：使用匿名内部类实现button按钮的
         */
        //绑定button按钮
        addRead = (Button) findViewById(R.id.add_read);

        //监听button事件
        addRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                加入书架
                String ids = diskLruCacheHelper.getAsString(appConstant.CACHE_BOOK_ID);
                if(ids !=null){
                    idList =  Arrays.asList(ids.split(","));
                    if(bookInfo.get_id() !=null && !idList.contains(bookInfo.get_id())){
                        ids +=","+bookInfo.get_id();
                        diskLruCacheHelper.put(appConstant.CACHE_BOOK_ID,ids);
                        Toast ss = Toast.makeText(context, "加入成功",Toast.LENGTH_LONG);
                        ss.show() ;
                    }else{
                        Toast ss = Toast.makeText(context, "已在书架",Toast.LENGTH_LONG);
                        ss.show() ;
                    }
                }else{
                    diskLruCacheHelper.put(appConstant.CACHE_BOOK_ID,bookInfo.get_id());
                    Toast ss = Toast.makeText(context, "加入成功",Toast.LENGTH_LONG);
                    ss.show() ;
                }
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
}
