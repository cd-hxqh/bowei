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
import com.cdhxqh.bowei.bean.Inventory;
import com.cdhxqh.bowei.bean.Knowledge;
import com.cdhxqh.bowei.bean.Results;
import com.cdhxqh.bowei.config.Constants;
import com.cdhxqh.bowei.manager.HttpManager;
import com.cdhxqh.bowei.manager.HttpRequestHandler;
import com.cdhxqh.bowei.ui.adapter.InventoryAdapter;
import com.cdhxqh.bowei.ui.widget.SwipeRefreshLayout;
import com.cdhxqh.bowei.utils.JsonUtils;

import java.util.ArrayList;

/**公司库存**/
public class In_Company_Activity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,SwipeRefreshLayout.OnLoadListener{
    private static final String TAG="In_Company_Activity";
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

    /**公司库存适配器**/
    InventoryAdapter inventoryAdapter;

    int page = 1;

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
        inventoryAdapter=new InventoryAdapter(In_Company_Activity.this);
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(getResources().getString(R.string.company_title_text));
        seachImageView.setImageResource(R.drawable.ic_search);
        seachImageView.setOnClickListener(seachOnClickListener);


        layoutManager = new LinearLayoutManager(In_Company_Activity.this);
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
            intent.setClass(In_Company_Activity.this,SearchActivity.class);
            intent.putExtra("search_mark",Constants.CINVENTORY_SEARCH);
            startActivityForResult(intent,0);
        }
    };



    /**获取公司库存信息**/

    private void getCompanyInfo(int page){

        HttpManager.getDataPagingInfo(In_Company_Activity.this, Constants.get_commany_inv(page, 20), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results data) {
                Log.i(TAG, "data=" + data);

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
                ArrayList<Inventory> list=JsonUtils.parsingInventory(In_Company_Activity.this,data.getResultlist());
                if(list==null||list.isEmpty()){
                    notdatalayout.setVisibility(View.VISIBLE);
                }else{
                    inventoryAdapter.adddate(list);
                }
            }

            @Override
            public void onFailure(String error) {
                Log.i(TAG, "error=" + error);
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
        inventoryAdapter = new InventoryAdapter(this);
        recyclerView.setAdapter(inventoryAdapter);
        getCompanyInfo(page);
    }

    @Override
    public void onLoad(){
        page++;
        getCompanyInfo(page);
    }

}
