package com.cdhxqh.bowei.config;

import android.content.Context;
public class Constants {
    public static String URL = "http://182.92.8.94:7001/meaweb/wsdl/cuwo.wsdl";
    public static final String USER_INFO = "userinfo";
    public static final String NAME_KEY = "name_key";
    public static final String PASS_KEY = "pass_key";
    public static final String ISREMENBER = "isRemenber";
    public static String getWsUrl(Context context){
        return URL;
    }
}
