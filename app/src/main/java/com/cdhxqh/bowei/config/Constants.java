package com.cdhxqh.bowei.config;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by think on 2015/8/11.
 */
public class Constants {
    public static String URL = "";
    public static final String USER_INFO = "userinfo";//������˺�key
    public static final String NAME_KEY = "name_key";//�˺��û�key
    public static final String PASS_KEY = "pass_key";//�˺�����key
    public static final String ISREMENBER = "isRemenber";//�˺��Ƿ��ס����״̬key
    public static String getWsUrl(Context context){
        return URL;
    }
}
