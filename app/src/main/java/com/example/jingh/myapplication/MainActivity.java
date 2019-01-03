package com.example.jingh.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.jingh.myapplication.fragment.BookRank;
import com.example.jingh.myapplication.fragment.MyBooks;
import com.example.jingh.myapplication.fragment.MyInfo;
import com.example.jingh.myapplication.utils.BottomBar;

public class MainActivity extends AppCompatActivity {

    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_main);
        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottom_bar);
        bottomBar.setContainer(R.id.fl_container)
                .setTitleBeforeAndAfterColor("#999999", "#ff5d5e")
                .addItem(MyBooks.class,
                        "我的书架",
                        R.mipmap.item1_before,
                        R.mipmap.item1_after)
                        .addItem(BookRank.class,
                        "排行版",
                        R.mipmap.item2_before,
                        R.mipmap.item2_after)
                        .addItem(MyInfo.class,
                        "我的",
                        R.mipmap.item3_before,
                        R.mipmap.item3_after)
                .build();

    }
}
