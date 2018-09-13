package com.example.jingh.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Author:${XWQ}
 * Time   2018/4/20
 * Descrition: this is AppPreference
 */

public class AppPreference extends BasePreferences {
    private static final String FILE_APP_PREFERENCES = "app_preferences";
    public static final  String USER_NAME            = "username";
    public static final  String PASS_WORD            = "password";
    public static final  String USER_ID              = "userid";
    private static AppPreference mAppPreference;

    public AppPreference(SharedPreferences sharedPreferences) {
        super(sharedPreferences);
    }

    public static AppPreference getAppPreference() {
        return mAppPreference;
    }

    public static AppPreference init(Context context) {
        if (mAppPreference == null) {
            mAppPreference = new AppPreference(context.getSharedPreferences(FILE_APP_PREFERENCES, Context.MODE_PRIVATE));
        }
        return mAppPreference;
    }
}
