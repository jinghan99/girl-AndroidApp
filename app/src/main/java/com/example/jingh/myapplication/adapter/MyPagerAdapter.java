package com.example.jingh.myapplication.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.jingh.myapplication.R;
import com.example.jingh.myapplication.entiy.ChapterText;

import java.util.List;

/**
 * @Package com.example.jingh.myapplication.adapter
 * @Description: TODO
 * @author: jingh
 * @date 2019/1/9 21:15
 */
public class MyPagerAdapter  extends PagerAdapter {
    private Context      mContext;
    private List<String> mData;
    private ChapterText  chapterText;

    public MyPagerAdapter(Context context ,List<String> list,ChapterText  text) {
        mContext = context;
        mData = list;
        chapterText = text;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(mContext, R.layout.book_text,null);
        TextView tv = (TextView) view.findViewById(R.id.chapter_text_body);
        tv.setText(mData.get(position));
        ((TextView) view.findViewById(R.id.chapter_text_title)).setText(chapterText.getTitle());
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // super.destroyItem(container,position,object); 这一句要删除，否则报错
        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
