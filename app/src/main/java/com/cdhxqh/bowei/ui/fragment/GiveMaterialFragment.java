package com.cdhxqh.bowei.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.bean.MaterialInfo;
import com.cdhxqh.bowei.bean.WorkerInfo;
import com.cdhxqh.bowei.ui.adapter.GiveMaterialAdapter;
import com.cdhxqh.bowei.ui.adapter.WorkerInfoAdapter;

import java.util.ArrayList;

/**
 * Created by think on 2015/8/25.
 */
public class GiveMaterialFragment extends Fragment {
    private RecyclerView recyclerView;
    private GiveMaterialAdapter giveMaterialAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_2, container, false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView = (RecyclerView) view.findViewById(R.id.give_material_list);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        giveMaterialAdapter = new GiveMaterialAdapter(getActivity());
        recyclerView.setAdapter(giveMaterialAdapter);
        addData();
        return view;
    }

    private void addData() {
        ArrayList<MaterialInfo> list = new ArrayList<MaterialInfo>();
        for (int i = 0; i < 3; i++) {
            MaterialInfo materialInfo = new MaterialInfo();
            materialInfo.setNumber("LSD588"+i);
            materialInfo.setName("螺丝刀");
            materialInfo.setSize(20+i);
            list.add(i,materialInfo);
        }
        giveMaterialAdapter.update(list, true);
    }
    public void adddata(MaterialInfo materialInfo){
        ArrayList<MaterialInfo> list = new ArrayList<MaterialInfo>();
        list.add(0,materialInfo);
        giveMaterialAdapter.update(list, true);
    }
}
