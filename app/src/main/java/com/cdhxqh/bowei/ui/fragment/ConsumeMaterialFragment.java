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

import com.cdhxqh.bowei.Dao.JobMaterialDao;
import com.cdhxqh.bowei.Dao.JobPlanDao;
import com.cdhxqh.bowei.Dao.MaterialInfoDao;
import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.bean.Jobmaterial;
import com.cdhxqh.bowei.bean.Jobplan;
import com.cdhxqh.bowei.bean.MaterialInfo;
import com.cdhxqh.bowei.bean.OrderMain;
import com.cdhxqh.bowei.bean.WorkerInfo;
import com.cdhxqh.bowei.config.Constants;
import com.cdhxqh.bowei.manager.HttpManager;
import com.cdhxqh.bowei.manager.HttpRequestHandler;
import com.cdhxqh.bowei.ui.adapter.ConsumeMaterialAdapter;
import com.cdhxqh.bowei.ui.adapter.GiveMaterialAdapter;
import com.cdhxqh.bowei.utils.JsonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 计划物料消耗页面
 * Created by think on 2015/8/25.
 */
@SuppressLint("ValidFragment")
public class ConsumeMaterialFragment extends Fragment {
    private RecyclerView recyclerView;
    private ConsumeMaterialAdapter consumeMaterialAdapter;
    private ProgressDialog mProgressDialog;
    private LinearLayout nodatalayout;
    String num;
    private int id;
    private OrderMain orderMain;

    public ConsumeMaterialFragment() {
    }

    public ConsumeMaterialFragment(OrderMain orderMain) {
        this.orderMain = orderMain;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_3, container, false);
        nodatalayout = (LinearLayout) view.findViewById(R.id.have_not_data_id);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView = (RecyclerView) view.findViewById(R.id.consume_material_list);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        consumeMaterialAdapter = new ConsumeMaterialAdapter(getActivity());
        recyclerView.setAdapter(consumeMaterialAdapter);
        id = orderMain.getId();
        num = orderMain.getNumber();
//        if (orderMain.isNew()) {
//            getlocationData();
//        } else {
//            getData();
//        }
        return view;
    }
    private boolean mHasLoadedOnce = false;
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (this.isVisible()) {
            // we check that the fragment is becoming visible
            if (isVisibleToUser && !mHasLoadedOnce && consumeMaterialAdapter.getItemCount()==0) {
                Toast.makeText(getActivity(),"计划",Toast.LENGTH_SHORT).show();
                // async http request here
                if (orderMain.isNew()) {
                    getlocationData();
                } else {
                    getData();
                }
                mHasLoadedOnce = true;
            }
        }
        super.setUserVisibleHint(isVisibleToUser);
    }


    private void getData() {
        mProgressDialog = ProgressDialog.show(getActivity(), null,
                getString(R.string.requesting), true, true);
        HttpManager.getData(getActivity(), Constants.getMeterialConsumePlanUrl(num), new HttpRequestHandler<String>() {
            @Override
            public void onSuccess(String data) {
                mProgressDialog.dismiss();
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(data);
                    if (jsonObject.getString("errmsg").equals(getResources().getString(R.string.request_ok))) {
                        JsonUtils.parsingWpMaterial(getActivity(), jsonObject.getString("result"), id);


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
                mProgressDialog.dismiss();
            }
        });
        addData(id);
    }

    private void getlocationData() {
        if (new MaterialInfoDao(getActivity()).queryByLabtransId(id, true).size() == 0) {
            Jobplan jobplan = new JobPlanDao(getActivity()).queryByJobNum(orderMain.getWorkplan());
            List<Jobmaterial> jobmaterialList = new JobMaterialDao(getActivity()).queryByJobPlanId(jobplan.getJOBPLANID());
            MaterialInfo materialInfo;
            for (int i = 0; i < jobmaterialList.size(); i++) {
                materialInfo = new MaterialInfo();
                materialInfo.setBelongorderid(orderMain.getId());
                materialInfo.setName(jobmaterialList.get(i).getITEMDESC());
                materialInfo.setNumber(jobmaterialList.get(i).getITEMNUM());
                if (jobmaterialList.get(i).getITEMQTY() != null) {
                    materialInfo.setSize(Integer.parseInt(jobmaterialList.get(i).getITEMQTY()));
                }
                materialInfo.setWarehouse(jobmaterialList.get(i).getWarehouse());
                materialInfo.setIsPlan(true);
                new MaterialInfoDao(getActivity()).update(materialInfo);
            }
        }
        addData(id);
    }

    private void addData(int id) {
        ArrayList<MaterialInfo> list = new ArrayList<MaterialInfo>();
        List<MaterialInfo> materialInfoList = new MaterialInfoDao(getActivity()).queryByLabtransId(id, true);
        for (int i = 0; i < materialInfoList.size(); i++) {
            list.add(i, materialInfoList.get(i));
        }
        if (materialInfoList.size() == 0) {
            nodatalayout.setVisibility(View.VISIBLE);
        } else {
            nodatalayout.setVisibility(View.GONE);
        }
        consumeMaterialAdapter.update(list, true);
    }
}
