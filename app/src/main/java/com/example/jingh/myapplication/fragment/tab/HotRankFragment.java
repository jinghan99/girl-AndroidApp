package com.example.jingh.myapplication.fragment.tab;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.example.jingh.myapplication.R;
import com.example.jingh.myapplication.adapter.RankBookAdapter;
import com.example.jingh.myapplication.entiy.BookInfo;
import com.example.jingh.myapplication.utils.BizConstant;
import com.example.jingh.myapplication.utils.BookUtils;

import java.io.IOException;
import java.util.List;

/**
 * 最热榜
 */

public class HotRankFragment extends Fragment {

    private View     view;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.rank_book_list, container, false);

            listView = (ListView)view.findViewById(R.id.rank_book_list);
            new HotRankFragment.BookDataTask().execute();
            return view;
        } else {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            if (viewGroup != null){
                viewGroup.removeView(view);
            }
        }
        return view;

    }

    /*
     * 定义内部类： <Params, Progress, Result>，实现网络异步访问
     */
    class BookDataTask extends AsyncTask<String, Void, List<BookInfo>> {
        /**
         * 异步处理 获取书信息
         * @param   ids
         * @return
         */
        @Override
        protected List<BookInfo> doInBackground(String... ids) {
            try {
                return BookUtils.getRankBookList(BizConstant.RANK_HOT_MAN.getValue());
            } catch (IOException e) {
                return null;
            }

        }



        /**
         * 执行完后台任务后更新UI
         * onPostExecute用于UI的更新.此方法的参数为doInBackground方法返回的值.
         * @param bookList
         */
        @Override
        protected void onPostExecute(List<BookInfo> bookList) {
            super.onPostExecute(bookList);
            listView.setAdapter(new RankBookAdapter(getActivity(), R.layout.rank_book_info, bookList));
        }
    }
}

