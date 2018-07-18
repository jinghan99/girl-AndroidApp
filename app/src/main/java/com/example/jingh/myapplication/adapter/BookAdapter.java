package com.example.jingh.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.jingh.myapplication.entiy.BookEntiy;
import com.example.jingh.myapplication.R;

import java.util.List;

/**
 * @ClassName BookAdapter
 * @Description
 * @Date 2018/7/18 14:40
 * @Author jinghan
 * @Version 1.0
 */
public class BookAdapter extends ArrayAdapter {


    private final int resourceId;

    public BookAdapter(Context context, int textViewResourceId, List<BookEntiy> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BookEntiy book = (BookEntiy) getItem(position); // 获取当前项的Fruit实例


        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);//实例化一个对象

        TextView bookName = (TextView) view.findViewById(R.id.book_name);//获取该布局内的文本视图
        bookName.setText(book.getBookName());//为文本视图设置文本内容
        TextView bookType = (TextView) view.findViewById(R.id.book_type);//获取该布局内的文本视图
        bookType.setText(book.getBookType());

        TextView bookUpdateChapter = (TextView) view.findViewById(R.id.book_update_chapter);//获取该布局内的文本视图
        bookUpdateChapter.setText(book.getBookUpdateChapter());


        return view;
    }

}