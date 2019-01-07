package com.example.jingh.myapplication.fragment.tab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.jingh.myapplication.R;

/**
 * Created by q805699513 on 2017/5/16.
 */

public class HeiheiFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.rank_tab_view, container, false);
            //在这里做一些初始化处理
            TextView textView = (TextView) view.findViewById(R.id.rank_tab_content);
            textView.setText("嘿嘿");
        } else {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            if (viewGroup != null)
                viewGroup.removeView(view);
        }
        return view;

    }
}

