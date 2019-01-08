package com.example.jingh.myapplication;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.widget.Toast;
import com.example.jingh.myapplication.helper.ActivityLifeHelper;

/**
 * @Package com.example.jingh.myapplication
 * @Description: Application
 * @author: jingh
 * @date 2018/9/13 15:52
 */
public class AndroidApplication extends Application {
    private static AndroidApplication mInstance;
    private        Context            mContext;
    AppPreference appPreference;

    /**
     * 屏幕宽度
     */
    public static int   screenWidth;
    /**
     * 屏幕高度
     */
    public static int   screenHeight;
    /**
     * 屏幕密度
     */
    public static float screenDensity;

    @Override
    public void onCreate() {
        super.onCreate();
        //activityManager = ActivityManager.getInstance(); // 获得实例
        mInstance = this;
        mContext = getApplicationContext();
        appPreference = AppPreference.init(this);
        /**
         * 初始化
         */
        initScreenSize();
        registerActivityLifecycleCallbacks(ActivityLifeHelper.instance());
    }

    /**
     * 初始化当前设备屏幕宽高
     */
    private void initScreenSize() {
        DisplayMetrics curMetrics = getApplicationContext().getResources().getDisplayMetrics();
        screenWidth = curMetrics.widthPixels;
        screenHeight = curMetrics.heightPixels;
        screenDensity = curMetrics.density;
    }

    public AppPreference getAppPreference_() {
        if (appPreference == null) {
            appPreference = AppPreference.init(this);
        }
        return appPreference;
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    public static AndroidApplication getInstance() {
        return mInstance;
    }



}
