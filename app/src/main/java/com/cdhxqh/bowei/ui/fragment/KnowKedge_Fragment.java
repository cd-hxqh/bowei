package com.cdhxqh.bowei.ui.fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.bean.Knowledge;
import com.cdhxqh.bowei.config.Constants;
import com.cdhxqh.bowei.manager.HttpManager;
import com.cdhxqh.bowei.manager.HttpRequestHandler;
import com.cdhxqh.bowei.ui.adapter.KnowKedgeAdapter;
import com.cdhxqh.bowei.ui.widget.SwipeRefreshLayout;
import com.cdhxqh.bowei.utils.JsonUtils;

import java.util.ArrayList;

/**
 *知识库 fragment
 */
public class KnowKedge_Fragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,SwipeRefreshLayout.OnLoadListener {

    private static final String TAG="KnowKedge_Fragment";
    /**适配器**/
    KnowKedgeAdapter knowKedgeAdapter;

    /**暂无数据**/
    private LinearLayout notdatalayout;

    private View view;

    private SwipeRefreshLayout mSwipeLayout = null;
    LinearLayoutManager layoutManager;
    public RecyclerView recyclerView;

    private int page = 1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_know_kedge, container, false);
        findByView();
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();

    }




    /**初始化控件**/
    private void findByView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_id);
        mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        notdatalayout=(LinearLayout)view.findViewById(R.id.have_not_data_id);
        knowKedgeAdapter=new KnowKedgeAdapter(getActivity());
    }

    /**初始化控件**/
    private void initView(){
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(knowKedgeAdapter);
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setOnLoadListener(this);

//        mSwipeLayout.setColorScheme(android.R.color.holo_blue_bright,
//                android.R.color.holo_green_light,
//                android.R.color.holo_orange_light,
//                android.R.color.holo_red_light);
//        mSwipeLayout.setProgressViewOffset(false, 0,
//                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));

        mSwipeLayout.setRefreshing(true);
//        mSwipeLayout.setLoading(true);


//        knowKedgeAdapter.update(addKnow(),true);

        getKnowKegeInfo();
    }


    /**添加测试数据**/
    private ArrayList<Knowledge> addKnow(){
        ArrayList<Knowledge> arrayList=new ArrayList<Knowledge>();
        for (int i=0;i<10;i++){
            Knowledge knowledge=new Knowledge();
            knowledge.setKnowledgeid(i);
            knowledge.setKnowdesc("关于螺丝刀刀片的使用情况");
            arrayList.add(knowledge);
        }

        return arrayList;
    }


    /**获取知识库信息**/
    private void getKnowKegeInfo(){
        HttpManager.getDataInfo(getActivity(), Constants.KNOW_LEDGE_LIST, new HttpRequestHandler<String>() {
            @Override
            public void onSuccess(String data) {
                Log.i(TAG, "data=" + data);
                ArrayList<Knowledge> list = JsonUtils.parsingKnowKedge(getActivity(),data);
                mSwipeLayout.setRefreshing(false);
                if(list==null||list.isEmpty()){
                    notdatalayout.setVisibility(View.VISIBLE);
                }else{
                    knowKedgeAdapter.update(list,true);
                }
            }

            @Override
            public void onSuccess(String data, int totalPages, int currentPage) {
                Log.i(TAG, "data=" + data + "totalPages=" + totalPages + "currentPage=" + currentPage);
            }

            @Override
            public void onFailure(String error) {
                Toast.makeText(getActivity(),"获取知识库信息失败",Toast.LENGTH_SHORT).show();
                Log.i(TAG, "error=" + error);
            }
        });
    }

    /**刷新知识库信息**/
    private void refreshKnowKegeInfo(){
        page=1;
        HttpManager.getDataInfo(getActivity(), Constants.KNOW_LEDGE_LIST, new HttpRequestHandler<String>() {
            @Override
            public void onSuccess(String data) {
                Log.i(TAG, "data=" + data);
                ArrayList<Knowledge> list = JsonUtils.parsingKnowKedge(getActivity(),data);
                mSwipeLayout.setRefreshing(false);
                if(list==null||list.isEmpty()){
                    notdatalayout.setVisibility(View.VISIBLE);
                }else{
                    knowKedgeAdapter = new KnowKedgeAdapter(getActivity());
                    recyclerView.setAdapter(knowKedgeAdapter);
                    knowKedgeAdapter.update(list,true);
                }
            }

            @Override
            public void onSuccess(String data, int totalPages, int currentPage) {
                Log.i(TAG, "data=" + data + "totalPages=" + totalPages + "currentPage=" + currentPage);
            }

            @Override
            public void onFailure(String error) {
                Toast.makeText(getActivity(),"获取知识库信息失败",Toast.LENGTH_SHORT).show();
                Log.i(TAG, "error=" + error);
            }
        });
    }

    /**加载更多知识库信息**/
    private void addmoreKnowKegeInfo(int page){
        HttpManager.getDataInfo(getActivity(), Constants.getKnow_ledge_list(page), new HttpRequestHandler<String>() {
            @Override
            public void onSuccess(String data) {
                Log.i(TAG, "data=" + data);
                ArrayList<Knowledge> list = JsonUtils.parsingKnowKedge(getActivity(),data);
                mSwipeLayout.setLoading(false);
                if(list==null||list.isEmpty()){
                    notdatalayout.setVisibility(View.VISIBLE);
                }else{
                    knowKedgeAdapter.adddate(list);
                }
            }

            @Override
            public void onSuccess(String data, int totalPages, int currentPage) {
                Log.i(TAG, "data=" + data + "totalPages=" + totalPages + "currentPage=" + currentPage);
            }

            @Override
            public void onFailure(String error) {
                mSwipeLayout.setLoading(false);
                Toast.makeText(getActivity(),"获取知识库信息失败",Toast.LENGTH_SHORT).show();
                Log.i(TAG, "error=" + error);
            }
        });
    }

    //下拉刷新触发事件
    @Override
    public void onRefresh() {
        refreshKnowKegeInfo();
    }
    //上拉加载更多触发事件
    @Override
    public void onLoad(){
        page++;
        addmoreKnowKegeInfo(page);
    }


}
