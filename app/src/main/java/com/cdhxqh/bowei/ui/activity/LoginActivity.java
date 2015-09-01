package com.cdhxqh.bowei.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.application.BaseApplication;
import com.cdhxqh.bowei.bean.SoapObject;
import com.cdhxqh.bowei.config.Constants;
import com.cdhxqh.bowei.manager.HttpTransportSE;
import com.cdhxqh.bowei.ui.CustomProgressDialog;
import com.cdhxqh.bowei.utils.NetUtils;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LoginActivity extends BaseActivity {

    public static final String TAG = "LoginActivity";

    private EditText username;
    private EditText password;
    private CheckBox isremenber;
    private Button login;

    private CustomProgressDialog progressDialog = null;

    protected static final int S = 0;
    protected static final int F = 1;
    private String result;
    String json = "{woNum:'abc',description:'www'}";

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case S:
//                    Intent intent = new Intent();
//                    intent.setClass(LoginActivity.this, MainHomeActivity.class);
//                    startActivity(intent);
//                    finish();
//                    if(result==null){
//                        Toast.makeText(LoginActivity.this,"null",Toast.LENGTH_SHORT).show();
//                    }else {
                        Toast.makeText(LoginActivity.this, result, Toast.LENGTH_SHORT).show();
//                    }
                    break;
                case F:
//                    if(result!=null){
                        Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
//                    }

                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        findViewById();
        initView();
    }

    @Override
    protected void findViewById() {
        username = (EditText) findViewById(R.id.user_login_id);
        password = (EditText) findViewById(R.id.user_login_password);
        isremenber = (CheckBox) findViewById(R.id.isremenber_password);
        login = (Button) findViewById(R.id.user_login);
    }

    @Override
    protected void initView() {
        username.setText(myshared.getString(Constants.NAME_KEY, ""));
        password.setText(myshared.getString(Constants.PASS_KEY,""));
        isremenber.setChecked(myshared.getBoolean(Constants.ISREMENBER, false));
        login.setOnClickListener(loginonclick);
    }

    private View.OnClickListener loginonclick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SharedPreferences.Editor editor = myshared.edit();
            editor.putString(Constants.NAME_KEY, username.getText().toString());
            if(isremenber.isChecked()){
                editor.putString(Constants.PASS_KEY,password.getText().toString());
            }else{
                editor.putString(Constants.PASS_KEY, "");
            }
            editor.putBoolean(Constants.ISREMENBER,isremenber.isChecked());
            editor.commit();
            logon();
        }
    };

    private void logon(){
        if (progressDialog == null){
            progressDialog = CustomProgressDialog.createDialog(this);
            progressDialog.setMessage("登录中...");
        }
        Thread thread = new Thread(){
            public void run() {
                SoapSerializationEnvelope envelop = new SoapSerializationEnvelope(
                        SoapEnvelope.VER11);
                SoapObject soapObject = new SoapObject("http://www.ibm.com/maximo",
                        "InsertWO");
                soapObject.addProperty("json", json);
                envelop.dotNet = true;
                envelop.bodyOut = soapObject;
//                HttpTransportSE httpSE = new HttpTransportSE("http://182.92.8.94:7001/meaweb/services/CUWO");
//                HttpTransportSE httpSE = new HttpTransportSE("http://182.92.8.94:7001/meaweb/wsdl/cuwo");
                HttpTransportSE httpSE = new HttpTransportSE("http://182.92.8.94:7001/meaweb/schema/service/ss/CUWOService.xsd");

                try {
                    httpSE.call("urn:action", envelop);
                } catch (IOException | XmlPullParserException e) {
                    e.printStackTrace();
                }
                if (envelop.bodyIn!=null) {
                    result = envelop.bodyIn.toString();
                }else {
                    result = "返回为null";
                }
                    mHandler.sendEmptyMessage(S);
                }
//                String uriAPI = "http://182.92.8.94:7001/maximo/mobile/system/login";  //声明网址字符串
//                HttpPost httpRequest = new HttpPost(uriAPI);   //建立HTTP POST联机
//                List<NameValuePair> params = new ArrayList<NameValuePair>();   //Post运作传送变量必须用NameValuePair[]数组储存
//                params.add(new BasicNameValuePair("loginid", "maxadmin"));
//                params.add(new BasicNameValuePair("password", "maxadmin"));
//                params.add(new BasicNameValuePair("imei",((TelephonyManager) getSystemService(TELEPHONY_SERVICE))
//                        .getDeviceId()));
//                try {
//                    httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));   //发出http请求
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
//                HttpResponse httpResponse = null;   //取得http响应
//                try {
//                    httpResponse = new DefaultHttpClient().execute(httpRequest);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                if (httpResponse.getStatusLine().getStatusCode() == 200)
//                    try {
//                        result = EntityUtils.toString(httpResponse.getEntity());   //获取字符串
//                        mHandler.sendEmptyMessage(S);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//            }
        };
        thread.start();
//        progressDialog.show();
//        new AsyncTask<String, String, String>() {
//            @Override
//            protected String doInBackground(String... s) {
//                List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
//                params.add(new BasicNameValuePair("loginid",username.getText().toString()));
//                params.add(new BasicNameValuePair("password",password.getText().toString()));
//                params.add(new BasicNameValuePair("imei",((TelephonyManager) getSystemService(TELEPHONY_SERVICE))
//                        .getDeviceId()));
//                return NetUtils.postRequest(Constants.loginURL,params);
//                result = getBaseApplication().getWsService().InsertWO(json);
//                if (getBaseApplication().getWsService().InsertWO(json)!=null){
//
//                    mHandler.sendEmptyMessage(S);
//                }else {
//                    mHandler.sendEmptyMessage(F);
//                }
//                result = getBaseApplication().getWsService().InsertWO(json);
//                return "s";
//            }

//            @Override
//            protected void onPostExecute(String s) {
//                super.onPostExecute(s);
//                if(s != null){
//                    result = s;
//                    mHandler.sendEmptyMessage(S);
//                }else {
//                    result = s;
//                    mHandler.sendEmptyMessage(F);
//                }
//                progressDialog.dismiss();
//            }
//        }.execute();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
//            return true;
//        }
        return super.onKeyDown(keyCode, event);
    }
}
