package com.cdhxqh.bowei.ui.activity;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.ui.adapter.MenuItemAdapter;
import com.cdhxqh.bowei.ui.fragment.InventoryFragment;
import com.cdhxqh.bowei.ui.fragment.OrderFragment;

/**
 * Created by think on 2015/8/11.
 */
public class MainHomeActivity extends BaseActivity {
    public static final String TAG = "MainHomeActivity";


    private ListView mLvLeftMenu;
    private DrawerLayout mDrawerLayout;
    private ImageView maintitleimg;
    private TextView titlename;
    private FragmentTransaction fragmentTransaction;
    private OrderFragment orderFragment = new OrderFragment(); //工单
    private InventoryFragment inventoryFragment=new InventoryFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawlayout_mainhome_activity);

        findViewById();
        initView();
    }


    @Override
    protected void findViewById() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawer_layout);
        mLvLeftMenu = (ListView) findViewById(R.id.left_menu);
        maintitleimg = (ImageView) findViewById(R.id.main_title);
        titlename = (TextView) findViewById(R.id.title_activity_name);
    }

    @Override
    protected void initView() {
        setUpDrawer();
        invalidateOptionsMenu();
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.replace(R.id.container,orderFragment);
        fragmentTransaction.commit();
        mLvLeftMenu.setOnItemClickListener(leftmenulistener);
        maintitleimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
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

    private ListView.OnItemClickListener leftmenulistener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mDrawerLayout.closeDrawer(Gravity.LEFT);
            switch (position){
                case 1://工单管理
                    titlename.setText(getResources().getString(R.string.order));
                    fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    fragmentTransaction.replace(R.id.container,orderFragment);
                    fragmentTransaction.commit();
                    break;
                case 2://资产查询
                    fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    fragmentTransaction.replace(R.id.container,orderFragment);
                    fragmentTransaction.commit();
                    titlename.setText(getResources().getString(R.string.property));
                    break;
                case 3://库存查询
                    fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    fragmentTransaction.replace(R.id.container,inventoryFragment);
                    fragmentTransaction.commit();
                    titlename.setText(getResources().getString(R.string.inventory));
                    break;
                case 4://知识库
                    titlename.setText(getResources().getString(R.string.knowledge));
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
