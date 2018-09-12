package com.example.jingh.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.jingh.myapplication.R;
import com.example.jingh.myapplication.entiy.UpdateBook;
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

    public BookAdapter(Context context, int textViewResourceId, List<UpdateBook> objects) {
        super(context, textViewResourceId, objects);
        this.context = context;
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinkedTreeMap book = (LinkedTreeMap) getItem(position); // 获取当前项的Fruit实例


        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);//实例化一个对象

        TextView bookName = (TextView) view.findViewById(R.id.book_name);//获取该布局内的文本视图
        bookName.setText(book.get("author").toString());//为文本视图设置文本内容
        TextView bookType = (TextView) view.findViewById(R.id.book_type);//获取该布局内的文本视图
        bookType.setText(book.get("updated").toString());

        TextView bookUpdateChapter = (TextView) view.findViewById(R.id.book_update_chapter);//获取该布局内的文本视图
        bookUpdateChapter.setText(book.get("lastChapter").toString());

//        加载图片
        Picasso.with(context)
                .load(imgUrlFirst+"/agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F2258545%2F2258545_6ac95535e17d4b03b9ce3cabbd2c402c.jpg%2F")
                .into((ImageView) view.findViewById(R.id.book_image));

        return view;
    }

}
