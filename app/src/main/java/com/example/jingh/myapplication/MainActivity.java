package com.example.jingh.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import com.example.jingh.myapplication.adapter.BookAdapter;
import com.example.jingh.myapplication.entiy.BookEntiy;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<BookEntiy> bookList = new ArrayList<BookEntiy>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruits(); // 初始化数据
        BookAdapter adapter = new BookAdapter(MainActivity.this, R.layout.activity_book, bookList);
        ListView listView = (ListView) findViewById(R.id.list_book);
        listView.setAdapter(adapter);

    }

//String bookImage, String bookName, String bookType, String updateTime, String bookUpdateChapter
    private void initFruits() {
        int size = 10;
        for(int i=0;i<size;i++){
            BookEntiy book = new BookEntiy("null","圣墟"+i,"连载中。。",i+"个月前","第九十五章 妖邪");
            bookList.add(book);
        }

    }

}
