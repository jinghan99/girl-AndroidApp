package com.example.jingh.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.jingh.myapplication.R;

/**
 * 爱看榜 跳转 至 榜单名录
 */
public class RankNameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rank_name);
        //取得启动该Activity的Intent对象
        Intent intent =getIntent();
        String sexType = intent.getStringExtra("sexType");
        //取得TextView对象

    }
}
