package com.cdhxqh.bowei.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.config.Constants;
import com.cdhxqh.bowei.ui.widget.CustomProgressDialog;


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
                    progressDialog.dismiss();
                    Intent intent = new Intent();
                    intent.setClass(LoginActivity.this, MainHomeActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case F:
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
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
        mHandler.sendEmptyMessage(S);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
//            return true;
//        }
        return super.onKeyDown(keyCode, event);
    }
}
