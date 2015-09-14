package com.cdhxqh.bowei.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
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

import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.bean.Knowledge;
import com.cdhxqh.bowei.config.Constants;
import com.cdhxqh.bowei.manager.HttpManager;
import com.cdhxqh.bowei.manager.HttpRequestHandler;
import com.cdhxqh.bowei.ui.adapter.KnowKedgeAdapter;
import com.cdhxqh.bowei.utils.JsonUtils;

import java.util.ArrayList;

/**搜索界面**/
public class SearchActivity extends BaseActivity {

    private static final String TAG = "SearchActivity";

    /**
     * 搜索输入栏*
     */
    private EditText searchEdit;

    /**
     * 取消*
     */
    private TextView quxiaoBtn;


    /**适配器**/
    KnowKedgeAdapter knowKedgeAdapter;

    /**暂无数据**/
    private LinearLayout notdatalayout;

    private View view;

    private SwipeRefreshLayout mSwipeLayout = null;

    LinearLayoutManager layoutManager;

    public RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        findViewById();
        initView();
//        setSoftware();
    }

    @Override
    protected void findViewById() {
        searchEdit = (EditText) findViewById(R.id.search_edit_text_id);
        quxiaoBtn = (TextView) findViewById(R.id.search_quxiao_btn_id);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_id);
        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        notdatalayout=(LinearLayout)findViewById(R.id.have_not_data_id);
        knowKedgeAdapter=new KnowKedgeAdapter(SearchActivity.this);
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
        recyclerView.setAdapter(knowKedgeAdapter);


        mSwipeLayout.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mSwipeLayout.setProgressViewOffset(false, 0,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
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

    /**edit编辑事件**/
    private TextView.OnEditorActionListener searchOnEditorActionListener=new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == KeyEvent.ACTION_DOWN || actionId == EditorInfo.IME_ACTION_GO) {
                    setSoftware();
                    if(knowKedgeAdapter!=null){
                        knowKedgeAdapter=new KnowKedgeAdapter(SearchActivity.this);
                        recyclerView.setAdapter(knowKedgeAdapter);
                    }
                    mSwipeLayout.setRefreshing(true);
                    String str=searchEdit.getText().toString();
                    Log.i(TAG,"str="+str);

                    getKnowKegeInfo(str);
                }
                return true;
            }
    };



    /**获取知识库信息**/

    private void getKnowKegeInfo(String knowdesc){

        HttpManager.getDataInfo(SearchActivity.this, Constants.search_Knowledge(knowdesc), new HttpRequestHandler<String>() {
            @Override
            public void onSuccess(String data) {
                Log.i(TAG, "data=" + data);

                ArrayList<Knowledge> list = JsonUtils.parsingKnowKedge(SearchActivity.this, data);
                mSwipeLayout.setRefreshing(false);
                if (list == null || list.isEmpty()) {
                    notdatalayout.setVisibility(View.VISIBLE);
                } else {

                    knowKedgeAdapter.update(list, true);
                    notdatalayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onSuccess(String data, int totalPages, int currentPage) {
                Log.i(TAG, "data=" + data + "totalPages=" + totalPages + "currentPage=" + currentPage);
            }

            @Override
            public void onFailure(String error) {
                Log.i(TAG, "error=" + error);
            }
        });

    }




}
