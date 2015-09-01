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
import com.cdhxqh.bowei.config.Constants;
import com.cdhxqh.bowei.ui.CustomProgressDialog;
import com.cdhxqh.bowei.utils.NetUtils;

import org.apache.http.message.BasicNameValuePair;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
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
                    Intent intent = new Intent();
                    intent.setClass(LoginActivity.this, MainHomeActivity.class);
                    startActivity(intent);
                    finish();
//                    if(result==null){
//                        Toast.makeText(LoginActivity.this,"null",Toast.LENGTH_SHORT).show();
//                    }else {
//                        Toast.makeText(LoginActivity.this, result, Toast.LENGTH_SHORT).show();
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
        progressDialog.show();
//        Thread thread = new Thread(){
//            public void run(){
//                result = getBaseApplication().getWsService().InsertWO(json);
////                SoapObject soapObject = new SoapObject("http://www.ibm.com/maximo/wsdl/CUWO",
////                        "InsertWO");
////                SoapSerializationEnvelope envelop = new SoapSerializationEnvelope(
////                        SoapEnvelope.VER12);
////                envelop.dotNet = true;
////                envelop.setOutputSoapObject(soapObject);
////                HttpTransportSE httpSE = new HttpTransportSE("http://182.92.8.94:7001/meaweb/wsdl/cuwo.wsdl");
////                try {
////                    httpSE.call("urn:action", envelop);
////                } catch (IOException e) {
////                    e.printStackTrace();
////                } catch (XmlPullParserException e) {
////                    e.printStackTrace();
////                }
//////                    SoapObject resultObj = null;
//////                    try {
//////                        resultObj = (SoapObject) envelop.getResponse();
//////                    } catch (SoapFault soapFault) {
//////                        soapFault.printStackTrace();
//////                    }
////                if (envelop.bodyIn!=null) {
////                    result = envelop.bodyIn.toString();
////                }else {
////                    result = "ss";
////                }
//////            result = ((BaseApplication)LoginActivity.this.getApplication()).getWsService().InsertWO(json);
                    mHandler.sendEmptyMessage(S);
//////                }
//                }
//        };
//        thread.start();

//        new AsyncTask<String, String, String>() {
//            @Override
//            protected String doInBackground(String... s) {
////                List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
////                params.add(new BasicNameValuePair("loginid",username.getText().toString()));
////                params.add(new BasicNameValuePair("password",password.getText().toString()));
////                params.add(new BasicNameValuePair("imei",((TelephonyManager) getSystemService(TELEPHONY_SERVICE))
////                        .getDeviceId()));
////                return NetUtils.postRequest(Constants.loginURL,params);
////                result = getBaseApplication().getWsService().InsertWO(json);
//                if (getBaseApplication().getWsService().InsertWO(json)!=null){
//
//                    mHandler.sendEmptyMessage(S);
//                }else {
//                    mHandler.sendEmptyMessage(F);
//                }
//
//                return "s";
//            }
////
////            @Override
////            protected void onPostExecute(String s) {
////                super.onPostExecute(s);
////                if(s != null){
////                    result = s;
////                    mHandler.sendEmptyMessage(S);
////                }else {
////                    result = s;
////                    mHandler.sendEmptyMessage(F);
////                }
////                progressDialog.dismiss();
////            }
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
