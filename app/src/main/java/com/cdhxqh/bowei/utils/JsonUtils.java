package com.cdhxqh.bowei.utils;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 解析类
 */
public class JsonUtils {

    /**解析登录信息**/
    public static String parsingAuthStr(final Context cxt, String data) {
        String isSuccess=null;
        try {
            JSONObject json = new JSONObject(data);
            String jsonString = json.getString("errcode");
            return jsonString;


        } catch (JSONException e) {
            e.printStackTrace();
            return isSuccess;
        }
    }

}
