package com.example.jingh.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.jingh.myapplication.R;
import com.example.jingh.myapplication.entiy.BookInfo;
import com.example.jingh.myapplication.entiy.UpdateBook;
import com.example.jingh.myapplication.utils.FormatUtils;
import com.google.gson.internal.LinkedTreeMap;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * @ClassName BookAdapter
 * @Description
 * @Date 2018/7/18 14:40
 * @Author jinghan
 * @Version 1.0
 */
public class BookAdapter extends ArrayAdapter {

    private static final String imgUrlFirst="http://statics.zhuishushenqi.com";

    private final int resourceId;

    private Context context;

    public BookAdapter(Context context, int textViewResourceId, List<BookInfo> objects) {
        super(context, textViewResourceId, objects);
        this.context = context;
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BookInfo book = (BookInfo) getItem(position); // 获取当前项的实例


        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);//实例化一个对象

        TextView bookName = (TextView) view.findViewById(R.id.book_name);//获取该布局内的文本视图
        bookName.setText(book.getTitle().toString());//为文本视图设置文本内容
        TextView bookType = (TextView) view.findViewById(R.id.book_type);//获取该布局内的文本视图

        bookType.setText(book.getAuthor().toString());

        TextView bookUpdateChapter = (TextView) view.findViewById(R.id.book_update_chapter);//获取该布局内的文本视图
        bookUpdateChapter.setText(book.getLastChapter().toString());

        TextView update_time = (TextView) view.findViewById(R.id.update_time);//获取该布局内的文本视图

        update_time.setText(FormatUtils.getDescriptionTimeFromDate(book.getUpdated()));

//        加载图片
        Picasso.with(context).load(imgUrlFirst+book.getCover())
                .into((ImageView) view.findViewById(R.id.book_image));

        return view;
    }

}
