package com.cdhxqh.bowei.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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
    }

    @Override
    protected void initView() {
        ActionBar actionBar = getSupportActionBar();
        ActionBar.LayoutParams lp =new ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View titleView = inflater.inflate(R.layout.title_layout, null);
        actionBar.setCustomView(titleView, lp);

        actionBar.setDisplayShowHomeEnabled(false);//去掉导航
        actionBar.setDisplayShowTitleEnabled(false);//去掉标题
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        setUpDrawer();
        maintenance.setOnClickListener(buttonclick);
        serve.setOnClickListener(buttonclick);
        service.setOnClickListener(buttonclick);
        mLvLeftMenu.setOnItemClickListener(leftmenulistener);
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
            Toast.makeText(MainHomeActivity.this,""+v.getId(),Toast.LENGTH_SHORT).show();
            switch (v.getId()){
                case R.id.maintenance://跳转到维保工单页面
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
            Intent intent = new Intent();
            switch (position){
                case 1://跳转工单管理页面(刷新页面)
//                    onCreate(null);
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                    break;
                case 2://跳转资产查询页面
                    break;
                case 3://跳转库存查询页面
                    break;
                case 4://跳转知识库页面
                    break;
            }
        }
    };
}
