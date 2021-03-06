package com.cdhxqh.bowei.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.bean.Deptinventory;
import com.cdhxqh.bowei.bean.Inventory;
import com.cdhxqh.bowei.bean.Knowledge;
import com.cdhxqh.bowei.bean.Results;
import com.cdhxqh.bowei.config.Constants;
import com.cdhxqh.bowei.manager.HttpManager;
import com.cdhxqh.bowei.manager.HttpRequestHandler;
import com.cdhxqh.bowei.ui.adapter.DeptinventoryAdapter;
import com.cdhxqh.bowei.ui.adapter.InventoryAdapter;
import com.cdhxqh.bowei.ui.adapter.KnowKedgeAdapter;
import com.cdhxqh.bowei.ui.widget.SwipeRefreshLayout;
import com.cdhxqh.bowei.utils.JsonUtils;

import java.util.ArrayList;

/**
 * 搜索界面*
 */
public class SearchActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {

    private static final String TAG = "SearchActivity";

    /**
     * 搜索输入栏*
     */
    private EditText searchEdit;

    /**
     * 取消*
     */
    private TextView quxiaoBtn;


    /**
     * 知识库适配器*
     */
    KnowKedgeAdapter knowKedgeAdapter;
    /**
     * 公司库存适配器*
     */
    InventoryAdapter inventoryAdapter;

    /**
     * 捷运库存*
     */
    DeptinventoryAdapter dInventoryAdapter;

    /**
     * 暂无数据*
     */
    private LinearLayout notdatalayout;

    private View view;

    private SwipeRefreshLayout mSwipeLayout = null;

    LinearLayoutManager layoutManager;

    public RecyclerView recyclerView;


    /**
     * 搜索标识*
     */
    private int searchMark;
    private String searchtype;

    private int page = 1;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getInitData();
        findViewById();
        initView();
    }

    /**
     * 获取上个界面的数据*
     */
    private void getInitData() {
        searchMark = getIntent().getExtras().getInt("search_mark");
        if (searchMark == Constants.KNOWKEDGE_SEARCH) {
            searchtype = getIntent().getExtras().getString("search_type");
        }
    }

    @Override
    protected void findViewById() {
        searchEdit = (EditText) findViewById(R.id.search_edit_text_id);
        quxiaoBtn = (TextView) findViewById(R.id.search_quxiao_btn_id);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_id);
        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        notdatalayout = (LinearLayout) findViewById(R.id.have_not_data_id);
        if (searchMark == Constants.KNOWKEDGE_SEARCH) {
            knowKedgeAdapter = new KnowKedgeAdapter(SearchActivity.this);
        } else if (searchMark == Constants.CINVENTORY_SEARCH) {
            inventoryAdapter = new InventoryAdapter(SearchActivity.this);
        } else if (searchMark == Constants.DINVENTORY_SEARCH) {
            dInventoryAdapter = new DeptinventoryAdapter(SearchActivity.this);
        }
    }

    @Override
    protected void initView() {
        quxiaoBtn.setOnClickListener(quxiaoBtnOnClickListener);
        searchEdit.setImeOptions(EditorInfo.IME_ACTION_GO);

        searchEdit.setOnEditorActionListener(searchOnEditorActionListener);

        layoutManager = new LinearLayoutManager(SearchActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        if (searchMark == Constants.KNOWKEDGE_SEARCH) {
            recyclerView.setAdapter(knowKedgeAdapter);
        } else if (searchMark == Constants.CINVENTORY_SEARCH) {
            recyclerView.setAdapter(inventoryAdapter);
        } else if (searchMark == Constants.DINVENTORY_SEARCH) {
            recyclerView.setAdapter(dInventoryAdapter);
        }


//        mSwipeLayout.setColorScheme(android.R.color.holo_blue_bright,
//                android.R.color.holo_green_light,
//                android.R.color.holo_orange_light,
//                android.R.color.holo_red_light);
//        mSwipeLayout.setProgressViewOffset(false, 0,
//                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setOnLoadListener(this);
        mSwipeLayout.setRefreshing(false);


    }

    private View.OnClickListener quxiaoBtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    /**
     * 设置软键盘*
     */
    private void setSoftware() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * edit编辑事件*
     */
    private TextView.OnEditorActionListener searchOnEditorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == KeyEvent.ACTION_DOWN || actionId == EditorInfo.IME_ACTION_GO) {
                setSoftware();

                if (knowKedgeAdapter != null && searchMark == Constants.KNOWKEDGE_SEARCH) {
                    knowKedgeAdapter = new KnowKedgeAdapter(SearchActivity.this);
                    recyclerView.setAdapter(knowKedgeAdapter);
                } else if (inventoryAdapter != null && searchMark == Constants.CINVENTORY_SEARCH) {
                    inventoryAdapter = new InventoryAdapter(SearchActivity.this);
                    recyclerView.setAdapter(inventoryAdapter);
                } else if (dInventoryAdapter != null && searchMark == Constants.DINVENTORY_SEARCH) {
                    dInventoryAdapter = new DeptinventoryAdapter(SearchActivity.this);
                    recyclerView.setAdapter(dInventoryAdapter);
                }
                mSwipeLayout.setRefreshing(true);
                str = searchEdit.getText().toString();
                if (searchMark == Constants.KNOWKEDGE_SEARCH) { //知识库查询
                    getKnowKegeInfo(page,str);
                } else if (searchMark == Constants.CINVENTORY_SEARCH) { //公司库存查询
                    getCInventoryInfo(page,str);
                } else if (searchMark == Constants.DINVENTORY_SEARCH) { //捷运库存查询
                    getDInventoryInfo(page,str);
                }
            }
            return true;
        }


    };


    /**
     * 获取知识库信息*
     */

    private void getKnowKegeInfo(int page,String knowxl) {
        HttpManager.getDataInfo(SearchActivity.this, Constants.search_Knowledge(page, 20,searchtype, knowxl), new HttpRequestHandler<String>() {
            @Override
            public void onSuccess(String data) {
                Log.i(TAG, "data=" + data);
                ArrayList<Knowledge> list = JsonUtils.parsingKnowKedge(SearchActivity.this, data);
                if(mSwipeLayout.isRefreshing()){
                    mSwipeLayout.setRefreshing(false);
                }
                if(mSwipeLayout.isLoading()){
                    mSwipeLayout.setLoading(false);
                }
                if (list == null || list.isEmpty()) {
                    notdatalayout.setVisibility(View.VISIBLE);
                } else {
                    knowKedgeAdapter.adddate(list);
                }
            }

            @Override
            public void onSuccess(String data, int totalPages, int currentPage) {
                Log.i(TAG, "data=" + data + "totalPages=" + totalPages + "currentPage=" + currentPage);
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
                Log.i(TAG, "error=" + error);
            }
        });

    }


    /**
     * 获取公司库存信息*
     */
    private void getCInventoryInfo(int page,String description) {

        HttpManager.getDataPagingInfo(SearchActivity.this, Constants.search_commany_inv(description, page, 20), new HttpRequestHandler<Results>() {

            @Override
            public void onSuccess(Results data) {
            }

            @Override
            public void onSuccess(Results data, int totalPages, int currentPage) {
                /**解析返回的数据**/
                ArrayList<Inventory> list = JsonUtils.parsingInventory(SearchActivity.this, data.getResultlist());
                if(mSwipeLayout.isRefreshing()){
                    mSwipeLayout.setRefreshing(false);
                }
                if(mSwipeLayout.isLoading()){
                    mSwipeLayout.setLoading(false);
                }
                if (list == null || list.isEmpty()) {
                    notdatalayout.setVisibility(View.VISIBLE);
                } else {
                    inventoryAdapter.adddate(list);
                    notdatalayout.setVisibility(View.GONE);
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

    /**
     * 获取捷运库存信息*
     */
    private void getDInventoryInfo(int page,String description) {

        HttpManager.getDataPagingInfo(SearchActivity.this, Constants.search_deptinventory(description, page, 20), new HttpRequestHandler<Results>() {

            @Override
            public void onSuccess(Results data) {
            }

            @Override
            public void onSuccess(Results data, int totalPages, int currentPage) {
                /**解析返回的数据**/
                ArrayList<Deptinventory> list = JsonUtils.parsingDeptinventory(SearchActivity.this, data.getResultlist());
                if(mSwipeLayout.isRefreshing()){
                    mSwipeLayout.setRefreshing(false);
                }
                if(mSwipeLayout.isLoading()){
                    mSwipeLayout.setLoading(false);
                }
                if (list == null || list.isEmpty()) {
                    notdatalayout.setVisibility(View.VISIBLE);
                } else {
                    dInventoryAdapter.adddate(list);
                    notdatalayout.setVisibility(View.GONE);
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
        str = searchEdit.getText().toString();
        if (searchMark == Constants.KNOWKEDGE_SEARCH) {
            knowKedgeAdapter = new KnowKedgeAdapter(this);
            recyclerView.setAdapter(knowKedgeAdapter);
            getKnowKegeInfo(page,str);
        } else if (searchMark == Constants.CINVENTORY_SEARCH) {
            inventoryAdapter = new InventoryAdapter(this);
            recyclerView.setAdapter(inventoryAdapter);
            getCInventoryInfo(page,str);
        } else if (searchMark == Constants.DINVENTORY_SEARCH) {
            dInventoryAdapter = new DeptinventoryAdapter(this);
            recyclerView.setAdapter(dInventoryAdapter);
            getDInventoryInfo(page,str);
        }
    }

    //上拉加载更多触发事件
    @Override
    public void onLoad() {
        page++;
        String str = searchEdit.getText().toString();
        if (searchMark == Constants.KNOWKEDGE_SEARCH) {
            getKnowKegeInfo(page,str);
        } else if (searchMark == Constants.CINVENTORY_SEARCH) {
            getCInventoryInfo(page,str);
        } else if (searchMark == Constants.DINVENTORY_SEARCH) {
            getDInventoryInfo(page,str);
        }
    }
}