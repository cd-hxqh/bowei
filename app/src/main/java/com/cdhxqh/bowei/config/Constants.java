package com.cdhxqh.bowei.config;

import android.content.Context;
public class Constants {
    public static String webserviceURL = "http://182.92.8.94:7001/meaweb/wsdl/cuwo.wsdl";//webservice接口地址
    public static String loginURL = "http://182.92.8.94:7001/maximo/mobile/system/login";//登录接口地址
    public static String searchURL = "http://182.92.8.94:7001/maximo/mobile/common/api";//通用查询接口地址
    public static final String USER_INFO = "userinfo";
    public static final String NAME_KEY = "name_key";
    public static final String PASS_KEY = "pass_key";
    public static final String ISREMENBER = "isRemenber";
    public static String getWsUrl(Context context){
        return webserviceURL;
    }
}
