package com.cdhxqh.bowei.ui.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cdhxqh.bowei.Dao.WorkerInfoDao;
import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.bean.OrderMain;
import com.cdhxqh.bowei.bean.WorkerInfo;
import com.cdhxqh.bowei.config.Constants;
import com.cdhxqh.bowei.manager.HttpManager;
import com.cdhxqh.bowei.manager.HttpRequestHandler;
import com.cdhxqh.bowei.ui.adapter.WorkerInfoAdapter;
import com.cdhxqh.bowei.utils.JsonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 员工信息页面
 * Created by think on 2015/8/25.
 */
@SuppressLint("ValidFragment")
public class WorkerFragment extends Fragment {
    private RecyclerView recyclerView;
    private WorkerInfoAdapter workerInfoAdapter;
    private ProgressDialog mProgressDialog;
    private LinearLayout nodatalayout;
    private String num;
    private int id;
    private OrderMain orderMain;
    public WorkerFragment(){
    }
    public WorkerFragment(OrderMain orderMain){
        this.orderMain = orderMain;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_1, container, false);
        nodatalayout = (LinearLayout) view.findViewById(R.id.have_not_data_id);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView = (RecyclerView) view.findViewById(R.id.worker_list);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        workerInfoAdapter = new WorkerInfoAdapter(getActivity());
        recyclerView.setAdapter(workerInfoAdapter);
        id = orderMain.getId();
        num = orderMain.getNumber();

        return view;
    }
    private boolean mHasLoadedOnce = false;
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (this.isVisible()) {
            // we check that the fragment is becoming visible
            if (isVisibleToUser && !mHasLoadedOnce && workerInfoAdapter.getItemCount()==0) {
                if(orderMain.getNumber().equals("")){
                    addData(id);
                }else {
                    getData();
                }
                // async http request here
                mHasLoadedOnce = true;
            }
        }
        super.setUserVisibleHint(isVisibleToUser);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        setUserVisibleHint(true);
        super.onActivityCreated(savedInstanceState);
    }

    private void getData(){
        mProgressDialog = ProgressDialog.show(getActivity(), null,
                getString(R.string.requesting), true, true);
        HttpManager.getData(getActivity(), Constants.getRealWorkerInfoUrl(num), new HttpRequestHandler<String>() {
            @Override
            public void onSuccess(String data) {
                mProgressDialog.dismiss();
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(data);
                    if (jsonObject.getString("errmsg").equals(getResources().getString(R.string.request_ok))) {
                        JsonUtils.parsingWorkInfo(getActivity(), jsonObject.getString("result"), id);
                        addData(id);
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
                addData(id);
                mProgressDialog.dismiss();
            }
        });

    }
    private void addData(int id) {
        ArrayList<WorkerInfo> list = new ArrayList<WorkerInfo>();
        List<WorkerInfo>workerInfoList = new WorkerInfoDao(getActivity()).queryByOrdermainId(id);
        for (int i = 0; i < workerInfoList.size(); i++) {
            list.add(i,workerInfoList.get(i));
        }
        if(workerInfoList.size()==0){
            nodatalayout.setVisibility(View.VISIBLE);
        }else {
            nodatalayout.setVisibility(View.GONE);
        }
        workerInfoAdapter.update(list, true);

    }
    public void adddata(WorkerInfo workerInfo){
        ArrayList<WorkerInfo> list = new ArrayList<WorkerInfo>();
        list.add(0,workerInfo);
        workerInfoAdapter.update(list, true);
        if(workerInfoAdapter.getItemCount()==0){
            nodatalayout.setVisibility(View.VISIBLE);
        }else {
            nodatalayout.setVisibility(View.GONE);
        }
    }
}
