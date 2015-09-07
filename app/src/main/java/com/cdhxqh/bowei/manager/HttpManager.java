package com.cdhxqh.bowei.manager;

import android.content.Context;
import android.util.Log;

import com.cdhxqh.bowei.config.Constants;
import com.cdhxqh.bowei.utils.JsonUtils;
import com.cdhxqh.bowei.utils.SafeHandler;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONObject;

/**
 * Created by apple on 15/9/1.
 */
public class HttpManager {

    private static final String TAG="HttpManager11";
    /**
     * 登录方法*
     */
    public static void login(final Context cxt, final String username, final String password,final HttpRequestHandler<String> handler) {


        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("loginid", username);
        params.put("password", password);
        params.put("imei", "1");
        client.post(Constants.loginURL, params, new TextHttpResponseHandler() {


            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.i(TAG,"1statusCode="+statusCode+",responseString="+responseString);
                SafeHandler.onFailure(handler, "登录失败");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.i(TAG,"2statusCode="+statusCode+",responseString="+responseString);
                //解析登录信息
                String reult= JsonUtils.parsingAuthStr(cxt, responseString);
                Log.i(TAG,reult);
                if(reult.equals(Constants.LOGINSUCCESS)||reult.equals(Constants.CHANGEIMEI)){
                    SafeHandler.onSuccess(handler,reult);
                }else if (reult.equals(Constants.USERNAMEERROR)){
                    SafeHandler.onSuccess(handler,"用户名或密码错误");
                }

            }
        });
    }

    public static void getData(final Context cxt,String data,final HttpRequestHandler<String> handler){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("data",data);
//        client.addHeader("data",data);
        client.get(Constants.searchURL,params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.i(TAG, "1statusCode=" + statusCode + ",responseString=" + responseString);
                SafeHandler.onFailure(handler, "查询失败");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.i(TAG, "2statusCode=" + statusCode + ",responseString=" + responseString);
                SafeHandler.onSuccess(handler,responseString);
            }
        });
    }
}
