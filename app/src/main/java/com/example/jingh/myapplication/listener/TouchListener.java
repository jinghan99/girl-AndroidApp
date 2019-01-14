package com.example.jingh.myapplication.listener;

import android.view.View;

/**
 * 用来实时回调触摸事件回调
 * 监听 view text view 中的滑动事件
 */
public interface TouchListener {

    /** 手指向左滑动，即查看下一章节 */
    final int MOVE_TO_LEFT = 0;
    /** 手指向右滑动，即查看上一章节 */
    final int MOVE_TO_RIGHT = 1;

    /**
     * 创建一个承载Text的View
     * direction : OVE_TO_LEFT,MOVE_TO_RIGHT}
     * @param direction
     * @return
     */
    public View createView(final int direction);

    /***
     * 当前页是否是第一页
     *
     * @return
     */
    public boolean currentIsFirstPage();

    /***
     * 当前页是否是最后一页
     *
     * @return
     */
    public boolean currentIsLastPage();

    /**
     * 当前页是否有上一页（用来判断可滑动性）
     *
     * @return
     */
    public boolean whetherHasPreviousPage();

    /***
     * 当前页是否有下一页（用来判断可滑动性）
     *
     * @return
     */
    public boolean whetherHasNextPage();
}
