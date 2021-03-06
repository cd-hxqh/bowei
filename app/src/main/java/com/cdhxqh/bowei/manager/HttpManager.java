package com.cdhxqh.bowei.manager;

import android.content.Context;
import android.util.Log;

import com.cdhxqh.bowei.bean.Results;
import com.cdhxqh.bowei.config.Constants;
import com.cdhxqh.bowei.utils.JsonUtils;
import com.cdhxqh.bowei.utils.SafeHandler;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by apple on 15/9/1.
 */
public class HttpManager {

    private static final String TAG = "HttpManager11";

    /**
     * 登录方法*
     */
    public static void login(final Context cxt, final String username, final String password,String imei, final HttpRequestHandler<String> handler) {


        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("loginid", username);
        params.put("password", password);
        params.put("imei", imei);
        client.post(Constants.loginURL, params, new TextHttpResponseHandler() {


            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                SafeHandler.onFailure(handler, "登录失败");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                //解析登录信息
                String reult = JsonUtils.parsingAuthStr(cxt, responseString);
                if (reult.equals(Constants.LOGINSUCCESS) || reult.equals(Constants.CHANGEIMEI)) {
                    try {
                        SafeHandler.onSuccess(handler, new JSONObject(responseString).getJSONObject("result").getString("personId"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else if (reult.equals(Constants.USERNAMEERROR)) {
                    SafeHandler.onSuccess(handler, "用户名或密码错误");
                }

            }
        });
    }


    /**
     * 获取信息方法*
     */
    public static void getData(final Context cxt, String data, final HttpRequestHandler<String> handler) {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("data", data);
        client.setTimeout(600000);
        client.get(Constants.SEARCHURL, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                SafeHandler.onFailure(handler, "查询失败");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                SafeHandler.onSuccess(handler, responseString);
            }
        });
    }


    /**
     * 解析返回的结果*
     */
    public static void getDataInfo(final Context cxt, String data, final HttpRequestHandler<String> handler) {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("data", data);
        client.get(Constants.SEARCHURL, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                SafeHandler.onFailure(handler, "查询失败");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                String result = JsonUtils.parsing(cxt, responseString);

                SafeHandler.onSuccess(handler, result);
            }
        });
    }

    /**
     * 解析返回的结果--分页*
     */
    public static void getDataPagingInfo(final Context cxt, String data, final HttpRequestHandler<Results> handler) {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("data", data);
        client.get(Constants.SEARCHURL, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                SafeHandler.onFailure(handler, "查询失败");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Results result = JsonUtils.parsingResults(cxt, responseString);

                SafeHandler.onSuccess(handler, result, result.getCurpage(), result.getShowcount());
            }
        });
    }


}
