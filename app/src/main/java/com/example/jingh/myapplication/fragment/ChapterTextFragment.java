package com.example.jingh.myapplication.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.jingh.myapplication.R;

/**
 * @Package
 * @Description: 分页显示页面
 * @author: jingh
 * @date 2019/1/10 11:10
 */
public class ChapterTextFragment extends Fragment {
    private View     view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.book_text, null);
        Bundle bundle = getArguments();
        ((TextView) view.findViewById(R.id.chapter_text_body)).setText( bundle.getString("position"));
        ((TextView) view.findViewById(R.id.chapter_text_title)).setText(bundle.getString("title"));
        return view;
    }
}
