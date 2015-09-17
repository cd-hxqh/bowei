package com.cdhxqh.bowei.ui.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cdhxqh.bowei.Dao.OrderMainDao;
import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.bean.OrderMain;
import com.cdhxqh.bowei.config.Constants;
import com.cdhxqh.bowei.manager.HttpManager;
import com.cdhxqh.bowei.manager.HttpRequestHandler;
import com.cdhxqh.bowei.ui.adapter.OrderMaintenanceAdapter;
import com.cdhxqh.bowei.utils.JsonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by think on 2015/8/13.
 */
public class OrderListActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    public static final String TAG = "OrderListActivity";

    public String name;
    private ImageView backimg;
    private ImageView addimg;
    private TextView titlename;
    private Button chooseitembtn;
    private SwipeRefreshLayout refresh_layout = null;
    LinearLayoutManager layoutManager;
    public RecyclerView recyclerView;
    private OrderMaintenanceAdapter orderMainAdapter;
    private ArrayList<OrderMain> orderMainArrayList;
    private ProgressDialog mProgressDialog;
    private LinearLayout nodatalayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maintenance_activity);
        getInitData();
        findViewById();
        initView();
    }


    /**获取上个界面传递的数据**/
    private void getInitData() {
        name = (String) getIntent().getExtras().get("ordername");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        orderMainAdapter.notifyDataSetChanged();
        refreshData();
    }

    @Override
    protected void findViewById() {
        backimg = (ImageView) findViewById(R.id.maintenance_title_back);
        addimg = (ImageView) findViewById(R.id.maintenance_title_add);
        titlename = (TextView) findViewById(R.id.title_name);
        chooseitembtn = (Button) findViewById(R.id.activity_chooser_view_content);
        recyclerView = (RecyclerView) findViewById(R.id.maintenance_list);
        refresh_layout = (SwipeRefreshLayout) this.findViewById(R.id.swipe_container);
        nodatalayout = (LinearLayout) findViewById(R.id.have_not_data_id);
    }

    @Override
    protected void initView() {

        titlename.setText(name);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        orderMainAdapter = new OrderMaintenanceAdapter(this,this);
        recyclerView.setAdapter(orderMainAdapter);
        addimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                if(name.equals(getResources().getString(R.string.maintenance))){
                    intent.setClass(OrderListActivity.this, AddOrderMaintenanceActivity.class);
                }else if(name.equals(getResources().getString(R.string.serve))){
                    intent.setClass(OrderListActivity.this, AddOrderServeActivity.class);
                }else if(name.equals(getResources().getString(R.string.service))){
                    intent.setClass(OrderListActivity.this, AddOrderServiceActivity.class);
                }
                startActivityForResult(intent, 1);
            }
        });
        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        refresh_layout.setOnRefreshListener(this);
        getData();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        OrderMain orderMain;
        switch (resultCode) {
            case 0:
                break;
            case 1:
                orderMain = (OrderMain) data.getSerializableExtra("orderMain");
                additem(orderMain);
                break;
        }
    }

    private void getData(){
        HttpManager.getData(this, Constants.ORDER_GETDATA, new HttpRequestHandler<String>() {
            @Override
            public void onSuccess(String data) {
                refresh_layout.setRefreshing(false);
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(data);
                    if(jsonObject.getString("errmsg").equals(getResources().getString(R.string.request_ok))){
                        Toast.makeText(OrderListActivity.this,getResources().getString(R.string.request_ok),Toast.LENGTH_SHORT).show();
                        JsonUtils.parsingOrderArr(jsonObject.getString("result"), OrderListActivity.this);
                        refreshData();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onSuccess(String data, int totalPages, int currentPage) {
            }

            @Override
            public void onFailure(String error) {
                Toast.makeText(OrderListActivity.this,getResources().getString(R.string.request_fail),Toast.LENGTH_SHORT).show();
                refresh_layout.setRefreshing(false);
                refreshData();
            }
        });
    }
    private void refreshData(){
        orderMainAdapter = new OrderMaintenanceAdapter(this,this);
        recyclerView.setAdapter(orderMainAdapter);
        addData();
    }
    private void addData() {
        List<OrderMain> list;
        if(name.equals(getResources().getString(R.string.maintenance))){ //维保
            list = new OrderMainDao(this).queryForPMAndCM();
        }else if(name.equals(getResources().getString(R.string.serve))){ //维修
            list = new OrderMainDao(this).queryForEM();
        }else if(name.equals(getResources().getString(R.string.service))){ //服务
            list = new OrderMainDao(this).queryForSVR();
        }else {
            list = new OrderMainDao(this).queryForAll();
        }
        orderMainArrayList = new ArrayList<OrderMain>();
        if(list.size()==0){
            Toast.makeText(OrderListActivity.this,getResources().getString(R.string.order_null),Toast.LENGTH_SHORT).show();
            nodatalayout.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }else {
            if(nodatalayout.getVisibility()==View.VISIBLE){
                nodatalayout.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
            for (int i = 0; i < list.size(); i++) {
                orderMainArrayList.add(i, list.get(i));
            }
            orderMainAdapter.update(orderMainArrayList, true);
        }
    }

    private void additem(OrderMain orderMain){
        new OrderMainDao(this).update(orderMain);
        ArrayList<OrderMain> list = new ArrayList<OrderMain>();
        list.add(0,orderMain);
        orderMainAdapter.update(list, true);
    }
//下拉刷新触发事件
    @Override
    public void onRefresh() {
//        if(orderMainAdapter.getItemCount()>0) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(OrderListActivity.this);
//            builder.setMessage("刷新将清除本地数据，确定吗？").setTitle("提示")
//                    .setNegativeButton("确定", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            dialogInterface.dismiss();
//                            getData();
//                        }
//                    }).setPositiveButton("取消", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    refresh_layout.setRefreshing(false);
//                    dialogInterface.dismiss();
//                }
//            }).create().show();
//        }else {
            getData();
//        }
    }

//    public void changeitem(){
//        for(int i = 0;i<orderMainAdapter.getItemCount();i++){
//            layoutManager.findViewByPosition(i).findViewById(R.id.order_main_in).setVisibility(View.GONE);
//            layoutManager.findViewByPosition(i).findViewById(R.id.order_checkbox).setVisibility(View.VISIBLE);
//        }
//    }

}
