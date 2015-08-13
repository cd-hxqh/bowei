package com.cdhxqh.bowei.config;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by think on 2015/8/11.
 */
public class Constants {
    public static String URL = "";
    public static final String USER_INFO = "userinfo";//±£´æµÄÕËºÅkey
    public static final String NAME_KEY = "name_key";//ÕËºÅÓÃ»§key
    public static final String PASS_KEY = "pass_key";//ÕËºÅÃÜÂëkey
    public static final String ISREMENBER = "isRemenber";//ÕËºÅÊÇ·ñ¼Ç×¡ÃÜÂë×´Ì¬key
    public static String getWsUrl(Context context){
        return URL;
    }
}
