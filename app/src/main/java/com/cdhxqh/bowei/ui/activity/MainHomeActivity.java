package com.cdhxqh.bowei.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.ui.adapter.MenuItemAdapter;

/**
 * Created by think on 2015/8/11.
 */
public class MainHomeActivity extends BaseActivity {
    public static final String TAG = "MainHomeActivity";

    private Button maintenance;
    private Button serve;
    private Button service;
    private ListView mLvLeftMenu;
    private DrawerLayout mDrawerLayout;
    private ImageView maintitleimg;

    private Intent intent;//页面跳转
    private Intent leftmenuintent;//导航栏跳转

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawlayout_mainhome_activity);

        findViewById();
        initView();
    }


    @Override
    protected void findViewById() {
        maintenance = (Button) findViewById(R.id.maintenance);
        serve = (Button) findViewById(R.id.serve);
        service = (Button) findViewById(R.id.service);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawer_layout);
        mLvLeftMenu = (ListView) findViewById(R.id.left_menu);
        maintitleimg = (ImageView) findViewById(R.id.main_title);
    }

    @Override
    protected void initView() {
        setUpDrawer();
        maintenance.setOnClickListener(buttonclick);
        serve.setOnClickListener(buttonclick);
        service.setOnClickListener(buttonclick);
        mLvLeftMenu.setOnItemClickListener(leftmenulistener);
        maintitleimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mDrawerLayout.isDrawerOpen(Gravity.LEFT)){
                    mDrawerLayout.openDrawer(Gravity.LEFT);
                }
            }
        });
    }

    private void setUpDrawer()
    {
        LayoutInflater inflater = LayoutInflater.from(this);
        mLvLeftMenu.addHeaderView(inflater.inflate(R.layout.header_username, mLvLeftMenu, false));
        mLvLeftMenu.setAdapter(new MenuItemAdapter(this));
    }

    private View.OnClickListener buttonclick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            intent = new Intent();
            switch (v.getId()){
                case R.id.maintenance://跳转到维保工单页面
                    intent.setClass(MainHomeActivity.this,MaintenanceActivity.class);
                    startActivity(intent);
                    break;
                case R.id.serve://跳转到维修工单页面
                    break;
                case R.id.service://跳转到服务工单页面
                    break;
            }
        }
    };

    private ListView.OnItemClickListener leftmenulistener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mDrawerLayout.closeDrawer(Gravity.LEFT);
            leftmenuintent = new Intent();
            switch (position){
                case 1://跳转工单管理页面
                    break;
                case 2://跳转资产查询页面
                    leftmenuintent.setClass(MainHomeActivity.this,LoginActivity.class);//测试跳转
                    startActivity(leftmenuintent);
                    break;
                case 3://跳转库存查询页面
                    break;
                case 4://跳转知识库页面
                    break;
            }
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            //do something...
            exit(this);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
