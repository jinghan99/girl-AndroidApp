package com.example.jingh.myapplication.utils;

import java.io.UnsupportedEncodingException;

/**
 * @Package com.example.jingh.myapplication.utils
 * @Description: TODO
 * @author: jingh
 * @date 2019/1/9 16:43
 */
public class Utils {
    private final static String ENCODE = "GBK";
    /**
     * URL 转码
     *
     * @return String
     * @author lifq
     * @date 2015-3-17 下午04:10:28
     */
    public static String getURLEncoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLEncoder.encode(str, ENCODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

}
