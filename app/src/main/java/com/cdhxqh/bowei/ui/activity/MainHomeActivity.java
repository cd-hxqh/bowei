package com.cdhxqh.bowei.ui.activity;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.config.Constants;
import com.cdhxqh.bowei.manager.AppManager;
import com.cdhxqh.bowei.manager.HttpManager;
import com.cdhxqh.bowei.manager.HttpRequestHandler;
import com.cdhxqh.bowei.ui.adapter.MenuItemAdapter;
import com.cdhxqh.bowei.ui.fragment.AssetFragment;
import com.cdhxqh.bowei.ui.fragment.DownloadFragment;
import com.cdhxqh.bowei.ui.fragment.InventoryFragment;
import com.cdhxqh.bowei.ui.fragment.KnowKedge_Fragment;
import com.cdhxqh.bowei.ui.fragment.OrderFragment;
import com.cdhxqh.bowei.ui.fragment.SettingFragment;
import com.cdhxqh.bowei.utils.JsonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by think on 2015/8/11.
 */
public class MainHomeActivity extends BaseActivity {
    public static final String TAG = "MainHomeActivity";

    /**
     * 搜索按钮*
     */
    private ImageView searchImageView;
    private ListView mLvLeftMenu;
    private DrawerLayout mDrawerLayout;
    private ImageView maintitleimg;
    private TextView titlename;
    private FragmentTransaction fragmentTransaction;
    private OrderFragment orderFragment = new OrderFragment(); //工单
    /**
     * 库存*
     */
    private InventoryFragment inventoryFragment = new InventoryFragment();
    /**
     * 知识库*
     */
    private KnowKedge_Fragment knowKedge_Fragment = new KnowKedge_Fragment();
    private DownloadFragment downloadFragment = new DownloadFragment();
    /**
     * 资产扫描*
     */
    private AssetFragment assetFragment = new AssetFragment();

    /**
     * 设置界面*
     */
    private SettingFragment settingFragment = new SettingFragment();


    private ProgressDialog mProgressDialog;
    TextView nameview;
    TextView dateview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawlayout_mainhome_activity);

        findViewById();
        initView();
        getOwnerId();
    }


    @Override
    protected void findViewById() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawer_layout);
        mLvLeftMenu = (ListView) findViewById(R.id.left_menu);
        maintitleimg = (ImageView) findViewById(R.id.main_title);
        titlename = (TextView) findViewById(R.id.title_activity_name);
        searchImageView = (ImageView) findViewById(R.id.title_search_btn);
    }

    @Override
    protected void initView() {
        setUpDrawer();
        invalidateOptionsMenu();
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.replace(R.id.container, orderFragment);
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

    private void setUpDrawer() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.header_username, mLvLeftMenu, false);
        nameview = (TextView) view.findViewById(R.id.id_link);
        nameview.setText(Constants.UserName + ",你好");
        dateview = (TextView) view.findViewById(R.id.id_date);
        dateview.setText(new SimpleDateFormat("yyyy.MM.dd").format(new Date()));
        mLvLeftMenu.addHeaderView(view);
        mLvLeftMenu.setAdapter(new MenuItemAdapter(this));
    }

    private ListView.OnItemClickListener leftmenulistener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mDrawerLayout.closeDrawer(Gravity.LEFT);
            searchImageView.setVisibility(View.GONE);
            switch (position) {
                case 1://工单管理
                    titlename.setText(getResources().getString(R.string.order));
                    fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    fragmentTransaction.replace(R.id.container, orderFragment);
                    fragmentTransaction.commit();
                    break;
                case 2://资产查询
                    fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    fragmentTransaction.replace(R.id.container, assetFragment);
                    fragmentTransaction.commit();
                    titlename.setText(getResources().getString(R.string.property));
                    break;
                case 3://库存查询
                    fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    fragmentTransaction.replace(R.id.container, inventoryFragment);
                    fragmentTransaction.commit();
                    titlename.setText(getResources().getString(R.string.inventory));
                    break;
                case 4://知识库
                    searchImageView.setVisibility(View.VISIBLE);
                    searchImageView.setOnClickListener(searchOnClickListener);
                    fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    fragmentTransaction.replace(R.id.container, knowKedge_Fragment);
                    fragmentTransaction.commit();
                    titlename.setText(getResources().getString(R.string.knowledge));
                    break;
                case 5://下载
                    fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    fragmentTransaction.replace(R.id.container, downloadFragment);
                    fragmentTransaction.commit();
                    titlename.setText(getResources().getString(R.string.download));
                    break;
                case 6://设置
                    fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    fragmentTransaction.replace(R.id.container, settingFragment);
                    fragmentTransaction.commit();
                    titlename.setText(getResources().getString(R.string.setting));
                    break;
                case 7://退出
                    showDialog();

                    break;
            }
        }
    };


    /**
     * 弹出对话框*
     */

    private void showDialog() {

        if (!mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            mDrawerLayout.openDrawer(Gravity.LEFT);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(MainHomeActivity.this);

        builder.setTitle("提示");
        builder.setMessage("确定退出程序");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                AppManager.AppExit(MainHomeActivity.this);
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {

            }
        });


        AlertDialog dialog = builder.create();
        dialog.show();


    }


    private void getOwnerId() {
        mProgressDialog = ProgressDialog.show(this, null,
                getString(R.string.requesting), true, true);
        HttpManager.getData(this, Constants.getOwnerId(getBaseApplication().getUsername()), new HttpRequestHandler<String>() {
            @Override
            public void onSuccess(String data) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(data);
                    if (jsonObject.getString("errmsg").equals(getResources().getString(R.string.request_ok))) {
                        JsonUtils.parsingWenerId(jsonObject.getString("result"));
                        mProgressDialog.dismiss();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onSuccess(String data, int totalPages, int currentPage) {
                mProgressDialog.dismiss();
            }

            @Override
            public void onFailure(String error) {
                mProgressDialog.dismiss();
            }
        });
    }

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


    /****/
    private View.OnClickListener searchOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(MainHomeActivity.this, SearchActivity.class);
            startActivityForResult(intent, 0);
        }
    };


}
