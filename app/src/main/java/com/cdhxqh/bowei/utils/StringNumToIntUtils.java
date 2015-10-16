package com.cdhxqh.bowei.utils;

/**
 * Created by think on 2015/10/16.
 * 去掉String型数字中的逗号
 */
public class StringNumToIntUtils {
    public static String parsingnum(String num){
        if(num==null||num.equals("")){
            return "0";
        }else {
            return num.replaceAll(",","");
        }
    }
}
