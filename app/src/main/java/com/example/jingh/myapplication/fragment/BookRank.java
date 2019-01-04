package com.example.jingh.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.jingh.myapplication.activity.RankNameActivity;

import static com.example.jingh.myapplication.R.id;
import static com.example.jingh.myapplication.R.layout;

/**
 * 榜单 页面
 */
public class BookRank extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(layout.book_rank, container, false);
        ImageView manImage = (ImageView) view.findViewById(id.book_man);
        //监听
        manImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.putExtra("sexType","man");
                intent.setClass(getActivity(),RankNameActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
