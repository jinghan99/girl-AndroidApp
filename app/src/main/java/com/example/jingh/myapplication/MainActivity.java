package com.example.jingh.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.jingh.myapplication.disk.DiskLruCacheHelper;
import com.example.jingh.myapplication.fragment.MyBookBottomBar;
import com.example.jingh.myapplication.fragment.MyInfoBottomBar;
import com.example.jingh.myapplication.fragment.RankBottomBar;
import com.example.jingh.myapplication.utils.BottomBar;

public class MainActivity extends AppCompatActivity {

    private Context context;

    private DiskLruCacheHelper diskLruCacheHelper ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;

        setContentView(R.layout.activity_main);
        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottom_bar);
        bottomBar.setContainer(R.id.bottom_bar_container)
                .setTitleBeforeAndAfterColor("#999999", "#ff5d5e")
                .addItem(MyBookBottomBar.class,
                        "我的书架",
                        R.mipmap.item1_before,
                        R.mipmap.item1_after)
                        .addItem(RankBottomBar.class,
                        "排行版",
                        R.mipmap.item2_before,
                        R.mipmap.item2_after)
                        .addItem(MyInfoBottomBar.class,
                        "我",
                        R.mipmap.item3_before,
                        R.mipmap.item3_after)
                .build();

    }
}
