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

import com.cdhxqh.bowei.Dao.MaterialInfoDao;
import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.bean.MaterialInfo;
import com.cdhxqh.bowei.bean.OrderMain;
import com.cdhxqh.bowei.config.Constants;
import com.cdhxqh.bowei.manager.HttpManager;
import com.cdhxqh.bowei.manager.HttpRequestHandler;
import com.cdhxqh.bowei.ui.adapter.RealMaterialConsumeAdapter;
import com.cdhxqh.bowei.utils.JsonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 实际物料信息页面
 * Created by think on 2015/8/25.
 */
@SuppressLint("ValidFragment")
public class RealMaterialConsunmeFragment extends Fragment {


    private RecyclerView recyclerView;
    private RealMaterialConsumeAdapter materialConsumeAdapter;
    private ProgressDialog mProgressDialog;
    private LinearLayout nodatalayout;
    String num;
    private int id;
    private OrderMain orderMain;
    public RealMaterialConsunmeFragment(){
    }
    public RealMaterialConsunmeFragment(OrderMain orderMain){
        this.orderMain = orderMain;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_5, container, false);
        nodatalayout = (LinearLayout) view.findViewById(R.id.have_not_data_id);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView = (RecyclerView) view.findViewById(R.id.material_consume_list);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        materialConsumeAdapter = new RealMaterialConsumeAdapter(getActivity());
        recyclerView.setAdapter(materialConsumeAdapter);
        id = orderMain.getId();
        num = orderMain.getNumber();
        if(!orderMain.getNumber().equals("")||orderMain.getNumber()!=null){
            getData();
        }
        return view;
    }

    private void getData(){
        mProgressDialog = ProgressDialog.show(getActivity(), null,
                getString(R.string.requesting), true, true);
        HttpManager.getData(getActivity(), Constants.getMeterialConsumeRealUrl(orderMain.getNumber()), new HttpRequestHandler<String>() {
            @Override
            public void onSuccess(String data) {
                mProgressDialog.dismiss();
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(data);
                    if (jsonObject.getString("errmsg").equals(getResources().getString(R.string.request_ok))) {
                        JsonUtils.parsingDeptmatusetrans(getActivity(), jsonObject.getString("result"), id);
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
    private void addData(int id) {
        ArrayList<MaterialInfo> list = new ArrayList<MaterialInfo>();
        List<MaterialInfo> materialInfoList = new MaterialInfoDao(getActivity()).queryByLabtransId(id,false);
        for (int i = 0; i < materialInfoList.size(); i++) {
            list.add(i,materialInfoList.get(i));
        }
        if(materialInfoList.size()==0){
            nodatalayout.setVisibility(View.VISIBLE);
        }else {
            nodatalayout.setVisibility(View.GONE);
        }
        materialConsumeAdapter.update(list, true);
    }
//    public void adddata(MaterialInfo materialInfo){
//        ArrayList<MaterialInfo> list = new ArrayList<MaterialInfo>();
//        list.add(0,materialInfo);
//        materialConsumeAdapter.update(list, true);
//    }
}
