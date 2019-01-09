package com.example.jingh.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.jingh.myapplication.R;
import com.example.jingh.myapplication.appConstant.AppConstant;
import com.example.jingh.myapplication.entiy.BookInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * @ClassName BookAdapter 适配器
 * @Description
 * @Date 2018/7/18 14:40
 * @Author jinghan
 * @Version 1.0
 */
public class RankBookAdapter extends ArrayAdapter {



    private final int resourceId;

    private Context context;

    public RankBookAdapter(Context context, int textViewResourceId, List<BookInfo> objects) {
        super(context, textViewResourceId, objects);
        this.context = context;
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BookInfo book = (BookInfo) getItem(position); // 获取当前项的实例
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);//实例化一个对象

//      书名
        TextView bookName = (TextView) view.findViewById(R.id.rank_book_name);//获取该布局内的文本视图
//      简介
        TextView shortIntro = (TextView) view.findViewById(R.id.rank_book_short_intro);//获取该布局内的文本视图
//      作者
        TextView author = (TextView) view.findViewById(R.id.rank_book_author);//获取该布局内的文本视图

//      追书人数
        TextView latelyFollower = (TextView) view.findViewById(R.id.rank_book_lately_follower);//获取该布局内的文本视图

        bookName.setText(book.getTitle());//为文本视图设置文本内容
        author.setText(book.getAuthor());
        shortIntro.setText(book.getShortIntro());
        latelyFollower.setText(
                book.getLatelyFollower().length()>2 ?book.getLatelyFollower().substring(0,book.getLatelyFollower().length()-2) :book.getLatelyFollower()

        );

        //加载图片
        Picasso.with(context).load(AppConstant.imgUrlFirst + book.getCover()).into((ImageView) view.findViewById(R.id.rank_book_image));
        return view;
    }
}
