package com.cdhxqh.bowei.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.manager.AppManager;
import com.cdhxqh.bowei.manager.HttpManager;
import com.cdhxqh.bowei.manager.HttpRequestHandler;
import com.cdhxqh.bowei.utils.AccountUtils;


/**
 * 登录界面
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";
    private EditText mUsername;
    private EditText mPassword;
    private Button mLogin;
    private ProgressDialog mProgressDialog;
    //    private MemberModel mProfile;
    private CheckBox checkBox; //记住密码

    private boolean isRemember; //是否记住密码


    String userName; //用户名

    String userPassWorld; //密码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
//        UmengUpdateAgent.setDefault();
//        UmengUpdateAgent.update(this);
        findViewById();
        initView();
        setEvent();
        mLogin.setOnClickListener(this);
    }

    @Override
    protected void findViewById() {
        mUsername = (EditText) findViewById(R.id.user_login_id);
        mPassword = (EditText) findViewById(R.id.user_login_password);
        checkBox = (CheckBox) findViewById(R.id.isremenber_password);
        mLogin = (Button) findViewById(R.id.user_login);
    }

    @Override
    protected void initView() {
        mUsername.setText("maxadmin");
        mPassword.setText("maxadmin");
        boolean isChecked = AccountUtils.getIsChecked(LoginActivity.this);
        if (isChecked) {
            mUsername.setText(AccountUtils.getUserName(LoginActivity.this));
            mPassword.setText(AccountUtils.getUserPassword(LoginActivity.this));
        }
    }



    /**
     * 设置事件监听*
     */
    private void setEvent() {
        checkBox.setOnCheckedChangeListener(cheBoxOnCheckedChangListener);
    }


    private CompoundButton.OnCheckedChangeListener cheBoxOnCheckedChangListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            isRemember = isChecked;
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_login:
                if (mUsername.getText().length() == 0) {
                    mUsername.setError(getString(R.string.login_error_empty_user));
                    mUsername.requestFocus();
                } else if (mPassword.getText().length() == 0) {
                    mPassword.setError(getString(R.string.login_error_empty_passwd));
                    mPassword.requestFocus();
                } else {
                    login();
                }
                break;

        }
    }

    /**
     * 登陆*
     */
    private void login() {
        mProgressDialog = ProgressDialog.show(LoginActivity.this, null,
                getString(R.string.login_loging), true, true);

        HttpManager.login(LoginActivity.this,
                mUsername.getText().toString(),
                mPassword.getText().toString(),
                new HttpRequestHandler<String>() {
                    @Override
                    public void onSuccess(String data) {
                        Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                        mProgressDialog.dismiss();
                        if (isRemember) {
                            AccountUtils.setChecked(LoginActivity.this, isRemember);
                            //记住密码
                            AccountUtils.setUserNameAndPassWord(LoginActivity.this, mUsername.getText().toString(), mPassword.getText().toString());
                        }

                        startIntent();

                    }


                    @Override
                    public void onSuccess(String data, int totalPages, int currentPage) {
                        Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();

                        startIntent();
                    }

                    @Override
                    public void onFailure(String error) {
                        Toast.makeText(LoginActivity.this,error,Toast.LENGTH_SHORT).show();
                        mProgressDialog.dismiss();
                    }
                });
    }


    private void startIntent() {
        Intent inetnt = new Intent();
        inetnt.setClass(this, MainHomeActivity.class);
        startActivity(inetnt);
    }


    private long exitTime = 0;

    @Override
    public void onBackPressed() {


        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_LONG).show();
            exitTime = System.currentTimeMillis();
        } else {
            AppManager.AppExit(LoginActivity.this);
        }
    }

}
