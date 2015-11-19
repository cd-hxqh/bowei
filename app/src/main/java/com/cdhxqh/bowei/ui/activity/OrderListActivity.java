package com.cdhxqh.bowei.ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
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
import com.cdhxqh.bowei.ui.widget.SwipeRefreshLayout;
import com.cdhxqh.bowei.utils.JsonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by think on 2015/8/13.
 */
public class OrderListActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,SwipeRefreshLayout.OnLoadListener {

    public static final String TAG = "OrderListActivity";

    public String name;
    private ImageView backimg;
    private ImageView addimg;
    private TextView titlename;
    private EditText searchedit;
    private String searchText = "";
    private SwipeRefreshLayout refresh_layout = null;
    LinearLayoutManager layoutManager;
    public RecyclerView recyclerView;
    private OrderMaintenanceAdapter orderMainAdapter;
    private ArrayList<OrderMain> orderMainArrayList;
    private ProgressDialog mProgressDialog;
    private LinearLayout nodatalayout;
    int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maintenance_activity);
        getInitData();
        findViewById();
        initView();
    }


    /**
     * 获取上个界面传递的数据*
     */
    private void getInitData() {
        name = (String) getIntent().getExtras().get("ordername");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        initView();
        refreshData();
        getOwnerId();
    }

    @Override
    protected void findViewById() {
        backimg = (ImageView) findViewById(R.id.maintenance_title_back);
        addimg = (ImageView) findViewById(R.id.maintenance_title_add);
        titlename = (TextView) findViewById(R.id.title_name);
        searchedit = (EditText) findViewById(R.id.search_edit_text_id);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_id);
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
        orderMainAdapter = new OrderMaintenanceAdapter(this);
        recyclerView.setAdapter(orderMainAdapter);
        addimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("jump_mark", Constants.ORDER_MARK);
                if (name.equals(getResources().getString(R.string.maintenance))) {
                    intent.setClass(OrderListActivity.this, AddOrderMaintenanceActivity.class);
                } else if (name.equals(getResources().getString(R.string.serve))) {
                    intent.setClass(OrderListActivity.this, AddOrderServeActivity.class);
                } else if (name.equals(getResources().getString(R.string.service))) {
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
        refresh_layout.setOnLoadListener(this);
        searchedit.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    // 先隐藏键盘
                    ((InputMethodManager) searchedit.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(
                                    OrderListActivity.this.getCurrentFocus()
                                            .getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    searchText = searchedit.getText().toString();
                    refreshData();
                    return true;
                }
                return false;
            }
        });
        getData();
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
                Toast.makeText(OrderListActivity.this, "获取最新工单失败", Toast.LENGTH_SHORT).show();
//                startIntent();
                mProgressDialog.dismiss();
            }
        });
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

    private void getData() {
        if (Constants.ORDER_GETDATA.equals("")) {
            refreshData();
        } else {
            String url = null;
            GregorianCalendar gc=new GregorianCalendar();
            SimpleDateFormat sf  =new SimpleDateFormat("yyyy-MM-dd");
            gc.setTime(new Date());
            gc.add(Calendar.MONTH, -3);
            gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), gc.get(Calendar.DATE));
            String reportdate= sf.format(gc.getTime());
            if (name.equals(getResources().getString(R.string.maintenance))) {
                url = Constants.getOrderUrl(i,"PM,CM",reportdate);
            } else if (name.equals(getResources().getString(R.string.serve))) {
                url = Constants.getOrderUrl(i,"EM",reportdate);
            } else if (name.equals(getResources().getString(R.string.service))) {
                url = Constants.getOrderUrl(i,"SVR",reportdate);
            }
//            mProgressDialog = ProgressDialog.show(this, null,
//                    getString(R.string.requesting), true, true);
            HttpManager.getData(this, url, new HttpRequestHandler<String>() {
                @Override
                public void onSuccess(String data) {
                    if (refresh_layout.isRefreshing()) {
                        refresh_layout.setRefreshing(false);
                    }
                    if (refresh_layout.isLoading()) {
                        refresh_layout.setLoading(false);
                    }
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(data);
                        if (jsonObject.getString("errmsg").equals(getResources().getString(R.string.request_ok))) {
                            JsonUtils.parsingOrderArr(jsonObject.getString("result"), OrderListActivity.this, getBaseApplication().getUsername());
                            refreshData();
                        }
//                        mProgressDialog.dismiss();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onSuccess(String data, int totalPages, int currentPage) {
                }

                @Override
                public void onFailure(String error) {
                    Toast.makeText(OrderListActivity.this, getResources().getString(R.string.request_fail), Toast.LENGTH_SHORT).show();
                    if (refresh_layout.isRefreshing()) {
                        refresh_layout.setRefreshing(false);
                    }
                    if (refresh_layout.isLoading()) {
                        refresh_layout.setLoading(false);
                    }
                    refreshData();
//                    mProgressDialog.dismiss();
                }
            });
//            mProgressDialog.dismiss();
        }
    }

    private void refreshData() {
        orderMainAdapter = new OrderMaintenanceAdapter(this);
        recyclerView.setAdapter(orderMainAdapter);
        addData();
    }

    private void addData() {
        List<OrderMain> list = null;
        if (name.equals(getResources().getString(R.string.maintenance))) { //维保
            list = new OrderMainDao(this).queryForPMAndCM(getBaseApplication().getUsername(),searchText);
        } else if (name.equals(getResources().getString(R.string.serve))) { //维修
            list = new OrderMainDao(this).queryForEM(getBaseApplication().getUsername(),searchText);
        } else if (name.equals(getResources().getString(R.string.service))) { //服务
            list = new OrderMainDao(this).queryForSVR(getBaseApplication().getUsername(),searchText);
        } else {
            list = new OrderMainDao(this).queryForAll();
        }
        orderMainArrayList = new ArrayList<OrderMain>();
        if (list == null || list.isEmpty()) {
            nodatalayout.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            if (nodatalayout.getVisibility() == View.VISIBLE) {
                nodatalayout.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
            for (int i = 0; i < list.size(); i++) {
                orderMainArrayList.add(i, list.get(i));
            }
            orderMainAdapter.update(orderMainArrayList, true);
        }
    }

    private void additem(OrderMain orderMain) {
//        new OrderMainDao(this).update(orderMain);
        ArrayList<OrderMain> list = new ArrayList<OrderMain>();
        list.add(0, orderMain);
        orderMainAdapter.update(list, true);
    }

    //下拉刷新触发事件
    @Override
    public void onRefresh() {
        if(nodatalayout.getVisibility()==View.VISIBLE){
            nodatalayout.setVisibility(View.GONE);
        }
        getOwnerId();
        getData();
    }

    @Override
    public void onLoad(){
        i++;
        getOwnerId();
        getData();
    }
}
