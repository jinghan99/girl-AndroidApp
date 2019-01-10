package com.example.jingh.myapplication.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;
import com.example.jingh.myapplication.fragment.ChapterTextFragment;

import java.util.List;

/**
 * @Package 分页适配器
 * @Description: TODO
 * @author: jingh
 * @date 2019/1/10 11:24
 * （1）在instantiateItem方法中，创建Fragment，读取相应Fragment的状态，对其进行初始化，然后add 到FragmentManager中。
 * （2）在destoryItem方法中，销毁Fragment，保存Fragment的状态，通过remove移除Fragment。
 */
public class ChapterTextFragmentStatePagerAdapter extends FragmentStatePagerAdapter {
    private List<ChapterTextFragment> fragments;
    private ChapterTextFragment       mCurrentFragment;

    public ChapterTextFragmentStatePagerAdapter(FragmentManager fm, List<ChapterTextFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    public void addFragment(ChapterTextFragment fragment) {
        if (fragments != null) {
            fragments.add(fragment);
            notifyDataSetChanged();
        }
    }

    @Override
    public Fragment getItem(int i) {
        if (fragments != null) {
            return fragments.get(i);
        }
        return null;
    }

    @Override
    public int getCount() {
        if (fragments != null) {
            return fragments.size();
        }
        return 0;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        mCurrentFragment = (ChapterTextFragment) object;
        super.setPrimaryItem(container, position, object);
    }

    //获取当前的Fragment
    public Fragment getCurrentFragment() {
        return mCurrentFragment;
    }

}
