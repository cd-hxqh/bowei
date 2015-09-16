package com.cdhxqh.bowei.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.bean.Asset;
import com.cdhxqh.bowei.ui.widget.NavigationHorizontalScrollView;
import com.cdhxqh.bowei.utils.JsonUtils;

/**
 * 查询结果界面*
 */
public class Results_Activity extends BaseActivity {
    private static final String TAG = "Results_Activity";
    /**
     * 返回按钮*
     */
    private ImageView backImageView;
    /**
     * 标题*
     */
    private TextView titleText;
    /**
     * 扫描结果*
     */
    String result;

    Asset asset;

    /**
     * 编号*
     */
    private TextView numText;
    /**
     * 名称*
     */
    private TextView nameText;
    /**
     * 位置*
     */
    private TextView locationText;


    /**
     * 查询对应工单*
     */
    private Button searchBtn;
    /**
     * 新建工单*
     */
    private Button addBtn;

    String[] itemList = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        getInit();
        findViewById();
        initView();
    }

    /**
     * 获取上个界面的数据*
     */
    private void getInit() {
        result = getIntent().getExtras().getString("result");

        asset = JsonUtils.parsingAssets(Results_Activity.this, result);
        Log.i(TAG, "result=" + result);
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.info_title_img_left);
        titleText = (TextView) findViewById(R.id.info_title_name);


        numText = (TextView) findViewById(R.id.asset_number_id);
        nameText = (TextView) findViewById(R.id.asset_name_id);
        locationText = (TextView) findViewById(R.id.asset_location_id);


        searchBtn = (Button) findViewById(R.id.search_order_id);
        addBtn = (Button) findViewById(R.id.add_order_id);
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleText.setText(getString(R.string.results_title_text));
        if (asset != null) {
            numText.setText(asset.getASSETNUM());
            nameText.setText(asset.getDESCRIPTION());
            locationText.setText(asset.getLOCATION());
        }else{
            Toast.makeText(Results_Activity.this,"未扫描到资产数据",Toast.LENGTH_SHORT).show();
            searchBtn.setVisibility(View.GONE);
            addBtn.setVisibility(View.GONE);
        }

        searchBtn.setOnClickListener(searchOnClickListener);
        addBtn.setOnClickListener(addOnClickListener);

    }

    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };


    /**
     * 查询*
     */
    private View.OnClickListener searchOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };


    /**
     * 新建*
     */

    private View.OnClickListener addOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showAlertDialog();
        }
    };


    /**
     * 自定弹出框*
     */
    private void showAlertDialog() {
        itemList = new String[]{"维保", "维修", "服务"};
        ListAdapter mAdapter = new ArrayAdapter(Results_Activity.this, R.layout.dialog_item, itemList);
        LayoutInflater inflater = LayoutInflater.from(Results_Activity.this);
        View view = inflater.inflate(R.layout.dialog_title, null);


        ListView listview = (ListView) view.findViewById(android.R.id.list);
        listview.setAdapter(mAdapter);
        listview.setOnItemClickListener(listener);

        AlertDialog.Builder builder = new AlertDialog.Builder(Results_Activity.this);
        AlertDialog mAlertDialog = builder.create();
        mAlertDialog.show();
        mAlertDialog.getWindow().setContentView(view);
//        mAlertDialog.getWindow().setLayout(150, 320);
    }


    private AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent=new Intent();
            switch (position) {
                case 0: //维保
                    intent.setClass(Results_Activity.this,AddOrderMaintenanceActivity.class);
                    startActivityForResult(intent,0);
                    break;
                case 1: //维修
                    intent.setClass(Results_Activity.this,AddOrderServeActivity.class);
                    startActivityForResult(intent,0);
                    break;
                case 2: //服务
                    intent.setClass(Results_Activity.this,AddOrderServiceActivity.class);
                    startActivityForResult(intent,0);
                    break;
            }
        }
    };

}
