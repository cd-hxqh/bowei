package com.cdhxqh.bowei.ui.activity;

import android.content.Intent;
import android.os.Bundle;
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
import com.cdhxqh.bowei.bean.Deptinventory;
import com.cdhxqh.bowei.bean.Inventory;
import com.cdhxqh.bowei.bean.Results;
import com.cdhxqh.bowei.config.Constants;
import com.cdhxqh.bowei.manager.HttpManager;
import com.cdhxqh.bowei.manager.HttpRequestHandler;
import com.cdhxqh.bowei.ui.adapter.DeptinventoryAdapter;
import com.cdhxqh.bowei.ui.adapter.InventoryAdapter;
import com.cdhxqh.bowei.ui.widget.SwipeRefreshLayout;
import com.cdhxqh.bowei.utils.JsonUtils;

import java.util.ArrayList;

/**捷运库存**/
public class In_Deptinventory_Activity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener{
    private static final String TAG="In_Deptinventory_Activity";
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

    /**捷运库存**/
    DeptinventoryAdapter inventoryAdapter;

    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_company);
        findViewById();
        initView();
    }

    @Override
    protected void findViewById() {
        backImageView=(ImageView)findViewById(R.id.maintenance_title_back);

        titleTextView=(TextView)findViewById(R.id.title_name);

        seachImageView=(ImageView)findViewById(R.id.maintenance_title_add);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_id);

        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);

        notdatalayout=(LinearLayout)findViewById(R.id.have_not_data_id);
        inventoryAdapter=new DeptinventoryAdapter(In_Deptinventory_Activity.this);
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(getString(R.string.deptinventory_text));
        seachImageView.setImageResource(R.drawable.ic_search);
        seachImageView.setOnClickListener(seachOnClickListener);


        layoutManager = new LinearLayoutManager(In_Deptinventory_Activity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(inventoryAdapter);


//        mSwipeLayout.setColorScheme(android.R.color.holo_blue_bright,
//                android.R.color.holo_green_light,
//                android.R.color.holo_orange_light,
//                android.R.color.holo_red_light);
//        mSwipeLayout.setProgressViewOffset(false, 0,
//                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));

        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setOnLoadListener(this);
        mSwipeLayout.setRefreshing(true);
        getCompanyInfo(page);
    }

    private View.OnClickListener backImageViewOnClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };


    private View.OnClickListener seachOnClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent();
            intent.setClass(In_Deptinventory_Activity.this,SearchActivity.class);
            intent.putExtra("search_mark",Constants.DINVENTORY_SEARCH);
            startActivityForResult(intent,0);
        }
    };



    /**获取公司库存信息**/

    private void getCompanyInfo(int page){

        HttpManager.getDataPagingInfo(In_Deptinventory_Activity.this, Constants.get_deptinventory(page, 20), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results data) {

            }

            @Override
            public void onSuccess(Results data, int totalPages, int currentPage) {
                if(mSwipeLayout.isRefreshing()){
                    mSwipeLayout.setRefreshing(false);
                }
                if(mSwipeLayout.isLoading()){
                    mSwipeLayout.setLoading(false);
                }
                /**解析返回的数据**/
                ArrayList<Deptinventory> list=JsonUtils.parsingDeptinventory(In_Deptinventory_Activity.this, data.getResultlist());
                if(list==null||list.isEmpty()){
                    notdatalayout.setVisibility(View.VISIBLE);
                }else{
                    inventoryAdapter.adddate(list);
                }
            }

            @Override
            public void onFailure(String error) {
                if(mSwipeLayout.isRefreshing()){
                    mSwipeLayout.setRefreshing(false);
                }
                if(mSwipeLayout.isLoading()){
                    mSwipeLayout.setLoading(false);
                }
                notdatalayout.setVisibility(View.VISIBLE);
            }
        });

    }

    //下拉刷新触发事件
    @Override
    public void onRefresh() {
       page = 1;
        inventoryAdapter = new DeptinventoryAdapter(this);
        recyclerView.setAdapter(inventoryAdapter);
        getCompanyInfo(page);
    }

    //上拉加载更多触发事件
    @Override
    public void onLoad() {
        page++;
        getCompanyInfo(page);
    }

}
