package com.cdhxqh.bowei.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.bean.OrderMain;
import com.cdhxqh.bowei.bean.Results;
import com.cdhxqh.bowei.config.Constants;
import com.cdhxqh.bowei.manager.HttpManager;
import com.cdhxqh.bowei.manager.HttpRequestHandler;
import com.cdhxqh.bowei.ui.adapter.OrderMaintenanceAdapter;
import com.cdhxqh.bowei.utils.JsonUtils;

import java.util.ArrayList;
/**工单查询界面**/
public class OrderSearchActivity extends BaseActivity {
    private static final String TAG="OrderSearchActivity";
    /**返回按钮**/
    private ImageView backImageView;
    /**标题**/
    private TextView titleTextView;
    /**搜索按钮**/
    private ImageView seachImageView;


    private SwipeRefreshLayout mSwipeLayout = null;

    LinearLayoutManager layoutManager;

    public RecyclerView recyclerView;

    /**暂无数据**/
    private LinearLayout notdatalayout;

    /**工单适配器**/
    OrderMaintenanceAdapter orderMaintenanceAdapter;

    /**资产编号**/
    private String assetNum;
    /**资产名称**/
    private String description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_search);
        getInitData();
        findViewById();
        initView();
    }

    /**获取资产编号**/
    private void getInitData() {
        assetNum=getIntent().getExtras().getString("assetNum");
        description=getIntent().getExtras().getString("description");
        Log.i(TAG,"assetNum="+assetNum);
    }

    @Override
    protected void findViewById() {
        backImageView=(ImageView)findViewById(R.id.maintenance_title_back);

        titleTextView=(TextView)findViewById(R.id.title_name);

        seachImageView=(ImageView)findViewById(R.id.maintenance_title_add);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_id);

        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);

        notdatalayout=(LinearLayout)findViewById(R.id.have_not_data_id);
        orderMaintenanceAdapter=new OrderMaintenanceAdapter(OrderSearchActivity.this);
        getCompanyInfo(assetNum);

    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(description);
        seachImageView.setVisibility(View.GONE);


        layoutManager = new LinearLayoutManager(OrderSearchActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(orderMaintenanceAdapter);


        mSwipeLayout.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mSwipeLayout.setProgressViewOffset(false, 0,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));

        mSwipeLayout.setRefreshing(true);
    }

    private View.OnClickListener backImageViewOnClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };


    /**根据资产编号查询工单数据**/

    private void getCompanyInfo(String assetnum){

        HttpManager.getDataPagingInfo(OrderSearchActivity.this, Constants.getNumByOrder(assetnum, 1, 20), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results data) {
                Log.i(TAG, "data=" + data);

            }

            @Override
            public void onSuccess(Results data, int totalPages, int currentPage) {
                Log.i(TAG, "order data=" + data.getResultlist());
                mSwipeLayout.setRefreshing(false);
                /**解析返回的数据**/
                ArrayList<OrderMain> list =JsonUtils.parsingOrderMain(OrderSearchActivity.this,data.getResultlist());
                if (list == null || list.isEmpty()) {
                    notdatalayout.setVisibility(View.VISIBLE);
                } else {
                    orderMaintenanceAdapter.update(list, true);
                }
            }

            @Override
            public void onFailure(String error) {
                Log.i(TAG, "error=" + error);
                mSwipeLayout.setRefreshing(false);
                notdatalayout.setVisibility(View.VISIBLE);
            }
        });

    }




}
